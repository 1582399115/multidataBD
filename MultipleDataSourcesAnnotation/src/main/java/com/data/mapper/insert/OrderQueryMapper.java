package com.data.mapper.insert;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.data.pojo.Order;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderQueryMapper {
    @DS("db2")
    @Select("select * from order")
    List<Order> getOrder();
}
