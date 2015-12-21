package com.app.bean;


public class Trajectory {
	protected int driverid;
	protected String tel;
	protected double lng;
	protected double lat;
	protected String time;
	public synchronized int getDriverid() {
		return driverid;
	}
	public synchronized void setDriverid(int driverid) {
		this.driverid = driverid;
	}
	public synchronized String getTel() {
		return tel;
	}
	public synchronized void setTel(String tel) {
		this.tel = tel;
	}
	public synchronized double getLng() {
		return lng;
	}
	public synchronized void setLng(double lng) {
		this.lng = lng;
	}
	public synchronized double getLat() {
		return lat;
	}
	public synchronized void setLat(double lat) {
		this.lat = lat;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
