<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"%>
<%@ taglib uri="/webwork" prefix="ww"%>
<html>
  <head>
    <title>IE客户端控制
    </title>
  </head>
  <meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/queue/plugs/ecside/css/ecside_style.css" />
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside_msg_utf8_cn.js" ></script>
<script type="text/javascript" src="/queue/js/jquery.js"></script>
<script type="text/javascript" src="/queue/js/ajaxfileupload.js"></script>
<body>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">IE客户端控制</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
	<form action="" method="post" name="form1"  >
	
    <table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" >
	<tr>
	<td>
	传入参数：</br>
	<input  name="pare" id="pare" style="width:200">  </iput><br>
		<input name="bt1" class="button" type="button" value="执行" onClick="doo()" />
    </td>
  
	</table>
	</form>
	<br>
	<center>   
    <font color="red">格式是:0#196.128.1.133#0#196.128.1.151#0#196.128.1.193#<br/><br/>  
     关闭指定IP机子上已经打开的IE:      
    0#196.128.1.133#0#196.128.1.151#0#196.128.1.193#  <br/>
    打开指定IP机子上已经打开的IE:      
    1#196.128.1.133#1#196.128.1.151#1#196.128.1.193#    <br/> 
    </font>   
    </center> 
	<br>
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
<script type="text/javascript">

	function doo(){
	   var message = document.form1.pare.value;
       if(message.length ==0){
          alert("请填写信息！");
          return false;
       }
       document.form1.action = "ieListen.action";
	   document.form1.submit();         
	}
</script>
</html>