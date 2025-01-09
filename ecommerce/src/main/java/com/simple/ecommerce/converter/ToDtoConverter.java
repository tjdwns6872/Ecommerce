package com.simple.ecommerce.converter;

public interface ToDtoConverter<T1, T2> {
    
    T1 toEntity(T2 entity);
}
