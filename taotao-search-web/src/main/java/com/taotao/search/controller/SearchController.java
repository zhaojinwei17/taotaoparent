package com.taotao.search.controller;

import com.taotao.pojo.SearchResult;
import com.taotao.search.service.SearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@Controller
public class SearchController {

    @Resource
    SearchService searchService;
    @Value("${SEARCH_RESULT_ROWS}")
    Integer rows;

    @RequestMapping("/search")
    public String searchItemList(String keyword, @RequestParam(defaultValue = "1") Integer page, Model model){
        if (keyword!=null){
            try {
                byte[] bytes = keyword.getBytes("iso8859-1");
                keyword=new String(bytes,"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            SearchResult searchResult = searchService.search(keyword, page, rows);
            model.addAttribute("query",keyword);
            model.addAttribute("totalPages",searchResult.getTotalPages());
            model.addAttribute("page",page);
            model.addAttribute("recourdCount",searchResult.getRecourdCount());
            model.addAttribute("itemList",searchResult.getItemList());
        }
        return "search";
    }
}
