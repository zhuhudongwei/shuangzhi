package com.wechat.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 微信的工具类
 * @author Sting
 * @created 下午3:55:32
 */
public class WechatUtils {
	private static final Logger logger = Logger.getLogger("xmlInfo");
	
	
	private static final String PROJECT = 
			"http://app.paireach.com";

			
	private static final String API_PROJECT = PROJECT + "/wechat_api";
	private static final String BACKEND_PROJECT = PROJECT + "/wechat_backend";
	
	public static final String API_PROJECT_AUTN = API_PROJECT + "/operator/loginGetOpenId";
	public static final String BACKEND_PROJECT_AUTH = BACKEND_PROJECT + "/intelligent/getwechatOpenId";
			
	/*
	 * 公众号的唯一标识
	 */
	private static final String WECHAT_APPID = 
			"wxb79b293dd69fa688";
	
	public static String getWechatAppid() {
		return WECHAT_APPID;
	}

	/*
	 * 密钥(公众微信平台下可以找到)
	 */
	private static final String WECHAT_APPSECRET = 
			"72eab02428d53a98f1c2b03769c99006";

			
	public static String getWechatAppSecret(){
		return WECHAT_APPSECRET;
	}
	
	/*
	 * 获取openId的url
	 * appid:公众号的唯一标识;
	 * redirect_uri:跳转回客户端的url
	 */
	public static String openIdUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?"
			+ "appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
	
