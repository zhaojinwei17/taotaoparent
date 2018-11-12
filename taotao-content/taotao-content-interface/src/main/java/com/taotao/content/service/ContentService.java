package com.taotao.content.service;

import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbContent;
import com.taotao.utils.TaotaoResult;

import java.util.List;

public interface ContentService {
    EasyUIDataGridResult queryContentList(Long categoryId,Integer page,Integer rows);
    TaotaoResult addContent(TbContent tbContent);
    TaotaoResult updateContent(TbContent tbContent);
    List<TbContent> getContentListByCid(Long id);
    TaotaoResult deleteContent(Long[] ids);
}
