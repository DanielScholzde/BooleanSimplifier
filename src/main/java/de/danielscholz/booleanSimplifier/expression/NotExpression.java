package de.danielscholz.booleanSimplifier.expression;

import de.danielscholz.booleanSimplifier.Rule;

import java.util.Map;

/**
 * Created by Daniel on 22.04.2017.
 */
public class NotExpression extends Expression {

    static final int PRECENDENCE = 1;

    private final Expression expression;

    public NotExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    protected Expression applyToChilds(Rule rule) {
        Expression newExpression = expression.apply(rule);
        if (newExpression != expression) {
            return new NotExpression(addNecessaryParenthesisIntern(newExpression, 1));
        }
        return this;
    }

    @Override
    protected Map<String, Expression> matches(Expression rule, boolean invert, boolean matchUnsharp) {
        Map<String, Expression> vars = matchAtomicVar(rule, invert);
        if (vars != null) return vars;

        if (rule.getClass() == getClass()) {
            NotExpression notExprRule = (NotExpression) rule;
            return expression.matches(notExprRule.expression, invert, matchUnsharp);
        }
        return null;
    }

    @Override
    protected Expression applyMatch(Map<String, Expression> vars) {
        return new NotExpression(addNecessaryParenthesisIntern(expression.applyMatch(vars), 1));
    }

    @Override
    public int getPrecendence() {
        return PRECENDENCE;
    }

    @Override
    protected int getComplexityIntern() {
        return 1 + expression.getComplexityIntern();
    }

    public Expression removeUnnecessaryParenthesis() {
        Expression expr = removeUnnecessaryParenthesisIntern(expression, 1);
        if (expr != expression) {
            return new NotExpression(expr);
        }
        return this;
    }

    protected Expression getChild() {
        return expression;
    }

    @Override
    public String toString() {
        return "!" + expression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotExpression notExpr = (NotExpression) o;
        return expression.equals(notExpr.expression);
    }

    @Override
    public int hashCode() {
        return expression.hashCode();
    }
}
