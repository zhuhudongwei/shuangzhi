package com.wechat.service;

import java.util.Date;
import java.util.List;

import com.wechat.model.Appsubmit;
import com.wechat.model.AppsubmitDetail;

public interface AppsubmitService {

	Appsubmit getAppsubmitByTel(String tel)throws Exception;
	
	List<Appsubmit> listAppsubmits()throws Exception;
	
	List<AppsubmitDetail> listAppsubmitDetail(String tel, Date driverreceivedate, Date expectedTakeDeliveryTime)throws Exception;
}
