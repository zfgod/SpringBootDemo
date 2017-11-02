package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.model.TestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * author: zf
 * Date: 2017-10-31  20:37
 * Description:
 */
@Controller
@RequestMapping("/banktest")
public class AppRefelctController {

	private static final Logger logger = LoggerFactory.getLogger(AppRefelctController.class);

	@RequestMapping(value = "/single", method = {RequestMethod.POST})
	@ResponseBody
	public String singleTest(@RequestBody TestData req, HttpServletRequest request) throws Exception {
		logger.info("接收参数：{}", req);
		//
		String facadeName = req.getFacadeName();
		String methodName = req.getMethodName();

		Map<String,Object> paramMap = req.getParamMap();

		// 获取接口
		Class clz = Class.forName(facadeName);
		// 获取方法
		Object obj = clz.newInstance();
		Method m = obj.getClass().getDeclaredMethod(methodName, Map.class);
		// 调用方法
		Object o = m.invoke(obj, paramMap);

		return o.toString();
	}
}
