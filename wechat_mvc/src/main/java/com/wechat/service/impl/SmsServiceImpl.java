package com.wechat.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.model.Sms;
import com.wechat.service.SmsService;
@Service
public class SmsServiceImpl implements SmsService{

	@Autowired
	protected SqlSession sqlSession;

	public List<Sms> searchAll() throws Exception {
		// TODO Auto-generated method stub
		List<Sms> sms = sqlSession.selectList(Sms.class.getName()+".searchAll");
		return sms;
	}
}
