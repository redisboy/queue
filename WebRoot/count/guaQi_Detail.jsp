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
    
    <title>挂起详细信息</title>
    
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
					<span style="font-family: '黑体', '宋体'; font-size: 18px; color: #fff; padding-left: 15px; letter-spacing: 10px; letter-spacing: 10px;">挂起详细信息</span>
				</td>
				<td width="3" height="51"><img src="images/t-3.jpg" width="8" height="51"></td>
			</tr>
			<tr>
				<td width="7" background="images/t-4.jpg">&nbsp;</td>
				<td height="100" style="text-align: center" valign="middle" bgcolor="#f7f7f7">
					<form action="" name="form1" method="post">
						<table id="myTable" width="60%" border="0" cellpadding="1" cellspacing="1" class="table_back">
							<tr>
				    			<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">取号人证件号码：</td>
				    			<td width="55%" height="35" bgcolor="#F1F9FD">&nbsp;&nbsp;&nbsp;${number.IDNumber}</td>
				    			<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1" rowspan="5">取号人照片：</td>
				    			<td width="25%" height="35" bgcolor="#F1F9FD" rowspan="5">
				    				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_back">
				    					<tr>
				    						<td width="100%" height="100%" bgcolor="#F1F9FD">
				    							<img alt="${number.IDNumber}照片" src="getQhPhoto.action?id=${number.id}" onerror="this.src='./images/zwtp.jpg'" style="width:180px;height:160px;border:2px;">
				    						</td>
				    					</tr>
				    				</table>
				    			</td>
				    		</tr>
				   			<tr>
				    			<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">办理人证件号码：</td>
				    			<td width="35%" height="35" bgcolor="#F1F9FD">&nbsp;&nbsp;&nbsp;${number.blridnumber}</td>
				    		</tr>
				    		<tr>
				    			<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">顺 序 号：</td>
				    			<td width="35%" height="35" bgcolor="#F1F9FD">&nbsp;&nbsp;&nbsp;${number.serialNum}</td>
				    		</tr>
				    		<tr>
				    			<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">员工编号：</td>
				    			<td width="35%" height="35" bgcolor="#F1F9FD">&nbsp;&nbsp;&nbsp;${number.code}</td>
				    		</tr>
				    		<tr>
				    			<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">员工姓名：</td>
				    			<td width="35%" height="35" bgcolor="#F1F9FD">&nbsp;&nbsp;&nbsp;${number.xm}</td>
				    		</tr>
				    		<tr>
				    			<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">窗 口 号：</td>
				    			<td width="35%" height="35" bgcolor="#F1F9FD">&nbsp;&nbsp;&nbsp;${number.barId}号窗口</td>
				    			<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1" rowspan="5">办理人照片：</td>
				    			<td width="30%" height="35" bgcolor="#F1F9FD" rowspan="5">
									<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_back">
				    					<tr>
				    						<td width="100%" height="100%" bgcolor="#F1F9FD">
				    							<img alt="${number.blridnumber}照片" src="getPjPhoto.action?id=${number.id}" onerror="this.src='./images/zwtp.jpg'" style="width:180px;height:160px;border:2px;">
				    						</td>
				    					</tr>
				    				</table>
								</td>
				    		</tr>
				    		<tr>
				    			<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">取号时间：</td>
				    			<td width="35%" height="35" bgcolor="#F1F9FD">&nbsp;&nbsp;&nbsp;${number.enterTime}</td>
				    		</tr>
				    		<tr>
				    			<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">开始时间：</td>
				    			<td width="35%" height="35" bgcolor="#F1F9FD">&nbsp;&nbsp;&nbsp;${number.beginTime}</td>
				    		</tr>
				    		<tr>
				    			<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">结束时间：</td>
				    			<td width="35%" height="35" bgcolor="#F1F9FD">&nbsp;&nbsp;&nbsp;${number.endTime}</td>
				    		</tr>
						</table>
					</form>
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
</html>
