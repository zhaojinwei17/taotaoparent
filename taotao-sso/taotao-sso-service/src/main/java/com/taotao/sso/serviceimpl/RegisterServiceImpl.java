package com.taotao.sso.serviceimpl;

import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.service.RegisterService;
import com.taotao.utils.TaotaoResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Resource
    TbUserMapper tbUserMapper;
    @Resource
    PasswordEncoder passwordEncoder;


    @Override
    public TaotaoResult checkData(String param, int type) {
        TbUserExample example=new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        //type:1用户名，2手机号，3邮箱
        if (type==1){
            criteria.andUsernameEqualTo(param);
        }else if (type==2){
            criteria.andPhoneEqualTo(param);
        }else if (type==3){
            criteria.andEmailEqualTo(param);
        }else {
            return TaotaoResult.build(400,"验证类型错误！");
        }
        List<TbUser> users = tbUserMapper.selectByExample(example);
        if (users!=null && users.size()>0){
            return TaotaoResult.ok(false);
        }
        return TaotaoResult.ok(true);
    }

    @Override
    public TaotaoResult register(TbUser user) {
        if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword()) || StringUtils.isBlank(user.getPhone())){
            return TaotaoResult.build(400,"数据不完整！");
        }
        TaotaoResult result = checkData(user.getUsername(), 1);
        if (!(boolean)result.getData()){
            return TaotaoResult.build(400,"用户名已被占用！");
        }
        result=checkData(user.getPhone(),2);
        if (!(boolean)result.getData()){
            return TaotaoResult.build(400,"手机号已被占用！");
        }
        user.setCreated(new Date());
        user.setUpdated(new Date());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        tbUserMapper.insert(user);
        return TaotaoResult.ok();
    }
}
