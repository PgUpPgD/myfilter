package com.zh.myfilter.service;

import com.zh.myfilter.entity.UploadEntity;
import com.zh.myfilter.utils.JsonUtil;

public interface UploadService {
    JsonUtil insertImage(UploadEntity uploadEntity);
}
