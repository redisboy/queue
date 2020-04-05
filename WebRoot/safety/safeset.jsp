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
    
    <title>用户安全策略设置</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/mainframe.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="/queue/plugs/ecside/js/prototype_mini.js" ></script>	
	<script type="text/javascript" src="/queue/plugs/ecside/js/ecside.js" ></script>
	<script type="text/javascript" src="/queue/plugs/ecside/js/ecside_msg_utf8_cn.js" ></script> 
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  		<input type="hidden" name="msg" id="msg" style="width: 150px" value="${msg }"/>
   		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr><td height="5" colspan="3"></td></tr>
			<tr>
				<td width="7" height="51"><img src="images/t-1.jpg" width="7" height="51"></td>
				<td height="51" valign="middle" background="images/t-2.jpg">
					<span style="font-family: '黑体', '宋体'; font-size: 18px; color: #fff; padding-left: 15px; letter-spacing: 10px; letter-spacing: 10px;">用户安全策略设置</span>
				</td>
				<td width="3" height="51"><img src="images/t-3.jpg" width="8" height="51"></td>
			</tr>
			<tr>
				<td width="7" background="images/t-4.jpg">&nbsp;</td>
				<td height="100" style="text-align: center" valign="middle" bgcolor="#f7f7f7">
					<form action="" name="form1" method="post" id="form1">
						<table id="myTable" width="60%" border="0" cellpadding="1" cellspacing="1" class="table_back">
							<%-- <tr>
				    			<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">&nbsp;规定时段外访问：</td>
				    			<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
				    				<input name="gdhour" type="number"  id="gdhour" value="${safety.gdhour}" style="width: 30px" maxlength="2" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" />&nbsp;<b>:</b>
				    				<input name="gdminute" id="gdminute" value="${safety.gdminute}" style="width:30px" maxlength="2" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" />&nbsp;&nbsp;<b>至</b>
				    				<input name="gdhour1" id="gdhour1" value="${safety.gdhour1}" style="width: 30px" maxlength="2" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" />&nbsp;<b>:</b>
				    				<input name="gdminute1" id="gdminute1" value="${safety.gdminute1}" style="width: 30px" maxlength="2" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" />&nbsp;
				    			</td>
				    		</tr> --%>
				   			<tr>
				    			<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">账户长期未使用时限：</td>
				    			<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
				    				<input name="timelimit" id="timelimit" value="${safety.timelimit}" maxlength="4" style="width: 100px" type="number" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" />&nbsp;天
				    			</td>
				    		</tr>
				    		<tr>
				    		<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">用户访问频率设置（次/时）：</td>
				    			<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
				    				<input name="visits" id="visits" type="number" value="${safety.visits}"style="width: 100px" maxlength="3" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" />&nbsp;次
				    			</td>
				    		</tr>
				    		<tr>
				    		<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">终端鉴别失败阀值（次/天）：</td>
				    			<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
				    				<input name="ipSum" id="ipSum" type="number" value="${safety.ipSum}"style="width: 100px" maxlength="3" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" />&nbsp;次
				    			</td>
				    		</tr>
				    		<tr>
				    		<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">用户鉴别失败阀值（次/天）：</td>
				    			<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
				    				<input name="userSum" id="userSum" type="number" value="${safety.userSum}"style="width: 100px" maxlength="3" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" />&nbsp;次
				    			</td>
				    		</tr>
				    		<tr>
				    		<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">日志保留天数：</td>
				    			<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
				    				<input name="logSum" id="logSum" type="number" value="${safety.logSum}"style="width: 100px" maxlength="3" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" />&nbsp;天
				    			</td>
				    		</tr>
				    			<tr>
				    		<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">最大在线用户：</td>
				    			<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
				    				<input name="sessionSum" id="sessionSum" type="number" value="${safety.sessionSum}"style="width: 100px" maxlength="3" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')" />
				    			</td>
				    		</tr>
						</table>
					</form>
					<input name="bt1" class="button" type="button" value=" 保存 " onClick="save()" />
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
		<script type="text/javascript">
		if("" !=document.getElementById("msg").value && null != document.getElementById("msg").value){
			alert(document.getElementById("msg").value);
		}
function save(){
	/* var gdhour="";
	var gdhour1="";
	var gdminute="";
	var gdminute1="";
	if(document.getElementById("gdhour").value.length>1){
		gdhour = document.getElementById("gdhour").value;
	}else{
		gdhour=0+document.getElementById("gdhour").value;
	}
	if(document.getElementById("gdhour1").value.length>1){
		gdhour1 = document.getElementById("gdhour1").value;
	}else{
		gdhour1=0+document.getElementById("gdhour1").value;
	}
	if(document.getElementById("gdminute").value.length>1){
		gdminute = document.getElementById("gdminute").value;
	}else{
		gdminute=0+document.getElementById("gdminute").value;
	}
	if(document.getElementById("gdminute1").value.length>1){
		gdminute1 = document.getElementById("gdminute1").value;
	}else{
		gdminute1=0+document.getElementById("gdminute1").value;
	}
	if(gdhour>23 || gdhour1>23 || gdminute>59 || gdminute>59 || (gdhour > gdhour1) || (gdhour==gdhour1 && gdminute > gdminute1)){
		alert("请输入正确的时间");
		return false;
	} */
	document.form1.action = "savesafety.action";
	document.form1.submit();
}

</script>
	</body>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
</html>
