<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>手册资料</title>
<link rel="stylesheet" type="text/css" href="../qsrail_files/page_mp_article20910f.css">
<style>
        ol,ul{list-style-position:inside;}
.az_down_btn1_x {
display: block;
width: 70%;
height: 37px;
line-height: 37px;
margin: 20px auto 0;
text-align: center;
font-size: 18px;
color: #cfd0d0;;
background-color: #26292e;
}
.rich_media_content{margin-top:50px;}
.az_down_btn1 {
display: block;
width: 70%;
height: 30px;
line-height: 32px;
margin: 12px auto 0;
text-indent:20%; 
white-space:nowrap; 
text-align: left;
font-size: 14px;
color: #fff;
background: url(../qsrail_files/1111.png) no-repeat left; 
border-radius:30px;
background-size:100% 100%;
}
.bg_btn2{background: url(../qsrail_files/2222.png) no-repeat center;background-size:100% 100%; }
.bg_btn3{background: url(../qsrail_files/3333.png) no-repeat center; background-size:100% 100%;}
.bg_btn4{background: url(../qsrail_files/4444.png) no-repeat center;background-size:100% 100%; }
.bg_btn5{background: url(../qsrail_files/5555.png) no-repeat center;background-size:100% 100%; }
.bg_btn6{background: url(../qsrail_files/6666.png) no-repeat center; background-size:100% 100%;}
</style>
<link rel="stylesheet" href="../qsrail_files/csshide1.css"/>
<style></style >
<link rel="stylesheet" href="../qsrail_files/abprule.css"/>
<style></style  >
<style></style>
<script type="text/javascript">window.onerror=function(){return true;}</script></head>
<body id="activity-detail" class="zh_CN ">
    <div class="rich_media">
        <div class="rich_media_inner">
            <div id="page-content" style="text-align: center;"><img src="../qsrail_files/img_login_logo.png" style="width: 55%"/></div>
            <div id="page-content">
                <div id="img-content">                                        
                    <div class="rich_media_content" id="js_content">
                    	<c:forEach var="wcs" items="${articles }" varStatus="st">
                    		<h1 style="font-weight: bold;text-align: center;">
	                   			<div class="az_down_btn1 bg_btn${st.index+1}">
								  <a class="" href="viewArticle?article_id=${wcs.article_id }" style="color: rgb(96, 127, 166);">
								  	${wcs.article_title_min }
								  </a>
								</div>
	                   		</h1>
				        </c:forEach>
					</div>
				</div>
			</div>
        </div>
    </div>
</body>
</html>