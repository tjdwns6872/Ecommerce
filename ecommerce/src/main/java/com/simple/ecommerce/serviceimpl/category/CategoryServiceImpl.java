package com.simple.ecommerce.serviceimpl.category;

import org.springframework.stereotype.Service;

import com.simple.ecommerce.entity.category.CategoryEntity;
import com.simple.ecommerce.exception.category.CategoryException;
import com.simple.ecommerce.repository.category.CategoryRepository;
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
    
}
