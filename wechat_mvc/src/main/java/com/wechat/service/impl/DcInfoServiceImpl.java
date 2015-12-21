package com.wechat.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wechat.model.DcInfo;
import com.wechat.service.DcInfoService;

@Service
public class DcInfoServiceImpl implements DcInfoService{
	@Autowired
	protected SqlSession sqlSession;

	public DcInfo getDcInfoByTransportno(String transportno) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("transportno", transportno);
		DcInfo dcInfo = sqlSession.selectOne(DcInfo.class.getName()+".getDcInfoByTransportno", params);
		return dcInfo;
	}


}
