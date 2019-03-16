package com.asiainfo.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class JsonUtil {
	
	/**
	 * java对象转json字符串
	 * @param obj
	 * @return
	 */
	public static String objectToJsonString(Object obj){
		return JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * json字符串转map<String,String>
	 * @param json
	 * @return
	 */
	public static Map<String,String> jsonStringToMap(String json){
		Map<String,String> map = (Map<String,String>) JSON.parseObject(json, new TypeReference<Map<String,String>>(){});
		return map;
	}
	
	/**
	 * java对象转map<String,String>
	 * @param obj
	 * @return
	 */
	public static Map<String,String> objectToMap(Object obj) throws Exception{
		String json = objectToJsonString(obj);
		Map<String, String> map = jsonStringToMap(json);
		return map;
	}
}
