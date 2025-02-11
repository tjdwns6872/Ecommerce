package com.simple.ecommerce.dto.products;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class ProductInsertDto {
    
    private Integer productsId;

    @NotBlank
    private Integer categoryId;

    //넘어온 토큰으로 구할 예정
    private Integer userId;
    
    private String userToken;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private Integer price;

    @NotBlank
    private Integer stockQuantity;

    @NotBlank
    private Integer status;

    @NotBlank
    private Date createdAt;

    @NotBlank
    private Date updatedAt;
}
