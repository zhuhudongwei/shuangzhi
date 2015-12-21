package com.wechat.utils;

public class ResponseMessage {
	/**
	 * 
	 * 图文信息返回值
	 * @return
	 * @author chenjie
	 * @create 2014年10月11日 上午10:34:36
	 */
	public static String newsMessage(WechatMessage message,String title[],String Description[],String PicUrl[],String Url[]){
		StringBuilder sb = viewHeaderMessage(message, "news");
		int count = title.length;
		sb.append(String.format("<ArticleCount><![CDATA[%d]]></ArticleCount>",count));
		sb.append("<Articles>");
		for(int i = 0 ; i< count ;i++){
			sb.append("<item>");
			sb.append(String.format("<Title><![CDATA[%s]]></Title>",title[i]));
			sb.append(String.format("<Description><![CDATA[%s]]></Description>",Description[i]));
			sb.append(String.format("<PicUrl><![CDATA[%s]]></PicUrl>",PicUrl[i]));
			sb.append(String.format("<Url><![CDATA[%s]]></Url>",Url[i]));
			sb.append("</item>");
		}
		sb.append("</Articles>");
		return sb.toString();
	}
	
	/**
	 * 
	 * 返回文本消息
	 * @param message
	 * @param content
	 * @return
	 * @author chenjie
	 * @create 2014年10月11日 上午10:50:13
	 */
	public static String textMessage(WechatMessage message,String content){
		StringBuilder sb = viewHeaderMessage(message, "text");
		sb.append(String.format("<Content><![CDATA[%s]]></Content>",content));
		return sb.toString();
	}
	
	/**
	 * 
	 * 这个是头部 
	 * @param message
	 * @param type
	 * @return
	 * @author chenjie
	 * @create 2014年10月11日 上午10:44:33
	 */
	public static StringBuilder viewHeaderMessage(WechatMessage message,String type){
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append(String.format("<ToUserName><![CDATA[%s]]></ToUserName>", message.getToUser()));
		sb.append(String.format("<FromUserName><![CDATA[%s]]></FromUserName>", message.getFromUser()));
		sb.append(String.format("<CreateTime>%d</CreateTime>", System.currentTimeMillis()/1000));
		sb.append(String.format("<MsgType><![CDATA[%s]]></MsgType>", type));
		return sb;
	}
}
