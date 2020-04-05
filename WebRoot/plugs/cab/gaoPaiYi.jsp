<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page isELIgnored="false"%>
<html>
  <head>
    <title>高拍仪信息采集</title>
  </head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/queue/plugs/time/time.js" ></script>
<script type="text/javascript" src="/queue/js/ajax.js"></script>
<script type="text/javascript" src="/queue/js/jquery.js"></script>
<script type="text/javascript" src="/queue/dwr/util.js"></script>
<script type="text/javascript" src="/queue/dwr/engine.js"></script>
<OBJECT ID="vehPrinter" width="0" height="0" CLASSID="CLSID:A71282D8-FB78-4BBB-9114-3F4FE3E96F1D" ></OBJECT>
<OBJECT id="BASE64" name="BASE64" classid="clsid:11064666-12A8-40DC-88C8-A8E09FDF8F99" width="0" height="0"  CODEBASE="CaptureOCX.cab#version=3.6.11.312" align="middle"></OBJECT>

<script type="text/javascript">
function shot1(){
		jsrzP.bSaveJPG("D:\\","test333");
		if(jsrzP.bStopPlay()){
			if(jsrzP.bStartFaceVideo('1', 0)){
				document.getElementById("photo1").src="D:\\test333.jpg";
				//var  xs	=BASE64.JpgToBase64("D:\\test333.jpg");
				//alert(xs);
			}
		}
	}

function shot2(){
		jsrzP.bSaveJPG("D:\\","test444");
		if(jsrzP.bStopPlay()){
			if(jsrzP.bStartPlay2('2')){
				document.getElementById("photo2").src="D:\\test444.jpg";
				//var  xz	=BASE64.JpgToBase64("");
				//alert(xz);
			}
		}
	}


function shot3(){
		jsrzP.bSetIamgeXYZoom(0.3);
		jsrzP.bSaveJPG("D:\\","test555");
		document.getElementById("photo3").src="D:\\test555.jpg";
		//var  xx	=BASE64.JpgToBase64("D:\\test555.jpg");
				//alert(xx);
		
		if(jsrzP.bStopPlay()){
			jsrzP.bStartPlay2('2');
		
		}
		
	}
	function pz(){
		document.getElementById("photo1").src="/queue/images/nopic.gif";
		document.getElementById("photo2").src="/queue/images/nopic.gif";
		document.getElementById("photo3").src="/queue/images/nopic.gif";
		var str=jsrzP.bStopPlay();
  		var str = jsrzP.bStartPlay2('1');
	}
	function savePhoto(){
		var  photo1	=BASE64.JpgToBase64("D:\\test333.jpg");
		var  photo2	=BASE64.JpgToBase64("D:\\test444.jpg");
		var  photo3	=BASE64.JpgToBase64("D:\\test555.jpg");
		var params = '&photo1=' + encodeURIComponent(photo1) +'&photo2=' + encodeURIComponent(photo2)+'&photo3=' + encodeURIComponent(photo3)
		$.ajax({type:"POST", cache:false, data:params,
		url:"/queue/number/savaGpyPhoto.action",
		success:function (req) {
				if(0==req){
					alert("保存成功");
					document.getElementById("photo1").src="/queue/images/nopic.gif";
					document.getElementById("photo2").src="/queue/images/nopic.gif";
					document.getElementById("photo3").src="/queue/images/nopic.gif";
				}else{
					alert("保存失败");
				}
			}
		});
	}
</script>

<body onload="pz()">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
<form name="forms" methed="post" action="">
	<input name="shu1" id="shu1" type="hidden"> 
	<input name="shu2" id="shu2" type="hidden"> 
	<input name="shu3" id="shu3" type="hidden"> 
</form>
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">高拍仪信息采集</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    <div align="center">
			<table align="center" width="60%">
				<TR>
					<TD height="20" bgcolor="#F7F7F7" style="text-align:center; align:center">
						<div id="chart1div" align="center"></div>
					</TD>
				</TR>
			</table>
	</div>
	<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td>
		<object id="jsrzP"  width="190" height="140"  classid="clsid:454C18E2-8B7D-43C6-8C17-B1825B49D7DE"  align="center" CODEBASE="/queue/plugs/camera/zdywzp.CAB#version=1.0.0.3"></OBJECT>
		</td>
		<td>
			<img name="photo1" id="photo1" width="190" height="140">
		</td>
		<td>
			<img name="photo2" id="photo2" width="190" height="140">
		</td>
		<td>
			<img name="photo3" id="photo3" width="190" height="140">
		</td>
	</tr>
	</table>
	<input type="button" name="gpy" value="拍照1" onclick="shot1()">
	<input type="button" name="gpy" value="拍照2" onclick="shot2()">
	<input type="button" name="gpy" value="拍照3" onclick="shot3()">
	<br/>
	<input type="button" name="save" value="保存" onclick="savePhoto()">
    </td>
    <td width="8" background="/queue/images/t-5.jpg">&nbsp;</td>
  </tr>
  <tr>
    <td ><img src="/queue/images/t-6.jpg" width="7" height="11"></td>
    <td height="11" background="/queue/images/t-7.jpg"></td>
    <td ><img src="/queue/images/t-8.jpg" width="8" height="11"></td>
  </tr>
</table>
</body>
</html>
