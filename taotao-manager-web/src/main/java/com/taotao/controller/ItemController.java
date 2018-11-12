package com.taotao.controller;

import com.taotao.jms.SendMessage;
import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import com.taotao.utils.TaotaoResult;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
@Controller
public class ItemController {
    @Resource
    ItemService itemService;
    @Resource
    JmsTemplate jmsTemplate;
    @Resource
    ActiveMQTopic addItemtopic;

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page,Integer rows){
        EasyUIDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }

    @RequestMapping("/item/save")
    @ResponseBody
    public TaotaoResult addItem(TbItem item,String desc){
        String itemId = itemService.addItem(item, desc);
        SendMessage.sendQueueMessage(jmsTemplate,addItemtopic,itemId);
        return TaotaoResult.ok();
    }
    @RequestMapping("/rest/item/update")
    @ResponseBody
    public TaotaoResult updateItem(TbItem item,String desc){
        String itemId = itemService.updateItem(item, desc);
        SendMessage.sendQueueMessage(jmsTemplate,addItemtopic,itemId);
        return TaotaoResult.ok();
    }

    @RequestMapping("/rest/page/item-edit")
    public String editItem(){
        return "item-edit";
    }
    @RequestMapping("/rest/item/query/item/desc/{id}")
    @ResponseBody
    public TaotaoResult editItem(@PathVariable Long id){
        TaotaoResult result = itemService.editItem(id);
        return result;
    }
    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public TaotaoResult deleteItems(Long[] ids){
        TaotaoResult result = itemService.deleteItems(ids);
        return result;
    }
    @RequestMapping("/rest/item/instock")//下架
    @ResponseBody
    public TaotaoResult instockItems(Long[] ids){
        TaotaoResult result = itemService.instockItems(ids);
        return result;
    }
    @RequestMapping("/rest/item/reshelf")//上架
    @ResponseBody
    public TaotaoResult reshelfItems(Long[] ids){
        TaotaoResult result = itemService.reshelfItems(ids);
        return result;
    }
    @RequestMapping("/rest/item/param/item/query/{id}")
    @ResponseBody
    public TaotaoResult queryItemParam(@PathVariable Long id){
        return itemService.queryItemParam(id);
    }

}
