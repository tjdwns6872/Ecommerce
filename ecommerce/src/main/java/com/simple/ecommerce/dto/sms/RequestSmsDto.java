package com.simple.ecommerce.dto.sms;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestSmsDto {

    // 메시지 정보
    MessageDto message;   
}
