package de.danielscholz.booleanSimplifier

import de.danielscholz.booleanSimplifier.expression.*
import de.danielscholz.booleanSimplifier.expression.TwoValExpression.Type
import de.danielscholz.booleanSimplifier.grammar.BooleanGrammarBaseListener
import de.danielscholz.booleanSimplifier.grammar.BooleanGrammarParser.*
import java.util.*

/**

 */
internal class BooleanGrammarListenerImpl : BooleanGrammarBaseListener() {

    private val stack = Stack<MutableList<Expression>>()

    init {
        enter()
    }

    override fun exitAnd(ctx: AndContext) = newTwoValExpression(Type.and)

    override fun exitOr(ctx: OrContext) = newTwoValExpression(Type.or)

    override fun exitBitAnd(ctx: BitAndContext) = newTwoValExpression(Type.bitAnd)

    override fun exitBitOr(ctx: BitOrContext) = newTwoValExpression(Type.bitOr)

    override fun exitBitXor(ctx: BitXorContext) = newTwoValExpression(Type.bitXor)

    override fun exitCompareEq(ctx: CompareEqContext) {
        val opText = ctx.getChild(1).text
        newTwoValExpression(if (opText == "==") Type.eq else Type.neq)
    }

    override fun exitCompareRel(ctx: CompareRelContext) {
        val type = when (ctx.getChild(1).text) {
            "<" -> Type.lt
            ">" -> Type.gt
            "<=" -> Type.lte
            ">=" -> Type.gte
            "instanceof" -> Type.instanceofType
            else -> throw IllegalStateException()
        }
        newTwoValExpression(type)
    }

    private fun newTwoValExpression(type: Type) {
        val exprList = stack.pop()
        stack.peek().add(TwoValExpression(type, exprList[0], exprList[1]))
    }

    override fun exitNot(ctx: NotContext) {
        val expressionList = stack.pop()
        stack.peek().add(NotExpression(expressionList[0]))
    }

    override fun exitGroup(ctx: GroupContext) {
        val expressionList = stack.pop()
        stack.peek().add(ParenthesisExpression(expressionList[0]))
    }

    override fun exitTernary(ctx: TernaryContext) {
        val exprList = stack.pop()
        stack.peek().add(TernaryExpression(exprList[0], exprList[1], exprList[2]))
    }

    override fun exitAtom(ctx: AtomContext) {
        stack.peek().add(AtomicExpression(ctx.text))
    }

    override fun enterGroup(ctx: GroupContext) = enter()

    override fun enterAnd(ctx: AndContext) = enter()

    override fun enterOr(ctx: OrContext) = enter()

    override fun enterBitAnd(ctx: BitAndContext) = enter()

    override fun enterBitOr(ctx: BitOrContext) = enter()

    override fun enterBitXor(ctx: BitXorContext) = enter()

    override fun enterCompareEq(ctx: CompareEqContext) = enter()

    override fun enterCompareRel(ctx: CompareRelContext) = enter()

    override fun enterNot(ctx: NotContext) = enter()

    override fun enterTernary(ctx: TernaryContext) = enter()

    private fun enter() {
        stack.push(ArrayList())
    }

    val expression: Expression
        get() = stack.peek()[0]

}
