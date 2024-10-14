package com.simple.ecommerce.dto.sms;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestSmsDto {

    // 메시지 정보
    MessageDto message;   
}
