package com.simple.ecommerce.component.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.simple.ecommerce.util.sms.AbstractSmsWriteType;
import com.simple.ecommerce.util.sms.SmsFindWriteCode;
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

    // 클래스 빈처리를 위한 코드
    @Autowired
    private ApplicationContext context;

    public AbstractSmsWriteType getSmsWriteType(String type) throws Exception{
        // Sms를 사용하는 기능에 따라 처리되는 데이터가 달라지기 때문에 분기처리를 위한 코드
        switch (type) {
            case "join":
                // SmsJoinWriteCode클래스 선언
                return context.getBean(SmsJoinWriteCode.class);
            case "emailFind":
                //SmsFindWriteCode클래스 선언
                return context.getBean(SmsFindWriteCode.class);
            case "passwordFind":
                //SmsFindWriteCode클래스 선언
                return context.getBean(SmsFindWriteCode.class);
            default:
                throw new Exception();
        }
    }
}
