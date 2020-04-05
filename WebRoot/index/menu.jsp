<%@ page language="java" pageEncoding="utf-8"%>
<%
	String name = (String)session.getAttribute("name");
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath %>" />
<title>系统</title>
<link rel="stylesheet" href="plugs/dtree/css/dtree.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/style.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
body {
	background-color: #CFE8FE;
	margin:0;
	padding:0;
}
.menu_table_bg {
	background-image: url(/queue/images/bg3.gif);
	background-repeat: no-repeat;
	background-position: left bottom;
	padding-left:7px;
}
-->
</style>
<script type="text/javascript" src="plugs/dtree/js/dtree.js"></script>
<script type='text/javascript'>
var weekday = new initArray("星期日","星期一","星期二","星期三","星期四","星期五","星期六"); 

function initArray(){
	this.length = initArray.arguments.length;
	for(var i=0;i<this.length;i++){
		this[i+1] = initArray.arguments[i];
	}
}

function CurrentTime(){ 
	today = new Date(); 
	year = today.getFullYear();
	month = today.getMonth()+1;
	if(month <= 9){
		month = "0" + month;
	}
	day = today.getDate();
	if(day <= 9){
		day = "0" + day;
	} 
	var clocktext = year + "年" + month + "月" + day + "日  " + weekday[today.getDay()+1]; //时间格式
	clocktimer = setTimeout("CurrentTime()", 1000); 
	var showtime = document.getElementById("time");
	showtime.innerHTML = clocktext;   //innerText
}

</script>
</head>
<body style=overflow-x:hidden  leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="195"  height="100%" border="0" cellpadding="0" cellspacing="0" class="menu_table_bg">
  <tr>
    <td width="170" valign="top"><table width="165"  height="8" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="168"></td>
      </tr>
    </table>
      <table width="165" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td><img src="/queue/images/left_r2_c2.jpg" width="165" height="11"></td>
        </tr>
        <tr>
          <td height="21" align="center" background="/queue/images/left_r3_c2.jpg">
		  <script language=JavaScript>
	 	  today=new Date();
	 	  function initArray(){
	      	this.length = initArray.arguments.length;
	      	for(var i=0;i<this.length;i++){
	      		this[i+1] = initArray.arguments[i];
	      	}
	      }
	      var d=new initArray(" 星期日"," 星期一"," 星期二"," 星期三"," 星期四"," 星期五","星期六");
	      document.write("<font color=#ffffff style='font-size:9pt;font-family: 宋体'><b> ",today.getYear(),"年",today.getMonth()+1,"月",today.getDate(),"日",d[today.getDay()+1],"</b></font>" ); 
	      </script>
		  </td>
        </tr>
           <tr>
          <td height="19" align="center" background="/queue/images/left_r4_c2.jpg">
          	
          </td>
        </tr>
        <tr>
          <td height="19" align="center" background="/queue/images/left_r4_c2.jpg">
          	<span style="text-align:center">
          	<font color="#FFFFFF" style="font-size:9pt;font-family: 宋体"><b>${user.name}，欢迎您！<a href="exit.action" target="_top">退出</a></b></font><br>
          	</span>
          </td>
        </tr>
      <tr>
          <td height="19" align="center" background="/queue/images/left_r4_c2.jpg">
          	<span style="text-align:center">
          	<font color="#FFFFFF" style="font-size:8pt;font-family: 宋体"><b>本次登录:${user.RSACheck}</br>IP:${user.loginIp}</b></font><br>
          	<font color="#FFFFFF" style="font-size:8pt;font-family: 宋体"><b>上次登录:${loginl.rTime}</br>IP:${loginl.originIp}</b></font><br>
          	<font color="#FFFFFF" style="font-size:8pt;font-family: 宋体"><b>用户有效期:${user.yhyxq}天</b></font><br>
          	<font color="#FFFFFF" style="font-size:8pt;font-family: 宋体"><b>密码有效期:${user.passCode}天</b></font><br>
          	</span>
          </td>
        </tr>
         <tr>
          <td height="19" align="center" background="/queue/images/left_r4_c2.jpg">
          	<span style="text-align:center">
          	 <marquee height=50px vspace="5" hspace="5" align="right" scrollamount="2" direction = "up" behavior = "scroll">
							<div style='layout-grid:15.6pt;'>
							<font color='#FFFFFF' style='font-size:9pt;font-family: 宋体'><b>失败记录如下:${loginls.RSACheck}</br>
							</div>
			</marquee>
          	</span>
          </td>
        </tr>
        <tr>
          <td><img src="/queue/images/left_r5_c2.jpg" width="165" height="19"></td>
        </tr>
        <tr>
          <td><img src="/queue/images/left_r6_c2.jpg" width="165" height="5"></td>
        </tr>
        <tr>
          <td><img src="/queue/images/left_r7_c2.jpg" width="165" height="16"></td>
        </tr>
        <tr>
          <td><img src="/queue/images/left_r8_c2.jpg" width="165" height="7"></td>
        </tr>
        <tr>
          <td align="center" background="/queue/images/left_r9_c2.jpg">
          <TABLE cellSpacing=0 cellPadding=0 width="90%" border=0>
          <TR><TD>
          <!--insert menu begin-->
		  <script type="text/javascript">
		  <%=session.getAttribute("tree")%> 
		  </script>
          <!--insert menu end-->
          </TD></TR>
          </TABLE>
          </td>
        </tr>
        <tr>
          <td><img src="/queue/images/left_r10_c2.jpg" width="165" height="13"></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
