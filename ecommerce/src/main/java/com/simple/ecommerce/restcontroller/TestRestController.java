package com.simple.ecommerce.restcontroller;

import org.springframework.web.bind.annotation.RestController;

import com.simple.ecommerce.mapper.testMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class TestRestController {
    
    @Autowired
    private testMapper testMapper;

    @GetMapping("/")
    public int getMethodName() {
        return testMapper.testMybatis();
    }
    
}
