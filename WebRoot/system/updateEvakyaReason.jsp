<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>评价原因修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form action="" name="form1" method="post">
<input type="hidden" name="dmlb" id="dmlb" value="${code.dmlb }" >
<input type="hidden" name="id" id="id" value="${code.dm }" >
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">评价原因修改</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    <br>
	    <table width="50%" border="0" cellpadding="1" cellspacing="1" class="table_back">
	     <tr>
	        <td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">编号</td>
	        <td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
	          <input name="id" id="id" maxlength="10" style="width:100px" value="<c:out value="${code.dm }"></c:out>" disabled="disabled" />
	          &nbsp;<font color="#ff0000"></font>
	        </td>
	      </tr>
	      <tr>
	        <td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">原因</td>
	        <td width="80%" height="200" bgcolor="#F1F9FD">&nbsp;
	          <input name="name" id="name" maxlength="10" style="width:60%;height:160px;" value="<c:out value="${code.mc }"></c:out>"  />
	          &nbsp;<font color="#ff0000"></font>
	        </td>
	      </tr>
	      
	    </table>
    	  <br>
	      <br>
		  <input name="bt1" class="button" type="button" value=" 修改 " onClick="updateByCode()"/>
		  <input name="bt2" class="button" type="button" value=" 返回 " onClick="window.history.back(-1);"/>
	      <br>
	      <br>

   		</td>
		<td width="8" background="images/t-5.jpg">&nbsp;</td>
  </tr>
  <tr>
	 <td><img src="images/t-6.jpg" width="7" height="11"></td>
	 <td height="11" background="images/t-7.jpg"></td>
	 <td><img src="images/t-8.jpg" width="8" height="11"></td>
  </tr>
</table>
</form>

<script type="text/javascript">
function updateByCode(){
	var name = document.form1.id.value;
    var name = document.form1.name.value;
	if(name.length == 0){
		alert("内容不能为空!");
		document.form1.name.focus();
		return false;
	}
document.form1.action = "editGredentialsEvaluaReason.action";
document.form1.submit();
}

</script>
</body>
</html>
