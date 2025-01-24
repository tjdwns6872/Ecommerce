package com.simple.ecommerce.serviceimpl.category;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.simple.ecommerce.dto.category.CategorySelectRequestDto;
import com.simple.ecommerce.dto.category.CategorySelectResponse;
import com.simple.ecommerce.entity.category.CategoryEntity;
import com.simple.ecommerce.exception.category.CategoryException;
import com.simple.ecommerce.repository.category.CategoryRepository;
import com.simple.ecommerce.repository.category.CategorySpecification;
import com.simple.ecommerce.service.category.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryEntity dataDetailSelect(Integer id) {
        CategoryEntity entity;
        try {
            entity = categoryRepository.findByEcCategoryId(id);
        } catch (Exception e) {
            throw new CategoryException("조회 실패");
        }
        return entity;
    }

    @Override
    public CategorySelectResponse dataListSelect(CategorySelectRequestDto dto) {
        CategorySelectResponse response = new CategorySelectResponse();
        Specification<CategoryEntity> spec = CategorySpecification.list(dto);
        try {
            response.setCategoryList(categoryRepository.findAll(spec));
        } catch (Exception e) {
            throw new CategoryException("조회 실패");
        }
        return response;
    }
    
}

