package com.qiaoge.barbecue.dal.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(MyBatisConfigurer.class)
public class MyBatisMapperScannerConfig {
	private static final Logger log = LoggerFactory.getLogger(MyBatisMapperScannerConfig.class);
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
    	String beanName = "sqlSessionFactory";
    	String basePackage = "com.qiaoge.barbecue.dal";
    	log.debug("setSqlSessionFactoryBeanName(" + beanName + ")");
    	log.debug("setBasePackage(" + basePackage + ")");
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        //获取之前注入的beanName为sqlSessionFactory的对象
        mapperScannerConfigurer.setSqlSessionFactoryBeanName(beanName);
        //指定xml配置文件的路径
        mapperScannerConfigurer.setBasePackage(basePackage);
        return mapperScannerConfigurer;
    }
}
