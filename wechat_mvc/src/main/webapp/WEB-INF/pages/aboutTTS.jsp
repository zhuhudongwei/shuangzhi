<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>TTS</title>
<link rel="stylesheet" type="text/css" href="../qsrail_files/page_mp_article20910f.css">
<style>
        ol,ul{list-style-position:inside;}
</style>
</head>
<body id="activity-detail" class="zh_CN ">
    <div class="rich_media">
        <div class="rich_media_inner">
            <div id="page-content">
            	<div id="page-content" style="text-align: center;">
		            <img alt="" src="../qsrail_files/tts.png?20150518" style="width: 100%;">
	            </div>
	            <div style="clear: both;"></div>
                <div id="img-content">                                        
                    <div class="rich_media_content" id="js_content">
                    	<h1 style="font-weight: bold;">1、TTS简介</h1>
						<p>&nbsp;&nbsp;&nbsp;&nbsp;运输跟踪系统（TTS）是供应链平台技术和实践相结合的产物，是先进的供应链管理理念和已经被验证的成功经验的沉淀，是面向中国制造业，零售商贸企业和第三方物流的集行业规范、专业标准、物流管理技术为一体的“第四方”供应链管理运营系统平台。
						</p> 
						<p>&nbsp;&nbsp;&nbsp;&nbsp;TTS基于国际先进的供应链管理运营系统技术与互联网云计算技术体系而构建，以开放的平台和统一的标准，实现物流运输上、下游多方业务和操作协同。
						</p> 
						<p>&nbsp;&nbsp;&nbsp;&nbsp;在本案的货物运输过程中，发货方、承运商(所辖司机)、收货方(客户)各方通过TTS平台（协同司机手机APP的操作），以运单为线索，实现了货物运输的可视化，实时货物跟踪反馈以及货物运输的及时可控。
						</p> 
						<h1 style="font-weight: bold;">2、双至TTS APP简介</h1>
						<p>双至TTS是一款根据TTS PC端开发的APP应用，分为安卓版和IOS版，发货方、承运商、收货方各方通过手机端TTS，实现随时接派单及实时的运单状态查询。配合司机端我来运APP，多途径实现实时可视化。
						</p>
						<img alt="" src="../qsrail_files/ttsapp1.jpg" style="width: 45%;">
						<img alt="" src="../qsrail_files/ttsapp2.jpg" style="width: 45%;">
						<h1 style="font-weight: bold;">3、TTS中的角色</h1>
						<ul style="padding-left: 25px;">
							<li>•	发货方</li>
							<li>•	发货仓库</li>
							<li>•	承运商</li>
							<li>•	司机（我来运APP）</li>
							<li>•	收货方（客户）</li>
						</ul>
						<p></p>
						<h1 style="font-weight: bold;">4、TTS中运单状态定义</h1>
						<ul style="padding-left: 25px;">
							<li><strong>•	承运商未接收</strong>：发货方下单到平台（TTS）后，承运商尚未接收的运单</li>
							<li><strong>•	已接收未调度</strong>：承运商已接收但尚未派车的运单</li>
							<li><strong>•	司机未接收</strong>：承运商已经分配给司机，但司机（APP）未确认接收的运单</li>
							<li><strong>•	装货在途</strong>：司机已发车但尚未到达发货仓库的运单</li>
							<li><strong>•	装货中</strong>：司机在装货的运单</li>
							<li><strong>•	送货在途</strong>：已装货完成但尚未达到客户地的运单</li>
							<li><strong>•	收货中</strong>：正在卸货的运单</li>
							<li><strong>•	已签收</strong>：确认送货完成的运单</li>
							<p></p>
							<li><strong>异常运单状态：</strong></li>
							<li><strong>•	车辆调度异常</strong>：以计划发货日期为基准内车辆没有到厂的运单</li>
							<li><strong>•	装货压车</strong>：到达发货工厂超过12小时未离开即装货压车1天，此后又超过24小时未离开即装货压车2天。</li>
							<li><strong>•	司机APP超时</strong>：司机APP端超过30分钟未与服务器进行通讯提醒的运单</li>
							<li><strong>•	卸货压车</strong>：到达收货工厂超过12小时未离开即卸货压车1天，此后又超过24小时未离开即卸货压车2天。</li>
						</ul>
						<p></p>
						<h1 style="font-weight: bold;">5、TTS运作流程</h1>
						<img alt="" src="../qsrail_files/tts_float.png" style="width: 100%;">
					</div>
				</div>
			</div>
        </div>
    </div>
</body>
</html>