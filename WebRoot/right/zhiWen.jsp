<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page isELIgnored="false"%>
<html>
  <head>
    <title>指纹采集</title>
  </head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/queue/plugs/time/time.js" ></script> 
<SCRIPT ID=clientEventHandlersJS LANGUAGE=javascript>
<!--
function btnGetZW_onclick()
{
	alert(jsrzw.JsrZwInfo);
}
//-->
</SCRIPT>

<body>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">指纹采集</span>
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
		<object id=jsrzw classid="clsid:1CFF1F3A-1537-35A2-A2EA-EC20CFA0CFFB" CODEBASE="/queue/plugs/cab/JSRZWOCX.cab#version=1.0.0.0"></OBJECT>
		<INPUT id=btnGetZW type=button value="保存指纹信息" name=btnGetZW LANGUAGE=javascript onclick="return btnGetZW_onclick()">
	</td>
	</tr>
	</table>
	
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
