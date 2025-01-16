package com.simple.ecommerce.dto.products;

import org.springframework.data.domain.Pageable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SelectDto {
    
    private SelectRequestDto requestDto;

    private Pageable pageable;
}