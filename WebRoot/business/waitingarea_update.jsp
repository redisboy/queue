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
<title>修改等待区域</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/queue/js/jquery.js"></script>
<script type="text/javascript" src="/queue/js/ajax.js"></script>
<script type="text/javascript">
window.onload = function() {
	//为business输入框添加blur事件
	$("#waitingarea").blur(function(){
		var waitingarea = $("#waitingarea").val();
		//检查是否为空
		if(""==waitingarea){
		   $("#businessInfo").html("<img src='/queue/images/wrong.gif'/>等待区域不能为空!");
			return;
		}
		//检查唯一性
		var url = "checkBusiness.action";
		var param = "business="+business;
		var result = new MyJqueryAjax(url,param).request();
		if("1"==result){
	    	$("#businessInfo").html("<img src='/queue/images/wrong.gif'/>业务类型已被占用!");
	    	$("#business").val(""); 
		}else{
		    $("#businessInfo").html("<img src='/queue/images/right.gif'/>业务类型未占用!");
		}
	});
}
</script>
</head>
<body>
<form action="" name="form1" method="post">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">修改等待区域</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    
    <table width="40%" border="0" cellpadding="1" cellspacing="1" class="table_back">
      <tr>
        <td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">业务类型</td>
        <td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;
        	<input name="name" id="name" maxlength="10" style="width:250px" value="${name}" readonly="readonly"/>
        	<input type="hidden" name="id" id="id" value="${id}"/>
        </td>
      </tr>
       <tr>
        <td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">等待区域</td>
        <td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;
          <input name="waitingarea" id="waitingarea" maxlength="10" style="width:250px" value="${waitingarea}"   />
          &nbsp;<font id="businessInfo" color="#ff0000"></font>
        </td>
      </tr>
    </table>
    <br>
      <br>
	  <input name="bt1" class="button" type="button" value=" 修改 " onClick="updateWaitingarea()"/>
	  <input name="bt2" class="button" type="button" value=" 返回 " onClick="window.history.back(-1);"/>
      <br>
      <br>

    </td>
    <td width="8" background="/queue/images/t-5.jpg">&nbsp;</td>
  </tr>
  <tr>
    <td ><img src="/queue/images/t-6.jpg" width="7" height="11"></td>
    <td height="11" background="/queue/images/t-7.jpg"></td>
    <td ><img src="/queue/images/t-8.jpg" width="8" height="11"></td>
  </tr>
</table>
</form>

<script type="text/javascript">
function updateWaitingarea(){
    document.form1.action = "updateWaitingarea.action";
	document.form1.submit();
}

</script>
</body>
</html>
