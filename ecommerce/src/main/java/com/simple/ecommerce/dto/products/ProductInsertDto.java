package com.simple.ecommerce.dto.products;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class ProductInsertDto {
    
    private Integer productsId;

    private Integer categoryId;

    //넘어온 토큰으로 구할 예정
    private Integer userId;
    
    private String userToken;

    private String name;

    private String description;

    private Integer price;

    private Integer stockQuantity;

    private Integer status;

    private Date createdAt;

    private Date updatedAt;
}
