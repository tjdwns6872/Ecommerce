package com.simple.ecommerce.util.sms;

import com.simple.ecommerce.dto.sms.RequestSmsDto;

public interface SmsCertDb {
    
    public String certCodeInsert(RequestSmsDto smsDto);
}
