package com.data.service;

import com.data.mapper.insert.OrderQueryMapper;
import com.data.mapper.query.UserInsertMapper;
import com.data.mapper.query.UserQueryMapper;
import com.data.pojo.Order;
import com.data.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTestImpl {
    @Autowired
    private OrderQueryMapper orderQueryMapper;
    @Autowired
    private UserInsertMapper userInsertMapper;
    @Autowired
    private UserQueryMapper userQueryMapper;

    public List<User> getUser() {
        return userQueryMapper.getUser();
    }

    public List<Order> getOrder() {
        return orderQueryMapper.getOrder();
    }

    public Integer insertUser() {
        return userInsertMapper.insertUser();
    }
}
