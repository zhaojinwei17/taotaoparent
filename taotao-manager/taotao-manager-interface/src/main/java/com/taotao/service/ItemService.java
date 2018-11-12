package com.taotao.service;

import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.utils.TaotaoResult;

public interface ItemService {
    EasyUIDataGridResult getItemList(int page,int rows);
    String addItem(TbItem item,String desc);
    TaotaoResult editItem(Long id);
    TaotaoResult queryItemParam(Long id);
    String updateItem(TbItem item,String desc);
    TaotaoResult deleteItems(Long[] ids);
    TaotaoResult instockItems(Long[] ids);
    TaotaoResult reshelfItems(Long[] ids);
    TbItem getItemById(Long id);
    TbItemDesc getItemDescById(Long id);
}
