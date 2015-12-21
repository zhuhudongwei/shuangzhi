package com.wechat.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.model.Driver;
import com.wechat.model.DriverTruck;
import com.wechat.model.Truck;
import com.wechat.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService{

	@Autowired
	protected SqlSession sqlSession;
	
	public Driver getDriver(String tel) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tel", tel);
		Driver article = sqlSession.selectOne(Driver.class.getName()+".selectDriver", params);
		return article;
	}

	public Truck getTruck(String tel) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tel", tel);
		List<Truck> trucks = sqlSession.selectList(Truck.class.getName()+".selectTruck", params);
		Truck truck = null;
		if(trucks.size() != 0){
			truck = trucks.get(0);
		}
		return truck;
	}
	
	public List<DriverTruck> listDriverTruck(String tel,String supplierno) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tel", tel);
		params.put("supplierno", supplierno);
		List<DriverTruck> driverTrucks = sqlSession.selectList(DriverTruck.class.getName()+".listTruck", params);
		return driverTrucks;
	}

}
