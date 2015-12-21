package com.wechat.utils;

public abstract class ResponseMessageGenerator {
	private StringBuilder sb = new StringBuilder();
	public String generate(WechatMessage request) throws Exception{
		appendBody(sb, request);
		appendFooter(sb, request);
		return sb.toString();
	}
	private void appendFooter(StringBuilder sb, WechatMessage request) {
		sb.append("</xml>");
	}
	protected abstract void appendBody(StringBuilder sb, WechatMessage request) throws Exception;
}
