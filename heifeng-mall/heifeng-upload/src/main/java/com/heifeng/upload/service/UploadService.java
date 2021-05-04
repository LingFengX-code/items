package com.heifeng.upload.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {
    /**
     * 文件校验的格式
     */
    private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg", "image/gif");

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    //文件上传方法
    public String upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        // 校验文件的类型
        String contentType = file.getContentType();
        if (!CONTENT_TYPES.contains(contentType)){
            // 文件类型不合法，直接返回null
            LOGGER.info("文件类型不合法：{"+originalFilename+"}" );
            return null;
        }
        try {
            // 校验文件的内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null){
                LOGGER.info("文件内容不合法：{"+originalFilename+"}");
                return null;
            }
            // 保存到服务器
            file.transferTo(new File("E:\\javaDevelopSoftware\\Idea\\userFiles\\images\\update\\" + originalFilename));
            // 生成url地址，返回
            return "http://image.heifeng.com/" + originalFilename;
        } catch (IOException e) {
            LOGGER.info("服务器内部错误：{"+originalFilename+"}");
            e.printStackTrace();
        }
        return null;
    }
}
