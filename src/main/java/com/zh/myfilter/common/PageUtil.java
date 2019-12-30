package com.zh.myfilter.common;


import java.util.List;

public class PageUtil {

    public static List<Object> paging(List<Object> list, int page, int pageSize){
        if (page <= 0) {
            page = 1;
        }
        if (pageSize <= 0 || pageSize > 20) {
            pageSize = 10;
        }
        int start = (page - 1) * pageSize;
        int end = page * pageSize;
        List<Object> list1 = list.subList(Math.min(list.size(), start), Math.min(list.size(), end));
        return list1;
    }
}
