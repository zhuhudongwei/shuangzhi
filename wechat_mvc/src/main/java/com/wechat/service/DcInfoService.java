package com.wechat.service;

import com.wechat.model.DcInfo;

public interface DcInfoService {

	DcInfo getDcInfoByTransportno(String transportno)throws Exception;
}
