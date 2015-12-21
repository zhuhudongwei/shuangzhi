package com.wechat.utils;

/**
 * 
 * code换取access_token
 * @author hehaiyang
 * @create 2014年9月15日 下午2:55:22
 * @version 1.0
 */
public class OAuthToken {
    public String accessToken;//网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
    public int expiresIn;//access_token接口调用凭证超时时间，单位（秒）
    public String refreshToken;//用户刷新access_token
    public String openid;//用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
    public String scope;//用户授权的作用域，使用逗号（,）分隔
    public String unionid;
    
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
    
}
