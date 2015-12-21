package com.wechat.controllor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wechat.model.WechatCustomer;
import com.wechat.service.WechatCustomerService;
import com.wechat.utils.DateUtil;
import com.wechat.utils.OAuthToken;
import com.wechat.utils.ResponseMessageGenerator;
import com.wechat.utils.ResponseMessageGeneratorFactory;
import com.wechat.utils.UserBaseInformation;
import com.wechat.utils.WechatMessage;
import com.wechat.utils.WechatUtils;

/***
 * 智能回复--微信认证模块
 * @author zdw
 *
 */
@Controller
@RequestMapping("/intelligent")
public class IntelligentResponseControllor {
	@Autowired
	private WechatCustomerService wechatCustomerServiceImpl;
	
	@Autowired
	private WechatCustomerService wechatCustomerService;

	/**
	 * 启动页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/openToIndex", method = RequestMethod.GET)
	public synchronized String openToIndex(Model model, HttpServletRequest request) throws Exception {
		Map<String, Object> attrs = new HashMap<String, Object>();
		String localPath = WechatUtils.BACKEND_PROJECT_AUTH;
		String weixinUrl = WechatUtils.codeRequestURL
				.replace("APPID", WechatUtils.getWechatAppid())
				.replace("REDIRECT_URI", URLEncoder.encode(localPath))
				.replace("SCOPE", "snsapi_userinfo")
				.replace("STATE", "1");
		attrs.put("weixinUrl", weixinUrl);
//		String weixinUrl = WechatUtils.openIdUrl
//				.replace("APPID", WechatUtils.getWechatAppid())
//				.replace("REDIRECT_URI", URLEncoder.encode(localPath));
//		attrs.put("weixinUrl", weixinUrl);
		model.addAllAttributes(attrs);
		return "/wechatIndex";
	}
	
	@RequestMapping(value = "/getwechatOpenId", method = RequestMethod.GET)
	public synchronized String getwechatOpenId(Model model, HttpSession httpSession, HttpServletRequest request)throws Exception{
		Map<String, Object> attrs = new HashMap<String, Object>();
		// 获得code
		String code = request.getParameter("code");
		Date date = new Date();
	    int hour = date.getHours();//小时 
	    if(hour < 9 || hour > 18){
	    	attrs.put("sysmsg", "0");
	    }else{
	    	attrs.put("sysmsg", "您好，很高兴为您服务，请问有什么可以帮到您的吗？");
	    }
		if(code != null){
			if(code.length() != 0){
		System.out.println("code:"+code);
				// 获得请求access_token的url
				String accessTokenRequest = WechatUtils.getAccessTokenRequest(code);
		System.out.println("accessTokenRequest:"+accessTokenRequest);
				// 请求access_token的url,获得对应的json String
				String resultFromHttpUrl = WechatUtils.getResultFromHttpUrl(accessTokenRequest);
		System.out.println("resultFromHttpUrl:"+resultFromHttpUrl);
				// 获得accessToken对象(包含了微信账号信息)
				OAuthToken accessToken = WechatUtils.getAccessToken(resultFromHttpUrl);
		System.out.println("accessToken:"+accessToken);
				// 获取OpenId
				String weixinCode = accessToken.getOpenid();
				httpSession.setAttribute("WEIXINCODE", weixinCode);
		System.out.println(weixinCode);
				// 查询微信昵称
				String userInfoUrl = WechatUtils.getUserInfoAfterAuth
						.replace("ACCESS_TOKEN", accessToken.getAccessToken())
						.replace("OPENID", weixinCode);
		System.out.println("userInfoHttpRequest:" + userInfoUrl);
				String resultJSONStr = WechatUtils.getResultFromHttpUrl(userInfoUrl);
		System.out.println("userInfoFromHttp:" + resultJSONStr);
				net.sf.json.JSONObject resultJsonObject = net.sf.json.JSONObject.fromObject(resultJSONStr);
				// 微信用户全部基本信息对象
				UserBaseInformation userBaseInformation= (UserBaseInformation) 
						net.sf.json.JSONObject.toBean(resultJsonObject, UserBaseInformation.class);
				String nickname = userBaseInformation.getNickname();
				httpSession.setAttribute("WEIXINNAME", nickname);
		System.out.println("========================nickname==>"+nickname);
				attrs.put("weixinCode", weixinCode);
				attrs.put("weixinName", nickname);
			}
			
		}
//	    if(code != null){
//			if(code.length() != 0){
//		System.out.println("code:"+code);
//				// 获得请求access_token的url
//				String accessTokenRequest = WechatUtils.getAccessTokenRequest(code);
//		System.out.println("accessTokenRequest:"+accessTokenRequest);
//				// 请求access_token的url,获得对应的json String
//				String resultFromHttpUrl = WechatUtils.getResultFromHttpUrl(accessTokenRequest);
//		System.out.println("resultFromHttpUrl:"+resultFromHttpUrl);
//				// 获得accessToken对象(包含了微信账号信息)
//				OAuthToken accessToken = WechatUtils.getAccessToken(resultFromHttpUrl);
//		System.out.println("accessToken:"+accessToken);
//				// 获取OpenId
//				String weixinCode = accessToken.getOpenid();
//				httpSession.setAttribute("WEIXINCODE", weixinCode);
//		System.out.println(weixinCode);
//				attrs.put("weixinCode", weixinCode);
//				attrs.put("weixinName", "");
//			}
//			
//		}
		model.addAllAttributes(attrs);
		return "/wechatOAuth";
	}
	
	@RequestMapping(value="saveQuestion", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object saveQuestion(String weixinCode, String weixinName, String question, HttpSession httpSession, HttpServletResponse response)throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
//		weixinCode = (String)httpSession.getAttribute("WEIXINCODE");
		result.put("weixinCode", weixinCode);
		result.put("question", question);
		System.out.println(result.toString());
		Date date = new Date();
	    int hour = date.getHours();//小时 
	    WechatCustomer wc = new WechatCustomer();
	    wc.setOpen_id(weixinCode);
	    wc.setOpen_name(weixinName);
	    wc.setCreate_time(System.currentTimeMillis());
	    wc.setContent(question);
	    long customer_id = wechatCustomerServiceImpl.addWechatCustomer(wc);
	    if(hour < 9 || hour > 18){
//	    	WechatCustomerResponse wcr = new WechatCustomerResponse();
//	    	wcr.setCustomer_id(customer_id);
//	    	wcr.setResponse_content("微客服人工服务时间为周一至周日09：00--17：00");
//	    	wcr.setResponse_name("系统客服");
//	    	wcr.setResponse_time(System.currentTimeMillis());
//	    	wcr.setIssend(WechatCustomerResponse.RESPONSE_ISSEND_YES);
//	    	wechatCustomerServiceImpl.addWechatCustomerResponse(wcr);
	    	result.put("systemMSG", "您好，智能客服人工回复时间为周一至周五上午9：30-下午18：30，您可以在对话框中给我们留言，我们将尽快回复您的问题或建议，谢谢！");
//	    	wechatCustomerServiceImpl.updateWechatCustomerByState(customer_id);
	    }
		String systime = DateUtil.convertLongToDateString2(System.currentTimeMillis(), "HH:mm:ss");
	    result.put("systime", systime);
		return result;
	}
	
	private static Logger logger = Logger.getLogger("xmlInfo");
	private static final String TOKEN = "szwccharttstoken";
	private ResponseMessageGeneratorFactory responseMessageGeneratorFactory = new ResponseMessageGeneratorFactory();

	@RequestMapping(value = "/serve")
	public void serve(String signature, String timestamp, String nonce, String echostr, HttpServletRequest request, HttpServletResponse response){
		String messageContent = "";
		String responseString = "error";
		//如果是GET方法，则判断是否是微信请求验证，验证成功，则返回echostr以确认验证成功；
		if (request.getMethod().equals("GET")) {
			//微信请求验证
			logger.debug("signature is" +signature+".timestamp is" +timestamp+".nonce is" +nonce+".TOKEN is" +TOKEN+".echostr is" +echostr);
			if (WechatUtils.isFromWechatServer(signature, timestamp, nonce,
					TOKEN)) {	
				logger.debug("isFromWechatServer is ture!");
				responseString = echostr;
			} 
		//如果是POST方法，则首先验证微信请求验证信息，若验证通过则解析来自微信的消息
		} else {
			if (WechatUtils.isFromWechatServer(signature, timestamp, nonce,
					TOKEN)) {
				System.out.println("getMethod is not GET!");
				try {
					request.setCharacterEncoding("UTF-8");
					//解析来自微信的消息（XML）
					WechatMessage message = WechatUtils.parseMessage(request.getInputStream());
					String toUser = message.getToUser();
					String fromUser = message.getFromUser();//发送方帐号（一个OpenID)	
					System.out.println("fromUser："+fromUser);
					if(message.getType().equals("text")){
						messageContent = message.getContent();
						System.out.println("messageContent："+messageContent);
					}else if (message.getType().equals("event")) {
						messageContent = message.getEventKey();
					}
					
					ResponseMessageGenerator generator = responseMessageGeneratorFactory.newInstance(message);
					responseString = generator.generate(message);
					System.out.println("responseString："+responseString);
				} catch (Exception e) {
					logger.error(e.toString());
				}
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.print(responseString);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value="updateMsgIsSend", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Object updateMsgIsSend(long response_id, HttpSession httpSession, HttpServletResponse response)throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		wechatCustomerService.updateWechatCustomerResponseByState(response_id);
		return result;
	}
}
