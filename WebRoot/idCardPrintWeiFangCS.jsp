<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style type="text/css">
<!--
*{
margin:0;
padding:0;
}
body{
background: #fff;
font-family: "方正黑体简体", "黑体", "宋体";
font-size:12px;
letter-spacing:1px;
}
.sfz{
margin:25px auto;
width:340px;
height:465px;
position:relative;
}
.div1,
.div2,
.div3,
.div4,
.div5,
.div6,
.div7,
.div8,
.div9,
.div10,
.photo{
position:absolute;
}
.div1{
left:68px;
top:33px;
}
.div2{
left:68px;
top:57px;
}
.div3{
left:140px;
top:57px;
}
.div4{
left:68px;
top:81px;
}
.div5{
left:116px;
top:81px;
width:20px;
text-align:center;
}
.div6{
left:148px;
top:81px;
width:20px;
text-align:center;
}
.div7{
left:68px;
top:105px;
width:135px;
line-height:18px;
}
.div8{
left:115px;
top:170px;
font-size:13px;
font-weight:bold;
letter-spacing:2px;
}
.div9{
left:135px;
top:400px;
}
.div10{
left:135px;
top:426px;
}
.photo{
left:216px;
top:28px;
}
-->
</style>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="/queue/js/jquery.js"></script>
<script type="text/javascript" src="/queue/js/ajax.js"></script>
<script type="text/javascript" src="jquery.PrintArea.js"></script>
<script language="javascript" src="js/LodopFuncs.js"></script>
<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0><embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed></object>
<object id="WebBrowser" classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height="0" width="0"></object>
<script type="text/javascript" defer="defer">
	function printID(){
		var now = $("#now");
		var myDate = new Date();
		now.html("本文件仅用于"+myDate.toLocaleString()+"办理车驾管业务");
		
//		var dybd = document.getElementById('udybd');
		var dybd = '<%=request.getParameter("udybd")%>';
//		document.all.WebBrowser.ExecWB(6,2);
		var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
////	LODOP=getLodop();  

		if (undefined != dybd && "" != dybd) {
			var datas = dybd.split("H");
//			document.write(datas[0]);
//			var datas=new Array("jdcsqb","jsrsqb1");
			for(var i=0;i<datas.length;i++){
				LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_表单一");
				LODOP.ADD_PRINT_HTM(88,200,350,600,document.getElementById(datas[i]).innerHTML);
		//		LODOP.PREVIEW();
		//		LODOP.PRINT();
		//		用序号获得打印机名，一般序号从0开始，-1特指默认打印机；
				if (LODOP.SET_PRINTER_INDEX("1")){
	//			LODOP.PREVIEW();
				LODOP.PRINT();
				}
			}
		}



//		LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_表单一");
//		LODOP.ADD_PRINT_HTM(88,200,350,600,document.getElementById("form1").innerHTML);
//		LODOP.PREVIEW();
//		LODOP.PRINT();
//		用序号获得打印机名，一般序号从0开始，-1特指默认打印机；
//		if (LODOP.SET_PRINTER_INDEX("1")){
//			LODOP.PREVIEW();
//			LODOP.PRINT();
//		}
//		LODOP.ADD_PRINT_HTM(88,200,350,600,document.getElementById("form2").innerHTML);
//		LODOP.PREVIEW();
//		LODOP.PRINT();
//		用序号获得打印机名，一般序号从0开始，-1特指默认打印机；
//		if (LODOP.SET_PRINTER_INDEX("1")){
//			LODOP.PREVIEW();
//			LODOP.PRINT();
//		}

		window.close();
	}
</script>
</head>
<body onload="printID()">
	<input type="hidden" id="udybd" value="<%=request.getParameter("udybd") %>" />
<form id="jdcsqb">
	<div id="idcard" class="sfz">
		<img src="images/bg_sfz.jpg" alt="" />
		<div id="nameA" class="div1"><%=URLDecoder.decode(request.getParameter("nameA"),"utf-8") %></div>
	</div>
	<div id="now" align="center" style="font-size: 18px"></div><br /><br /><br />
</form>

<form id="jsrsqb">
	<div id="idcard" class="sfz">
		<img src="images/bg_sfz.jpg" alt="" />
		<div id="nameA" class="div1"><%=URLDecoder.decode(request.getParameter("nameA"),"utf-8") %></div>
	</div>
	<div id="now" align="center" style="font-size: 18px"></div><br /><br /><br />
</form>
</body>
</html>