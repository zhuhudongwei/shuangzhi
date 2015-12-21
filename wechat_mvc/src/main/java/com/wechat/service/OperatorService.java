package com.wechat.service;

import java.sql.Date;
import java.util.List;

import com.wechat.model.Area;
import com.wechat.model.Customer;
import com.wechat.model.Line;
import com.wechat.model.Operator;
import com.wechat.model.Source;
import com.wechat.model.Supplier;

public interface OperatorService {

	/**
	 * 通过收货人姓名获取收货人信息
	 * @param username
	 * @return
	 * @throws Exception
	 */
	Operator getOperatorByUsername(String username)throws Exception;
	
	void modifyOperator(Operator operator)throws Exception;
	
	/***
	 * 通过承运商获取承运商信息
	 * @param username
	 * @return
	 * @throws Exception
	 */
	Supplier getSupplierByUsername(String username)throws Exception;
	
	/***
	 * 
	 * @param roleid 承运商==4;发货仓库==7;收货方==5;
	 * @return
	 * @throws Exception
	 */
	List<Operator> searchOperatorByStauts(int roleid,Integer lastLogin)throws Exception;
	
	/**
	 * 获取区域
	 * @param lastLogin 
	 * @return
	 * @throws Exception
	 */
	List<Area> searchArea(Integer lastLogin)throws Exception;
	/**
	 * 获取线路
	 * @param lastLogin 
	 * @return
	 * @throws Exception
	 */
	List<Line> searchLine(Integer lastLogin)throws Exception;

	List<Supplier> searchSupplier(Integer lastLogin)throws Exception;

	List<Source> searchSource( Integer lastLogin)throws Exception;

	List<Customer> searchCustomer( Integer lastLogin)throws Exception;

}
