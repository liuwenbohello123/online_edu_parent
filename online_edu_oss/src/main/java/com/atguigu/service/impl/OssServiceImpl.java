package com.atguigu.service.impl;

import com.atguigu.oss.OssTemplate;
import com.atguigu.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Autowired
    OssTemplate ossTemplate;

    @Override
    public String upLoadFile(MultipartFile file) throws Exception {
        //获取上传流
        InputStream inputStream = file.getInputStream();
        //获取文件的名称
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
       String fileName= UUID.randomUUID().toString().replaceAll("-", "")+suffix;
        String retUrl = ossTemplate.upload(inputStream, fileName);
        //https://liuwenbo123.oss-cn-beijing.aliyuncs.com/104.jpg

        return retUrl;
    }

    @Override
    public boolean deleteFile(String fileName) {
        ossTemplate.deleteFile(fileName);
        return true;
    }


}
