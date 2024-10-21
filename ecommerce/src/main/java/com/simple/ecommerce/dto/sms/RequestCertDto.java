package com.simple.ecommerce.dto.sms;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestCertDto {
    // 인증코드 고유 식별 번호
    private Integer certId;
    // 인증코드
    private String code;
}
