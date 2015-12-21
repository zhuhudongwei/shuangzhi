package com.wechat.service;

import java.util.List;

import com.wechat.model.Sms;

public interface SmsService {

	List<Sms> searchAll()throws Exception;
}
