package com.wechat.utils;

import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 微信消息通用类
 */
public class WechatMessage {
	public static final String TYPE_TEXT = "text";
	public static final String TYPE_EVENT = "event";
	public static final String TYPE_LOCATION = "location";//地理位置
	
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	public static final String EVENT_TYPE_LOCATION = "LOCATION";
	
	public static WechatMessage newInstance(Map<String, String> params) {
		String type = params.get("MsgType");
		String toUserName = params.get("ToUserName");
		String fromUserName = params.get("FromUserName");
		String createTime = params.get("CreateTime");
		
		WechatMessage message = new WechatMessage(type, toUserName, fromUserName, createTime);
		
		if (type.equals(TYPE_TEXT)) {
			message.content = params.get("Content");
		} else if (type.equals(TYPE_EVENT)) {
			message.event = params.get("Event");
			message.eventKey = params.get("EventKey");
			message.latitude = params.get("Latitude");
			message.longitude = params.get("Longitude");
			message.precision = params.get("Precision");
		} else {
			throw new UnsupportedOperationException("Unsupported message type: " + type);
		}
		return message;
	}
	
	private String type;
	private String fromUser;
	private String toUser;
	private int createTime;
	private String content;
	private String event;
	private String eventKey;
	
	private String latitude;//地理位置纬度
	private String longitude;//地理位置经度
	private String precision;//地理位置精度
	
	private boolean verifyFlag;//flag= true 验证通过 false 验证失败
	
	public WechatMessage() {}
	
	public WechatMessage(String type, String fromUser, String toUser, String createTimeString) {
		this.type = type;
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.createTime = Integer.parseInt(createTimeString);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
	

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

	public boolean getVerifyFlag() {
		return verifyFlag;
	}

	public void setVerifyFlag(boolean verifyFlag) {
		this.verifyFlag = verifyFlag;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public boolean isTextMessage() {
		return this.type.equals(TYPE_TEXT);
	}
	
	public boolean isEventMessage() {
		return this.type.equals(TYPE_EVENT);
	}
	
	public boolean isHelpMessage() {
		return content.toLowerCase().startsWith("h");
	}
	
	public boolean isSearchMessage() {
		return content.toLowerCase().startsWith("s");
	}
	
	public boolean isLocationMessage() {
		return this.event.equals(EVENT_TYPE_LOCATION);
	}
	
	public boolean isSubscribeMessage() {
		return this.event.equals(EVENT_TYPE_SUBSCRIBE);
	}
	

	//TODO 根据自定义菜单修改
	public boolean isMenuEventMessage() {
		return eventKey.toUpperCase().equals("GDI");
	}
	
}
