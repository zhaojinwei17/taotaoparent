package com.taotao.cart.service;

import com.taotao.pojo.TbItem;
import com.taotao.utils.TaotaoResult;

import java.util.List;

public interface CartService {
    TaotaoResult addCart(Long userId,Long itemId,Integer num);
    TaotaoResult mergeCart(Long userId, List<TbItem> items);
    List<TbItem> getCartList(Long userId);
    TaotaoResult deleteCartItem(Long userId,Long itemId);
    TaotaoResult updateCartNum(Long userId,Long itemId,Integer num);
    TaotaoResult clearCartItem(Long userId);
}
