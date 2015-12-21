package com.wechat.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.model.WxMemberRelation;
import com.wechat.service.WxMemberRelationService;

@Service
public class WxMemberRelationServiceImpl implements WxMemberRelationService{

	@Autowired
	protected SqlSession sqlSession;
	
	public WxMemberRelation getMemberToWxByOpenId(String openid) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", openid);
		WxMemberRelation relation = sqlSession.selectOne(WxMemberRelation.class.getName()+".getMemberToWxByOpenId", params);
		return relation;
	}

	public void addMemberToWx(String openid, String membercode)
			throws Exception {
		Long key = sqlSession.selectOne(WxMemberRelation.class.getName()+".selectMaxId");
		if(key == null){
			key = 0l;
		}
		key +=1;
		WxMemberRelation relation = new WxMemberRelation();
		relation.setId(key);
		relation.setMembercode(membercode);
		relation.setOpenid(openid);
		relation.setCreatetime(System.currentTimeMillis());
		relation.setRemark1("");
		relation.setRemark2("");
		sqlSession.insert(WxMemberRelation.class.getName()+".addMemberToWx", relation);
	}

	public void removRelation(String openid) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openId", openid);
		sqlSession.delete(WxMemberRelation.class.getName() + ".removRelation", params);
	}

}
