package com.taotao.pojo;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable {
    String totalPages;
    String recourdCount;
    List<SearchItem> itemList;

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public String getRecourdCount() {
        return recourdCount;
    }

    public void setRecourdCount(String recourdCount) {
        this.recourdCount = recourdCount;
    }

    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }
}
