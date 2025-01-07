package com.simple.ecommerce.serviceimpl.products;

import org.springframework.stereotype.Service;

import com.simple.ecommerce.dto.products.InsertDto;
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
        log.info("\n?");
        return 0;
    }

    public Integer dataDelete(Integer data) {
        // 데이터 삭제 로직
        throw new UnsupportedOperationException("Unimplemented method 'dataDelete'");
    }
    
}
