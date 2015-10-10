package com.juno.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juno.dao.IEmpDAO;
import com.juno.model.Dept;
import com.juno.model.Emp;
import com.juno.service.IEmpService;

@Service
public class EmpServiceImpl implements IEmpService {

	@Resource(name = "mybatisEmpDAOImpl")
	private IEmpDAO iEmpDAO;
	
	@Transactional
	public void insert(Emp emp) {
		this.iEmpDAO.insert(emp);
	}
	
	
	public List<Emp> listAll() {
		return this.iEmpDAO.findAll();
	}
	
	public List<Dept> getAllDepts() {
		return this.iEmpDAO.getAllDept();
	}
	
	public List<Emp> listAllMap() {
		// TODO Auto-generated method stub
		return null;
	}
}
