package com.taotao.serviceimpl;

import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    public TbItemMapper tbItemMapper;

    public TbItem getItem(){
        TbItem item = tbItemMapper.selectByPrimaryKey(536563l);
        return item;
    }
}
