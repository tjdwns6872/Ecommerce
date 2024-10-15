package com.simple.ecommerce.service.sms;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import com.simple.ecommerce.dto.sms.RequestSmsDto;

@Service
public interface SmsService {

    String smsRequest(RequestSmsDto smsDto) throws IOException, InvalidKeyException, NoSuchAlgorithmException;
}
