package com.wechat.service.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.model.Area;
import com.wechat.model.Customer;
import com.wechat.model.Line;
import com.wechat.model.Operator;
import com.wechat.model.Source;
import com.wechat.model.Supplier;
import com.wechat.service.OperatorService;

@Service
public class OperatorServiceImpl implements OperatorService{
	@Autowired
	protected SqlSession sqlSession;
	
	public Operator getOperatorByUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		Operator operator = sqlSession.selectOne(Operator.class.getName()+".selectOperatorByUsername", params);
		return operator;
	}

	public Supplier getSupplierByUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		Supplier supplier = sqlSession.selectOne(Supplier.class.getName()+".selectSupplierByUsername", params);
		return supplier;
	}

	public void modifyOperator(Operator operator) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(Operator.class.getName()+".modifyOperator", operator);
	}

	public List<Operator> searchOperatorByStauts(int roleid,Integer lastLogin) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleid", roleid);
		params.put("lastLogin",  new Date(lastLogin*1000l));
		List<Operator> operators = sqlSession.selectList(Operator.class.getName()+".searchOperatorByStauts", params);
		return operators;
	}
	public List<Supplier> searchSupplier(Integer lastLogin) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lastLogin",  new Date(lastLogin));
		List<Supplier> suppliers = sqlSession.selectList(Supplier.class.getName()+".searchSupplier", params);
		return suppliers;
	}
	public List<Source> searchSource(Integer lastLogin) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lastLogin",  new Date(lastLogin));
		List<Source> sources = sqlSession.selectList(Source.class.getName()+".searchSource", params);
		return sources;
	}
	public List<Customer> searchCustomer(Integer lastLogin) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lastLogin",  new Date(lastLogin));
		List<Customer> customers = sqlSession.selectList(Customer.class.getName()+".searchCustomer", params);
		return customers;
	}

	public List<Area> searchArea(Integer lastLogin) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lastLogin", new Date(lastLogin*1000l));
		List<Area> areas = sqlSession.selectList(Area.class.getName()+".searchArea", params);
		return areas;
	}


	public List<Line> searchLine(Integer lastLogin) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lastLogin",  new Date(lastLogin*1000l));
		List<Line> lines = sqlSession.selectList(Line.class.getName()+".searchLine", params);
		return lines;
	}
}
