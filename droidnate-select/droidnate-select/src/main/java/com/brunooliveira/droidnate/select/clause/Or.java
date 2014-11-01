package com.brunooliveira.droidnate.select.clause;

import java.util.ArrayList;
import java.util.List;

public final class Or implements Criteria {
	
	private List<Criteria> criterias;
	
	public Or(Criteria... criterias) {
		this.criterias = new ArrayList<Criteria>();
		for (Criteria criteria : criterias) {
			if (criteria != null) this.criterias.add(criteria);
		}
	}

	@Override
	public String toSql() {
		StringBuilder sqlBuilder = new StringBuilder();
		for (Criteria criteria : criterias) {
			sqlBuilder.append(criteria.toSql() + " OR ");
		}
		if (sqlBuilder.toString().contains(" OR ")) return sqlBuilder.toString().substring(0, sqlBuilder.toString().lastIndexOf(" OR "));
		return "";
	}

}