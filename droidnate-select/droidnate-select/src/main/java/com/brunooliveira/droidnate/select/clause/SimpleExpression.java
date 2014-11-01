package com.brunooliveira.droidnate.select.clause;

import com.brunooliveira.droidnate.select.enums.ClauseType;

public final class SimpleExpression implements Criteria {
	
	private String key;
	private Object value;
	private ClauseType type;
	
	protected SimpleExpression(String key, Object value, ClauseType type) {
		this.key = key;
		this.value = value;
		this.type = type;
	}

	@Override
	public String toSql() {
		if (type == null) return "";
		if (type.equals(ClauseType.IN)) {
			String sql = key + " " + type.getValue() + " (";
			for (Object v : (Object[]) value) {
				if (v != null) {					
					if (v.getClass().equals(String.class) || v.getClass().equals(char.class)) sql += "'" + v + "',";
					else sql += v + ",";
				}
			}
			return sql.toString().substring(0, sql.toString().lastIndexOf(",")) + ")";
		}
		if (value != null && value.getClass().equals(String.class) || value.getClass().equals(char.class)) return key + " " + type.getValue() + " '" + value + "'";
		return key + " " + type.getValue() + " " + value;
	}

}