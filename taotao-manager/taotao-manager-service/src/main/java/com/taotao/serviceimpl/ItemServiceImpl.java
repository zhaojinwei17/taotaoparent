package com.taotao.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.service.ItemService;
import com.taotao.utils.IDUtils;
import com.taotao.utils.JsonUtils;
import com.taotao.utils.TaotaoResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    TbItemMapper tbItemMapper;
    @Resource
    TbItemDescMapper tbItemDescMapper;
    @Resource
    TbItemParamItemMapper itemParamItemMapper;
    @Resource
    JedisClient jedisClient;


    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        EasyUIDataGridResult result=new EasyUIDataGridResult();
        PageHelper.startPage(page,rows);
        List<TbItem> tbItems = tbItemMapper.selectByExample(new TbItemExample());

        PageInfo<TbItem> itemPageInfo=new PageInfo<TbItem>(tbItems);
        result.setTotal(itemPageInfo.getTotal());
        result.setRows(tbItems);
        return result;
    }

    @Override
    public String addItem(TbItem item, String desc) {
        item.setId(IDUtils.genItemId());
        //商品状态，1-正常，2-下架，3-删除
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        tbItemMapper.insert(item);
        TbItemDesc itemDesc=new TbItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        tbItemDescMapper.insert(itemDesc);
        return item.getId()+"";
    }

    @Override
    public TaotaoResult editItem(Long id) {
        return TaotaoResult.ok(tbItemDescMapper.selectByPrimaryKey(id));
    }

    @Override
    public TaotaoResult queryItemParam(Long id) {
        TbItemParamItemExample example=new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(id);
        List<TbItemParamItem> tbItemParamItems = itemParamItemMapper.selectByExample(example);
        if (tbItemParamItems!=null && tbItemParamItems.size()>0){
            return TaotaoResult.ok(tbItemParamItems.get(0));
        }else {
            return TaotaoResult.ok();
        }
    }

    @Override
    public String updateItem(TbItem item, String desc) {
        item.setUpdated(new Date());
        int i = tbItemMapper.updateByPrimaryKeySelective(item);
        TbItemDesc itemDesc = tbItemDescMapper.selectByPrimaryKey(item.getId());
        if (itemDesc==null){
            itemDesc=new TbItemDesc();
            itemDesc.setItemId(item.getId());
            itemDesc.setItemDesc(desc);
            itemDesc.setCreated(new Date());
            itemDesc.setUpdated(new Date());
            int insert = tbItemDescMapper.insert(itemDesc);
        }else {
            itemDesc.setItemDesc(desc);
            itemDesc.setUpdated(new Date());
            int i1 = tbItemDescMapper.updateByPrimaryKeySelective(itemDesc);
        }
        return item.getId()+"";
    }

    @Override
    public TaotaoResult deleteItems(Long[] ids) {
        TbItem item=new TbItem();
        for (Long id : ids) {
            item.setId(id);
            //商品状态，1-正常，2-下架，3-删除
            item.setStatus((byte) 3);
            tbItemMapper.updateByPrimaryKeySelective(item);
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult instockItems(Long[] ids) {
        TbItem item=new TbItem();
        for (Long id : ids) {
            item.setId(id);
            //商品状态，1-正常，2-下架，3-删除
            item.setStatus((byte) 2);
            tbItemMapper.updateByPrimaryKeySelective(item);
        }
        return TaotaoResult.ok();
    }
    @Override
    public TaotaoResult reshelfItems(Long[] ids) {
        TbItem item=new TbItem();
        for (Long id : ids) {
            item.setId(id);
            //商品状态，1-正常，2-下架，3-删除
            item.setStatus((byte) 1);
            tbItemMapper.updateByPrimaryKeySelective(item);
        }
        return TaotaoResult.ok();
    }

    @Override
    public TbItem getItemById(Long id) {
        String key="ITEM_INFO:"+id+":BASE";
        try{
            String json = jedisClient.get(key);
            if (StringUtils.isNotBlank(json)){
                TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
                return item;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        TbItem item = tbItemMapper.selectByPrimaryKey(id);
        if (item!=null){
            String value= JsonUtils.objectToJson(item);
            jedisClient.set(key,value);
            jedisClient.expire(key,3600);
        }
        return item;
    }

    @Override
    public TbItemDesc getItemDescById(Long id) {
        String key="ITEMDESC_INFO:"+id+":DESC";
        try{
            String json = jedisClient.get(key);
            if (StringUtils.isNotBlank(json)){
                TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
                return itemDesc;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        TbItemDesc itemDesc = tbItemDescMapper.selectByPrimaryKey(id);
        if (itemDesc!=null){
            String value= JsonUtils.objectToJson(itemDesc);
            jedisClient.set(key,value);
            jedisClient.expire(key,3600);
        }
        return itemDesc;
    }
}
