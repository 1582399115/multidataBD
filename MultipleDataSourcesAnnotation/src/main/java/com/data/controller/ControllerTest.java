package com.data.controller;

import com.data.pojo.Order;
import com.data.pojo.User;
import com.data.service.ServiceTestImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Api(tags = "测试控制类")
public class ControllerTest {
    @Autowired
    private ServiceTestImpl serviceTest;
    @GetMapping("/getUser")
    @ApiOperation(value = "获得全部用户")
    public List<User> getUser() {
        List<User> users = null;
        users = serviceTest.getUser();
        return users;
    }
    @GetMapping("/getOrder")
    @ApiOperation(value = "获得全部订单")
    public List<Order> getOrder() {
        List<Order> orders = null;
        orders = serviceTest.getOrder();
        return orders;
    }
    @GetMapping("/insertUser")
    @ApiOperation(value = "添加用户")
    public String insertUser() {
        serviceTest.insertUser();
        return "成功";
    }
}
