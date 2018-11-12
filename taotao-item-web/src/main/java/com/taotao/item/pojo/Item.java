package com.taotao.item.pojo;

import com.taotao.pojo.TbItem;

public class Item extends TbItem {
    public Item(TbItem tbItem){
        setId(tbItem.getId());
        setBarcode(tbItem.getBarcode());
        setCid(tbItem.getCid());
        setCreated(tbItem.getCreated());
        setImage(tbItem.getImage());
        setNum(tbItem.getNum());
        setPrice(tbItem.getPrice());
        setSellPoint(tbItem.getSellPoint());
        setStatus(tbItem.getStatus());
        setTitle(tbItem.getTitle());
        setUpdated(tbItem.getUpdated());
    }

    public String[] getImages() {
        String image = getImage();
        String[] images = image.split(",");
        return images;
    }
}
