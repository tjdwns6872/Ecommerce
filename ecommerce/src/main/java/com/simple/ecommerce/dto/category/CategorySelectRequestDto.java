package com.simple.ecommerce.dto.category;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategorySelectRequestDto {
    
    private Integer parentId;
    private String name;
    private String description;
}
