package com.simple.ecommerce.dto.products;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SelectDto {
    
    private String name;

    private Integer categoryId;

    private Integer userId;

    private Date startDate;
    private Date endDate;

    private Integer startPrice;
    private Integer endPrice;

    private Integer nowPage;

}