package com.brunooliveira.droidnate.select.enums;

public enum ClauseType {
	
	EQ("="), LIKE("LIKE"), GT(">"), GE(">="), LT("<"), LE("<="), NEQ("<>"), IN("IN");
	
	private String value;
	
	private ClauseType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

}