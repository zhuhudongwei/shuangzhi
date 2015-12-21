package com.wechat.utils;

import java.util.HashMap;
import java.util.Map;

public class CookieParser {

	public static Map<String, Object> parser(String cookie){
		Map<String, Object> vs = new HashMap<String, Object>();
		if(null != cookie && !cookie.isEmpty()){
			String[] cks = cookie.split(":");
			vs.put("account", cks[0]);
			vs.put("password", cks[1]);
			vs.put("department", cks[2]);
		}
		return vs;
	}
}
