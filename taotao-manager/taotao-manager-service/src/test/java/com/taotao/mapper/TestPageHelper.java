package com.taotao.mapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.utils.IDUtils;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Date;
import java.util.List;

public class TestPageHelper{

    @Test
    public void testph(){
//        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao1.xml");
//        TbItemMapper tbItemMapper = context.getBean(TbItemMapper.class);
//        PageHelper.startPage(1,10);
//        TbItemExample example=new TbItemExample();
//        TbItemExample.Criteria criteria = example.createCriteria();
//        criteria.andPriceBetween(0l,20000000l);
//
//        List<TbItem> tbItems = tbItemMapper.selectByExample(example);
//        PageInfo<TbItem> pageInfo=new PageInfo<TbItem>(tbItems);
//        System.out.println(pageInfo.getTotal());
//        System.out.println(pageInfo.getPages());
//        for (TbItem item:tbItems) {
//            System.out.println(item.getTitle());
//        }
    }
    @Test
    public void testInsert(){
//        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao1.xml");
//        TbItemMapper tbItemMapper = context.getBean(TbItemMapper.class);
//        TbItem item=new TbItem();
//        item.setId(IDUtils.genItemId());
//        item.setTitle("肯定会及时");
//        item.setCid(897l);
//        item.setUpdated(new Date());
//        item.setCreated(new Date());
//        item.setStatus((byte) 1);
//        item.setSellPoint("的房价快速回复");
//        item.setPrice((long) 34);
//        item.setNum(324);
//        item.setImage("上课了的回复的");
//        item.setBarcode("大家开始发光");
//        tbItemMapper.insert(item);
    }
    @Test
    public void testSs(){
//        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao1.xml");
//        TbItemParamMapper itemParamMapper = context.getBean(TbItemParamMapper.class);
//        List<TbItemParam> tbItemParams = itemParamMapper.selectByExample(new TbItemParamExample());
//        System.out.println();
    }
    @Test
    public void testMQ(){
//        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activeMQ.xml");
//        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
//        ActiveMQQueue queue = context.getBean(ActiveMQQueue.class);
//        jmsTemplate.send(queue, new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                Message message = session.createTextMessage("hello activemq!");
//                return message;
//            }
//        });
    }
}
