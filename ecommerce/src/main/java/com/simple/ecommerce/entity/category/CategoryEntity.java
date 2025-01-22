package com.simple.ecommerce.entity.category;

import java.sql.Date;

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

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "EC_CATEGORY")
public class CategoryEntity {
    
    @Id
    @Column(name = "EC_CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ecCategoryId;

    @Column(name = "EC_CATEGORY_PARENT_ID")
    private Integer ecCategoryParentId;

    @Column(name = "EC_CATEGORY_NAME")
    private String ecCategoryName;

    @Column(name = "EC_CATEGORY_DESCRIPTION")
    private String ecCategoryDescription;

    @Column(name = "EC_CATEGORY_CREATED_AT")
    private Date ecCategoryCreatedAt;

    @Column(name = "EC_CATEGORY_UPDATED_AT")
    private Date ecCategoryUpdatedAt;
}
