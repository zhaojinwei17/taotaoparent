package com.taotao.cart.controller;

import com.taotao.cart.service.CartService;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbUser;
import com.taotao.service.ItemService;
import com.taotao.utils.CookieUtils;
import com.taotao.utils.JsonUtils;
import com.taotao.utils.TaotaoResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @Resource
    ItemService itemService;
    @Value("${COOKIE_CART_EXPIRE}")
    Integer COOKIE_CART_EXPIRE;
    @Resource
    CartService cartService;

    @RequestMapping("/cart/cart")
    public String showCartList(HttpServletRequest request,HttpServletResponse response){
        TbUser user = (TbUser) request.getAttribute("user");
        List<TbItem> cartList=getCartListFromCookie(request);
        if (user!=null){
            if (cartList.size()>0){
                cartService.mergeCart(user.getId(),cartList);
                CookieUtils.deleteCookie(request,response,"cart");
            }
            cartList = cartService.getCartList(user.getId());
        }
        request.setAttribute("cartList",cartList);
        return "cart";
    }

    @RequestMapping("/cart/add/{itemId}")
    public String addCart(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num, HttpServletRequest request, HttpServletResponse response){
        TbUser user = (TbUser) request.getAttribute("user");
        if (user!=null){
            TaotaoResult result = cartService.addCart(user.getId(), itemId, num);
            return "cartSuccess";
        }
        List<TbItem> cartList = getCartListFromCookie(request);
        boolean flag=true;
        for (TbItem item:cartList) {
            if (item.getId().equals(itemId)){
                flag=false;
                item.setNum(item.getNum()+num);
                break;
            }
        }
        if (flag){
            TbItem item=itemService.getItemById(itemId);
            item.setNum(num);
            String image = item.getImage();
            if (StringUtils.isNotBlank(image) && image.contains(",")){
                item.setImage(image.split(",")[0]);
            }
            cartList.add(item);
        }
        CookieUtils.setCookie(request,response,"cart",JsonUtils.objectToJson(cartList),COOKIE_CART_EXPIRE,true);
        return "cartSuccess";
    }

    @RequestMapping("/cart/update/num/{itemId}/{num}")
    @ResponseBody
    public TaotaoResult updateCartNum(@PathVariable Long itemId,@PathVariable Integer num,HttpServletRequest request, HttpServletResponse response){
        TbUser user = (TbUser) request.getAttribute("user");
        if (user!=null){
            cartService.updateCartNum(user.getId(),itemId,num);
        }else {
            List<TbItem> cartList = getCartListFromCookie(request);
            for (TbItem item : cartList) {
                if (item.getId().equals(itemId)){
                    item.setNum(num);
                    break;
                }
            }
            CookieUtils.setCookie(request,response,"cart",JsonUtils.objectToJson(cartList),COOKIE_CART_EXPIRE,true);
        }
        return TaotaoResult.ok();
    }
    @RequestMapping("/cart/delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId,HttpServletRequest request,HttpServletResponse response){
        TbUser user = (TbUser) request.getAttribute("user");
        if (user!=null){
            cartService.deleteCartItem(user.getId(),itemId);
        }else {
            List<TbItem> cartList = getCartListFromCookie(request);
            for (int i = 0; i < cartList.size(); i++) {
                if (cartList.get(i).getId().equals(itemId)){
                    cartList.remove(i);
                    break;
                }
            }
            CookieUtils.setCookie(request,response,"cart",JsonUtils.objectToJson(cartList),COOKIE_CART_EXPIRE,true);
        }
        return "redirect:/cart/cart.html";
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
