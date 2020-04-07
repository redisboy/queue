<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="/webwork" prefix="ww"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>取号显示内容设置</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='/queue/dwr/interface/gzsbUtil.js'></script>
<script type='text/javascript' src='/queue/dwr/engine.js'></script>
<script type='text/javascript' src='/queue/dwr/util.js'></script>
<script type='text/javascript' src='/queue/js/dwrgzsbglbm.js'></script>
<link rel="stylesheet" href="/queue/printSet/editor-min.css" type="text/css"></link>
<!-- 1. 页头引入 editor css -->
<link rel="stylesheet" href="/queue/printSet/content-min.css" type="text/css"/>
</head>
<body>
<form action="addSHowQuHaoContent.action" name="form1" method="post">
<input type="hidden" name="id" value="${id}">
<input type="hidden" name="isHaveValue" value="${isHaveValue}">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">取号显示内容设置</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    
    <table width="50%" border="0" cellpadding="1" cellspacing="1" class="table_back">
      <tr>
        <td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">主屏内容</td>
        <td width="80%" height="35" bgcolor="#F1F9FD">
        <div id="demo-page">
			<!-- 文本编辑器  begin-->
			<TABLE border="0" cellpadding="2" cellspacing="1" width="100%">
				<div id="page1"><textarea name="code" id="demo" rows="50" cols="152" style="width: 350px; height: 295px"  >${message}</textarea></div>
			</TABLE>
			<!-- 文本编辑器  end-->
		</div>
        </td>
      </tr>
      <tr>
        <td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">滚动条内容</td>
        <td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
         <textarea rows="5" style="width: 340px;" name="gdMsg">${gdMsg}</textarea>
          &nbsp;<font color="#ff0000"></font>
        </td>
      </tr>
      <tr>
        <td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">状态</td>
        <td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
            <input type="radio" name="state" value="0" <ww:if test="${state eq 0}">checked</ww:if> />是
			<input type="radio" name="state" value="1" <ww:if test="${state eq 1}">checked</ww:if> />否
          &nbsp;<font color="#ff0000"></font>
        </td>
      </tr>
      
    </table>
    <br>
      <br>
	  <input name="bt1" class="button" type="submit" value="保存"/>
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

<!-- 2. 页尾引入 editor js and init code -->
  <script type="text/javascript" src="/queue/printSet/editor-aio.js"></script>
<script type="text/javascript"  >KISSY.Editor("demo")</script>
</body>
</html>
