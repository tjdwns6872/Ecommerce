package com.simple.ecommerce.dto.sms;

import lombok.*;

@Data
@NoArgsConstructor
public class MessageDataDto {
    
    // 수신자 번호
    private String to;

    // 발신자 번호(사전에 등록된 번호)
    private String form;

    // 메시지 제목
    private String subject;

    // 메시지 내용
    private String text;

    // 메시지 타입
    private String type;
}
