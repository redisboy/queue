<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
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
    <base href="<%=basePath%>">
    
    <title>My JSP 'success.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <form action="dlrdj.action">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" valign="top">
      <table border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="500" >操作成功</td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td align="center" valign="middle">&nbsp;</td>
  </tr>
</table>
<input type="submit" value="返回" />
</form>
  </body>
</html>
