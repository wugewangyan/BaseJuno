package com.juno.dao;

import com.juno.model.Custom;

public interface CustomDao {

	public void save(Custom custom);
	
	public Custom get(String customId);
	
	public void delete(String customId);
}
