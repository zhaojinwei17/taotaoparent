package com.taotao.search.serviceimpl;

import com.taotao.pojo.SearchItem;
import com.taotao.pojo.SearchResult;
import com.taotao.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {

    @Resource
    SolrServer solrServer;

    @Override
    public SearchResult search(String keyword, int page, int rows) {
        int start=0;
        if (page<0){
            start=(page-1)*rows;
        }
        List<SearchItem> searchItems=new ArrayList<SearchItem>();
        SolrQuery query=new SolrQuery();
        query.setQuery(keyword);
        query.setStart(start);
        query.setRows(rows);
        query.set("df","item_keywords");
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<span style=\" color:red \">");
        query.setHighlightSimplePost("</span>");
        QueryResponse queryResponse = null;
        try {
            queryResponse = solrServer.query(query);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        SolrDocumentList results = queryResponse.getResults();
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();

        for (SolrDocument document:results) {
            String id = (String) document.get("id");
            SearchItem item=new SearchItem();
            item.setId(id);
            item.setPrice(Long.valueOf(document.get("item_price")+""));
            item.setCat_name((String) document.get("item_category_name"));
            String images = (String) document.get("item_image");
            int begin=images.lastIndexOf(',')==-1?0:images.lastIndexOf(',')+1;
            item.setImage(images.substring(begin,images.length()));
            item.setSell_point((String) document.get("item_sell_point"));
            List<String> titles = highlighting.get(id).get("item_title");
            if (titles!=null && titles.size()>0){
                item.setTitle(titles.get(0));
            }else {
                item.setTitle((String) document.get("item_title"));
            }
            searchItems.add(item);
        }
        SearchResult result=new SearchResult();
        result.setItemList(searchItems);
        result.setRecourdCount(results.getNumFound()+"");

        if (results.getNumFound()%rows>0){
            result.setTotalPages((results.getNumFound()/rows+1)+"");
        }else {
            result.setTotalPages((results.getNumFound()/rows)+"");
        }

        return result;
    }
}
