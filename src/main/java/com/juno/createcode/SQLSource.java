package com.juno.createcode;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Component;

import com.juno.bean.Config;


@Component
public class SQLSource {
        
	private Map<String, SQLBean> sqlMap = new HashMap<String, SQLBean>();
	
	public Map<String, SQLBean> getSqlMap() {
		return sqlMap;
	}
	
	public SQLBean getSQLBean(String tableName){
		if(sqlMap != null){
			return sqlMap.get(tableName.toUpperCase());
		}else{
			return null;
		}
	} 
	
	public String getSaveSQL(String tableName){
		SQLBean sqlBean = null;
		if((sqlBean = getSQLBean(tableName)) != null){
			return sqlBean.getInertSql(); 
		}else{
			return null;
		}
	}
	
	public String getUpdateSQL(String tableName){
		SQLBean sqlBean = null;
		if((sqlBean = getSQLBean(tableName)) != null){
			return sqlBean.getUpdateSql();
		}else{
			return null;
		}
	}
	
	
	public String getDeleteSQL(String tableName){
		SQLBean sqlBean = null;
		if((sqlBean = getSQLBean(tableName)) != null){
			return sqlBean.getDeleteSql();
		}else{
			return null;
		}
	}
	
	
	public String getFindByIdSQL(String tableName){
		SQLBean sqlBean = null;
		if((sqlBean = getSQLBean(tableName)) != null){
			return sqlBean.getFindByIdSql();
		}else{
			return null;
		}
	}
	
	public String getFindAllSQL(String tableName){
		SQLBean sqlBean = null;
		if((sqlBean = getSQLBean(tableName)) != null){
			return sqlBean.getFindAllSql();
		}else{
			return null;
		}
	}
	
	
	public String getFindByPageSQL(String tableName){
		SQLBean sqlBean = null;
		if((sqlBean = getSQLBean(tableName)) != null){
			return sqlBean.getFindByPageSql();
		}else{
			return null;
		}
	}
	
	
	@Autowired
	public SQLSource(DataSource dataSource, Config config) throws Exception{
		
		String database_schema = config.getDbSchema();
		DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
		ResultSet resultSet = metaData.getTables(null, database_schema, null, new String[]{"TABLE"});
		Map<String, SQLBean> sqlBeans = new HashMap<String, SQLBean>();
		
		while(resultSet.next()){
			SQLBean sqlBean = new SQLBean();
			
			String tableName = resultSet.getString(3);
			sqlBean.setTableName(tableName);
			
			ResultSet columns = metaData.getColumns(null, database_schema, tableName, null);
			List<String> columnNames = new ArrayList<String>();
			List<Integer> columnTypes = new ArrayList<Integer>();
			List<String> decimalDigits = new ArrayList<String>(); 
			List<String> javaBeanColumns = new ArrayList<String>();
			List<String> remarks = new ArrayList<String>();
			List<String> jdbcTypes = new ArrayList<String>();
			
			while(columns.next()){
				columnNames.add(columns.getString(4)); // 列名
				columnTypes.add(Integer.parseInt(columns.getString(5))); // 列类型
				jdbcTypes.add(columns.getString(6));  // 列类型中文名
				decimalDigits.add(columns.getString(9));  // 精度
				remarks.add(columns.getString("REMARKS"));  // 注释
				String value = JdbcUtils.convertUnderscoreNameToPropertyName(columns.getString(4).toLowerCase());
				javaBeanColumns.add(value);
			}
			
			sqlBean.setColumnNames(columnNames);
			sqlBean.setColumnTypes(columnTypes);
			sqlBean.setDecimalDigits(decimalDigits);
			sqlBean.setJavaBeanColumns(javaBeanColumns);
			sqlBean.setRemarks(remarks);
			sqlBean.setJdbcTypes(jdbcTypes);
			
			ResultSet pk = metaData.getPrimaryKeys(null, database_schema, tableName);
			while(pk.next()){
				sqlBean.setPkName(pk.getString(4)); // 主键列名
				String pkJdbType = jdbcTypes.get(columnNames.indexOf(sqlBean.getPkName()));
				sqlBean.setPkJdbcType(pkJdbType);
			}
			sqlBean.setPkBeanName(JdbcUtils.convertUnderscoreNameToPropertyName(sqlBean.getPkName().toLowerCase()));
			sqlBean.doCreateSQL();  // 开始装配SQL
			
			sqlBeans.put(sqlBean.getTableName().toUpperCase(), sqlBean);
		}
		
		this.sqlMap = sqlBeans;
	}
	
	
}
