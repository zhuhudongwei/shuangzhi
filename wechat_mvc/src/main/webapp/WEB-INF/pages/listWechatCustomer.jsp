<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>未回复问题列表</title>
<script language="javascript">
$(document).ready(function() {
	$('.change-follow').click(function(){
		var cid = $(this).attr("cid");
		window.location.href="openToResponse?customer_id="+cid;
	});
	function show(){
		window.location.href="../backend/listWechatCustomer";
	}
	setInterval(show,10000);
});

</script>
</head>
<body>
<div class="content">
<h2></h2>
<div class="c_box_5 p5 mt2 cl">
	<input type="hidden" id="leftHome" menuid="messages"/>
	<table class="bcc lh24 tc gray2 line_list" width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<th style="color:#000;width: 5%;">序号</th>
            <th style="color:#000;width: 15%;">用户编号</th>
            <th style="color:#000;width: 15%;">提问时间</th>
            <th style="color:#000;width: 55%;">提问内容</th>
            <th style="color:#000;width: 10%;">操作</th>
		</tr>
		<c:forEach var="wcs" items="${wcs }" varStatus="st">
			<c:if test="${st.index % 2 == 0 }">
				<tr class="lh25" cid="${wcs.customer_id }">
			</c:if>
			<c:if test="${st.index % 2 != 0 }">
				<tr class="lh25" style="background-color:#f8f8f8">
			</c:if>
			    <td style="color:#000">${st.index+1}</td>
				<td style="color:#000">${wcs.open_name }</td>
				<td style="color:#000">${wcs.create_time_str }</td>
				<td style="color:#000;text-align: left;">${wcs.content }</td>
				<td style="color:#000"><span style="color:blue;cursor: pointer;" class="change-follow" cid="${wcs.customer_id }">回复</span></td>
			</tr>
        </c:forEach>
	</table>
</div>
</div>
</body>
</html>