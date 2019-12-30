package com.zh.myfilter.controller;

import com.zh.myfilter.service.UserService;
import com.zh.myfilter.utils.JsonUtil;
import com.zh.myfilter.vo.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String Login(){
        return "login";
    }

    @ResponseBody
    @RequestMapping("/login.do")
    public JsonUtil login(@RequestBody UserDto dto){
        return userService.findOne(dto.getUsername(), dto.getPassword());
    }


}
