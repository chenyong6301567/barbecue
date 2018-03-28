package com.qiaoge.barbecue.biz.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qiaoge.barbecue.biz.config.cache.CacheConfiguration;
import com.qiaoge.barbecue.biz.manager.TestsManager;
import com.qiaoge.barbecue.dal.config.DataSourceConfiguration;
import com.qiaoge.barbecue.dal.config.MyBatisConfigurer;
import com.qiaoge.barbecue.dal.config.MyBatisMapperScannerConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
@ContextConfiguration(classes = { DataSourceConfiguration.class, MyBatisConfigurer.class,
		MyBatisMapperScannerConfig.class, CacheConfiguration.class })
@ComponentScan(basePackages = { "com.qiaoge.barbecue.utils", "com.qiaoge.barbecue.biz","com.qiaoge.barbecue.middle" })
public class BaseBizTest {
	@Autowired
	private TestsManager testsManager;

	@Test
	public void testUpdate() {
		testsManager.testUpdate(1);
	}

}
