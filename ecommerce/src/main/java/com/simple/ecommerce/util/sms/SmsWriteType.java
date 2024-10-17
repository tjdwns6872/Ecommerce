package com.simple.ecommerce.util.sms;

import com.simple.ecommerce.dto.sms.RequestSmsDto;

public interface SmsWriteType {

    /**
     * // SMS 전송 시 사용할 데이터 셋팅
     * @param smsDto
     * @return RequestSmsDto
     */
    public abstract RequestSmsDto dtoSetting(RequestSmsDto smsDto);
}
