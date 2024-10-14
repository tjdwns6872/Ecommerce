package com.simple.ecommerce.serviceimpl.sms;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.ecommerce.dto.sms.RequestSmsDto;
import com.simple.ecommerce.service.sms.SmsWriteService;
import com.simple.ecommerce.util.sms.SmsConnect;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SmsWriteServiceImpl implements SmsWriteService{
    

    @Autowired
    private SmsConnect smsConnect;

    @Override
    public String SmsWrite(RequestSmsDto smsdto) throws IOException, InvalidKeyException, NoSuchAlgorithmException {
        log.info("\n\n{}\n\n", smsdto.toString());
        smsConnect.smsRequest(smsdto);

        return null;
    }
}
