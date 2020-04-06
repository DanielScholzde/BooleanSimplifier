package de.danielscholz.booleanSimplifier.expression;

import de.danielscholz.booleanSimplifier.Rule;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel on 22.04.2017.
 */
public class AtomicExpression extends Expression {

    private final String str;

    public AtomicExpression(String str) {
        this.str = str;
    }

    @Override
    protected Expression applyToChildren(Rule rule) {
        return this;
    }

    @Override
    protected Map<String, Expression> matches(Expression rule, boolean invert, boolean fuzzyMatch) {
        Map<String, Expression> vars = matchAtomicVar(rule, invert);
        if (vars != null) return vars;

        if (rule.getClass() == getClass()) {
            AtomicExpression atomicExprRule = (AtomicExpression) rule;
            if (atomicExprRule.str.equals(str)) {
                return new HashMap<>();
            }
        } else if (fuzzyMatch && rule.getClass() == NotExpression.class) {
            return matches(((NotExpression) rule).getChild(), true, fuzzyMatch);
        }
        return null;
    }

    @Override
    protected Expression applyMatch(Map<String, Expression> vars) {
        if (isAtomicVar() || isAtomicVarS()) {
            return vars.get(str);
        }
        return this;
    }

    @Override
    public Expression removeUnnecessaryParentheses() {
        return this;
    }

    @Override
    protected boolean isAtomicVar() {
        return "a".equals(str) || "b".equals(str) || "c".equals(str);
    }

    @Override
    protected boolean isAtomicVarS() {
        return "s".equals(str);
    }

    String getStr() {
        return str;
    }

    @Override
    public int getPrecedence() {
        return -1;
    }

    @Override
    protected int getComplexityIntern() {
        return 1;
    }

    @Override
    public String toString() {
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtomicExpression atomicExpression = (AtomicExpression) o;
        return str.equals(atomicExpression.str);
    }

    @Override
    public int hashCode() {
        return str.hashCode();
    }
}
