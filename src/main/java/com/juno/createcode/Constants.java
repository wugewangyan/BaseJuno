package com.juno.createcode;


public class Constants {
	// insert 
	public static final String INSERT_PRE_SQL = " INSERT INTO ";
	public static final String INSERT_VALUES_SQL = " VALUES( ";
	
	// update
	public static final String UPDATE_PRE_SQL = " UPDATE ";
	public static final String UPDATE_SET_SQL = " SET ";
	public static final String UPDATE_PARAMS_FALG = " = ?, ";
	
	
	// delete
	public static final String DELETE_PRE_SQL = " DELETE FROM ";
	
	// select
	public static final String SELECT_PRE_SQL = " SELECT ";
	
	
	// 分页
	public static final String PAGING_PRE_SQL = " select * from ( select row_.*, rownum rownum_ from ( ";
	public static final String PAGING_END_SQL = " ) row_ where rownum <= ?) where rownum_ > ? ";
	public static final String PAGING_COUNT_SQL = " select count(*) from ( ";
	
	//Common
	public static final String COMMA = ", ";
	public static final String LEFT_BRACES = " ( ";
	public static final String RIGHT_BRACES = " ) ";
	public static final String PARAMS_FLAG = "?";
	public static final String WHERE_SQL = " WHERE ";
	public static final String FROM_SQL = " FROM ";
	public static final String EQUAL_PARAMS_FALG = " = ? ";
	
	public static final String OPT_TYPE_UPDATE = "UPDATE";
	public static final String OPT_TYPE_SAVE = "SAVE";
	
	public static final String BASE_PACKAGE_NAME = "com.pccw.business.epms";
	
	public static final String FIELD_SHOW_TYPE_CHECKBOX = "checkbox";
	public static final String FIELD_SHOW_TYPE_DATEINPUT = "dateInput";
	public static final String FIELD_SHOW_TYPE_INPUT = "input";
	public static final String FIELD_SHOW_TYPE_LINK = "link";
	public static final String FIELD_SHOW_TYPE_RADIO = "radio";
	public static final String FIELD_SHOW_TYPE_SELECT = "select";
	public static final String FIELD_SHOW_TYPE_SELECTINPUT = "selectInput";
	
}
