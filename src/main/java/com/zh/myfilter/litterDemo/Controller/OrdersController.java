package com.zh.myfilter.litterDemo.Controller;

import com.zh.myfilter.dao.LitterUserDao;
import com.zh.myfilter.litterDemo.entity.Order;
import com.zh.myfilter.litterDemo.entity.Rol;
import com.zh.myfilter.litterDemo.entity.User;
import com.zh.myfilter.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
public class OrdersController {

    @Autowired
    private LitterUserDao litterUserDao;

    @RequestMapping("order/findOne")
    public void findAll(){
        Order order = litterUserDao.findOne();
        System.out.println(order);
    }

    @RequestMapping("order/findsAll")
    public void findsAll(){
        //只能一对多，多对一报错
        User order = litterUserDao.findsAll();
        System.out.println(order);
    }

    @RequestMapping("order/findMany")
    public JsonUtil findMany() {
        List<Rol> order = litterUserDao.findMany();
        System.out.println(order);
        return JsonUtil.setOk(order);
    }


}
