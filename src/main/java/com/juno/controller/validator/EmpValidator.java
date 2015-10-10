package com.juno.controller.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.juno.model.Emp;

@Component
public class EmpValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Emp.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ename", "required.ename", "雇员名称不能为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "job", "required.job", "雇员职务不能为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mgr", "required.mgr", "雇员上司不能为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hiredate", "required.hiredate", "雇佣日期不能为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sal", "required.sal", "雇员薪水不能为空");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deptno", "required.deptno", "所属部门不能为空");

		Emp emp = (Emp)target;
		if(emp.getSal() != null){
			if(new BigDecimal("100000").compareTo(emp.getSal()) <= 0){
				errors.rejectValue("sal", "invalid.emp.sal", "员工薪水不该超过100000");
			}
		}
		
	}
}
