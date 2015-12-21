package com.wechat.model;

/**
 * 承运商
 * @author zdw
 *
 */
public class Supplier {

	protected int id;
	protected String code;
	protected String name;
	protected String linkman;
	protected String tel;
	protected String fax;
	protected String addr;
	protected String ishide;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getIshide() {
		return ishide;
	}
	public void setIshide(String ishide) {
		this.ishide = ishide;
	}
	
}
