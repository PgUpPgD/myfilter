package com.zh.myfilter.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadDto {
    private Integer uid;
    private MultipartFile file;
}
