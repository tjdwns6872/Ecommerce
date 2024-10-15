package com.simple.ecommerce.controller.sms;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple.ecommerce.dto.sms.RequestSmsDto;
import com.simple.ecommerce.service.sms.SmsService;
import com.simple.ecommerce.util.AjaxResult;

@RestController
@RequestMapping("/ecommerce/api/sms")
public class SmsRestController {

    @Autowired
    private SmsService smsService;
    
    @PostMapping("/write")
    public ResponseEntity<AjaxResult<Void>> write(@RequestBody RequestSmsDto smsDto) throws InvalidKeyException, NoSuchAlgorithmException, IOException{
        smsService.smsRequest(smsDto);
        return null;
    }
}
