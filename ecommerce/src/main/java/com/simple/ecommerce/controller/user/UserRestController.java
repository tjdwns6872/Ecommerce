package com.simple.ecommerce.controller.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.simple.ecommerce.dto.social.SocialConnectDto;
import com.simple.ecommerce.service.user.UserService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/ecommerce/api/user")
public class UserRestController {
    
    @Autowired
    private UserService userService;

    @PutMapping("/signup")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        //TODO: process PUT request
        
        return entity;
    }

    @PostMapping("/login/{platform}")
    public String login(HttpServletResponse response, @PathVariable("platform") String platform) throws IOException {
        String url = userService.login(platform);
        response.sendRedirect(url);
        return null;
    }

    @GetMapping("/{platform}/callback")
    public String platformCallback(SocialConnectDto socialConnectDto, @PathVariable("platform") String platform) throws JsonMappingException, JsonProcessingException {
        String url = userService.socialCallback(socialConnectDto, platform);
        System.out.println(url);
        return new String();
    }
    
    
}
