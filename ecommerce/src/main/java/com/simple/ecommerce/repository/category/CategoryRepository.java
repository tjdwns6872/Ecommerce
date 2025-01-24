package com.simple.ecommerce.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.simple.ecommerce.entity.category.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer>
                                            , JpaSpecificationExecutor<CategoryEntity>{
    
    CategoryEntity findByEcCategoryId(Integer ecCategoryId);
}
