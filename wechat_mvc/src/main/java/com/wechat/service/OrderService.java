package com.wechat.service;

import java.util.Date;
import java.util.List;

import com.wechat.model.Customer;
import com.wechat.model.Joinorder;
import com.wechat.model.OrderDetail;
import com.wechat.model.Orderhead;
import com.wechat.utils.DataPage;

public interface OrderService {

	void addOrderheadBAK(Orderhead orderhead)throws Exception;
	
	void addOrderhead(Orderhead orderhead)throws Exception;
	
	void addOrderDetailBAK(OrderDetail orderDetail)throws Exception;
	
	void addOrderDetail(OrderDetail orderDetail)throws Exception;
	
	void delOrderheadBAK(int id)throws Exception;
	
	void delOrderhead(int id)throws Exception;
	
	void delOrderDetailBAK(String loadingnote)throws Exception;
	
	void delOrderDetail(String loadingnote)throws Exception;
	
	
	OrderDetail getOrderDetailById(int id)throws Exception;
	
	List<OrderDetail> getOrderDetailBAKById(int id)throws Exception;
	
	List<OrderDetail> getOrderDetailBAKByLoadingNote(String loadingNote)throws Exception;
	
	void updateOrderhead(Orderhead oh)throws Exception;
	
	List<OrderDetail> listOrderDetailById(int id)throws Exception;
	
	List<OrderDetail> listOrderDetailByheadId(int id)throws Exception;
	
	Orderhead getOrderheadById(int id)throws Exception;
	
	List<Orderhead> getOrderheadByTEL(String tel,Integer[] status)throws Exception;
	
	Orderhead getOrderheadBAKById(int id)throws Exception;
	
	String getOrderheadState(String loadingNote)throws Exception;
	
	Customer getCustomerByCode(String code)throws Exception;
	
	void addJoinorder(Joinorder joinorder)throws Exception;
	
	void delJoinorder(int id, int joinid)throws Exception;
	
	void updateJoinorder(Joinorder joinorder)throws Exception;
	
	List<Joinorder> searchJoinorder(String loadingnote)throws Exception;
	
	Joinorder getJoinorderById(int id)throws Exception;
	
	int searchOrderheadByState_size(String supplierno, String sendername, String guestno, int stauts, Integer pageNo, Integer pageSize)throws Exception;
	
	int searchOrderhead_anomalyByState_size(String supplierno, String sendername, String guestno, int stauts, Date time, Integer pageNo, Integer pageSize)throws Exception;
	
	List<Orderhead> searchOrderheadByState(String supplierno, String sendername, String guestno, int stauts, Integer pageNo, Integer pageSize)throws Exception;
	
	List<Orderhead> searchwaybillCode(String area, Date beginshipDate, Date endshipDate, String waybillCode, String sourceno, String guestno, String supplierno, String sendername, int stauts, String transportationNo, Integer pageNo, Integer pageSize)throws Exception;
	
	int countwaybillCode(String area, Date beginshipDate, Date endshipDate, String waybillCode, String sourceno, String guestno, String supplierno, String sendername, int stauts, String transportationNo)throws Exception;
	
	List<Orderhead> searchOrderhead_anomalyByState(String supplierno, String sendername, String guestno, int stauts, Date time, Integer pageNo, Integer pageSize)throws Exception;
	
	List<Joinorder> listJoinOrderByJoinId(int joinid) throws Exception;
	
	List<Joinorder> listJoinOrderByloadingnote(int loadingnote) throws Exception;
	
	int searchOrderheadByState_size_v2(
			String area, Date beginshipDate, Date endshipDate, String waybillCode, String sourceno, String transportationNo,
			String supplierno, String sendername, String guestno, int stauts, Integer pageNo, Integer pageSize)throws Exception;
	
	int searchOrderhead_anomalyByState_size_v2(
			String area, Date beginshipDate, Date endshipDate, String waybillCode, String sourceno, String transportationNo,
			String supplierno, String sendername, String guestno, int stauts, Date time, Integer pageNo, Integer pageSize)throws Exception;
}
