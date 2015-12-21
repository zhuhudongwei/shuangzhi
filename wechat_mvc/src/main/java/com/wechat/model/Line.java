package com.wechat.model;

public class Line {

	protected int ID;
	protected String CODE;
	protected String NAME;
	protected String DCNO;
	protected Integer LINEDIST;
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
	public String getDCNO() {
		return DCNO;
	}
	public void setDCNO(String dCNO) {
		DCNO = dCNO;
	}
	public Integer getLINEDIST() {
		return LINEDIST;
	}
	public void setLINEDIST(Integer lINEDIST) {
		LINEDIST = lINEDIST;
	}
	
}
