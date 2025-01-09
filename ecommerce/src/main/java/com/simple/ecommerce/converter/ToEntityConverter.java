package com.simple.ecommerce.converter;

public interface ToEntityConverter<T1, T2> {

    T1 toEntity(T2 dto);
    
}