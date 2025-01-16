package com.simple.ecommerce.repository.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.simple.ecommerce.entity.products.ProductsEntity;


@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Integer>
                                            , JpaSpecificationExecutor<ProductsEntity>{

    ProductsEntity findByEcProductsId(Integer ecProductsId);

    Page<ProductsEntity> findAll(Pageable pageable);
}
