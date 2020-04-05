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

<Script language="javascript">
	function Capture()
	{
		CmCaptureOcx.SetFileType(1);
		var strFile = "d:\\test333.jpg";
		//alert(strFile);
		CmCaptureOcx.CaptureImage(strFile);
		document.getElementById("photo1").src=strFile;
	}
	function StartVideo()
	{
		var index = CmCaptureOcx.Initial();
		//alert(index);
		if(index == -2)
		{
			alert("没有设备");
		}
		else if(index == -1)
		{
			alert("设备没有授权");
			index = 0;
		}
		CmCaptureOcx.StartRun(index);
		CmCaptureOcx.SetJpgQuanlity(100);
	}

	function Adjust()
	{
		CmCaptureOcx.AutoCrop(1);
	}
	
	function CropOne()
	{
		CmCaptureOcx.SetCropMode(1);
	}
	function CropMulti()
	{
	      CmCaptureOcx.SetCropMode(0);
	}

</script>
<script type="text/javascript">
	window.onload = function() {
		var index = CmCaptureOcx.Initial();
		//alert(index);
		if(index == -2)
		{
			alert("没有设备");
		}
		else if(index == -1)
		{
			alert("设备没有授权");
			index = 0;
		}
		CmCaptureOcx.StartRun(index);
		CmCaptureOcx.SetJpgQuanlity(100);
		CmCaptureOcx.SetResolution(2);
	}
	function savePhoto(){
		var  photo64 = CmCaptureOcx.Base64Encode("D:\\test333.jpg");
		var params = 'photo1=' + encodeURIComponent(photo64);
		$.ajax({type:"POST", cache:false, data:params,
		url:"/queue/number/savaGpyPhoto.action",
		success:function (req) {
				if(0==req){
					alert("保存成功");
					document.getElementById("photo1").src="/queue/images/nopic.gif";
					 CmCaptureOcx.DeleteFile("D:\\test333.jpg");
				}else{
					alert("保存失败");
				}
			}
		});
	}
</script>
<SCRIPT type="text/javascript" for="CmCaptureOcx" event="GetImageFileName(fileName);">
	CmCaptureOcx.AddPDFImageFile(fileName);
</SCRIPT>


<body>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
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
		<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" >
		<tr>
			<td>
				<OBJECT id='CmCaptureOcx' style='MARGIN-LEFT:5px; WIDTH: 192px; HEIGHT:141px' classid='clsid:3CA842C5-9B56-4329-A7CA-35CA77C7128D' ></OBJECT>
			</td>
			<td>
				<img name="photo1" id="photo1" width="190" height="140" src="/queue/images/nopic.gif">
			</td>
		</tr>
		</table>
		<input type = "button" value = " 拍照 " onClick = "Capture();" name = "Capture">&nbsp;&nbsp;&nbsp;
		<input type="button" name="save" value=" 保存 " onclick="savePhoto()">
	<br/>
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
