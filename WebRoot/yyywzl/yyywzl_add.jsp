<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"  %>

<html>
 <head>
    <title>新增预约业务所需资料</title>
  </head>
   <meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/queue/plugs/ecside/css/ecside_style.css" />
<script type="text/javascript" src="/queue/plugs/ecside/js/prototype_mini.js" ></script>	
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside.js" ></script>
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside_msg_utf8_cn.js" ></script>
<link rel="stylesheet" type="text/css" href="" />
<style type="text/css"></style>
<script type="text/javascript"></script>
<META NAME="Description" CONTENT="Power by hill"> 
<script type="text/javascript" src="/queue/js/jquery.js"></script>
<script type="text/javascript" src="/queue/js/ajax.js"></script>
 
<script language="JavaScript">
		function  addYyywzl(){
			 document.form1.action = "addYyywzl.action"; 
			document.form1.submit(); 
				
		}
		
		
	
</script>
<body>
<form action="" name="form1" method="post">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">新增预约业务所需资料</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    
    <table width="60%" border="0" cellpadding="1" cellspacing="1" class="table_back">
      <tr>
        <td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">资料名称：</td>
        <td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;
          <input name="zlmc" id="zlmc" maxlength="20" style="width:200px"    />
          &nbsp;<font  color="#ff0000"></font>
        </td>
      </tr>
		
		<tr>
			<td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">资料说明：</td>
		       <td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;
			
		<textarea rows="5" style="width: 300px;"  id="sm" name="sm"></textarea><br/>
		</td>
		</tr>


      
	   </table>
    <br>
      <br>
	  <input name="bt1" class="button" type="button" value=" 添加 " onClick="addYyywzl()"/>
	  <input name="bt2" class="button" type="button" value=" 返回 " onClick="window.history.back(-1);"/>
      <br>
      <br>
</table>
</table>
</body>  

</html>
