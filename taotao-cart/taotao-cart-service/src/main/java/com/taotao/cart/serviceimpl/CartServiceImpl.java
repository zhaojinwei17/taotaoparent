package com.taotao.cart.serviceimpl;

import com.taotao.cart.service.CartService;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.utils.JsonUtils;
import com.taotao.utils.TaotaoResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Resource
    JedisClient jedisClient;
    @Resource
    TbItemMapper tbItemMapper;

    @Override
    public TaotaoResult addCart(Long userId, Long itemId,Integer num) {
        String json = jedisClient.hget("CART:" + userId, itemId + "");
        TbItem item=null;
        if (StringUtils.isNotBlank(json)){
            item = JsonUtils.jsonToPojo(json, TbItem.class);
            item.setNum(item.getNum()+num);
        }else {
            item = tbItemMapper.selectByPrimaryKey(itemId);
            item.setNum(num);
            String image = item.getImage();
            if (StringUtils.isNotBlank(image)){
                item.setImage(image.split(",")[0]);
            }
        }
        jedisClient.hset("CART:"+userId,itemId+"", JsonUtils.objectToJson(item));
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult mergeCart(Long userId, List<TbItem> items) {
        for (TbItem item:items) {
            addCart(userId,item.getId(),item.getNum());
        }
        return TaotaoResult.ok();
    }

    @Override
    public List<TbItem> getCartList(Long userId) {
        List<String> jsonList = jedisClient.hvals("CART:" + userId);
        List<TbItem> items=new ArrayList<>();
        for (String json:jsonList) {
            TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
            items.add(item);
        }
        return items;
    }

    @Override
    public TaotaoResult deleteCartItem(Long userId, Long itemId) {
        jedisClient.hdel("CART:" + userId,itemId+"");
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult updateCartNum(Long userId, Long itemId, Integer num) {
        String json = jedisClient.hget("CART:" +userId, itemId+"");
        TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
        item.setNum(num);
        jedisClient.hset("CART:" +userId, itemId+"",JsonUtils.objectToJson(item));
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult clearCartItem(Long userId) {
        jedisClient.del("CART:" +userId);
        return TaotaoResult.ok();
    }
}
