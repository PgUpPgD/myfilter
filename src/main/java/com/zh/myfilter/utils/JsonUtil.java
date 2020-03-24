package com.zh.myfilter.utils;

import lombok.Data;

@Data
public class JsonUtil {
    private int code;  //0成功  1失败
    private String msg;
    private Object data;

    public static JsonUtil setOk(){
        JsonUtil u = new JsonUtil();
        u.setCode(0);
        u.setMsg("OK");
        u.setData(null);
        return u;
    }
    public static JsonUtil setOk(Object data){
        JsonUtil u = new JsonUtil();
        u.setCode(0);
        u.setMsg("OK");
        u.setData(data);
        return u;
    }
    public static JsonUtil setERROR(){
        JsonUtil u = new JsonUtil();
        u.setCode(1);
        u.setMsg("ERROR");
        u.setData(null);
        return u;
    }
    public static JsonUtil setERROR(String msg){
        JsonUtil u = new JsonUtil();
        u.setCode(1);
        u.setMsg(msg);
        u.setData(null);
        return u;
    }
    public static JsonUtil setERROR(int code, String msg){
        JsonUtil u = new JsonUtil();
        u.setCode(code);
        u.setMsg(msg);
        u.setData(null);
        return u;
    }
    public static JsonUtil setERROR(Boolean succ){
        if (succ){
            return setOk();
        }else {
            return setERROR();
        }
    }





}
