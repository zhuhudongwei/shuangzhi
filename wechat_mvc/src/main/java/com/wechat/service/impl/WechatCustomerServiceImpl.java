package com.wechat.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.model.WechatCustomer;
import com.wechat.model.WechatCustomerResponse;
import com.wechat.service.WechatCustomerService;
import com.wechat.utils.DataPage;

@Service
public class WechatCustomerServiceImpl implements WechatCustomerService{
	@Autowired
	protected SqlSession sqlSession;

	public long addWechatCustomer(WechatCustomer wechatCustomer)
			throws Exception {
		// TODO Auto-generated method stub
		Long key = sqlSession.selectOne(WechatCustomer.class.getName()+".selectMaxId");
		if(key == null){
			key = 0l;
		}
		key +=1;
		wechatCustomer.setCustomer_id(key);
		sqlSession.insert(WechatCustomer.class.getName()+".wechatCustomerinsert", wechatCustomer);
		return key;
	}

	public long addWechatCustomerResponse(
			WechatCustomerResponse wechatCustomerResponse) throws Exception {
		// TODO Auto-generated method stub
		Long key = sqlSession.selectOne(WechatCustomerResponse.class.getName()+".selectMaxId");
		if(key == null){
			key = 0l;
		}
		key +=1;
		wechatCustomerResponse.setResponse_id(key);
		sqlSession.insert(WechatCustomerResponse.class.getName()+".WechatCustomerResponseinsert", wechatCustomerResponse);
		return key;
	}

	public void updateWechatCustomerResponseByState(long response_id)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("response_id", response_id);
		params2.put("issend", WechatCustomerResponse.RESPONSE_ISSEND_YES);
		sqlSession.update(WechatCustomerResponse.class.getName()+".updateWechatCustomerResponseByState", params2);
	}

	public DataPage<WechatCustomer> listWechatCustomerByBackend(String open_id,
			Integer pageNo, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("open_id", open_id);
		Integer offset  = null;
		int cout = sqlSession.selectOne(WechatCustomer.class.getName()+".selectWechatCustomerByOpenId2_count", params);
		if(null != pageNo && pageNo > 0 && null != pageSize){
			offset = (pageNo-1)*pageSize;
			if(offset >= cout){
				if(cout == 0){
					offset = 0;
				}else{
					offset = cout-pageSize;
				}
			}
			params.put("offset", offset);
			params.put("pageSize", pageSize);
		}
		if(null == offset){
			offset = 0;
		}
		if(null == pageSize){
			pageSize = 20;
		}

		
		List<WechatCustomer> wechatC = sqlSession.selectList(WechatCustomer.class.getName()+".selectWechatCustomerByOpenId2", params);
		for (int j = 0; j < wechatC.size(); j++) {
			Map<String, Object> params2 = new HashMap<String, Object>();
			params2.put("customer_id", wechatC.get(j).getCustomer_id());
			List<WechatCustomerResponse> wcrs = sqlSession.selectList(WechatCustomerResponse.class.getName()+".selectWechatCustomerResponseByOpenId2", params2);
			wechatC.get(j).setWcrs(wcrs);
		}
		DataPage<WechatCustomer> datas = new DataPage<WechatCustomer>(offset, cout, pageSize, wechatC);
		return datas;
	}

	public WechatCustomer getWechatCustomer(long customer_id) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("customer_id", customer_id);
		WechatCustomer wc = sqlSession.selectOne(WechatCustomer.class.getName()+".selectWechatCustomerInfo", params2);
		return wc;
	}

	public void updateWechatCustomerByState(long customer_id) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("customer_id", customer_id);
		params2.put("isResponse", WechatCustomer.RESPONSE_ISRESPONSE_YES);
		sqlSession.update(WechatCustomer.class.getName()+".updateWechatCustomerByState", params2);
	}

	public List<WechatCustomerResponse> listWechatCustomerResponseByIsSend(
			String open_id) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("open_id", open_id);
		return sqlSession.selectList(WechatCustomerResponse.class.getName()+".listWechatCustomerResponseByIsSend", params2);
	}

	public List<WechatCustomerResponse> listWechatCustomerResponseAll()
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WechatCustomer> listWechatCustomerAll() throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		List<WechatCustomer> wechatC = sqlSession.selectList(WechatCustomer.class.getName()+".selectWechatCustomerByOpenId2", params);
		for (int j = 0; j < wechatC.size(); j++) {
			Map<String, Object> params2 = new HashMap<String, Object>();
			params2.put("customer_id", wechatC.get(j).getCustomer_id());
			List<WechatCustomerResponse> wcrs = sqlSession.selectList(WechatCustomerResponse.class.getName()+".selectWechatCustomerResponseByOpenId2", params2);
			wechatC.get(j).setWcrs(wcrs);
		}
		return wechatC;
	}
	
	public List<WechatCustomer> listWechatCustomerByOpenId(String open_id) throws Exception{
		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("open_id", open_id);
		List<WechatCustomer> wechatC = sqlSession.selectList(
				WechatCustomer.class.getName()+".listWechatCustomerByOpenId", params2);
		return wechatC;
	}
	
	public List<WechatCustomerResponse> listWechatCustomerResponseByCustomerId(long customer_id) throws Exception{
		Map<String, Object> params2 = new HashMap<String, Object>();
		params2.put("customer_id", customer_id);
		List<WechatCustomerResponse> wcrs = sqlSession.selectList(
				WechatCustomerResponse.class.getName()+".selectWechatCustomerResponseByOpenId2", params2);
		return wcrs;
	}
	
	public List<WechatCustomer> listWechatCustomerHistory() throws Exception{
		Map<String, Object> params2 = new HashMap<String, Object>();
		List<WechatCustomer> wechatC = sqlSession.selectList(
				WechatCustomer.class.getName()+".listWechatCustomerHistory", params2);
		return wechatC;
	}
}
