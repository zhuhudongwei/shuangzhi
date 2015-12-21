package com.wechat.utils;

public class WelcomeMessageGenerator extends TextMessageGenerator {
	//private StringBuffer HELP_MSG;
	@Override
	protected String generateContent(WechatMessage message) {
		try {
			String REGISTER_MSG =  ResponseMessage.textMessage(message, "欢迎关注百纯科技，无锡百纯科技有限公司经营软件、网站以及手机APP定制开发，微营销辅助 !");
			return new String(REGISTER_MSG.getBytes("UTF-8"), "UTF-8");
		} catch (Exception e) {
			return "error";
		}
	}

}
