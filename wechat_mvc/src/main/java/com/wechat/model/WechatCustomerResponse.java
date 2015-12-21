package com.wechat.model;

public class WechatCustomerResponse {
	
	public static final short RESPONSE_ISSEND_NO = 0;
	public static final short RESPONSE_ISSEND_YES = 1;

	protected long response_id;
	protected long customer_id;
	protected long response_time;
	protected String response_content;
	protected String response_name;
	protected short issend;
	
	protected String response_time_str;
	public synchronized long getResponse_id() {
		return response_id;
	}
	public synchronized void setResponse_id(long response_id) {
		this.response_id = response_id;
	}
	public synchronized long getCustomer_id() {
		return customer_id;
	}
	public synchronized void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}
	public synchronized long getResponse_time() {
		return response_time;
	}
	public synchronized void setResponse_time(long response_time) {
		this.response_time = response_time;
	}
	public synchronized String getResponse_content() {
		return response_content;
	}
	public synchronized void setResponse_content(String response_content) {
		this.response_content = response_content;
	}
	public synchronized String getResponse_name() {
		return response_name;
	}
	public synchronized void setResponse_name(String response_name) {
		this.response_name = response_name;
	}
	public synchronized String getResponse_time_str() {
		return response_time_str;
	}
	public synchronized void setResponse_time_str(String response_time_str) {
		this.response_time_str = response_time_str;
	}
	public synchronized short getIssend() {
		return issend;
	}
	public synchronized void setIssend(short issend) {
		this.issend = issend;
	}
	
}
