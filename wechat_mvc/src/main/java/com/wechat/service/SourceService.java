package com.wechat.service;

import com.wechat.model.Source;

public interface SourceService {

	Source getSourceBySourceno(String sourceno)throws Exception;
}
