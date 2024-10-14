package com.simple.ecommerce.dto.sms;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomSmsDto {

    // 타입에 따라 분기 처리
    // 1. joinCertCode(회원가입 시 인증코드)
    // 2. finCertCode(아이디/비밀번호 찾기 인증코드)
    private String type;

    // 인증코드(없으면 null처리)
    private String code;
    
}
