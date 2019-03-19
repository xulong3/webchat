package com.asiainfo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class HttpUtil {


	
	public static String sendPost(String url,Map<String,String> params) throws Exception {
		
		HttpClient client = new HttpClient();
		//String url = "http://localhost:8081/webchat-portal/uploadUserRootDir";
		
		PostMethod method = new PostMethod(url);
		
		Set<String> set = params.keySet();
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for (String s : set) {
			NameValuePair p = new NameValuePair(s,params.get(s));
			list.add(p);
		}
		NameValuePair[] array={};
		
		method.addParameters(list.toArray(array));
 
			// Execute the method.
			int statusCode = client.executeMethod(method);
 
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}
 
			// Read the response body.
			byte[] responseBody = method.getResponseBody();
 
			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary data
		
		
			method.releaseConnection();
			return new String(responseBody, "utf-8");
		
	}

}
