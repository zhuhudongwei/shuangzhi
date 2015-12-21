package com.wechat.service;

import java.util.List;

import com.wechat.model.Driver;
import com.wechat.model.DriverTruck;
import com.wechat.model.Truck;

public interface DriverService {

	Driver getDriver(String tel)throws Exception;
	
	Truck getTruck(String tel)throws Exception;
	
    List<DriverTruck> listDriverTruck(String tel,String supplierno) throws Exception;
}
