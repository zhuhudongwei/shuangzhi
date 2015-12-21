package com.wechat.utils;

import java.io.UnsupportedEncodingException;

/**回复推送信息通用类
 * 针对公众号的消息推送，根据推送提示进行回复信息的处理
 */
public class ReplyMessageGenerator extends TextMessageGenerator {
	@Override
	protected String generateContent(WechatMessage message) throws Exception{
		String messageContent = "";//key
		String operationName ="";
		StringBuffer HELP_MSG = new StringBuffer();
		operationName = HELP_MSG.toString();
		if(message.getType().equals("text")){
			messageContent = message.getContent();
		}else if (message.getType().equals("event")) {
			messageContent = message.getEventKey();
		} else {
			throw new UnsupportedOperationException("Unsupported message type: " + message.getType());
		}

		try {
			return new String(operationName.getBytes("UTF-8"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "error";
		}
	}
}
