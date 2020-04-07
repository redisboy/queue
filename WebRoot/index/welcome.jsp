<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(/queue/images/d1.gif);
}
-->
</style>
</head>
<body scroll="no">
<c:if test="${lycount > 0}">
<jsp:include page="popup.jsp" />
</c:if>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" valign="top">
      <table border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="500" ><img src="/queue/images/welcome.jpg"  style="margin-top:55px;"/></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td align="center" valign="middle">&nbsp;</td>
  </tr>
</table>
</body>
</html>
