package com.juno.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juno.service.IBookService;


@Controller
@RequestMapping("/simple/*")
public class SimpleController {
	
	@Value("#{messageSource.getMessage('admin.email', null, 'en_US')}")
	private String email;
	
	@Autowired
	private IBookService iBookService;
	
	@RequestMapping("show/{user}")
	public String simpleGet(String simpleName, Model model, @PathVariable("user") String user){
		Date date = new Date();
		model.addAttribute("today", date);
		model.addAttribute("simpleName", simpleName);
		model.addAttribute("user", user);
		model.addAttribute("email", email);
		
		return "welcome";
	}
	
	@RequestMapping("purchase")
	public String purchase(String isbn, String account, Model model){
		iBookService.purchase(isbn, account);
		model.addAttribute("email", email);
		return "welcome";
	}
}
