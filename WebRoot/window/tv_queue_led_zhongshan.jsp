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
		<script type="text/javascript" src="/queue/window/js/tv_queue_led_zhongshan.js"></script>
		<script type="text/javascript">
		var fontsize = window.opener.fontsize;
		var hallname = window.opener.hallname;var gdxx = window.opener.gdxx;var gdxx1 = window.opener.gdxx1;
		var title1 = window.opener.title1;var content1 = window.opener.content1;
		var title2 = window.opener.title2;var content2 = window.opener.content2;
		var title3 = window.opener.title3;var content3 = window.opener.content3;
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
			$("#tab2").width(width);$("#tab2").height(height);
			$("#tab3").width(width);$("#tab3").height(height);
			$("#tab4").width(width);$("#tab4").height(height);
			$("#tab5").width(width);$("#tab5").height(height);
			$("#tab6").width(width);$("#tab6").height(height);
			$("#tab7").width(width);$("#tab7").height(height);
			$("#tab8").width(width);$("#tab8").height(height);
			$("#tab9").width(width);$("#tab9").height(height);
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
//			 nowDate.innerHTML= ndate;
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
		li {list-style:none;}.ul_1 li{height:32px;line-height:32px;border-bottom: none;}
		<!--
		*{margin:0;padding:0;}html{height:100%;}
		body{color:#f00;text-shadow: 0 0 0 transparent, 0 0 0;font-size:10px;height:100%;background-color:#000;}
		.tab1{border:0px solid #f00;border-collapse:collapse;}
		.tab1 td{text-align:center;}.border_t{border-top:0px solid #f00;}
		.border_b{border-bottom:0px solid #f00;}.border_l{border-left:0px solid #f00;}
		.border_r{border-right:0px solid #f00;height:20px}.title{font-size:30px;height:10%;}
		.td1{height:6%;}.td2{height:6%;}.td3{vertical-align:top;height:36%;}.td4{height:41px;}
		.td5{height:36%;vertical-align:top;}
		.border_a{border-top:1px solid #f00;border-bottom:1px solid #f00;border-left:1px solid #f00;border-right:1px solid #f00;width: 89px;}
		-->
		</style>
	</head>
	<body scroll="no">
		<div class="inner tv" name="top_u1" id="top_u1" style="margin-left:0px;position:absolute;left:0;top:0;background:#000;">
			<table id="tab1" class="tab1">
				<tr>
					<td id="title1" colspan="1" class="border_b td1" style="width:14%;font-size:20px">1号窗口</td>
					<td id="title2" colspan="1" class="border_b td1" style="width:14%;font-size:20px">2号窗口</td>
					<td id="title3" colspan="1" class="border_b td1" style="width:14%;font-size:20px">19号窗口</td>
				</tr>
				<tr>
					<td id="content1" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
					
						<ul name="date_u1" id="date_u1">
						<li style="color:yellow;font-size:20px">疑难业务受理</li>
						</ul>
					</td>
					<td id="content2" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						<ul name="date_u2" id="date_u2">
						<li style="color:yellow;font-size:20px">互联网选号</li>
						</ul>
					</td>
					<td id="content3" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						<ul name="date_u19" id="date_u19">
						<li style="color:yellow;font-size:20px">车管档案业务窗</li>
						</ul>
					</td>
				</tr>
			</table>
			
			<table id="tab2" class="tab1">
				<tr>
					<td id="title1" colspan="1" class="border_b td1" style="width:14%;font-size:20px">3号窗口</td>
					<td id="title2" colspan="1" class="border_b td1" style="width:14%;font-size:20px">4号窗口</td>
					<td id="title3" colspan="1" class="border_b td1" style="width:14%;font-size:20px">20号窗口</td>
				</tr>
				<tr>
					<td id="content1" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						
						<ul name="date_u3" id="date_u3">
						<li style="color:yellow;font-size:20px">互联网注册</li>
						</ul>
					</td>
					<td id="content2" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						<ul name="date_u4" id="date_u4">
						<li style="color:yellow;font-size:20px">互联网注册</li>
						</ul>
					</td>
					<td id="content3" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						<ul name="date_u20" id="date_u20">
						<li style="color:yellow;font-size:20px">车管检验机构备案窗</li>
						</ul>
					</td>
				</tr>
			</table>
			
			<table id="tab3" class="tab1">
				<tr>
					<td id="title1" colspan="1" class="border_b td1" style="width:14%;font-size:20px">5号窗口</td>
					<td id="title2" colspan="1" class="border_b td1" style="width:14%;font-size:20px">6号窗口</td>
					<td id="title3" colspan="1" class="border_b td1" style="width:14%;font-size:20px">21号窗口</td>
				</tr>
				<tr>
					<td id="content1" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
					
						<ul name="date_u5" id="date_u5">
						<li style="color:yellow;font-size:20px">证件制作窗</li>
						</ul>
					</td>
					<td id="content2" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						<ul name="date_u6" id="date_u6">
						<li style="color:yellow;font-size:20px">证件制作窗</li>
						</ul>
					</td>
					<td id="content3" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						<ul name="date_u21" id="date_u21">
						<li style="color:yellow;font-size:20px">车管公检法查询窗</li>
						</ul>
					</td>
				</tr>
			</table>
			
			<table id="tab4" class="tab1">
				<tr>
					<td id="title1" colspan="1" class="border_b td1" style="width:14%;font-size:20px">7号窗口</td>
					<td id="title2" colspan="1" class="border_b td1" style="width:14%;font-size:20px">8号窗口</td>
					<td id="title3" colspan="1" class="border_b td1" style="width:14%;font-size:20px">22号窗口</td>
				</tr>
				<tr>
					<td id="content1" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						
						<ul name="date_u7" id="date_u7">
						<li style="color:yellow;font-size:20px">业务受理窗</li>
						</ul>
					</td>
					<td id="content2" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						<ul name="date_u8" id="date_u8">
						<li style="color:yellow;font-size:20px">业务受理窗</li>
						</ul>
					</td>
					<td id="content3" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						<ul name="date_u22" id="date_u22">
						<li style="color:yellow;font-size:20px">暂停服务</li>
						</ul>
					</td>
				</tr>
			</table>
			
			<table id="tab5" class="tab1">
				<tr>
					<td id="title1" colspan="1" class="border_b td1" style="width:14%;font-size:20px">9号窗口</td>
					<td id="title2" colspan="1" class="border_b td1" style="width:14%;font-size:20px">10号窗口</td>
					<td id="title3" colspan="1" class="border_b td1" style="width:14%;font-size:20px">23号窗口</td>
				</tr>
				<tr>
					<td id="content1" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						
						<ul name="date_u9" id="date_u9">
						<li style="color:yellow;font-size:20px">业务受理窗</li>
						</ul>
					</td>
					<td id="content2" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						<ul name="date_u10" id="date_u10">
						<li style="color:yellow;font-size:20px">业务受理窗</li>
						</ul>
					</td>
					<td id="content3" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						<ul name="date_u23" id="date_u23">
						<li style="color:yellow;font-size:20px">暂停服务</li>
						</ul>
					</td>
				</tr>
			</table>
			
			<table id="tab6" class="tab1">
				<tr>
					<td id="title1" colspan="1" class="border_b td1" style="width:14%;font-size:20px">11号窗口</td>
					<td id="title2" colspan="1" class="border_b td1" style="width:14%;font-size:20px">12号窗口</td>
					<td id="title3" colspan="1" class="border_b td1" style="width:14%;font-size:20px">24号窗口</td>
				</tr>
				<tr>
					<td id="content1" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						<ul name="date_u11" id="date_u11">
						<li style="color:yellow;font-size:20px">业务受理窗</li>
						</ul>
					</td>
					<td id="content2" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						<ul name="date_u12" id="date_u12">
						<li style="color:yellow;font-size:20px">绿色通道</li>
						</ul>
					</td>
					<td id="content3" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						<ul name="date_u24" id="date_u24">
						<li style="color:yellow;font-size:20px">暂停服务</li>
						</ul>
					</td>
				</tr>
			</table>
			
			<table id="tab7" class="tab1">
				<tr>
					<td id="title1" colspan="1" class="border_b td1" style="width:14%;font-size:20px">13号窗口</td>
					<td id="title2" colspan="1" class="border_b td1" style="width:14%;font-size:20px">14号窗口</td>
					<td id="title3" colspan="1" class="border_b td1" style="width:14%;font-size:20px">25号窗口</td>
				</tr>
				<tr>
					<td id="content1" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						
						<ul name="date_u13" id="date_u13">
						<li style="color:yellow;font-size:20px">业务受理窗</li>
						</ul>
					</td>
					<td id="content2" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						<ul name="date_u14" id="date_u14">
						<li style="color:yellow;font-size:20px">业务受理窗</li>
						</ul>
					</td>
					<td id="content3" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						<ul name="date_u25" id="date_u25">
						<li style="color:yellow;font-size:20px">暂停服务</li>
						</ul>
					</td>
				</tr>
			</table>
			
			<table id="tab8" class="tab1">
				<tr>
					<td id="title1" colspan="1" class="border_b td1" style="width:14%;font-size:20px">15号窗口</td>
					<td id="title2" colspan="1" class="border_b td1" style="width:14%;font-size:20px">16号窗口</td>
					<td id="title3" colspan="1" class="border_b td1" style="width:14%;font-size:20px">26号窗口</td>
				</tr>
				<tr>
					<td id="content1" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						
						<ul name="date_u15" id="date_u15">
						<li style="color:yellow;font-size:20px">发证窗</li>
						</ul>
					</td>
					<td id="content2" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						<ul name="date_u16" id="date_u16">
						<li style="color:yellow;font-size:20px">收费窗</li>
						</ul>
					</td>
					<td id="content3" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						<ul name="date_u26" id="date_u26">
						<li style="color:yellow;font-size:20px">暂停服务</li>
						</ul>
					</td>
				</tr>
			</table>
			
			<table id="tab9" class="tab1">
				<tr>
					<td id="title1" colspan="1" class="border_b td1" style="width:14%;font-size:20px">17号窗口</td>
					<td id="title2" colspan="1" class="border_b td1" style="width:14%;font-size:20px">18号窗口</td>
					<td id="title3" colspan="1" class="border_b td1" style="width:14%;font-size:20px">27号窗口</td>
				</tr>
				<tr>
					<td id="content1" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						
						<ul name="date_u17" id="date_u17">
						<li style="color:yellow;font-size:20px">暂停服务</li>
						</ul>
					</td>
					<td id="content2" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						<ul name="date_u18" id="date_u18">
						<li style="color:yellow;font-size:20px">暂停服务</li>
						</ul>
					</td>
					<td id="content3" rowspan="1" class="border_r td4" style="vertical-align:top;width: 14%;">
						<ul name="date_u27" id="date_u27">
						<li style="color:yellow;font-size:20px">暂停服务</li>
						</ul>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>