package com.joogle.query;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import com.google.common.io.CharStreams;
import com.joogle.datamodel.Query;
import com.joogle.query.QueryParser.IdContext;
import com.joogle.query.QueryParser.WcContext;

public class QueryHandler extends QueryBaseListener {

	Query query;

	@Override
	public void exitQuery(QueryParser.QueryContext ctx) {
	}

	@Override
	public void exitId(IdContext ctx) {
		System.out.println("ID: " + ctx.getText());
		
	}
	
	@Override
	public void exitWc(WcContext ctx) {
		System.out.println("Wildcard!");
	}

	public QueryHandler(String inp) {
		QueryLexer lexer = new QueryLexer(new ANTLRInputStream(inp));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		QueryParser qp = new QueryParser(tokens);
		qp.addParseListener(this);
		
		qp.q();
	}
}
