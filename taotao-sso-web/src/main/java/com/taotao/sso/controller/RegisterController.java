package com.taotao.sso.controller;

import com.taotao.pojo.TbUser;
import com.taotao.sso.service.RegisterService;
import com.taotao.utils.TaotaoResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class RegisterController {

    @Resource
    RegisterService registerService;

    @RequestMapping("/page/register")
    public String showRegitster(){
        return "register";
    }

    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public TaotaoResult checkData(@PathVariable String param,@PathVariable int type) {
        return registerService.checkData(param,type);
    }

    @RequestMapping("/user/register")
    @ResponseBody
    public TaotaoResult register(TbUser user) {
        return registerService.register(user);
    }
}
