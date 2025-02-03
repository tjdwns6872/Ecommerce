package com.simple.ecommerce.service.products;

import com.simple.ecommerce.dto.products.ProductInsertDto;
import com.simple.ecommerce.dto.products.ProductSelectResponse;
import com.simple.ecommerce.dto.products.SelectDto;
import com.simple.ecommerce.entity.products.ProductsEntity;
import com.simple.ecommerce.interfaces.DataBase.DataDelete;
import com.simple.ecommerce.interfaces.DataBase.DataDetailSelect;
import com.simple.ecommerce.interfaces.DataBase.DataInsert;
import com.simple.ecommerce.interfaces.DataBase.DataListSelect;
import com.simple.ecommerce.interfaces.DataBase.DataUpdate;

public interface ProductsService extends 
            DataInsert<ProductInsertDto>, DataDelete<Integer>, DataUpdate<ProductInsertDto>
            ,DataDetailSelect<ProductsEntity>, DataListSelect<ProductSelectResponse, SelectDto>{

}
