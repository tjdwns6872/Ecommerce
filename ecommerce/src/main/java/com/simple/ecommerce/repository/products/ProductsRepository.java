package com.simple.ecommerce.repository.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simple.ecommerce.entity.products.ProductsEntity;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Integer>{

    <S extends ProductsEntity> S save(S entity);

    ProductsEntity findByEcProductsId(Integer ecProductsId);
}
