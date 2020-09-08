package com.atguigu.service;

import org.springframework.web.multipart.MultipartFile;

public interface VideoService {
    String uploadAliyunVideo(MultipartFile file);

    void deleteVideo(String videoId);
}
