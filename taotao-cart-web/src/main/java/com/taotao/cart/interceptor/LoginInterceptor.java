package com.taotao.cart.interceptor;

import com.taotao.pojo.TbUser;
import com.taotao.sso.service.TokenService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.TaotaoResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        if (StringUtils.isBlank(token)){
            return true;
        }
        TaotaoResult result = tokenService.getUserByToken(token);
        Integer status = result.getStatus();
        if (status!=200){
            return true;
        }
        TbUser user= (TbUser) result.getData();
        request.setAttribute("user",user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
