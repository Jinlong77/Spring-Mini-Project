package org.kshrd.gamifiedhabittracker.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.kshrd.gamifiedhabittracker.handler.UUIDTypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.UUID;

@Configuration
public class MyBatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.getTypeHandlerRegistry().register(UUID.class, UUIDTypeHandler.class);
        sessionFactory.setConfiguration(configuration);
        return sessionFactory.getObject();
    }
}