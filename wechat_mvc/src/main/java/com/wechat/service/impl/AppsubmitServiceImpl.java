package com.wechat.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.model.Appsubmit;
import com.wechat.model.AppsubmitDetail;
import com.wechat.service.AppsubmitService;

@Service
public class AppsubmitServiceImpl implements AppsubmitService{
	@Autowired
	protected SqlSession sqlSession;

	public Appsubmit getAppsubmitByTel(String tel) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tel", tel);
		Appsubmit appsubmit = sqlSession.selectOne(Appsubmit.class.getName()+".getAppsubmitByTel", params);
		return appsubmit;
	}

	public List<Appsubmit> listAppsubmits() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AppsubmitDetail> listAppsubmitDetail(String tel, Date driverreceivedate, Date expectedTakeDeliveryTime)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tel", tel);
		params.put("driverreceivedate", driverreceivedate);
		params.put("expectedTakeDeliveryTime", expectedTakeDeliveryTime);
		List<AppsubmitDetail> appsubmitDetails = sqlSession.selectList(AppsubmitDetail.class.getName()+".listAppsubmitDetail", params);
		return appsubmitDetails;
	}

	
}
