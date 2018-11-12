package com.taotao.sso.serviceimpl;

import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.service.LoginService;
import com.taotao.utils.JsonUtils;
import com.taotao.utils.TaotaoResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    TbUserMapper tbUserMapper;
    @Resource
    PasswordEncoder passwordEncoder;
    @Resource
    JedisClient jedisClient;
    @Value("${SESSION_EXPIRE}")
    Integer SESSION_EXPIRE;

    @Override
    public TaotaoResult login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return TaotaoResult.build(400,"数据不完整！");
        }
        TbUserExample example=new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> users = tbUserMapper.selectByExample(example);
        if (users==null || users.size()==0){
            return TaotaoResult.build(400,"用户名或密码错误！");
        }
        TbUser user=users.get(0);
        boolean matches = passwordEncoder.matches(password, user.getPassword());
        if (matches){
            user.setPassword(null);
            String token= UUID.randomUUID().toString();
            jedisClient.set("SESSION:"+token, JsonUtils.objectToJson(user));
            jedisClient.expire("SESSION:"+token,SESSION_EXPIRE);
            return TaotaoResult.ok(token);
        }
        return TaotaoResult.build(400,"用户名或密码错误！");
    }
}
