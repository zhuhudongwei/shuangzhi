package com.wechat.controllor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.bean.DriverBean;
import com.app.bean.JoinorderBean;
import com.app.bean.ProductBean;
import com.app.bean.ResponeBean;
import com.app.bean.Trajectory;
import com.app.bean.ViewBillInfoBean;
import com.app.bean.WayBillTypeBean;
import com.app.bean.WaybillInfoBean;
import com.wechat.model.Appsubmit;
import com.wechat.model.AppsubmitDetail;
import com.wechat.model.Area;
import com.wechat.model.Customer;
import com.wechat.model.DcInfo;
import com.wechat.model.Driver;
import com.wechat.model.DriverTruck;
import com.wechat.model.Joinorder;
import com.wechat.model.Line;
import com.wechat.model.Operator;
import com.wechat.model.OrderDetail;
import com.wechat.model.Orderhead;
import com.wechat.model.Sms;
import com.wechat.model.Source;
import com.wechat.model.Supplier;
import com.wechat.model.Truck;
import com.wechat.service.AppsubmitService;
import com.wechat.service.DcInfoService;
import com.wechat.service.DriverService;
import com.wechat.service.OperatorService;
import com.wechat.service.OrderService;
import com.wechat.service.SmsService;
import com.wechat.service.SourceService;
import com.wechat.utils.DateUtil;
import com.wechat.utils.MapDistance;

/**
 * 访问控制器--承运商
 * @author zdw
 *
 */
@RestController
@RequestMapping("/carrier")
public class CarrierController {

	@Autowired
	private OperatorService operatorServiceImpl;
	
	@Autowired
	private OrderService orderServiceImpl;
	
	@Autowired
	private AppsubmitService appsubmitImpl;
	
	@Autowired
	private SourceService sourceServiceImpl;
	
	@Autowired
	private DriverService driverServiceImpl;
	
	@Autowired
	private SmsService smsServiceImpl;
	
	@Autowired
	private DcInfoService dcInfoServiceImpl;
	
	private static final int size = 15;
    
