package com.simple.ecommerce.util.sms;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import com.simple.ecommerce.dto.sms.RequestSmsDto;

public interface SmsWriteType {

    public String smsWrite(RequestSmsDto smsDto) throws MalformedURLException, IOException;

    public abstract RequestSmsDto dtoSetting(RequestSmsDto smsDto);

    public String headerSetting() throws IOException, NoSuchAlgorithmException, InvalidKeyException;
    
}
