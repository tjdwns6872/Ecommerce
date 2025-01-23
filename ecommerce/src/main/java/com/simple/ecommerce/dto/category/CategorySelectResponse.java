package com.simple.ecommerce.dto.category;

import java.util.List;

import com.simple.ecommerce.entity.category.CategoryEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategorySelectResponse {

    List<CategoryEntity> CategoryList;
}
