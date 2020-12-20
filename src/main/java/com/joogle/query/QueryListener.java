// Generated from Query.g4 by ANTLR 4.5.3

package com.joogle.query;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QueryParser}.
 */
public interface QueryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QueryParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(QueryParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(QueryParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#q}.
	 * @param ctx the parse tree
	 */
	void enterQ(QueryParser.QContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#q}.
	 * @param ctx the parse tree
	 */
	void exitQ(QueryParser.QContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#classQuery}.
	 * @param ctx the parse tree
	 */
	void enterClassQuery(QueryParser.ClassQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#classQuery}.
	 * @param ctx the parse tree
	 */
	void exitClassQuery(QueryParser.ClassQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#methodQuery}.
	 * @param ctx the parse tree
	 */
	void enterMethodQuery(QueryParser.MethodQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#methodQuery}.
	 * @param ctx the parse tree
	 */
	void exitMethodQuery(QueryParser.MethodQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(QueryParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(QueryParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(QueryParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(QueryParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link QueryParser#wc}.
	 * @param ctx the parse tree
	 */
	void enterWc(QueryParser.WcContext ctx);
	/**
	 * Exit a parse tree produced by {@link QueryParser#wc}.
	 * @param ctx the parse tree
	 */
	void exitWc(QueryParser.WcContext ctx);
}