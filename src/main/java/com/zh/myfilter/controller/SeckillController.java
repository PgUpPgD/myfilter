package com.zh.myfilter.controller;

import com.zh.myfilter.service.SeckillService;
import com.zh.myfilter.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@CrossOrigin
@Controller
@ResponseBody
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @RequestMapping("skill/surplusTime")
    public JsonUtil surplusTime(Integer id){
        Map<String, Object> map = seckillService.findById(id);
        return JsonUtil.setOk(map);
    }

    @RequestMapping("skill/bySeckillGoods")
    public JsonUtil bySeckillGoods(Integer uid, Integer goodsId){
        return seckillService.bySeckillGoods(uid, goodsId);
    }

}
