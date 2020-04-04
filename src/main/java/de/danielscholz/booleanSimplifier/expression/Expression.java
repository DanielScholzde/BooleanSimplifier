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
     * Wendet die Regel auf diesen Ausdruck an. Wenn die Regel passt, wird der geänderte Ausdruck zurück geliefert. Sonst
     * wird die Regel auf die Kinder angewendet.
     */
    public final Expression apply(Rule rule) {
        Expression newExpression = applyToThis(rule);
        if (newExpression != this) {
            return newExpression;
        }
        return applyToChilds(rule);
    }

    /**
     * Wendet die Regel nur auf genau diesen Ausdrucks und nicht auf die Kindelemente an.
     */
    public final Expression applyToThis(Rule rule) {
        Map<String, Expression> vars = matches(rule.getExpr(), false, rule.isMatchUnsharp());
        if (vars != null) {
            return rule.getResult().applyMatch(vars).removeUnnecessaryParenthesis();
        }
        return this;
    }

    /**
     * Wendet die Regel auf die Kindelemente an.
     */
    protected abstract Expression applyToChilds(Rule rule);

    /**
     * Testet, ob der Ausdruck rule zu diesem Ausdruck passt. Falls ja, wird eine Map mit den konkreten Werten für die
     * Variablen aus dem Ausdruck rule zurück gegeben. Ist in rule keine Variable enthalten, wird eine leere Map zurück
     * geliefert.
     */
    protected abstract Map<String, Expression> matches(Expression rule, boolean invert, boolean matchUnsharp);

    /**
     * Wendet auf diese Rule-Expression die Variablen-Ersetzung an, die in der Map hinterlegt ist.
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
     * Entfernt unnötige Klammerausdrücke von den einzelnen Kindausdrücken dieses Ausdrucks.
     */
    public abstract Expression removeUnnecessaryParenthesis();

    /**
     * Entfernt unnötige Klammern um den Kindausdruck.
     */
    protected final Expression removeUnnecessaryParenthesisIntern(Expression child, int pos) {
        Expression child_ = child.removeUnnecessaryParenthesis();
        if (child_ instanceof ParenthesisExpression) {
            Expression childChild = ((ParenthesisExpression) child_).getChild();
            if (!needParenthesis(childChild, pos)) {
                child_ = childChild;
            }
        }
        return child_;
    }

    /**
     * Fügt bei Bedarf eine Klammer um den Kindausdruck hinzu.
     */
    protected final Expression addNecessaryParenthesisIntern(Expression child, int pos) {
        if (needParenthesis(child, pos)) {
            return new ParenthesisExpression(child);
        }
        return child;
    }

    /**
     * Werden Klammern benötigt, wenn der Kind-Ausdruck child an pos in diesem Ausdruck gesetzt wird?
     */
    protected boolean needParenthesis(Expression child, int pos) {
        if (getPrecendence() > child.getPrecendence()) {
            return false;
        } else if (getPrecendence() == child.getPrecendence()) {
            if (getPrecendence() == Type.or.getPrecendence()
                    || getPrecendence() == Type.and.getPrecendence()
                    || getPrecendence() == NotExpression.PRECENDENCE
                    || getPrecendence() == ParenthesisExpression.PRECENDENCE) {
                return false;
            }
        }
        return true;
    }

    /**
     * Ist dieser rule-Ausdruck endweder 'a', 'b' oder 'c'?
     */
    protected boolean isAtomicVar() {
        return false;
    }

    /**
     * Ist dieser rule-Ausdruck ein 's'
     */
    protected boolean isAtomicVarS() {
        return false;
    }

    protected abstract int getPrecendence();

    public Expression flipSides() {
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
     * Vereinigt zwei Mengen von Variablen, die bei zwei matchings entstanden sind. Wenn eine Variable in beiden Maps
     * vorhanden ist, so muss der Wert dieser Variablen gleich sein. Ansonsten wird null zurück geliefert.
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
                // da der Wert dieser Variable unterschiedlich ist, wird null zurück geliefert.
                return null;
            }
            return result;
        }
        return null;
    }

}
