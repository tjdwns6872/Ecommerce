package com.simple.ecommerce.entity.products;

import java.sql.Date;

import com.simple.ecommerce.util.products.StatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="EC_PRODUCTS")
public class ProductsEntity {
    
    @Id
    @Column(name = "EC_PRODUCTS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ecProductsId;

    @Column(name = "EC_PRODUCTS_CATEGORY_ID")
    private Integer ecProductsCategoryId;

    @Column(name = "EC_PRODUCTS_SELLER_ID")
    private Integer ecProductsSellerId;

    @Column(name = "EC_PRODUCTS_NAME")
    private String ecProductsName;

    @Column(name = "EC_PRODUCTS_DESCRIPTION")
    private String ecProductsDescription;

    @Column(name = "EC_PRODUCTS_PRICE")
    private String ecProductsPrice;

    @Column(name = "EC_PRODUCTS_STOCK_QUANTITY")
    private Integer ecProductsStockQuantity;

    @Column(name = "EC_PRODUCTS_STATUS")
    private StatusEnum ecProductsStatus;

    @Column(name = "EC_PRODUCTS_CREATED_AT")
    private Date ecProductsCreatedAt;

    @Column(name = "EC_PRODUCTS_UPDATED_AT")
    private Date ecProductsUpdatedAt;
}
