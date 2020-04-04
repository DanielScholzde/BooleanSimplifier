// Generated from C:/Users/Daniel/Projekte-git/BooleanSimplifier/src/main/resources\BooleanGrammar.g4 by ANTLR 4.8
package de.danielscholz.booleanSimplifier.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BooleanGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, OTHER_TOKEN=19, WS=20;
	public static final int
		RULE_expression = 0, RULE_atom = 1, RULE_single_expr = 2, RULE_method_params = 3, 
		RULE_mmm = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"expression", "atom", "single_expr", "method_params", "mmm"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'!'", "'('", "')'", "'<'", "'>'", "'<='", "'>='", "'instanceof'", 
			"'=='", "'!='", "'&'", "'^'", "'|'", "'&&'", "'||'", "'?'", "':'", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "OTHER_TOKEN", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "BooleanGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public BooleanGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class GroupContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public GroupContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).enterGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).exitGroup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanGrammarVisitor ) return ((BooleanGrammarVisitor<? extends T>)visitor).visitGroup(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NotContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).enterNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).exitNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanGrammarVisitor ) return ((BooleanGrammarVisitor<? extends T>)visitor).visitNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitXorContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BitXorContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).enterBitXor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).exitBitXor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanGrammarVisitor ) return ((BooleanGrammarVisitor<? extends T>)visitor).visitBitXor(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitOrContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BitOrContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).enterBitOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).exitBitOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanGrammarVisitor ) return ((BooleanGrammarVisitor<? extends T>)visitor).visitBitOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public OrContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).enterOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).exitOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanGrammarVisitor ) return ((BooleanGrammarVisitor<? extends T>)visitor).visitOr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TernaryContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TernaryContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).enterTernary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).exitTernary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanGrammarVisitor ) return ((BooleanGrammarVisitor<? extends T>)visitor).visitTernary(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AndContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).enterAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).exitAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanGrammarVisitor ) return ((BooleanGrammarVisitor<? extends T>)visitor).visitAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BitAndContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BitAndContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).enterBitAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).exitBitAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanGrammarVisitor ) return ((BooleanGrammarVisitor<? extends T>)visitor).visitBitAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AtomicContext extends ExpressionContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public AtomicContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).enterAtomic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).exitAtomic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanGrammarVisitor ) return ((BooleanGrammarVisitor<? extends T>)visitor).visitAtomic(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CompareEqContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public CompareEqContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).enterCompareEq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).exitCompareEq(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanGrammarVisitor ) return ((BooleanGrammarVisitor<? extends T>)visitor).visitCompareEq(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CompareRelContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public CompareRelContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).enterCompareRel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).exitCompareRel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanGrammarVisitor ) return ((BooleanGrammarVisitor<? extends T>)visitor).visitCompareRel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OTHER_TOKEN:
				{
				_localctx = new AtomicContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(11);
				atom();
				}
				break;
			case T__0:
				{
				_localctx = new NotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(12);
				match(T__0);
				setState(13);
				expression(10);
				}
				break;
			case T__1:
				{
				_localctx = new GroupContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(14);
				match(T__1);
				setState(15);
				expression(0);
				setState(16);
				match(T__2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(49);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(47);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
					case 1:
						{
						_localctx = new CompareRelContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(20);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(21);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(22);
						expression(9);
						}
						break;
					case 2:
						{
						_localctx = new CompareEqContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(23);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(24);
						_la = _input.LA(1);
						if ( !(_la==T__8 || _la==T__9) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(25);
						expression(8);
						}
						break;
					case 3:
						{
						_localctx = new BitAndContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(26);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(27);
						match(T__10);
						setState(28);
						expression(7);
						}
						break;
					case 4:
						{
						_localctx = new BitXorContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(29);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(30);
						match(T__11);
						setState(31);
						expression(6);
						}
						break;
					case 5:
						{
						_localctx = new BitOrContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(32);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(33);
						match(T__12);
						setState(34);
						expression(5);
						}
						break;
					case 6:
						{
						_localctx = new AndContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(35);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(36);
						match(T__13);
						setState(37);
						expression(4);
						}
						break;
					case 7:
						{
						_localctx = new OrContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(38);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(39);
						match(T__14);
						setState(40);
						expression(3);
						}
						break;
					case 8:
						{
						_localctx = new TernaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(41);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(42);
						match(T__15);
						setState(43);
						expression(0);
						setState(44);
						match(T__16);
						setState(45);
						expression(1);
						}
						break;
					}
					} 
				}
				setState(51);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public Single_exprContext single_expr() {
			return getRuleContext(Single_exprContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanGrammarVisitor ) return ((BooleanGrammarVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_atom);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			single_expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Single_exprContext extends ParserRuleContext {
		public TerminalNode OTHER_TOKEN() { return getToken(BooleanGrammarParser.OTHER_TOKEN, 0); }
		public List<Method_paramsContext> method_params() {
			return getRuleContexts(Method_paramsContext.class);
		}
		public Method_paramsContext method_params(int i) {
			return getRuleContext(Method_paramsContext.class,i);
		}
		public Single_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_single_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).enterSingle_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).exitSingle_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanGrammarVisitor ) return ((BooleanGrammarVisitor<? extends T>)visitor).visitSingle_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Single_exprContext single_expr() throws RecognitionException {
		Single_exprContext _localctx = new Single_exprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_single_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(OTHER_TOKEN);
			setState(63);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(55);
				match(T__1);
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__10) | (1L << T__12) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << OTHER_TOKEN))) != 0)) {
					{
					{
					setState(56);
					method_params();
					}
					}
					setState(61);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(62);
				match(T__2);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Method_paramsContext extends ParserRuleContext {
		public List<MmmContext> mmm() {
			return getRuleContexts(MmmContext.class);
		}
		public MmmContext mmm(int i) {
			return getRuleContext(MmmContext.class,i);
		}
		public Method_paramsContext method_params() {
			return getRuleContext(Method_paramsContext.class,0);
		}
		public Method_paramsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).enterMethod_params(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).exitMethod_params(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanGrammarVisitor ) return ((BooleanGrammarVisitor<? extends T>)visitor).visitMethod_params(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Method_paramsContext method_params() throws RecognitionException {
		Method_paramsContext _localctx = new Method_paramsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_method_params);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(66); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(65);
					mmm();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(68); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(73);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(70);
					mmm();
					}
					} 
				}
				setState(75);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(76);
				match(T__1);
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__10) | (1L << T__12) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << OTHER_TOKEN))) != 0)) {
					{
					setState(77);
					method_params();
					}
				}

				setState(80);
				match(T__2);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MmmContext extends ParserRuleContext {
		public TerminalNode OTHER_TOKEN() { return getToken(BooleanGrammarParser.OTHER_TOKEN, 0); }
		public MmmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mmm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).enterMmm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BooleanGrammarListener ) ((BooleanGrammarListener)listener).exitMmm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BooleanGrammarVisitor ) return ((BooleanGrammarVisitor<? extends T>)visitor).visitMmm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MmmContext mmm() throws RecognitionException {
		MmmContext _localctx = new MmmContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_mmm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__10) | (1L << T__12) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << OTHER_TOKEN))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 8);
		case 1:
			return precpred(_ctx, 7);
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		case 4:
			return precpred(_ctx, 4);
		case 5:
			return precpred(_ctx, 3);
		case 6:
			return precpred(_ctx, 2);
		case 7:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\26X\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\25\n\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2\62\n\2\f\2\16\2\65\13\2\3\3"+
		"\3\3\3\4\3\4\3\4\7\4<\n\4\f\4\16\4?\13\4\3\4\5\4B\n\4\3\5\6\5E\n\5\r\5"+
		"\16\5F\3\5\7\5J\n\5\f\5\16\5M\13\5\3\5\3\5\5\5Q\n\5\3\5\5\5T\n\5\3\6\3"+
		"\6\3\6\2\3\2\7\2\4\6\b\n\2\5\3\2\6\n\3\2\13\f\6\2\3\3\r\r\17\17\22\25"+
		"\2b\2\24\3\2\2\2\4\66\3\2\2\2\68\3\2\2\2\bD\3\2\2\2\nU\3\2\2\2\f\r\b\2"+
		"\1\2\r\25\5\4\3\2\16\17\7\3\2\2\17\25\5\2\2\f\20\21\7\4\2\2\21\22\5\2"+
		"\2\2\22\23\7\5\2\2\23\25\3\2\2\2\24\f\3\2\2\2\24\16\3\2\2\2\24\20\3\2"+
		"\2\2\25\63\3\2\2\2\26\27\f\n\2\2\27\30\t\2\2\2\30\62\5\2\2\13\31\32\f"+
		"\t\2\2\32\33\t\3\2\2\33\62\5\2\2\n\34\35\f\b\2\2\35\36\7\r\2\2\36\62\5"+
		"\2\2\t\37 \f\7\2\2 !\7\16\2\2!\62\5\2\2\b\"#\f\6\2\2#$\7\17\2\2$\62\5"+
		"\2\2\7%&\f\5\2\2&\'\7\20\2\2\'\62\5\2\2\6()\f\4\2\2)*\7\21\2\2*\62\5\2"+
		"\2\5+,\f\3\2\2,-\7\22\2\2-.\5\2\2\2./\7\23\2\2/\60\5\2\2\3\60\62\3\2\2"+
		"\2\61\26\3\2\2\2\61\31\3\2\2\2\61\34\3\2\2\2\61\37\3\2\2\2\61\"\3\2\2"+
		"\2\61%\3\2\2\2\61(\3\2\2\2\61+\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63"+
		"\64\3\2\2\2\64\3\3\2\2\2\65\63\3\2\2\2\66\67\5\6\4\2\67\5\3\2\2\28A\7"+
		"\25\2\29=\7\4\2\2:<\5\b\5\2;:\3\2\2\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2\2>@"+
		"\3\2\2\2?=\3\2\2\2@B\7\5\2\2A9\3\2\2\2AB\3\2\2\2B\7\3\2\2\2CE\5\n\6\2"+
		"DC\3\2\2\2EF\3\2\2\2FD\3\2\2\2FG\3\2\2\2GK\3\2\2\2HJ\5\n\6\2IH\3\2\2\2"+
		"JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2LS\3\2\2\2MK\3\2\2\2NP\7\4\2\2OQ\5\b\5\2"+
		"PO\3\2\2\2PQ\3\2\2\2QR\3\2\2\2RT\7\5\2\2SN\3\2\2\2ST\3\2\2\2T\t\3\2\2"+
		"\2UV\t\4\2\2V\13\3\2\2\2\13\24\61\63=AFKPS";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}