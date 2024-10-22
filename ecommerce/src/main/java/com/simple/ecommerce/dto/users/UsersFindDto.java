package com.simple.ecommerce.dto.users;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsersFindDto {
    
    private String email;

    private String name;

    private String phone;
    
    private String type;
}
