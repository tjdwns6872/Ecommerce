package com.simple.ecommerce.controller.reviews;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple.ecommerce.dto.reviews.ReviewsInsertDto;
import com.simple.ecommerce.service.reviews.ReviewsService;
import com.simple.ecommerce.util.AjaxResult;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/ecommerce/api/review")
public class ReviewsRestController {

    private final ReviewsService reviewsService;

    public ReviewsRestController(ReviewsService reviewsService){
        this.reviewsService = reviewsService;
    }
    
    @PostMapping("/insert")
    public ResponseEntity<AjaxResult<Integer>> reviewInsert (@RequestBody ReviewsInsertDto insetDto) {
        Integer result = reviewsService.dataInsert(insetDto);
        AjaxResult<Integer> responese = AjaxResult.<Integer>builder()
            .status(HttpStatus.OK.value())
            .message("리뷰 작성")
            .data(result)
            .build();
        return ResponseEntity.status(responese.getStatus()).body(responese);
    }
    
}
