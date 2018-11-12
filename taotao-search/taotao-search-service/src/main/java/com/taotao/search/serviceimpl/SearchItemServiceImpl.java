package com.taotao.search.serviceimpl;

import com.taotao.pojo.SearchItem;
import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.service.SearchItemService;
import com.taotao.utils.TaotaoResult;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Service
public class SearchItemServiceImpl implements SearchItemService {

    @Resource
    ItemMapper itemMapper;
    @Resource
    SolrServer solrServer;


    @Override
    public TaotaoResult importAllItems() {
        List<SearchItem> itemList = itemMapper.getItemList();
        try {
            for (SearchItem searchItem : itemList) {
                SolrInputDocument document=new SolrInputDocument();
                document.addField("id",searchItem.getId());
                document.addField("item_title",searchItem.getTitle());
                document.addField("item_sell_point",searchItem.getSell_point());
                document.addField("item_price",searchItem.getPrice());
                document.addField("item_image",searchItem.getImage());
                document.addField("item_category_name",searchItem.getCat_name());
                solrServer.add(document);
            }
            solrServer.commit();
            return TaotaoResult.ok();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return TaotaoResult.build(500,"数据导入失败！");
    }
}
