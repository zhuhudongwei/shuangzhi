<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>问题回复</title>
<!--[if lte IE 6]>
<style type="text/css">
body { behavior:url("csshover.htc");}
</style>
<![endif]-->
<script language="javascript">
$(document).ready(function() {
	$('#form_response').submit(function(){
		var _question = $.trim($('#J-orderno').val())
		if(_question == ''){
			return false;
		}
	});
	$('#back').click(function(){
		window.history.go(-1);
	});
});
</script>
<link type="text/css" rel="stylesheet" href="../im_style/mobile_main.css">
</head>
<body>
<div class="h30 lh30 pl10 pr10 line_height_1">
	<h2>问题回复</h2>
</div>
<div class="c_box_1 mt10 pt20 pr20 pb20 pl20"> 
<div class="content_title01">
<input type="hidden" id="leftHome" menuid="messages"/>
</div>
<form name="response" id="form_response" method="post" action="responseMsg" enctype="multipart/form-data">
	<table class="bcc line_add_edit" style="width: 100%" cellspacing="0" cellpadding="0">
		<tr>
			<td class="tr pt10 w100"> </td>
			<td>
			 	<p>客户名称：<span>${wc.open_name }</span></p>
				<p></p>
				<div style="width: 650px; height: 300px; border: 1px solid #333; padding: 10px 0; 
					overflow:auto; overflow-x:hidden;" >
					<c:forEach items="${wcList}" var="wca">
						<p style="width: 620px; text-align: center;">
							<span style="background-color: #dedede;">${wca.create_time_str }</span>
						</p>
		    			<p style="width: 620px; text-align: left; margin-left: 10px;">
		    				<img src="../img/wx_portrait.jpg" width="30px" height="30px" style="vertical-align: bottom;"/>
		    				<span style="padding: 5px; border: 1px solid #666; border-radius: 5px;">${wca.content}</span>
		    			</p>
		    			
		    			<c:forEach items="${wca.wcrs}" var="wcr">
		    				<p style="width: 620px; text-align: center;">
		    					<span style="background-color: #dedede;">${wcr.response_time_str }</span>
		    				</p>
		    				<p style="width: 620px; text-align: right; ">
		    					<span style="padding: 5px; border: 1px solid #666; border-radius: 5px; margin-left: 10px;">${wcr.response_content}</span>
		    					<img src="../img/system_portrait.png" width="30px" height="30px" style="vertical-align: bottom;"/>
		    				</p>
		    			</c:forEach>
		    			
		    		</c:forEach>
				</div>
			</td>
		</tr>
		<tr>
			<td class="tr pt10 w100">回复：</td>
			<td>
				<textarea style="width: 650px; border: 1px solid #999;" rows="5" cols="100" id="J-orderno" name="question"></textarea>
			</td>
		</tr>
		<tr>
            <td></td>
            <td>
            	<div style="width: 634px;">
            		<input type="button" value="返回" class="w80 h30 b" id="back" name="back" style="float: left;"/>
            		<input type="submit" value="发送" class="w80 h30 b" id="submitForm" name="submitForm" style="float: right;"/>
            		<input type="hidden" name="customer_id" value="${wc.customer_id }" /></td>
            	</div>
		</tr>
	</table>
</form>
</div>
</body>
</html>