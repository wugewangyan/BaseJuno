package com.juno.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.juno.dao.IEmpDAO;
import com.juno.mapper.EmpMapper;
import com.juno.model.Dept;
import com.juno.model.Emp;

@Repository
public class MybatisEmpDAOImpl implements IEmpDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	/**
	 * SqlSessionTemplate 查询多行多列，并自动组转成List bean
	 */
	public List<Emp> findAll() {
		return this.sqlSessionTemplate.selectList(EmpMapper.class.getCanonicalName() + ".selectAllEmp");
	}
	
	
	
	/**
	 * SqlSessionTemplate 查询单行多列 Bean
	 */
	public Emp findByID(Integer id) {
		return this.sqlSessionTemplate.selectOne(EmpMapper.class.getCanonicalName() + ".selectEmp", id);
	}
	
	
	/**
	 * SqlSessionTemplate 插入一条记录
	 */
	public void insert(Emp emp) {
		this.sqlSessionTemplate.insert(EmpMapper.class.getCanonicalName() + ".insertEmp", emp);
	}
	
	/**
	 * SqlSessionTemplate 更新一条记录
	 */
	public void update(Emp emp) {
		this.sqlSessionTemplate.update(EmpMapper.class.getCanonicalName() + ".updateEmp", emp);
		
	}
	
	public Integer findCount() {
		return this.sqlSessionTemplate.selectOne(EmpMapper.class.getCanonicalName() + ".selectAllCount");
	}
	
	public List<Dept> getAllDept() {
		// TODO Auto-generated method stub
		return null;
	}
}
