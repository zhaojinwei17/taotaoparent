package com.taotao.order.interceptor;

import com.taotao.cart.service.CartService;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.TokenService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.JsonUtils;
import com.taotao.utils.TaotaoResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    TokenService tokenService;
    @Resource
    CartService cartService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        if (StringUtils.isBlank(token)){
            try {
                response.sendRedirect("http://localhost:8088/page/login?redirect="+request.getRequestURL());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        TaotaoResult result = tokenService.getUserByToken(token);
        Integer status = result.getStatus();
        if (status!=200){
            try {
                response.sendRedirect("http://localhost:8088/page/login?redirect="+request.getRequestURL());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        TbUser user= (TbUser) result.getData();
        request.setAttribute("user",user);
        List<TbItem> cartList = getCartListFromCookie(request);
        if (cartList!=null && cartList.size()>0){
            cartService.mergeCart(user.getId(),cartList);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
    private List<TbItem> getCartListFromCookie(HttpServletRequest request){
        String json= CookieUtils.getCookieValue(request,"cart",true);
        List<TbItem> items=new ArrayList<>();
        if (StringUtils.isBlank(json)){
            return items;
        }
        items = JsonUtils.jsonToList(json, TbItem.class);
        return items;
    }
}
