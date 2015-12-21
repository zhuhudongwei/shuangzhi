package com.wechat.model;

public class Source {

	protected int id;
	protected String code;
	protected String name;
	protected String addr;
	protected String lng;
	protected String lat;
	protected int corpid;
	protected int gropid;
	protected int appdistance;
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
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public int getCorpid() {
		return corpid;
	}
	public void setCorpid(int corpid) {
		this.corpid = corpid;
	}
	public int getGropid() {
		return gropid;
	}
	public void setGropid(int gropid) {
		this.gropid = gropid;
	}
	public int getAppdistance() {
		return appdistance;
	}
	public void setAppdistance(int appdistance) {
		this.appdistance = appdistance;
	}
	
}
