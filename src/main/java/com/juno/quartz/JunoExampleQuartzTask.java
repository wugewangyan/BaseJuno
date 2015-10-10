package com.juno.quartz;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.juno.mapper.EmpMapper;
import com.juno.model.Emp;

@Component
public class JunoExampleQuartzTask {
	
	@Resource(name = "empMapper")
	private EmpMapper empMapper;
	
	@Value(value = "10")
	private Integer times;
	
	public List<Emp> selectAll(){
		List<Emp> emps = new ArrayList<Emp>();
		for(int i = 0; i < times; i++){
			List<Emp> result = empMapper.selectAllEmp();
			emps.addAll(result);
			for(Emp emp : result){
				System.out.println(emp.getEname() + "  --->  " + emp.getJob());
			}
		}
		
		System.out.println("获取的Emp个数为：" + emps.size());
		return emps;
	}
}
