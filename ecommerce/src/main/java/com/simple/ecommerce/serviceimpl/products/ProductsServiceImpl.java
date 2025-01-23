package com.simple.ecommerce.serviceimpl.products;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simple.ecommerce.converter.products.ProductsConverter;
import com.simple.ecommerce.dto.products.InsertDto;
import com.simple.ecommerce.dto.products.ProductSelectResponse;
import com.simple.ecommerce.dto.products.SelectDto;
import com.simple.ecommerce.entity.products.ProductsEntity;
import com.simple.ecommerce.exception.products.ProductsException;
import com.simple.ecommerce.repository.products.ProductsRepository;
import com.simple.ecommerce.repository.products.ProductsSpecification;
import com.simple.ecommerce.service.category.CategoryService;
import com.simple.ecommerce.service.products.ProductsService;
import com.simple.ecommerce.util.products.StatusEnum;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductsServiceImpl implements ProductsService{

    private final ProductsRepository productsRepository;

    private final CategoryService categoryService;

    public ProductsServiceImpl(ProductsRepository productsRepository
                            , CategoryService categoryService) {
        this.productsRepository = productsRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Integer dataInsert(InsertDto dto) {
        // 데이터 삽입 로직        
        Integer productId = 0;
        try {
            ProductsEntity entity = new ProductsConverter().toEntity(dto);
            log.info("ProductsEntity=>{}", entity.toString());
            productId = productsRepository.save(entity).getEcProductsId();
        } catch (Exception e) {
            throw new ProductsException("저장 실패");
        }
        return productId;
    } 

    public Integer dataDelete(Integer data) {
        try {
            ProductsEntity entity = productsRepository.findByEcProductsId(data);
            entity.setEcProductsStatus(StatusEnum.INACTIVE.getType());
            data = productsRepository.save(entity).getEcProductsId();
        } catch (Exception e) {
            throw new ProductsException("삭제 실패");
        }
        return data;
    }

    @Override
    @Transactional
    public Integer dataUpdate(InsertDto dto) {
        ProductsEntity entity;
        try {
            entity = productsRepository.findByEcProductsId(dto.getProductsId());
            entity.setEcProductsName(dto.getName());
        } catch (Exception e) {
            throw new ProductsException("업데이트 실패");
        }
        return entity.getEcProductsId();
    }

    @Override
    public ProductsEntity dataDetailSelect(Integer id) {
        ProductsEntity entity;
        try {
            entity = productsRepository.findByEcProductsId(id);
            log.info("Product Detail Entity Null Check====>{}", entity.toString());
        } catch (Exception e) {
            throw new ProductsException("조회 실패");
        }
        return entity;
    }

    @Override
    public ProductSelectResponse dataListSelect(SelectDto dto) {
        ProductSelectResponse response = new ProductSelectResponse();
        Specification<ProductsEntity> spec = ProductsSpecification.productList(dto.getRequestDto());
        try {
            response.setProductList(productsRepository.findAll(spec, dto.getPageable()).getContent());
            response.setCategoryList(categoryService.dataListSelect(null).getCategoryList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    
}
