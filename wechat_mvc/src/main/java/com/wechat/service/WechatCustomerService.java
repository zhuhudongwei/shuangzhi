package com.wechat.service;

import java.util.List;

import com.wechat.model.WechatCustomer;
import com.wechat.model.WechatCustomerResponse;
import com.wechat.utils.DataPage;

public interface WechatCustomerService {

	long addWechatCustomer(WechatCustomer wechatCustomer)throws Exception;
	
	long addWechatCustomerResponse(WechatCustomerResponse wechatCustomerResponse)throws Exception;
	
	void updateWechatCustomerResponseByState(long response_id)throws Exception;
	
	void updateWechatCustomerByState(long customer_id)throws Exception;
	
	WechatCustomer getWechatCustomer(long customer_id)throws Exception;
	
	List<WechatCustomerResponse> listWechatCustomerResponseByIsSend(String open_id)throws Exception;
	
	List<WechatCustomer> listWechatCustomerAll()throws Exception;
	
//	DataPage<WechatCustomer> listWechatCustomerByOpenId(String open_id, Integer pageNo, Integer pageSize)throws Exception;
//	
//	DataPage<WechatCustomer> listWechatCustomerByBackend(String open_id, Integer pageNo, Integer pageSize)throws Exception;

	List<WechatCustomer> listWechatCustomerByOpenId(String open_id) throws Exception;
	
	List<WechatCustomerResponse> listWechatCustomerResponseByCustomerId(long customer_id) throws Exception;
	
	List<WechatCustomer> listWechatCustomerHistory() throws Exception;
}
