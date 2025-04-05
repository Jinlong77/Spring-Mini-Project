package org.kshrd.gamifiedhabittracker.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.kshrd.gamifiedhabittracker.typehandler.UUIDTypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MyBatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        // Register the custom type handler
        sessionFactory.setTypeHandlers(new UUIDTypeHandler());

        return sessionFactory.getObject();
    }
}