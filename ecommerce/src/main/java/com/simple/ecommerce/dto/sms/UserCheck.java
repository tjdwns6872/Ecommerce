package com.simple.ecommerce.dto.sms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCheck {
    
    private String type;
    private String email;
    private String phone;
    private String name;
}
