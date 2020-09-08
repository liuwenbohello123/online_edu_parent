package com.atguigu.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface OssService {
    String upLoadFile(MultipartFile file) throws FileNotFoundException, Exception;


    boolean deleteFile(String fileName);
}
