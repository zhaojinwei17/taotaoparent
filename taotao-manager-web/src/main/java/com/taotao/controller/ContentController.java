package com.taotao.controller;

import com.taotao.content.service.ContentService;
import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbContent;
import com.taotao.utils.TaotaoResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class ContentController {

    @Resource
    ContentService contentService;


    @RequestMapping("/content/query/list")
    @ResponseBody
    public EasyUIDataGridResult queryContentList(Long categoryId,Integer page,Integer rows){
        return contentService.queryContentList(categoryId,page,rows);
    }

    @RequestMapping("/content/save")
    @ResponseBody
    public TaotaoResult addContent(TbContent tbContent){
        return contentService.addContent(tbContent);
    }
    @RequestMapping("/rest/content/edit")
    @ResponseBody
    public TaotaoResult updateContent(TbContent tbContent){
        return contentService.updateContent(tbContent);
    }
    @RequestMapping("/content/delete")
    @ResponseBody
    public TaotaoResult deleteContent(Long[] ids){
        return contentService.deleteContent(ids);
    }
}
