package com.app.bean;

public class JoinorderBean {

	protected int id;//主运单id
	protected String loadingnote;//运单号
	protected Integer joinid; //子运单id
	protected String carrierName;//承运商
	protected String transportationLine;//运输线路
	protected int type;//运单类型1：主运单；2：子运单

	public synchronized String getLoadingnote() {
		return loadingnote;
	}
	public synchronized void setLoadingnote(String loadingnote) {
		this.loadingnote = loadingnote;
	}
	public synchronized Integer getJoinid() {
		return joinid;
	}
	public synchronized void setJoinid(Integer joinid) {
		this.joinid = joinid;
	}
	public synchronized String getCarrierName() {
		return carrierName;
	}
	public synchronized void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	public synchronized String getTransportationLine() {
		return transportationLine;
	}
	public synchronized void setTransportationLine(String transportationLine) {
		this.transportationLine = transportationLine;
	}
	public synchronized int getId() {
		return id;
	}
	public synchronized void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

}
