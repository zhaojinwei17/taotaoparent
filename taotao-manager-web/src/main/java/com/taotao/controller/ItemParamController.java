package com.taotao.controller;

import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.service.ItemParamService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class ItemParamController {
    @Resource
    ItemParamService itemParamService;

    @RequestMapping("/item/param/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(int page, int rows){
        return itemParamService.getItemParamList(page,rows);
    }
}
