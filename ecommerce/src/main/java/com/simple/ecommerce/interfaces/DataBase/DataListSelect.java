package com.simple.ecommerce.interfaces.DataBase;

import java.util.List;

public interface DataListSelect<T1, T2> {
    
    public List<T1> dataListSelect(T2 dto);
}
