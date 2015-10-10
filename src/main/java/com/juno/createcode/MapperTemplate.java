package com.juno.createcode;

import java.util.List;

public class MapperTemplate {
	private String packageName;
	private List<String> imports;
	private String className;
	private List<Entry> entrys;
	private String tableNameAnnotation;
	private String primaryKey;
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public List<Entry> getEntrys() {
		return entrys;
	}
	public void setEntrys(List<Entry> entrys) {
		this.entrys = entrys;
	}
	public List<String> getImports() {
		return imports;
	}
	public void setImports(List<String> imports) {
		this.imports = imports;
	}
	public String getTableNameAnnotation() {
		return tableNameAnnotation;
	}
	public void setTableNameAnnotation(String tableNameAnnotation) {
		this.tableNameAnnotation = tableNameAnnotation;
	}
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
}
