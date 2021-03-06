package com.zh.myfilter.exception;

public enum EnumException {

    SUCCESS(200,"操作成功"),
    ERROR(400,"操作失败");

    private Integer code;
    private String msg;

    EnumException(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
