package com.blog.controller.back;

import com.blog.properties.MinioProperties;
import com.blog.result.Result;
import com.blog.service.MinioService;
import com.blog.vo.BackUploadImageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/file")
@Api(tags = "文件存储")
@Slf4j
public class MinioController {

    @Resource
    private MinioService minioService;

    @Resource
    private MinioProperties minioProperties;

    @GetMapping("/list")
    @ApiOperation(value = "查询单个桶内文件列表")
    public List<Object> list() {
        log.info("查询单个储桶中的所有对象信息...");
        List<Object> items = minioService.listObjects(minioProperties.getBucketName());
        return items;
    }

    @PostMapping("/upload/img")
    @ApiOperation(value = "上传图片")
    public Result uploadFile(MultipartFile file) {
        log.info("上传图片");
        try {
            String fileName = file.getOriginalFilename();
            assert fileName != null;

            // 根据业务设计，设置存储路径：按天创建目录
            String objectName = new SimpleDateFormat("yyyy-MM-dd/").format(new Date())
                    + UUID.randomUUID().toString()
                    + fileName.substring(fileName.lastIndexOf("."));

            minioService.upload(file, objectName);
            log.info("图片格式为：{}", file.getContentType());
            log.info("图片原名称为：{}", fileName);
            // 下面的url是为了
            String fileUrl = "http://zmzcoding.top" + "/images/"+ minioProperties.getBucketName() +"/"+ objectName;  // 生产环境
            // String fileUrl = "http://localhost:7070" + "/images/"+ minioProperties.getBucketName() +"/"+ objectName;  // 开发环境
            log.info("图片对象路径为：{}", fileUrl);
            return Result.success(new BackUploadImageVO(fileUrl));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("上传图片失败");
        }
    }

    @DeleteMapping("/delete/{fileName}")
    @ApiOperation(value = "删除文件")
    public Result<String> deleteFile(@PathVariable("fileName") String fileName) {
        log.info("删除文件");
        boolean status = minioService.delFile(minioProperties.getBucketName(), fileName);
        return status ? Result.success("删除成功") : Result.error("删除失败");
    }

    @GetMapping("/download/{fileName}")
    @ApiOperation(value = "下载文件")
    public Result<String> downloadFile(@PathVariable("fileName") String fileName) {
        log.info("下载文件");
        try {
            minioService.download(fileName);
            return Result.success();
        } catch (Exception e) {
            return Result.error("下载失败");
        }

    }



}
