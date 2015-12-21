package com.wechat.model;

import java.util.Date;

public class DriverTruck {
	
	protected int id;
	
	protected int truckId;
	
	protected int driverId;
	
	protected String truckName;
	
	protected String truckType;
	
	protected String tel;
	
	protected String driverName;
	
	protected String card;
	
	protected Date createdate;
	
	protected String supplierno;
	
	protected int driverstatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTruckId() {
		return truckId;
	}

	public void setTruckId(int truckId) {
		this.truckId = truckId;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public String getTruckName() {
		return truckName;
	}

	public void setTruckName(String truckName) {
		this.truckName = truckName;
	}

	public String getTruckType() {
		return truckType;
	}

	public void setTruckType(String truckType) {
		this.truckType = truckType;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getSupplierno() {
		return supplierno;
	}

	public void setSupplierno(String supplierno) {
		this.supplierno = supplierno;
	}

	public int getDriverstatus() {
		return driverstatus;
	}

	public void setDriverstatus(int driverstatus) {
		this.driverstatus = driverstatus;
	}
	
}
