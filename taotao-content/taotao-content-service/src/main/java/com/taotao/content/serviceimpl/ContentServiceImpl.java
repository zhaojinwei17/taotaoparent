package com.taotao.content.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.content.service.ContentService;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.utils.JsonUtils;
import com.taotao.utils.TaotaoResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Value("${CONTENT_LIST}")
    String CONTENT_LIST;

    @Resource
    TbContentMapper tbContentMapper;
    @Resource
    JedisClient jedisClient;


    @Override
    public EasyUIDataGridResult queryContentList(Long categoryId, Integer page, Integer rows) {
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        PageHelper.startPage(page, rows);
        TbContentExample exampl = new TbContentExample();
        TbContentExample.Criteria criteria = exampl.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> contents = tbContentMapper.selectByExampleWithBLOBs(exampl);
        PageInfo<TbContent> contentPageInfo = new PageInfo<TbContent>(contents);
        result.setTotal(contentPageInfo.getTotal());
        result.setRows(contents);
        return result;
    }

    @Override
    public TaotaoResult addContent(TbContent tbContent) {
        tbContent.setUpdated(new Date());
        tbContent.setCreated(new Date());
        tbContentMapper.insert(tbContent);
        try {
            jedisClient.hdel(CONTENT_LIST, tbContent.getCategoryId().toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult updateContent(TbContent tbContent) {
        tbContent.setUpdated(new Date());
        tbContentMapper.updateByPrimaryKeySelective(tbContent);
        try {
            jedisClient.hdel(CONTENT_LIST, tbContent.getCategoryId().toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return TaotaoResult.ok();
    }

    @Override
    public List<TbContent> getContentListByCid(Long id) {
        try {
            String json = jedisClient.hget(CONTENT_LIST, id + "");
            if (StringUtils.isNotBlank(json)) {
                List<TbContent> contents = JsonUtils.jsonToList(json, TbContent.class);
                return contents;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(id);
        List<TbContent> contents = tbContentMapper.selectByExample(example);

        try {
            jedisClient.hset(CONTENT_LIST, id + "", JsonUtils.objectToJson(contents));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contents;
    }

    @Override
    public TaotaoResult deleteContent(Long[] ids) {
        for (Long id : ids) {
            try {
                TbContent tbContent = tbContentMapper.selectByPrimaryKey(id);
                jedisClient.hdel(CONTENT_LIST, tbContent.getCategoryId().toString());
            }catch (Exception e){
                e.printStackTrace();
            }
            tbContentMapper.deleteByPrimaryKey(id);
        }
        return TaotaoResult.ok();
    }

}
