package com.simple.ecommerce.testcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class TestController {
    
    @RequestMapping("test")
    public String testPage(){
        return "test/test";
    }
}
