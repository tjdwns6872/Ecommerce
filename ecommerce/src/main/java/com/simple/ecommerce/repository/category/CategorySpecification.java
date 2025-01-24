package com.simple.ecommerce.repository.category;

import org.springframework.data.jpa.domain.Specification;

import com.simple.ecommerce.dto.category.CategorySelectRequestDto;
import com.simple.ecommerce.entity.category.CategoryEntity;
import com.simple.ecommerce.util.StringUtils;

import jakarta.persistence.criteria.Predicate;

public class CategorySpecification {

    public static Specification<CategoryEntity> list(CategorySelectRequestDto dto){
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (!StringUtils.isStringEmpty(dto.getName())) {
                predicate = criteriaBuilder.and(
                    criteriaBuilder.like(root.get("ecCategoryName"), "%"+dto.getName()+"%"));
            }
            if (!StringUtils.isStringEmpty(dto.getDescription())) {
                predicate = criteriaBuilder.and(
                    criteriaBuilder.like(root.get("ecCategoryDescription"), "%"+dto.getDescription()+"%"));
            }
            if(dto.getParentId() != null && dto.getParentId() > 0){
                predicate = criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("ecCategoryParentId"), dto.getParentId())
                );
            }
            return predicate;
        };
    }
}
