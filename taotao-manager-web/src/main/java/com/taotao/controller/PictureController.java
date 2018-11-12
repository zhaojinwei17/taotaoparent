package com.taotao.controller;

import com.taotao.utils.FastDFSClient;
import com.taotao.utils.JsonUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PictureController {

    @RequestMapping(value = "/pic/upload" , produces = MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    @ResponseBody
    public String uploadImg(MultipartFile uploadFile){
        Map<String,Object> map=new HashMap<String, Object>();
        try {
            FastDFSClient client=new FastDFSClient("classpath:conf/client.conf");
            String originalFilename = uploadFile.getOriginalFilename();
            String extName=originalFilename.substring(originalFilename.lastIndexOf(".")+1);
            String fileid = client.uploadFile(uploadFile.getBytes(), extName);
            String url=client.getUrl(fileid);
            map.put("error",0);
            map.put("url",url);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error",1);
            map.put("message","图片上传失败！");
        }
        return JsonUtils.objectToJson(map);
    }
}
