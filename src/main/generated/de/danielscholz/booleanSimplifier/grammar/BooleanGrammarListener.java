// Generated from C:/Users/Daniel/Projekte-git/BooleanSimplifier/src/main/resources\BooleanGrammar.g4 by ANTLR 4.8
package de.danielscholz.booleanSimplifier.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BooleanGrammarParser}.
 */
public interface BooleanGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code Group}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterGroup(BooleanGrammarParser.GroupContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Group}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitGroup(BooleanGrammarParser.GroupContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Not}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNot(BooleanGrammarParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Not}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNot(BooleanGrammarParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BitXor}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitXor(BooleanGrammarParser.BitXorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BitXor}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitXor(BooleanGrammarParser.BitXorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BitOr}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitOr(BooleanGrammarParser.BitOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BitOr}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitOr(BooleanGrammarParser.BitOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Or}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOr(BooleanGrammarParser.OrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Or}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOr(BooleanGrammarParser.OrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Ternary}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterTernary(BooleanGrammarParser.TernaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Ternary}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitTernary(BooleanGrammarParser.TernaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code And}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAnd(BooleanGrammarParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code And}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAnd(BooleanGrammarParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BitAnd}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitAnd(BooleanGrammarParser.BitAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BitAnd}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitAnd(BooleanGrammarParser.BitAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Atomic}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAtomic(BooleanGrammarParser.AtomicContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Atomic}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAtomic(BooleanGrammarParser.AtomicContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CompareEq}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCompareEq(BooleanGrammarParser.CompareEqContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CompareEq}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCompareEq(BooleanGrammarParser.CompareEqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CompareRel}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCompareRel(BooleanGrammarParser.CompareRelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CompareRel}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCompareRel(BooleanGrammarParser.CompareRelContext ctx);
	/**
	 * Enter a parse tree produced by {@link BooleanGrammarParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(BooleanGrammarParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link BooleanGrammarParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(BooleanGrammarParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link BooleanGrammarParser#single_expr}.
	 * @param ctx the parse tree
	 */
	void enterSingle_expr(BooleanGrammarParser.Single_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link BooleanGrammarParser#single_expr}.
	 * @param ctx the parse tree
	 */
	void exitSingle_expr(BooleanGrammarParser.Single_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link BooleanGrammarParser#method_params}.
	 * @param ctx the parse tree
	 */
	void enterMethod_params(BooleanGrammarParser.Method_paramsContext ctx);
	/**
	 * Exit a parse tree produced by {@link BooleanGrammarParser#method_params}.
	 * @param ctx the parse tree
	 */
	void exitMethod_params(BooleanGrammarParser.Method_paramsContext ctx);
	/**
	 * Enter a parse tree produced by {@link BooleanGrammarParser#mmm}.
	 * @param ctx the parse tree
	 */
	void enterMmm(BooleanGrammarParser.MmmContext ctx);
	/**
	 * Exit a parse tree produced by {@link BooleanGrammarParser#mmm}.
	 * @param ctx the parse tree
	 */
	void exitMmm(BooleanGrammarParser.MmmContext ctx);
}