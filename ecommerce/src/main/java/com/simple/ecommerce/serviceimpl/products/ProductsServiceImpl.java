package com.simple.ecommerce.serviceimpl.products;

import org.springframework.stereotype.Service;

import com.simple.ecommerce.converter.products.ProductsConverter;
import com.simple.ecommerce.dto.products.InsertDto;
import com.simple.ecommerce.entity.products.ProductsEntity;
import com.simple.ecommerce.exception.products.ProductsException;
import com.simple.ecommerce.repository.products.ProductsRepository;
import com.simple.ecommerce.service.products.ProductsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductsServiceImpl implements ProductsService{

    private final ProductsRepository productsRepository;

    public ProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
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
        // 데이터 삭제 로직
        throw new UnsupportedOperationException("Unimplemented method 'dataDelete'");
    }

    @Override
    public Integer dataUpdate(InsertDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dataUpdate'");
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
    
}
