<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><decorator:title default="background" />-background</title>
<jsp:include page="../template/cssjs.jsp"></jsp:include>
<decorator:head />
</head>
<body>
<div id="centdiv"> 
<jsp:include page="../template/header.jsp"></jsp:include>
<table border="0" cellpadding="0" cellspacing="15" width="100%">
  <tr>
    <td valign="top" align="left">
    	<decorator:body />
    </td>
  </tr>
</table>
<jsp:include page="../template/footer.jsp" />
</div>
</body>
</html>