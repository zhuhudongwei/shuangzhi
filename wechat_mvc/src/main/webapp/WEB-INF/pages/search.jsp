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
				<span class="text">运单查询</span>
				 
			</div>
			
			<div class="login-ent-cont">
				<form name="login" id="form_login" method="post" action="search" enctype="multipart/form-data">
				<div class="lg-cont-box">
					<div class="ipt ipsc clearfix">
						<span class="tspan"><i class="icons iussearch"></i></span>
						<input type="text" class="tinput" id="J-orderno" name="orderid" placeholder="运单编号">
						<input type="hidden" name="roleid" value="${roleid }">
						<input type="hidden" name="sendername" value="${sendername }">
						<input type="hidden" name="supplierno" value="${supplierno }">
						<input type="hidden" name="guestno" value="${guestno }">
					</div>
				</div>
				<div class="lg-btn"><input type="submit" class="btn" value="查&nbsp;&nbsp;询"></div>
				</form>
				
				<form method="get" action="unbindWx" enctype="multipart/form-data">
					<div class="lg-btn" style="margin-top: 20px;"><input type="submit" class="btn" value="退&nbsp;&nbsp;出"></div>
				</form>
			</div>
			
			<div class="login-tips-cont">
			</div>
			<div  id="J-error" style="color: red;text-align: center;">${msg }  </div> 
		</div>
    </div>
<script type="text/javascript">
 
$('#form_login').submit(function(){
	var Jusername = $.trim($('#J-orderno').val())
	if(Jusername == ''){
		$('#J-error').html("运单号不能为空！");
		$('#J-username').focus();
		return false;
	}
});

</script>

</body>
</html>