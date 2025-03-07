package com.simple.ecommerce.converter.products;

import com.simple.ecommerce.converter.ToEntityConverter;
import com.simple.ecommerce.dto.products.ProductInsertDto;
import com.simple.ecommerce.entity.products.ProductsEntity;
import com.simple.ecommerce.util.products.StatusEnum;

public class ProductsConverter implements ToEntityConverter<ProductsEntity, ProductInsertDto>{

    @Override
    public ProductsEntity toEntity(ProductInsertDto dto) {
        ProductsEntity entity = ProductsEntity.builder()
            .ecProductsId(dto.getProductsId())
            .ecProductsCategoryId(dto.getCategoryId())
            .ecProductsSellerId(dto.getUserId())
            .ecProductsName(dto.getName())
            .ecProductsDescription(dto.getDescription())
            .ecProductsPrice(dto.getPrice())
            .ecProductsStockQuantity(dto.getStockQuantity())
            .ecProductsStatus(StatusEnum.getEnum(dto.getStatus()).getType())
            .ecProductsCreatedAt(dto.getCreatedAt())
            .ecProductsUpdatedAt(dto.getUpdatedAt())
            .build();
        return entity;
    }

}
