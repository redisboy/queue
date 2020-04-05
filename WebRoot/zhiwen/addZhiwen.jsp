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
<title>添加指纹信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/queue/js/jquery.js"></script>
<script type="text/javascript" src="/queue/js/ajax.js"></script>
<script type="text/javascript">
window.onload = function() {
	zwctl.CloseZwDev();//关闭指纹仪
	if( true == zwctl.InitZwDev()){
		zwctl.StartCaptureZw("1");//开始采集指纹
	}else{
		alert("初始化设备失败");
		return;
	}
}
function kscj(){
	zwctl.StartCaptureZw("1");//开始采集指纹
}
function returncj(){
	zwctl.CloseZwDev();//关闭指纹仪
	zwctl.InitZwDev();//初始化采集指纹
	zwctl.StartCaptureZw("1");//开始采集指纹
}
</script>
</head>
<body>
<form action="" name="form1" method="post">
<input type="hidden" id="zhiwenBase" name="zhiwenBase"/>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">添加指纹信息</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    
    <table width="40%" border="0" cellpadding="1" cellspacing="1" class="table_back">
      <tr>
        <td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">身份证号</td>
        <td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;
          <input name="idNumber" id="idNumber" maxlength="10" style="width:200px"    />
          &nbsp;<font id="businessInfo" color="#ff0000">*</font>
        </td>
      </tr>
      <tr>
        <td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">指纹信息</td>
        <td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;
        	<OBJECT ID="zwctl" CLASSID="CLSID:EB358473-435E-4948-80A5-B6B69D4B8A16"   CODEBASE="prjzwcapture.cab#version=1,0,0,0" ></OBJECT>
        	<input name="zhiwenBase64" type="button" class="button" value="开始采集" onClick="kscj()"/>
        	<input name="retuCj" type="button" class="button" value="重新采集" onClick="returncj()"/>
        </td>
      </tr>
    </table>
    <br>
      <br>
	  <input name="bt1" class="button" type="button" value=" 保存 " onClick="addZhiWen()"/>
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
<script LANGUAG="javascript"   for="zwctl"   event="EventGetCapInfo(sInfo,result)"> 
	//alert("返回采集结果(result=true采集到了数据，false未采集数据)");
	alert(sInfo);
	alert(result);
	if(result = true && ""!=sInfo){
		$("#zhiwenBase").val(sInfo);
		alert("采集成功");
	}
</script> 
<script type="text/javascript">
function addZhiWen(){
      var idNumber = $("#idNumber").val();
	  var zhiwenBase = $("#zhiwenBase").val();
	  if(idNumber.length == 0){
		alert("身份证号不能为空!");
		return false;
	  }
	zwctl.CloseZwDev();//关闭指纹仪
    document.form1.action = "number/saveZhiWen.action?idNumber="+idNumber+"&zwbase64="+zhiwenBase+"&flag=0";
	document.form1.submit();
}

</script>
</body>
</html>
