<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function turn(){
	document.form1.action = "${action}";
	document.form1.submit();
}

</script>
</head>
  
<body>
<p>&nbsp;</p>
<p>&nbsp;</p>
<form action="" name="form1" method="post">
<table width="330" height="261" border="0" align="center" cellpadding="0" cellspacing="0" background="/queue/images/message.gif">
  <tr>
    <td height="43" colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td width="91" height="163"><div align="right"><img src="/queue/images/mes.gif" /></div></td>
    <td width="210" style="text-align:center">${msg }</td>
    <td width="29">&nbsp;</td>
  </tr>
  <tr>
    <td height="30" colspan="3"><div align="center">
      <input name="Submit3" type="button" class="button" value="返回" onclick="turn()"/>
    </div></td>
  </tr>
  <tr>
    <td height="20" colspan="3">&nbsp;</td>
  </tr>
</table>
</form>
<br>
<br>
</body>
</html>
