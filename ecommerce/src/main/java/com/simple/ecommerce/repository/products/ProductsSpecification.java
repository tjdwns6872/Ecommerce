package com.simple.ecommerce.repository.products;

import org.springframework.data.jpa.domain.Specification;

import com.simple.ecommerce.dto.products.SelectRequestDto;
import com.simple.ecommerce.entity.products.ProductsEntity;
import com.simple.ecommerce.util.StringUtils;

import jakarta.persistence.criteria.Predicate;

public class ProductsSpecification {
    
    public static Specification<ProductsEntity> productList(SelectRequestDto dto){
        return (root, query, criteriaBuilder) -> {
            // 기본 값
            Predicate predicate = criteriaBuilder.conjunction();

            if (!StringUtils.isStringEmpty(dto.getName())) {
                // 조건 추가 (and)
                predicate = criteriaBuilder.and(
                    criteriaBuilder.like(root.get("ecProductsName"), "%"+dto.getName()+"%"));
            }
            if(dto.getCategoryId() != null && dto.getCategoryId() > 0){
                predicate = criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("ecProductsCategoryId"), dto.getCategoryId())
                );
            }
            if(dto.getUserId() != null && dto.getUserId() > 0){
                predicate = criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("ecProductsSellerId"), dto.getUserId())
                );
            }
            if(dto.getStartDate() != null && dto.getEndDate() != null){
                predicate = criteriaBuilder.and(
                    // criteriaBuilder.greaterThanOrEqualTo(dto.getStartDate())
                );
            }
            predicate = criteriaBuilder.and(
                criteriaBuilder.equal(root.get("ecProductsStatus"), dto.getPStatus().getType())
            );
            
            return predicate;
        };
    }
}
