package com.wechat.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.model.Article;
import com.wechat.model.Customer;
import com.wechat.model.Joinorder;
import com.wechat.model.OrderDetail;
import com.wechat.model.Orderhead;
import com.wechat.service.OrderService;
import com.wechat.utils.DataPage;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private SqlSession sqlSession;

	public OrderDetail getOrderDetailById(int id) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<OrderDetail> details = sqlSession.selectList(OrderDetail.class.getName()+".selectOrderDetailById", params);
		OrderDetail orderdetail = null;
		if(details != null){
			if(details.size() != 0){
				orderdetail = details.get(0);
			}
		}
		return orderdetail;
	}

	public Orderhead getOrderheadById(int id) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		Orderhead orderhead = sqlSession.selectOne(Orderhead.class.getName()+".selectOrderheadById", params);
		return orderhead;
	}

	public Customer getCustomerByCode(String code) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		Customer orderhead = sqlSession.selectOne(Customer.class.getName()+".selectCustomerByCode", params);
		return orderhead;
	}

	public List<OrderDetail> listOrderDetailById(int id) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<OrderDetail> details = sqlSession.selectList(OrderDetail.class.getName()+".selectOrderDetailById", params);
		return details;
	}

	public String getOrderheadState(String loadingNote) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loadingNote", loadingNote);
		String noteStatus = sqlSession.selectOne(Orderhead.class.getName()+".getOrderheadState", params);
		return noteStatus;
	}

	public List<Orderhead> searchOrderheadByState(String supplierno,
			String sendername, String guestno, int stauts, Integer pageNo, Integer pageSize)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("supplierno", supplierno);
		params.put("sendername", sendername);
		params.put("guestno", guestno);
		params.put("stauts", stauts);
		
		int offset = 1;
		int onset = 0;
		if(pageSize != null){
			onset = pageSize;
		}
		if(pageNo == null){
			onset = 1000000000;
		}else{
			if(pageNo > 1){
				int size = (pageNo - 1) * pageSize;
				offset = 1 + size;
				onset = 15 + size;
			}
		}
		params.put("offset", offset);
		params.put("onset", onset);
		
		List<Orderhead> orderheads = sqlSession.selectList(Orderhead.class.getName()+".searchOrderheadByState", params);
		return orderheads;
	}

	public List<OrderDetail> listOrderDetailByheadId(int id) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<OrderDetail> details = sqlSession.selectList(OrderDetail.class.getName()+".selectOrderDetailByheadId", params);
		return details;
	}

	public void updateOrderhead(Orderhead oh) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(Orderhead.class.getName()+".updateOrderhead", oh);
	}

	public List<Orderhead> searchOrderhead_anomalyByState(String supplierno,
			String sendername, String guestno, int stauts, Date time, Integer pageNo,
			Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("supplierno", supplierno);
		params.put("sendername", sendername);
		params.put("guestno", guestno);
		params.put("stauts", stauts);
		params.put("time", time);
		int offset = 1;
		int onset = 0;
		if(pageSize != null){
			onset = pageSize;
		}
		if(pageNo == null){
			onset = 1000000000;
		}else{
			if(pageNo > 1){
				int size = (pageNo - 1) * pageSize;
				offset = 1 + size;
				onset = 15 + size;
			}
		}
		params.put("offset", offset);
		params.put("onset", onset);
		
		List<Orderhead> orderheads = sqlSession.selectList(Orderhead.class.getName()+".searchOrderhead_anomalyByState", params);
		return orderheads;
	}

	public void addJoinorder(Joinorder joinorder) throws Exception {
		// TODO Auto-generated method stub
//		int id = sqlSession.selectOne(Joinorder.class.getName()+".selectMaxId");
//		joinorder.setId(id+1);
		sqlSession.insert(Joinorder.class.getName()+".insert", joinorder);
	}

	public void delJoinorder(int id, int joinid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("joinid", joinid);
		sqlSession.delete(Joinorder.class.getName()+".delete", params);
	}

	public List<Joinorder> searchJoinorder(String loadingnote) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loadingnote", loadingnote);
		List<Joinorder> orderheads = sqlSession.selectList(Joinorder.class.getName()+".searchJoinorder", params);
		return orderheads;
	}

	public void updateJoinorder(Joinorder joinorder) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(Joinorder.class.getName()+".updateJoinorder", joinorder);
	}

	public void addOrderheadBAK(Orderhead orderhead) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", orderhead.getId());
		if(orderhead.getOrdernohead() == null){
			params.put("ordernohead", "");
		}else{
			params.put("ordernohead", orderhead.getOrdernohead());
		}
		params.put("billdate", orderhead.getBilldate());
		params.put("senderno", orderhead.getSenderno());
		params.put("sendername", orderhead.getSendername());
		params.put("sendtel", orderhead.getSendtel());
		if(orderhead.getFax() == null){
			params.put("fax", "");
		}else{
			params.put("fax", orderhead.getFax());
		}
		
		params.put("guestno", orderhead.getGuestno());
		params.put("guestname", orderhead.getGuestname());
		params.put("receiveman", orderhead.getReceiveman());
		params.put("receivetel", orderhead.getReceivetel());
		params.put("supplierno", orderhead.getSupplierno());
		params.put("suppliername", orderhead.getSuppliername());
		params.put("gropid", orderhead.getGropid());
		params.put("corpid", orderhead.getCorpid());
		params.put("maker", orderhead.getMaker());
		params.put("makedate", orderhead.getMakedate());
		if(orderhead.getEditor() == null){
			params.put("editor", "");
		}else{
			params.put("editor", orderhead.getEditor());
		}
		if(orderhead.getEditdate() == null){
			params.put("editdate", "");
		}else{
			params.put("editdate", orderhead.getEditdate());
		}
		if(orderhead.getApprover() == null){
			params.put("approver", "");
		}else{
			params.put("approver", orderhead.getApprover());
		}
		if(orderhead.getApprovedate() == null){
			params.put("approvedate", "");
		}else{
			params.put("approvedate", orderhead.getApprovedate());
		}
		params.put("status", orderhead.getStatus());
		if(orderhead.getMemo() == null){
			params.put("memo", "");
		}else{
			params.put("memo", orderhead.getMemo());
		}
		
		params.put("sfilename", orderhead.getSfilename());
		params.put("subject", orderhead.getSubject());
		params.put("sysorderno", orderhead.getSysorderno());
		if(orderhead.getRejectmemo() == null){
			params.put("rejectmemo", "");
		}else{
			params.put("rejectmemo", orderhead.getRejectmemo());
		}
		if(orderhead.getRejectdate() == null){
			params.put("rejectdate", "");
		}else{
			params.put("rejectdate", orderhead.getRejectdate());
		}
		if(orderhead.getReceivedate() == null){
			params.put("receivedate", "");
		}else{
			params.put("receivedate", orderhead.getReceivedate());
		}
		if(orderhead.getDriverreceivedate() == null){
			params.put("driverreceivedate", "");
		}else{
			params.put("driverreceivedate", orderhead.getDriverreceivedate());
		}
		if(orderhead.getTruckno() == null){
			params.put("truckno", "");
		}else{
			params.put("truckno", orderhead.getTruckno());
		}
		if(orderhead.getTrucktype() == null){
			params.put("trucktype", "");
		}else{
			params.put("trucktype", orderhead.getTrucktype());
		}
		if(orderhead.getDrivername() == null){
			params.put("drivername", "");
		}else{
			params.put("drivername", orderhead.getDrivername());
		}
		if(orderhead.getTel() == null){
			params.put("tel", "");
		}else{
			params.put("tel", orderhead.getTel());
		}
		if(orderhead.getIdcard() == null){
			params.put("idcard", "");
		}else{
			params.put("idcard", orderhead.getIdcard());
		}
		if(orderhead.getControldate() == null){
			params.put("controldate", "");
		}else{
			params.put("controldate", orderhead.getControldate());
		}
		if(orderhead.getPicktime() == null){
			params.put("picktime", "");
		}else{
			params.put("picktime", orderhead.getPicktime());
		}
		if(orderhead.getUpddate() == null){
			params.put("upddate", "");
		}else{
			params.put("upddate", orderhead.getUpddate());
		}
		params.put("chgtype", 0);
		sqlSession.insert(Orderhead.class.getName()+".insert_ORDERDETAIL_BAK", params);
	}

	public void addOrderhead(Orderhead orderhead) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", orderhead.getId());
		if(orderhead.getOrdernohead() == null){
			params.put("ordernohead", "");
		}else{
			params.put("ordernohead", orderhead.getOrdernohead());
		}
		params.put("billdate", orderhead.getBilldate());
		params.put("senderno", orderhead.getSenderno());
		params.put("sendername", orderhead.getSendername());
		params.put("sendtel", orderhead.getSendtel());
		if(orderhead.getFax() == null){
			params.put("fax", "");
		}else{
			params.put("fax", orderhead.getFax());
		}
		
		params.put("guestno", orderhead.getGuestno());
		params.put("guestname", orderhead.getGuestname());
		params.put("receiveman", orderhead.getReceiveman());
		params.put("receivetel", orderhead.getReceivetel());
		params.put("supplierno", orderhead.getSupplierno());
		params.put("suppliername", orderhead.getSuppliername());
		params.put("gropid", orderhead.getGropid());
		params.put("corpid", orderhead.getCorpid());
		params.put("maker", orderhead.getMaker());
		params.put("makedate", orderhead.getMakedate());
		if(orderhead.getEditor() == null){
			params.put("editor", "");
		}else{
			params.put("editor", orderhead.getEditor());
		}
		if(orderhead.getEditdate() == null){
			params.put("editdate", "");
		}else{
			params.put("editdate", orderhead.getEditdate());
		}
		if(orderhead.getApprover() == null){
			params.put("approver", "");
		}else{
			params.put("approver", orderhead.getApprover());
		}
		if(orderhead.getApprovedate() == null){
			params.put("approvedate", "");
		}else{
			params.put("approvedate", orderhead.getApprovedate());
		}
		params.put("status", orderhead.getStatus());
		if(orderhead.getMemo() == null){
			params.put("memo", "");
		}else{
			params.put("memo", orderhead.getMemo());
		}
		
		params.put("sfilename", orderhead.getSfilename());
		params.put("subject", orderhead.getSubject());
		params.put("sysorderno", orderhead.getSysorderno());
		if(orderhead.getRejectmemo() == null){
			params.put("rejectmemo", "");
		}else{
			params.put("rejectmemo", orderhead.getRejectmemo());
		}
		if(orderhead.getRejectdate() == null){
			params.put("rejectdate", "");
		}else{
			params.put("rejectdate", orderhead.getRejectdate());
		}
		if(orderhead.getReceivedate() == null){
			params.put("receivedate", "");
		}else{
			params.put("receivedate", orderhead.getReceivedate());
		}
		if(orderhead.getDriverreceivedate() == null){
			params.put("driverreceivedate", "");
		}else{
			params.put("driverreceivedate", orderhead.getDriverreceivedate());
		}
		if(orderhead.getTruckno() == null){
			params.put("truckno", "");
		}else{
			params.put("truckno", orderhead.getTruckno());
		}
		if(orderhead.getTrucktype() == null){
			params.put("trucktype", "");
		}else{
			params.put("trucktype", orderhead.getTrucktype());
		}
		if(orderhead.getDrivername() == null){
			params.put("drivername", "");
		}else{
			params.put("drivername", orderhead.getDrivername());
		}
		if(orderhead.getTel() == null){
			params.put("tel", "");
		}else{
			params.put("tel", orderhead.getTel());
		}
		if(orderhead.getIdcard() == null){
			params.put("idcard", "");
		}else{
			params.put("idcard", orderhead.getIdcard());
		}
		if(orderhead.getControldate() == null){
			params.put("controldate", "");
		}else{
			params.put("controldate", orderhead.getControldate());
		}
		if(orderhead.getPicktime() == null){
			params.put("picktime", "");
		}else{
			params.put("picktime", orderhead.getPicktime());
		}
		if(orderhead.getUpddate() == null){
			params.put("upddate", "");
		}else{
			params.put("upddate", orderhead.getUpddate());
		}
		sqlSession.insert(Orderhead.class.getName()+".insert_ORDERDETAIL", params);
	}

	public void addOrderDetailBAK(OrderDetail orderDetail) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", orderDetail.getId());
		params.put("loadingnote", orderDetail.getLoadingnote());
		params.put("orderno", orderDetail.getOrderno());
		params.put("customerno", orderDetail.getCustomerno());
		params.put("customername", orderDetail.getCustomername());
		params.put("transportationno", orderDetail.getTransportationno());
		params.put("transportation", orderDetail.getTransportation());
		params.put("productname", orderDetail.getProductname());
		params.put("productno", orderDetail.getProductno());
		params.put("planquantity", orderDetail.getPlanquantity());
		if(orderDetail.getBatch() == null){
			params.put("batch", "");
		}else{
			params.put("batch", orderDetail.getBatch());
		}
		params.put("sourcename", orderDetail.getSourcename());
		params.put("sourceno", orderDetail.getSourceno());
		params.put("transmode", orderDetail.getTransmode());
		params.put("shipdate", orderDetail.getShipdate());
		if(orderDetail.getEta() == null){
			params.put("eta", "");
		}else{
			params.put("eta", orderDetail.getEta());
		}
		if(orderDetail.getDeliverynote() == null){
			params.put("deliverynote", "");
		}else{
			params.put("deliverynote", orderDetail.getDeliverynote());
		}
		if(orderDetail.getInsulationstays() == null){
			params.put("insulationstays", "");
		}else{
			params.put("insulationstays", orderDetail.getInsulationstays());
		}
		
		params.put("gropid", orderDetail.getGropid());
		params.put("corpid", orderDetail.getCorpid());
		params.put("maker", orderDetail.getMaker());
		params.put("makedate", orderDetail.getMakedate());
		if(orderDetail.getEditdate() == null){
			params.put("editdate", "");
		}else{
			params.put("editdate", orderDetail.getEditdate());
		}
		if(orderDetail.getUnit() == null){
			params.put("unit", "");
		}else{
			params.put("unit", orderDetail.getUnit());
		}
		params.put("sfilename", orderDetail.getSfilename());
		params.put("dtlid", orderDetail.getDtlid());
		params.put("supplierno", orderDetail.getSupplierno());
		params.put("suppliername", orderDetail.getSuppliername());
		if(orderDetail.getLoadindate() == null){
			params.put("loadindate", "");
		}else{
			params.put("loadindate", orderDetail.getLoadindate());
		}
		if(orderDetail.getLoadoutdate() == null){
			params.put("loadoutdate", "");
		}else{
			params.put("loadoutdate", orderDetail.getLoadoutdate());
		}
		if(orderDetail.getUnloadindate() == null){
			params.put("unloadindate", "");
		}else{
			params.put("unloadindate", orderDetail.getUnloadindate());
		}
		if(orderDetail.getUnloadoutdate() == null){
			params.put("unloadoutdate", "");
		}else{
			params.put("unloadoutdate", orderDetail.getUnloadoutdate());
		}
		if(orderDetail.getLoadnum() == null){
			params.put("loadnum", "");
		}else{
			params.put("loadnum", orderDetail.getLoadnum());
		}
		if(orderDetail.getSignnum() == null){
			params.put("signnum", "");
		}else{
			params.put("signnum", orderDetail.getSignnum());
		}
		if(orderDetail.getDeliveryaddress() == null){
			params.put("deliveryaddress", "");
		}else{
			params.put("deliveryaddress", orderDetail.getDeliveryaddress());
		}
		if(orderDetail.getUpdreason() == null){
			params.put("updreason", "");
		}else{
			params.put("updreason", orderDetail.getUpdreason());
		}
		if(orderDetail.getUpddate() == null){
			params.put("upddate", "");
		}else{
			params.put("upddate", orderDetail.getUpddate());
		}
		if(orderDetail.getUpdname() == null){
			params.put("updname", "");
		}else{
			params.put("updname", orderDetail.getUpdname());
		}
		params.put("chgtype", 0);
		sqlSession.insert(OrderDetail.class.getName()+".insert_ORDERDETAIL_BAK", params);
	}

	public void addOrderDetail(OrderDetail orderDetail) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", orderDetail.getId());
		params.put("loadingnote", orderDetail.getLoadingnote());
		params.put("orderno", orderDetail.getOrderno());
		params.put("customerno", orderDetail.getCustomerno());
		params.put("customername", orderDetail.getCustomername());
		params.put("transportationno", orderDetail.getTransportationno());
		params.put("transportation", orderDetail.getTransportation());
		params.put("productname", orderDetail.getProductname());
		params.put("productno", orderDetail.getProductno());
		params.put("planquantity", orderDetail.getPlanquantity());
		if(orderDetail.getBatch() == null){
			params.put("batch", "");
		}else{
			params.put("batch", orderDetail.getBatch());
		}
		params.put("sourcename", orderDetail.getSourcename());
		params.put("sourceno", orderDetail.getSourceno());
		params.put("transmode", orderDetail.getTransmode());
		params.put("shipdate", orderDetail.getShipdate());
		if(orderDetail.getEta() == null){
			params.put("eta", "");
		}else{
			params.put("eta", orderDetail.getEta());
		}
		if(orderDetail.getDeliverynote() == null){
			params.put("deliverynote", "");
		}else{
			params.put("deliverynote", orderDetail.getDeliverynote());
		}
		if(orderDetail.getInsulationstays() == null){
			params.put("insulationstays", "");
		}else{
			params.put("insulationstays", orderDetail.getInsulationstays());
		}
		
		params.put("gropid", orderDetail.getGropid());
		params.put("corpid", orderDetail.getCorpid());
		params.put("maker", orderDetail.getMaker());
		params.put("makedate", orderDetail.getMakedate());
		if(orderDetail.getEditdate() == null){
			params.put("editdate", "");
		}else{
			params.put("editdate", orderDetail.getEditdate());
		}
		if(orderDetail.getUnit() == null){
			params.put("unit", "");
		}else{
			params.put("unit", orderDetail.getUnit());
		}
		params.put("sfilename", orderDetail.getSfilename());
		params.put("dtlid", orderDetail.getDtlid());
		params.put("supplierno", orderDetail.getSupplierno());
		params.put("suppliername", orderDetail.getSuppliername());
		if(orderDetail.getLoadindate() == null){
			params.put("loadindate", "");
		}else{
			params.put("loadindate", orderDetail.getLoadindate());
		}
		if(orderDetail.getLoadoutdate() == null){
			params.put("loadoutdate", "");
		}else{
			params.put("loadoutdate", orderDetail.getLoadoutdate());
		}
		if(orderDetail.getUnloadindate() == null){
			params.put("unloadindate", "");
		}else{
			params.put("unloadindate", orderDetail.getUnloadindate());
		}
		if(orderDetail.getUnloadoutdate() == null){
			params.put("unloadoutdate", "");
		}else{
			params.put("unloadoutdate", orderDetail.getUnloadoutdate());
		}
		if(orderDetail.getLoadnum() == null){
			params.put("loadnum", "");
		}else{
			params.put("loadnum", orderDetail.getLoadnum());
		}
		if(orderDetail.getSignnum() == null){
			params.put("signnum", "");
		}else{
			params.put("signnum", orderDetail.getSignnum());
		}
		if(orderDetail.getDeliveryaddress() == null){
			params.put("deliveryaddress", "");
		}else{
			params.put("deliveryaddress", orderDetail.getDeliveryaddress());
		}
		if(orderDetail.getUpdreason() == null){
			params.put("updreason", "");
		}else{
			params.put("updreason", orderDetail.getUpdreason());
		}
		if(orderDetail.getUpddate() == null){
			params.put("upddate", "");
		}else{
			params.put("upddate", orderDetail.getUpddate());
		}
		if(orderDetail.getUpdname() == null){
			params.put("updname", "");
		}else{
			params.put("updname", orderDetail.getUpdname());
		}
		sqlSession.insert(OrderDetail.class.getName()+".insert_ORDERDETAIL", params);
	}

	public void delOrderheadBAK(int id) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		sqlSession.delete(Orderhead.class.getName()+".delOrderheadBAK", params);
	}

	public void delOrderhead(int id) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		sqlSession.delete(Orderhead.class.getName()+".delOrderhead", params);
	}

	public void delOrderDetailBAK(String loadingnote) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loadingnote", loadingnote);
		sqlSession.delete(OrderDetail.class.getName()+".delOrderDetailBAK", params);
	}

	public void delOrderDetail(String loadingnote) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loadingnote", loadingnote);
		sqlSession.delete(OrderDetail.class.getName()+".delOrderDetail", params);
	}

	public List<OrderDetail> getOrderDetailBAKById(int id) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<OrderDetail> details = sqlSession.selectList(OrderDetail.class.getName()+".selectOrderDetailBAKById", params);
		return details;
	}
	
	public List<OrderDetail> getOrderDetailBAKByLoadingNote(String loadingNote)throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loadingNote", loadingNote);
		List<OrderDetail> details = sqlSession.selectList(OrderDetail.class.getName()+".selectOrderDetailBAKByLoadingNote", params);
		return details;
	}

	public Orderhead getOrderheadBAKById(int id) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		Orderhead orderhead = sqlSession.selectOne(Orderhead.class.getName()+".selectOrderheadBAKById", params);
		return orderhead;
	}

	public int searchOrderheadByState_size(String supplierno,
			String sendername, String guestno, int stauts, Integer pageNo,
			Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("supplierno", supplierno);
		params.put("sendername", sendername);
		params.put("guestno", guestno);
		params.put("stauts", stauts);
		
		int size = sqlSession.selectOne(Orderhead.class.getName()+".searchOrderheadByState_size", params);
		return size;
	}
	
	public int searchOrderheadByState_size_v2(
			String area, Date beginshipDate, Date endshipDate, String waybillCode, String sourceno, String transportationNo,
			String supplierno, String sendername, String guestno, int stauts, Integer pageNo, Integer pageSize) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("area", area);
		params.put("beginshipDate", beginshipDate);
		params.put("endshipDate", endshipDate);
		params.put("waybillCode", waybillCode);
		params.put("sourceno", sourceno);
		params.put("transportationNo", transportationNo);
		
		params.put("supplierno", supplierno);
		params.put("sendername", sendername);
		params.put("guestno", guestno);
		params.put("stauts", stauts);
		
		int size = sqlSession.selectOne(Orderhead.class.getName()+".searchOrderheadByState_size_v2", params);
		return size;
	}

	public int searchOrderhead_anomalyByState_size(String supplierno,
			String sendername, String guestno, int stauts, Date time,
			Integer pageNo, Integer pageSize) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("supplierno", supplierno);
		params.put("sendername", sendername);
		params.put("guestno", guestno);
		params.put("stauts", stauts);
		params.put("time", time);
		int size = 0;
		if(stauts == 3){
			size = sqlSession.selectOne(Orderhead.class.getName()+".searchOrderhead_anomalyByState_size_3", params);
		}else{
			size = sqlSession.selectOne(Orderhead.class.getName()+".searchOrderhead_anomalyByState_size", params);
		}
		return size;
	}
	
	public int searchOrderhead_anomalyByState_size_v2(
			String area, Date beginshipDate, Date endshipDate, String waybillCode, String sourceno,
			String transportationNo, String supplierno, String sendername, String guestno, int stauts, Date time,
			Integer pageNo, Integer pageSize) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("area", area);
		params.put("beginshipDate", beginshipDate);
		params.put("endshipDate", endshipDate);
		params.put("waybillCode", waybillCode);
		params.put("sourceno", sourceno);
		params.put("transportationNo", transportationNo);
		
		params.put("supplierno", supplierno);
		params.put("sendername", sendername);
		params.put("guestno", guestno);
		params.put("stauts", stauts);
		params.put("time", time);
		int size = 0;
		if(stauts == 3){
			size = sqlSession.selectOne(Orderhead.class.getName()+".searchOrderhead_anomalyByState_size_3_v2", params);
		}else{
			size = sqlSession.selectOne(Orderhead.class.getName()+".searchOrderhead_anomalyByState_size_v2", params);
		}
		return size;
	}

	public List<Orderhead> searchwaybillCode(String area, Date beginshipDate,
			Date endshipDate, String waybillCode, String sourceno,
			String guestno, String supplierno, String sendername, int stauts, String transportationNo,
			Integer pageNo, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("area", area);
		params.put("beginshipDate", beginshipDate);
		params.put("endshipDate", endshipDate);
		params.put("waybillCode", waybillCode);
		params.put("sourceno", sourceno);
		params.put("supplierno", supplierno);
		params.put("sendername", sendername);
		params.put("guestno", guestno);
		params.put("stauts", stauts);
		params.put("transportationNo", transportationNo);
		params.put("time", new Date());
		int offset = 1;
		int onset = 0;
		if(pageSize != null){
			onset = pageSize;
		}
		if(pageNo == null){
			onset = 1000000000;
		}else{
			if(pageNo > 1){
				int size = (pageNo - 1) * pageSize;
				offset = 1 + size;
				onset = 15 + size;
			}
		}
		params.put("offset", offset);
		params.put("onset", onset);
		
		List<Orderhead> orderheads = sqlSession.selectList(Orderhead.class.getName()+".searchwaybillCode", params);
		return orderheads;
	}
	
	public int countwaybillCode(String area, Date beginshipDate, Date endshipDate, String waybillCode, String sourceno, String guestno, String supplierno, String sendername, int stauts, String transportationNo)throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("area", area);
		params.put("beginshipDate", beginshipDate);
		params.put("endshipDate", endshipDate);
		params.put("waybillCode", waybillCode);
		params.put("sourceno", sourceno);
		params.put("supplierno", supplierno);
		params.put("sendername", sendername);
		params.put("guestno", guestno);
		params.put("stauts", stauts);
		params.put("transportationNo", transportationNo);
		params.put("time", new Date());
		int totalnum = sqlSession.selectOne(Orderhead.class.getName()+".countwaybillCode", params);
		return totalnum;
	}

	public Joinorder getJoinorderById(int id) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		Joinorder j = sqlSession.selectOne(Joinorder.class.getName()+".searchJoinorderById", params);
		return j;
	}
	
	public List<Joinorder> listJoinOrderByJoinId(int joinid){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("joinid", joinid);
		List<Joinorder> list = sqlSession.selectList(Joinorder.class.getName()+".searchJoinOrderByJoinId", params);
		return list;
	}
	
	public List<Joinorder> listJoinOrderByloadingnote(int loadingnote){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loadingnote", loadingnote);
		List<Joinorder> list = sqlSession.selectList(Joinorder.class.getName()+".searchJoinOrderByloadingnote", params);
		return list;
	}

	public List<Orderhead> getOrderheadByTEL(String tel, Integer[] status)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tel", tel);
		params.put("status", status);
		List<Orderhead> orderheads = sqlSession.selectList(Orderhead.class.getName()+".selectOrderheadByTel", params);
		return orderheads;
	}
}
