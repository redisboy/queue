<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"%>
<%@ taglib uri="/webwork" prefix="ww"%>
<html>
	<head>
		<title>电视窗口设置</title>
	</head>
	<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="/queue/js/jquery.js"></script>
	<script type="text/javascript" src="/queue/js/ajaxfileupload.js"></script>
	<body>
		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr><td height="5" colspan="3"></td></tr>
			<tr>
				<td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
				<td height="51" valign="middle" background="/queue/images/t-2.jpg">
					<span style="font-family: '黑体', '宋体'; font-size: 18px; color: #fff; padding-left: 15px; letter-spacing: 10px; letter-spacing: 10px;">电视窗口设置</span>
				</td>
				<td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
			</tr>
			<tr>
				<td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
				<td height="100" style="text-align: center" valign="middle" bgcolor="#f7f7f7">
					<form action="" method="post" name="form1" enctype="multipart/form-data">
						<input type="hidden" name="id" value="${id}" />
						<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									视频上传<br/>
									<input type="file" id="file1" name="file1" onchange="checkView()" /><br/>
									<input name="bt1" class="button" type="button" value="上 传" onClick="upload()" />
									<input name="bt2" class="button" type="button" value="返 回" onClick="window.history.back(-1);" />
								</td>
								<td>
									电视广告<br/>
									<textarea rows="5" style="width: 300px;" name="message">${message}</textarea><br/>
									<input name="bt1" class="button" type="button" value="${id > 0 ? '修改' : '添加'}" onClick="update()" />
								</td>
							</tr>
						</table>
					</form>
					<br>
					<center>
						<font color="red">
							限制上传视频文件在100m以内，否则上传失败!<br />
							上传的视频文件为wmv,3gp,mp4,avi,mov,flv格式，此文件格式在保证画质的前提下适于网络带宽的传输！
						</font>
					</center>
					<br>
				</td>
				<td width="8" background="/queue/images/t-5.jpg">&nbsp;</td>
			</tr>
			<tr>
				<td><img src="/queue/images/t-6.jpg" width="7" height="11"></td>
				<td height="11" background="/queue/images/t-7.jpg"></td>
				<td><img src="/queue/images/t-8.jpg" width="8" height="11"></td>
			</tr>
		</table>
	</body>
	<script type="text/javascript">
	function checkView(){
		var PName = $('#file1').val();
		if(PName != ""){
			var PNameFmt = PName.substring(PName.lastIndexOf("."));
			if(PNameFmt!=".rmvb" && PNameFmt!=".avi" && PNameFmt!=".mp4" && PNameFmt!=".mov" && PNameFmt!=".flv" 
				&& PNameFmt!=".3gp" && PNameFmt!=".WMV" ) {
				alert("视频格式不正确！");
				document.getElementById('form1').file1.value="";
				return false;
			}      
		}
	}
	
	function upload(){
		var url=$('#file1').val();
		if(url==null || url==""){
	    	alert("请选择视频文件");
		    return false;
		}
	    var pos = url.lastIndexOf("/");
	    if(pos == -1){pos = url.lastIndexOf("\\");}
	    var filename = url.substr(pos +1);
		form1.action = "upload.action?filename="+filename;
		form1.submit();
	}
	
	function update(){
	    var message = document.form1.message.value;
        message=message.replace(/^\s*|\s*$/g,'');
	    if(message.length == 0){
			alert("广告内容不能为空!");
			return false;
		}
	    document.form1.action = "update.action";
		document.form1.submit();         
	}
</script>
</html>