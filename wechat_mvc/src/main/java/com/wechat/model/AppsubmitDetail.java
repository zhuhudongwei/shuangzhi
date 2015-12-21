package com.wechat.model;

import java.util.Date;

public class AppsubmitDetail {
	protected int id;
	
	protected int driverid;
	
	protected String tel;
	
	protected double lng;
	
	protected double lat;
	
	protected String speed;
	
	protected String direction;
	
	protected String addrstr;
	
	protected Date makedate;
	
	protected String province;
	
	protected String city;
	
	protected String district;
	
	protected String loctype;
	
	protected String ip;
	
	protected String sloadingnote;
	
	protected Integer perminute;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDriverid() {
		return driverid;
	}

	public void setDriverid(int driverid) {
		this.driverid = driverid;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getAddrstr() {
		return addrstr;
	}

	public void setAddrstr(String addrstr) {
		this.addrstr = addrstr;
	}

	public Date getMakedate() {
		return makedate;
	}

	public void setMakedate(Date makedate) {
		this.makedate = makedate;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getLoctype() {
		return loctype;
	}

	public void setLoctype(String loctype) {
		this.loctype = loctype;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSloadingnote() {
		return sloadingnote;
	}

	public void setSloadingnote(String sloadingnote) {
		this.sloadingnote = sloadingnote;
	}

	public Integer getPerminute() {
		return perminute;
	}

	public void setPerminute(Integer perminute) {
		this.perminute = perminute;
	}
	
}
