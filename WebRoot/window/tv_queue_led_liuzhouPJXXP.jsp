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
		<link rel="stylesheet" type="text/css" href="/queue/queue/css/fetch.css" />
		<script type="text/javascript" src="/queue/js/jquery.js"></script>
    	<script type="text/javascript" src="/queue/dwr/util.js"></script>
		<script type="text/javascript" src="/queue/dwr/engine.js"></script>
		<script type="text/javascript" src="/queue/dwr/interface/JspRegister.js"></script>
		<script type="text/javascript">JspRegister.register("<%=ip%>", "L");</script>
		<script type="text/javascript" src="/queue/window/js/tv_queue_led_liuzhouPJXXP.js"></script>
		<script type="text/javascript">
		var fontsize = window.opener.fontsize;
		var hallname = window.opener.hallname;var gdxx = window.opener.gdxx;var gdxx1 = window.opener.gdxx1;
		var title1 = window.opener.title1;var content1 = window.opener.content1;var title2 = window.opener.title2;
		var content2 = window.opener.content2;var title3 = window.opener.title3;var content3 = window.opener.content3;
		var title4 = window.opener.title4;var content4 = window.opener.content4;
		var width=window.opener.width;var height=window.opener.height;
		
	    
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
			//$("#tab1").width(width);$("#tab1").height(height);
			$("#top_u1").width(width);$("#top_u1").height(height);
			//$("#hallname").html(hallname);
			//$("#gdxx").html(gdxx);$("#gdxx1").html(gdxx1);
			//$("#title1").html(title1);
			//if("排队信息"==title1){$("#content1").html("<ul name='date_u1' id='date_u1'></ul>");}
			//else if("领证信息"==title1){$("#content1").html("<ul name='lzxx' id='lzxx'></ul>");}else{$("#content1").html(content1);}
			//$("#title2").html(title2);
			//if("排队信息"==title2){$("#content2").html("<ul name='date_u1' id='date_u1'></ul>");}
			//else if("领证信息"==title2){$("#content2").html("<ul name='lzxx' id='lzxx'></ul>");}else{$("#content2").html(content2);}
			//$("#title3").html(title3);
			//if("排队信息"==title3){$("#content3").html("<ul name='date_u1' id='date_u1'></ul>");}
			//else if("领证信息"==title3){$("#content3").html("<ul name='lzxx' id='lzxx'></ul>");}else{$("#content3").html(content3);}
			//$("#title4").html(title4);
			//if("排队信息"==title4){$("#content4").html("<ul name='date_u1' id='date_u1'></ul>");}
			//else if("领证信息"==title4){$("#content4").html("<ul name='lzxx' id='lzxx'></ul>");}else{$("#content4").html(content4);}
			//$("#nowDate").html(y+"年"+(m>9?m:("0"+m))+"月"+(d>9?d:("0"+d))+"日 "); 
			$("#content1").html(content3);
			$("#content2").html(content4);
		}
		</script>
		<!-- 拖动层 end -->
		<style type="text/css">
		li {list-style:none;}.ul_1 li{height:32px;line-height:32px;border-bottom: none;}
		<!--
		*{margin:0;padding:0;}html{height:100%;}
		body{color:#fff;text-shadow: 0 0 0 transparent, 0 0 0;font-size:10px;height:100%;background-color:#006;}
		.tab1{border:1px solid #fff;border-collapse:collapse;}
		.tab1 td{text-align:center;}.border_t{border-top:1px solid #fff;}
		.border_b{border-bottom:1px solid #fff;}.border_l{border-left:1px solid #fff;}
		.border_r{border-right:1px solid #fff;}.title{font-size:30px;height:10%;}
		.td1{height:6%;}.td2{height:6%;}.td3{vertical-align:top;height:36%;}.td4{height:6%;}
		.td5{height:36%;vertical-align:top;}
		-->
		</style>
	</head>
	<body scroll="no">
		<div class="inner tv" name="top_u1" id="top_u1" style="position:absolute;left:0;top:0;background:#006;">
			<div style="width:20%;height:100%;float:left;text-align:center;">
				<table style="height:98%;width:98%;border:1px solid white;border-collapse:collapse;">
					<tr>
						<td colspan	="3" style="font-size:19px;height:13%;border-right:1px solid white;border-bottom:1px solid white;">员工业务评价满意率</td>
					</tr>
					<tr>
						<td style="font-size:19px;width:39%;height:13%;border-right:1px solid white;border-bottom:1px solid white;">员工名称</td>
						<td style="font-size:19px;width:31%;border-right:1px solid white;border-bottom:1px solid white;border-left:1px solid white;">满意率</td>
						<td style="font-size:19px;width:30%;border-bottom:1px solid white;border-left:1px solid white;">评价量</td>
					</tr>
					<tr>
						<td  style="height:72%;border-top:1px solid white;vertical-align:top">
							<ul id="name">
								
							</ul>
						</td>
						<td  style="height:72%;border-top:1px solid white;vertical-align:top">
							<ul id="myl">
								
							</ul>
						</td>
						<td  style="height:72%;border-top:1px solid white;vertical-align:top">
							<ul id="jhl">
								
							</ul>
						</td>
					</tr>
				</table>
			</div>
			<div style="width:59%;height:100%;float:left;">
				<div style="height:30%;width:99%;border-left: 1px solid white;border-right: 1px solid white;border-top: 1px solid white;">
<!--					<marquee >-->
<!--					 	<div id="content1" ></div>					 	-->
<!--					</marquee>-->
					<div id="content1"></div>
				</div>
				<div style="height:68%;width:99%;border-left: 1px solid white;border-right: 1px solid white;border-bottom: 1px solid white;">
					<marquee scrolldelay="200" direction="up" height="98%">
					 	<div id="content2"></div>
					</marquee>
				</div>
			</div>
			<div style="width:20%;height:100%;float:left;">
				<div style="height:40%;width:98%;border-left:1px solid white;border-right:1px solid white;border-top:1px solid white;">
					<div style="line-height:15px;margin-top:10px"></div>
					<div style="float:left;font-size:18px;line-height:40px;color:#FC0;font-weight:bold;padding-top:10px">&nbsp;当前时间:</div>
					<dir style="clear:both;"></dir>
					<div style="text-align:	center;color:#FC0;font-weight:bold;font-size:22px;line-height:40px">
						<span id="year"></span>年
						<span id="mouth"></span>月
						<span id="day"></span>日
					</div>
					<div style="text-align:	center;color:#FC0;font-weight:bold;font-size:22px;line-height:40px">
						<span id="hour"></span>时
						<span id="minute"></span>分
					</div>
					<div id="xq" style="color:#FC0;font-weight:bold;font-size:22px;line-height:40px;text-align: center;"></div>
					
				</div>
				<div style="text-align:center;height:58%;width:98%;border-left: 1px solid white;border-right: 1px solid white;border-bottom: 1px solid white;">
					<div style="line-height:15px;margin-top:80px"></div>
					<div style="font-size:22px;line-height:30px;color:#FC0;font-weight:bold">全力打造</div>
					<div style="font-size:22px;line-height:30px;color:#FC0;font-weight:bold">人民满意车管所</div>
				</div>
			</div>
		</div>
	</body>
	<script language=JavaScript>
		var date = new Date(); 
		var xq = date.getDay();
    	var y = date.getFullYear();
    	var m = date.getMonth()+1;       
	    var d = date.getDate();       
	    var h = date.getHours();
	    var i = date.getMinutes();
	    
	    if(i<10){
	    	i="0"+i
	    }
	    var xqname;
	    $("#year").text(y);
	    $("#mouth").text(m);
	    $("#day").text(d);
	    $("#hour").text(h);
	    $("#minute").text(i);
		if(xq==0){
			$("#xq").text("星期日");
		}else if(xq==1){
			$("#xq").text("星期一");
		}else if(xq==2){
			$("#xq").text("星期二");
		}else if(xq==3){
			$("#xq").text("星期三");
		}else if(xq==4){
			$("#xq").text("星期四");
		}else if(xq==5){
			$("#xq").text("星期五");
		}else if(xq==6){
			$("#xq").text("星期六");
		}
		if(y==2015&&m==12&&d<=31&&h<24){
	    		$("#djsd").text(31-d);
	    		$("#djss").text(24-h);
	    }else{
	    	$("#djs:hidden");
	    }
	    
	    
		setInterval(function() {//设置倒计时 循环定时器
	    	var date = new Date(); 
	    	var y = date.getFullYear();
	    	var m = date.getMonth()+1;       
		    var d = date.getDate();       
		    var h = date.getHours();       
		    var i = date.getMinutes();
		    if(i<10){
	    		i="0"+i
    		}
		    $("#year").text(y);
		    $("#mouth").text(m);
		    $("#day").text(d);
		    $("#hour").text(h);
		    $("#minute").text(i);
		    
		    if(xq==0){
				$("#xq").text("星期日");
			}else if(xq==1){
				$("#xq").text("星期一");
			}else if(xq==2){
				$("#xq").text("星期二");
			}else if(xq==3){
				$("#xq").text("星期三");
			}else if(xq==4){
				$("#xq").text("星期四");
			}else if(xq==5){
				$("#xq").text("星期五");
			}else if(xq==6){
				$("#xq").text("星期六");
			}
		    
	    	if(y==2015&&m==12&&d<=31&&h<24){
	    		$("#djsd").text(31-d);
	    		$("#djss").text(24-h);
	    	}
	    
	    },60000)
	</script>
</html>