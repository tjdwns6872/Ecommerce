package com.simple.ecommerce.component.sms;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simple.ecommerce.util.sms.SmsWriteType;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SmsWriteTypeFactory {

    @Autowired
    private Map<String, SmsWriteType> smsWriteTypeMap;

    /**
     * 전달 받은 String 값으로 해당 커넥션 리턴
     * ,로직 설명 : Map<String, SmsWriteType> 타입으로 sms+String Key값으로 사용자가 요청한 SMS 타입 커넥션 리턴
     * @param type - (String)사용자가 요청한 SMS 타입
     * @return SmsWriteType - 사용자가 요청한 커넥션
     * @throws 어떤 상황에서 예외가 발생!
     */
    public SmsWriteType getSmsWriteType(String type){
        return smsWriteTypeMap.get("sms"+type);
    }
}
