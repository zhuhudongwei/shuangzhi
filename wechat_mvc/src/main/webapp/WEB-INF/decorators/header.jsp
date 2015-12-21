<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
function logout()
{
	document.forms[0].submit();
}
$(document).ready(function() {
	var menuid = $("#leftHome").attr("menuid");
	$(".menuLi").each(function(){
		$(this).removeClass("active");
	});
	$("#"+menuid+"_topmenu").addClass("active");
});
</script>
 <style type="text/css">
    .active
    {
        background: white;
    }
</style>
<div id="header" class="ml15 mr15">
	<form action="../website/backendIndex">
		<input type="hidden" id="topMenuIdInForm" value="${sessionScope.topMenuId}" />
		<input type="hidden" id="userType" value="${userType}" />
		<h2>
			双至后台
			<span class="r mr10" style="font-size: 12px;font-weight:none;color: #FFF;">
				${session.userName }
		      	<a href="owner_showChangePasswordPage.action" style="color: #FFF;margin-left: 20px;">修改密码</a>
		      	<a href="javascript:void(0)" onclick="logout()" id="LoginOut" style="color: #FFF;" class="ml5 mr5">退出系统</a>
		    </span>
		</h2>
		<jsp:include page="top_menu.jsp"></jsp:include>
	</form>
</div>
