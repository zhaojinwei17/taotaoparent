package com.taotao.controller;

import com.taotao.pojo.TbItem;
import com.taotao.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/test")
public class TestController {

    @Resource
    TestService testService;

    @RequestMapping("/testitem.do")
    @ResponseBody
    public TbItem getItem(){
        TbItem item = testService.getItem();
        return item;
    }
}
