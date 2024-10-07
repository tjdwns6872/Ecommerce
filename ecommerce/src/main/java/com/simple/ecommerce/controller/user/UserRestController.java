package com.simple.ecommerce.controller.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.simple.ecommerce.dto.social.SocialConnectDto;
import com.simple.ecommerce.dto.users.UsersLoginDto;
import com.simple.ecommerce.service.user.UsersLoginService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/ecommerce/api/user")
public class UserRestController {
    
    @Autowired
    private UsersLoginService usersLoginService;

    //회원가입 컨트롤러
    @PutMapping("/signup")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        //TODO: process PUT request
        
        return entity;
    }

    //로그인 컨트롤러
    @PostMapping("/login")
    public ResponseEntity<String> login(HttpServletResponse response
        ,@RequestBody UsersLoginDto loginDto) throws IOException {
        // 로그인 시 상태메시지를 저장할 변수 선언
        String message = null;
        // 로그인 시도
        message = usersLoginService.login(loginDto);
        return ResponseEntity.ok(message);
    }

    //소셜 로그인 컨트롤러
    @PostMapping("/login/{platform}")
    public void login(HttpServletResponse response
        , @PathVariable("platform") String platform) throws IOException {
        //URL에서 받은 PathVariable값을 가지고 login 함수로 이동
        String url = usersLoginService.login(platform);
        //리턴 받은 url로 리다이렉트
        response.sendRedirect(url);
    }

    //소셜로그인 시 인증코드를 받는 컨트롤러
    @GetMapping("/{platform}/callback")
    public String platformCallback(SocialConnectDto socialConnectDto, @PathVariable("platform") String platform) throws JsonMappingException, JsonProcessingException {
        String url = usersLoginService.socialCallback(socialConnectDto, platform);
        System.out.println(url);
        return new String();
    }
    
    
}
