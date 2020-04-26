package com.zh.myfilter.dao;

import com.zh.myfilter.litterDemo.entity.Order;
import com.zh.myfilter.litterDemo.entity.Rol;
import com.zh.myfilter.litterDemo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LitterUserDao {
    Order findOne();
    User findsAll();
    List<Rol> findMany();
}
