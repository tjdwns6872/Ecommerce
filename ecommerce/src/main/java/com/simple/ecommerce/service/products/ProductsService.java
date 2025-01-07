package com.simple.ecommerce.service.products;

import com.simple.ecommerce.dto.products.InsertDto;
import com.simple.ecommerce.interfaces.DataBase.DataDelete;
import com.simple.ecommerce.interfaces.DataBase.DataInsert;

public interface ProductsService extends DataInsert<InsertDto>, DataDelete<Integer>{

}
