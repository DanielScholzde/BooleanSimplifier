package de.danielscholz.booleanSimplifier.expression;

import de.danielscholz.booleanSimplifier.BooleanExpressionSimplifier;
import de.danielscholz.booleanSimplifier.Rule;
import de.danielscholz.booleanSimplifier.expression.TwoValExpression.Type;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel on 22.04.2017.
 */
public abstract class Expression implements Serializable {

    private int complexity = -1;

    /**
     * Applies the rule to this expression. If the rule matches, the changed expression is returned.
     * Otherwise, the rule is applied to the children.
     */
    public final Expression apply(Rule rule) {
        Expression newExpression = applyToThis(rule);
        if (newExpression != this) {
            return newExpression;
        }
        return applyToChildren(rule);
    }

    /**
     * Applies the rule only to this exact expression and not to the child elements.
     */
    public final Expression applyToThis(Rule rule) {
        Map<String, Expression> vars = matches(rule.getExpr(), false, rule.isFuzzyMatch());
        if (vars != null) {
            return rule.getResult().applyMatch(vars).removeUnnecessaryParentheses();
        }
        return this;
    }

    /**
     * Applies the rule to the child elements.
     */
    protected abstract Expression applyToChildren(Rule rule);

    /**
     * Tests if the expression 'rule' matches this expression. If yes, a map with the concrete values for the variables
     * from the expression 'rule' is returned. If no variable is contained in 'rule', an empty map is returned.
     */
    protected abstract Map<String, Expression> matches(Expression rule, boolean invert, boolean fuzzyMatch);

    /**
     * Applies variable replacement to this expression, which is stored in the map 'vars'
     */
    protected abstract Expression applyMatch(Map<String, Expression> vars);

    protected Map<String, Expression> matchAtomicVar(Expression rule, boolean invert) {
        if (rule.isAtomicVar() || rule.isAtomicVarS() && getClass() == AtomicExpression.class) {
            Map<String, Expression> vars = new HashMap<>();
            vars.put(((AtomicExpression) rule).getStr(), !invert ? this :
                    BooleanExpressionSimplifier.simplify(new NotExpression(new ParenthesisExpression(this)), false));
            return vars;
        }
        return null;
    }

    /**
     * Removes unnecessary parentheses from each child expression of this expression.
     */
    public abstract Expression removeUnnecessaryParentheses();

    /**
     * Removes unnecessary parentheses around the child expression.
     */
    protected final Expression removeUnnecessaryParenthesesIntern(Expression child, int pos) {
        Expression child_ = child.removeUnnecessaryParentheses();
        if (child_ instanceof ParenthesisExpression) {
            Expression childChild = ((ParenthesisExpression) child_).getChild();
            if (!needParenthesis(childChild, pos)) {
                child_ = childChild;
            }
        }
        return child_;
    }

    /**
     * Adds a parenthesis around the child expression if necessary.
     */
    protected final Expression addNecessaryParenthesisIntern(Expression child, int pos) {
        if (needParenthesis(child, pos)) {
            return new ParenthesisExpression(child);
        }
        return child;
    }

    /**
     * Is a parenthesis needed if the child expression is set to position pos in this expression?
     */
    protected boolean needParenthesis(Expression child, int pos) {
        if (getPrecedence() > child.getPrecedence()) {
            return false;
        } else if (getPrecedence() == child.getPrecedence()) {
            if (getPrecedence() == Type.or.getPrecedence()
                    || getPrecedence() == Type.and.getPrecedence()
                    || getPrecedence() == NotExpression.PRECEDENCE
                    || getPrecedence() == ParenthesisExpression.PRECEDENCE) {
                return false;
            }
        }
        return true;
    }

    /**
     * is this rule expression an 'a', 'b' or 'c'?
     */
    protected boolean isAtomicVar() {
        return false;
    }

    /**
     * is this rule expression an 's'
     */
    protected boolean isAtomicVarS() {
        return false;
    }

    protected abstract int getPrecedence();

    public Expression invertSides() {
        return this;
    }

    public int getComplexity() {
        if (complexity < 0) {
            complexity = getComplexityIntern();
        }
        return complexity;
    }

    protected abstract int getComplexityIntern();

    /**
     * Combines two sets of variables created by two matches. If a variable is present in both maps, the value of this
     * variable must be equal. Otherwise null will be returned.
     */
    protected Map<String, Expression> joinVars(Map<String, Expression> vars1, Map<String, Expression> vars2) {
        if (vars1 != null && vars2 != null) {
            Map<String, Expression> result = new HashMap<>(vars1);
            for (Map.Entry<String, Expression> entry : vars2.entrySet()) {
                if (!result.containsKey(entry.getKey())) {
                    result.put(entry.getKey(), entry.getValue());
                    continue;
                }
                if (result.get(entry.getKey()).equals(entry.getValue())) {
                    continue;
                }
                // because the value of this variable is different, null is returned
                return null;
            }
            return result;
        }
        return null;
    }

}
