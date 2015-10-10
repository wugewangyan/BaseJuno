package com.juno.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.juno.dao.IEmpDAO;
import com.juno.model.Dept;
import com.juno.model.Emp;

@Repository
public class EmpDAOImpl implements IEmpDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	/**
	 * jdbcTemplate 查询多行多列，并自动组转成List bean
	 */
	public List<Emp> findAll() {
		String sql = " select * from emp ";
		return this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Emp.class));
	}
	
	
	/**
	 * jdbcTemplate 查询单列单行（单个值）
	 */
	public Integer findCount(){
		String sql = " select count(*) from emp ";
		return this.jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	/**
	 * jdbcTemplate 查询单行多列 Bean
	 */
	public Emp findByID(Integer id) {
		String sql = " select * from emp where empno = ? ";
		return this.jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Emp.class), id);
	}
	
	
	/**
	 * jdbcTemplate 使用语句设置器更新数据库（PreparedStatementSetter）
	 */
	public void insert(final Emp emp) {
		String sql = " insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno) values (?, ?, ?, ?, ?, ?, ?, ?) ";
		this.jdbcTemplate.update(sql, new PreparedStatementSetter(){
			public void setValues(PreparedStatement stat) throws SQLException {
				stat.setInt(1, emp.getEmpno());
				stat.setString(2, emp.getEname());
				stat.setString(3, emp.getJob());
				stat.setInt(4, emp.getMgr());
				stat.setDate(5, new java.sql.Date(emp.getHiredate().getTime()));
				stat.setBigDecimal(6, emp.getSal());
				stat.setBigDecimal(7, emp.getComm());
				stat.setInt(8, emp.getDeptno());
			}
		});
		
	}
	
	/**
	 * jdbcTemplate 使用可变参数列表更新数据库
	 */
	public void update(Emp emp) {
		String sql = " update emp e set e.ename = ?, e.job = ?, e.mgr = ?, e.hiredate = ?, e.sal = ?, e.comm = ?, e.deptno = ? where e.empno = ? ";
		this.jdbcTemplate.update(sql, emp.getEname(), emp.getJob(), emp.getMgr(), emp.getHiredate(),
				emp.getSal(), emp.getComm(), emp.getDeptno(), emp.getEmpno());
		
	}
	
	
	/**
	 * jdbcTemplate 使用batchUpdate批量更新数据库（BatchPreparedStatementSetter）
	 * @param emps
	 */
	public void batchUpdate(final List<Emp> emps){
		String sql = " insert into emp(empno, ename, job, mgr, hiredate, sal, comm, deptno) value (?, ?, ?, ?, ?, ?, ?, ?) ";
		this.jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			public void setValues(PreparedStatement stat, int index) throws SQLException {
				Emp emp = emps.get(index);
				stat.setInt(1, emp.getEmpno());
				stat.setString(2, emp.getEname());
				stat.setString(3, emp.getJob());
				stat.setInt(4, emp.getMgr());
				stat.setDate(5, new java.sql.Date(emp.getHiredate().getTime()));
				stat.setBigDecimal(6, emp.getSal());
				stat.setBigDecimal(7, emp.getComm());
				stat.setInt(8, emp.getDeptno());
			}
			
			public int getBatchSize() {
				return emps.size();
			}
		});
	}
	
	
	
	public List<Dept> getAllDept() {
		String sql = " select * from dept ";
		return this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Dept.class));
	}
}
