package com.taotao.search.mapper;

import com.taotao.pojo.SearchItem;

import java.util.List;

public interface ItemMapper {
    List<SearchItem> getItemList();
    SearchItem getItemById(Long itemId);
}
