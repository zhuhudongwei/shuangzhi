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
			<div class="login-tips-cont">
				<span class="text"><img style="width:70%;" src="../qsrail_files/img_login_logo.png" /></span>
			</div>
			<form name="login" id="form_login" method="post" action="login" enctype="multipart/form-data">
			<div class="login-ent-cont">
				<div class="lg-cont-box">
					<div class="ipt clearfix">
						<span class="tspan"><i style="padding:0;margin:0;" class="icons iusipt"></i></span>
						<input type="text" class="tinput" id="J-username" name="username" value="" placeholder="输入用户名">
					</div>
					<div class="ipt iptn clearfix">
						<span class="tspan"><i class="icons iuspass"></i></span>
						<input type="password" class="tinput" id="J-userpass" name="pwd" placeholder="输入密码">
					</div>
				</div>
				<div class="login-sel-lx clearfix">
				<label><input type="checkbox" name="type" value="0">记住账号</label>
				<label><input type="checkbox" name="type" value="1">记住密码</label>
				</div>
				<div class="lg-btn"><input type="submit" class="btn" value="登 录"></div>
			</div>
			</form>
			<div class="login-tips-cont">
			</div>
			<div  id="J-error" style="color: red;text-align: center;">${msg } </div> 
		</div>
    </div>
<script type="text/javascript">
$('#form_login').submit(function(){
	var Jusername = $.trim($('#J-username').val()),
	Juserpass = $.trim($('#J-userpass').val());

	if(Jusername == ''){
		$('#J-error').html("账号不能为空！");
		$('#J-username').focus();
		return false;
	}

	if(Juserpass == ''){
		$('#J-error').html("密码不能为空！");
		$('#J-userpass').focus();
		return false;
	}
 
});
</script>
</body>
</html>