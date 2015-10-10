package com.juno.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juno.interceptor.PageInterceptor;
import com.juno.mapper.EmpMapper;
import com.juno.model.Emp;
import com.juno.service.IEmpService;

@Service
public class MybatisEmpServiceImpl implements IEmpService {

	@Resource(name = "empMapper")
	private EmpMapper empMapper;
	
	@Transactional
	public void insert(Emp emp) {
		this.empMapper.insertEmp(emp);
	}
	
	
	public List<Emp> listAll() {
		PageInterceptor.startPage(1, 5);  
		this.empMapper.selectAllEmp();
		return PageInterceptor.endPage().getResult();  
	}
	
	public List<Emp> listAllMap() {
		return this.empMapper.selectForMap();
	}
}
