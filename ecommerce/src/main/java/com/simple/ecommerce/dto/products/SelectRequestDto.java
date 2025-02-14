package com.simple.ecommerce.dto.products;

import java.sql.Date;

import com.simple.ecommerce.util.StringUtils;
import com.simple.ecommerce.util.products.StatusEnum;

import lombok.Getter;

@Getter
public class SelectRequestDto {
    
    private String name;

    private Integer categoryId;

    private Integer userId;

    private Date startDate;
    private Date endDate;

    private StatusEnum cStatus;
    private Integer categoryStatus;

    private StatusEnum pStatus;
    private Integer productStatus;

    private Integer page;
    private Integer size;
    private String sort;
    private String sortWay;

    public SelectRequestDto(String name, Integer categoryId
                        , Integer userId, Date startDate
                        , Date endDate, Integer page
                        , Integer size, String sort, String sortWay
                        , Integer categoryStatus, Integer productStatus){
        this.name = name;
        this.categoryId = categoryId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;

        if(page == null || page.equals(0)){
            this.page = 0;
        }else{
            this.page = page-1;
        }
        if(size == null || size == 0){
            this.size = 10;
        }else{
            this.size = size;
        }
        if(StringUtils.isStringEmpty(sortWay)){
            this.sortWay = "ASC";
        }else{
            this.sortWay = sortWay;
        }if(categoryStatus == null){
            categoryStatus = 0;
        }
        this.cStatus = StatusEnum.getEnum(categoryStatus);
        if(productStatus == null){
            productStatus = 0;
        }
        this.pStatus = StatusEnum.getEnum(productStatus);
        this.sort = sort;
    }
}
