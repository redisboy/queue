<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html>
  <head>
    <title>小窗口按钮设置</title>
  </head>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.ul_1 li{
float:left;
width:80px;
text-align:center;
margin-top:10px;
}
.ul_1 li p{
margin:0;
padding;0;
text-align:center;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>


<script type="text/javascript">
function upd(){
	document.form1.action = "updateBtn.action";
	document.form1.submit();
}
//复选框
function check(){	
	var code=document.getElementsByName('code');
	for(var i=0;i<code.length;i++){
			if("0"==code[i].value){
				code[i].checked=true;
		}
	}	
}
function checkType(){
var code=document.getElementsByName("code");
var stroke=document.getElementsByName("stroke");
	var checked=false;
	for(var i=0;i<code.length;i++){	
			if(code[i].checked){
				stroke[i].value="0";
			}else{
				stroke[i].value="1";
			}	
		}
}
function checkAll(){
   var a = document.getElementsByTagName("input");
   var stroke=document.getElementsByName("stroke");
	for(var i = 0;i<a.length;i++){
		if(a[i].type == "checkbox"){
			a[i].checked = true;
			checkType();
		}
	}
}
function uncheckAll(){
   var a = document.getElementsByTagName("input");
   var stroke=document.getElementsByName("stroke");
	for(var i = 0;i<a.length;i++){
		if(a[i].type == "checkbox"){
			a[i].checked = false;
			checkType();
		}
	}
}
</script>
<body onload="check()">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">小窗口按钮设置</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    
	    <form action="" method="post" name="form1">
	    
	    	
	          <table width="80%" border="0" cellpadding="1" cellspacing="1" class="table_back">
	            <tr>
	              <td bgColor=#f1f9fd height=30>
	              	<ul class="ul_1">
	              	 <c:forEach var="smallList" items="${smallList }" >
	              		<li>
	              			<input name="smallid" id="${smallList.id }" type="hidden" value="${smallList.id }"/>
	              			<input name="btnName"  id="${smallList.id }" type="hidden" value="${smallList.btnName}"/>
	              			<input name="event"  id="${smallList.id }" type="hidden" value="${smallList.event}"/>
	              			<img name="${smallList.btnName }" src="../right/images/${smallList.btnName } "/>
	              			<p>
	              				<input name="code" id="${smallList.id }" type="checkbox" value="${smallList.status }" onclick="checkType()"/>
	              				<input name="stroke" id="${smallList.id }" type="hidden"/>
	              			</p>
	              		</li>
	              	 </c:forEach>
	              	</ul>                	
	              </td>
	        	</tr>
	        	<tr>
	        		<input type="button" name="select" onclick="javascript:checkAll()" value="全选"/>
	        		<input type="button" name="select" onclick="javascript:uncheckAll()" value="反选"/>
	        	</tr>
	          </table>
	          <br>
	          <input name="button" type="button" class="button" value="修改" onClick="upd()"/>
	          <br/>
	         
		</form>
	
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