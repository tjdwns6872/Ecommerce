package com.simple.ecommerce.dto.category;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InsertDto {
    
    private Integer ecCategoryId;

    private Integer ecCategoryParentId;

    private String ecCategoryName;

    private String ecCategoryDescription;

    private Date ecCategoryCreatedAt;
    
    private Date ecCategoryUpdateAt;
}
