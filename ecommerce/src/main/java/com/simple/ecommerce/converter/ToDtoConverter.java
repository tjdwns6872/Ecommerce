package com.simple.ecommerce.converter;

public interface ToDtoConverter<D, E> {
    
    D toEntity(E entity);
}
