package com.qiaoge.barbecue.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qiaoge.barbecue.utils.Constants;

/**
 * 服务测试页面
 *
 * @author cy
 * @Title: 服务测试页面
 * @Description: 服务测试页面
 * @date 2018年3月27日 下午19:22:29
 */
@RestController
public class IndexController {

	/**
	 * 服务测试页面
	 *
	 * @return Result
	 * @Title home
	 * @respbody {"code":1,"message":"成功","data":null,"error":false,"success":true}
	 * @author cy
	 * @Description 服务测试页面
	 * @date 2018/3/17 9:22:29
	 */
	@RequestMapping("/")
	String home(HttpServletRequest request, HttpServletResponse response) {
		return Constants.WELCOME;
	}

}
