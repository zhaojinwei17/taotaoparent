package com.taotao.sso.controller;

import com.taotao.sso.service.LoginService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Resource
    LoginService loginService;
    @Value("${TOKEN_KEY}")
    String TOKEN_KEY;

    @RequestMapping("/page/login")
    public String showLogin(String redirect, Model model){
        model.addAttribute("redirect",redirect);
        return "login";
    }
    @RequestMapping("/user/login")
    @ResponseBody
    public TaotaoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response){
        TaotaoResult result = loginService.login(username, password);
        if (result.getStatus()==200){
            String token= (String) result.getData();
            CookieUtils.setCookie(request,response,TOKEN_KEY,token);
        }
        return result;
    }
}
