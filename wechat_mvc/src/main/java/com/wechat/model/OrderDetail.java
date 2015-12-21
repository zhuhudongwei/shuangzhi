package com.wechat.model;

import java.util.Date;

/**
 * 运单
 * @author zdw
 *
 */
public class OrderDetail {

	protected int id;
	protected String loadingnote;
	protected String orderno;
	protected String customerno;
	protected String customername;
	protected String transportationno;
	protected String transportation;
	protected String productname;
	protected String productno;
	protected Integer planquantity;
	protected String batch;
	protected String sourcename;
	protected String sourceno;
	protected String transmode;
	protected Date shipdate;
	protected Date eta;
	protected String deliverynote;
	protected String insulationstays;
	protected Integer gropid;
	protected Integer corpid;
	protected Integer maker;
	protected Date makedate;
	protected Date editdate;
	protected String unit;
	protected String sfilename;
	protected Integer dtlid;
	protected String supplierno;
	protected String suppliername;
	protected Date loadindate;
	protected Date loadoutdate;
	protected Date unloadindate;
	protected Date unloadoutdate;
	protected Integer loadnum;
	protected Integer signnum;
	protected String deliveryaddress;
	protected String updreason;
	protected Date upddate;
	protected String updname;
	protected Integer pcsign;
	
	protected String shipdate_str;
	protected String loadindate_str;
	protected String loadoutdate_str;
	protected String unloadindate_str;
	protected String unloadoutdate_str;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoadingnote() {
		return loadingnote;
	}
	public void setLoadingnote(String loadingnote) {
		this.loadingnote = loadingnote;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getCustomerno() {
		return customerno;
	}
	public void setCustomerno(String customerno) {
		this.customerno = customerno;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getTransportationno() {
		return transportationno;
	}
	public void setTransportationno(String transportationno) {
		this.transportationno = transportationno;
	}
	public String getTransportation() {
		return transportation;
	}
	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getProductno() {
		return productno;
	}
	public void setProductno(String productno) {
		this.productno = productno;
	}
	public Integer getPlanquantity() {
		return planquantity;
	}
	public void setPlanquantity(Integer planquantity) {
		this.planquantity = planquantity;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getSourcename() {
		return sourcename;
	}
	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}
	public String getSourceno() {
		return sourceno;
	}
	public void setSourceno(String sourceno) {
		this.sourceno = sourceno;
	}
	public String getTransmode() {
		return transmode;
	}
	public void setTransmode(String transmode) {
		this.transmode = transmode;
	}
	public Date getShipdate() {
		return shipdate;
	}
	public void setShipdate(Date shipdate) {
		this.shipdate = shipdate;
	}
	public Date getEta() {
		return eta;
	}
	public void setEta(Date eta) {
		this.eta = eta;
	}
	public String getDeliverynote() {
		return deliverynote;
	}
	public void setDeliverynote(String deliverynote) {
		this.deliverynote = deliverynote;
	}
	public String getInsulationstays() {
		return insulationstays;
	}
	public void setInsulationstays(String insulationstays) {
		this.insulationstays = insulationstays;
	}
	public Integer getGropid() {
		return gropid;
	}
	public void setGropid(Integer gropid) {
		this.gropid = gropid;
	}
	public Integer getCorpid() {
		return corpid;
	}
	public void setCorpid(Integer corpid) {
		this.corpid = corpid;
	}
	public Integer getMaker() {
		return maker;
	}
	public void setMaker(Integer maker) {
		this.maker = maker;
	}
	public Date getMakedate() {
		return makedate;
	}
	public void setMakedate(Date makedate) {
		this.makedate = makedate;
	}
	public Date getEditdate() {
		return editdate;
	}
	public void setEditdate(Date editdate) {
		this.editdate = editdate;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getSfilename() {
		return sfilename;
	}
	public void setSfilename(String sfilename) {
		this.sfilename = sfilename;
	}
	public Integer getDtlid() {
		return dtlid;
	}
	public void setDtlid(Integer dtlid) {
		this.dtlid = dtlid;
	}
	public String getSupplierno() {
		return supplierno;
	}
	public void setSupplierno(String supplierno) {
		this.supplierno = supplierno;
	}
	public String getSuppliername() {
		return suppliername;
	}
	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}
	public Date getLoadindate() {
		return loadindate;
	}
	public void setLoadindate(Date loadindate) {
		this.loadindate = loadindate;
	}
	public Date getLoadoutdate() {
		return loadoutdate;
	}
	public void setLoadoutdate(Date loadoutdate) {
		this.loadoutdate = loadoutdate;
	}
	public Date getUnloadindate() {
		return unloadindate;
	}
	public void setUnloadindate(Date unloadindate) {
		this.unloadindate = unloadindate;
	}
	public Date getUnloadoutdate() {
		return unloadoutdate;
	}
	public void setUnloadoutdate(Date unloadoutdate) {
		this.unloadoutdate = unloadoutdate;
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
	public String getDeliveryaddress() {
		return deliveryaddress;
	}
	public void setDeliveryaddress(String deliveryaddress) {
		this.deliveryaddress = deliveryaddress;
	}
	public String getUpdreason() {
		return updreason;
	}
	public void setUpdreason(String updreason) {
		this.updreason = updreason;
	}
	public Date getUpddate() {
		return upddate;
	}
	public void setUpddate(Date upddate) {
		this.upddate = upddate;
	}
	public String getUpdname() {
		return updname;
	}
	public void setUpdname(String updname) {
		this.updname = updname;
	}
	public Integer getPcsign() {
		return pcsign;
	}
	public void setPcsign(Integer pcsign) {
		this.pcsign = pcsign;
	}
	public String getShipdate_str() {
		return shipdate_str;
	}
	public void setShipdate_str(String shipdate_str) {
		this.shipdate_str = shipdate_str;
	}
	public String getLoadindate_str() {
		return loadindate_str;
	}
	public void setLoadindate_str(String loadindate_str) {
		this.loadindate_str = loadindate_str;
	}
	public String getLoadoutdate_str() {
		return loadoutdate_str;
	}
	public void setLoadoutdate_str(String loadoutdate_str) {
		this.loadoutdate_str = loadoutdate_str;
	}
	public String getUnloadindate_str() {
		return unloadindate_str;
	}
	public void setUnloadindate_str(String unloadindate_str) {
		this.unloadindate_str = unloadindate_str;
	}
	public String getUnloadoutdate_str() {
		return unloadoutdate_str;
	}
	public void setUnloadoutdate_str(String unloadoutdate_str) {
		this.unloadoutdate_str = unloadoutdate_str;
	}
	
}
