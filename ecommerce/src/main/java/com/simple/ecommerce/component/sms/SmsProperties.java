package com.simple.ecommerce.component.sms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.Data;

@Component
@Data
public class SmsProperties {
    // properties에 있는 sms 수신 API URL을 url 변수에 저장
    @Value("${sms.api.url}")
    private String url;

    // properties에 있는 sms API KEY값을 apiKey 변수에 저장
    @Value("${sms.api.key}")
    private String apiKey;

    // properties에 있는 sms API SECRET값을 apiSecret 변수에 저장
    @Value("${sms.api.secret}")
    private String apiSecret;

    @Value("${sms.api.outgoing}")
    private String from;
}
