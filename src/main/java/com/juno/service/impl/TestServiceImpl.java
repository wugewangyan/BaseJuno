package com.juno.service.impl;

import org.springframework.stereotype.Component;

import com.juno.service.ITestService;

@Component
public class TestServiceImpl implements ITestService {

    public void purchase(String isbn, String account) {
        System.out.println("Success to purchase the book : " + isbn + ", account : " + account);
    }

}
