package com.taotao.sso.service;

import com.taotao.utils.TaotaoResult;

public interface LoginService {
    TaotaoResult login(String username,String password);
}
