<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"  %>

<html>
 <head>
    <title>添加二级业务类型</title>
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

function  addTwoywlx(){
	var yysl=$("#yysl").val();
	var dtId=$("#dtId").val();
	 document.form1.action = "addTwoywlx.action?yysl="+yysl+"&dtId="+dtId; 
	document.form1.submit();   
	
}






function moveOption(e1, e2){ 
try{ 
for(var i=0;i<e1.options.length;i++){ 
if(e1.options[i].selected){ 
var e = e1.options[i]; 
e2.options.add(new Option(e.text, e.value)); 
e1.remove(i); 
ii=i-1 
} 
} 
document.getElementById('sxjbzlTest').value=getvalue(document.getElementById('list2')); 
document.getElementById('dtId').value=getvalue(document.getElementById('list4')); 
} 
catch(e){} 
} 
function getvalue(geto){ 
var allvalue = ""; 
for(var i=0;i<geto.options.length;i++){ 
allvalue +=geto.options[i].value + ","; 
} 
return allvalue; 
} 
function changepos(obj,index) { 
if(index==-1){ 
if (obj.selectedIndex>0){ 
obj.options(obj.selectedIndex).swapNode(obj.options(obj.selectedIndex-1)) 
} 
} else if(index==1){ 
if (obj.selectedIndex<obj.options.length-1){ 
obj.options(obj.selectedIndex).swapNode(obj.options(obj.selectedIndex+1)) 
} 
} 
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
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">添加二级业务类型</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    
    <table width="69%" border="0" cellpadding="1" cellspacing="1" class="table_back">
    	
    	<tr>
		<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
			一级业务类型名称：
		</td>
		<td width="80%" height="35" bgcolor="#F1F9FD">
			&nbsp;
			<select id="oneywlxName" name="oneywlxName" style="width: 180px;" >
				<c:forEach var="list" items="${list }" >
					<option value="${list.id}">${list.name }</option> 
			   </c:forEach>
			</select>
			
		</td>
		</tr>
    	
      <tr>
        <td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">二级业务类型名称：</td>
        <td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;
          <input name="name" id="name" maxlength="20" style="width:200px"    />
          &nbsp;<font  color="#ff0000"></font>
        </td>
      </tr>

		<tr>
		<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
			 所需基本资料：
		</td>
		<td width="80%" height="35" bgcolor="#F1F9FD">
		<form method="post" name="myform"> 
			<table border="0" width="300"> 
			<tr> 
			<td width="40%"> 
			<select style="WIDTH:100%" multiple id="list1" name="list1" size="12" ondblclick="moveOption(document.getElementById('list1'), document.getElementById('list2'))"> 
			 	<c:forEach var="yyywzlList" items="${yyywzlList }" >
					<option value="${yyywzlList.id }">${yyywzlList.zlmc }</option> 
			   </c:forEach>
			</select> 
			</td> 
			<td width="20%" align="center"> 
			<input type="button" value="添加" onclick="moveOption(document.getElementById('list1'), document.getElementById('list2'))">
			<br/> 
			<br/> 
			<input type="button" value="删除" onclick="moveOption(document.getElementById('list2'),document.getElementById('list1'))"> 
			</td> 
			<td width="40%"> 
			<select style="WIDTH:100%" multiple name="list2" size="12" ondblclick="moveOption(document.getElementById('list2'),document.getElementById('list1'))"> 
			</select> 
			</td> 
			</tr> 
			</table> 
		<input type="hidden" id="sxjbzlTest" name="sxjbzlTest" size="40"> 
		</form> 
		</td>
		</tr>
		
	  <tr>
		<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
			办理地点：
		</td>
		<td width="80%" height="35" bgcolor="#F1F9FD">
		<form method="post" name="xform"> 
			<table border="0" width="300"> 
			<tr> 
			<td width="40%"> 
			<select style="WIDTH:100%" multiple id="list3" name="list3" size="12" ondblclick="moveOption(document.getElementById('list3'), document.getElementById('list4'))"> 
			 	<c:forEach var="yydtList" items="${yydtList }" >
					<option value="${yydtList.id}">${yydtList.blddz }</option> 
			   </c:forEach>
			</select> 
			</td> 
			<td width="20%" align="center"> 
			<input type="button" value="添加" onclick="moveOption(document.getElementById('list3'), document.getElementById('list4'))">
			<br/> 
			<br/> 
			<input type="button" value="删除" onclick="moveOption(document.getElementById('list4'),document.getElementById('list3'))"> 
			</td> 
			<td width="40%"> 
			<select style="WIDTH:100%" multiple name="list4" size="12" ondblclick="moveOption(document.getElementById('list4'),document.getElementById('list3'))"> 
			</select> 
			</td> 
			</tr> 
			</table> 
		<input type="hidden"  id="dtId" name="dtId" size="40"> 
		</form> 
		</td>
	</tr>
	 <tr>
        <td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">可预约数量：</td>
        <td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;
          <input name="yysl" id="yysl" maxlength="20" style="width:200px"    />
          &nbsp;
			<font id="barinfo" color="#ff0000">*请按照办理地点右侧从上到下顺序依次写出预约数量，请用逗号分隔</font>
        </td>
      
      </tr>

	   </table>

    <br>
      <br>
	  <input name="bt1" class="button" type="button" value=" 添加 " onClick="addTwoywlx()"/>
	  <input name="bt2" class="button" type="button" value=" 返回 " onClick="window.history.back(-1);"/>
      <br>
      <br>


</body>  

</html>
