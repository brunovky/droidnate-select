package com.brunooliveira.droidnate.select.clause;

import java.util.ArrayList;
import java.util.List;

import com.brunooliveira.droidnate.select.enums.OrderType;

public class Order {
	
	private OrderType type;
	private List<String> keys;
	
	private Order(OrderType type, String... keys) {
		this.keys = new ArrayList<String>();
		this.type = type;
		for (String key : keys) {
			if (key != null) this.keys.add(key);
		}
	}
	
	public static Order asc(String... keys) {
		return new Order(OrderType.ASC, keys);
	}
	
	public static Order desc(String... keys) {
		return new Order(OrderType.DESC, keys);
	}
	
	public String toSql() {
		StringBuilder sqlBuilder = new StringBuilder();
		for (String key : keys) {
			sqlBuilder.append(key + (type.equals(OrderType.DESC) ? " DESC" : "") + ",");
		}
		if (sqlBuilder.toString().contains(",")) return sqlBuilder.toString().substring(0, sqlBuilder.toString().lastIndexOf(","));
		return "";
	}
	
	public String toSql(String className) {
		StringBuilder sqlBuilder = new StringBuilder();
		for (String key : keys) {
			sqlBuilder.append(className + "." + key + (type.equals(OrderType.DESC) ? " DESC" : "") + ",");
		}
		if (sqlBuilder.toString().contains(",")) return sqlBuilder.toString().substring(0, sqlBuilder.toString().lastIndexOf(","));
		return "";
	}

}