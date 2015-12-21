package com.wechat.controllor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wechat.model.Operator;
import com.wechat.model.Supplier;
import com.wechat.service.AppsubmitService;
import com.wechat.service.OperatorService;
import com.wechat.service.OrderService;
import com.wechat.service.SourceService;
import com.wechat.utils.MD5;

/**
 * 访问控制器--APP登陆
 * @author zdw
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private OperatorService operatorServiceImpl;
	
	@Autowired
	private OrderService orderServiceImpl;
	
	@Autowired
	private AppsubmitService appsubmitImpl;
	
	@Autowired
	private SourceService sourceServiceImpl;
	/***
	 * 登录
	 * @param account
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/loginUser", method = RequestMethod.POST)
	public Map loginUser(String username, String password, HttpServletRequest request){
		Map ma = new HashMap();
		try {
			ma.put("username", username);
			String md5password = MD5.ToMD5(password);
			String dbpassword = null;
			Operator operator = operatorServiceImpl.getOperatorByUsername(username);
			if(operator == null){
				Supplier supplier = operatorServiceImpl.getSupplierByUsername(username);
				if(supplier == null){
					ma.put("state", -4);
				}else{
					ma.put("supplierno", supplier.getCode());
				}
			}else{
				dbpassword = operator.getPwd();
				if(md5password.equals(dbpassword)){
					if(operator.getRoleid() == 5){
						ma.put("guestno", operator.getUsername());
					}
					if(operator.getRoleid() == 4){
						ma.put("supplierno", operator.getUsername());
					}
					if(operator.getRoleid() == 3 || operator.getRoleid() == 1){
						ma.put("sendername", "SCMC1");
					}
					ma.put("state", 1);
				}else{
					ma.put("state", -4);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ma.put("state", -1);
		}
		return ma;
	}
	
	@RequestMapping(value ="/modifyUserPWD", method = RequestMethod.POST)
	public Map modifyUserPWD(String username, String oldpassword, String password, HttpServletRequest request){
		Map ma = new HashMap();
		try {
			String md5password = MD5.ToMD5(password);
			String dbpassword = MD5.ToMD5(oldpassword);
			Operator operator = operatorServiceImpl.getOperatorByUsername(username);
			if(operator != null){
				if(dbpassword.equals(operator.getPwd())){
					operator.setPwd(md5password);
					operatorServiceImpl.modifyOperator(operator);
					ma.put("state", 1);
				}else{
					ma.put("state", -4);
				}
			}else{
				ma.put("state", -1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ma.put("state", -1);
		}
		return ma;
	}
}
