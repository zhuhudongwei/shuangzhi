package com.wechat.controllor;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wechat.model.Appsubmit;
import com.wechat.model.Customer;
import com.wechat.model.Joinorder;
import com.wechat.model.Operator;
import com.wechat.model.OrderDetail;
import com.wechat.model.Orderhead;
import com.wechat.model.Source;
import com.wechat.model.Supplier;
import com.wechat.model.WxMemberRelation;
import com.wechat.service.AppsubmitService;
import com.wechat.service.OperatorService;
import com.wechat.service.OrderService;
import com.wechat.service.SourceService;
import com.wechat.service.WxMemberRelationService;
import com.wechat.utils.DateUtil;
import com.wechat.utils.MD5;
import com.wechat.utils.MapDistance;
import com.wechat.utils.OAuthToken;
import com.wechat.utils.WechatUtils;

@Controller
@RequestMapping("/operator")
public class OperatorControllor {
	
	@Autowired
	private OperatorService operatorServiceImpl;
	
	@Autowired
	private OrderService orderServiceImpl;
	
	@Autowired
	private AppsubmitService appsubmitImpl;
	
	@Autowired
	private SourceService sourceServiceImpl;
	
	@Autowired
	private WxMemberRelationService wxMemberRelationServiceImpl;

	@RequestMapping(value="/toLogin",method = RequestMethod.GET)
	public String toLogin(Model model, HttpServletRequest request)throws Exception{
		Map<String, Object> attrs = new HashMap<String, Object>();
		String localPath = WechatUtils.API_PROJECT_AUTN;
		String weixinUrl = WechatUtils.openIdUrl
				.replace("APPID", WechatUtils.getWechatAppid())
				.replace("REDIRECT_URI", URLEncoder.encode(localPath));
		attrs.put("weixinUrl", weixinUrl);
		System.out.println("qgl-------------:" + weixinUrl);
		model.addAllAttributes(attrs);
		return "/wechatIndex";
//		return "/login";
	}
	
	@RequestMapping(value = "/loginGetOpenId", method = RequestMethod.GET)
	public String loginGetOpenId(Model model, HttpSession httpSession, HttpServletRequest request) throws Exception{
		
		Map<String, Object> attrs = new HashMap<String, Object>();
		String code = request.getParameter("code");
		System.out.println("qgl-------------code:" + code);
		if(code != null && !"".equals(code)){
			// 获得请求access_token的url
			String accessTokenRequest = WechatUtils.getAccessTokenRequest(code);
		System.out.println("accessTokenRequest:"+accessTokenRequest);
			// 请求access_token的url,获得对应的json String
			String resultFromHttpUrl = WechatUtils.getResultFromHttpUrl(accessTokenRequest);
		System.out.println("resultFromHttpUrl:"+resultFromHttpUrl);
			// 获得accessToken对象(包含了微信账号信息)
			OAuthToken accessToken = WechatUtils.getAccessToken(resultFromHttpUrl);
			String openid = null;
			if(accessToken == null){
				openid = (String) httpSession.getAttribute("OPENID");
				System.out.println("qgl-------1------openid:" + openid);
				if(openid == null)
					return "/login";
			} else {
				System.out.println("accessToken:"+accessToken.getAccessToken());
				// 获取OpenId
				openid = accessToken.getOpenid();
				httpSession.setAttribute("OPENID", openid);
				System.out.println("qgl-------2------openid:" + openid);
			}
		
			//获取微信关联的客户
			WxMemberRelation memberToWx = wxMemberRelationServiceImpl.getMemberToWxByOpenId(openid);
			System.out.println("qgl----------------:memberToWx" + memberToWx);
			//微信已经关联自动登录
			if(memberToWx != null){
				Operator operator = operatorServiceImpl.getOperatorByUsername(memberToWx.getMembercode());
				if(operator == null){
					Supplier supplier = operatorServiceImpl.getSupplierByUsername(memberToWx.getMembercode());
					attrs.put("supplierno", supplier.getCode());
					attrs.put("roleid", 4);//承运商
				} else {
					if(operator.getRoleid() == 5){
						attrs.put("guestno", operator.getUsername());
					}
					if(operator.getRoleid() == 4){
						attrs.put("supplierno", operator.getUsername());
					}
					if(operator.getRoleid() == 3){
						attrs.put("sendername", operator.getGuestname());
					}
					attrs.put("roleid", operator.getRoleid());//3:发货方;5:收货方
				}
				model.addAllAttributes(attrs);
				return "/search";
			}
		}
		return "/login";
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String pwd, HttpSession httpSession, Model model, HttpServletRequest request)throws Exception{
		String openid = (String) httpSession.getAttribute("OPENID");
		System.out.println("login openid:" + openid);
		Map<String, Object> attrs = new HashMap<String, Object>();
		Operator operator = operatorServiceImpl.getOperatorByUsername(username);
		String md5password = MD5.ToMD5(pwd);
		String dbpassword = null;
		if(operator == null){
			System.out.println("login operator:" + "null");
			Supplier supplier = operatorServiceImpl.getSupplierByUsername(username);
			if(supplier == null){
				System.out.println("login supplier:" + "null");
				attrs.put("msg", "请输入正确的账号密码！");
				model.addAllAttributes(attrs);
				return "/login";
			}else{
				//绑定微信
				if(openid != null && !"".equals(openid)) {
					wxMemberRelationServiceImpl.addMemberToWx(openid, supplier.getCode());
				}
				attrs.put("supplierno", supplier.getCode());
				attrs.put("roleid", 4);//承运商
			}
		}else{
			dbpassword = operator.getPwd();
			if(md5password.equals(dbpassword)){
				//绑定微信
				if(openid != null && !"".equals(openid)) {
					wxMemberRelationServiceImpl.addMemberToWx(openid, operator.getUsername());
				}
				if(operator.getRoleid() == 5){
					attrs.put("guestno", operator.getUsername());
				}
				if(operator.getRoleid() == 4){
					attrs.put("supplierno", operator.getUsername());
				}
				if(operator.getRoleid() == 3){
					attrs.put("sendername", operator.getGuestname());
				}
				attrs.put("roleid", operator.getRoleid());//3:发货方;5:收货方
			}else{
				attrs.put("msg", "请输入正确的账号密码！");
				model.addAllAttributes(attrs);
				return "/login";
			}
		}
		model.addAllAttributes(attrs);
		return "/search";
	}
	
