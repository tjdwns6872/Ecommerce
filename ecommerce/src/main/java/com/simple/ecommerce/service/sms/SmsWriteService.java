package com.simple.ecommerce.service.sms;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import com.simple.ecommerce.dto.sms.RequestSmsDto;

@Service
public interface SmsWriteService {

    String SmsWrite(RequestSmsDto smsdto) throws IOException, InvalidKeyException, NoSuchAlgorithmException;
}
