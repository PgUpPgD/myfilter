package com.zh.myfilter.utils;

public class PeopleCountUtil {

    private static int count = 0;

    public static void countAdd(){
        count ++;
    }
    public static void countSubtract(){
        count --;
    }
    public static int getCount(){
        return count;
    }
}
