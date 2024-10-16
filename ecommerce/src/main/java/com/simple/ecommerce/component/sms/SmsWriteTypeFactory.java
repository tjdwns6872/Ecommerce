package com.simple.ecommerce.component.sms;

import org.springframework.stereotype.Component;

import com.simple.ecommerce.util.sms.AbstractSmsWriteType;
import com.simple.ecommerce.util.sms.SmsJoinWriteCode;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SmsWriteTypeFactory {

    /**
     * 전달 받은 String 값으로 해당 커넥션 리턴
     * @param type - (String)사용자가 요청한 SMS 타입
     * @return SmsWriteType - 사용자가 요청한 커넥션
     * @throws Exception 변경예정
     */
    public AbstractSmsWriteType getSmsWriteType(String type) throws Exception{
        switch (type) {
            case "join":
                return new SmsJoinWriteCode();
            default:
                throw new Exception();
        }
    }
}
