package com.zhang.oauther2.mysql.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Copyright 深圳金雅福控股集团有限公司
 * @Author: zhangfanjun
 * @Date 2021/11/17
 * @Version: 1.0
 */
@ConditionalOnBean(BaseDataSourceProperties.class)
@Configuration
public class MybatisDataSourceConfig {
    @Autowired
    BaseDataSourceProperties baseDataSourceProperties;

//    @Value("${mybatis-plus.mapper-locations}")
//    private String mapperLoacations;

    @Bean(name = "baseDataSource")
    @Primary
    public DataSource baseDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(baseDataSourceProperties.getDriverClassName());
        dataSource.setUrl(baseDataSourceProperties.getUrl());
        dataSource.setUsername(baseDataSourceProperties.getUsername());
        dataSource.setPassword(baseDataSourceProperties.getPassword());
        return dataSource;
    }


    @Bean(name = "baseSqlSessionFactory")
    @Primary
    public SqlSessionFactory baseSqlSessionFactory(@Qualifier("baseDataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        //数据源地址
        bean.setDataSource(dataSource);
        // 设置mybatis的主配置文件
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] mybatisConfigXml = resolver.getResources(baseDataSourceProperties.getMapperLoacations());
//        Resource[] mybatisConfigXml = resolver.getResources(mapperLoacations);
        bean.setMapperLocations(mybatisConfigXml);
        return bean.getObject();
    }

    @Bean(name = "baseTransactionManager")
    @Primary
    public DataSourceTransactionManager baseTransactionManager(@Qualifier("baseDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "baseSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate baseSqlSessionTemplate(@Qualifier("baseSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
