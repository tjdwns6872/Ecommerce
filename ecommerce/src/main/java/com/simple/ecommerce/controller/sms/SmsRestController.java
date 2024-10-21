package com.simple.ecommerce.controller.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simple.ecommerce.dto.sms.RequestCertDto;
import com.simple.ecommerce.dto.sms.RequestSmsDto;
import com.simple.ecommerce.service.sms.SmsCertCodeService;
import com.simple.ecommerce.service.sms.SmsService;
import com.simple.ecommerce.util.AjaxResult;

@RestController
@RequestMapping("/ecommerce/api/sms")
public class SmsRestController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private SmsCertCodeService smsCertCodeService;
    
    @PostMapping("/write")
    public ResponseEntity<AjaxResult<Void>> write(@RequestBody RequestSmsDto smsDto) throws Exception{
        smsService.smsRequest(smsDto);
        return null;
    }

    @GetMapping("/cert/code")
    public ResponseEntity<AjaxResult<Void>> certCode(RequestCertDto certDto){
        //사용자한테 받은 인증코드 비교
        smsCertCodeService.codeCert(certDto);
        //사용자한테 리턴해줄 데이터 폼
        AjaxResult<Void> response = AjaxResult.<Void>builder()
        .status(HttpStatus.OK.value())
        .message("인증 성공!")
        .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
