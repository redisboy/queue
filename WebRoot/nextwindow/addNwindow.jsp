<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    
    <title>添加业务类型提示信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/mainframe.css" rel="stylesheet" type="text/css" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	
	-->
  </head>
  
  <body>
		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr><td height="5" colspan="3"></td></tr>
			<tr>
				<td width="7" height="51"><img src="images/t-1.jpg" width="7" height="51"></td>
				<td height="51" valign="middle" background="images/t-2.jpg">
					<span style="font-family: '黑体', '宋体'; font-size: 18px; color: #fff; padding-left: 15px; letter-spacing: 10px; letter-spacing: 10px;">添加业务类型提示信息</span>
				</td>
				<td width="3" height="51"><img src="images/t-3.jpg" width="8" height="51"></td>
			</tr>
			<tr>
				<td width="7" background="images/t-4.jpg">&nbsp;</td>
				<td height="100" style="text-align: center" valign="middle" bgcolor="#f7f7f7">
					<form action="" name="form1" method="post" id="form1">
						<table id="myTable" width="60%" border="0" cellpadding="1" cellspacing="1" class="table_back">
							<tr id="nwindowDmlbTr">
				    			<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">系统类别：</td>
				    			<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
				    			<select name="dmlb" id="nwindowDmlb">
				    				<option value="00">请选择</option>
		    						<option value="0060" <c:if test="${dmlb eq '0060'}">selected="selected"</c:if>>机动车</option>
									<option value="0008" <c:if test="${dmlb eq '0008'}">selected="selected"</c:if>>驾驶证</option>
									<option value="04" <c:if test="${dmlb eq '04'}">selected="selected"</c:if>>违法</option>	
				    			</select>
				    			</td>
				    		</tr>
				   			<tr>
				    			<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">业务类型：</td>
				    			<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
				    			<select name="dmz" id="nwindowDmz">
				    				<option value="00">请选择</option>
				    			</select>
				    			</td>
				    		</tr>
				    		<tr>
				    		<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">业务原因：</td>
				    			<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
				    			<select name="ywyybh" id="nwindowywyybh">
				    				<option value="00">请选择</option>
				    			</select>
				    			</td>
				    		</tr>
				    		<tr>
				    		<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">提示信息：</td>
				    			<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
				    				<textarea name="nextwindowval" id="nextwindowval" rows="4" cols="22"></textarea>
				    			</td>
				    		</tr>
						</table>
					</form>
					<input name="bt1" class="button" type="button" value=" 添加 " onClick="addNwindow()" />
					<input name="bt2" class="button" type="button" value=" 返回 " onClick="window.history.back(-1);" />
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
		<div id="divPop" style="background-color:#f0f0f0; display:none;">
		<iframe id="divIframe" style="position:absolute;z-index:9;background-color:#f0f0f0;" frameborder="0"></iframe>
		</div>
	</body>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="nextwindow/js/setNWindow.js"></script>
</html>
