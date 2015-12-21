<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><decorator:title default="background" /></title>
<jsp:include page="cssjs.jsp"></jsp:include>
<decorator:head />
</head>
<body>
<div id="centdiv"> 
<jsp:include page="header.jsp"></jsp:include>
    	<decorator:body />
<jsp:include page="footer.jsp" />
</div>
</body>
</html>