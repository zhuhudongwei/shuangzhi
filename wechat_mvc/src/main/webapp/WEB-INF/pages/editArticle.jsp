<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改文章</title>
<!--[if lte IE 6]>
<style type="text/css">
body { behavior:url("csshover.htc");}
</style>
<![endif]-->
<script language="javascript" src="../js/jquery.blockUI.js"></script>
<script charset="utf-8" src="../kindeditor/kindeditor-min.js"></script>
<script charset="utf-8" src="../kindeditor/lang/zh_CN.js"></script>  
<script language="javascript">
$(document).ready(function() {
	var subfalg = false;
	var editor = KindEditor.create('textarea[name="article_content"]',{
		   resizeType : 0,  // 2时可以拖动改变宽度和高度，1时只能改变高度，0时不能拖动。
		   themeType : 'simple',  //指定主题风格，可设置”default”、”simple”  指定simple时需要引入simple.css
		   width : '700px',
		    height: '500px',
		   uploadJson : '../backend/uploadImage',
		   readonlyMode : false, //只读模式 默认为false
		   allowFileManager : true,  //显示浏览远程服务器按钮

		   });
	$("#editUserForm").submit(function(){
		var nan = /^[0-9]+.?[0-9]*$/;
		if($("#title").val() == null || $("#title").val() == "" || $.trim($("#title").val()).length ==  0){
			alert("请填写标题");
			return false;
		}
		if(subfalg){
			return false;
		}
		return true;
	});
});
</script>
</head>
<body>
<div class="h30 lh30 pl10 pr10 line_height_1">
	<em class="b">
		<h2>修改文章</h2>
	</em>
</div>
<div class="c_box_1 mt10 pt20 pr20 pb20 pl20"> 
<div class="content_title01">
	<div style = "color:red">
		<s:fielderror name="message"></s:fielderror>
		<input type="hidden" id="leftHome" menuid="article"/>
	</div>
</div>
<form action="updateArticle" method="post" id="editUserForm" enctype="multipart/form-data">
	<table class="bcc line_add_edit" style="width: 100%" cellspacing="0" cellpadding="0">
		<tr>
			<td class="tr pt10 w100"><span style='color: red;'>*</span>标题：</td>
			<td colspan="3">
				<input type="text" id="title" name="article_title" style="width: 700px;" value="${article.article_title }"/>
			</td>
		</tr>
		<tr>
			<td class="tr pt10 w100">内容：</td>
			<td colspan="3">
				<textarea class="tea_e" name="article_content" id="article_content" >${article.article_content }</textarea>
			</td>
		</tr>
		
		<tr>
            <td colspan="2"><input type="submit" value="添加" class="w80 h30 b" id="submitForm" name="submitForm"/></td>
            <td colspan="2"><input type="reset" value="重置" class="w80 h30 b" />
            <input type="hidden" name="article_id" value="${article.article_id }" />
            </td>
		</tr>
	</table>
</form>
</div>
</body>
</html>