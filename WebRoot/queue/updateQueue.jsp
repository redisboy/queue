<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>队列更新</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
window.onload = function() {
	$("#prenum").blur(function(){
		var preindex = $("#prenum").val();
		//检查是否为空
		if(""==preindex){
		   $("#info").html("<img src='/queue/images/wrong.gif'/>前缀不能为空!");
			return;
		}
		
		//检查唯一性
		$.post("checkPreIndex.action", {preindex: preindex}, function(data) {
		    if("1"==data){
		    	$("#info").html("<img src='/queue/images/wrong.gif'/>前缀已被占用!");
		    	$("#preindex").val(""); 
			}else{
			    $("#info").html("<img src='/queue/images/right.gif'/>前缀未占用!");
			}
		});
	});
}
</script>
</head>
<body>
<form action="" name="form1" method="post">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">队列修改</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    
    <table width="50%" border="0" cellpadding="1" cellspacing="1" class="table_back">
     <tr style="display:none" >
        <td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">队列ID</td>
        <td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
          <input name="id" id="id" maxlength="10" style="width:100px" value="<c:out value="${id }"></c:out>" readonly />
          &nbsp;<font color="#ff0000"></font>
        </td>
      </tr>
      <tr>
        <td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">队列编号</td>
        <td width="80%" height="35" bgcolor="#F1F9FD">
          &nbsp;&nbsp;${code}
          <input type="hidden" name="code" value="${code}" />
        </td>
      </tr>
      <tr>
        <td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">队列名称</td>
        <td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
          <input name="name" id="name" maxlength="10" style="width:100px" value="<c:out value="${name }"></c:out>"  />
          &nbsp;<font color="#ff0000"></font>
        </td>
      </tr>
       <tr>
        <td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">前缀</td>
        <td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;${preNum }</td>
      </tr>
 <tr style="display: none">
        <td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">下一级队列名称</td>
        <td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
        
          <select name="nextQueueName" id="nextQueueName" onchange="changeQueue(this)">
             <option value="">请选择</option>
             <c:forEach var="queue" items="${list}" varStatus="vs">
                    <option value="${queue.code}" <c:if test="${queue.code eq nextQueueName}">selected="selected"</c:if>>${queue.name}</option>
             </c:forEach>
           </select>
        </td>
      </tr>
      <tr style="display: none">
        <td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">下一级业务类型</td>
        <td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
        	<select name="nextType" id="nextType">
        	<option value="">请选择</option>
				<c:forEach var="buseness" items="${businessList}" varStatus="vs">
                    <option value="${buseness.id}" <c:if test="${nextType eq buseness.id}">selected="selected"</c:if>>${buseness.name}</option>
             </c:forEach>
			</select>
        </td>
      </tr> 
    </table>
    <br>
      <br>
	  <input name="bt1" class="button" type="button" value=" 修改 " onClick="updateByCode()"/>
	  <input name="bt2" class="button" type="button" value=" 返回 " onClick="window.history.back(-1);"/>
      <input name="bt3" class="button" type="button" value=" 退出 " onClick="exit()"/>
      <br>
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
</form>

<script type="text/javascript">
function exit(){
	document.form1.action = "/queue/index/welcome.html";
	document.form1.submit();
}
function updateByCode(){
    var name = document.form1.name.value;
	if(name.length == 0){
		alert("队列名不能为空!");
		document.form1.name.focus();
		return false;
	}
	
	/*var obj=document.getElementById("prenum").value;
	var exp = /^[A-Z0-9]/;
    var reg = obj.match(exp); 
	if(reg==null){ 
	      alert("前缀不合法！"); 
	      return false;
	} */
	document.form1.action = "updateQueueByCode.action";
	document.form1.submit();
}
function changeQueue(queueCode){
	if("" == queueCode.value){
		$("#nextType").empty();
		$("#nextType").append("<option value=''>请选择</option>");
	}else{
	$.ajax({type: "POST", cache: false, async: false, dataType: "json",data:{"queueCode":queueCode.value},
		        url: 'getBuseinessByQueueCode.action',
		        success: function(request) {
		            if (request.isSuccess) {
		           
		                isChange = true;
		                delete request.isSuccess;
		                $("#nextType").empty();
		                $("#nextType").append("<option value=''>请选择</option>");
						$.each(request.datas, function(key, obj) {
						$("#nextType").append("<option value="+obj.businessId+">"+obj.businessName+"</option>");
	     				});
					} else {
						 $("#nextType").empty();
		                $("#nextType").append("<option value=''>请选择</option>");
						 isSuccess = request.isSuccess;
						 window.alert(request.error);
					}
		        },
		        error: function() {
		            isSuccess = false;
		            window.alert("连接服务器失败，请稍候再试");
		        }
		    });
	}
}
</script>
</body>
</html>