	/***
	 * 承运商首页数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/waybillInfoIndex", method = RequestMethod.GET)
	public Map waybillInfoIndex(String supplierno, String sendername, String guestno, HttpServletRequest request){
		Map ma = new HashMap();
		try {
			String []code_array = "1,3,2,4,5,6,7,10".split(",");
			String []name_array = "承运商未接收,已接收未调度,司机未接收,装货在途,装货中,送货在途,收货中,已签收".split(",");
			List<WayBillTypeBean> wbtbs = new ArrayList<WayBillTypeBean>();
			for (int i = 0; i < code_array.length; i++) {
				WayBillTypeBean wbtb = new WayBillTypeBean();
				wbtb.setCode(code_array[i]);
				wbtb.setTypename(name_array[i]);
				wbtbs.add(wbtb);
			}
			ma.put("waybillType", wbtbs);
			ma.put("state", 1);
		} catch (Exception e) {
			// TODO: handle exception
			ma.put("state", -1);
			ma.put("errormsg", e.getMessage());
			e.printStackTrace();
		}
		return ma;
	}
	
	/***
	 * 筛选
	 * @param area             区域
	 * @param beginshipDate    装运日期起
	 * @param endshipDate      装运日期终
	 * @param supplierno       承运商
	 * @param waybillCode      装运单号
	 * @param sourceno         发货仓库
	 * @param guestno          收货方
	 * @param sendername       发货方
	 * @param stauts           状态
	 * @param transportationNo 运输路线
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/waybillInfoIndex_size", method = RequestMethod.GET)
	public Map waybillInfoIndex_size(String area, Date beginshipDate, Date endshipDate, String waybillCode, String sourceno,
			String transportationNo, String supplierno, String sendername, String guestno, int stauts, HttpServletRequest request){
		Map ma = new HashMap();
		try {
			int totalnum = orderServiceImpl.countwaybillCode(area, beginshipDate, endshipDate, waybillCode, sourceno, guestno, supplierno, sendername, stauts, transportationNo);
//			int size = orderServiceImpl.searchOrderheadByState_size_v2(
//					area, beginshipDate, endshipDate, waybillCode, sourceno, transportationNo,
//					supplierno, sendername, guestno, stauts, null, null);
			ma.put("waybillTypeSize", totalnum);
			ma.put("state", 1);
		} catch (Exception e) {
			// TODO: handle exception
			ma.put("state", -1);
			ma.put("errormsg", e.getMessage());
			e.printStackTrace();
		}
		return ma;
	}

	/***
	 * 运单信息反馈
	 * @param waybillType
	 * @param waybillCode
	 * @param mobile
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/waybillInfoByState", method = RequestMethod.GET)
	public Map waybillInfoByState(String area, Date beginshipDate, Date endshipDate, String waybillCode, String sourceno,
			String transportationNo, String supplierno, String sendername, String guestno, int stauts, int pageNo, HttpServletRequest request){
		Map ma = new HashMap();
		try {
			List<Orderhead> orderheads = orderServiceImpl.searchwaybillCode(area, beginshipDate, endshipDate, waybillCode, sourceno, guestno, supplierno, sendername, stauts, transportationNo, pageNo, size);
			List<WaybillInfoBean> wbibs = new ArrayList<WaybillInfoBean>();
			for (int i = 0; i < orderheads.size(); i++) {
				WaybillInfoBean wbib = new WaybillInfoBean();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(stauts == 13){
					//APP超时
					String tel = orderheads.get(i).getTel();
					Appsubmit appsubmit = new Appsubmit();
					if(tel != null){
						appsubmit = appsubmitImpl.getAppsubmitByTel(orderheads.get(i).getTel());
					}
					
					if(appsubmit != null){
						int day = DateUtil.printDifference(appsubmit.getMakedate(), new Date());
						int hours = DateUtil.printDifference1(appsubmit.getMakedate(), new Date());
						int minute = DateUtil.printDifference2(appsubmit.getMakedate(), new Date());
						String outTime = day + "天" + hours + "小时" + minute + "分钟";
						if(day > 0 || hours > 0 || minute > 30){
							wbib = new WaybillInfoBean();
							wbib.setWaybillCode(orderheads.get(i).getLoadingnote2());
							wbib.setDeliveryTime(sdf.format(orderheads.get(i).getShipdate2()));
							wbib.setOutTime(outTime);
							wbib.setTransportationLine(orderheads.get(i).getTransportation2());
							wbib.setCarrierName(orderheads.get(i).getSuppliername());
							wbibs.add(wbib);
						}
					}else{
						wbib = new WaybillInfoBean();
						wbib.setWaybillCode(orderheads.get(i).getLoadingnote2());
						if(orderheads.get(i).getShipdate2() != null){
							wbib.setDeliveryTime(sdf.format(orderheads.get(i).getShipdate2()));
						}else{
							wbib.setDeliveryTime(null);
						}
						wbib.setTransportationLine(orderheads.get(i).getTransportation2());
						wbib.setCarrierName(orderheads.get(i).getSuppliername());
						wbibs.add(wbib);
					}
				}else{
					String yacheTime = null;
					if(stauts == 12){
//						int day = DateUtil.printDifference(ods.get(0).getLoadindate(), new Date());
//						int hour = DateUtil.printDifference1(ods.get(0).getLoadindate(), new Date());
//						int minute = DateUtil.printDifference2(ods.get(0).getLoadindate(), new Date());
						yacheTime = orderheads.get(i).getInExpDay()+"天";
					}
					if(stauts == 14){
//						int day = DateUtil.printDifference(ods.get(0).getUnloadindate(), new Date());
//						int hour = DateUtil.printDifference1(ods.get(0).getUnloadindate(), new Date());
//						int minute = DateUtil.printDifference2(ods.get(0).getUnloadindate(), new Date());
						yacheTime = orderheads.get(i).getOutExpDay()+"天";
					}
					wbib = new WaybillInfoBean();
					wbib.setWaybillCode(orderheads.get(i).getLoadingnote2());
					if(orderheads.get(i).getShipdate2() != null){
						wbib.setDeliveryTime(sdf.format(orderheads.get(i).getShipdate2()));
					}else{
						wbib.setDeliveryTime(null);
					}
					wbib.setYacheTime(yacheTime);
					wbib.setTransportationLine(orderheads.get(i).getTransportation2());
					wbib.setCarrierName(orderheads.get(i).getSuppliername());
					wbibs.add(wbib);
				}
//				if(ods.size() != 0){
//					
//					wbib.setWaybillCode(ods.get(0).getLoadingnote());
//					wbib.setDeliveryTime(sdf.format(ods.get(0).getShipdate()));
//				}
//				wbib.setTransportationLine(ods.get(0).getTransportation());
//				wbib.setCarrierName(orderheads.get(i).getSuppliername());
//				wbibs.add(wbib);
			}
			ma.put("WaybillInfoBeans", wbibs);
			int totalnum = orderServiceImpl.countwaybillCode(area, beginshipDate, endshipDate, waybillCode, sourceno, guestno, supplierno, sendername, stauts, transportationNo);
			ma.put("totalnum", totalnum);
			ma.put("state", 1);
		} catch (Exception e) {
			// TODO: handle exception
			ma.put("state", -1);
			ma.put("errormsg", e.getMessage());
		}
		return ma;
	}
	
	/**
	 * 运单详情——货物明细
	 * @param waybillCode
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/waybillInfoProducts", method = RequestMethod.GET)
	public Map waybillInfoProducts(String supplierno, String sendername, String guestno, String waybillCode, HttpServletRequest request){
		Map ma = new HashMap();
		ViewBillInfoBean vbib = new ViewBillInfoBean();
		try {
			OrderDetail od = orderServiceImpl.getOrderDetailById(Integer.parseInt(waybillCode));
	
			Orderhead oh = orderServiceImpl.getOrderheadById(od.getId());
			boolean ismanager = true;
			if(supplierno != null){
				if(!supplierno.equals(oh.getSupplierno())){
					ismanager = false;
				}
			}
			if(guestno != null){
				if(!guestno.equals(oh.getGuestno())){
					ismanager = false;
				}
			}
			if(ismanager){
				String noteStatus = orderServiceImpl.getOrderheadState(od.getLoadingnote());
				Customer customer = orderServiceImpl.getCustomerByCode(od.getCustomerno());
				Appsubmit appsubmit = new Appsubmit();
				vbib.setWaybillCode(waybillCode);
				if(oh.getTel() != null){
					appsubmit = appsubmitImpl.getAppsubmitByTel(oh.getTel());
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(od.getShipdate() != null){
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
					vbib.setExpectedDeliveryTime(sdf1.format(od.getShipdate()));
				}
				if(od.getLoadindate() != null){
					vbib.setArriveLoadingTime(sdf.format(od.getLoadindate()));
				}
				if(od.getLoadoutdate() != null){
					vbib.setLoadingOverTime(sdf.format(od.getLoadoutdate()));
				}
				if(od.getUnloadindate() != null){
					vbib.setExpectedTakeDeliveryTime(sdf.format(od.getUnloadindate()));
				}
				if(od.getUnloadoutdate() != null){
					vbib.setTakeDeliveryOverTime(sdf.format(od.getUnloadoutdate()));
				}
				double path = 0;
				vbib.setAnomalyWay("正常");
				vbib.setPath("暂无数据");
				vbib.setWaybillstate(noteStatus);
				if(noteStatus.equals("承运商未接收") || noteStatus.equals("已接收未调度") 
				|| noteStatus.equals("司机未接收") || noteStatus.equals("装货在途")){
					if(noteStatus.equals("装货在途")){
						vbib.setWaybillstate("装货在途");
					}
					int day = DateUtil.printDifference(od.getShipdate(), new Date());
					int hour = DateUtil.printDifference1(od.getShipdate(), new Date());
					int minute = DateUtil.printDifference2(od.getShipdate(), new Date());
					minute = day * 24 * 60 + hour * 60 + minute;
					if(od.getLoadindate() == null && minute > 1440){
						vbib.setAnomalyWay("车辆调度异常");
					}
				}
				if(noteStatus.equals("装货中")){
					if(noteStatus.equals("装货中")){
						vbib.setWaybillstate("装货中");
					}
					int day = DateUtil.printDifference(od.getLoadindate(), new Date());
					int hour = DateUtil.printDifference1(od.getLoadindate(), new Date());
					int minute = DateUtil.printDifference2(od.getLoadindate(), new Date());
					minute = day * 24 * 60 + hour * 60 + minute;
					if(od.getLoadoutdate() == null && minute > 1440){
						vbib.setAnomalyWay("装货压车");
					}
				}
				if(noteStatus.equals("送货在途")){
					if(noteStatus.equals("送货在途")){
						vbib.setWaybillstate("送货在途");
					}
					//司机APP超时
					int day = DateUtil.printDifference(appsubmit.getMakedate(), new Date());
					int hour = DateUtil.printDifference1(appsubmit.getMakedate(), new Date());
					int minute = DateUtil.printDifference2(appsubmit.getMakedate(), new Date());
					minute = day * 24 * 60 + hour * 60 + minute;
					if(minute > 30 && oh.getDriverreceivedate() != null && od.getUnloadoutdate() == null && od.getLoadoutdate() != null && od.getUnloadindate() == null){
						vbib.setAnomalyWay("司机APP超时");
					}
				}
				if(noteStatus.equals("收货中")){
					if(noteStatus.equals("收货中")){
						vbib.setWaybillstate("收货中");
					}
					int day = DateUtil.printDifference(od.getUnloadindate(), new Date());
					int hour = DateUtil.printDifference1(od.getUnloadindate(), new Date());
					int minute = DateUtil.printDifference2(od.getUnloadindate(), new Date());
					minute = day * 24 * 60 + hour * 60 + minute;
					if(od.getUnloadoutdate() == null && minute > 1440){
						vbib.setAnomalyWay("收货压车");
					}
				}
				if(noteStatus.indexOf("送货在途") != -1){
					//送货在途
					if(appsubmit != null){
						path = MapDistance.getDistance((double)appsubmit.getLat(), (double)appsubmit.getLng(), Double.parseDouble(customer.getLat()), Double.parseDouble(customer.getLng()));
					}
					vbib.setPath(path+"KM");
				}
				
				if(noteStatus.equals("装货在途")){
					//装货在途
					Source source = sourceServiceImpl.getSourceBySourceno(od.getSourceno());
					if(appsubmit != null){
						path = MapDistance.getDistance((double)appsubmit.getLat(), (double)appsubmit.getLng(), Double.parseDouble(source.getLat()), Double.parseDouble(source.getLng()));
					}
					vbib.setPath(path+"KM");
				}
				vbib.setMotoristName(oh.getDrivername());
				vbib.setMotoristMobile(oh.getTel());
				vbib.setCarNo(oh.getTruckno());
				vbib.setTrucktype(oh.getTrucktype());
				vbib.setTransportationLine(od.getTransportation());
				vbib.setCarrierName(oh.getSuppliername());
				vbib.setDeliveryFactory(od.getSourcename());
				vbib.setTakeDeliveryFactory(od.getCustomername());
				vbib.setTakeDeliveryName(oh.getReceiveman());
				vbib.setTakeDeliveryMobile(oh.getReceivetel());
				vbib.setProductname(od.getProductname());
				if(customer != null){
					vbib.setAddress(customer.getAddr());
				}
				List<ProductBean> productBeans = new ArrayList<ProductBean>();
				List<OrderDetail> ods = orderServiceImpl.listOrderDetailByheadId(oh.getId());
				for (int i = 0; i < ods.size(); i++) {
					ProductBean pb = new ProductBean();
					pb.setProductno(ods.get(i).getProductno());
					pb.setPlanquantity(ods.get(i).getPlanquantity());
					pb.setProductname(ods.get(i).getProductname());
					pb.setLoadnum(ods.get(i).getLoadnum());
					pb.setSignnum(ods.get(i).getSignnum());
					productBeans.add(pb);
				}
				vbib.setProductBeans(productBeans);
				ma.put("ViewBillInfoBean", vbib);
				ma.put("state", 1);
			}else{
				ma.put("state", -2);
				ma.put("errormsg", "无权限");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ma.put("state", -1);
			ma.put("errormsg", "无订单详情信息");
		}
		return ma;
	}
	
	/***
	 * 查看运单明细详情
	 * @param waybillCode
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/waybillInfo", method = RequestMethod.GET)
	public Map waybillInfo(String supplierno, String sendername, String guestno, String waybillCode, HttpServletRequest request){
		Map ma = new HashMap();
		ViewBillInfoBean vbib = new ViewBillInfoBean();
		try {
			String loadingnote = "";
			//用waybillCode查询主运单
			OrderDetail od = orderServiceImpl.getOrderDetailById(Integer.parseInt(waybillCode));
			Joinorder jo = null;
			//主运单为空
			if(od == null) {
				//用waybillCode查询子运单列表
				List<Joinorder> joinorders = orderServiceImpl.listJoinOrderByloadingnote(Integer.parseInt(waybillCode));
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
			Orderhead oh = orderServiceImpl.getOrderheadById(od.getId());
			boolean ismanager = true;
			if(supplierno != null){
				if(!supplierno.equals(oh.getSupplierno())){
					ismanager = false;
				}
			}
			if(guestno != null){
				if(!guestno.equals(oh.getGuestno())){
					ismanager = false;
				}
			}
			if(ismanager){
				String noteStatus = orderServiceImpl.getOrderheadState(od.getLoadingnote());
				Customer customer = orderServiceImpl.getCustomerByCode(od.getCustomerno());
				Appsubmit appsubmit = new Appsubmit();
				vbib.setWaybillCode(od.getLoadingnote());
				if(oh.getTel() != null){
					appsubmit = appsubmitImpl.getAppsubmitByTel(oh.getTel());
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(od.getShipdate() != null){
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
					vbib.setExpectedDeliveryTime(sdf1.format(od.getShipdate()));
				}
				if(od.getLoadindate() != null){
					vbib.setArriveLoadingTime(sdf.format(od.getLoadindate()));
				}
				if(od.getLoadoutdate() != null){
					vbib.setLoadingOverTime(sdf.format(od.getLoadoutdate()));
				}
				if(od.getUnloadindate() != null){
					vbib.setExpectedTakeDeliveryTime(sdf.format(od.getUnloadindate()));
				}
				if(od.getUnloadoutdate() != null){
					vbib.setTakeDeliveryOverTime(sdf.format(od.getUnloadoutdate()));
				}
				double path = 0;
				vbib.setAnomalyWay("正常");
				vbib.setPath("暂无数据");
				vbib.setWaybillstate(noteStatus);
				if(noteStatus.equals("承运商未接收") || noteStatus.equals("已接收未调度") 
				|| noteStatus.equals("司机未接收") || noteStatus.equals("装货在途")){
					if(noteStatus.equals("装货在途")){
						vbib.setWaybillstate("装货在途");
					}
					int day = DateUtil.printDifference(od.getShipdate(), new Date());
					int hour = DateUtil.printDifference1(od.getShipdate(), new Date());
					int minute = DateUtil.printDifference2(od.getShipdate(), new Date());
					minute = day * 24 * 60 + hour * 60 + minute;
					if(od.getLoadindate() == null && minute > 1440){
						vbib.setAnomalyWay("车辆调度异常");
					}
				}
				if(noteStatus.equals("装货中")){
					if(noteStatus.equals("装货中")){
						vbib.setWaybillstate("装货中");
					}
					int day = DateUtil.printDifference(od.getLoadindate(), new Date());
					int hour = DateUtil.printDifference1(od.getLoadindate(), new Date());
					int minute = DateUtil.printDifference2(od.getLoadindate(), new Date());
					minute = day * 24 * 60 + hour * 60 + minute;
					if(od.getLoadoutdate() == null && minute > 1440){
						vbib.setAnomalyWay("装货压车");
					}
				}
				if(noteStatus.equals("送货在途")){
					if(noteStatus.equals("送货在途")){
						vbib.setWaybillstate("送货在途");
					}
					//司机APP超时
					int day = DateUtil.printDifference(appsubmit.getMakedate(), new Date());
					int hour = DateUtil.printDifference1(appsubmit.getMakedate(), new Date());
					int minute = DateUtil.printDifference2(appsubmit.getMakedate(), new Date());
					minute = day * 24 * 60 + hour * 60 + minute;
					if(minute > 30 && oh.getDriverreceivedate() != null && od.getUnloadoutdate() == null && od.getLoadoutdate() != null && od.getUnloadindate() == null){
						vbib.setAnomalyWay("司机APP超时");
					}
				}
				if(noteStatus.equals("收货中")){
					if(noteStatus.equals("收货中")){
						vbib.setWaybillstate("收货中");
					}
					int day = DateUtil.printDifference(od.getUnloadindate(), new Date());
					int hour = DateUtil.printDifference1(od.getUnloadindate(), new Date());
					int minute = DateUtil.printDifference2(od.getUnloadindate(), new Date());
					minute = day * 24 * 60 + hour * 60 + minute;
					if(od.getUnloadoutdate() == null && minute > 1440){
						vbib.setAnomalyWay("收货压车");
					}
				}
				if(noteStatus.indexOf("送货在途") != -1){
					//送货在途
					if(appsubmit != null){
						path = MapDistance.getDistance((double)appsubmit.getLat(), (double)appsubmit.getLng(), Double.parseDouble(customer.getLat()), Double.parseDouble(customer.getLng()));
					}
					vbib.setPath(path+"KM");
				}
				
				if(noteStatus.equals("装货在途")){
					//装货在途
					Source source = sourceServiceImpl.getSourceBySourceno(od.getSourceno());
					if(appsubmit != null){
						path = MapDistance.getDistance((double)appsubmit.getLat(), (double)appsubmit.getLng(), Double.parseDouble(source.getLat()), Double.parseDouble(source.getLng()));
					}
					vbib.setPath(path+"KM");
				}
				vbib.setMotoristName(oh.getDrivername());
				vbib.setMotoristMobile(oh.getTel());
				vbib.setCarNo(oh.getTruckno());
				vbib.setTrucktype(oh.getTrucktype());
				vbib.setTransportationLine(od.getTransportation());
				vbib.setCarrierName(oh.getSuppliername());
				vbib.setDeliveryFactory(od.getSourcename());
				vbib.setTakeDeliveryFactory(od.getCustomername());
				vbib.setTakeDeliveryName(oh.getReceiveman());
				vbib.setTakeDeliveryMobile(oh.getReceivetel());
				vbib.setProductname(od.getProductname());
				if(customer != null){
					vbib.setAddress(customer.getAddr());
				}
				List<ProductBean> productBeans = new ArrayList<ProductBean>();
				List<OrderDetail> ods = orderServiceImpl.listOrderDetailByheadId(oh.getId());
				for (int i = 0; i < ods.size(); i++) {
					ProductBean pb = new ProductBean();
					pb.setProductno(ods.get(i).getProductno());
					pb.setPlanquantity(ods.get(i).getPlanquantity());
					pb.setProductname(ods.get(i).getProductname());
					pb.setLoadnum(ods.get(i).getLoadnum());
					pb.setSignnum(ods.get(i).getSignnum());
					productBeans.add(pb);
				}
				vbib.setProductBeans(productBeans);
				ma.put("ViewBillInfoBean", vbib);
				ma.put("customerno", od.getCustomerno());
				ma.put("supplierno", od.getSupplierno());
				if(loadingnote.endsWith("|"))
					loadingnote = loadingnote.substring(0, loadingnote.length() - 1);
				ma.put("loadingnote", loadingnote);
				ma.put("state", 1);
			}else{
				ma.put("state", -2);
				ma.put("errormsg", "无权限");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ma.put("state", -1);
			ma.put("errormsg", "无订单详情信息");
		}
		return ma;
	}
	
	/***
	 * 获取司机信息
	 * @param code 供应室或者承运商
	 * @param mobile
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/getDriver", method = RequestMethod.GET)
	public Map getDriver(String code,String mobile, HttpServletRequest request){
		Map ma = new HashMap();
		List<DriverBean> driverBeans = new ArrayList<DriverBean>();
		try {
			Supplier supplier = operatorServiceImpl.getSupplierByUsername(code);//查找承运商
			if(supplier != null){
				List<DriverTruck> listDriverTrucks = driverServiceImpl.listDriverTruck(mobile,code);
//				Driver driver = driverServiceImpl.getDriver(mobile);
				if(listDriverTrucks != null && listDriverTrucks.size() > 0){
					Integer[] status = {3,4};
					List<Orderhead> orderheads = orderServiceImpl.getOrderheadByTEL(mobile, status);
					if(orderheads != null && orderheads.size() > 0){
						ma.put("state", 4);//标示该司机在送货途中
					}else{
						for (int i = 0; i < listDriverTrucks.size(); i++) {
							DriverBean db = new DriverBean();
							db.setIdcard(listDriverTrucks.get(i).getCard());
							db.setName(listDriverTrucks.get(i).getDriverName());
							db.setTel(listDriverTrucks.get(i).getTel());
							db.setCarNo(listDriverTrucks.get(i).getTruckName());
							db.setTrucktype(listDriverTrucks.get(i).getTruckType());
							driverBeans.add(db);
						}
						ma.put("DriverBeans", driverBeans);
						ma.put("state", 1);
					}
				}else{
					ma.put("state", 0);//标示该司机不在本承运商下
				}
				
//				for (int i = 0; i < listDriverTrucks.size(); i++) {
//					if(!listDriverTrucks.get(i).getSupplierno().equals(code)){
//						ma.put("state", 0);//标示该司机不在本承运商下
//					}else{
//						Integer[] status = {3,4};
//						List<Orderhead> orderheads = orderServiceImpl.getOrderheadByTEL(mobile, status);
//						if(orderheads != null && orderheads.size() > 0){
//							ma.put("state", 4);//标示该司机在送货途中
//						}else{
//							Truck truck = driverServiceImpl.getTruck(mobile);
//							DriverBean db = new DriverBean();
//							db.setIdcard(driver.getIdcard());
//							db.setName(driver.getName());
//							db.setTel(driver.getTel());
//							db.setCarNo(truck.getNAME());
//							db.setTrucktype(truck.getTRUCKTYPE());
//							ma.put("DriverBean", db);
//							ma.put("state", 1);
//						}
//					}
//				}
				
			}else{//非承运商的处理
				Integer[] status = {3,4};
				List<Orderhead> orderheads = orderServiceImpl.getOrderheadByTEL(mobile, status);
				if(orderheads != null && orderheads.size() > 0){
					ma.put("state", 4);//标示该司机在送货途中
				}else{
//					Driver driver = driverServiceImpl.getDriver(mobile);
//					Truck truck = driverServiceImpl.getTruck(mobile);
					List<DriverTruck> listDriverTrucks = driverServiceImpl.listDriverTruck(mobile,null);
					if(listDriverTrucks != null && listDriverTrucks.size() > 0){
						for (int i = 0; i < listDriverTrucks.size(); i++) {
							DriverBean db = new DriverBean();
							db.setIdcard(listDriverTrucks.get(i).getCard());
							db.setName(listDriverTrucks.get(i).getDriverName());
							db.setTel(listDriverTrucks.get(i).getTel());
							db.setCarNo(listDriverTrucks.get(i).getTruckName());
							db.setTrucktype(listDriverTrucks.get(i).getTruckType());
							driverBeans.add(db);
						}
						ma.put("DriverBeans", driverBeans);
						ma.put("state", 1);
					}
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			ma.put("state", -1);
			ma.put("errormsg", e.getMessage());
		}
		return ma;
	}
	
	/**
	 * 调度或接单
	 * @param waybillCode
	 * @param carNo
	 * @param carModels
	 * @param driver
	 * @param mobile
	 * @param idCard
	 * @param orderStates 3==已接收未调度，2==司机未接收,0==修改司机信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/wayBillDispatchOrOrder", method = RequestMethod.POST)
	public Map wayBillDispatchOrOrder(String waybillCode, String carNo, String carModels, String driver, String mobile, String idCard, int orderStates, HttpServletRequest request){
		Map ma = new HashMap();
		try {
			final String code = waybillCode;
			List<OrderDetail> ods = orderServiceImpl.listOrderDetailById(Integer.parseInt(waybillCode));
			if(ods.size() != 0){
				int id = ods.get(0).getId();
				Orderhead orderhead = orderServiceImpl.getOrderheadById(id);
				orderhead.setTruckno(carNo);
				orderhead.setTrucktype(carModels);
				orderhead.setDrivername(driver);
				orderhead.setTel(mobile);
				orderhead.setIdcard(idCard);
				if(orderStates == 3){
					orderhead.setReceivedate(new Date());
				}
				if(orderStates == 4){
					orderhead.setControldate(new Date());
				}
				if(orderStates != 0){
					orderhead.setStatus(orderStates);
				}
				if(orderStates == 2){
					orderhead.setDriverreceivedate(null);
				}
				orderServiceImpl.updateOrderhead(orderhead);
				ma.put("state", 1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			ma.put("state", -1);
			ma.put("errormsg", e.getMessage());
		}
		return ma;
	}
	
	/***
	 * 异常运单首页
	 * @param supplierno
	 * @param sendername
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/anomalyWaybillIndex", method = RequestMethod.GET)
	public Map anomalyWaybillIndex(String supplierno, String sendername, HttpServletRequest request){
		Map ma = new HashMap();
		try {
			String []code_array = "11,12,13,14".split(",");
			String []name_array = "车辆调度异常,装货压车,司机APP超时,收货压车".split(",");
			List<WayBillTypeBean> wbtbs = new ArrayList<WayBillTypeBean>();
			for (int i = 0; i < code_array.length; i++) {
				WayBillTypeBean wbtb = new WayBillTypeBean();
				wbtb.setCode(code_array[i]);
				wbtb.setTypename(name_array[i]);
				wbtbs.add(wbtb);
			}
			ma.put("anomalywaybillType", wbtbs);
			ma.put("state", 1);
		} catch (Exception e) {
			// TODO: handle exception
			ma.put("state", -1);
			ma.put("errormsg", e.getMessage());
		}
		return ma;
	}
	
	@RequestMapping(value ="/anomalyWaybillIndex_size", method = RequestMethod.GET)
	public Map anomalyWaybillIndex_size(String area, Date beginshipDate, Date endshipDate, String waybillCode, String sourceno,
			String transportationNo, String supplierno, String sendername, String guestno, int stauts, HttpServletRequest request){
		Map ma = new HashMap();
		try {
//			Date d = new Date();
//			final Date nowD = new Date(d.getTime() - (1 * 24 * 60 * 60 * 1000)); 
//			int size = 0;
//			size = orderServiceImpl.searchOrderhead_anomalyByState_size_v2(
//					area, beginshipDate, endshipDate, waybillCode, sourceno, transportationNo,
//					supplierno, sendername, guestno, stauts, nowD, null, null);
			int totalnum = orderServiceImpl.countwaybillCode(area, beginshipDate, endshipDate, waybillCode, sourceno, guestno, supplierno, sendername, stauts, transportationNo);
			ma.put("anomalywaybillTypeSize", totalnum);
			ma.put("state", 1);
		} catch (Exception e) {
			// TODO: handle exception
			ma.put("state", -1);
			ma.put("errormsg", e.getMessage());
		}
		return ma;
	}
	
	/***
	 * 异常运单
	 * @param supplierno
	 * @param sendername
	 * @param stauts
	 * @param pageNo
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/anomalyWaybill", method = RequestMethod.GET)
	public Map anomalyWaybill(String area, Date beginshipDate, Date endshipDate, String waybillCode, String sourceno,
			String transportationNo, String supplierno, String sendername, String guestno, int stauts, int pageNo, HttpServletRequest request){
		Map ma = new HashMap();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d=new Date();
			final Date nowD = new Date(d.getTime() - (1 * 24 * 60 * 60 * 1000)); 
			List<Orderhead> orderheads = orderServiceImpl.searchwaybillCode(area, beginshipDate, endshipDate, waybillCode, sourceno, guestno, supplierno, sendername, stauts, transportationNo, pageNo, size);
			List<WaybillInfoBean> wbibs = new ArrayList<WaybillInfoBean>();
			for (int i = 0; i < orderheads.size(); i++) {
				List<OrderDetail> ods = orderServiceImpl.listOrderDetailByheadId(orderheads.get(i).getId());
				Customer customer = orderServiceImpl.getCustomerByCode(ods.get(0).getCustomerno());
				if(stauts == 3){
					//APP超时
					String tel = orderheads.get(i).getTel();
					Appsubmit appsubmit = new Appsubmit();
					if(tel != null){
						appsubmit = appsubmitImpl.getAppsubmitByTel(orderheads.get(i).getTel());
					}
					
					if(appsubmit != null){
						int day = DateUtil.printDifference(appsubmit.getMakedate(), new Date());
						int hours = DateUtil.printDifference1(appsubmit.getMakedate(), new Date());
						int minute = DateUtil.printDifference2(appsubmit.getMakedate(), new Date());
						String outTime = day + "天" + hours + "小时" + minute + "分钟";
						if(day > 0 || hours > 0 || minute > 30){
							WaybillInfoBean wbib = new WaybillInfoBean();
							if(ods.size() != 0){
								wbib.setWaybillCode(ods.get(0).getLoadingnote());
								wbib.setDeliveryTime(sdf.format(ods.get(0).getShipdate()));
							}
							wbib.setOutTime(outTime);
							wbib.setTransportationLine(ods.get(0).getTransportation());
							wbib.setCarrierName(orderheads.get(i).getSuppliername());
							wbibs.add(wbib);
						}
					}else{
						WaybillInfoBean wbib = new WaybillInfoBean();
						if(ods.size() != 0){
							wbib.setWaybillCode(ods.get(0).getLoadingnote());
							wbib.setDeliveryTime(sdf.format(ods.get(0).getShipdate()));
						}
						wbib.setTransportationLine(ods.get(0).getTransportation());
						wbib.setCarrierName(orderheads.get(i).getSuppliername());
						wbibs.add(wbib);
					}
				}else{
					String yacheTime = null;
					if(stauts == 2){
						int day = DateUtil.printDifference(ods.get(0).getLoadindate(), new Date());
						int hour = DateUtil.printDifference1(ods.get(0).getLoadindate(), new Date());
						int minute = DateUtil.printDifference2(ods.get(0).getLoadindate(), new Date());
						yacheTime = day + "天" + hour + "小时" + minute + "分钟";
					}
					if(stauts == 4){
						int day = DateUtil.printDifference(ods.get(0).getUnloadindate(), new Date());
						int hour = DateUtil.printDifference1(ods.get(0).getUnloadindate(), new Date());
						int minute = DateUtil.printDifference2(ods.get(0).getUnloadindate(), new Date());
						yacheTime = day + "天" + hour + "小时" + minute + "分钟";
					}
					WaybillInfoBean wbib = new WaybillInfoBean();
					if(ods.size() != 0){
						wbib.setWaybillCode(ods.get(0).getLoadingnote());
						wbib.setDeliveryTime(sdf.format(ods.get(0).getShipdate()));
					}
					wbib.setYacheTime(yacheTime);
					wbib.setTransportationLine(ods.get(0).getTransportation());
					wbib.setCarrierName(orderheads.get(i).getSuppliername());
					wbibs.add(wbib);
				}
			}
			ma.put("orderheads", wbibs);
			int totalnum = orderServiceImpl.countwaybillCode(area, beginshipDate, endshipDate, waybillCode, sourceno, guestno, supplierno, sendername, stauts, transportationNo);
			ma.put("totalnum", totalnum);
			ma.put("state", 1);
		} catch (Exception e) {
			// TODO: handle exception
			ma.put("state", -1);
			ma.put("errormsg", e.getMessage());
		}
		return ma;
	}
	
	/***
	 * 轨迹
	 * @param motoristMobile
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/trajectory", method = RequestMethod.GET)
	public Map trajectory(String waybillCode, HttpServletRequest request){
		Map ma = new HashMap();
		try {
			List<OrderDetail> ods = orderServiceImpl.listOrderDetailById(Integer.parseInt(waybillCode));
			Date driverreceivedate = null;//司机接收时间
			Date expectedTakeDeliveryTime = new Date();//到达收货工厂时间
			String motoristMobile = null;
			if(ods.size() != 0){
				int id = ods.get(0).getId();
				Orderhead orderhead = orderServiceImpl.getOrderheadById(id);
				List<Trajectory> trajectorys_depot = new ArrayList<Trajectory>();
				Source source = sourceServiceImpl.getSourceBySourceno(orderhead.getSourceno2());
				Trajectory t = new Trajectory();
				t.setLat(Double.valueOf(source.getLat()));
				t.setLng(Double.valueOf(source.getLng()));
				trajectorys_depot.add(t);
				DcInfo dcInfo = dcInfoServiceImpl.getDcInfoByTransportno(orderhead.getTransportationno2());
				t = new Trajectory();
				t.setLat(Double.valueOf(dcInfo.getLat()));
				t.setLng(Double.valueOf(dcInfo.getLng()));
				trajectorys_depot.add(t);
				ma.put("trajectorys_depot", trajectorys_depot);
				driverreceivedate = orderhead.getDriverreceivedate();
				if(ods.get(0).getUnloadindate() != null){
					expectedTakeDeliveryTime = ods.get(0).getUnloadindate();
				}
				motoristMobile = orderhead.getTel();
			}
			if(motoristMobile !=null && driverreceivedate!=null && expectedTakeDeliveryTime!=null){
				List<AppsubmitDetail> appsubmitDetails  = appsubmitImpl.listAppsubmitDetail(motoristMobile, driverreceivedate, expectedTakeDeliveryTime);
				List<Trajectory> trajectorys = new ArrayList<Trajectory>();
				for (int i = 0; i < appsubmitDetails.size(); i++) {
					Trajectory t = new Trajectory();
					t.setDriverid(appsubmitDetails.get(i).getDriverid());
					t.setTel(appsubmitDetails.get(i).getTel());
					t.setLat(appsubmitDetails.get(i).getLat());
					t.setLng(appsubmitDetails.get(i).getLng());
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					t.setTime(sdf1.format(appsubmitDetails.get(i).getMakedate()));
					trajectorys.add(t);
				}
				ma.put("trajectorys", trajectorys);
				ma.put("size", trajectorys.size());
				ma.put("state", 1);
			}else{
				ma.put("state", -1);
				ma.put("errormsg", "尚未发车！");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			ma.put("state", -1);
			ma.put("errormsg", e.getMessage());
		}
		return ma;
	}
	
	/**
	 * 合并订单
	 * @param id：主运单号
	 * @param joinid：关联运单号
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/mergerWaybill", method = RequestMethod.GET)
	public Map mergerWaybill(String waybillCode, String joinwaybillCode, HttpServletRequest request){
		Map ma = new HashMap();
		if(waybillCode != null && joinwaybillCode != null){
			if(waybillCode.length() != 0 && joinwaybillCode.length() != 0){
				try {
					List<OrderDetail> ods = orderServiceImpl.listOrderDetailById(Integer.parseInt(waybillCode));//主运单
					List<OrderDetail> joinods = orderServiceImpl.listOrderDetailById(Integer.parseInt(joinwaybillCode));//子运单
					Joinorder joinorder = new Joinorder();
					joinorder.setJoinid(joinods.get(0).getId());
					joinorder.setId(ods.get(0).getId());
					//修改以前的子单为主运单的运单
					orderServiceImpl.updateJoinorder(joinorder);
					Orderhead orderhead = orderServiceImpl.getOrderheadById(joinods.get(0).getId());//子运单所属的主运单
					joinorder = new Joinorder();
					joinorder.setId(joinods.get(0).getId());//子运单Id
					joinorder.setJoinid(ods.get(0).getId());//主运单的id
					joinorder.setLoadingnote(joinwaybillCode);//子运单号
					joinorder.setCustomername(joinods.get(0).getCustomername());
					joinorder.setCustomerno(joinods.get(0).getCustomerno());
					joinorder.setTransportationno(joinods.get(0).getTransportationno());
					joinorder.setTransportation(joinods.get(0).getTransportation());
					joinorder.setSourcename(joinods.get(0).getSourcename());
					joinorder.setSourceno(joinods.get(0).getSourceno());
					joinorder.setSupplierno(joinods.get(0).getSupplierno());
					joinorder.setSuppliername(joinods.get(0).getSuppliername());
					joinorder.setTransmode(joinods.get(0).getTransmode());
					joinorder.setShipdate(joinods.get(0).getShipdate());
					joinorder.setGropid(joinods.get(0).getGropid());
					joinorder.setCorpid(joinods.get(0).getCorpid());
					joinorder.setMakedate(orderhead.getMakedate());
					joinorder.setEta(new Date());
					joinorder.setJoinnote("");
					joinorder.setOrderno("");
					orderServiceImpl.addJoinorder(joinorder);//增加合并运单记录
					//保存子运单内容
					orderServiceImpl.addOrderheadBAK(orderhead);
					for (int i = 0; i < joinods.size(); i++) {
						orderServiceImpl.addOrderDetailBAK(joinods.get(i));
					}
					//删除子运单
					orderServiceImpl.delOrderhead(orderhead.getId());
					orderServiceImpl.delOrderDetail(joinods.get(0).getLoadingnote());
					//显示合并列表
					//用waybillCode查询主运单
					OrderDetail od = orderServiceImpl.getOrderDetailById(Integer.parseInt(waybillCode));
					List<Joinorder> joinorders = orderServiceImpl.listJoinOrderByJoinId(joinorder.getJoinid());
					List<JoinorderBean> JoinorderBean = new ArrayList<JoinorderBean>();
					//拼装主运单
					JoinorderBean jb = new JoinorderBean();
					jb.setId(joinorder.getId());
					jb.setJoinid(joinorder.getJoinid());
					jb.setLoadingnote(waybillCode);
					orderhead = orderServiceImpl.getOrderheadById(joinorder.getJoinid());
					jb.setTransportationLine(joinorder.getTransportation());
					if(orderhead != null){
						jb.setCarrierName(orderhead.getSuppliername());
					}
					jb.setType(1);
					JoinorderBean.add(jb);
					//拼装子运单
					for (int i = 0; i < joinorders.size(); i++) {
						jb = new JoinorderBean();
						jb.setId(joinorders.get(i).getId());
						jb.setJoinid(joinorders.get(i).getJoinid());
						jb.setLoadingnote(joinorders.get(i).getLoadingnote());
						orderhead = orderServiceImpl.getOrderheadById(joinorders.get(i).getJoinid());
						jb.setTransportationLine(joinorders.get(0).getTransportation());
						if(orderhead != null){
							jb.setCarrierName(orderhead.getSuppliername());
						}
						jb.setType(2);
						JoinorderBean.add(jb);
					}
					ma.put("JoinorderBean", JoinorderBean);
				} catch (Exception e) {
					// TODO: handle exception
					ma.put("state", -1);
					ma.put("errormsg", e.getMessage());
				}
			}else{
				ma.put("state", -1);
				ma.put("errormsg", "运单号为空");
			}
		}else{
			ma.put("state", -1);
			ma.put("errormsg", "运单号为空");
		}
		return ma;
	}
	
	@RequestMapping(value ="/delMergerWaybill", method = RequestMethod.GET)
	public Map delMergerWaybill(int id, int joinid, HttpServletRequest request){
		Map ma = new HashMap();
		try {
			List<OrderDetail> orderDetails = orderServiceImpl.getOrderDetailBAKById(id);
			Orderhead oh = orderServiceImpl.getOrderheadBAKById(id);
			orderServiceImpl.addOrderhead(oh);
			for (int i = 0; i < orderDetails.size(); i++) {
				orderServiceImpl.addOrderDetail(orderDetails.get(i));
			}
			orderServiceImpl.delOrderheadBAK(id);
			orderServiceImpl.delOrderDetailBAK(orderDetails.get(0).getLoadingnote());
			orderServiceImpl.delJoinorder(id, joinid);
			ma.put("state", 1);
		} catch (Exception e) {
			// TODO: handle exception
			ma.put("state", -1);
			ma.put("errormsg", e.getMessage());
		}
		return ma;
	}
	
	@RequestMapping(value ="/searchMergerWaybill", method = RequestMethod.GET)
	public Map searchMergerWaybill(String waybillCode, HttpServletRequest request){
		Map ma = new HashMap();
		try {
			//用waybillCode查询主运单
			OrderDetail od = orderServiceImpl.getOrderDetailById(Integer.parseInt(waybillCode));
			List<Joinorder> joinorder = null;
			//主运单为空
			if(od == null) {
				//用waybillCode查询子运单列表
				joinorder = orderServiceImpl.listJoinOrderByloadingnote(Integer.parseInt(waybillCode));
				int headid = joinorder.get(0).getJoinid();
				joinorder = orderServiceImpl.listJoinOrderByJoinId(headid);
				waybillCode = orderServiceImpl.listOrderDetailByheadId(headid).get(0).getLoadingnote();
			} else {
				//主运单不为空，列出子运单
				joinorder = orderServiceImpl.listJoinOrderByJoinId(od.getId());
				
			}
			
			List<JoinorderBean> JoinorderBean = new ArrayList<JoinorderBean>();
			//拼装主运单
			JoinorderBean jb = new JoinorderBean();
			jb.setId(joinorder.get(0).getId());
			jb.setJoinid(joinorder.get(0).getJoinid());
			jb.setLoadingnote(waybillCode);
			Orderhead orderhead = orderServiceImpl.getOrderheadById(jb.getJoinid());
			jb.setTransportationLine(joinorder.get(0).getTransportation());
			if(orderhead != null){
				jb.setCarrierName(orderhead.getSuppliername());
			}
			jb.setType(1);
			JoinorderBean.add(jb);
			//拼装子运单
			for (int i = 0; i < joinorder.size(); i++) {
				jb = new JoinorderBean();
				jb.setId(joinorder.get(i).getId());
				jb.setJoinid(joinorder.get(i).getJoinid());
				jb.setLoadingnote(joinorder.get(i).getLoadingnote());
				orderhead = orderServiceImpl.getOrderheadBAKById(jb.getId());
				if(orderhead != null){
					jb.setCarrierName(orderhead.getSuppliername());
				}
				jb.setTransportationLine(joinorder.get(0).getTransportation());
				jb.setType(2);
				JoinorderBean.add(jb);
			}
			ma.put("JoinorderBean", JoinorderBean);
			ma.put("state", 1);
		} catch (Exception e) {
			// TODO: handle exception
			ma.put("state", -1);
			ma.put("errormsg", e.getMessage());
		}
		return ma;
	}
	
	/***
	 * 消息推送
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/searchSMS", method = RequestMethod.GET)
	public Map searchSMS(HttpServletRequest request){
		Map ma = new HashMap();
		try {
			List<Sms> sms = smsServiceImpl.searchAll();
			for (int i = 0; i < sms.size(); i++) {
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				sms.get(i).setMakedateStr(sdf1.format(sms.get(i).getMAKEDATE()));
			}
			ma.put("smsBean", sms);
			ma.put("state", 1);
		} catch (Exception e) {
			// TODO: handle exception
			ma.put("state", -1);
			ma.put("errormsg", e.getMessage());
		}
		return ma;
	}
	
	/***
	 * 筛选
	 * @param area             区域
	 * @param beginshipDate    装运日期起
	 * @param endshipDate      装运日期终
	 * @param supplierno       承运商
	 * @param waybillCode      装运单号
	 * @param sourceno         发货仓库
	 * @param guestno          收货方
	 * @param sendername       发货方
	 * @param stauts           状态
	 * @param transportationNo 运输路线
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/searchwaybillCode", method = RequestMethod.GET)
	public Map searchwaybillCode(String area, Date beginshipDate, Date endshipDate, String waybillCode, String sourceno, 
			String guestno, String supplierno, String sendername, int stauts, String transportationNo, int pageNo, HttpServletRequest request){
		Map ma = new HashMap();
		try {
			List<Orderhead> orderheads = orderServiceImpl.searchwaybillCode(area, beginshipDate, endshipDate, waybillCode, sourceno, guestno, supplierno, sendername, stauts, transportationNo, pageNo, size);
			int totalnum = orderServiceImpl.countwaybillCode(area, beginshipDate, endshipDate, waybillCode, sourceno, guestno, supplierno, sendername, stauts, transportationNo);
			List<WaybillInfoBean> wbibs = new ArrayList<WaybillInfoBean>();
			for (int i = 0; i < orderheads.size(); i++) {
				WaybillInfoBean wbib = new WaybillInfoBean();
				List<OrderDetail> ods = orderServiceImpl.listOrderDetailByheadId(orderheads.get(i).getId());
				if(ods.size() != 0){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					wbib.setWaybillCode(ods.get(0).getLoadingnote());
					wbib.setDeliveryTime(sdf.format(ods.get(0).getShipdate()));
				}
				wbib.setTransportationLine(ods.get(0).getTransportation());
				wbib.setCarrierName(orderheads.get(i).getSuppliername());
				wbibs.add(wbib);
			}
			ma.put("WaybillInfoBeans", wbibs);
			ma.put("state", 1);
			ma.put("totalnum", totalnum);
		} catch (Exception e) {
			// TODO: handle exception
			ma.put("state", -1);
			ma.put("errormsg", e.getMessage());
		}
		return ma;
	}
	
	/***
	 * 由于app端承运商，发货仓库和收货方查询时反应慢查询数据错误 2015-6-23 lianghui update
	 * 保留原api接口不变，源代码保存，把承运商，发货仓库和收货方分开各自进行查询
	 * @param roleid 承运商==4;发货仓库==7;收货方==5;
	 * @param lastLogin 最后一次登录时戳;
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/searchOperatorByStauts", method = RequestMethod.GET)
	public Map searchOperatorByStauts(int roleid,Integer lastLogin, HttpServletRequest request){
		Map ma = new HashMap();
		try {
			List<ResponeBean> rbs = new ArrayList<ResponeBean>();
			List<Supplier> supplier=new ArrayList<Supplier>();
			List<Source> source = new ArrayList<Source>();
			List<Customer> customer = new ArrayList<Customer>();
			if(roleid==4){
				supplier = operatorServiceImpl.searchSupplier(lastLogin);
				for (int i = 0; i < supplier.size(); i++) {
					ResponeBean rb = new ResponeBean();
					rb.setCode(supplier.get(i).getCode());
					rb.setName(supplier.get(i).getName());
					rbs.add(rb);
				}
			}else if(roleid==7){
				source = operatorServiceImpl.searchSource(lastLogin);
				for (int i = 0; i < source.size(); i++) {
					ResponeBean rb = new ResponeBean();
					rb.setCode(source.get(i).getCode());
					rb.setName(source.get(i).getName());
					rbs.add(rb);
				}
			}else if(roleid==5){
				customer = operatorServiceImpl.searchCustomer(lastLogin);
				for (int i = 0; i < customer.size(); i++) {
					ResponeBean rb = new ResponeBean();
					rb.setCode(customer.get(i).getCode());
					rb.setName(customer.get(i).getName());
					rbs.add(rb);
				}
			}
			ma.put("operatorsBeans", rbs);
			ma.put("state", 1);
		} catch (Exception e) {
			// TODO: handle exception
			ma.put("state", -1);
			ma.put("errormsg", e.getMessage());
		}
		return ma;
	}
//	/***
//	 * 由于app端承运商，发货仓库和收货方查询时反应慢查询数据错误 2015-6-23 lianghui update
//	 * 把承运商，发货仓库和收货方分开各自进行查询
//	 * @param roleid 承运商==4;发货仓库==7;收货方==5;
//	 * @param lastLogin 最后一次登录时戳;
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value ="/searchOperatorByStauts", method = RequestMethod.GET)
//	public Map searchOperatorByStauts(int roleid,Integer lastLogin, HttpServletRequest request){
//		Map ma = new HashMap();
//		try {
//			List<ResponeBean> rbs = new ArrayList<ResponeBean>();
//			List<Operator> operators = operatorServiceImpl.searchOperatorByStauts(roleid,lastLogin);
//			for (int i = 0; i < operators.size(); i++) {
//				ResponeBean rb = new ResponeBean();
//				rb.setCode(operators.get(i).getUsername());
//				rb.setName(operators.get(i).getGuestname());
//				rbs.add(rb);
//			}
//			ma.put("operatorsBeans", rbs);
//			ma.put("state", 1);
//		} catch (Exception e) {
//			// TODO: handle exception
//			ma.put("state", -1);
//			ma.put("errormsg", e.getMessage());
//		}
//		return ma;
//	}

//	/***
//	 * 
//	 * @param lastLogin 最后一次登录时戳;
//	 * @return
//	 * @throws Exception
//	 */
	@RequestMapping(value ="/searchArea", method = RequestMethod.GET)
	public Map searchArea(Integer lastLogin,HttpServletRequest request){
		Map ma = new HashMap();
		try {
			List<ResponeBean> rbs = new ArrayList<ResponeBean>();
			List<Area> areas = operatorServiceImpl.searchArea(lastLogin);
			for (int i = 0; i < areas.size(); i++) {
				ResponeBean rb = new ResponeBean();
				rb.setCode(areas.get(i).getCODE());
				rb.setName(areas.get(i).getNAME());
				rbs.add(rb);
			}
			ma.put("AreaBeans", rbs);
			ma.put("state", 1);
		} catch (Exception e) {
			// TODO: handle exception
			ma.put("state", -1);
			ma.put("errormsg", e.getMessage());
		}
		return ma;
	}
	/***
	 * 
	 * @param lastLogin 最后一次登录时戳;
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/searchLine", method = RequestMethod.GET)
	public Map searchLine(Integer lastLogin,HttpServletRequest request){
		Map ma = new HashMap();
		try {
			List<ResponeBean> rbs = new ArrayList<ResponeBean>();
			List<Line> areas = operatorServiceImpl.searchLine(lastLogin);
			for (int i = 0; i < areas.size(); i++) {
				ResponeBean rb = new ResponeBean();
				rb.setCode(areas.get(i).getCODE());
				rb.setName(areas.get(i).getNAME());
				rbs.add(rb);
			}
			ma.put("LineBeans", rbs);
			ma.put("state", 1);
		} catch (Exception e) {
			// TODO: handle exception
			ma.put("state", -1);
			ma.put("errormsg", e.getMessage());
		}
		return ma;
	}

}