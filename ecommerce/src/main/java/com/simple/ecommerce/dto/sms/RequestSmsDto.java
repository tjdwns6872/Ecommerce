package com.simple.ecommerce.dto.sms;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestSmsDto {

    // 메시지 정보
    private MessageDataDto message;   

    // SMS 용도 분기처리를 위한 DTO
    private CustomSmsDto Custom;

    private UserCheck check;
}
