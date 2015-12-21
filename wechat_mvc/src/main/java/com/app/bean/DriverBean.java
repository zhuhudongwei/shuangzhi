package com.app.bean;


public class DriverBean {

	protected String name;//司机名称
	protected String tel;//手机号码
	protected String idcard;//身份证号
	protected String carNo;//车号
	protected String trucktype;//车型
	public synchronized String getName() {
		return name;
	}
	public synchronized void setName(String name) {
		this.name = name;
	}
	public synchronized String getTel() {
		return tel;
	}
	public synchronized void setTel(String tel) {
		this.tel = tel;
	}
	public synchronized String getIdcard() {
		return idcard;
	}
	public synchronized void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getTrucktype() {
		return trucktype;
	}
	public void setTrucktype(String trucktype) {
		this.trucktype = trucktype;
	}
}
