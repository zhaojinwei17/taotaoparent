package com.taotao.order.controller;

import com.taotao.cart.service.CartService;
import com.taotao.order.pojo.OrderInfo;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbUser;
import com.taotao.utils.TaotaoResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class OrderController {
    @Resource
    CartService cartService;
    @Resource
    OrderService orderService;

    @RequestMapping("/order/order-cart")
    public String showOrderCart(HttpServletRequest request){
        TbUser user= (TbUser) request.getAttribute("user");
        List<TbItem> cartList = cartService.getCartList(user.getId());
        request.setAttribute("cartList",cartList);
        return "order-cart";
    }

    @RequestMapping(value = "/order/create",method = RequestMethod.POST)
    public String creatOrder(OrderInfo orderInfo,HttpServletRequest request){
        TbUser user= (TbUser) request.getAttribute("user");
        orderInfo.setUserId(user.getId());
        orderInfo.setBuyerNick(user.getUsername());
        TaotaoResult result = orderService.cearteOrder(orderInfo);
        if (result.getStatus()==200){
            cartService.clearCartItem(user.getId());
        }
        request.setAttribute("orderId",result.getData());
        request.setAttribute("payment",orderInfo.getPayment());

        return "success";
    }

}
