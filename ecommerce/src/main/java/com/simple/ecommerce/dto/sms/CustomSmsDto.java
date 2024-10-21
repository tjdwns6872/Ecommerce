package com.simple.ecommerce.dto.sms;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomSmsDto {

    // 타입에 따라 분기 처리
    // 1. joinCertCode(회원가입 시 인증코드)
    // 2. findCertCode(아이디/비밀번호 찾기 인증코드)
    private String customType;

    // 인증코드(없으면 null처리)
    private String customCode;

    private String customHeaderStr;
    
}
