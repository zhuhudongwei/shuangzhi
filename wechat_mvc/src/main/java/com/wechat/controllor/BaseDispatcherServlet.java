package com.wechat.controllor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;

public class BaseDispatcherServlet extends DispatcherServlet {

	@Override
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.doDispatch(request, response);
	}

}
