package de.danielscholz.booleanSimplifier;

import de.danielscholz.booleanSimplifier.expression.AtomicExpression;
import de.danielscholz.booleanSimplifier.expression.Expression;
import de.danielscholz.booleanSimplifier.grammar.BooleanGrammarLexer;
import de.danielscholz.booleanSimplifier.grammar.BooleanGrammarParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.*;

import static de.danielscholz.booleanSimplifier.BooleanExpressionSimplifier.RuleOption.*;

/**
 *
 */
public class BooleanExpressionSimplifier {

    private static final List<Rule> rules = new ArrayList<>();
    private static final List<Rule> finishingRules = new ArrayList<>();

    enum RuleOption {
        ALSO_ADD_INVERTED_RULE, OPTIONAL, FUZZY_MATCH
    }

    static {
        // s means: true | false | variable | method call with boolean result
        // a, b, c means: Complex boolean expressions | the same as s
        rule(" true && a", "a", ALSO_ADD_INVERTED_RULE);
        rule("false && a", "false", ALSO_ADD_INVERTED_RULE);

        rule(" true || a", "true", ALSO_ADD_INVERTED_RULE);
        rule("false || a", "a", ALSO_ADD_INVERTED_RULE);

        rule(" a  &&  a", "a");
        rule("!a  &&  a", "false", ALSO_ADD_INVERTED_RULE);

        rule(" a || a", "a");
        rule("!a || a", "true", ALSO_ADD_INVERTED_RULE);

        rule("true == false", "false", ALSO_ADD_INVERTED_RULE);
        rule("true != false", "true", ALSO_ADD_INVERTED_RULE);

        rule("a == a", "true");
        rule("a != a", "false");

        rule("!false", "true");
        rule("!true ", "false");
        rule("!!a   ", "a");
        rule("!(a || b)", "!a && !b", OPTIONAL);
        rule("!(a && b)", "!a || !b", OPTIONAL);
        rule("!(a == b)", "a != b", OPTIONAL);
        rule("!(a != b)", "a == b", OPTIONAL);

        rule("a ^ b", "a != b");

        rule("!(a <  b)", "a >= b");
        rule("!(a <= b)", "a >  b");
        rule("!(a >  b)", "a <= b");
        rule("!(a >= b)", "a <  b");

        rule("(!a || b) && a", "a && b", ALSO_ADD_INVERTED_RULE, FUZZY_MATCH);
        rule("( a || b) && !a", "!a && b", ALSO_ADD_INVERTED_RULE, FUZZY_MATCH);
        rule("(b || !a) && a", "a && b", ALSO_ADD_INVERTED_RULE, FUZZY_MATCH);
        rule("(b || a ) && !a", "!a && b", ALSO_ADD_INVERTED_RULE, FUZZY_MATCH);

        rule("a ? b : c", "a && b || !a && c", OPTIONAL);

        finishRule("(a)", "a");
        finishRule("a && b || !a && c", "a ? b : c", ALSO_ADD_INVERTED_RULE, FUZZY_MATCH);
        finishRule("!a ? b : c", "a ? c : b");
    }

    public static String simplify(String expr) {
        return simplify(parse(expr)).toString();
    }

    public static Expression simplify(Expression expr) {
        return simplify(expr, true);
    }

    public static Expression simplify(Expression expr, boolean applyFinishRules) {
        Set<Expression> simplified = new TreeSet<>(Comparator.comparingInt(Expression::getComplexity));
        while (true) {
            Expression result = expr;
            expr = expr.removeUnnecessaryParentheses();
            simplified.add(expr);
            //Map<Expression, Rule> matchedRules = new HashMap<>();
            for (Rule rule : rules) {
                //if (rule.equals(matchedRules.get(expr))) continue;
                Expression exprNew = expr.apply(rule);
                if (exprNew != expr) {
                    //matchedRules.put(expr, rule);
                    expr = exprNew;
                    simplified.add(expr);
                }
            }
            if (expr == result) break;
        }

        if (applyFinishRules) {
            for (Rule rule : finishingRules) {
                expr = expr.applyToThis(rule); // apply rules here only at top level
            }
            simplified.add(expr);
        }

        return simplified.iterator().next();
        //return expr;
    }

    static Expression parse(String expression) {
        CodePointCharStream stream = CharStreams.fromString(expression);
        BooleanGrammarLexer lexer = new BooleanGrammarLexer(stream); // create a lexer that feeds off of input CharStream
        CommonTokenStream tokens = new CommonTokenStream(lexer); // create a buffer of tokens pulled from the lexer
        BooleanGrammarParser parser = new BooleanGrammarParser(tokens); // create a parser that feeds off the tokens buffer
        parser.addErrorListener(new ANTLRErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) {
                throw new RuntimeException("Error in expression: " + s);
            }

            @Override
            public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean b, BitSet bitSet, ATNConfigSet atnConfigSet) {
            }

            @Override
            public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitSet, ATNConfigSet atnConfigSet) {
            }

            @Override
            public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atnConfigSet) {
            }
        });
        ParseTree tree = parser.expression(); // begin parsing at init expr
        //if (!parser.isMatchedEOF()) throw new RuntimeException("Expression was not completely parsed");
        // System.out.println(tree.toStringTree(parser)); // print LISP-style tree
        ParseTreeWalker walker = new ParseTreeWalker(); // create standard walker
        BooleanGrammarListenerImpl listener = new BooleanGrammarListenerImpl();
        walker.walk(listener, tree);
        return listener.getExpression();
    }

    private static void rule(String expr, String result, RuleOption... options) {
        List<RuleOption> types = Arrays.asList(options);
        rule(expr, result, types.contains(OPTIONAL), types.contains(ALSO_ADD_INVERTED_RULE), false, types.contains(FUZZY_MATCH));
    }

    private static void finishRule(String expr, String result, RuleOption... options) {
        List<RuleOption> types = Arrays.asList(options);
        rule(expr, result, false, types.contains(ALSO_ADD_INVERTED_RULE), true, types.contains(FUZZY_MATCH));
    }

    private static void rule(String expr, String result, boolean optional, boolean alsoAddInvertedRule, boolean finishRule, boolean fuzzyMatch) {
        Expression ruleResultExpr;
        if (result.equals("true") || result.equals("false") || result.equals("a")) {
            ruleResultExpr = new AtomicExpression(result);
        } else {
            ruleResultExpr = parse(result);
        }
        Expression ruleExpr = parse(expr);

        Rule rule = new Rule(ruleExpr, ruleResultExpr, optional, fuzzyMatch);
        if (finishRule) {
            finishingRules.add(rule);
        } else {
            rules.add(rule);
            if (alsoAddInvertedRule) {
                rules.add(new Rule(ruleExpr.invertSides(), ruleResultExpr));
            }
        }
    }

}
