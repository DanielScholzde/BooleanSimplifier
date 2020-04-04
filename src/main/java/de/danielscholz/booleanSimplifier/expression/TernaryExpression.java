package de.danielscholz.booleanSimplifier.expression;

import de.danielscholz.booleanSimplifier.Rule;

import java.util.Map;

/**
 * Created by Daniel on 22.04.2017.
 */
public class TernaryExpression extends Expression {

    private final Expression cond;
    private final Expression condTrue;
    private final Expression condFalse;

    public TernaryExpression(Expression cond, Expression condTrue, Expression condFalse) {
        this.cond = cond;
        this.condTrue = condTrue;
        this.condFalse = condFalse;
    }

    @Override
    protected Expression applyToChilds(Rule rule) {
        Expression newCond = cond.apply(rule);
        Expression newCondTrue = condTrue.apply(rule);
        Expression newCondFalse = condFalse.apply(rule);
        if (newCond != cond || newCondTrue != condTrue || newCondFalse != condFalse) {
            return new TernaryExpression(addNecessaryParenthesisIntern(newCond, 1),
                    addNecessaryParenthesisIntern(newCondTrue, 2),
                    addNecessaryParenthesisIntern(newCondFalse, 3));
        }
        return this;
    }

    @Override
    protected Map<String, Expression> matches(Expression rule, boolean invert, boolean matchUnsharp) {
        Map<String, Expression> vars = matchAtomicVar(rule, invert);
        if (vars != null) return vars;

        if (rule.getClass() == getClass()) {
            TernaryExpression ternaryExprRule = (TernaryExpression) rule;
            Map<String, Expression> varsCond = cond.matches(ternaryExprRule.cond, invert, matchUnsharp);
            if (varsCond == null) return null;
            Map<String, Expression> varsCondTrue = condTrue.matches(ternaryExprRule.condTrue, invert, matchUnsharp);
            if (varsCondTrue == null) return null;
            Map<String, Expression> varsCondFalse = condFalse.matches(ternaryExprRule.condFalse, invert, matchUnsharp);
            return joinVars(varsCond, joinVars(varsCondTrue, varsCondFalse));
        } else if (matchUnsharp && rule.getClass() == NotExpression.class) {
            return matches(((NotExpression) rule).getChild(), true, matchUnsharp);
        }
        return null;
    }

    @Override
    protected Expression applyMatch(Map<String, Expression> vars) {
        return new TernaryExpression(addNecessaryParenthesisIntern(cond.applyMatch(vars), 1),
                addNecessaryParenthesisIntern(condTrue.applyMatch(vars), 2),
                addNecessaryParenthesisIntern(condFalse.applyMatch(vars), 3));
    }

    @Override
    public int getPrecendence() {
        return 12;
    }

    @Override
    protected int getComplexityIntern() {
        int add = 0;
        if (cond.getClass() == TernaryExpression.class) add += 2;
        if (condTrue.getClass() == TernaryExpression.class) add += 2;
        if (condFalse.getClass() == TernaryExpression.class) add += 2;
        return 1 + add + cond.getComplexityIntern() + condTrue.getComplexityIntern() + condFalse.getComplexityIntern();
    }

    public Expression removeUnnecessaryParenthesis() {
        Expression cond1 = removeUnnecessaryParenthesisIntern(cond, 1);
        Expression condTrue1 = removeUnnecessaryParenthesisIntern(condTrue, 2);
        Expression condFalse1 = removeUnnecessaryParenthesisIntern(condFalse, 3);
        if (cond1 != cond || condTrue1 != condTrue || condFalse1 != condFalse) {
            return new TernaryExpression(cond1, condTrue1, condFalse1);
        }
        return this;
    }

    @Override
    protected boolean needParenthesis(Expression child, int pos) {
        if (child instanceof TernaryExpression && pos == 3) {
            return true;
        }
        return super.needParenthesis(child, pos);
    }

    @Override
    public String toString() {
        return "" + cond + " ? " + condTrue + " : " + condFalse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TernaryExpression that = (TernaryExpression) o;
        return cond.equals(that.cond) && condTrue.equals(that.condTrue) && condFalse.equals(that.condFalse);
    }

    @Override
    public int hashCode() {
        int result = cond.hashCode();
        result = 31 * result + condTrue.hashCode();
        result = 31 * result + condFalse.hashCode();
        return result;
    }
}
