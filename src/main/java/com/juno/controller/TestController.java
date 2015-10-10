package com.juno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.juno.service.ITestService;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private ITestService testService;

    @RequestMapping(method = RequestMethod.GET)
    public String test(Model model) {
        testService.purchase("Thinking in Java", "$16.99");
        return "emp/add_emp";
    }
}
