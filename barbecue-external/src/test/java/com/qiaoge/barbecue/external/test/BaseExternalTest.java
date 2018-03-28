package com.qiaoge.barbecue.external.test;

import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qiaoge.barbecue.external.ConsumerConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
@ContextConfiguration(classes = { ConsumerConfiguration.class })
@ComponentScan(basePackages = { "com.qiaoge.barbecue.external" })
public class BaseExternalTest {

}
