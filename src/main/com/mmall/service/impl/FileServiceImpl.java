package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by geely
 */
@Service("iFileService")
public class FileServiceImpl implements IFileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);


    public String upload(MultipartFile file, String path) {
        /**得到文件的原始文件名*/
        String fileName = file.getOriginalFilename();
        //扩展名
        //abc.jpg
        /**得到文件的后缀名，从后面开始进行截取，因为有的文件名是abc.abc.jpg*/
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        logger.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}", fileName, path, uploadFileName);

        File fileDir = new File(path);
        if (!fileDir.exists()) {
            /**赋予可写的权限*/
            fileDir.setWritable(true);
            /**文件不存在进行创建*/
            fileDir.mkdirs();///a/a/c/d文件夹都可以创建；
        }
        /**路径和文件名到目标文件*/
        File targetFile = new File(path, uploadFileName);


        try {
            /**targetFile文件名file文件对象*/
            file.transferTo(targetFile);
            //文件已经上传成功了


            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //已经上传到ftp服务器上

            targetFile.delete();
        } catch (IOException e) {
            logger.error("上传文件异常", e);
            return null;
        }
        //A:abc.jpg
        //B:abc.jpg
        //返回文件名
        return targetFile.getName();
    }

}
