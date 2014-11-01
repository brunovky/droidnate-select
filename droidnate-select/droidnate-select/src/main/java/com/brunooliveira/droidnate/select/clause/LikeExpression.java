package com.brunooliveira.droidnate.select.clause;

import com.brunooliveira.droidnate.select.enums.LikeType;

public final class LikeExpression implements Criteria {
	
	private String key;
	private String value;
	private LikeType type;
	
	protected LikeExpression(String key, String value, LikeType type) {
		this.key = key;
		this.value = value;
		this.type = type;
	}

	@Override
	public String toSql() {
		if (type == null) return "";
		switch (type) {
		case LEFT_SIDE:
			return key + " LIKE '%" + value + "'";
		case RIGHT_SIDE:
			return key + " LIKE '" + value + "%'";
		default:
			return key + " LIKE '%" + value + "%'";
		}
	}

}