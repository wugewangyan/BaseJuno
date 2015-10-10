package com.juno.mapper;

import java.util.List;

import com.juno.model.Emp;

public interface EmpMapper {
	
	public void insertEmp(Emp emp);
	
	public void updateEmp(Emp emp);
	
	public void deleteEmp(Integer empno);
	
	public Emp selectEmp();
	
	public List<Emp> selectAllEmp();
	
	public Integer selectAllCount();
	
	public List<Emp> selectForMap();
}
