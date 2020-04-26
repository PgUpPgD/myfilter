package com.zh.myfilter.utils;

import com.zh.myfilter.exception.MyException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class UploadImageUtil {

    public static String upload(MultipartFile file){
        String path = "F:/images";
//        String path = "/usr/local/apache-tomcat-8.5.23/webapps/images";
        File pare = new File(path);
        if (!pare.getParentFile().exists()){
            pare.getParentFile().mkdir();
        }
        String rand = UUID.randomUUID().toString().replace("-","");
        String name = file.getOriginalFilename();
        name = rand + "_" +name;
        try {
            file.transferTo(new File(pare,name));
        }catch (Exception e){
            throw new MyException(1,"上传失败");
        }
        return name;
    }

}
