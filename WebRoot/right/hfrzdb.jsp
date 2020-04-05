<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String clientIp = request.getHeader("x-forwarded-for");//客户端IP
	if (null == clientIp || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
		clientIp = request.getHeader("Proxy-Client-IP");
	}
	if (null == clientIp || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
		clientIp = request.getHeader("WL-Proxy-Client-IP");
	}
	if (null == clientIp || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
		clientIp = request.getRemoteAddr();
	}
	
    String serverIP = "26.50.13.22:8080"; //服务端IP 192.168.1.105:8088 10.118.243.58:8080 10.119.20.103:8080
    String serverDwrPath = "http://" + serverIP + "/queue/dwr";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>恢复叫号人证对比页面</title>
    
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<object classid="clsid:4B3CB088-9A00-4D24-87AA-F65C58531039" id="SynCardOcx1" codebase="plugs/cab/SynCardOcx.CAB#version=1,0,0,1" width="0" height="0"></object>
<link href="css/mainframe.css" rel="stylesheet" type="text/css" />
<style type="text/css">
*{
	margin:0;
	padding:0;
}
body,td,th {
	font-family: 宋体;
	font-size: 9pt;
}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
a:link,
a:visited{
	color:#fff;
	text-decoration:none;
	font-weight:bold;
}
a:hover{
	color:#FF0;
	font-weight:bold;
}
#tab1{
background:url(images/top_bg.jpg);
height:99px;	
width:100%;
}
#tab1-tr{
background:url(images/top-r.jpg)  no-repeat right;	
}
#tab1-td{
	text-align:left;
	vertical-align:bottom;
}
.td_1{
	background-color:#f1f9fd;;
	text-align:center;
}

.table1 td {
    border: 1px solid #4482d3;
    height: 250px;
}
</style>
</head>

  		<script type="text/javascript">  
       
	function initOCX(){
		var csh= ScanCtrl.InitFaceSDK(); //初始化
		var open = ScanCtrl.OpenFaceCamera(1,0,0); //打开摄像头
		alert(csh);
		alert(open);
		document.getElementById("csh").value=csh;
		document.getElementById("open").value=open;
	}
     	
     function comparePic(){
          	
       	alert("开始");
	//	var result = CmFaceSDKOCX2.GetIDCardInfoRFID("D:\\headpath.bmp");
		var sxh = $("#sxh").val();
		if(sxh ==''){alert("顺序号不能为空!");return;}
		var csh = document.getElementById("csh").value;
		var open = 	document.getElementById("open").value;
		var _serverIP = $("#serverIP").val();
		var params = 'clientIp=' + $("#clientIp").val()+'&sxh='+sxh;
		alert("csh值="+csh);
		alert("open值="+open);
		alert(_serverIP);
	    alert(sxh);
		if(csh == 0 && open == 0){
			$.ajax({type: "POST", cache: false,dataType: "json",async: false,data: params,
			url:"http://" + _serverIP + "/queue/number/rzdbHFYZ.action",
			success:function (datas) {
				alert($("#dbckz").val());
				document.getElementById("dbckz").value=datas.dbckz;
				alert($("#dbckz").val());
				if(0 == datas.yzjh){
					var str;
					  	str = SynCardOcx1.FindReader();
					  	if (str > 0)
					  	{
					  		if(str>1000)
					  		{
					  			alert("读卡器连接在USB"+str);
					  		}else{
					  			alert("读卡器连接在COM"+str);
					  		}
					  	}else{
					  		alert("没有找到读卡器");
					  	}
		
					  	var nRet;
					  	SynCardOcx1.SetReadType(0);
					  	nRet = SynCardOcx1.ReadCardMsg();
					  	if(nRet==0){SynCardOcx1.PhotoName;}
					var img = document.getElementById("photo");
		           	img.src = "C:\\tmp.bmp";
					ScanCtrl.SetHeadByFile("C:\\tmp.bmp");
		//          ScanCtrl.SetHeadByFile(url);
		           	var score = ScanCtrl.MatchFace("C:\\3.jpg");
		           	alert(score);
		           	
		           	if(score>= $("#dbckz").val() && score <= 100){
					//保存人证对比结果
					var param = 'clientIp=' + $("#clientIp").val() +'&Score=' +score+'&sxh=' +sxh;
					$.ajax({type: "POST", cache: false,dataType: "json",async: false,data: param,
							url:"http://" + _serverIP + "/queue/number/rzdbhfz.action",
							success:function (datas) {
								if(0 == datas){
									alert("匹配成功");
//									document.getElementById("ifs").value="匹配成功";
//									document.getElementById("mathscore").value=Score;
								}else if(1 == datas){
									alert("数据保存错误,请联系管理员!");
									return;
								}else if(2 == datas){
									alert("未找到要保存的对应顺序号!");
									return;
								}
							}
						});
					}else if(score == '10001'){
						alert("视频没有找到人像或者活体人像");
					}else if(score == '10003'){
						alert("没有找到人脸");
					}else{
						alert("匹配失败");
//					document.getElementById("ifs").value="匹配失败";
//					document.getElementById("mathscore").value=Score;
					}
				}else if(1 == datas.yzjh){
					alert("要恢复的号码不存在,<br/>不能进行人证对比!");
					return;
				}else{
					alert("发生异常,请联系管理员.")
				}
				}
			});
			}else{
			alert("失败-1");
			ScanCtrl.UninitFaceSDK();
			ScanCtrl.CloseCamera();
			initOCX();
		}
          	
    }
            
