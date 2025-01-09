package com.simple.ecommerce.util.products;

import java.util.HashMap;
import java.util.Map;

public enum StatusEnum {
    ACTIVE(0, "ACTIVE"), INACTIVE(1, "INACTIVE");

    private final Integer no;

    private final String type;

    private static final Map<Integer, StatusEnum> STATUS_MAP = new HashMap<>();

    static {
        for(StatusEnum value : values()){
            STATUS_MAP.put(value.getNo(), value);
        }
    }

    StatusEnum(Integer no, String type){
        this.no = no;
        this.type = type;
        
    }


    public String getType(){
        return this.type;
    }

    public Integer getNo(){
        return this.no;
    }

    public static StatusEnum getEnum(Integer no){
        return STATUS_MAP.getOrDefault(no, null);
    }
}
