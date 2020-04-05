<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="/webwork" prefix="ww"%>
<%@ page import="com.suntendy.queue.util.*"%>
<%
	String ip = request.getHeader("x-forwarded-for");
	if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("Proxy-Client-IP");
	}
	if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getHeader("WL-Proxy-Client-IP");
	}
	if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		ip = request.getRemoteAddr();
	}
	//防止js缓存
    String jsVer = new DateUtils().jsVer();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>窗口屏页面</title>
<script type="text/javascript">
	
		window.onload = function(){
			$("#ip").text('<%=ip%>');
			var url = '/queue/number/dsAutoInit.action';
		    var operatorInfo = {loginIP:'<%=ip%>'};
		    $.post(url, operatorInfo, function(datas) {
				var jsonReturn = JSON.parse(datas);
	           	if (jsonReturn.bj) {
		        	$("#ckid").text(jsonReturn.ckid);
		        	$("#ckmc").text(jsonReturn.ckmc);
		        	$("#sxh").text(jsonReturn.sxh);
	           	} else {
					$("#ckid").css("display","none");
					$("#ckmc").css("display","none");
					$("#sxh").css("display","none");
					$("#hygl").css("display","");
	           	}
	        });
		}
</script>
<style type="text/css">
<!--
*{
margin:0;
padding:0;
}
body{
font-family:"黑体",sans-serif;
}
.outer{
width:1280px;
height:720px;
margin:0 auto;
text-align:center;
background:url(images/bg.jpg) no-repeat;
position:relative;
}
.div_1{
position:absolute;
font-size:320px;
color:#fff;
left:30px;
top:-20px;
text-shadow:10px 10px 20px #000;
}
.div_2{
position:absolute;
font-size:213px;
color:#fff;
/*渐变
  font-weight: bold;
  font-family: helvetica;
  text-align:center;
  background: -webkit-linear-gradient(left, #4f185d , #fe5d4b);
  -webkit-background-clip: text;       
  -webkit-text-fill-color: transparent; 
*/
left:380px;
top:57px;
text-shadow:10px 10px 20px #000;
}
.div_3{
position:absolute;
left:230px;
top:260px;
font-size:280px;
color:#ffff00;
letter-spacing:30px;
text-shadow:10px 10px 20px #000;
}
.div_4{
position:absolute;
left:276px;
top:370px;
font-size:213px;
color:#ffff00;
letter-spacing:10px;
text-shadow:10px 10px 20px #000;
}
.div_5{
position:absolute;
left:53px;
top:115px;
font-size:283px;
color:#ffff00;
letter-spacing:10px;
text-shadow:10px 10px 20px #000;
}
.div_6{
position:absolute;
left:20px;
top:700px;
font-size:4px;
color:#009ACD;

text-shadow:10px 10px 20px #000;
}
-->
</style>
</head>
<body>
	<div class="outer">
		<div class="div_1" id="ckid"></div>
		<div class="div_2" id="ckmc"></div>
		<div class="div_3" id="sxh"></div>
		<div class="div_4" id="ztsl" style="display: none;">暂停受理</div>
		<div class="div_5" id="hygl" style="display: none;">欢迎光临</div>
		<div class="div_6" id="ip"></div>
	</div>
	<script type="text/javascript" src="/queue/js/ajax.js?ver=<%=jsVer%>"></script>
    <script type="text/javascript" src="/queue/js/jquery.js?ver=<%=jsVer%>"></script>
	<script type="text/javascript" src="/queue/dwr/util.js?ver=<%=jsVer%>"></script>
	<script type="text/javascript" src="/queue/dwr/engine.js?ver=<%=jsVer%>"></script>
	<script type="text/javascript" src="/queue/dwr/interface/JspRegister.js?ver=<%=jsVer%>"></script>
	<script type="text/javascript" src="/queue/window/js/qrcode.js?ver=<%=jsVer%>"></script>
	<script type="text/javascript" src="/queue/waiping/js/waiPing.js?ver=<%=jsVer%>"></script>
	<script type="text/javascript">JspRegister.register("<%=ip%>", "S");</script>
</body>
</html>