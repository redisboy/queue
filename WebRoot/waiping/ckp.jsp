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
	           		if(jsonReturn.code != undefined){
			        	$("#ckid").text(jsonReturn.ckid);
			        	$("#ckmc").text(jsonReturn.ckmc);
			        	$("#sxh").text(jsonReturn.sxh);
			        	$("#tootip_img").attr("src", jsonReturn.photo);
			        	$("#div_zym").html(jsonReturn.comments);
			        	$("#police_code").html(jsonReturn.code);
						$("#police_name").html(jsonReturn.name);
	           		}else{
	           			$("#outer").css("display","none");
						$("#outer2").css("display","");
	           			$("#ckid_h").text(jsonReturn.ckid);
			        	$("#ckmc_h").text(jsonReturn.ckmc);
	           		}
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
background:url(images/bj_1280_20190311.jpg) no-repeat;
position:relative;
}
.div_1{
position:absolute;
font-size:300px;
color:#fff;
left:320px;
top:5px;
text-shadow:10px 10px 20px #000;
}
.div_1_h{
position:absolute;
font-size:300px;
color:#fff;
left:440px;
top:0px;
text-shadow:10px 10px 20px #000;
}
.div_2{
position:absolute;
left:970px;
top:90px;
text-shadow:10px 10px 20px #000;
}
.div_2 img{
width:230px;
height:345px;
}
.div_3{
position:absolute;
left:127px;
top:435px;
width:696px;
height:208px;
font-size:120px;
color:#ffff00;
letter-spacing:30px;
text-shadow:10px 10px 20px #000;
text-align:center;
display:table;
}
.cell{
display:table-cell; 
vertical-align:middle;
}
.cell p{
line-height:100px;
padding-left:35px;
}
.div_4,
.div_5,
.div_6{
position:absolute;
left:1020px;
top:440px;
color:#fff;
font-size:30px;
text-shadow:10px 10px 20px #000;
}
.div_5{
top:520px;
}
.div_6{
top:590px;
}
.outer2{
width:1280px;
height:720px;
margin:0 auto;
text-align:center;
background:url(images/bj_1280_20190311_h.jpg) no-repeat;
position:relative;
}
.outer2 .div_1{
left:535px;
top:87px;
}
.outer2 .div_3{
left:307px;
top:435px;
width:701px;
height:208px;
}
.div_7{
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
	<div>
		<div class="outer" id="outer">
			<div class="div_1" id="ckid"></div>
			<div class="div_2">
				<img id="tootip_img" src="/queue/images/jgzp.jpg" onerror="this.src='/queue/images/jgzp.jpg'" />
			</div>
			<div class="div_3">
				<div class="cell">
					<div id="ckmc"></div>
					<div id="zanting" style="display: none;">暂停受理</div>
				</div>
			</div>
			<div class="div_4" id="police_code"></div>
			<div class="div_5" id="police_name"></div>
			<div class="div_6" id="div_zym"></div>
		</div>
		<div class="outer2" style="display:none;" id="outer2">
			<div class="div_1_h" id="ckid_h"></div>		
			<div class="div_3">
				<div class="cell">
					<p id="ckmc_h"></p>
				</div>
			</div>
		</div>
	</div>
	<div class="div_7" id="ip"></div>
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