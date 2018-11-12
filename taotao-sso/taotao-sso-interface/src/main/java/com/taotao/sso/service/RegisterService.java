package com.taotao.sso.service;

import com.taotao.pojo.TbUser;
import com.taotao.utils.TaotaoResult;

public interface RegisterService {
    TaotaoResult checkData(String param,int type);
    TaotaoResult register(TbUser user);
}
