package com.zh.myfilter.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UploadEntity implements Serializable {
    private Integer uid;
    private String name;
    private Date date;
    private String url;
}
