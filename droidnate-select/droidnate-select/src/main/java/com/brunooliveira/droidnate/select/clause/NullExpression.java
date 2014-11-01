package com.brunooliveira.droidnate.select.clause;

public class NullExpression implements Criteria {
	
	private String key;
	private boolean isNull;
	
	protected NullExpression(String key, boolean isNull) {
		this.key = key;
		this.isNull = isNull;
	}

	@Override
	public String toSql() {
		return key + " IS " + (isNull ? "NULL" : "NOT NULL");
	}

}