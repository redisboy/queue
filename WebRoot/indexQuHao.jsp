<%@page import="com.suntendy.queue.util.cache.CacheManager"%>
<%@page import="com.suntendy.queue.util.DateUtils"%>﻿<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>公安交通管理业务排队叫号系统</title>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="queue/css/fetch.css" />
		<style type="text/css">
			.tab_nav{
				position:absolute;
				left:676px;
				top:138px;
				}
				.tab_nav td{
					height:90px;
					width:103px;
				}
				a.back{
					display:block;
					background:url(/queue/images/queue/back.gif) no-repeat;
					text-indent:-9999px;
					line-height:75px;
					width:73px;
					cursor:hand;
				}
				a.back:hover{
					background-position:1px 1px;
					margin-left:2px;
					margin-top:2px;	
				}
				a.back:active{
				background:url(/queue/images/queue/back_a.gif) no-repeat;
				}
				#msg{text-align:center;font-size:35px;font-weight:bold;letter-spacing:5px;margin-top:120px;color:#c33;}
		</style>
	</head>
	<% String readID = CacheManager.getInstance().getSystemConfig("readID"); 
		String jsVer = new DateUtils().jsVer();
	%>
	<body window.onload()>
		<!-- 办理人页面 -->
		<div id="indexBL" class="outer">
			<div class="inner qh">
				<a href="javascript:void(0);" class="btn_br" onfocus="this.blur()" onclick="gn.benren()">本人办理</a>
				<a href="javascript:void(0);" class="btn_dbr" onfocus="this.blur()" onclick="gn.daibanren()">代办人办理</a>
			</div>
		</div>
	    <!-- 身份证取号界面 -->
		<div id="index" class="outer" style="display: none;">
			<div class="inner qh">
			<div id="msg"></div>
				<a href="javascript:void(0);" class="btn_sfzqh" onfocus="this.blur()" onclick="gn.card()">身份证取号</a>
				<a href="javascript:void(0);" class="btn_fsfzqh" onfocus="this.blur()" onclick="gn.noCard()">非身份证取号</a>
				<input type="hidden" id="sxh" name="sxh" >
				<input type="hidden" id="sxhid" name="sxhid" >
				<input type="hidden" id="zwbase64" name="zwbase64" >
				<div class="inner_fsfz" id="inner_fsfz" style="display: none">
					<p><select class="sel1" id="tIDType"></select></p>
					<p><input id="tIDNumber" class="input1" onkeyup="gn.clears(this)" /></p>
					<p>
					    <img src="images/queue/ok.jpg" style="border: 0px; cursor: pointer;" onclick="gn.btnOk()" />
					    &nbsp;&nbsp;
					    <img src="images/queue/cancel.jpg" style="border: 0px; cursor: pointer;" onclick="gn.btnCancel()" />
					</p>
				</div>
				<div class="shuoming" id="showWaitRs"><!-- 存放等待人数 -->
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
		<div id="type" class="outer" style="display:none">
			<div id="tdiv" class="inner ywlx"></div>
		</div>
		<!-- 返回按钮 -->
        <div  id="backDiv" style="z-index:-1;position:absolute;left:350px;top:500px;"></div>
		<!-- 帮助信息界面 -->
        <div  id="hdiv" style="display:none; width:520px; height:600px; position:absolute; left:410px; top:280px; ">
        	<table id="tID1" border='1' style="background:#246ec3; width:520px;">	
        	</table>
		</div>
        <div class="help" id="hb1" onclick="helpShow()">
		</div>	
	
		<div id="h1" style="display:none; width:180px; height:25px; position:absolute; border:3px solid #ffffff; background:#246ec3;" ></div>
	
	</body>
	<script type="text/javascript" src="js/jquery.js?ver=<%=jsVer%>"></script>
	<%if("0".equals(readID)){ %>
	<script type="text/javascript" src="queue/js/indexDanCS.js?ver=<%=jsVer%>"></script>
	<%}else if("1".equals(readID)){ %>
	<script type="text/javascript" src="queue/js/indexDanCS2.js?ver=<%=jsVer%>"></script>
	<%} %>
	<script type="text/javascript" for="xhWebFingerCtrl" event="CaptureFingerEvent(lpBuffer,lpRst)"></script>
	<script LANGUAG="javascript"   for="zwctl"   event="EventGetCapInfo(sInfo,result)"> 
	//alert("返回采集结果(result=true采集到了数据，false未采集数据)");
		//alert(sInfo);
		//alert(result);
		if(result == true){
			$("#zwbase64").val(sInfo);
		}
	</script> 
	<script type="text/javascript">
	window.onload=function(){
		var operator = '${user.name}';
		operatorSZ(operator);
	}
	</script>
</html>