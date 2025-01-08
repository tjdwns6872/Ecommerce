package com.simple.ecommerce.converter;

public interface ToEntityConverter<E, D> {

    E toEntity(D dto);
    
}