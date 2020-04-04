package de.danielscholz.booleanSimplifier;

import de.danielscholz.booleanSimplifier.expression.*;
import de.danielscholz.booleanSimplifier.expression.TwoValExpression.Type;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class BooleanExpressionSimplifierTest {

    private final List<String> vars = Arrays.asList("x", "y", "z", "w", "v");

    @Test
    @SuppressWarnings({"PointlessBooleanExpression", "ConstantConditions", "SimplifiableBooleanExpression", "SimplifiableConditionalExpression", "ConstantConditionalExpression"})
    public void testSimplifier() {
        test("true && false", true && false);
        test("true || false", true || false);
        test("true || false && true", true || false && true);
        test("true || false && !(true)", true || false && !(true));
        test("((true || false))", ((true || false)));
        test("!(true && false || true == false)", !(true && false || true == false));
        test("true == false != true", true == false != true);
        test("true != false == true", true != false == true);
        test("true != false == true", true != false == true);
        test("true != false == true == false", true != false == true == false);
        test("true && false == false || true", true && false == false || true);
        test("true ? true : true ? !true : !false", true ? true : true ? !true : !false);

        test("true && a", "a");
        test("(true)", "true");
        test("(a)", "a");
        test("(a && b)", "a && b");
        test("a || (b && c) && d", "a || b && c && d");
        test("a || !!(b || c) && d", "a || (b || c) && d");
        test("(a && !(b || c)) || d", "a && !b && !c || d");
        test("(a || !(b || c)) || d", "a || !b && !c || d");
        test("(a || !(b || c)) && d", "(a || !b && !c) && d");
        test("(a && b) || (c)", "a && b || c");
        test("(a && b && d) || c", "a && b && d || c");
        test("(a && false) || c", "c");
        test("(a && false) || c || d", "c || d");
        test("(a && true) || c", "a || c");
        test("(a) || ((b) && (c)) && d", "a || b && c && d");
        test("(a) || ((b) && (c && d))", "a || b && c && d");
        test("!(a && b) || c", "!a || !b || c");
        test("!(a && b) && c", "!(a && b) && c");
        test("!(a) || a", "true");
        test("!(a) && a", "false");
        test("!x && x", "false");
        test("!(a) && !a", "!a");
        test("a ? true : false", "a");
        test("(a) ? (true) : (false)", "a");
        test("a ? !a : false", "false");
        test("a ? !a : true", "!a"); // a && !a || !a && true  -->  false || !a  --> !a
        test("a ? b ? true : false : false", "a && b");
        test("a && b || !a && c", "a ? b : c");
        test("!a && b || a && c", "a ? c : b");
        test("a ? b : c", "a ? b : c");
        test("!(3 < 4)", "3 >= 4");
        test("!(3 < 4) && true", "3 >= 4");
        test("!(3 < 4) && (3 == 3)", "3 >= 4");
        test("!(a != null ? a.id <= 0 : true)", "a != null && a.id > 0");
        test("!(a != null ? a.getId() <= 0 : true)", "a != null && a.getId() > 0");

        test("x ? !true : true ? x : z ", "false");
        //test("!(a() && b.c(s||g()))","!a() || !b.c(s||g())");
        //test("2-2 == a(3-r)","2-2 == a(3-r)");
        test("(a || k) ? (b) : (c)", "a || k ? b : c");
        //test("b && a || c && !a", "a ? b : c");

        //test("!(a != null ? a.getId() <= 0 : true)", "!(a == null || a.getId() <= 0)");
        //test("a ? !_b : _b", "a != _b");
        //test("(!a || b) && a", "a && b");
    }

    //@Test
    private void testRandomGeneratedExpressions() {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("nashorn");

        try {
            long start = System.currentTimeMillis();
            int count = 0;
            do {
                String s = createExpr(0).toString();
                Expression expr = BooleanExpressionSimplifier.parse(s);
                if (!s.equals(expr.toString())) {
                    throw new IllegalStateException();
                }
                Expression simplify = BooleanExpressionSimplifier.simplify(expr);

                String exprStr = expr.toString();
                String simplifyStr = simplify.toString();

                for (String var : vars) {
                    if (exprStr.contains(var)) {
                        String value = Math.random() > 0.5 ? "true" : "false";
                        exprStr = exprStr.replace(var, value);
                        simplifyStr = simplifyStr.replace(var, value);
                    }
                }

                String result1 = engine.eval(exprStr) + "";
                String result2 = engine.eval(simplifyStr) + "";
                if (!result1.equals(result2)) {
                    System.out.println(expr + "   !=   " + simplify + "   for: " + exprStr + "   !=   " + simplifyStr);
                }
                count++;

            } while ((System.currentTimeMillis() - start) / 1000 <= 30);
            System.out.println(count + " Ausdrücke getestet");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    private static Expression createExpr(int depth) {
        int random = (int) (Math.random() * 5);
        if (depth > 2) {
            random = 0;
        }
        switch (random) {
            case 0:
                return createVar();
            case 1:
                return new NotExpression(createExpr(depth + 1));
            case 2:
                return new ParenthesisExpression(createExpr(depth + 1));
            case 3:
                List<Type> values = Arrays.asList(Type.and, Type.or, Type.eq, Type.neq);
                Type type = values.get((int) (Math.random() * values.size()));
                return new TwoValExpression(type, createExpr(depth + 1), createExpr(depth + 1));
            case 4:
                return new TernaryExpression(createExpr(depth + 1), createExpr(depth + 1), createExpr(depth + 1));
        }
        throw new IllegalStateException();
    }

    private static AtomicExpression createVar() {
        int random = (int) (Math.random() * 7);
        if (random == 0) {
            return new AtomicExpression("true");
        }
        if (random == 1) {
            return new AtomicExpression("false");
        }
        if (random == 2) {
            return new AtomicExpression("x");
        }
        if (random == 3) {
            return new AtomicExpression("y");
        }
        if (random == 4) {
            return new AtomicExpression("z");
        }
        if (random == 5) {
            return new AtomicExpression("w");
        }
        if (random == 6) {
            return new AtomicExpression("v");
        }
        throw new IllegalStateException();
    }

    private static void test(String expr, boolean result) {
        String s = BooleanExpressionSimplifier.simplify(BooleanExpressionSimplifier.parse(expr)).toString();
        if (!s.equals("" + result)) {
            System.err.println(expr + " -> " + s + " != " + result);
        }
    }

    private static void test(String expr, String result) {
        Expression s = BooleanExpressionSimplifier.simplify(BooleanExpressionSimplifier.parse(expr));
        Expression exprResult = BooleanExpressionSimplifier.parse(result);
        if (!s.toString().equals(exprResult.toString())) {
            System.err.println(expr + "  -->  " + s + "  !=  " + result);
        }
    }
}
