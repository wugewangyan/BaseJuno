package com.juno.createcode;

public class BeanMappingInfo {

	private String tableName;
	private String primaryKey;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	@Override
	public String toString() {
		return "BeanMappingInfo [tableName=" + tableName + ", primaryKey="
				+ primaryKey + "]";
	}
}
