package com.simple.ecommerce.converter;

public interface Converter<E, D> {

    E toEntity(D dto);
    
    D toDto(E entity);
}
