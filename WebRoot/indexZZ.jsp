
<%@page import="com.suntendy.queue.util.cache.CacheManager"%>
<%@page import="com.suntendy.queue.util.DateUtils"%>﻿<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>公安交通管理业务排队叫号系统</title>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="queue/css/fetchZZ.css" />
		<link rel="stylesheet" type="text/css" href="queue/css/alxckeyboard.css" />
		<style type="text/css">
			<!--
			.btn_sfzqh,.btn_fsfzqh{display:block;width:378px;height:72px;position:absolute;text-indent:-9999px;}
			.btn_sfzqh{left:320px;top:267px;background-image:url(/queue/images/queue/btn_sfzqh2_zz.jpg);}
			.btn_sfzqh:hover{background-image:url(/queue/images/queue/btn_sfzqh_h2_zz.jpg);}
			.btn_fsfzqh{left:320px;top:500px;background-image:url(/queue/images/queue/btn_fsfzqh_zz.jpg);}
			.btn_fsfzqh:hover{background-image:url(/queue/images/queue/btn_fsfzqh_h_zz.jpg);}
			.inner_sfz{left:270px;}
			.inner_fsfz{left:330px;top:600px;}
			.inner_ywsl{left:330px;top:600px;}
			a.back{display:block;background:url(/queue/images/queue/back.gif) no-repeat;text-indent:-9999px;line-height:75px;width:73px;cursor:hand;}
			a.back:hover{background-position:1px 1px;margin-left:2px;margin-top:2px;}
			a.back:active{background:url(/queue/images/queue/back_a.gif) no-repeat;}
			#timers{text-align:center;font-size:35px;font-weight:bold;letter-spacing:5px;margin-top:350px;}
			.time_js{color:#bf0202;font-size:45px;margin:0 10px;}
			#msg{text-align:center;font-size:35px;font-weight:bold;letter-spacing:5px;margin-top:120px;color:#c33;}
			-->
		</style>
	</head>
	<% String readID = CacheManager.getInstance().getSystemConfig("readID"); 
		String jsVer = new DateUtils().jsVer();
	%>
	<body window.onload() style="overflow:hidden">
		<!-- 办理人页面 -->
		<div id="indexBL" class="outer" style="margin-top:-19px;">
			<div class="inner qh">
				<a href="javascript:void(0);" class="btn_br" onfocus="this.blur()" onclick="gn.benren()">本人办理</a>
				<a href="javascript:void(0);" class="btn_dbr" onfocus="this.blur()" onclick="gn.daibanren()">代办人办理</a>
			</div>
		</div>
	    <!-- 身份证取号界面 -->
		<div id="index" class="outer" style="margin-top:-19px;display: none;">
			<div class="inner qh">
				<div id="msg"></div>
				<a href="javascript:void(0);" class="btn_sfzqh" onfocus="this.blur()" onclick="gn.card()">身份证取号</a>
				<a href="javascript:void(0);" class="btn_fsfzqh" onfocus="this.blur()" onclick="gn.noCard()">非身份证取号</a>
				<input type="hidden" id="sxh" name="sxh" >
				<input type="hidden" id="sxhid" name="sxhid" >
				<input type="hidden" id="zwbase64" name="zwbase64" >
				
				<!-- 自助机箭头提示 
				<div style="position:absolute;top:700px;right:500px;">
				<img src="images/queue/arrow.gif" height="480" />
				</div>
				 -->
				<div class="inner_fsfz" id="inner_fsfz" style="display: none">
					<p><select class="sel1" id="tIDType"></select></p>
					<p><input id="tIDNumber" class="input1" onkeyup="gn.clears(this)" onfocus="gn.hqjp()"/></p>
					<p>
					    <img src="images/queue/ok.jpg" style="border: 0px; cursor: pointer;" onclick="gn.btnOk()" />
					    &nbsp;&nbsp;
					    <img src="images/queue/cancel.jpg" style="border: 0px; cursor: pointer;" onclick="gn.btnCancel()" />
					</p>
				</div>
				<!-- <div class="inner_ywsl" id="inner_ywsl" style="display: none">
					<p style="font-size: 16px;">请输入办理业务的笔数</p>
					<p><input id="tNumber" class="input1" onkeyup="gn.clears(this)" onfocus="gn.srbs()"/></p>
					<p>
					    <img src="images/queue/ok.jpg" style="border: 0px; cursor: pointer;" onclick="gn.bnumOk()" />
					    &nbsp;&nbsp;
					    <img src="images/queue/cancel.jpg" style="border: 0px; cursor: pointer;" onclick="gn.btnCancel()" />
					</p>
				</div> -->
				<div class="shuoming" id="showWaitRs"><!-- 存放等待人数 -->
				</div>
				<div id="timers"></div>
			</div>
		</div>
		<!-- 业务笔数界面 -->
		<div id="indexYWBS" class="outer" style="margin-top:-19px;display: none;">
			<div class="inner qh">
				<div class="inner_fsfz" id="inner_ywsl" style="display: none">
					<p style="font-size: 32px;color: red;">请输入办理业务的笔数</p>
					<p><input id="tNumber" class="input1" onkeyup="gn.clears(this)" onfocus="gn.srbs()"/></p>
					<p>
					    <img src="images/queue/ok.jpg" style="border: 0px; cursor: pointer;" onclick="gn.bnumOk()" />
					    &nbsp;&nbsp;
					    <img src="images/queue/cancel.jpg" style="border: 0px; cursor: pointer;" onclick="gn.bnumCancel()" />
					</p>
				</div>
			</div>
		</div>
		
		<%if("0".equals(readID)){%>
			<!-- 引入读取身份证OCX 新款-->
		    <object classid="clsid:46E4B248-8A41-45C5-B896-738ED44C1587" id="SynCardOcx1" codeBase="plugs/cab/newReadCard/SynCardOcx.CAB#version=1,0,0,1" width="0" height="0" >
			</object>	
		<% }else if("1".equals(readID)){%>
			<!-- 引入读取身份证OCX 老款-->
			<object classid="clsid:4B3CB088-9A00-4D24-87AA-F65C58531039" id="SynCardOcx1" codebase="plugs/cab/SynCardOcx.CAB#version=1,0,0,1" width="0" height="0">
			</object>	
		<% }else{%>
			<script type="text/javascript">
				alert("未选择身份证读卡器类型,无法使用身份证读卡器");
			</script>
			
		<% } %>
		
		
		<!-- 引入指纹OCX -->
		<div id="zhiwenDiv" style="z-index:-1;position:absolute;left:600px;top:450px;display:none;">
			<OBJECT ID="zwctl" CLASSID="CLSID:EB358473-435E-4948-80A5-B6B69D4B8A16"   CODEBASE="plugs/cab/prjzwcapture.cab#version=1,0,0,0" ></OBJECT>
		</div>
		<object id="xhWebFingerCtrl" width="0" height="0" classid="clsid:BB16B5DF-898F-4E1D-AABA-B23DB9E456A8" CODEBASE="/queue/plugs/cab/JhFinger.cab#version=1,0,0,1"></OBJECT>
		<!-- 引入打印OCX 
		<object  classid="clsid:466DC624-0FA5-4195-B492-393782680CEF" id="PrintJhBt" codebase="plugs/cab/prjJhPrintBt.cab#version=1,0,0,1" width="0" height="0">
		</object>
		-->
		<OBJECT ID="jatoolsPrinter"	CLASSID="CLSID:B43D3361-D075-4BE2-87FE-057188254255" codebase="plugs/cab/jatoolsPrinter.cab#version=8,5,2549" width="0" height="0"  ></OBJECT>
		<!-- 业务类型界面 -->
		<div id="type" class="outer" style="display:none;margin-top:-19px;">
			<div id="tdiv" class="inner ywlx"></div>
		</div>
		<!-- 返回按钮 -->
        <div  id="backDiv" style="z-index:-1;position:absolute;left:876px;top:1120px;"></div>
		<!-- 帮助信息界面 -->
        <div  id="hdiv" style="display:none; width:300px; height:200px; position:absolute; left:1200px; top:350px; ">
        	<table id="tID1" border='1' style="background:#246ec3;">	
        	</table>
		</div>
        <!-- 帮助按钮 -->
        <div class="help" id="hb1" onclick="helpShow()">
		</div>	
	
		<div id="h1" style="display:none; width:180px; height:25px; position:absolute; left:1px; top:1px; border:1px solid #ffffff; background:#246ec3;" ></div>
	
	
	</body>
	<script type="text/javascript" src="js/jquery.js?ver=<%=jsVer%>"></script>
	<%if("0".equals(readID)){ %>
	<script type="text/javascript" src="queue/js/indexDan_ZZ.js?ver=<%=jsVer%>"></script>
	<%}else if("1".equals(readID)){ %>
	<script type="text/javascript" src="queue/js/indexDan_ZZ2.js?ver=<%=jsVer%>"></script>
	<%} %>
	<script type="text/javascript" src="queue/js/alxckeyboard.js?ver=<%=jsVer%>"></script>
	<script type="text/javascript" for="xhWebFingerCtrl" event="CaptureFingerEvent(lpBuffer,lpRst)"></script>
	<script LANGUAG="javascript"   for="zwctl"   event="EventGetCapInfo(sInfo,result)"> 
	//alert("返回采集结果(result=true采集到了数据，false未采集数据)");
		//alert(sInfo);
		//alert(result);
		if(result == true){
			$("#zwbase64").val(sInfo);
		}
	</script>
</html>