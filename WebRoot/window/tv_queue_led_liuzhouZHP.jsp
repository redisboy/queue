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
		<title>电视窗口</title>
		<link rel="stylesheet" type="text/css" href="/queue/queue/css/fetchWeiFang.css" />
		<script type="text/javascript" src="/queue/js/jquery.js"></script>
    	<script type="text/javascript" src="/queue/dwr/util.js"></script>
		<script type="text/javascript" src="/queue/dwr/engine.js"></script>
		<script type="text/javascript" src="/queue/dwr/interface/JspRegister.js"></script>
		<script type="text/javascript">JspRegister.register("<%=ip%>", "L");</script>
		<script type="text/javascript" src="/queue/window/js/tv_queue_led_liuzhouZHP.js"></script>
		<script type="text/javascript">
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
			$("#tab1").width(width);$("#tab1").height(height);
			$("#hallname").html(hallname);
			$("#gdxx").html(gdxx);$("#gdxx1").html(gdxx1);
//			$("#title1").html(title1);
//			if("排队信息"==title1){$("#content1").html("<ul name='date_u1' id='date_u1'></ul>");}
//			else if("领证信息"==title1){$("#content1").html("<ul name='lzxx' id='lzxx'></ul>");}else{$("#content1").html(content1);}
//			$("#title2").html(title2);
//			if("排队信息"==title2){$("#content2").html("<ul name='date_u1' id='date_u1'></ul>");}
//			else if("领证信息"==title2){$("#content2").html("<ul name='lzxx' id='lzxx'></ul>");}else{$("#content2").html(content2);}
//			$("#title3").html(title3);
//			if("排队信息"==title3){$("#content3").html("<ul name='date_u1' id='date_u1'></ul>");}
//			else if("领证信息"==title3){$("#content3").html("<ul name='lzxx' id='lzxx'></ul>");}else{$("#content3").html(content3);}
//			$("#title4").html(title4);
//			if("排队信息"==title4){$("#content4").html("<ul name='date_u1' id='date_u1'></ul>");}
//			else if("领证信息"==title4){$("#content4").html("<ul name='lzxx' id='lzxx'></ul>");}else{$("#content4").html(content4);}
//			$("#nowDate").html(y+"年"+(m>9?m:("0"+m))+"月"+(d>9?d:("0"+d))+"日  "+h+":"+i+":"+s); 
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
			 var ndate = years+"年"+month+"月"+days+"日 "+hours+":"+minutes+":"+seconds+" "+week;
			 nowDate.innerHTML= ndate;
			}
		function add_zero(temp)
			{
			 if(temp<10) return "0"+temp;
			 else return temp;
			}
		setInterval("getCurDate()",100);
		
		
		</script>
		<!-- 拖动层 end -->
		<style type="text/css">
		li {list-style:none;float: left;}
		<!--
		*{margin:0;padding:0;}html{height:100%;}
		body{color:#f00;text-shadow: 0 0 0 transparent, 0 0 0;font-size:10px;height:100%;background-color:#000;}
		.tab1{border:1px solid #f00;border-collapse:collapse;}
		.border_t{border-top:1px solid #f00;}
		.border_b{border-bottom:1px solid #f00;}.border_l{border-left:1px solid #f00;}
		.border_r{border-right:1px solid #f00;}.title{font-size:30px;height:10%;}
		.td1{height:6%;}.td2{height:6%;}.td3{vertical-align:top;height:36%;}.td4{height:78%;}
		.td5{height:36%;vertical-align:top;}
		-->
		</style>
	</head>
	<body scroll="no">
		<div class="inner tv" name="top_u1" id="top_u1" style="margin-left:0px;position:absolute;left:0;top:0;background:#000;">
			<table id="tab1" class="tab1">
				<tr>
					<td id="content1" rowspan="1" class="border_r td4" style="vertical-align:top;width: 33%;">
						<ul name="date_u1" id="date_u1">
							<!-- <li style='height:30px;font-size: 31px;width: 33%;text-align:justify;text-justify:distribute-all-lines;text-align-last:justify'>往某某人&nbsp;&nbsp;&nbsp;&nbsp;请到12号窗口领证&nbsp;&nbsp;</li>
							<li style='height:30px;font-size: 31px;width: 33%;text-align:justify;text-justify:distribute-all-lines;text-align-last:justify'>AA&nbsp;&nbsp;&nbsp;请到12号窗口领证&nbsp;&nbsp;</li>
							<li style='height:30px;font-size: 31px;width: 33%;text-align:justify;text-justify:distribute-all-lines;text-align-last:justify'>11111请到12号窗口领证&nbsp;&nbsp;</li>
							<li style='height:30px;font-size: 31px;width: 33%;text-align:justify;text-justify:distribute-all-lines;text-align-last:justify'>11111请到12号窗口领证&nbsp;&nbsp;</li>
							<li style='height:30px;font-size: 31px;width: 33%;text-align:justify;text-justify:distribute-all-lines;text-align-last:justify'>11111请到12号窗口领证&nbsp;&nbsp;</li>
							<li style='height:30px;font-size: 31px;width: 33%;text-align:justify;text-justify:distribute-all-lines;text-align-last:justify'>11111号请到12号窗口领证&nbsp;&nbsp;</li>
							<li style='height:30px;font-size: 31px;width: 33%;text-align:justify;text-justify:distribute-all-lines;text-align-last:justify'>11111号请到12号窗口领证&nbsp;&nbsp;</li> -->
							
						</ul>
					</td>
				</tr>
			</table>
			
			
		</div>
	</body>
</html>