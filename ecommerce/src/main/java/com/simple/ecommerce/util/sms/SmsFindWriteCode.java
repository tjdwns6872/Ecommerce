package com.simple.ecommerce.util.sms;

import com.simple.ecommerce.dto.sms.RequestSmsDto;
import com.simple.ecommerce.util.ShaUtil;

public class SmsFindWriteCode extends AbstractSmsWriteType{

    @Override
    public RequestSmsDto dtoSetting(RequestSmsDto smsDto) {
        RequestSmsDto dto = new RequestSmsDto();
        return dto;
    }
    
}
