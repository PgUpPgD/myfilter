package com.zh.myfilter.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Orders {

    private Integer orderId;
    private Goods goods;
    private Double totalPrice;
    private Date createTime;
    private Integer status;
    private UserEntity user;

}
