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
	<title>电视窗口</title>
	<link rel="stylesheet" type="text/css" href="/queue/queue/css/fetch.css" />
		<script type="text/javascript" src="/queue/js/jquery.js"></script>
    	<script type="text/javascript" src="/queue/dwr/util.js"></script>
		<script type="text/javascript" src="/queue/dwr/engine.js"></script>
		<script type="text/javascript" src="/queue/dwr/interface/JspRegister.js"></script>
		<script type="text/javascript">JspRegister.register("<%=ip%>", "L");</script>
		<script type="text/javascript" src="/queue/window/js/tv_queue_led.js"></script>
		<script type="text/javascript" >
		var fontsize = window.opener.fontsize;
		var hallname = window.opener.hallname;var gdxx = window.opener.gdxx;var gdxx1 = window.opener.gdxx1;
		var title1 = window.opener.title1;var content1 = window.opener.content1;var title2 = window.opener.title2;
		var content2 = window.opener.content2;var title3 = window.opener.title3;var content3 = window.opener.content3;
		var title4 = window.opener.title4;var content4 = window.opener.content4;
		var width=window.opener.width;var height=window.opener.height;
		
		var date = new Date();       
	    var y = date.getFullYear();       
	    var m = date.getMonth()+1;       
	    var d = date.getDate();       
	    var h = date.getHours();       
	    var i = date.getMinutes();       
	    var s = date.getSeconds();   
		
		<!--拖动层 begin -->
		var rDrag = {o:null,
		init:function(o){o.onmousedown = this.start;},
		start:function(e){var o;
		e = rDrag.fixEvent(e);e.preventDefault && e.preventDefault();rDrag.o = o = this;
		o.x = e.clientX - rDrag.o.offsetLeft;o.y = e.clientY - rDrag.o.offsetTop;
		document.onmousemove = rDrag.move;document.onmouseup = rDrag.end;
		},
		move:function(e){
		e = rDrag.fixEvent(e);
		var oLeft,oTop;
		oLeft = e.clientX - rDrag.o.x;
		oTop = e.clientY - rDrag.o.y;
		rDrag.o.style.left = oLeft + 'px';
		rDrag.o.style.top = oTop + 'px';
		},end:function(e){e = rDrag.fixEvent(e);rDrag.o = document.onmousemove = document.onmouseup = null;},
		fixEvent: function(e){if (!e) {e = window.event;e.target = e.srcElement;e.layerX = e.offsetX;e.layerY = e.offsetY;}return e;}}
		window.onload = function(){
		    var obj = document.getElementById('top_u1');
			rDrag.init(obj);
			$("#tab1").width(width);$("#tab1").height(height);$("#hallname").html(hallname);
			$("#gdxx").html(gdxx);$("#gdxx1").html(gdxx1);
			$("#title1").html(title1);
			if("排队信息"==title1){$("#content1").html("<ul name='date_u1' id='date_u1'></ul>");}
			else if("领证信息"==title1){$("#content1").html("<ul name='lzxx' id='lzxx'></ul>");}else{$("#content1").html(content1);}
			$("#title2").html(title2);
			if("排队信息"==title2){$("#content2").html("<ul name='date_u1' id='date_u1'></ul>");}
			else if("领证信息"==title2){$("#content2").html("<ul name='lzxx' id='lzxx'></ul>");}else{$("#content2").html(content2);}
			$("#title3").html(title3);
			if("排队信息"==title3){$("#content3").html("<ul name='date_u1' id='date_u1'></ul>");}
			else if("领证信息"==title3){$("#content3").html("<ul name='lzxx' id='lzxx'></ul>");}else{$("#content3").html(content3);}
			$("#title4").html(title4);
			if("排队信息"==title4){$("#content4").html("<ul name='date_u1' id='date_u1'></ul>");}
			else if("领证信息"==title4){$("#content4").html("<ul name='lzxx' id='lzxx'></ul>");}else{$("#content4").html(content4);}
			$("#nowDate").html("<ul>"+y+"年"+(m>9?m:("0"+m))+"月"+(d>9?d:("0"+d))+"日</ul>"); 
		}
		</script>
		<!-- 拖动层 end -->
		<style type="text/css">
		li {list-style:none;}.ul_1 li{height:32px;line-height:32px;border-bottom: none;}
		<!--font-family:"Arial",
		*{margin:0;padding:0;}
		 html{height:100%;}body{color:#f00;text-shadow: 0 0 0 transparent, 0 0 0;
			font-size:10px;height:100%;background-color:#000;}
		.tab1{border:1px solid #f00;border-collapse:collapse;}
		.tab1 td{text-align:center;/*line-height:20px;*/}
		.border_t{border-top:1px solid #f00;}.border_b{border-bottom:1px solid #f00;}
		.border_l{border-left:1px solid #f00;}.border_r{border-right:1px solid #f00;}
		.title{font-size:25px;height:10%;}.td1{height:6%;}.td2{height:6%;}
		.td3{vertical-align:top;height:72%;}.td4{height:6%;}-->
		</style> 
	</head>
	<body scroll="no">
	<div class="inner tv" name="top_u1" id="top_u1" style="position:absolute;left:0;top:0;background:#000;font-family:'楷体',sans-serif;">
				<table id="tab1" class="tab1">
				<tr>
					<td id="hallname" colspan="4" class="border_b title" ></td>
				</tr>
				<tr>
					<td class="border_b td1" colspan="3"><marquee id="gdxx"></marquee></td>
					<td id="nowDate" class="border_b td1" style="font-size:14px;"></td>
				</tr>
				<tr>
					<td id="title1" class="border_b td2" style="width:23%;"></td>
					<td id="title2" class="border_b td2" style="width:27%;"></td>
					<td id="title3" class="border_b td2"style="width:23%;"></td>
					<td id="title4" class="border_b td2" style="width:27%;"></td>
				</tr>
				<tr>
					<td id="content1" class="border_r border_b td3"></td>
					<td id="content2" class="border_r border_b td3"></td>
					<td id="content3" class="border_r border_b td3"></td>
					<td id="content4" class="border_b td3"></td>
				</tr>
				<tr>
					<td colspan="4" class="td4"><marquee id="gdxx1" ></marquee></td>
				</tr>
				</table>
				
		</div>
	</body>
</html>