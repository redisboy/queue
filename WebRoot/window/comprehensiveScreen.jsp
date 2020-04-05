<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,com.suntendy.queue.advertise.domain.*"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>综合屏信息</title>
	<link rel="stylesheet" type="text/css" href="/queue/queue/css/fetch.css" />
	<style type="text/css">
	li {list-style:none;}
	.ul_1 li{
	height:95px;line-height:95px;
	border-bottom: none;
	}
	.tab3 td {vertical-align:top;}
	.div {
	position: absolute;
	left: 140px;
	top: 150px;
	text-align: left;
	color: #000;
	font-weight: bold;
	font-size: 25px;
	overflow: hidden;
	font-family:SimHei;
	}
	#dropcontentsubject{
	width: 248px;
	font-size:9pt;
	font-weight: bold;
	}
	.dropcontent{
	width: 248px;
	height: 98px;
	border: 1px solid black;
	background-color: skyblue;
	padding: 3px;
	display:block;
	}
	</style>
	<script type="text/javascript">
	var tickspeed=2000;
	var enablesubject=1;
	var selectedDiv=0
	var totalDivs=0
	function contractall(){
		var inc=0;
		while (document.getElementById("dropmsg"+inc)){
		document.getElementById("dropmsg"+inc).style.display="none";
		inc++;}
	}
	function expandone(){
		var selectedDivObj=document.getElementById("dropmsg"+selectedDiv);
		contractall();
		selectedDivObj.style.display="block";
		selectedDiv=(selectedDiv<totalDivs-1)? selectedDiv+1 : 0;
		setTimeout("expandone()",tickspeed);
	}
	function startscroller(){
		while (document.getElementById("dropmsg"+totalDivs)!=null)
		totalDivs++;
		expandone();
		if (!enablesubject)
		document.getElementById("dropcontentsubject").style.display="none";
	}
	</script>
	<script type="text/javascript" src="/queue/js/jquery.js"></script>
    <script type="text/javascript" src="/queue/dwr/util.js"></script>
	<script type="text/javascript" src="/queue/dwr/engine.js"></script>
	<script type="text/javascript" src="/queue/window/js/comprehensiveScreen.js"></script>
	</head>
	
	<body scroll="no">
		<div class="inner tv" style="background:#000;">
			<div id="dropcontentsubject"></div>
			<div class="div" id="contnetdiv" name="contnetdiv" style="position:absolute;left:150px;width:550px;text-align: center;">
				<div id="dropmsg0" style="color:#f00;"></div>
			</div>
		     <div style="position:absolute;left:750px;top:50px;width:500px;">
				<ul class="ul_1" id="top_ul" name="top_ul" style="left:0;top:0;color:#f00;"></ul>
			</div>
		</div>
	</body>
</html>