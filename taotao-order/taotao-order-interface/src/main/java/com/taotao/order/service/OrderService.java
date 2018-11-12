package com.taotao.order.service;

import com.taotao.order.pojo.OrderInfo;
import com.taotao.utils.TaotaoResult;

public interface OrderService {
    TaotaoResult cearteOrder(OrderInfo orderInfo);
}
