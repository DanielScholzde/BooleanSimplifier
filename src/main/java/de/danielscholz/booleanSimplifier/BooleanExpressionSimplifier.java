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
        FLIP, OPTIONAL, MATCH_UNSHARP
    }

    static {
        // s bedeutet: true | false | Variable | Methodenaufruf mit boolean-Returnwert
        // a, b, c bedeuten: Komplexe boolesche Ausdrücke ODER das gleiche wie s
        rule(" true && a", "a", FLIP);
        rule("false && a", "false", FLIP);

        rule(" true || a", "true", FLIP);
        rule("false || a", "a", FLIP);

        rule(" a  &&  a", "a");
        rule("!a  &&  a", "false", FLIP);

        rule(" a || a", "a");
        rule("!a || a", "true", FLIP);

        rule("true == false", "false", FLIP);
        rule("true != false", "true", FLIP);

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

        rule("(!a || b) && a", "a && b", FLIP, MATCH_UNSHARP);
        rule("( a || b) && !a", "!a && b", FLIP, MATCH_UNSHARP);
        rule("(b || !a) && a", "a && b", FLIP, MATCH_UNSHARP);
        rule("(b || a ) && !a", "!a && b", FLIP, MATCH_UNSHARP);

        rule("a ? b : c", "a && b || !a && c", OPTIONAL);

        finishRule("(a)", "a");
        finishRule("a && b || !a && c", "a ? b : c", FLIP, MATCH_UNSHARP);
        finishRule("!a ? b : c", "a ? c : b");
    }

    public static void main(String[] args) {
        String input = "!(true && false || !true == false)";
        //String input = "(false && true)";
        Expression expr = simplify(parse(input));
        System.out.println(expr.toString());
    }

    public static Expression simplify(Expression expr) {
        return simplify(expr, true);
    }

    public static Expression simplify(Expression expr, boolean applyFinishRules) {
        Set<Expression> simplified = new TreeSet<>((o1, o2) -> o1.getComplexity() - o2.getComplexity());
        while (true) {
            Expression result = expr;
            expr = expr.removeUnnecessaryParenthesis();
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
                expr = expr.applyToThis(rule); // Regeln hier nur Top-Level anwenden
            }
            simplified.add(expr);
        }

        return simplified.iterator().next();
        //return expr;
    }

    static Expression parse(String expression) {
        ANTLRInputStream input = new ANTLRInputStream(expression); // create a CharStream that reads from standard input
        BooleanGrammarLexer lexer = new BooleanGrammarLexer(input); // create a lexer that feeds off of input CharStream
        CommonTokenStream tokens = new CommonTokenStream(lexer); // create a buffer of tokens pulled from the lexer
        BooleanGrammarParser parser = new BooleanGrammarParser(tokens); // create a parser that feeds off the tokens buffer
        parser.addErrorListener(new ANTLRErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) {
                throw new RuntimeException("Fehler im Ausdruck: " + s);
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
        //if (!parser.isMatchedEOF()) throw new RuntimeException("Ausdruck wurde nicht vollständig geparst");
        // System.out.println(tree.toStringTree(parser)); // print LISP-style tree
        ParseTreeWalker walker = new ParseTreeWalker(); // create standard walker
        BooleanGrammarListenerImpl listener = new BooleanGrammarListenerImpl();
        walker.walk(listener, tree);
        return listener.getExpression();
    }

    private static void rule(String expr, String result, RuleOption... options) {
        List<RuleOption> types = Arrays.asList(options);
        rule(expr, result, types.contains(OPTIONAL), types.contains(FLIP), false, types.contains(MATCH_UNSHARP));
    }

    private static void finishRule(String expr, String result, RuleOption... options) {
        List<RuleOption> types = Arrays.asList(options);
        rule(expr, result, false, types.contains(FLIP), true, types.contains(MATCH_UNSHARP));
    }

    private static void rule(String expr, String result, boolean optional, boolean matcherFlipSides, boolean finishRule, boolean matchUnsharp) {
        Expression ruleResultExpr;
        if (result.equals("true") || result.equals("false") || result.equals("a")) {
            ruleResultExpr = new AtomicExpression(result);
        } else {
            ruleResultExpr = parse(result);
        }
        Expression ruleExpr = parse(expr);

        Rule rule = new Rule(ruleExpr, ruleResultExpr, optional, matchUnsharp);
        if (finishRule) {
            finishingRules.add(rule);
        } else {
            rules.add(rule);
            if (matcherFlipSides) {
                rules.add(new Rule(ruleExpr.flipSides(), ruleResultExpr));
            }
        }
    }

}
