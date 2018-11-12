package com.taotao.order.serviceimpl;

import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.pojo.OrderInfo;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;
import com.taotao.utils.TaotaoResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    TbOrderMapper tbOrderMapper;
    @Resource
    TbOrderShippingMapper tbOrderShippingMapper;
    @Resource
    TbOrderItemMapper tbOrderItemMapper;
    @Resource
    JedisClient jedisClient;

    @Override
    public TaotaoResult cearteOrder(OrderInfo orderInfo) {
        if (!jedisClient.exists("ORDER_ID_GEN")){
            jedisClient.set("ORDER_ID_GEN",100544+"");
        }
        String orderId = jedisClient.incr("ORDER_ID_GEN").toString();
        orderInfo.setOrderId(orderId);
        //订单状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        orderInfo.setStatus(1);
        orderInfo.setCreateTime(new Date());
        orderInfo.setUpdateTime(new Date());
        tbOrderMapper.insert(orderInfo);
        List<TbOrderItem> orderItems = orderInfo.getOrderItems();
        for (TbOrderItem item : orderItems) {
            if (!jedisClient.exists("ORDER_DETAIL_ID_GEN")){
                jedisClient.set("ORDER_DETAIL_ID_GEN",1314920+"");
            }
            String orderDetailId = jedisClient.incr("ORDER_DETAIL_ID_GEN").toString();
            item.setId(orderDetailId);
            item.setOrderId(orderId);
            tbOrderItemMapper.insert(item);
        }
        TbOrderShipping orderShipping = orderInfo.getOrderShipping();
        orderShipping.setOrderId(orderId);
        orderShipping.setCreated(new Date());
        orderShipping.setUpdated(new Date());
        tbOrderShippingMapper.insert(orderShipping);
        return TaotaoResult.ok(orderId);
    }
}
