package com.taotao.sso.service;

import com.taotao.utils.TaotaoResult;

public interface TokenService {
    TaotaoResult getUserByToken(String token);
}
