<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
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
%>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>叫号信息显示屏</title>
		<script type="text/javascript" src="/queue/js/jquery.js"></script>
    	<script type="text/javascript" src="/queue/dwr/util.js"></script>
		<script type="text/javascript" src="/queue/dwr/engine.js"></script>
		<script type="text/javascript" src="/queue/dwr/interface/JspRegister.js"></script>
		<script type="text/javascript">JspRegister.register("<%=ip%>", "L");</script>
		<script type="text/javascript" src="/queue/waiping/js/tv_queue_duilieping.js"></script>
		<script type="text/javascript">     
	    window.onload = function(){
	    	
		}
		
		function getCurDate()
			{
			 var d = new Date();
			 var week;
			 switch (d.getDay()){
			 case 1: week="星期一"; break;
			 case 2: week="星期二"; break;
			 case 3: week="星期三"; break;
			 case 4: week="星期四"; break;
			 case 5: week="星期五"; break;
			 case 6: week="星期六"; break;
			 default: week="星期天";
			 }
			 var years = d.getFullYear();
			 var month = add_zero(d.getMonth()+1);
			 var days = add_zero(d.getDate());
			 var hours = add_zero(d.getHours());
			 var minutes = add_zero(d.getMinutes());
			 var seconds=add_zero(d.getSeconds());
			 var ndate = years+"/"+month+"/"+days;
			 var sjdate = hours+":"+minutes+":"+seconds;
			 nyr.innerHTML = ndate;
			 xq.innerHTML = week;
			 sj.innerHTML = sjdate;
			}
		function add_zero(temp)
			{
			 if(temp<10) return "0"+temp;
			 else return temp;
			}
		setInterval("getCurDate()",100);
		</script>
		<style type="text/css">
				<!--
			*{
			margin:0;
			padding:0;
			}
			body{
			font-family:"宋体",sans-serif;
			max-width: 720px;
			max-height: 1280px;
			}
			.outer{
			width:720px;
			height:1280px;
			background:url(images/bg_720_1280_20190311.jpg) no-repeat;
			margin:0 auto;
			position:relative;
			}
			.div1,
			.div2{
			position:absolute;
			left:8px;
			top:13px;
			font-size:27px;
			color:#fff;
			}
			.div2{
			left:33px;
			top:56px;
			}
			.div3{
			position:absolute;
			color:#fff;
			left:164px;
			top:23px;
			font-size:47px;
			}
			.div4{
			position:absolute;
			left:600px;
			top:39px;
			color:#fff;
			font-size:27px;
			}
			ul{
			position:absolute;
			top:202px;
			list-style:none;
			font-size:54px;
			color:#ff0;
			width:720px;
			height:980px;
			}
			li{
			width:1080px;
			height:98px;
			line-height:98px;
			}
			.sp1{
			margin-left:145px;
			}
			.sp2{
			margin-left:145px;
			}
			-->
		</style>
  </head>
  
  <body>
    <div class="outer">
		<div class="div1" id="nyr"></div>
		<div class="div2" id="xq"></div>
		<div class="div3">叫号信息显示屏</div>
		<div class="div4" id="sj"></div>
		<ul name="date_u3" id="date_u3">
			<li>
				<span class="sp1" style="width: 440px;">A10114</span>
				<span class="sp2">16</span>
			</li>
			<li>
				<span class="sp1">A10114</span>
				<span class="sp2">16</span>
			</li>
			<li>
				<span class="sp1">A10114</span>
				<span class="sp2">16</span>
			</li>
			<li>
				<span class="sp1">A10114</span>
				<span class="sp2">16</span>
			</li>
			<li>
				<span class="sp1">A10114</span>
				<span class="sp2">16</span>
			</li>
			<li>
				<span class="sp1">A10114</span>
				<span class="sp2">16</span>
			</li>
			<li>
				<span class="sp1">A10114</span>
				<span class="sp2">16</span>
			</li>
			<li>
				<span class="sp1">A10114</span>
				<span class="sp2">16</span>
			</li>
			<li>
				<span class="sp1">A10114</span>
				<span class="sp2">16</span>
			</li>
			<li>
				<span class="sp1">A10114</span>
				<span class="sp2">16</span>
			</li>
		</ul>
	</div>
  </body>
</html>
