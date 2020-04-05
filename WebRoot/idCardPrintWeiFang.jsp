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
		document.all.WebBrowser.ExecWB(6,2);
		window.close();
	}
</script>
</head>
<body onload="printID()">
	<div id="idcard" class="sfz">
		<img src="images/bg_sfz.jpg" alt="" />
		<div id="nameA" class="div1"><%=URLDecoder.decode(request.getParameter("nameA"),"utf-8") %></div>
		<div class="div2">
			<c:if test="${param.Sex eq '1'}">男</c:if>
			<c:if test="${param.Sex eq '2'}">女</c:if>
		</div>
		<div class="div3">
			<c:if test="${param.Nation eq '01'}">汉</c:if>
			<c:if test="${param.Nation eq '02'}">蒙古族</c:if>
			<c:if test="${param.Nation eq '03'}">回族</c:if>
			<c:if test="${param.Nation eq '04'}">藏族</c:if>
			<c:if test="${param.Nation eq '05'}">维吾尔族</c:if>
			<c:if test="${param.Nation eq '06'}">苗族</c:if>
			<c:if test="${param.Nation eq '07'}">彝族</c:if>
			<c:if test="${param.Nation eq '08'}">壮族</c:if>
			<c:if test="${param.Nation eq '09'}">布依族</c:if>
			<c:if test="${param.Nation eq '10'}">朝鲜族</c:if>
			<c:if test="${param.Nation eq '11'}">满族</c:if>
			<c:if test="${param.Nation eq '12'}">侗族</c:if>
			<c:if test="${param.Nation eq '13'}">瑶族</c:if>
			<c:if test="${param.Nation eq '14'}">白族</c:if>
			<c:if test="${param.Nation eq '15'}">土家族</c:if>
			<c:if test="${param.Nation eq '16'}">哈尼族</c:if>
			<c:if test="${param.Nation eq '17'}">哈萨克族</c:if>
			<c:if test="${param.Nation eq '18'}">傣族</c:if>
			<c:if test="${param.Nation eq '19'}">黎族</c:if>
			<c:if test="${param.Nation eq '20'}">傈僳族</c:if>
			<c:if test="${param.Nation eq '21'}">佤族</c:if>
			<c:if test="${param.Nation eq '22'}">畲族</c:if>
			<c:if test="${param.Nation eq '23'}">高山族</c:if>
			<c:if test="${param.Nation eq '24'}">拉祜族</c:if>
			<c:if test="${param.Nation eq '25'}">水族</c:if>
			<c:if test="${param.Nation eq '26'}">东乡族</c:if>
			<c:if test="${param.Nation eq '27'}">纳西族</c:if>
			<c:if test="${param.Nation eq '28'}">景颇族</c:if>
			<c:if test="${param.Nation eq '29'}">柯尔克孜族</c:if>
			<c:if test="${param.Nation eq '30'}">土族</c:if>
			<c:if test="${param.Nation eq '31'}">达翰尔族</c:if>
			<c:if test="${param.Nation eq '32'}">仫佬族</c:if>
			<c:if test="${param.Nation eq '33'}">羌族</c:if>
			<c:if test="${param.Nation eq '34'}">布朗族</c:if>
			<c:if test="${param.Nation eq '35'}">撒拉族</c:if>
			<c:if test="${param.Nation eq '36'}">毛南族</c:if>
			<c:if test="${param.Nation eq '37'}">仡佬族</c:if>
			<c:if test="${param.Nation eq '38'}">锡伯族</c:if>
			<c:if test="${param.Nation eq '39'}">阿昌族</c:if>
			<c:if test="${param.Nation eq '40'}">普米族</c:if>
			<c:if test="${param.Nation eq '41'}">哈萨克族</c:if>
			<c:if test="${param.Nation eq '42'}">怒族</c:if>
			<c:if test="${param.Nation eq '43'}">乌孜别克族</c:if>
			<c:if test="${param.Nation eq '44'}">俄罗斯族</c:if>
			<c:if test="${param.Nation eq '45'}">鄂温克族</c:if>
			<c:if test="${param.Nation eq '46'}">德昂族</c:if>
			<c:if test="${param.Nation eq '47'}">保安族</c:if>
			<c:if test="${param.Nation eq '48'}">裕固族</c:if>
			<c:if test="${param.Nation eq '49'}">京族</c:if>
			<c:if test="${param.Nation eq '50'}">塔塔尔族</c:if>
			<c:if test="${param.Nation eq '51'}">独龙族</c:if>
			<c:if test="${param.Nation eq '52'}">鄂伦春族</c:if>
			<c:if test="${param.Nation eq '53'}">赫哲族</c:if>
			<c:if test="${param.Nation eq '54'}">门巴族</c:if>
			<c:if test="${param.Nation eq '55'}">珞巴族</c:if>
			<c:if test="${param.Nation eq '56'}">基诺族</c:if>
			<c:if test="${param.Nation eq '57'}">其它</c:if>
			<c:if test="${param.Nation eq '98'}">外国人入籍</c:if>
		</div>
		<div class="div4"><%=request.getParameter("Born").substring(0,4) %></div>
		<div class="div5"><%=request.getParameter("Born").substring(4,6) %></div>
		<div class="div6"><%=request.getParameter("Born").substring(6,8) %></div>
		<div class="div7"><%=URLDecoder.decode(request.getParameter("Address"),"utf-8") %></div>
		<div class="div8"><%=request.getParameter("number") %></div>
		<div class="div9"><%=URLDecoder.decode(request.getParameter("Police"),"utf-8") %></div>
		<div class="div10"><%=request.getParameter("UserLifeB").substring(0,4) %>.<%=request.getParameter("UserLifeB").substring(4,6) %>.<%=request.getParameter("UserLifeB").substring(6,8) %>-<%=request.getParameter("UserLifeE").substring(0,4) %>.<%=request.getParameter("UserLifeE").substring(4,6) %>.<%=request.getParameter("UserLifeE").substring(6,8) %></div>
		<div class="photo"><img src="/queue/IcardPic/<%=request.getParameter("number") %>.jpg" alt="" /></div>
	</div>
	<div id="now" align="center" style="font-size: 18px"></div><br /><br /><br />
	<div align="center" style="font-size: 12px;">您所办理的业务为：<span style="font-size: 18px;"><%=URLDecoder.decode(request.getParameter("queueName"),"utf-8") %></span><span style="font-size: 18px;">(<%=request.getParameter("count") %>笔)</span></div>
	<div align="center" style="font-size: 12px;">您的号码为：<span style="font-size: 24px;"><%=request.getParameter("serialNum") %></span></div>
	<div align="center" style="font-size: 12px;"><span style="font-size: 24px;"><%=URLDecoder.decode(request.getParameter("DYname"),"utf-8") %></span>  证件号码：<span style="font-size: 24px;"><%=request.getParameter("IDNumber") %></span></div>
	<div align="center" style="font-size: 12px;">在您前面还有：<span style="font-size: 18px;"><%=request.getParameter("peoNum") %></span>位在等候，</div>
	<div align="center" style="font-size: 12px;">请您在休息区等候,您的号码将被呼叫并在窗口显示过号无效，请重新取号，谢谢！</div>
</body>
</html>