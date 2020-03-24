package com.zh.myfilter.exception;

import lombok.Data;

@Data
public class My2Exception extends RuntimeException{

    private EnumException enumException;

    public My2Exception(){}
    public My2Exception(EnumException en){
        this.enumException = en;
    }
}
