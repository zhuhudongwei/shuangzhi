package com.wechat.model;

import java.util.Date;

public class Appsubmit {

	protected int id;
	protected Integer driverid;
	protected String tel;
	protected Double lng;
	protected Double lat;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
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
	public synchronized Date getMakedate() {
		return makedate;
	}
	public synchronized void setMakedate(Date makedate) {
		this.makedate = makedate;
	}
	public Integer getDriverid() {
		return driverid;
	}
	public void setDriverid(Integer driverid) {
		this.driverid = driverid;
	}
	
}
