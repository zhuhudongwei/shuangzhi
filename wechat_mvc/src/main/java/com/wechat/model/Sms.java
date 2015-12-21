package com.wechat.model;

import java.util.Date;

public class Sms {

	protected int ID;
	protected String TITLE;
	protected String FROMID;
	protected String TOID;
	protected String CONTENT;
	protected Date MAKEDATE;
	protected Integer MAKER;
	protected String makedateStr;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	public String getFROMID() {
		return FROMID;
	}
	public void setFROMID(String fROMID) {
		FROMID = fROMID;
	}
	public String getTOID() {
		return TOID;
	}
	public void setTOID(String tOID) {
		TOID = tOID;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public Date getMAKEDATE() {
		return MAKEDATE;
	}
	public void setMAKEDATE(Date mAKEDATE) {
		MAKEDATE = mAKEDATE;
	}
	public Integer getMAKER() {
		return MAKER;
	}
	public void setMAKER(Integer mAKER) {
		MAKER = mAKER;
	}
	public String getMakedateStr() {
		return makedateStr;
	}
	public void setMakedateStr(String makedateStr) {
		this.makedateStr = makedateStr;
	}
}
