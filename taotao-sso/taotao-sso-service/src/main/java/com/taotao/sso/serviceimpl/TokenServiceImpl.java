package com.taotao.sso.serviceimpl;

import com.taotao.jedis.JedisClient;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.TokenService;
import com.taotao.utils.JsonUtils;
import com.taotao.utils.TaotaoResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TokenServiceImpl implements TokenService {

    @Resource
    JedisClient jedisClient;
    @Value("${SESSION_EXPIRE}")
    Integer SESSION_EXPIRE;

    @Override
    public TaotaoResult getUserByToken(String token) {
        String json = jedisClient.get("SESSION:"+token);
        if (StringUtils.isBlank(json)){
            return TaotaoResult.build(201,"用户登录过期！");
        }
        TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
        jedisClient.expire("SESSION:"+token,SESSION_EXPIRE);
        return TaotaoResult.ok(user);
    }
}
