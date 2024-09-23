package com.simple.ecommerce.controller;

import org.springframework.web.bind.annotation.RestController;

import com.simple.ecommerce.mapper.testMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class TestRestController {
    
    @Autowired
    private testMapper testMapper;

    @GetMapping("/test")
    public int getMethodName() {
        return testMapper.testMybatis();
    }
    
}
