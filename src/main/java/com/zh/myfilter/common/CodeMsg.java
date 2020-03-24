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
    private String signInOk;
    @Value("${code.1004}")
    private String passError;
    @Value("${code.1005}")
    private String updateOk;
    @Value("${code.1006}")
    private String notConsistent;
    @Value("${code.1007}")
    private String accountsOk;
    @Value("${code.1008}")
    private String accountsError;

    @Value("${code.400}")
    private String operationFailure;

}
