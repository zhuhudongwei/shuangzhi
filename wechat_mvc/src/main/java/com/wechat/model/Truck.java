package com.wechat.model;

import java.util.Date;

public class Truck {

	protected int ID;
	protected String CODE;
	protected String NAME;
	protected String SUPPLIERNO;
	protected String TRUCKTYPE;
	protected String MAXWEIGHT;
	protected String DRIVERTEL;
	protected Date MAKEDATE;
	protected Integer GROPID;
	protected Integer CORPID;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getCODE() {
		return CODE;
	}
	public void setCODE(String cODE) {
		CODE = cODE;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getSUPPLIERNO() {
		return SUPPLIERNO;
	}
	public void setSUPPLIERNO(String sUPPLIERNO) {
		SUPPLIERNO = sUPPLIERNO;
	}
	public String getTRUCKTYPE() {
		return TRUCKTYPE;
	}
	public void setTRUCKTYPE(String tRUCKTYPE) {
		TRUCKTYPE = tRUCKTYPE;
	}
	public String getMAXWEIGHT() {
		return MAXWEIGHT;
	}
	public void setMAXWEIGHT(String mAXWEIGHT) {
		MAXWEIGHT = mAXWEIGHT;
	}
	public String getDRIVERTEL() {
		return DRIVERTEL;
	}
	public void setDRIVERTEL(String dRIVERTEL) {
		DRIVERTEL = dRIVERTEL;
	}
	public Date getMAKEDATE() {
		return MAKEDATE;
	}
	public void setMAKEDATE(Date mAKEDATE) {
		MAKEDATE = mAKEDATE;
	}
	public Integer getGROPID() {
		return GROPID;
	}
	public void setGROPID(Integer gROPID) {
		GROPID = gROPID;
	}
	public Integer getCORPID() {
		return CORPID;
	}
	public void setCORPID(Integer cORPID) {
		CORPID = cORPID;
	}
}
