package com.qiaoge.barbecue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.treeleafj.xdoc.boot.EnableXDoc;

@EnableXDoc
@SpringBootApplication
@ComponentScan(basePackages = { "com.qiaoge.barbecue" })
public class SpringBootMain {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootMain.class, args);
	}
	
}

