package com.wechat.controllor;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ContextLoaderListener;

import com.wechat.model.WechatCustomer;
import com.wechat.model.WechatCustomerResponse;
import com.wechat.service.WechatCustomerService;
import com.wechat.utils.DataPage;
import com.wechat.utils.DateUtil;

import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.EventPullSource;
import nl.justobjects.pushlet.core.Session;
import nl.justobjects.pushlet.core.SessionManager;

@Controller
public class PendingMessageTipController {
	
	private static WechatCustomerService wechatCustomerService;
	
	public static WechatCustomerService getWechatCustomerService() {
		return wechatCustomerService;
	}
	
	@Autowired
	public void setWechatCustomerService(
			WechatCustomerService wechatCustomerService) {
		PendingMessageTipController.wechatCustomerService = wechatCustomerService;
	}

	private static final Logger logger = LoggerFactory.getLogger(PendingMessageTipController.class);  
	
	public static class SendMsgEvent extends EventPullSource{
		@Override
		protected long getSleepTime() {
			// TODO Auto-generated method stub
			 return 5000; 
		}


		@Override
		protected Event pullEvent() {
			// TODO Auto-generated method stub
			 Event event = Event.createDataEvent("/shuangzhi");    
	         try {  
	        	 // 获取当前登陆用户Id(加入事件订阅的用户)
	        	 Session[] sessions = SessionManager.getInstance().getSessions();
	             String tipMessage = "";
//	             System.out.println(event.getField("p_id") + "==================p_id");  
//	             System.out.println(event.getField("userId") + "==================userId");  
//	             System.out.println(event.getField("p_format") + "==================p_format");  
//	             System.out.println(event.getSubject()+ "==================subject");  
	             for(int i=0; i<sessions.length; i++){
	                 //查询当前登录用户的报修任务
	            	 List<WechatCustomerResponse> wcrs = wechatCustomerService.listWechatCustomerResponseByIsSend(sessions[i].getId());
	            	 for (int l = 0; l < wcrs.size(); l++) {
	            		 tipMessage = wcrs.get(l).getResponse_content();
						 event.setField("msg_" + sessions[i].getId(), new String(tipMessage.getBytes("UTF-8"),"ISO-8859-1"));//封装参数
						 event.setField("response_id_" + sessions[i].getId(), wcrs.get(l).getResponse_id());
						 String systime = DateUtil.convertLongToDateString2(System.currentTimeMillis(), "HH:mm:ss");
						 event.setField("systime_" + sessions[i].getId(), new String(systime.getBytes("UTF-8"),"ISO-8859-1"));
						 return event;
					}
	             }
	         } catch (Exception e) {  
	             if (logger.isDebugEnabled())  
	                 e.printStackTrace();  
	             logger.info(e.toString());  
	         }  
	         return event;  
		} 
	}
}
