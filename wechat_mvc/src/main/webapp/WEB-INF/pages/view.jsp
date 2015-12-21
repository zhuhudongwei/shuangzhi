<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimal-ui">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<title></title>
<link type="text/css" rel="stylesheet" href="../qsrail_files/base.css">
<link type="text/css" rel="stylesheet" href="../qsrail_files/styles_v15.css">
<script type="text/javascript" src="../qsrail_files/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="../qsrail_files/baidu.js"></script>
<script type="text/javascript" src="../qsrail_files/ruan.js"></script>
<script type="text/javascript" src="../qsrail_files/swipe.js"></script>
<script type="text/javascript" src="../qsrail_files/util.js"></script>
<script type="text/javascript" src="../qsrail_files/sha1.js"></script>
</head>
<body>
<div class="wrap">
        
		<div class="login-cont">
			<div class="login-top clearfix">
				<a class="prev" href="http://m.ddeng.com/search"><i class="icons iprevl"></i></a>
				<span class="text">运单详情</span>
			</div>
			<div class="change-cont">
				<div class="change-cat">运单信息</div> 
				<div class="change-msg clearfix"><label>运单号：<span>${orderDetail.loadingnote }</span></label>
				<label>运单状态：
					<span>
						${noteStatus }
					</span>
				</label>
				</div>
				
				<div class="change-msg clearfix"><label>&nbsp;&nbsp;<span>&nbsp;&nbsp;&nbsp;&nbsp;</span></label><label>是否异常：<span style="color: red;">${anomalyWay }</span></label></div>
				
				<div class="change-msg clearfix"><label>司机姓名：<span>${orderhead.drivername }</span></label><label>手机号码：<span>${orderhead.tel }</span></label></div>
			
				<div class="clear"></div>
				<div class="change-msg clearfix"><label>车号：<span>${orderhead.truckno }</span></label><label>车型：<span>${orderhead.trucktype }</span></label></div>
				
				<div class="change-msg clearfix">承运商：<span>${orderhead.suppliername }</span></div>
				
				<div class="change-msg clearfix">运输路线：<span>${orderDetail.transportation }</span></div>
				
				<div class="change-msg clearfix">收货工厂：<span>${orderhead.guestname }</span></div>
				
				<div class="change-msg clearfix">收货人：<span>${orderhead.receiveman }</span></div>
				
				<div class="change-msg clearfix">收货人联系方式：<span>${orderhead.receivetel }</span></div>
				
				<div class="change-msg clearfix" ><em>地  址：</em><span class="ad">${orderDetail.transportation }</span></div>
				<div class="change-msg clearfix">子运单：<span>${loadingnote }</span></div>

				<div class="dashed-line"></div>
				<div class="change-cat">运单跟踪<span class="cat-r"><a href="map?orderid=${orderDetail.loadingnote }">货物明细</a></span></div> 
				<div class="change-follow">预计发货时间：<span>${orderDetail.shipdate_str }</span></div>
				<c:if test="${orderhead.status == 7}">
					<div class="change-follow">&nbsp;&nbsp;&nbsp;&nbsp;距离：<span>${path }</span></div>
				</c:if>
				<div class="change-follow">到达装货工厂时间：<span>${orderDetail.loadindate_str }</span></div>
				
				<div class="change-follow">装货完成时间：<span>${orderDetail.loadoutdate_str }</span></div>
				<c:if test="${orderhead.status == 4}">
					<div class="change-follow">&nbsp;&nbsp;&nbsp;&nbsp;距离：<span>${path }</span></div>
				</c:if>
				<div class="change-follow">到达收货工厂时间：<span>${orderDetail.unloadindate_str }</span></div>
				
				<div class="change-follow">收货完成时间：<span>${orderDetail.unloadoutdate_str }</span></div>
			</div> 
		</div>
        
    </div>
</body>
</html>