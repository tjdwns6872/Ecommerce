package com.simple.ecommerce.util.products;

public enum StatusEnum {
    ACTIVE(0, "ACTIVE"), INACTIVE(1, "INACTIVE");

    private final Integer no;

    private final String type;

    StatusEnum(Integer no, String type){
        this.no = no;
        this.type = type;
    }


    public String getType(){
        return this.type;
    }
}
