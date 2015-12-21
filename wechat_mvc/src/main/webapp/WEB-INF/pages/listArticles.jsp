<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>文章列表</title>
<script language="javascript">
$(document).ready(function() {
	$('.edit_but').click(function(){
		var cid = $(this).attr("cid");
		window.location.href="openToEditArticle?article_id="+cid;
	});
	
	$('.del_but').click(function(){
		var cid = $(this).attr("cid");
		window.location.href="delArticle?article_id="+cid;
	});
});
</script>
</head>
<body>
<div class="content">
<h2 style="text-align: right; padding: 5px 20px;">
<a href="../backend/openToEditArticle">添加文章</a>
</h2>
<div class="c_box_5 p5 mt2 cl">
	<input type="hidden" id="leftHome" menuid="article"/>
	<table class="bcc lh24 tc gray2 line_list" width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<th style="color:#000;width: 5%;">序号</th>
            <th style="color:#000;width: 70%;">文章标题</th>
            <th style="color:#000;width: 15%;">文章时间</th>
            <th style="color:#000;width: 10%;">操作</th>
		</tr>
		<c:forEach var="wcs" items="${articles }" varStatus="st">
			<c:if test="${st.index % 2 == 0 }">
				<tr class="lh25">
			</c:if>
			<c:if test="${st.index % 2 != 0 }">
				<tr class="lh25" style="background-color:#f8f8f8">
			</c:if>
			    <td style="color:#000">${st.index+1}</td>
				<td style="color:#000">${wcs.article_title }</td>
				<td style="color:#000">${wcs.create_time_str }</td>
				<td style="color:#000">
					<span style="color:blue;cursor: pointer;" class="edit_but" cid="${wcs.article_id }">修改</span>&nbsp;&nbsp;
					<span style="color:blue;cursor: pointer;" class="del_but" cid="${wcs.article_id }">删除</span>
				</td>
			</tr>
        </c:forEach>
	</table>
</div>
</div>
</body>
</html>