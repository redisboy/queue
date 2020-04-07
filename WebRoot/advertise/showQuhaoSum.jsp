<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="/webwork" prefix="ww"%>
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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>取号信息显示窗口</title>
	<link rel="stylesheet" type="text/css" href="/queue/queue/css/fetch.css" />
	<link rel="stylesheet" type="text/css" href="/queue/window/css/PcWinCss.css" />
	<style type="text/css">
	.jh_qz {background: url(../images/queue/bg_note_qh.jpg) no-repeat;}
	</style>
	</head>
	<body scroll="no">
		<div id="data_show" class="outer" style="">
			<div class="top"></div>
			<div class="inner jh_qz">
				<div id="div1" class="div1">
					<table class="tab1" id="tipTab">
						<caption id="_showTitle"></caption>
					</table>
				</div>
				<div class="mask" id="mask_tip" >
	            	<img src="/queue/images/queue/waitnext.jpg" />
	            </div>
				<div class="div2">
					<marquee id="myMarq" direction="left" scrollamount="3"></marquee>
				</div>
				<div class="div8" id="div8">
				    <marquee id="message" direction="up" height="400px;" scrollamount="3"></marquee>
				</div>
				<div class="div3" style="text-align:left"><h1><b>
					<span id="shownr" ></span> 
				</b></h1>
				</div>
			</div>
			<div class="bottom"></div>
		</div>
	<script type="text/javascript" src="/queue/js/ajax.js"></script>
    <script type="text/javascript" src="/queue/js/jquery.js"></script>
	<script type="text/javascript" src="/queue/dwr/util.js"></script>
	<script type="text/javascript" src="/queue/dwr/engine.js"></script>
	<script type="text/javascript" src="/queue/dwr/interface/JspRegister.js"></script>
	<script type="text/javascript" src="/queue/advertise/js/showQuhaoSum.js"></script>
	<script type="text/javascript">JspRegister.register("<%=ip%>", "C");</script>
	</body>
</html>