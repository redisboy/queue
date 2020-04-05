<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"%>
<html>
  <head>
    <title>短信内容设置</title>
  </head>
  <meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<link rel="stylesheet" href="/queue/messageSet/editor-min.css" type="text/css"></link>
<link href="/queue/messageSet/message.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/queue/plugs/ecside/css/ecside_style.css" />
<!-- 1. 页头引入 editor css -->
<link rel="stylesheet" href="/queue/messageSet/content-min.css" type="text/css"/>
<script type="text/javascript">
 function domessage(how) {
         	var myDoc = {
         		documents: document,
         		copyrights: '杰创软件拥有版权  www.jatools.com'    
         	};
         	if(how == '打印预览...')
           			jatoolsmessageer.messagePreview(myDoc );   // 打印预览
          	else if(how == '打印...')
          	        jatoolsmessageer.message(myDoc ,true);   // 打印前弹出打印设置对话框
            else 
          	        jatoolsmessageer.message(myDoc ,false);       // 不弹出对话框打印
         }
</script>
<body>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">短信内容设置</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    <br>
	<br>
	
 <form name="myform" action="updateMessage.action" method="post" enctype="multipart/form-data" >
 <input type="hidden" name="id" value="${messageVo.id}">
      <table width="96%"  border="1" cellpadding="1" cellspacing="1" bordercolor="#037CD5" style="border-collapse:collapse;" align="center">
        <tr valign="top">    
          <td bgcolor="#F1F9FD">
	          <TABLE border="0" cellpadding="2" cellspacing="1" width="100%" bgcolor="#F1F9FD" >
	            <tr>
				    <td>
				       <div id="demo-page">
				       <!-- 文本编辑器  begin-->
				  	 <TABLE border="0" cellpadding="2" cellspacing="1" width="100%">
					<div id="page1"><textarea name="code" id="demo" rows="50" cols="152" style="width: 350px; height: 300px">${messageVo.context }</textarea></div>
					</TABLE>
					<!-- 文本编辑器  end--></div>
				    </td>
				  	<td>
				  		<table>   
				             <tr >
				             	<td>
				             		<div id="page1">
										 <font color="#ff0000" size="5" >格式如下：</font> <br/>
										 <font size="3"> 您所办理的业务A还需等待大约1.5-2分钟，请提前到达等候区</font>  
								    </div>
								 </td>
				            </tr>
				         </table>
	          </TABLE>
          </td>
        </tr>  
      </table>
      <table bgcolor="#F1F9FD" width="96%" border="0" cellpadding="0" cellspacing="1" bordercolor="#037CD5" style="border-collapse:collapse;">
        <tr>
          <td colspan="2"><div align="center">
            <input name="tijiao" type="submit" style="width:80px" class="button" value="确 定" >
            <input type="button" name="fanhui" class="button" value="返回" onClick="window.history.back()" />
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
  <script type="text/javascript" src="/queue/messageSet/editor-aio.js"></script>
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