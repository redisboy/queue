<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
  <html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"%>
<%@ taglib uri="/webwork" prefix="ww"%>
<%@ page isELIgnored="false"%> 
<html>
	<head>
		<title>图片详细</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<form action="" name="form1" method="post" enctype="multipart/form-data">
			<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
				<tr>
					<td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
				    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
				    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">图片详细</span>
				    </td>
				    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
				</tr>
				<tr>
					<td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
					<td height="100" style="text-align: center" valign="middle" bgcolor="#f7f7f7">
						<fieldset>
							<table width="50%" border="0" cellpadding="1" cellspacing="1" class="table_back">
								<tr>
									<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">身份证号</td>
									<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;${record.idnumber }</td>
								</tr>
								<tr>
									<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">评价时间</td>
									<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;${record.valuetime }</td>
								</tr>
								<tr>
									<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">取号时间</td>
									<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;${record.entertime }</td>
								</tr>
								<tr>
									<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">拍照时间</td>
									<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;${record.pztime }</td>
								</tr>
								<tr>
									<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">办理员工编号</td>
									<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;${record.code }</td>
								</tr>
								<tr>
									<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">办理员工姓名</td>
									<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;${record.name }</td>
								</tr>
								<tr>
									<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">流水号</td>
									<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;${record.lsh }</td>
								</tr>
								<tr>
									<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">摄像头图片</td>
									<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
										<img id="cameraimg" src="setEvaluation!getCameraImg.action?id=${record.id }" onerror="this.src='/queue/images/nopic.gif'" 
											 style="width: 120px;height: 150px;border: 2px solid #B1A080;" />
									</td>
								</tr>
								<tr>
									<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">签名图片</td>
									<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
										<img id="signatureimg" src="setEvaluation!getSignatureImg.action?id=${record.id }" onerror="this.src='/queue/images/nopic.gif'" 
											 style="width: 120px;height: 150px;border: 2px solid #B1A080;" />
									</td>
								</tr>
								<tr>
									<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">高拍仪</td>
									<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
										<img id="signatureimg" src="setEvaluation!getGpyImg.action?id=${record.id }" onerror="this.src='/queue/images/nopic.gif'" 
											 style="width: 120px;height: 150px;border: 2px solid #B1A080;" />
									</td>
								</tr>
							</table>
						</fieldset>
						<input name="bt2" class="button" type="button" value="返 回" onClick="window.history.back(-1);" />
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
		</form>
	</body>
</html>