package com.taotao.portal.controller;

import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndexController {

    @Value("${CONTENT_LUNBO_ID}")
    Long CONTENT_LUNBO_ID;

    @Resource
    ContentService contentService;

    @RequestMapping("index")
    public String showIndex(Model model){
        List<TbContent> ad1list = contentService.getContentListByCid(CONTENT_LUNBO_ID);
        model.addAttribute("ad1List",ad1list);
        return "index";
    }

}
