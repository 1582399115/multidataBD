package com.data.mapper.query;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Insert;

public interface UserInsertMapper {
    @Insert("insert into user(name) values('李')")
    Integer insertUser();

}
