package com.zh.myfilter.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@PropertySource(value = {"classpath:codemsg/codemsg.properties"},
        ignoreResourceNotFound = false, encoding = "UTF-8", name = "codemsg.properties")
public class CodeMsg {
    @Value("${code.1001}")
    private String listIsEmpty;
    @Value("${code.1002}")
    private String userIsEmpty;
    @Value("${code.1003}")
    private String namePassOk;
    @Value("${code.1004}")
    private String passError;
}
