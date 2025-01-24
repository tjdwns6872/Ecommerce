package com.simple.ecommerce.service.category;

import com.simple.ecommerce.dto.category.CategorySelectRequestDto;
import com.simple.ecommerce.dto.category.CategorySelectResponse;
import com.simple.ecommerce.entity.category.CategoryEntity;
import com.simple.ecommerce.interfaces.DataBase.DataDetailSelect;
import com.simple.ecommerce.interfaces.DataBase.DataListSelect;

public interface CategoryService extends DataListSelect<CategorySelectResponse, CategorySelectRequestDto>, DataDetailSelect<CategoryEntity> {

}
