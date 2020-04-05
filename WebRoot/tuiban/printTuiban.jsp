<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>退办单</title>
<style type="text/css">
<!--
.style1 {
	font-size: 36px;
	font-weight: bold;
}
.style2 {
	font-size: 18px;
	font-weight: bold;
}
.style3 {
	font-size: 20px;
}
-->
</style>
</head>
<style media=print>
    	.Noprint{display:none;}
   		.PageNext{page-break-after: always;}
  		</style>
  		
<body>
<p align="center" class="style1">&nbsp;</p>
<p>&nbsp;</p>
<form action="" method="post" enctype="multipart/form-data" name="form1">
  <div align="center">
    <p class="style1">退办单 </p>
  </div>
  <table width="580" height="100" border="1" align="center" cellpadding="1" cellspacing="0">
    <tr>
      <td width="282" height="35"><div align="center" class="style2">证件号码</div></td>
      <td width="282"><div align="center"><span class="style2">退办原因</span></div></td>
    </tr>
    <tr>
      <td>
      <center>
      	<input type="text" name="idnumber" id="idnumber" class="style3" style="border: none;text-align: center"
      	  readonly="readonly" value="${tuiban.idnumber}">
     </center>
      </td>
      <td>
      <center><textarea name="reason" id="reason" rows="5"  class="style3" style="border: none;text-align: center;;overflow-y: hidden" 
      	readonly="readonly">${tuiban.yuanyin}</textarea>
      </center>
      </td>
    </tr>
  </table>
</form>
	<center class="Noprint" >
	 <hr align="center" width="90%" size="1" noshade>
	    <p> 
	        <OBJECT  id="WebBrowser"  classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2  height=0  width=0>
	        </OBJECT>        
	           <input  type="button"  value="打印"  style="width:58px; height:22px"   onclick=document.getElementById("WebBrowser").ExecWB(6,2)>&nbsp;&nbsp;
	           <input name="bt2" class="button" type="button" value=" 返回 " onClick="window.history.back(-1);" />
	    </p>
	</center>
</body>
</html>
