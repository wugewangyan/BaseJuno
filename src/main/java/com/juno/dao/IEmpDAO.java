package com.juno.dao;

import java.util.List;

import com.juno.model.Dept;
import com.juno.model.Emp;

public interface IEmpDAO {
	
	public List<Emp> findAll();
	
	public Integer findCount();
	
	public Emp findByID(Integer id);
	
	public void insert(Emp emp);
	
	public void update(Emp emp);
	
	public List<Dept> getAllDept();
}
