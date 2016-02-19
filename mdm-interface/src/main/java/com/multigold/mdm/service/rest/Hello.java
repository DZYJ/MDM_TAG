package com.multigold.mdm.service.rest;

import java.util.HashMap;
import java.util.Map;

public class Hello {
	
	private String hello = "hello";
	
	private Map<String,String> values = new HashMap<String,String>();
	
	public Hello(){
		values.put("key1", "value1");
		values.put("key2", "value2");
		values.put("key3", "value3");
		values.put("key4", "value4");
	}

	public String getHello() {
		return hello;
	}

	public void setHello(String hello) {
		this.hello = hello;
	}

	public Map<String, String> getValues() {
		return values;
	}

	public void setValues(Map<String, String> values) {
		this.values = values;
	}
	
	

}
