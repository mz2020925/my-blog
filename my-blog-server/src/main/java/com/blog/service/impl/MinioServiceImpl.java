package com.blog.service.impl;

import com.blog.service.MinioService;
import com.blog.utils.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MinioServiceImpl implements MinioService {
    
    @Autowired
    private MinioUtil minioUtil;

    public Boolean bucketExists(String bucketName) {
        return minioUtil.bucketExists(bucketName);
    }

    public void makeBucket(String bucketName) {
        minioUtil.makeBucket(bucketName);
    }

    @SneakyThrows
    public List<Object> listObjects(String bucketName) {
        return minioUtil.listObjects(bucketName);
    }



    public Boolean upload(MultipartFile multipartFile, String objectName) {
        return minioUtil.putObject(multipartFile, objectName);
    }


    public void upload(MultipartFile[] multipartFile) {
        minioUtil.putObject(multipartFile);
    }


    public Boolean delFile(String bucketName,String fileName) {
        return minioUtil.removeObject(bucketName, fileName);
    }


    public String getFileUrl(String fileName) {
        return minioUtil.getObjectUrl(fileName);
    }

    public String getFileUrl(String bucketName, String region, String fileName){
        return minioUtil.getObjectUrl(bucketName, region, fileName);
    }

    public void download(String fileName) {
        minioUtil.download(fileName);
    }
}
