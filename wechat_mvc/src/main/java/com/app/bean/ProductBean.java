package com.app.bean;

public class ProductBean {

	protected String productno;//产品编码
	protected String productname;//产品名称
	protected Integer planquantity;//计划数量
	protected Integer loadnum;//实装数量
	protected Integer signnum;//实收数量
	public synchronized String getProductno() {
		return productno;
	}
	public synchronized void setProductno(String productno) {
		this.productno = productno;
	}
	public synchronized String getProductname() {
		return productname;
	}
	public synchronized void setProductname(String productname) {
		this.productname = productname;
	}
	public synchronized Integer getPlanquantity() {
		return planquantity;
	}
	public synchronized void setPlanquantity(Integer planquantity) {
		this.planquantity = planquantity;
	}
	public Integer getLoadnum() {
		return loadnum;
	}
	public void setLoadnum(Integer loadnum) {
		this.loadnum = loadnum;
	}
	public Integer getSignnum() {
		return signnum;
	}
	public void setSignnum(Integer signnum) {
		this.signnum = signnum;
	}
	
}
