package com.simple.ecommerce.dto.products;

import java.util.List;

import com.simple.ecommerce.entity.category.CategoryEntity;
import com.simple.ecommerce.entity.products.ProductsEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductSelectResponse {

    List<ProductsEntity> ProductList;
    List<CategoryEntity> CategoryList;
}
