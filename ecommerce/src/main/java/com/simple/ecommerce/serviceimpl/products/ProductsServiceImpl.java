package com.simple.ecommerce.serviceimpl.products;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simple.ecommerce.converter.products.ProductsConverter;
import com.simple.ecommerce.dto.category.CategorySelectRequestDto;
import com.simple.ecommerce.dto.products.ProductInsertDto;
import com.simple.ecommerce.dto.products.ProductSelectResponse;
import com.simple.ecommerce.dto.products.SelectDto;
import com.simple.ecommerce.entity.products.ProductsEntity;
import com.simple.ecommerce.exception.products.ProductsException;
import com.simple.ecommerce.repository.products.ProductsRepository;
import com.simple.ecommerce.repository.products.ProductsSpecification;
import com.simple.ecommerce.service.category.CategoryService;
import com.simple.ecommerce.service.jwt.JwtService;
import com.simple.ecommerce.service.products.ProductsService;
import com.simple.ecommerce.util.products.StatusEnum;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductsServiceImpl implements ProductsService{

    private final ProductsRepository productsRepository;

    private final CategoryService categoryService;

    private final JwtService jwtService;

    public ProductsServiceImpl(ProductsRepository productsRepository
                            , CategoryService categoryService
                            , JwtService jwtService) {
        this.productsRepository = productsRepository;
        this.categoryService = categoryService;
        this.jwtService = jwtService;
    }

    @Override
    public Integer dataInsert(ProductInsertDto dto) {
        // 데이터 삽입 로직        
        Integer productId = 0;
        try {
            dto.setUserId(jwtService.tokenToUserId(dto.getUserToken()));
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
    public Integer dataUpdate(ProductInsertDto dto) {
        ProductsEntity entity;
        try {
            dto.setUserId(jwtService.tokenToUserId(dto.getUserToken()));
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
            // 상품 리뷰 쿼리 추가 예정
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
            if(dto.getRequestDto().getCStatus().equals(StatusEnum.ACTIVE)){
                response.setCategoryList(categoryService.dataListSelect(new CategorySelectRequestDto()).getCategoryList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    
}
