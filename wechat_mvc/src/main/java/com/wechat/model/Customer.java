package com.wechat.model;

public class Customer {

	protected int id;

	protected String code;

	protected String name;

	protected String linkman;

	protected String tel;

	protected String fax;

	protected String addr;

	protected String lat;

	protected String lng;

	protected Integer corpid;

	protected Integer gropid;

	protected Integer appdistance;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public Integer getCorpid() {
		return corpid;
	}
	public void setCorpid(Integer corpid) {
		this.corpid = corpid;
	}
	public Integer getGropid() {
		return gropid;
	}
	public void setGropid(Integer gropid) {
		this.gropid = gropid;
	}
	public Integer getAppdistance() {
		return appdistance;
	}
	public void setAppdistance(Integer appdistance) {
		this.appdistance = appdistance;
	}
	
}
