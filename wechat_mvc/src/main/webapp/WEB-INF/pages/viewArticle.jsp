<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="ui-mobile"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><!--<base href="http://www.wechat.siemens.com.cn/external/html/info4.html">--><base href=".">
<meta charset="utf-8">
<title>${article.article_title }</title>
<link rel="stylesheet" href="http://www.wechat.siemens.com.cn/external/html/css/jquery.mobile-1.2.0.min.css">
<link href="../qsrail_files/global.css" rel="stylesheet" type="text/css">
<meta id="viewport" name="viewport" content="width=640, initial-scale=0.5, maximum-scale=2.0, minimum-scale=0.5, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-touch-fullscreen" content="yes">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="MobileOptimized" content="640">
<script type="text/javascript" async="" src="../qsrail_files/ga.js"></script><script type="text/javascript" async="" src="./about_files/ga.js"></script><script type="text/javascript" src="./about_files/viewport.js"></script><meta name="viewport" content="width=640, target-densitydpi=device-dpi">
<script type="text/javascript" src="../qsrail_files/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="../qsrail_files/jquery.mobile-1.2.0.min.js"></script>
<script type="text/javascript" src="../qsrail_files/common.js"></script>
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="http://www.wechat.siemens.com.cn/csshide1.css">
<style></style>
<link rel="stylesheet" href="http://www.wechat.siemens.com.cn/abprule.css">
<style></style>
<style></style>
<script type="text/javascript">window.onerror=function(){return true;}</script>
</head>
<body class="ui-mobile-viewport ui-overlay-c">
	<div data-role="page" data-url="/external/html/info4.html" tabindex="0" class="ui-page ui-body-c ui-page-active" style="min-height: 914px;">

	<div class="wrapper pd">
    	
        <dl class="box-brief">
        	<dt class="one"><b>${article.article_title }</b><br><br></dt>
        	<dd class="two" style="margin-bottom:-20px;word-break: break-all;">
        		${article.article_content }
        	</dd>
        </dl>
	</div>
	</div>
</body>
</html>    	
