package com.simple.ecommerce.service.mail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MailComponent {

    @Value("${mailgun.api.key}")
    private String API_KEY;
    
    @Value("${mailgun.api.url}")
    private String MAILGUN_URL;

    @Value("${mailgun.api.domain}")
    private String DOMAIN;

    /**
     * 메일 전송을 위한 메소드
     * @param sendMailForm
     * @return Body 데이터
     * @throws UnirestException
     */
    public JsonNode sendSimpleMessage(SendMailForm sendMailForm) throws UnirestException {
      HttpResponse<JsonNode> request = Unirest.post(MAILGUN_URL+DOMAIN+"/messages")
        .basicAuth("api", API_KEY)
        .field("from", "Excited User <USER@"+DOMAIN+">")
        .field("to", sendMailForm.getTo())
        .field("subject", sendMailForm.getSubject())
        .field("text", sendMailForm.getText())
        .asJson();
      return request.getBody();
  	}

}
