package com.data.mapper.query;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.data.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserQueryMapper {
    @Select("select * from User;")
    List<User> getUser();
}
