package com.zh.myfilter.controller;

import com.zh.myfilter.entity.UploadEntity;
import com.zh.myfilter.service.UploadService;
import com.zh.myfilter.utils.JsonUtil;
import com.zh.myfilter.utils.UploadImageUtil;
import com.zh.myfilter.vo.UploadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping("upload/uploadImg")
    public JsonUtil upload(UploadDto dto){
        Integer uid = dto.getUid();
        MultipartFile file = dto.getFile();
        String name = UploadImageUtil.upload(file);
        UploadEntity uploadEntity = new UploadEntity();
        uploadEntity.setUid(uid);
        uploadEntity.setName(name);
        uploadEntity.setUrl("F:/images");
//        uploadEntity.setUrl("/images" + name);
        return uploadService.insertImage(uploadEntity);
    }


}
