package com.simple.ecommerce.dto.products;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SelectRequestDto {
    
    private String name;

    private Integer categoryId;

    private Integer userId;

    private Date startDate;
    private Date endDate;
}
