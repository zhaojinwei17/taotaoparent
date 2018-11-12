package com.taotao.search.listener;

import com.taotao.pojo.SearchItem;
import com.taotao.search.mapper.ItemMapper;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;

public class ItemAddMessageListener implements MessageListener {

    @Resource
    ItemMapper itemMapper;
    @Resource
    SolrServer solrServer;

    @Override
    public void onMessage(Message message) {
        TextMessage msg= (TextMessage) message;
        try {
            String itemId = msg.getText();
            SearchItem searchItem = itemMapper.getItemById(Long.valueOf(itemId));
            SolrInputDocument document=new SolrInputDocument();
            document.addField("id",searchItem.getId());
            document.addField("item_title",searchItem.getTitle());
            document.addField("item_sell_point",searchItem.getSell_point());
            document.addField("item_price",searchItem.getPrice());
            document.addField("item_image",searchItem.getImage());
            document.addField("item_category_name",searchItem.getCat_name());
            solrServer.add(document);
            solrServer.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
