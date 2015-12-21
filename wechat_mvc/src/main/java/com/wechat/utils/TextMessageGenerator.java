package com.wechat.utils;

public abstract class TextMessageGenerator extends ResponseMessageGenerator {
	@Override
	public void appendBody(StringBuilder sb, WechatMessage request) throws Exception {
		sb.append(generateContent(request));
	}
	protected abstract String generateContent(WechatMessage request) throws Exception;
}
