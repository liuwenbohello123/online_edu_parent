package com.atguigu.controller;

import com.atguigu.response.RetVal;
import com.atguigu.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/oss")
public class OssController {
    @Autowired
    OssService ossService;


    //文件上穿
    @PostMapping("upLoadFile")
    public RetVal upLoadFile(MultipartFile file) throws Exception {
        //返回一个路径给前端，前端用于展示
       String retUrl= ossService.upLoadFile(file);
        return RetVal.success().data("retUrl",retUrl);
    }
    //文件删除
    @DeleteMapping("deleteFile")
    public RetVal deleteFile(String fileName) throws Exception {
        //返回一个路径给前端，前端用于展示
       boolean flag= ossService.deleteFile(fileName);
        return RetVal.success();
    }

}