//            function loadSelectImg(){
            	
 //           	var url = "file://" + document.getElementById("imgOne").value;
 //           	var img = document.getElementById("photo");
 //           	img.src = url;
 //           }

function uninitOCX(){
	ScanCtrl.UninitFaceSDK();
}
            
        </script>


<body onunload="uninitOCX();">
<table id="tab1"  style="border-bottom:1px solid #7ABBDF;">
  <tr>
    <td id="tab1-td"><img src="images/top.jpg" width="1024" height="99" /></td>
  </tr>
</table>
<table class="waikuang">
  <tr>
    <td class="lt"></td>
    <td class="ct">
		<table class="table_title">
		  <tr>
			<td class="title_td">
				 <div class="title_div"><span class="title_span">人像比对</span></div>
			</td>
		  </tr>
		</table>
	</td>
    <td class="rt"></td>
  </tr>
  <tr>
    <td class="lc "></td>
    <td class="cc">
       <table width="35%" class="table_back table1">
        <tr>
          	<td height="250" class="tabletd1" align="center">
				<OBJECT id="ScanCtrl" style="MARGIN-LEFT:5px; WIDTH: 400px; HEIGHT:300px;background-color: black;" classid="clsid:6EB9FF10-764E-4BA4-BA70-F9D11F4C8E22" ></OBJECT>
			</td>
         </tr>
      	</table>
      	<input name="打开视频" type="button" class="button" value="打开视频"  style="margin-bottom:30px;" onclick="initOCX()"/>
      	<input type="hidden" id="clientIp" name="clientIp" value="<%=clientIp %>" />
		<input type="hidden" id="serverIP" name="serverIP" value="<%=serverIP %>" />
		<input type="hidden" id="dbckz" name="dbckz" value="40"/>
		<input type="hidden" id="csh"  name="csh"/>
        <input type="hidden" id="open"  name="open"/>
      <br/>
      <table width="35%" class="table_back table1">
        <tr>
          <td height="250" class="tabletd1" align="center">
          	<img id="photo" src="" width="300px" height="300px" />
          </td>
        </tr>
      </table>
      <h3>人像比对</h3>
      <table class="table_back" style="width:35%;">
        <tr>
          <td width="50%" class="tableheader1">恢复的顺序号</td>
          <td width="3" class="tabletd1">
          	<input name="sxh" type="text" class="inputface" style="width: 150px;" id="sxh" value=""/>
          </td>
        </tr>
      </table>
      <input name="匹配" type="button" class="button" value="匹配" onclick="comparePic()"/>
      <br />
    <p>&nbsp;</p></td>
    <td class="rc"></td>
  </tr>
  <tr>
    <td class="lb"></td>
    <td class="cb"></td>
    <td class="rb"></td>
  </tr>
</table>
</body>
	<script src="js/changecolor.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="<%=serverDwrPath%>/util.js"></script>
	<script type="text/javascript" src="<%=serverDwrPath%>/engine.js" id="ldEngine"></script>
	<script type="text/javascript" src="<%=serverDwrPath%>/interface/JspRegister.js"></script>
</html>
