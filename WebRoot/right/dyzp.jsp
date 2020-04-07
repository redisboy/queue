<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.io.File"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.util.*,com.suntendy.queue.evaluation.domain.*"%>
<%
String clientIp = request.getHeader("x-forwarded-for");//客户端IP
if (null == clientIp || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
	clientIp = request.getHeader("Proxy-Client-IP");
}
if (null == clientIp || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
	clientIp = request.getHeader("WL-Proxy-Client-IP");
}
if (null == clientIp || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
	clientIp = request.getRemoteAddr();
}

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>打印照片</title>
		<link rel="stylesheet" type="text/css" href="css/styles.css">
	</head>
		<style media=print>
    	.Noprint{display:none;}
   		.PageNext{page-break-after: always;}
  		</style>
  		
  		 <!-- 照片显示区域 -->
  		 <center><img name='zp' style='width:400px;height:400px' src='c:\\99999.jpg'></center>
  	<body>
	   
	<br/><br/><br/>
	
	<center class="Noprint" >
 <hr align="center" width="90%" size="1" noshade>
    <p> 
        <OBJECT  id="WebBrowser"  classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2  height=0  width=0>
        </OBJECT>        
           <input  type=button  value=打印预览  onclick=document.getElementById("WebBrowser").ExecWB(7,1)>&nbsp;&nbsp;  
           <input  type="button"  value="打印"     onclick=document.getElementById("WebBrowser").ExecWB(6,2)>&nbsp;&nbsp;
           <!--  <input  type=button  value=直接打印  onclick=document.getElementById("WebBrowser").ExecWB(6,6)>&nbsp;&nbsp;  -->
           <!--  <input  type=button  value=页面设置  onclick=document.getElementById("WebBrowser").ExecWB(8,1)>&nbsp;&nbsp;  -->
    		<input type="button" value="关闭" onclick="window.close()">
    </p>
   		
 		</center>
  </body>
</html>

