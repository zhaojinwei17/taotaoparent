package com.taotao.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.service.ItemParamService;
import com.taotao.vo.TbItemParamVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService{

    @Resource
    TbItemParamMapper tbItemParamMapper;
    @Resource
    TbItemCatMapper tbItemCatMapper;


    @Override
    public EasyUIDataGridResult getItemParamList(int page, int rows) {
        EasyUIDataGridResult result=new EasyUIDataGridResult();
        PageHelper.startPage(page,rows);
        List<TbItemParam> tbItemParams = tbItemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
        List<TbItemParamVo> tbItemParamVos=new ArrayList<TbItemParamVo>();
        for (TbItemParam tbItemParam : tbItemParams) {
            TbItemCat itemCat = tbItemCatMapper.selectByPrimaryKey(tbItemParam.getItemCatId());
            TbItemParamVo tbItemParamVo=new TbItemParamVo();
            tbItemParamVo.setId(tbItemParam.getId());
            tbItemParamVo.setItemCatId(tbItemParam.getItemCatId());
            tbItemParamVo.setItemCatName(itemCat.getName());
            tbItemParamVo.setParamData(tbItemParam.getParamData());
            tbItemParamVo.setCreated(tbItemParam.getCreated());
            tbItemParamVo.setUpdated(tbItemParam.getUpdated());
            tbItemParamVos.add(tbItemParamVo);
        }
        PageInfo<TbItemParam> itemPageInfo = new PageInfo<TbItemParam>(tbItemParams);
        result.setTotal(itemPageInfo.getTotal());
        result.setRows(tbItemParamVos);
        return result;
    }
}
