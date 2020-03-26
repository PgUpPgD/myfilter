package com.zh.myfilter.controller;

import com.github.pagehelper.Page;
import com.zh.myfilter.common.CodeMsg;
import com.zh.myfilter.entity.BankEntity;
import com.zh.myfilter.entity.UserEntity;
import com.zh.myfilter.service.UserService;
import com.zh.myfilter.utils.JsonUtil;
import com.zh.myfilter.utils.PeopleCountUtil;
import com.zh.myfilter.utils.RandomSalt;
import com.zh.myfilter.utils.UUIDUtils;
import com.zh.myfilter.vo.UserDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CodeMsg codeMsg;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ResponseBody
    @RequestMapping("/login.do")
    public JsonUtil login(UserDto dto){
        //存储输入的用户名和密码
        UsernamePasswordToken token = new UsernamePasswordToken(dto.getUsername(), dto.getPassword(), true);
        //主体对象
        Subject subject = SecurityUtils.getSubject();
        //登录判断
        try {
            subject.login(token);
        }catch (AuthenticationException e){
            e.printStackTrace();
            return JsonUtil.setERROR(codeMsg.getPassError());
        }
        //shiro有remember功能，可不用token验证登录
        String uuid = UUIDUtils.getUUID();
        String tok = uuid + dto.getUsername();
        stringRedisTemplate.opsForValue().set(tok, dto.getUsername(),60, TimeUnit.SECONDS);
        return JsonUtil.setOk(tok);
    }

    @ResponseBody
    @RequiresPermissions("min")  //设置需要的权限
    @RequestMapping("/findAll.do")
    public Map<String, Object> findAll(Integer page, Integer limit){
        List<UserEntity> list = userService.findAll(page, limit);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("count", ((Page) list).getTotal());
        map.put("data", list);
        return map;
    }

    @ResponseBody
    @RequiresPermissions("min")
    @RequestMapping("/peopleCount.do")
    public JsonUtil peopleCount(){
        int count = PeopleCountUtil.getCount();
        return JsonUtil.setOk(count);
    }

    @ResponseBody
    @RequestMapping("/signIn.do")
    public JsonUtil signIn(String name, String pass){
        String salt = RandomSalt.getRandomSalt();
        SimpleHash simpleHash = new SimpleHash("md5", pass, salt, 1);
        String s = simpleHash.toHex();
        int i = userService.userSignIn(name, s);
        if (i > 0){
            return JsonUtil.setOk(codeMsg.getSignInOk());
        }
        return JsonUtil.setERROR(codeMsg.getOperationFailure());
    }

    @ResponseBody
    @RequiresPermissions("min")
    @RequestMapping("/updateUser.do")
    public JsonUtil updateUser(UserEntity user){
        String salt = RandomSalt.getRandomSalt();
        SimpleHash simpleHash = new SimpleHash("md5", user.getPass(), salt, 1);
        String s = simpleHash.toHex();
        user.setPass(s);
        user.setSalt(salt);
        int i = userService.updateUser(user);
        if (i > 0){
            return JsonUtil.setOk(codeMsg.getUpdateOk());
        }
        return JsonUtil.setERROR(codeMsg.getOperationFailure());
    }

    @ResponseBody
//    @RequiresPermissions("min")
    @RequestMapping("/transfer.do")
    public JsonUtil transfer(BankEntity bank){
        String name = bank.getName();
        Integer uid = bank.getUid();
        Double subtract = bank.getSubtract();
        Integer tid = bank.getTid();
        return userService.transfer(name, uid, subtract, tid);
    }


}
