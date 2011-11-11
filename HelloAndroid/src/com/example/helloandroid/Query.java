package com.example.helloandroid;

public class Query {
	
	String column;
	String table;
	String where;
		
	public Query(String iColumn, String iTable) {
		// TODO Auto-generated constructor stub
		column = iColumn;
		table = iTable;
	}
	
	void addClause(String iWhere){
		where = " WHERE " + iWhere;
	}
}