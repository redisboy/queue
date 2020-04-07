<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"%>
<%@ page isELIgnored="false"%>


<html>
 <head>
    <title>预约须知内容</title>
  </head>
  <meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<link rel="stylesheet" href="/queue/printSet/editor-min.css" type="text/css"></link>
<link href="/queue/printSet/print.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/queue/plugs/ecside/css/ecside_style.css" />
<script type="text/javascript" src="/queue/js/jquery.js"></script>
<script type="text/javascript" src="/queue/js/ajax.js"></script>
<!-- 1. 页头引入 editor css -->
<link rel="stylesheet" href="/queue/printSet/content-min.css" type="text/css"/>
<script type="text/javascript">
</script>
<body>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">预约须知内容</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    <br>
	<br>
	
 <form name="myform" action="addOrUpdateYyxz.action" method="post" enctype="multipart/form-data" >
      <table width="50%"  border="1" cellpadding="1" cellspacing="1" bordercolor="#037CD5" style="border-collapse:collapse;" align="center">
        <tr valign="top">    
          <td bgcolor="#F1F9FD" align="center">
				       <!-- 文本编辑器  begin-->
			<textarea align="center" name="code" id="demo" rows="50" cols="152" style="width: 350px; height: 300px">${yyxz.content }</textarea>
					<!-- 文本编辑器  end-->
          </td>
        </tr>  
      </table>
      <table bgcolor="#F1F9FD" width="96%" border="0" cellpadding="0" cellspacing="1" bordercolor="#037CD5" style="border-collapse:collapse;">
        <tr>
          <td colspan="1"><div align="center">
            <input name="button" type="submit"  style="width:80px" class="button"  value="修改" >
            <input type="button" name="fanhui" class="button" value="返回" onClick="window.history.back(-1);" />
          </div></td>
        </tr>
      </table>
      </form> 
<br></td>
    <td width="8" background="images/t-5.jpg">&nbsp;</td>
  </tr>
  <tr>
    <td ><img src="images/t-6.jpg" width="7" height="11"></td>
    <td height="11" background="images/t-7.jpg"></td>
    <td ><img src="images/t-8.jpg" width="8" height="11"></td>
  </tr>         
</table>   
<iframe name="selfframe" height="200" width="1000" style="display:none;"></iframe>  
<br>
<iframe name="paramIframe" style="DISPLAY: none;"></iframe>
<div id=qroomdivProcessing style="width:200px;height:30px;position:absolute;left:300px;top:200px;display:none">
</div>
<!-- 2. 页尾引入 editor js and init code -->
  <script type="text/javascript" src="/queue/printSet/editor-aio.js"></script>
<script type="text/javascript"  >KISSY.Editor("demo")</script>
</body>  
	<br>
    <td width="8" background="/queue/images/t-5.jpg">&nbsp;</td>
  <tr>
    <td ><img src="/queue/images/t-6.jpg" width="7" height="11"></td>
    <td height="11" background="/queue/images/t-7.jpg"></td>
    <td ><img src="/queue/images/t-8.jpg" width="8" height="11"></td>
  </tr>
</html>
