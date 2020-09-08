package com.atguigu.edu.service;


import com.atguigu.edu.hander.VideoServiceHandler;
import com.atguigu.response.RetVal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Component
@FeignClient(value = "EDU-VIDEO",fallback = VideoServiceHandler.class)
public interface VideoServiceFeign {
    @DeleteMapping("/aliyun/{videoId}")
    public RetVal deleteSingleVideo(@PathVariable("videoId") String videoId);

    //3.删除多个视频
    @DeleteMapping("/aliyun/deleteMultiVideo")
    public RetVal deleteMultiVideo(@RequestParam("videoIdList") List<String> videoIdList);
}
