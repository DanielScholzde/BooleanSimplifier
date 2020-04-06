package de.danielscholz.booleanSimplifier.expression;

import de.danielscholz.booleanSimplifier.Rule;

import java.util.Map;

/**
 * Created by Daniel on 22.04.2017.
 */
public class ParenthesisExpression extends Expression {

    static final int PRECEDENCE = 0;

    private final Expression expression;

    public ParenthesisExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    protected Expression applyToChildren(Rule rule) {
        Expression newExpression = expression.apply(rule);
        if (newExpression != expression) {
            return new ParenthesisExpression(newExpression);
        }
        return this;
    }

    @Override
    protected Map<String, Expression> matches(Expression rule, boolean invert, boolean fuzzyMatch) {
        Map<String, Expression> vars = matchAtomicVar(rule, invert);
        if (vars != null) return vars;

        if (rule.getClass() == getClass()) {
            ParenthesisExpression groupExprRule = (ParenthesisExpression) rule;
            return expression.matches(groupExprRule.expression, invert, fuzzyMatch);
        } else if (fuzzyMatch && rule.getClass() == NotExpression.class) {
            return matches(((NotExpression) rule).getChild(), true, fuzzyMatch);
        }
        return null;
    }

    @Override
    protected Expression applyMatch(Map<String, Expression> vars) {
        return new ParenthesisExpression(expression.applyMatch(vars));
    }

    @Override
    public int getPrecedence() {
        return PRECEDENCE;
    }

    @Override
    protected int getComplexityIntern() {
        return 2 + expression.getComplexityIntern();
    }

    public Expression getChild() {
        return expression;
    }

    public Expression removeUnnecessaryParentheses() {
        Expression expr = removeUnnecessaryParenthesesIntern(expression, 1);
        if (expr != expression) {
            return new ParenthesisExpression(expr);
        }
        return this;
    }

    @Override
    public String toString() {
        return "(" + expression + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParenthesisExpression groupExpr = (ParenthesisExpression) o;
        return expression.equals(groupExpr.expression);
    }

    @Override
    public int hashCode() {
        return expression.hashCode();
    }
}
