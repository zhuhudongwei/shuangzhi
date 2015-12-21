package com.app.bean;

import java.util.List;

public class ViewBillInfoBean {

	protected String waybillCode;//运单编号
	protected String waybillstate;//运单状态
	protected String anomalyWay;//运单是否异常
	protected String motoristName;//司机姓名
	protected String motoristMobile;//手机号
	protected String carNo;//车号
	protected String trucktype;//车型
	protected String carrierName;//承运商
	protected String transportationLine;//运输线路
	protected String deliveryFactory;//发送工厂
	protected String takeDeliveryFactory;//收货工厂
	protected String takeDeliveryName;//收货人
	protected String takeDeliveryMobile;//收货人联系方式
	protected String expectedDeliveryTime;//预计发货时间
	protected String expectedArriveTime;//预计到达时间
	protected String arriveLoadingTime;//到达装货时间
	protected String loadingOverTime;//装货完成时间
	protected String expectedTakeDeliveryTime;//到达收货工厂时间
	protected String takeDeliveryOverTime;//收货完成时间
	protected String productname;//产品名称
	protected String path;//距离
	protected String address;//地址
	protected List<ProductBean> productBeans;//货物明细

	public String getWaybillCode() {
		return waybillCode;
	}
	public void setWaybillCode(String waybillCode) {
		this.waybillCode = waybillCode;
	}
	public String getWaybillstate() {
		return waybillstate;
	}
	public void setWaybillstate(String waybillstate) {
		this.waybillstate = waybillstate;
	}
	public String getMotoristName() {
		return motoristName;
	}
	public void setMotoristName(String motoristName) {
		this.motoristName = motoristName;
	}
	public String getMotoristMobile() {
		return motoristMobile;
	}
	public void setMotoristMobile(String motoristMobile) {
		this.motoristMobile = motoristMobile;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	public String getTransportationLine() {
		return transportationLine;
	}
	public void setTransportationLine(String transportationLine) {
		this.transportationLine = transportationLine;
	}
	public String getDeliveryFactory() {
		return deliveryFactory;
	}
	public void setDeliveryFactory(String deliveryFactory) {
		this.deliveryFactory = deliveryFactory;
	}
	public String getTakeDeliveryFactory() {
		return takeDeliveryFactory;
	}
	public void setTakeDeliveryFactory(String takeDeliveryFactory) {
		this.takeDeliveryFactory = takeDeliveryFactory;
	}
	public String getTakeDeliveryName() {
		return takeDeliveryName;
	}
	public void setTakeDeliveryName(String takeDeliveryName) {
		this.takeDeliveryName = takeDeliveryName;
	}
	public String getTakeDeliveryMobile() {
		return takeDeliveryMobile;
	}
	public void setTakeDeliveryMobile(String takeDeliveryMobile) {
		this.takeDeliveryMobile = takeDeliveryMobile;
	}
	public String getExpectedDeliveryTime() {
		return expectedDeliveryTime;
	}
	public void setExpectedDeliveryTime(String expectedDeliveryTime) {
		this.expectedDeliveryTime = expectedDeliveryTime;
	}
	public String getExpectedArriveTime() {
		return expectedArriveTime;
	}
	public void setExpectedArriveTime(String expectedArriveTime) {
		this.expectedArriveTime = expectedArriveTime;
	}
	public String getArriveLoadingTime() {
		return arriveLoadingTime;
	}
	public void setArriveLoadingTime(String arriveLoadingTime) {
		this.arriveLoadingTime = arriveLoadingTime;
	}
	public String getLoadingOverTime() {
		return loadingOverTime;
	}
	public void setLoadingOverTime(String loadingOverTime) {
		this.loadingOverTime = loadingOverTime;
	}
	public String getExpectedTakeDeliveryTime() {
		return expectedTakeDeliveryTime;
	}
	public void setExpectedTakeDeliveryTime(String expectedTakeDeliveryTime) {
		this.expectedTakeDeliveryTime = expectedTakeDeliveryTime;
	}
	public String getTakeDeliveryOverTime() {
		return takeDeliveryOverTime;
	}
	public void setTakeDeliveryOverTime(String takeDeliveryOverTime) {
		this.takeDeliveryOverTime = takeDeliveryOverTime;
	}
	public synchronized String getProductname() {
		return productname;
	}
	public synchronized void setProductname(String productname) {
		this.productname = productname;
	}
	public synchronized String getPath() {
		return path;
	}
	public synchronized void setPath(String path) {
		this.path = path;
	}
	public synchronized String getAnomalyWay() {
		return anomalyWay;
	}
	public synchronized void setAnomalyWay(String anomalyWay) {
		this.anomalyWay = anomalyWay;
	}
	public synchronized String getTrucktype() {
		return trucktype;
	}
	public synchronized void setTrucktype(String trucktype) {
		this.trucktype = trucktype;
	}
	public synchronized List<ProductBean> getProductBeans() {
		return productBeans;
	}
	public synchronized void setProductBeans(List<ProductBean> productBeans) {
		this.productBeans = productBeans;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
