package com.simple.ecommerce.dto.sms;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestSmsDto {

    // 메시지 정보
    private MessageDataDto message;   
}
