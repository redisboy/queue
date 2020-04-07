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
<title>添加资料类型</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/queue/js/jquery.js"></script>
<script type="text/javascript" src="/queue/js/ajax.js"></script>
<script type="text/javascript">
window.onload = function() {
	//为business输入框添加blur事件
	$("#zllxbh").blur(function(){
		var zllxbh = $("#zllxbh").val();
		//检查是否为空
		if(""==zllxbh){
		   $("#zllxInfo").html("<img src='/queue/images/wrong.gif'/>资料类型不能为空!");
			return;
		}
		//检查唯一性
		var url = "checkZllx.action";
		var param = "zllxbh="+zllxbh;
		var result = new MyJqueryAjax(url,param).request();
		if("1"==result){
	    	$("#zllxInfo").html("<img src='/queue/images/wrong.gif'/>资料类型编号已被占用!");
	    	$("#zllxbh").val(""); 
		}else{
		    $("#zllxInfo").html("<img src='/queue/images/right.gif'/>资料类型编号可用!");
		}
	});
}
</script>
</head>
<body>
<form action="" name="form1" method="post">
<input type="hidden" name="flag" id="flag" value="0" />
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">添加资料类型</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    
    <table width="40%" border="0" cellpadding="1" cellspacing="1" class="table_back">
      <tr>
        <td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">资料类型编号</td>
        <td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;
        	<input name="zllxbh" id="zllxbh" maxlength="10" style="width:200px"    />
        	&nbsp;<font id="zllxInfo" color="#ff0000"></font>
        </td>
      </tr>
      <tr>
        <td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">资料类型名称:</td>
        <td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;
          <input name="zllxmc" id="zllxmc" maxlength="10" style="width:200px"    />
        </td>
      </tr>
    </table>
    <br>
      <br>
	  <input name="bt1" class="button" type="button" value=" 保存 " onClick="addZllx()"/>
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
function addZllx(){
      var zllxbh = $("#zllxbh").val();
      var zllxmc = $("#zllxmc").val();
	  if(zllxbh.length == 0){
		alert("资料类型编号不能为空!");
		return false;
	  }
	  if(zllxmc.length == 0){
		alert("资料类型名称不能为空!");
		return false;
	  }
    document.form1.action = "saveZllx.action";
	document.form1.submit();
}

</script>
</body>
</html>
