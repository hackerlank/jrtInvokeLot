package com.jrt.invokeLot.test.util;

import java.util.ResourceBundle;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.jrt.invokeLot.util.IHttp;
import com.jrt.invokeLot.util.URLEncoder;

/**
 * 
 * @classname:   UserOperationUtil
 * @description: 用户操作的类包括注册、登录
 * @author:      徐丽
 * @date:        2010-11-8 上午10:50:21
 *
 */
public class UserOperationUtil {
	private static final Logger logger = Logger.getLogger(UserOperationUtil.class);
	private static ResourceBundle rbint = ResourceBundle.getBundle("ruyicai");
	private static String baseUrl = rbint.getString("baseUrl");
	
	/**
	 * 
	 * @Title:  register
	 * @Description: 调用jrtLot注册的方法
	 * @param: mobileId 手机号码
	 * @param: password 密码
	 * @param: cardNumber 身份证号码
	 * @param: agencyNo 渠道号码(wap、web、客户端)
	 * @param: CHANNEL 用户带来的渠道号
	 * @return:   返回的error_code
	 * @throws Exception 
	 * @exception:
	 */
	public static String register(JSONObject map) throws Exception {
		String action = "user.do";
		String method = "register";
		String reValue = "";
		IHttp http = new IHttp();
		String para = "";
		JSONObject paras = new JSONObject();
		paras.put("mobile_code", map.get("mobile_code"));
		paras.put("user_password", map.get("password"));
		paras.put("Id", map.get("cardNumber"));
		paras.put("channel_id", map.get("agencyNo")); // 渠道号
		paras.put("channel", map.get("CHANNEL"));
		para = URLEncoder.encode(paras.toString());

		String re = http.getViaHttpConnection(baseUrl + action + "?method="
				+ method + "&jsonString=" + para);
		
		logger.info(baseUrl + action + "?method=" + method + "&jsonString="
				+ para);
		logger.info("调用注册返回:" + re);
		
		if (re != null && re.length() > 0) {
			reValue = re;
			JSONObject obj = JSONObject.fromObject(re);
			reValue = obj.getString("error_code");
		}
	
		return reValue;
	}
	
	/**
	 * 
	 * @Title:  login
	 * @Description: 调用jrtLot登录
	 * @param: mobileId 手机号码
	 * @param: password 密码
	 * @return:   返回的error_code
	 * @throws Exception 
	 * @exception:
	 */
	public static String login(JSONObject map) throws Exception {
		String action = "user.do";
		String method = "login";
		String reValue = "";
		IHttp http = new IHttp();
		String para = "";
		JSONObject paras = new JSONObject();
		paras.put("mobile_code", map.get("mobile_code"));
		paras.put("password", map.get("password"));
		para = URLEncoder.encode(paras.toString());

		String re = http.getViaHttpConnection(baseUrl + action + "?method="
				+ method + "&jsonString=" + para);
		logger.info("返回的结果串re:"+re);
		
		if (re != null && re.length() > 0) {
			reValue = re;
			JSONObject obj = JSONObject.fromObject(re) ;
			reValue = obj.getString("error_code");
			if ("0000".equals(reValue)) {
				reValue = obj.getString("sessionid");
			}
		}
		
		return reValue;
	}
}
