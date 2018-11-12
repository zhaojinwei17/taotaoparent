package com.taotao.solrj;

import com.taotao.pojo.SearchItem;
import com.taotao.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestSolrJ {

    @Test
    public void addDocument() throws IOException, SolrServerException {
//        SolrServer server=new HttpSolrServer("http://192.168.0.150:8080/solr/collection");
//        SolrInputDocument document=new SolrInputDocument();
//        document.addField("id","2018爱你要发！");
//        document.addField("item_title","测试商品1");
//        document.addField("item_price",1000);
////        document.addField("cat_name","手机");
//        server.add(document);
//        server.commit();
    }
    @Test
    public void cloudSearchItems() throws IOException, SolrServerException {

//        CloudSolrServer client=new CloudSolrServer("192.168.0.151:2181,192.168.0.152:2181,192.168.0.153:2181");
//        client.setDefaultCollection("collection");
//
//        String qry="测试";
//        int rows=20;
//        int start=0;
//        String df="item_title";
//        List<SearchItem> searchItems=new ArrayList<SearchItem>();
//
//        SolrQuery query=new SolrQuery();
//        query.setQuery(qry);
//        query.setStart(start);
//        query.setRows(rows);
//        query.set("df",df);
//        query.setHighlight(true);
//        query.setHighlightSimplePre("<em>");
//        query.setHighlightSimplePost("</em>");
//        QueryResponse queryResponse = client.query(query);
//
//        SolrDocumentList results = queryResponse.getResults();
//        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
//
//        for (SolrDocument document:results) {
//            String id = (String) document.get("id");
//            SearchItem item=new SearchItem();
//            item.setId(id);
//            item.setPrice(Long.valueOf(document.get("item_price")+""));
//            item.setCat_name((String) document.get("item_category_name"));
//            item.setImage((String) document.get("item_image"));
//            item.setSell_point((String) document.get("item_sell_point"));
//            List<String> titles = highlighting.get(id).get("item_title");
//            if (titles!=null && titles.size()>0){
//                item.setTitle(titles.get(0));
//            }else {
//                item.setTitle((String) document.get("item_title"));
//            }
//            searchItems.add(item);
//        }
//        SearchResult result=new SearchResult();
//        result.setItemList(searchItems);
//        result.setTotalPages(results.getNumFound()+"");
//        result.setRecourdCount((results.getNumFound()/rows+1)+"");
//        System.out.println(searchItems);

    }
    @Test
    public void searchItems() throws IOException, SolrServerException {
//        String qry="手机";
//        int rows=20;
//        int start=0;
//        String df="item_title";
//
//        List<SearchItem> searchItems=new ArrayList<SearchItem>();
//
//        SolrServer client=new HttpSolrServer("http://192.168.0.150:8080/solr/collection");
//
//        SolrQuery query=new SolrQuery();
//        query.setQuery(qry);
//        query.setStart(start);
//        query.setRows(rows);
//        query.set("df",df);
//        query.setHighlight(true);
//        query.setHighlightSimplePre("<em>");
//        query.setHighlightSimplePost("</em>");
//        QueryResponse queryResponse = client.query(query);
//
//        SolrDocumentList results = queryResponse.getResults();
//        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
//
//        for (SolrDocument document:results) {
//            String id = (String) document.get("id");
//            SearchItem item=new SearchItem();
//            item.setId(id);
//            item.setPrice(Long.valueOf(document.get("item_price")+""));
//            item.setCat_name((String) document.get("item_category_name"));
//            item.setImage((String) document.get("item_image"));
//            item.setSell_point((String) document.get("item_sell_point"));
//            List<String> titles = highlighting.get(id).get("item_title");
//            if (titles!=null && titles.size()>0){
//                item.setTitle(titles.get(0));
//            }else {
//                item.setTitle((String) document.get("item_title"));
//            }
//            searchItems.add(item);
//        }
//        SearchResult result=new SearchResult();
//        result.setTotalPages(results.getNumFound()+"");
//        result.setRecourdCount((results.getNumFound()/rows+1)+"");
    }
    @Test
    public void testMQ(){
//        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activeMQ.xml");
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
