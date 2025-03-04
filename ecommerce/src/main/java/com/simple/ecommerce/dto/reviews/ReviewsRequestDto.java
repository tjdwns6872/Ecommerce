package com.simple.ecommerce.dto.reviews;

import org.springframework.data.domain.Pageable;

import com.simple.ecommerce.util.PagingUtil;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewsRequestDto {

    private Integer productId;

    private Integer page;
    private Integer size;
    private String sort;
    private String sortWay;

    private Pageable pageable;


    public ReviewsRequestDto(Integer page, Integer size,
                            String sort, String sortWay, Integer productId){

        PagingUtil util = new PagingUtil();
        util.PagingInfo(page, size, sort, sortWay);

        this.productId = productId;

        this.page = util.getPage();
        this.size = util.getSize();
        this.sort = util.getSort();
        this.sortWay = util.getSortWay();

        this.pageable = util.getPaging();
    }
}
