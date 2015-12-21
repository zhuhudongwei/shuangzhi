<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="apple-touch-fullscreen" content="YES">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
<title>对话窗口</title>
<link type="text/css" rel="stylesheet" href="../im_style/mobile_main.css">
<link type="text/css" rel="stylesheet" href="../im_style/skin.css">
<style type="text/css">
#ReceiveDriverContent,.gac_m{display:none;}
#editor_temp_text,#pluginWrap,#remoteHelpFrame,#sysTip,#bwrap,#language,#languageList,#shortcutkey,#shortKeyMenu,#footer{display:none;}
#track{position:absolute;top:0;left:0;z-index:-1;visibility:hidden;}
</style>
<script type="text/javascript" src="../qsrail_files/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="../qsrail_files/ajax-pushlet-client.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	PL.userId='${WEIXINCODE}'; 
	PL._init(); 
	PL.joinListen('/shuangzhi');
});
function onData(event) {
	var msg = event.get('msg_' + '${WEIXINCODE}');
	var response_id = event.get('response_id_' + '${WEIXINCODE}');
	var systime = event.get('systime_' + '${WEIXINCODE}');
	if(msg != undefined && msg != ""){
		var operator_html = '<div class="operator">';
		operator_html += '<label class="time robot">'+ systime +'</label>';
		operator_html += '<span class="robotName"></span>';
		operator_html += '<span class="robotContent">';
		operator_html += '<span style="font-family: Tahoma;mso-ascii-theme-font: minor-fareast;color: rgb(0,0,0);font-weight: bold;font-style: normal;">'+ msg +'</span>';
		operator_html += '</span><div class="clear"></div></div>';
		$('#history').append(operator_html);
		var h = $("#notewrap").height();
		$('#notewrap').scrollTop(h);
		$.getJSON("updateMsgIsSend",{"response_id":response_id},function(data){
		});
	}
}
</script> 
</head>
<body scroll="no" >
		<div id="tdhead">
			<div id="logo" style="width: 90px; height: 30px; background-image: url(../qsrail_files/img_login_logo.png);"></div>
			<div id="head"  >
				<span id="headerBox">您正在和双至客服对话</span><span id="headerBoxTime"></span>
			</div>
			<div class="clear"></div>
		</div>
		<div id="notewrap"  style="overflow: auto;">
			<div id="wrap" >
				<div id="history">
					<c:if test="${sysmsg != '0' }">
					<div class="sysinfo">
						<span class="robotName"></span><p class="info">${sysmsg }</p>
						<div class="clear"></div>
					</div>
					</c:if>
				</div>
			</div>
			 
		</div>
		<div class="clear"></div>
		<div id="msg">
			 
			<div id="enter_wrap">
				<div id="ewrap">
					<div id="enter">
						<label>发 送</label>
						<input type="hidden" id="weixinCode" value="${weixinCode}"/>
						<input type="hidden" id="weixinName" value="${weixinName}"/>
					</div>
				</div>
			</div>
			<div id="inputarea"><input id="inputbox" name="inputbox"></div>
			<div id="footer">
				<p><span id="shortKeyTip">[发送快捷键:Enter]:</span><span id="footerBox"></span></p>
			</div>
			<div class="clear"></div>
		</div>
<script type="text/javascript">
$('#enter').click(function(){
	var _question = $.trim($('#inputbox').val())
	if(_question == ''){
		return false;
	}
	var _weixinCode = $("#weixinCode").val();
	var _weixinName = $("#weixinName").val();
	$.getJSON("saveQuestion",{"weixinCode":_weixinCode,"question":_question,"weixinName":_weixinName},function(data){
		$('#inputbox').val("");
		var openid = data.weixinCode;
		//用户提问
		var visitor_html = '<div class="visitor">';
		visitor_html += '<label class="time user">'+ data.systime +'</label>';
		visitor_html += '<span class="userName"></span>';
		visitor_html += '<span class="userContent">'+ data.question +'</span>';
		visitor_html += '<div class="clear"></div></div>';
		$('#history').append(visitor_html);
		//客服回复
		if(data.systemMSG != undefined){
			var operator_html = '<div class="sysinfo">';
			operator_html += '<span class="robotName"></span><p class="info">'+ data.systemMSG +'</p>';
			operator_html += '<div class="clear"></div></div>';
			$('#history').append(operator_html);
		}
		var h = $("#notewrap").height();
		$('#notewrap').scrollTop(h);
	});
});
</script>
</body>
</html>