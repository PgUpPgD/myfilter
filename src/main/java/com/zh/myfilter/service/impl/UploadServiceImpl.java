package com.zh.myfilter.service.impl;

import com.zh.myfilter.dao.UploadDao;
import com.zh.myfilter.entity.UploadEntity;
import com.zh.myfilter.service.UploadService;
import com.zh.myfilter.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private UploadDao uploadDao;

    @Override
    public JsonUtil insertImage(UploadEntity uploadEntity) {
        int i = uploadDao.insertImage(uploadEntity);
        if (i > 0){
            return JsonUtil.setOk();
        }
        return JsonUtil.setERROR();
    }
}
