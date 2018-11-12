package com.taotao.content.service;

import com.taotao.pojo.EasyUITreeNode;
import com.taotao.utils.TaotaoResult;

import java.util.List;

public interface ContentCategoryService {
    List<EasyUITreeNode> getContentCatList(Long parentId);
    TaotaoResult addContentCategory(Long parentId, String name);
    TaotaoResult deleteContentCategory(Long id,boolean isTopNode);
    TaotaoResult updateContentCategory(Long id,String name);
}
