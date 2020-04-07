<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="/webwork" prefix="ww"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>

<html>
  <head>
    <title>LED队列屏内容设置</title>
  </head>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="/queue/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
window.onload = function(){
        editor = CKEDITOR.replace('fcknr'); //参数‘content’是textarea元素的name属性值，而非id属性值
        hphm = CKEDITOR.replace('hphm');
    }

function setLed() { 
	editor.updateElement();
	hphm.updateElement();
 	document.form1.action = "/queue/number/sendLzxx_zph.action";
  	document.form1.submit();
}
</script>
<body>
<form action="" method="post" name="form1">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" >
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">LED队列屏内容设置</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    <div id ="typePic" style="position:absolute;left:400px;top:95px;"></div>
          <table width="55%" border="0" cellpadding="1" cellspacing="1" class="table_back">
			<tr>
				<td width="22%" height="35" bgcolor="#FFFFFF" class="tableheader1">车牌号：</td>
				<td width="78%" height="35" bgcolor="#F1F9FD">&nbsp;
				 <!-- <textarea name="title1content" id="title1content" rows="3" style="width:300px;" name="message">${led.title1content }</textarea> -->
				 <textarea id="hphm" rows="30" cols="50" name="hphm"></textarea>
				</td>
			</tr>
			<tr id="jngdnr" style="display:none">
				<td width="22%" height="35" bgcolor="#FFFFFF" class="tableheader1">滚动内容:</td>
				<td width="78%" height="35" bgcolor="#F1F9FD">&nbsp;
					<textarea id="fcknr" rows="30" cols="50" name="fcknr"></textarea>
				</td>
			</tr>
          </table>
          
          <br/>
          <input name="button" type="button" class="button" value="发送数据" onClick="setLed()"/>
          <br/>
         
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