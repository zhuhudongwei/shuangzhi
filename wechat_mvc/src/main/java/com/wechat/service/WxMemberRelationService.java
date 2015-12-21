package com.wechat.service;

import com.wechat.model.WxMemberRelation;

public interface WxMemberRelationService {
	
	WxMemberRelation getMemberToWxByOpenId(String openid) throws Exception;

	void addMemberToWx(String openid, String membercode) throws Exception;

	void removRelation(String openid) throws Exception;
}
