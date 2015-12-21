package com.wechat.model;

import java.util.Date;
import java.util.List;

public class Orderhead {

	protected int id;
	protected String ordernohead;
	protected Date billdate;
	protected String senderno;	//发货人编号
	protected String sendername;//发货人
	protected String sendtel;	//发货人电话
	protected String fax;		
	protected String guestno;	//客户，发货方编号
	protected String guestname;	//发货方
	protected String receiveman;//接收人
	protected String receivetel;//接收人电话
	protected String supplierno;//承运方编号
	protected String suppliername;//承运方
	protected Integer gropid;
	protected Integer corpid;
	protected Integer maker;
	protected Date makedate;
	protected Integer editor;
	protected Date editdate;
	protected Integer approver;	//审批人
	protected Date approvedate;	//审批时间
	protected Integer status;	//状态
	protected String memo;
	protected String sfilename;
	protected String subject;	//标题
	protected Integer sysorderno;
	protected String rejectmemo;
	protected Date rejectdate;	//拒绝时间
	protected Date receivedate;	//收货时间
	protected Date driverreceivedate;	//司机接收时间
	protected String truckno;	//车牌号
	protected String trucktype;	//车型
	protected String drivername;//司机名
	protected String tel;		//司机电话
	protected String idcard;
	protected Date controldate;
	protected String picktime;	//
	protected String updreason;
	protected Date upddate;
	protected String updname;
	protected Date sourcecheckdate;
	protected Integer ishide;
	protected Integer appsubmitcnt;
	protected Integer rowno;
	protected Date makedate_max;
	protected Integer outExpDay;
	protected Integer inExpDay;
	protected String area2;
	protected String sourceno2;
	protected String transportation2;
	protected String transportationno2;
	protected Date shipdate2;
	protected Date loadindate2;
	protected Date loadoutdate2;
	protected Date unloadoutdate2;
	protected String vehischedulexception2;
	protected Date loadexceptionday2;
	protected Date uploadexceptionday2;
	protected String loadingnote2;
	protected List<OrderDetail> orderDetails;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrdernohead() {
		return ordernohead;
	}
	public void setOrdernohead(String ordernohead) {
		this.ordernohead = ordernohead;
	}
	public Date getBilldate() {
		return billdate;
	}
	public void setBilldate(Date billdate) {
		this.billdate = billdate;
	}
	public String getSenderno() {
		return senderno;
	}
	public void setSenderno(String senderno) {
		this.senderno = senderno;
	}
	public String getSendername() {
		return sendername;
	}
	public void setSendername(String sendername) {
		this.sendername = sendername;
	}
	public String getSendtel() {
		return sendtel;
	}
	public void setSendtel(String sendtel) {
		this.sendtel = sendtel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getGuestno() {
		return guestno;
	}
	public void setGuestno(String guestno) {
		this.guestno = guestno;
	}
	public String getGuestname() {
		return guestname;
	}
	public void setGuestname(String guestname) {
		this.guestname = guestname;
	}
	public String getReceiveman() {
		return receiveman;
	}
	public void setReceiveman(String receiveman) {
		this.receiveman = receiveman;
	}
	public String getReceivetel() {
		return receivetel;
	}
	public void setReceivetel(String receivetel) {
		this.receivetel = receivetel;
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
	public Integer getEditor() {
		return editor;
	}
	public void setEditor(Integer editor) {
		this.editor = editor;
	}
	public Date getEditdate() {
		return editdate;
	}
	public void setEditdate(Date editdate) {
		this.editdate = editdate;
	}
	public Integer getApprover() {
		return approver;
	}
	public void setApprover(Integer approver) {
		this.approver = approver;
	}
	public Date getApprovedate() {
		return approvedate;
	}
	public void setApprovedate(Date approvedate) {
		this.approvedate = approvedate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getSfilename() {
		return sfilename;
	}
	public void setSfilename(String sfilename) {
		this.sfilename = sfilename;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Integer getSysorderno() {
		return sysorderno;
	}
	public void setSysorderno(Integer sysorderno) {
		this.sysorderno = sysorderno;
	}
	public String getRejectmemo() {
		return rejectmemo;
	}
	public void setRejectmemo(String rejectmemo) {
		this.rejectmemo = rejectmemo;
	}
	public Date getRejectdate() {
		return rejectdate;
	}
	public void setRejectdate(Date rejectdate) {
		this.rejectdate = rejectdate;
	}
	public Date getReceivedate() {
		return receivedate;
	}
	public void setReceivedate(Date receivedate) {
		this.receivedate = receivedate;
	}
	public Date getDriverreceivedate() {
		return driverreceivedate;
	}
	public void setDriverreceivedate(Date driverreceivedate) {
		this.driverreceivedate = driverreceivedate;
	}
	public String getTruckno() {
		return truckno;
	}
	public void setTruckno(String truckno) {
		this.truckno = truckno;
	}
	public String getTrucktype() {
		return trucktype;
	}
	public void setTrucktype(String trucktype) {
		this.trucktype = trucktype;
	}
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public Date getControldate() {
		return controldate;
	}
	public void setControldate(Date controldate) {
		this.controldate = controldate;
	}
	public String getPicktime() {
		return picktime;
	}
	public void setPicktime(String picktime) {
		this.picktime = picktime;
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
	public Date getSourcecheckdate() {
		return sourcecheckdate;
	}
	public void setSourcecheckdate(Date sourcecheckdate) {
		this.sourcecheckdate = sourcecheckdate;
	}
	public Integer getIshide() {
		return ishide;
	}
	public void setIshide(Integer ishide) {
		this.ishide = ishide;
	}
	public Integer getAppsubmitcnt() {
		return appsubmitcnt;
	}
	public void setAppsubmitcnt(Integer appsubmitcnt) {
		this.appsubmitcnt = appsubmitcnt;
	}
	public Integer getRowno() {
		return rowno;
	}
	public void setRowno(Integer rowno) {
		this.rowno = rowno;
	}
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public Date getMakedate_max() {
		return makedate_max;
	}
	public void setMakedate_max(Date makedate_max) {
		this.makedate_max = makedate_max;
	}
	public Integer getOutExpDay() {
		return outExpDay;
	}
	public void setOutExpDay(Integer outExpDay) {
		this.outExpDay = outExpDay;
	}
	public Integer getInExpDay() {
		return inExpDay;
	}
	public void setInExpDay(Integer inExpDay) {
		this.inExpDay = inExpDay;
	}
	public String getArea2() {
		return area2;
	}
	public void setArea2(String area2) {
		this.area2 = area2;
	}
	public String getSourceno2() {
		return sourceno2;
	}
	public void setSourceno2(String sourceno2) {
		this.sourceno2 = sourceno2;
	}
	public String getTransportationno2() {
		return transportationno2;
	}
	public void setTransportationno2(String transportationno2) {
		this.transportationno2 = transportationno2;
	}
	public Date getShipdate2() {
		return shipdate2;
	}
	public void setShipdate2(Date shipdate2) {
		this.shipdate2 = shipdate2;
	}
	public Date getLoadindate2() {
		return loadindate2;
	}
	public void setLoadindate2(Date loadindate2) {
		this.loadindate2 = loadindate2;
	}
	public Date getLoadoutdate2() {
		return loadoutdate2;
	}
	public void setLoadoutdate2(Date loadoutdate2) {
		this.loadoutdate2 = loadoutdate2;
	}
	public Date getUnloadoutdate2() {
		return unloadoutdate2;
	}
	public void setUnloadoutdate2(Date unloadoutdate2) {
		this.unloadoutdate2 = unloadoutdate2;
	}
	public String getVehischedulexception2() {
		return vehischedulexception2;
	}
	public void setVehischedulexception2(String vehischedulexception2) {
		this.vehischedulexception2 = vehischedulexception2;
	}
	public Date getLoadexceptionday2() {
		return loadexceptionday2;
	}
	public void setLoadexceptionday2(Date loadexceptionday2) {
		this.loadexceptionday2 = loadexceptionday2;
	}
	public Date getUploadexceptionday2() {
		return uploadexceptionday2;
	}
	public void setUploadexceptionday2(Date uploadexceptionday2) {
		this.uploadexceptionday2 = uploadexceptionday2;
	}
	public String getLoadingnote2() {
		return loadingnote2;
	}
	public void setLoadingnote2(String loadingnote2) {
		this.loadingnote2 = loadingnote2;
	}
	public String getTransportation2() {
		return transportation2;
	}
	public void setTransportation2(String transportation2) {
		this.transportation2 = transportation2;
	}
	
}
