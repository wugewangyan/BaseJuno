package com.juno.service;

import java.util.List;

import com.juno.model.Emp;

public interface IEmpService {

	
	public void insert(Emp emp);
	
	public List<Emp> listAll();
	
	public List<Emp> listAllMap();
}
