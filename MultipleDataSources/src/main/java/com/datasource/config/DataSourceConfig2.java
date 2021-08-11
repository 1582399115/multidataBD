package com.datasource.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

public class DataSourceConfig2 {
    @Bean("datasource2")
    //读取application.yml中的配置参数映射成为一个对象
    @ConfigurationProperties(prefix = "spring.datasource.datasource2")
    public DataSource getDatasource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean("db2SqlSessionFactory")
    public SqlSessionFactory db1SqlSessionFactory(@Qualifier("datasource2") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/datasource/dao/insert/*.xml"));
        return bean.getObject();
    }

    @Bean("db1SqlSessionTemplate")
    public SqlSessionTemplate ddb1SqlSessionTemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
/*    在 service 层中根据不同的业务注入不同的 dao 层
    如果是主从复制- -读写分离：比如 db1 中负责增删改，db2 中负责查询。但是需要注意的是负责增删改的数据库必须是主库（master）*/
}
