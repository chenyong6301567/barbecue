package com.qiaoge.barbecue.facade.test;

import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qiaoge.barbecue.biz.config.cache.CacheConfiguration;
import com.qiaoge.barbecue.dal.config.DataSourceConfiguration;
import com.qiaoge.barbecue.dal.config.MyBatisConfigurer;
import com.qiaoge.barbecue.dal.config.MyBatisMapperScannerConfig;
import com.qiaoge.barbecue.facade.ProviderConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
@ContextConfiguration(classes={DataSourceConfiguration.class,
		MyBatisConfigurer.class,
		MyBatisMapperScannerConfig.class,
		CacheConfiguration.class,
		ProviderConfiguration.class})
//这个注解的class必须在上层package中
//@SpringBootTest(classes=SpringBootBizMain.class)
//这个注解一定要放到最下面
@ComponentScan(basePackages={"com.qiaoge.barbecue.biz","com.qiaoge.barbecue.facade"})

public class BaseFacadeTest {
	
}
