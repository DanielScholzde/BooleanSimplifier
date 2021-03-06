package de.danielscholz.booleanSimplifier.expression;

import de.danielscholz.booleanSimplifier.Rule;

import java.util.Map;

/**
 * Created by Daniel on 22.04.2017.
 */
public class TwoValExpression extends Expression {

    public enum Type {
        lt("<", 5),
        gt(">", 5),
        lte("<=", 5),
        gte(">=", 5),
        instanceofType("instanceof", 5),
        eq("==", 6),
        neq("!=", 6),
        bitAnd("&", 7),
        bitXor("^", 8),
        bitOr("|", 9),
        and("&&", 10),
        or("||", 11),
        ;

        private final String op;
        private final int precedence;

        Type(String str, int precedence) {
            op = str;
            this.precedence = precedence;
        }

        public int getPrecedence() {
            return precedence;
        }

        @Override
        public String toString() {
            return op;
        }
    }

    private final Type type;
    private final Expression leftExpr;
    private final Expression rightExpr;

    public TwoValExpression(Type type, Expression leftExpr, Expression rightExpr) {
        this.type = type;
        this.leftExpr = leftExpr;
        this.rightExpr = rightExpr;
    }

    @Override
    protected Expression applyToChildren(Rule rule) {
        Expression newLeftExpr = leftExpr.apply(rule);
        Expression newRightExpr = rightExpr.apply(rule);
        if (newLeftExpr != leftExpr || newRightExpr != rightExpr) {
            return new TwoValExpression(type, addNecessaryParenthesisIntern(newLeftExpr, 1), addNecessaryParenthesisIntern(newRightExpr, 2));
        }
        return this;
    }

    @Override
    protected Map<String, Expression> matches(Expression rule, boolean invert, boolean fuzzyMatch) {
        Map<String, Expression> vars = matchAtomicVar(rule, invert);
        if (vars != null) return vars;

        if (rule.getClass() == getClass()) {
            TwoValExpression twoValExprRule = (TwoValExpression) rule;
            if (twoValExprRule.type == type) {
                Map<String, Expression> varsLeft = leftExpr.matches(twoValExprRule.leftExpr, invert, fuzzyMatch);
                if (varsLeft == null) return null;
                Map<String, Expression> varsRight = rightExpr.matches(twoValExprRule.rightExpr, invert, fuzzyMatch);
                return joinVars(varsLeft, varsRight);
            }
        } else if (fuzzyMatch && rule.getClass() == NotExpression.class) {
            return matches(((NotExpression) rule).getChild(), true, fuzzyMatch);
        }
        return null;
    }

    @Override
    protected Expression applyMatch(Map<String, Expression> vars) {
        return new TwoValExpression(type, addNecessaryParenthesisIntern(leftExpr.applyMatch(vars), 1),
                addNecessaryParenthesisIntern(rightExpr.applyMatch(vars), 2));
    }

    public Expression removeUnnecessaryParentheses() {
        Expression left = removeUnnecessaryParenthesesIntern(leftExpr, 1);
        Expression right = removeUnnecessaryParenthesesIntern(rightExpr, 2);
        if (left != leftExpr || right != rightExpr) {
            return new TwoValExpression(type, left, right);
        }
        return this;
    }

    @Override
    public int getPrecedence() {
        return type.getPrecedence();
    }

    @Override
    protected int getComplexityIntern() {
        // chained characters (&& or ||) should not increase complexity excessively, for example a || b || c || d
        if ((type == Type.or || type == Type.and)) {
            if (leftExpr.getClass() == getClass()) {
                TwoValExpression childExpr = (TwoValExpression) leftExpr;
                if (childExpr.type == type) {
                    return leftExpr.getComplexityIntern() + rightExpr.getComplexityIntern();
                }
            }
            if (rightExpr.getClass() == getClass()) {
                TwoValExpression childExpr = (TwoValExpression) rightExpr;
                if (childExpr.type == type) {
                    return leftExpr.getComplexityIntern() + rightExpr.getComplexityIntern();
                }
            }
        }
        return 1 + leftExpr.getComplexityIntern() + rightExpr.getComplexityIntern();
    }

    @Override
    public Expression invertSides() {
        if (type == Type.and || type == Type.or || type == Type.neq || type == Type.eq) {
            return new TwoValExpression(type, rightExpr, leftExpr);
        }
        throw new IllegalStateException();
    }

    @Override
    public String toString() {
        return "" + leftExpr + " " + type + " " + rightExpr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwoValExpression twoValExpression = (TwoValExpression) o;
        return type == twoValExpression.type
                && leftExpr.equals(twoValExpression.leftExpr)
                && rightExpr.equals(twoValExpression.rightExpr);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + leftExpr.hashCode();
        result = 31 * result + rightExpr.hashCode();
        return result;
    }
}
