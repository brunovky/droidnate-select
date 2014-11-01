package com.brunooliveira.droidnate.select.clause;

import com.brunooliveira.droidnate.select.enums.ClauseType;
import com.brunooliveira.droidnate.select.enums.LikeType;

public class Clause {
	
	public static Criteria eq(String key, Object value) {
		return new SimpleExpression(key, value, ClauseType.EQ);
	}
	
	public static Criteria like(String key, String value, LikeType likeType) {
		return new LikeExpression(key, value, likeType);
	}
	
	public static Criteria gt(String key, Object value) {
		return new SimpleExpression(key, value, ClauseType.GT);
	}
	
	public static Criteria ge(String key, Object value) {
		return new SimpleExpression(key, value, ClauseType.GE);
	}
	
	public static Criteria lt(String key, Object value) {
		return new SimpleExpression(key, value, ClauseType.LT);
	}
	
	public static Criteria le(String key, Object value) {
		return new SimpleExpression(key, value, ClauseType.LE);
	}
	
	public static Criteria in(String key, Object... values) {
		return new SimpleExpression(key, values, ClauseType.IN);
	}
	
	public static Criteria between(String key, Object... values) {
		return new BetweenExpression(key, values);
	}
	
	public static Criteria isNull(String key) {
		return new NullExpression(key, true);
	}
	
	public static Criteria isNotNull(String key) {
		return new NullExpression(key, false);
	}

}