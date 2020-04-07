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
    
    <title>异常数据恢复(根据6合1库更改本地)</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/mainframe.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
  </head>
  <body>
   		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr><td height="5" colspan="3"></td></tr>
			<tr>
				<td width="7" height="51"><img src="images/t-1.jpg" width="7" height="51"></td>
				<td height="51" valign="middle" background="images/t-2.jpg">
					<span style="font-family: '黑体', '宋体'; font-size: 18px; color: #fff; padding-left: 15px; letter-spacing: 10px; letter-spacing: 10px;">异常数据恢复(通过DBLink同步数据)</span>
				</td>
				<td width="3" height="51"><img src="images/t-3.jpg" width="8" height="51"></td>
			</tr>
			<tr>
				<td width="7" background="images/t-4.jpg">&nbsp;</td>
				<td height="100" style="text-align: center" valign="middle" bgcolor="#f7f7f7">
					<form action="" name="form1" method="post" id="form1">
						<table id="myTable" width="60%" border="0" cellpadding="1" cellspacing="1" class="table_back">
							<tr>
				    			<td width="30%" height="35" bgcolor="#FFFFFF" class="tableheader1">顺序号：</td>
				    			<td width="70%" height="35" bgcolor="#F1F9FD">&nbsp;
				    				<input name="sjhm" id="sjhm" style="width: 300px;text-transform: uppercase" />
				    			</td>
				    		</tr>
						</table>
					</form>
					<input name="bt1" class="button" type="button" value="确定" onClick="upStatus()" />
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
	</body>
	<script type="text/javascript">
		function upStatus(){
			var sjhm = $("#sjhm").val();
			if(null == sjhm || "" == sjhm || undefined == sjhm){
				alert("请输入顺序号");
				return;
			}
			form1.action = "callout/yiChangCL.action?sjhm="+sjhm;
			form1.submit();
		}
	</script>
</html>