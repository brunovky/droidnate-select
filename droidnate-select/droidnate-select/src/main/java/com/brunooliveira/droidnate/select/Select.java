package com.brunooliveira.droidnate.select;

import java.util.ArrayList;
import java.util.List;

import com.brunooliveira.droidnate.select.clause.Criteria;
import com.brunooliveira.droidnate.select.clause.Or;
import com.brunooliveira.droidnate.select.clause.Order;
import com.brunooliveira.droidnate.select.model.JoinClass;

public final class Select {

	private List<Criteria> criterias;
	private List<Order> orders;
	private List<JoinClass> joinClasses;
	private List<String> columns;
	private boolean distinct;
	private int limit;
	private String className;
	private String alias;
	private String sql;
	
	private static final String ALL = "*";

	private Select(String... columns) {
		this.columns = new ArrayList<String>();
		this.criterias = new ArrayList<Criteria>();
		this.orders = new ArrayList<Order>();
		this.joinClasses = new ArrayList<JoinClass>();
		for(String column : columns) {
			if (column != null) this.columns.add(column);
		}
	}
	
	public static Select all() {
		return new Select(ALL);
	}
	
	public static Select columns(String... columns) {
		return new Select(columns);
	}

	public Select from(String className) {
		this.className = className;
		this.alias = className;
		return this;
	}

	public Select from(String className, String alias) {
		this.className = className;
		this.alias = alias;
		return this;
	}

	public Select join(String joinClassName, String pk, String fk) {
		this.joinClasses.add(new JoinClass(joinClassName, pk, fk));
		return this;
	}

	public Select join(String joinClassName, String pk, String fk, String alias) {
		this.joinClasses.add(new JoinClass(joinClassName, pk, fk, alias));
		return this;
	}

	public Select where(Criteria criteria) {
		if (criteria != null) this.criterias.add(criteria);
		return this;
	}

	public Select distinct() {
		this.distinct = true;
		return this;
	}

	public Select orderBy(Order order) {
		if (order != null) this.orders.add(order);
		return this;
	}

	public Select limit(int limit) {
		this.limit = limit;
		return this;
	}

	public Select unique() {
		this.sql = generateSQL(true);
		return this;
	}

	public Select list() {
		this.sql = generateSQL(false);
		return this;
	}

	public String getSql() {
		return sql;
	}
	
	public static Or or(Criteria... criterias) {
		return new Or(criterias);
	}

	private boolean hasJoin() {
		return joinClasses.size() > 0;
	}

	private String generateSQL(boolean unique) {
		StringBuilder sqlBuilder = new StringBuilder("SELECT " + (distinct ? "DISTINCT " : "") + getColumns() + " FROM " + className + (hasJoin() ? " " + alias : ""));
		if (hasJoin()) {
			for (JoinClass join : joinClasses) {
				sqlBuilder.append(" INNER JOIN " + join.getClassName() + " " + join.getAlias() + " ON " + alias + "." + join.getPk() + " = " + join.getAlias()
						+ "." + join.getFk());
			}
		}
		if (criterias.size() > 0) {
			sqlBuilder.append(" WHERE ");
			for (Criteria criteria : criterias) {
				sqlBuilder.append((hasJoin() ? criteria.toSql() : criteria.toSql()) + " AND ");
			}
			sqlBuilder = new StringBuilder(sqlBuilder.toString().substring(0, sqlBuilder.toString().lastIndexOf(" AND ")));
		}
		if (orders.size() > 0) {
			sqlBuilder.append(" ORDER BY ");
			for (Order order : orders) {
				sqlBuilder.append((hasJoin() ? order.toSql() : order.toSql()) + ", ");
			}
			sqlBuilder = new StringBuilder(sqlBuilder.toString().substring(0, sqlBuilder.toString().lastIndexOf(", ")));
		}
		if (unique) return sqlBuilder.toString() + " LIMIT 1";
		return sqlBuilder.toString() + (limit != 0 ? " LIMIT " + limit : "");
	}
	
	private String getColumns() {
		StringBuilder columnBuilder = new StringBuilder();
		for (String column : columns) {
			columnBuilder.append(column + ",");
		}
		if (columnBuilder.toString().contains(",")) return columnBuilder.toString().substring(0, columnBuilder.toString().lastIndexOf(","));
		return columnBuilder.toString();
	}

}