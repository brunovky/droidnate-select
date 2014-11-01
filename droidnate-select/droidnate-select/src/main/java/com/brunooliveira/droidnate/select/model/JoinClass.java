package com.brunooliveira.droidnate.select.model;

public final class JoinClass {

	private String className;
	private String pk;
	private String fk;
	private String alias;

	public JoinClass(String className, String pk, String fk, String alias) {
		this.className = className;
		this.pk = pk;
		this.fk = fk;
		if (alias != null) this.alias = alias;
		else this.alias = className;
	}
	
	public JoinClass(Class<?> clazz, String pk, String fk, String alias) {
		this.className = clazz.getSimpleName();
		this.pk = pk;
		this.fk = fk;
		if (alias != null) this.alias = alias;
		else this.alias = className;
	}
	
	public JoinClass(String className, String pk, String fk) {
		this.className = className;
		this.pk = pk;
		this.fk = fk;
		this.alias = className;
	}
	
	public JoinClass(Class<?> clazz, String pk, String fk) {
		this.className = clazz.getSimpleName();
		this.pk = pk;
		this.fk = fk;
		this.alias = className;
	}

	public String getClassName() {
		return className;
	}

	public String getPk() {
		return pk;
	}

	public String getFk() {
		return fk;
	}
	
	public String getAlias() {
		return alias;
	}

}