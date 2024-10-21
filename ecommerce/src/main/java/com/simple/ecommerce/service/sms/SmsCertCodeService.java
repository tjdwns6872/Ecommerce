package com.simple.ecommerce.service.sms;

import org.springframework.stereotype.Service;

import com.simple.ecommerce.dto.sms.RequestCertDto;

@Service
public interface SmsCertCodeService {
    
    /**
     * 인증번호 확인 및 비교해주는 메소드
     * @param certDto
     */
    public void codeCert(RequestCertDto certDto);
}