	/**
	 * 搜索运单
	 * @param orderno
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(int orderid, int roleid, String sendername, String supplierno, String guestno, Model model, HttpServletRequest request)throws Exception{
		Map<String, Object> attrs = new HashMap<String, Object>();
		//用waybillCode查询主运单
		String loadingnote = "";
		OrderDetail od = orderServiceImpl.getOrderDetailById(orderid);
		Joinorder jo = null;
		//主运单为空
		if(od == null) {
			//用waybillCode查询子运单列表
			List<Joinorder> joinorders = orderServiceImpl.listJoinOrderByloadingnote(orderid);
			//取子运单集合的第一条
			jo = joinorders.get(0);
			//根据子运单的joinid再次查询主运单
			if(jo != null)
				od = orderServiceImpl.listOrderDetailByheadId(jo.getJoinid()).get(0);
			//拼接子运单号
			for (Joinorder joinorder : joinorders) {
				loadingnote += joinorder.getLoadingnote() + "|";
			}
		} else {
		//主运单不为空，列出子运单
			List<Joinorder> joinorders = orderServiceImpl.listJoinOrderByJoinId(od.getId());
			if(joinorders != null)
				//拼接子运单号
				for (Joinorder joinorder : joinorders) {
					loadingnote += joinorder.getLoadingnote() + "|";
				}
		}
		if(od == null){
			attrs.put("msg", "该运单号无数据！");
			attrs.put("supplierno", supplierno);
			attrs.put("sendername", sendername);
			attrs.put("guestno", guestno);
			attrs.put("roleid", roleid);
			model.addAllAttributes(attrs);
			return "/search";
		}
		Orderhead oh = orderServiceImpl.getOrderheadById(od.getId());
		String noteStatus = orderServiceImpl.getOrderheadState(od.getLoadingnote());
		if(roleid == 4){
			if(!supplierno.equals(oh.getSupplierno())){
				attrs.put("msg", "该运单您无权查看");
				attrs.put("supplierno", supplierno);
				attrs.put("sendername", sendername);
				attrs.put("guestno", guestno);
				attrs.put("roleid", roleid);
				model.addAllAttributes(attrs);
				return "/search";
			}
		}
		if(roleid == 5){
			if(!guestno.equals(oh.getGuestno())){
				attrs.put("msg", "该运单您无权查看");
				attrs.put("supplierno", supplierno);
				attrs.put("sendername", sendername);
				attrs.put("guestno", guestno);
				attrs.put("roleid", roleid);
				model.addAllAttributes(attrs);
				return "/search";
			}
		}
		Customer customer = orderServiceImpl.getCustomerByCode(od.getCustomerno());
		Appsubmit appsubmit = new Appsubmit();
		if(oh.getTel() != null){
			appsubmit = appsubmitImpl.getAppsubmitByTel(oh.getTel());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(od.getShipdate() != null){
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			od.setShipdate_str(sdf1.format(od.getShipdate()));
		}
		if(od.getLoadindate() != null){
			od.setLoadindate_str(sdf.format(od.getLoadindate()));
		}
		if(od.getLoadoutdate() != null){
			od.setLoadoutdate_str(sdf.format(od.getLoadoutdate()));
		}
		if(od.getUnloadindate() != null){
			od.setUnloadindate_str(sdf.format(od.getUnloadindate()));
		}
		if(od.getUnloadoutdate() != null){
			od.setUnloadoutdate_str(sdf.format(od.getUnloadoutdate()));
		}
		double path = 0;
		String anomalyWay = "正常";
		try {
			if(noteStatus.equals("承运商未接收") || noteStatus.equals("已接收未调度") 
			|| noteStatus.equals("司机未接收") || noteStatus.equals("装货在途")){
				int day = DateUtil.printDifference(od.getShipdate(), new Date());
				int hour = DateUtil.printDifference1(od.getShipdate(), new Date());
				int minute = DateUtil.printDifference2(od.getShipdate(), new Date());
				minute = day * 24 * 60 + hour * 60 + minute;
				if(od.getLoadindate() == null && minute > 1440){
					anomalyWay = "车辆调度异常";
				}
			}
			if(noteStatus.equals("装货中")){
				int day = DateUtil.printDifference(od.getLoadindate(), new Date());
				int hour = DateUtil.printDifference1(od.getLoadindate(), new Date());
				int minute = DateUtil.printDifference2(od.getLoadindate(), new Date());
				minute = day * 24 * 60 + hour * 60 + minute;
				if(od.getLoadoutdate() == null && minute > 1440){
					anomalyWay = "装货压车";
				}
			}
			if(noteStatus.equals("送货在途")){
				//司机APP超时
				int day = DateUtil.printDifference(appsubmit.getMakedate(), new Date());
				int hour = DateUtil.printDifference1(appsubmit.getMakedate(), new Date());
				int minute = DateUtil.printDifference2(appsubmit.getMakedate(), new Date());
				minute = day * 24 * 60 + hour * 60 + minute;
				if(minute > 30 && oh.getDriverreceivedate() != null && od.getUnloadoutdate() == null && od.getLoadoutdate() != null && od.getUnloadindate() == null){
					anomalyWay = "司机APP超时";
				}
			}
			if(noteStatus.equals("收货中")){
				int day = DateUtil.printDifference(od.getUnloadindate(), new Date());
				int hour = DateUtil.printDifference1(od.getUnloadindate(), new Date());
				int minute = DateUtil.printDifference2(od.getUnloadindate(), new Date());
				minute = day * 24 * 60 + hour * 60 + minute;
				if(od.getUnloadoutdate() == null && minute > 1440){
					anomalyWay = "卸货压车";
				}
			}
			if(noteStatus.indexOf("送货在途") != -1){
				//送货在途
				path = MapDistance.getDistance((double)appsubmit.getLat(), (double)appsubmit.getLng(), Double.parseDouble(customer.getLat()), Double.parseDouble(customer.getLng()));
			}
			
			if(noteStatus.equals("装货在途")){
				//装货在途
				Source source = sourceServiceImpl.getSourceBySourceno(od.getSourceno());
				path = MapDistance.getDistance((double)appsubmit.getLat(), (double)appsubmit.getLng(), Double.parseDouble(source.getLat()), Double.parseDouble(source.getLng()));
			}
			attrs.put("path", path+"KM");
		} catch (Exception e) {
			// TODO: handle exception
			attrs.put("path", "暂无数据");
		}
		if(loadingnote.endsWith("|"))
			loadingnote = loadingnote.substring(0, loadingnote.length() - 1);
		attrs.put("loadingnote", loadingnote);
		attrs.put("noteStatus", noteStatus);
		attrs.put("anomalyWay", anomalyWay);
		model.addAllAttributes(attrs);
		model.addAttribute("customer", customer);
		model.addAttribute("orderhead", oh);
		model.addAttribute("orderDetail", od);
		return "/view";
	}
	
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String map(int orderid, Model model, HttpServletRequest request)throws Exception{
		Map<String, Object> attrs = new HashMap<String, Object>();
		List<OrderDetail> ods = orderServiceImpl.listOrderDetailById(orderid);
		Orderhead oh = orderServiceImpl.getOrderheadById(ods.get(0).getId());
		Customer customer = orderServiceImpl.getCustomerByCode(ods.get(0).getCustomerno());
		String noteStatus = orderServiceImpl.getOrderheadState(ods.get(0).getLoadingnote());
		Appsubmit appsubmit = new Appsubmit();
		if(oh.getTel() != null){
			appsubmit = appsubmitImpl.getAppsubmitByTel(oh.getTel());
		}
		model.addAttribute("customer", customer);
		attrs.put("noteStatus", noteStatus);
		attrs.put("transportation", ods.get(0).getTransportation());
		attrs.put("loadingnote", orderid);
		model.addAllAttributes(attrs);
		model.addAttribute("orderhead", oh);
		model.addAttribute("orderDetail", ods);
		return "/map";
	}
	
	@RequestMapping(value = "/unbindWx", method = RequestMethod.GET)
	public String unbindWx(HttpSession httpSession, Model model, HttpServletRequest request) throws Exception{
		String weixinCode = (String) httpSession.getAttribute("OPENID");
		if(weixinCode != null && !"".equals(weixinCode))
			wxMemberRelationServiceImpl.removRelation(weixinCode);
		return "/login";
	}
}
