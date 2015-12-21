package com.wechat.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.model.Source;
import com.wechat.service.SourceService;

@Service
public class SourceServiceImpl implements SourceService{
	@Autowired
	protected SqlSession sqlSession;

	public Source getSourceBySourceno(String sourceno) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sourceno", sourceno);
		Source customer = sqlSession.selectOne(Source.class.getName()+".getSourceBySourceno", params);
		return customer;
	}

}
