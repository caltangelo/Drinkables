package com.calebsantangelo.drink;

public class Query {
	
	String column;
	String table;
	String where;
		
	public Query(String qColumn, String qTable) {
		// TODO Auto-generated constructor stub
		column = qColumn;
		table = qTable;
	}
	
	void addClause(String qWhere){
		where = " WHERE " + qWhere;
	}
}