package com.app.bean;

public class WaybillInfoBean {

	protected String waybillCode;
	protected String deliveryTime;
	protected String transportationLine;
	protected String carrierName;
	protected String yacheTime;//压车时间
	protected String outTime;//超时时间
	
	public String getTransportationLine() {
		return transportationLine;
	}
	public void setTransportationLine(String transportationLine) {
		this.transportationLine = transportationLine;
	}
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	
	public String getWaybillCode() {
		return waybillCode;
	}
	public void setWaybillCode(String waybillCode) {
		this.waybillCode = waybillCode;
	}
	public String getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getYacheTime() {
		return yacheTime;
	}
	public void setYacheTime(String yacheTime) {
		this.yacheTime = yacheTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
}
