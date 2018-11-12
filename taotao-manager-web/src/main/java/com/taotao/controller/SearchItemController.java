package com.taotao.controller;

import com.taotao.search.service.SearchItemService;
import com.taotao.utils.TaotaoResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class SearchItemController {

    @Resource
    SearchItemService searchItemService;

    @RequestMapping("/index/item/import")
    @ResponseBody
    public TaotaoResult importAllItems(){
        return searchItemService.importAllItems();
    }
}
