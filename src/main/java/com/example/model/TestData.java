package com.example.model;

import java.io.Serializable;
import java.util.Map;

/**
 * author: zf
 * Date: 2017-11-1  9:08
 * Description:
 */
public class TestData implements Serializable{

	String facadeName;
	String methodName;
	Map<String,Object> paramMap;

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getFacadeName() {
		return facadeName;
	}

	public void setFacadeName(String facadeName) {
		this.facadeName = facadeName;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	@Override
	public String toString() {
		return "TestData{" +
				"facadeName='" + facadeName + '\'' +
				", methodName='" + methodName + '\'' +
				", paramMap=" + paramMap +
				'}';
	}
}
