package com.simple.ecommerce.controller.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple.ecommerce.dto.sms.MessageDto;
import com.simple.ecommerce.dto.sms.RequestSmsDto;
import com.simple.ecommerce.service.sms.SmsWriteService;
import com.simple.ecommerce.util.AjaxResult;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/ecommerce/api/sms")
public class SmsRestController {

    @Autowired
    private SmsWriteService smsWriteService;
    
    @PostMapping("/write")
    public ResponseEntity<AjaxResult<Void>> write(@RequestBody RequestSmsDto smsDto){
        System.out.println("\n\n\n\n"+smsDto.toString());
        return null;
    }
}
