package com.simple.ecommerce.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j @Getter @Setter
public class PagingUtil {

    private Integer page;
    private Integer size;
    private String sort;
    private String sortWay;
    
    public static Pageable getPaging(Integer page, Integer size, String sort, String sortWay){
        log.info("\npage:[{}]\nsize: [{}]\nsort: [{}]\nsortWay: [{}]", page, size, sort, sortWay);
        Pageable pageable = null;
        if(StringUtils.isStringEmpty(sort)){
            pageable = PageRequest.of(page, size);
        }else{
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortWay), sort));
        }
        return pageable;
    }

    public Pageable getPaging(){
        log.info("\npage:[{}]\nsize: [{}]\nsort: [{}]\nsortWay: [{}]", this.page, this.size, this.sort, this.sortWay);
        Pageable pageable = null;
        if(StringUtils.isStringEmpty(this.sort)){
            pageable = PageRequest.of(this.page, this.size);
        }else{
            pageable = PageRequest.of(this.page, this.size, Sort.by(Sort.Direction.fromString(this.sortWay), this.sort));
        }
        return pageable;
    }

    public void PagingInfo(Integer page, Integer size,
                        String sort, String sortWay){

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
        }
        this.sort = sort;
    }
}
