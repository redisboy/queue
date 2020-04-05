<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style type="text/css">
<!--
*{
margin:0;
padding:0;
}
body{
background: #fff;
font-family: "方正黑体简体", "黑体", "宋体";
font-size:12px;
letter-spacing:1px;
}
.sfz{
margin:25px auto;
width:340px;
height:465px;
position:relative;
}
.div1,
.div2,
.div3,
.div4,
.div5,
.div6,
.div7,
.div8,
.div9,
.div10,
.photo{
position:absolute;
}
.div1{
left:68px;
top:33px;
}
.div2{
left:68px;
top:57px;
}
.div3{
left:140px;
top:57px;
}
.div4{
left:68px;
top:81px;
}
.div5{
left:116px;
top:81px;
width:20px;
text-align:center;
}
.div6{
left:148px;
top:81px;
width:20px;
text-align:center;
}
.div7{
left:68px;
top:105px;
width:135px;
line-height:18px;
}
.div8{
left:115px;
top:170px;
font-size:13px;
font-weight:bold;
letter-spacing:2px;
}
.div9{
left:135px;
top:400px;
}
.div10{
left:135px;
top:426px;
}
.photo{
left:216px;
top:28px;
}
-->
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="jquery.PrintArea.js"></script>
<object id="WebBrowser" classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height="0" width="0"></object>
<script type="text/javascript" defer="defer">
	function printID(){
		var now = $("#now");
		var myDate = new Date();
		now.html("本文件仅用于"+myDate.toLocaleString()+"办理车驾管业务");
		//document.all.WebBrowser.ExecWB(6,2);
		//window.close();
	}
</script>
</head>
<body onload="printID()">
	<div id="idcard" class="sfz">
		<img src="images/bg_sfz.jpg" alt="" />
		<div id="nameA" class="div1"><%=URLDecoder.decode(request.getParameter("nameA"),"utf-8") %></div>
		<div class="div2"><%=URLDecoder.decode(request.getParameter("Sex"),"utf-8") %></div>
		<div class="div3"><%=URLDecoder.decode(request.getParameter("Nation"),"utf-8") %></div>
		<div class="div4"><%=request.getParameter("Born").substring(0,4) %></div>
		<div class="div5"><%=request.getParameter("Born").substring(4,6) %></div>
		<div class="div6"><%=request.getParameter("Born").substring(6,8) %></div>
		<div class="div7"><%=URLDecoder.decode(request.getParameter("Address"),"utf-8") %></div>
		<div class="div8"><%=request.getParameter("number") %></div>
		<div class="div9"><%=URLDecoder.decode(request.getParameter("Police"),"utf-8") %></div>
		<div class="div10"><%=request.getParameter("UserLifeB")%>-<%=URLDecoder.decode(request.getParameter("UserLifeE"),"utf-8") %></div>
		<div class="photo"><img src="/queue/IcardPic/<%=request.getParameter("number") %>.jpg" alt="" /></div>
	</div>
	<div id="now" align="center" style="font-size: 18px"></div>
</body>
</html>