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
				<span class="text">货物明细</span>
			</div>
			<div class="change-cont">
				<c:forEach var="detail" items="${orderDetail }" varStatus="st">
			        <div class="change-follow" style="background: none;height: auto;">
					        <p>产品编码：<span>${detail.productno }</span></p>
					        <p>产品名称：<span>${detail.productname }</span></p>
					        <p>计划数量：<span>${detail.planquantity }</span></p>
					        <p>实装数量：<span>${detail.loadnum }</span></p>
					        <p>实收数量：<span>${detail.signnum }</span></p>
			        </div>
			        <div class="dashed-line"></div>
		        </c:forEach>
			</div> 
		</div>
        
    </div>
</body>
</html>