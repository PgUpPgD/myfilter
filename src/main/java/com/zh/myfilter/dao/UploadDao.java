package com.zh.myfilter.dao;

import com.zh.myfilter.entity.UploadEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadDao {
    int insertImage(UploadEntity uploadEntity);
}
