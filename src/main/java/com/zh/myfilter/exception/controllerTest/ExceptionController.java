package com.zh.myfilter.exception.controllerTest;


import com.zh.myfilter.common.CodeMsg;
import com.zh.myfilter.exception.EnumException;
import com.zh.myfilter.exception.My2Exception;
import com.zh.myfilter.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @Autowired
    private CodeMsg codeMsg;

    @RequestMapping("/exception.do")
    public void exception(){
        throw new MyException(400, codeMsg.getOperationFailure());
    }

    @RequestMapping("/exception2.do")
    public void exception1(){
        throw new My2Exception(EnumException.ERROR);
    }

    @RequestMapping("/exception3.do")
    public void exception3(){
        int a = 3 / 0;
    }
}
