<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,com.suntendy.queue.advertise.domain.*"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>队列信息</title>
	<link rel="stylesheet" type="text/css" href="/queue/queue/css/fetch.css" />
	<style type="text/css">
	li {list-style:none;}
	.ul_1 li{
	height:110px;line-height:110px;
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
	</style>
	<script type="text/javascript" src="/queue/js/jquery.js"></script>
    <script type="text/javascript" src="/queue/dwr/util.js"></script>
	<script type="text/javascript" src="/queue/dwr/engine.js"></script>
	<script type="text/javascript" src="/queue/window/js/tvScreen.js"></script>
	</head>
	
	<body scroll="no">
	<div class="inner tv" style="background:#000;">
			<div style="position:absolute;top:50px;width:100%;">
				<ul class="ul_1" id="top_ul" name="top_ul" style="left:0;top:0;color:#f00;">
				</ul>
			</div>
		</div>
	</body>
</html>