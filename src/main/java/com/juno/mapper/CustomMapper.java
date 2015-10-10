package com.juno.mapper;

import java.util.List;

import com.juno.model.WugeCustomInfo;

public interface CustomMapper {
	
	public void doCreate(WugeCustomInfo custom);
	
	public void doUpdate(WugeCustomInfo custom);
	
	public void doDelete(String customId);
	
	public WugeCustomInfo selectById(String customId);
	
	public List<WugeCustomInfo> selectAllForPages(WugeCustomInfo custom);
	
	public Integer selectCountForPages(WugeCustomInfo custom);
}
