package com.simple.ecommerce.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PagingUtil {
    
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
}
