package com.juno.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.juno.controller.validator.EmpValidator;
import com.juno.model.Emp;
import com.juno.service.IEmpService;


@Controller
@RequestMapping(value = "/emp/*")
@SessionAttributes("emp")
public class EmpController {

	@Resource(name = "mybatisEmpServiceImpl")
	private IEmpService empService;
	
	@Autowired
	private EmpValidator empValidator;
	
//	@ModelAttribute("depts")
//	public List<Dept> getAllDepts(){
//		return this.empService.getAllDepts();
//	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String setForm(@RequestParam(required = false, value = "empno") Integer empno, Model model){
		Emp emp = new Emp();
		emp.setEmpno(empno);
		model.addAttribute("emp", emp);
		//model.addAttribute("depts", this.empService.getAllDepts());
		return "emp/add_emp";
	}
	
	@RequestMapping(value = "saveEmp", method = RequestMethod.POST)
	public String post(@ModelAttribute("emp") Emp emp, BindingResult result, SessionStatus status, Model model){
		empValidator.validate(emp, result);
		if(result.hasErrors()){
			model.addAttribute("emp", emp);
			return "emp/add_emp";
		}else{
			this.empService.insert(emp);
			// 清除SessionAttribute中的emp对象
			status.setComplete();  
			return "redirect:list";
		}
	}
	
	@RequestMapping("list")
	public String list(Model model){
		List<Emp> emps = this.empService.listAll();
		model.addAttribute("list", emps);
		return "emp/list_emps";
	}
	
}
