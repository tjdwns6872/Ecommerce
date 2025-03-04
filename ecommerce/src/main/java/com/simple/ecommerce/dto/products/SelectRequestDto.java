package com.simple.ecommerce.dto.products;

import java.sql.Date;

import org.springframework.data.domain.Pageable;

import com.simple.ecommerce.util.PagingUtil;
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

    private Pageable pageable;

    public SelectRequestDto(String name, Integer categoryId
                        , Integer userId, Date startDate
                        , Date endDate, Integer page
                        , Integer size, String sort, String sortWay
                        , Integer categoryStatus, Integer productStatus){
        
        PagingUtil util = new PagingUtil();
        util.PagingInfo(page, size, sort, sortWay);

        this.name = name;
        this.categoryId = categoryId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.page = util.getPage();
        this.size = util.getSize();
        this.sortWay = util.getSortWay();
        this.sort = util.getSort();
        
        if(categoryStatus == null){
            categoryStatus = 0;
        }
        this.cStatus = StatusEnum.getEnum(categoryStatus);
        if(productStatus == null){
            productStatus = 0;
        }
        this.pStatus = StatusEnum.getEnum(productStatus);

        this.pageable = util.getPaging();
    }
}
