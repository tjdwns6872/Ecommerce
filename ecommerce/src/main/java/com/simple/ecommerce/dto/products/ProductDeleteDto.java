package com.simple.ecommerce.dto.products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductDeleteDto {
    
    private Integer productId;

    private String userToken;
    private Integer userId;
}
