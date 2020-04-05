<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="/webwork" prefix="ww"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>修改广告</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='/queue/dwr/interface/gzsbUtil.js'></script>
<script type='text/javascript' src='/queue/dwr/engine.js'></script>
<script type='text/javascript' src='/queue/dwr/util.js'></script>
<script type='text/javascript' src='/queue/js/dwrgzsbglbm.js'></script>
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
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">修改广告</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    
    <table width="50%" border="0" cellpadding="1" cellspacing="1" class="table_back">
      <tr>
        <td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">广告内容</td>
        <td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
           <input type="hidden" id="id" name="id"  value="<c:out value="${id }"></c:out>" />
         <textarea rows="5" style="width: 300px;" name="message"  >${message}</textarea>
          &nbsp;<font color="#ff0000"></font>
        </td>
      </tr>
      
      <tr>
        <td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">状态</td>
        <td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
        <select name="state" id="state" value="${state}">
                    <option value="1"  <ww:if test="${state ==1}">selected</ww:if>  >有效</option>
              		<option value="0"  <ww:if test="${state ==0}">selected</ww:if>  >无效</option>
            </select>
          &nbsp;<font color="#ff0000"></font>
        </td>
      </tr>
    </table>
    <br>
      <br>
	  <input name="bt1" class="button" type="button" value="更改 " onClick="updateAdvertiseBycode()"/>
	  <input name="bt2" class="button" type="button" value="返回 " onClick="window.history.back(-1);"/>
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
function updateAdvertiseBycode(){
var message = document.form1.message.value;
message=message.replace(/^\s*|\s*$/g,'');
	  if(message.length == 0){
		alert("广告内容不能为空!");
		return false;
	  }
    document.form1.action = "updateByCode.action";
	document.form1.submit();
}
</script>
</body>
</html>
