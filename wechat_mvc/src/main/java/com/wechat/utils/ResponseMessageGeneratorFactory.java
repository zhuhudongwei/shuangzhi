package com.wechat.utils;

public class ResponseMessageGeneratorFactory {
	
	
	public ResponseMessageGenerator newInstance(WechatMessage request) {
		//事件信息
		if (request.isEventMessage()) {
			//关注
			if (request.isSubscribeMessage()) {
				return new WelcomeMessageGenerator();
		    //TODO 根据自定义菜单修改
			}else if(request.isLocationMessage()){
				
			}else if(request.isMenuEventMessage()){
				return new MenuEventMessageGenerator();
		    //根据推送信息回复
			}else{
				return new ReplyMessageGenerator();
			}
		//文本信息
		} else if (request.isTextMessage()) {
				return new ReplyMessageGenerator();
		}
		
		return new UnsupportedMessageGenerator();
	}
	
	private static class UnsupportedMessageGenerator extends ResponseMessageGenerator {
		@Override
		public String generate(WechatMessage request) {
			return "error";
		}
		
		@Override
		protected void appendBody(StringBuilder sb, WechatMessage request) {
			throw new UnsupportedOperationException();
		}
	}
}
