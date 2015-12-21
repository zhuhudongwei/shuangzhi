<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="../js/jquery-1.2.6.js"></script>
<link type="text/css" rel="stylesheet" href="../style/reset.css" />
<link type="text/css" rel="stylesheet" href="../style/system.css" />
<title></title>
<script type="text/javascript">
function focusInput(focusClass, normalClass) {
     var elements = document.getElementsByTagName("input");
     for (var i=0; i < elements.length; i++) {
         if (elements[i].type != "button" && elements[i].type != "submit" && elements[i].type != "reset") {
             elements[i].onfocus = function() { this.className = focusClass; };
             elements[i].onblur = function() { this.className = normalClass||''; };
         }
     }
}

$(document).ready(function() {

});

</script>  
</head>
<body>
<table cellspacing="0" cellpadding="0" border="0" id="loginHolder">
	<tr>
		<td>
			<div id="loginBox">
				<div id="loginBoxTop" style="text-align:center">
					<h2>双至后台</h2>
				</div>
				<form action="../backend/shuangzhiLogin" method = "post" class="forkForms submitWithLink" id="authenticationIndex">
					<div class="horizontal">
						<div style="color: red;text-align: center;">
							<c:if test="${expire == 3 }">
								<span>用户名或密码不正确！</span>
							</c:if>
						</div>
						<div id="loginFields">
							<p>
								<input type="text" class="normalInput inputText checkBeforeUnload" style="font-size:14px;" maxlength="255" name="loginName" value="用户名" onFocus="if(this.value==this.defaultValue) {this.value='';this.style.color='#000000';}" onBlur="if(!value) {value=this.defaultValue; this.style.color='#ACACAC';}" id="loginName" /></p>
							<p>
								<input type="text" class="normalInput inputText inputPassword checkBeforeUnload" style="font-size:14px;" name="password" id="password" value="密码" onFocus="if(this.value==this.defaultValue) {this.value='';this.style.color='#000000';this.type='password';}" onBlur="if(!value) {value=this.defaultValue; this.style.color='#ACACAC';this.type='text';}"/></p>
						</div>
						<p style="padding: 0 7px 12px 12px;">
							<input type="submit" name="submit" value="登陆" style="width: 100%;height: 33px;"/>
						</p>
					</div>
				</form>
			</div>
		</td>
	</tr>
</table>
</body>
</html>