package com.taotao.search.service;

import com.taotao.pojo.SearchResult;

public interface SearchService {
    SearchResult search(String keyword,int page,int rows);
}
