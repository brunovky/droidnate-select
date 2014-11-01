package com.brunooliveira.droidnate.select.clause;

import java.util.ArrayList;
import java.util.List;

public final class BetweenExpression implements Criteria {

	private String key;
	private List<Object> values;

	protected BetweenExpression(String key, Object... values) {
		this.key = key;
		this.values = new ArrayList<Object>();
		for (Object value : values) {
			if (value != null) this.values.add(value);
		}
	}

	private boolean isVarcharOrChar(Object value) {
		return value.getClass().equals(String.class) || value.getClass().equals(char.class);
	}

	@Override
	public String toSql() {
		StringBuilder sqlBuilder = new StringBuilder(key + " BETWEEN ");
		for (Object value : values) {
			sqlBuilder.append((isVarcharOrChar(value) ? "'" + value + "'" : value) + " AND ");
		}
		return sqlBuilder.toString().substring(0, sqlBuilder.toString().lastIndexOf(" AND "));
	}
	
}