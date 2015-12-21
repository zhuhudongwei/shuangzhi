package com.wechat.model;

import java.util.List;

/***
 * 微信客服
 * @author zdw
 *
 */
public class WechatCustomer {
	public static final short RESPONSE_ISRESPONSE_NO = 0;
	public static final short RESPONSE_ISRESPONSE_YES = 1;

	protected long customer_id;
	protected String open_id;//微信openid每个用户都是唯一的
	protected String open_name;//微信名称
	protected long create_time;//提问时间
	protected String content;//内容
	protected String create_time_str;
	protected short isResponse;
	
	protected List<WechatCustomerResponse> wcrs;
	public synchronized long getCustomer_id() {
		return customer_id;
	}
	public synchronized void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}
	public synchronized String getOpen_id() {
		return open_id;
	}
	public synchronized void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
	public synchronized long getCreate_time() {
		return create_time;
	}
	public synchronized void setCreate_time(long create_time) {
		this.create_time = create_time;
	}
	public synchronized String getContent() {
		return content;
	}
	public synchronized void setContent(String content) {
		this.content = content;
	}
	public synchronized List<WechatCustomerResponse> getWcrs() {
		return wcrs;
	}
	public synchronized void setWcrs(List<WechatCustomerResponse> wcrs) {
		this.wcrs = wcrs;
	}
	public synchronized String getCreate_time_str() {
		return create_time_str;
	}
	public synchronized void setCreate_time_str(String create_time_str) {
		this.create_time_str = create_time_str;
	}
	public synchronized short getIsResponse() {
		return isResponse;
	}
	public synchronized void setIsResponse(short isResponse) {
		this.isResponse = isResponse;
	}
	public String getOpen_name() {
		return open_name;
	}
	public void setOpen_name(String open_name) {
		this.open_name = open_name;
	}
	
}