	/*
	 * 微信授权URL
	 * 参数说明 :
	 * appid:公众号的唯一标识;
	 * edirect_uri:授权后重定向的回调链接地址;
	 * response_type:返回类型，请填写code
	 * scope:应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
	 * state:重定向后会带上state参数，开发者可以填写任意参数值
	 * #wechat_redirect	直接在微信打开链接，可以不填此参数。做页面302重定向时候，必须带此参数
	 */
	public static String  codeRequestURL = "https://open.weixin.qq.com/connect/oauth2/authorize?"
			+ "appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	
	/*
	 * 通过code换取网页授权access_token URL
	 * 参数说明 :
	 * appid:公众号的唯一标识;
	 * secret：公众号的appsecret密钥(公众微信平台下可以找到)
	 * code：根据微信授权URL获取的code参数
	 * grant_type：填写为authorization_code
	 */
	public static String  accessTokenRequestURL = 
			"https://api.weixin.qq.com/sns/oauth2/access_token?"
			+ "appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
	public static String tokenRequestURL = "https://api.weixin.qq.com/cgi-bin/token?"
			+ "grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	public static String getUserInfoAfterAuth = "https://api.weixin.qq.com/sns/userinfo?"
			+ "access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	public static String userInfoRequestURL = "https://api.weixin.qq.com/cgi-bin/user/info?"
			+ "access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	/*
	 * jsapi_ticket,公众号用于调用微信JS接口的临时票据
	 * 参数说明 :
	 * 	access_token:公众号的全局唯一票据
	 * 	type:jsapi
	 */
	public static String ticketRequestURL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?"
			+ "access_token=ACCESS_TOKEN&type=jsapi";
	/*
	 * 生成签名的时间戳
	 */
	public static int currentTimestamp;
	/*
	 * 生成签名的随机串
	 */
	public static String noncestr = "asd123";
	/*
	 * accesstoken常量，缓存
	 */
	public static String access_token;
	/*
	 * jsapi_ticket常量，缓存
	 */
	public static String js_ticket;

	
	/**
	 * 将input用sha1算法hash
	 * @param input 需要hash的字符串
	 * @return hash过的字符串
	 * @author Sting
	 * @created 2013-4-23 下午4:14:30
	 */
	public static String sha1(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			byte[] result = md.digest(input.getBytes());
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < result.length; i++) {
	            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.error("exception threw.", e);
			return "";
		}
	}
	
	/**
	 * 判断是否从微信的服务器发过来的消息。一是在认证开发者时用，另外，从微信服务器发过来的每条消息都会带有认证的信息，我们可以选择是否需要做相应的认证。
	 * @param expectedSignature 期望的认证字符串
	 * @param timestamp 时间戳
	 * @param nonce 附加字符串
	 * @param token 
	 * @return
	 */
	public static boolean isFromWechatServer(String expectedSignature, String timestamp, String nonce, String token) {
		List<String> list = Arrays.asList(new String[] {token,timestamp, nonce});
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for (String s : list) {
			sb.append(s);
		}
		return sha1(sb.toString()).equals(expectedSignature);
		
	}
	
	/**
	 * 从HTTP request的输入流里取出微信消息的xml。
	 * @param in
	 * @return
	 */
	public static WechatMessage parseMessage(InputStream in) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(in);
			String xml = scanner.useDelimiter("\\A").next();
			WechatMessage message = composeMessageFromXml(xml);
			return message;
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
		
	}
	
	/**
	 * 从xml里解析出微信的消息。此方法用来解析微信服务器传过来的消息。
	 * @param xml
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static WechatMessage composeMessageFromXml(String xml) {
		try {
			Document doc = DocumentHelper.parseText(xml);
			try {
				xml = new String(xml.getBytes("GBK"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("exception threw.", e);
			}
			doc.setXMLEncoding("UTF-8");
			Element root = doc.getRootElement();
			Iterator<Element> it = root.elementIterator();
			Map<String, String> params = new HashMap<String, String>();
			while (it.hasNext()) {
				Element node = it.next();
				String name = node.getName();
				String text = node.getTextTrim();
				params.put(name, text);
			}
			return WechatMessage.newInstance(params);
		} catch (DocumentException e) {
			logger.error("exception threw.", e);
			return null;
			
		}
		
	}
	/**
	 * 从IO流里解析出微信的消息。此方法用来解析微信服务器传过来的消息。
	 * @param xml
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static WechatMessage composeMessageFromInputStream(InputStream input) {
		try {
			SAXReader reader = new SAXReader();
			Document doc = reader.read(input);//dom4j解析输入流中的xml文档
			
			doc.setXMLEncoding("UTF-8");
			Element root = doc.getRootElement();
			Iterator<Element> it = root.elementIterator();
			Map<String, String> params = new HashMap<String, String>();
			while (it.hasNext()) {
				Element node = it.next();
				String name = node.getName();
				String text = node.getTextTrim();
				params.put(name, text);
			}
			return WechatMessage.newInstance(params);
		} catch (DocumentException e) {
			logger.error("exception threw.", e);
			return null;
		}
	}
	
	/**
	 * 
	 * 获得微信授权URL（可以获得对应的code）
	 * @param edirect_uri 授权后重定向的回调链接地址;
	 * @param scope 应用授权作用域，snsapi_base（不弹出授权页面，直接跳转，只能获取用户openid）snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
	 * @param state 重定向后会带上state参数 开发者可以填写任意参数值 (随便一个数字，这里填1)
	 * @return 
	 * @author hehaiyang
	 * @create 2014年9月15日 下午2:25:10
	 */
    public static String getCodeRequest(String edirect_uri,String scope,String state){
        String result = null;
        result  = codeRequestURL.replace("APPID",WechatUtils.WECHAT_APPID);
        result  = result.replace("REDIRECT_URI",urlEnodeUTF8(edirect_uri));
        result = result.replace("SCOPE", scope);
        result = result.replace("STATE", state);
        return result;
    }
    
    /**
     * 
     * 通过code换取网页授权access_token　URL
     * @param Code
     * @return
     * @author hehaiyang
     * @create 2014年9月15日 下午3:01:18
     */
    public static String getAccessTokenRequest(String code)
    {
    	String result = null;
    	result  = accessTokenRequestURL.replace("APPID",WechatUtils.WECHAT_APPID);
    	result  = result.replace("SECRET",WechatUtils.WECHAT_APPSECRET);
    	result  = result.replace("CODE",code);

        return result;
    }
    
    /**
     * 
     * 通过code换取网页授权access_token
     * @param result
     * @return
     * @author hehaiyang
     * @throws JSONException 
     * @create 2014年9月15日 下午3:01:18
     */
    public static OAuthToken getAccessToken(String result)
    {
		OAuthToken oAuthToken = null;
		try{
			oAuthToken = new OAuthToken();
			JSONObject jsonObject = new JSONObject(result);
			oAuthToken.setAccessToken(jsonObject.getString("access_token"));
			oAuthToken.setExpiresIn(jsonObject.getInt("expires_in"));
			oAuthToken.setRefreshToken(jsonObject.getString("refresh_token"));
			oAuthToken.setOpenid(jsonObject.getString("openid"));
			oAuthToken.setScope(jsonObject.getString("scope"));
		}catch(JSONException e){
			e.printStackTrace();
			return null;
		}
        return oAuthToken;
    }
    
    /**
     * 取access_token
     * @return
     * @author quguanglei
     * @create 2014/12/23 14:30:00
     */
    public static String getAccessTokenRequest() throws Exception
    {
    	String requestURL = null;
    	requestURL = tokenRequestURL.replace("APPID", WechatUtils.WECHAT_APPID);
    	requestURL = requestURL.replace("APPSECRET", WechatUtils.WECHAT_APPSECRET);
    	logger.debug("requestURL:" + requestURL);
		String resultJSONStr = WechatUtils.getResultFromHttpUrl(requestURL);
		logger.debug("resultJSONStr:" + resultJSONStr);
		net.sf.json.JSONObject resultJsonObject = net.sf.json.JSONObject.fromObject(resultJSONStr);
		AccessTokenBase accessToken= (AccessTokenBase)net.sf.json.JSONObject.toBean(resultJsonObject, AccessTokenBase.class);
		logger.debug("access_token:" + accessToken.getAccess_token());
		return accessToken.getAccess_token();	
    }
    
    /**
     * 获取用户基本信息
     * @param openId
     * @return
     * @author quguanglei
     * @create 2014/12/23 14:50:00
     */
    public static UserBaseInformation getUserBaseInformation(String openId) throws Exception
    {
    	logger.debug("openId:" + openId);
    	String requestURL = null;
    	requestURL = userInfoRequestURL.replace("ACCESS_TOKEN", WechatUtils.getAccessTokenRequest());
    	requestURL = requestURL.replace("OPENID", openId);
    	logger.debug("requestURL:" + requestURL);
    	String resultJSONStr = WechatUtils.getResultFromHttpUrl(requestURL);
    	logger.debug("resultJSONStr:" + resultJSONStr);
    	net.sf.json.JSONObject resultJsonObject = net.sf.json.JSONObject.fromObject(resultJSONStr);
		UserBaseInformation userBaseInformation= (UserBaseInformation)net.sf.json.JSONObject.toBean(resultJsonObject, UserBaseInformation.class);
		logger.info("nickname:" + userBaseInformation.getNickname());
		return userBaseInformation;
    }
    
    /**
     * 获取jsapi_ticket（有效期7200秒），公众号用于调用微信JS接口的临时票据
     * @return String
     * quguanglei
     * 2015年1月19日 下午5:21:04
     */
    public static String getTicketRequest() throws Exception{
    	String requestURL = null;
    	String token = access_token;
    	if(token == null){
    		access_token = getAccessTokenRequest();
    		token = access_token;
    	}
    	if(token == null || "".equals(token)){
			return null;
		}
    	requestURL = ticketRequestURL.replace("ACCESS_TOKEN", token);
    	logger.info("requestURL:" + requestURL);
		String resultJSONStr = WechatUtils.getResultFromHttpUrl(requestURL);
		if(resultJSONStr == null || "".equals(resultJSONStr)){
			return null;
		}
		logger.info("resultJSONStr:" + resultJSONStr);
		net.sf.json.JSONObject resultJsonObject = net.sf.json.JSONObject.fromObject(resultJSONStr);
		String ticket = (String) resultJsonObject.get("ticket");
		logger.info("ticket:" + ticket);
		return ticket;
    }
    /**
     * 计算 签名
     * @param url 完整的路径
     * @param timestamp 时间戳
     * @param noncestr 随机字符串
     * @return String
     * @throws Exception 
     * quguanglei
     * 2015年1月19日 下午5:23:20
     * 用指定参数替换jsapi_ticket=%s&noncestr=%s&timestamp=%s&url=%s
     * 用sha1加密
     */
    public static String getSignature(String noncestr, Integer timestamp, String url) throws Exception{
    	//jsapi_ticket
    	String jsapi_ticket = js_ticket;
    	if(jsapi_ticket == null){
    		js_ticket = getTicketRequest();
    		jsapi_ticket = js_ticket;
    	}
    	StringBuffer sb = new StringBuffer();
    	sb.append("jsapi_ticket=").append(jsapi_ticket).append("&noncestr=").append(noncestr)
    		.append("&timestamp=").append(timestamp).append("&url=").append(url);
    	logger.info("getSignatureParam(before sha1):" + sb.toString());
    	String signature = sha1(sb.toString());
    	return signature;
    }
    
    
    /**
     * 
     * 将String编码（UTF-8）
     * @param str
     * @return
     * @author hehaiyang
     * @create 2014年9月15日 下午2:39:06
     */
    public static String urlEnodeUTF8(String str){
        String result = str;
        try {
            result = URLEncoder.encode(str,"UTF-8");
        } catch (Exception e) {
        	logger.error("exception threw.", e);
        }
        return result;
    }
    
    /**
     * 
     * 发送某个请求，获得请求返回值
     * @param httpUrl
     * @return
     * @author hehaiyang
     * @create 2014年9月15日 下午3:53:19
     */
    public static String getResultFromHttpUrl(String httpUrl) {
		StringBuffer buffer = new StringBuffer();
/*		System.setProperty("sun.net.client.defaultConnectTimeout", "8000");
		System.setProperty("sun.net.client.defaultReadTimeout", "8000");*/
		try {
			URL newUrl = new URL(httpUrl);
			HttpURLConnection hConnect = (HttpURLConnection) newUrl
					.openConnection();
//			hConnect.setConnectTimeout(10000);// 10s内连不上就断开
			hConnect.setDoOutput(true);
			hConnect.setDoInput(true);
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					hConnect.getInputStream(), "UTF-8"));
			
			int ch;
			for (int length = 0; (ch = rd.read()) > -1
					&& (200500 <= 0 || length < 200500); length++)
				buffer.append((char) ch);
			rd.close();
			hConnect.disconnect();
			return buffer.toString().trim();
		} catch (Exception e) {
			logger.error("exception threw.", e);
			return "";
		}
	}
}
