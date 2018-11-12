package com.taotao.vo;

import com.taotao.pojo.TbItemParam;

import java.io.Serializable;

public class TbItemParamVo extends TbItemParam implements Serializable {
    private String itemCatName;

    public void setItemCatName(String itemCatName) {
        this.itemCatName = itemCatName;
    }

    public String getItemCatName() {
        return itemCatName;
    }
}
