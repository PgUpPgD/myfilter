package com.zh.myfilter.filter;

import com.alibaba.fastjson.JSONObject;

public class PackageReturnResult {
    public static JSONObject returnJson(Integer code, String msg){
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        return json;
    }
}