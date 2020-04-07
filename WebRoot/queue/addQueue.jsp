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
<title>添加队列</title>
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
	$("#code").blur(function(){
		var queuecode = document.getElementById("code").value;
		//检查唯一性
		$.post("checkCode.action", {queuecode: queuecode}, function(data) {
		    if("1"==data){
		    	$("#qcode").html("<img src='/queue/images/wrong.gif'/>前缀已被占用!");
		    	$("#queuecode").val(""); 
			}else{
			    $("#qcode").html("<img src='/queue/images/right.gif'/>前缀未占用!");
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
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">添加队列</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    
    <table width="50%" border="0" cellpadding="1" cellspacing="1" class="table_back">
    <tr>
        <td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">队列编号</td>
        <td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
          <input name="code" id="code" maxlength="15" style="width:100px" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') "/>
          &nbsp;<font id="qcode" color="#ff0000">*请录入数字</font>
        </td>
      </tr>
      <tr>
        <td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">队列名称</td>
        <td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
          <input name="name" id="name" maxlength="10" style="width:100px" />
          &nbsp;<font color="#ff0000"></font>
        </td>
      </tr>
       <tr>
        <td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">前缀</td>
        <td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
          <input name="prenum" id="prenum" onchange="this.value=this.value.toUpperCase()"  maxlength="15" style="width:100px" />
          &nbsp;<font id="info" color="#ff0000">*请输入一位大写字母或一位数字</font>
        </td>
      </tr>
      <tr style="display: none">
        <td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">下一级队列名称</td>
        <td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
          <select name="nextQueueName" id="nextQueueName" onchange="changeQueue(this)">
          <option value="">请选择</option>
             <c:forEach var="queue" items="${list}" varStatus="vs">
                    <option value="${ queue.code}">${ queue.name}</option>
             </c:forEach>
           </select>
        </td>
      </tr>
      <tr style="display: none">
        <td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">下一级业务类型</td>
        <td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
        	<select name="nextType" id="nextType">
				<option value="">请选择</option>
			</select>
        </td>
      </tr> 
    </table>
    <br>
      <br>
	  <input name="bt1" class="button" type="button" value=" 添加 " onClick="addQueue()"/>
	  <input name="bt2" class="button" type="button" value=" 返回 " onClick="window.history.back(-1);"/>
  	  <!--<input name="bt3" class="button" type="button" value=" 退出 " onClick="exit()"/> -->
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

function addQueue(){
    var obj=document.getElementById("prenum").value;
    var codeval=document.getElementById("code").value;
    if(codeval == null || codeval == ""){ 
	      alert("请输入队列编号！"); 
	      return false;
	} 
	var exp = /^[A-Z0-9]/;
    var reg = obj.match(exp); 
	if(reg==null){ 
	      alert("前缀不合法！"); 
	      return false;
	}
	document.form1.action = "addQueue.action";
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
