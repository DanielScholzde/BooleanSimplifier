// Generated from C:/Users/Daniel/Projekte-git/BooleanSimplifier/src/main/resources\BooleanGrammar.g4 by ANTLR 4.8
package de.danielscholz.booleanSimplifier.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BooleanGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BooleanGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code Group}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroup(BooleanGrammarParser.GroupContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Not}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(BooleanGrammarParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BitXor}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitXor(BooleanGrammarParser.BitXorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BitOr}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitOr(BooleanGrammarParser.BitOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Or}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(BooleanGrammarParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Ternary}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTernary(BooleanGrammarParser.TernaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code And}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(BooleanGrammarParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BitAnd}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitAnd(BooleanGrammarParser.BitAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Atomic}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomic(BooleanGrammarParser.AtomicContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CompareEq}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareEq(BooleanGrammarParser.CompareEqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CompareRel}
	 * labeled alternative in {@link BooleanGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompareRel(BooleanGrammarParser.CompareRelContext ctx);
	/**
	 * Visit a parse tree produced by {@link BooleanGrammarParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(BooleanGrammarParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link BooleanGrammarParser#single_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle_expr(BooleanGrammarParser.Single_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link BooleanGrammarParser#method_params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod_params(BooleanGrammarParser.Method_paramsContext ctx);
	/**
	 * Visit a parse tree produced by {@link BooleanGrammarParser#mmm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMmm(BooleanGrammarParser.MmmContext ctx);
}