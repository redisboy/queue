<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@page import="com.suntendy.queue.queue.domain.Number"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"  %>
<%@ page isELIgnored="false" import="java.util.*,com.suntendy.queue.count.domain.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>同步领证信息配置</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/queue/plugs/ecside/css/ecside_style.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
function changeZllx(zllx){
	if("" == zllx.value){
		$("#dmsm").empty();
		$("#dmsm").append("<option value=''>=请选择=</option>");
	}else{
		$.ajax({type: "POST", cache: false, async: false, dataType: "json",data:{"zllx":zllx.value},
			        url: 'changeZllx.action',
			        success: function(request) {
			            if (request.isSuccess) {
			                isChange = true;
			                delete request.isSuccess;
			                $("#dmsm").empty();
							$.each(request.datas, function(key, obj) {
							$("#dmsm").append("<option value="+obj.dm+">"+obj.mc+"</option>");
		     				});
						} else {
							 $("#dmsm").empty();
			                $("#dmsm").append("<option value=''>=请选择=</option>");
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
function addLzck(){
	document.form1.action = "lzxx/addLzck.action";
	document.form1.submit();
}
</script>
</head>
<body>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">同步领证信息配置</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    <div align="center">
			<table align="center" width="60%">
				<TR>
					<TD height="20" bgcolor="#F7F7F7" style="text-align:center; align:center">
						<div id="chart1div" align="center"></div>
					</TD>
				</TR>
			</table>
	</div>
	<br>
    <form action="" method="post" name="form1">
     <input name="flag" id="flag" type="hidden" value="1"/>
          <table width="80%" border="0" cellpadding="1" cellspacing="1" class="table_back">
            <tr>
            	<td class=tableheader1 width=40% height=30>系统类别:</td>
           		<td width=150 bgColor=#f1f9fd height=30>&nbsp;
           			<select name="zllx" id="zllx" onchange="changeZllx(this)">
           				<option value="">=请选择=</option>
	              		<option value="0060">机动车</option>
					  	<option value="0008">驾驶证</option>
	           		</select>
	            </td>
	        </tr>
	        <tr>
	       		<td class=tableheader1 width=40% height=30>资料类型:</td>
	            <td width=150 bgColor=#f1f9fd height=30>&nbsp;
           			<select name="dmsm" id="dmsm">
           				<option value="">=请选择=</option>
	           		</select>
	            </td>
	        </tr>
	        <tr>
	       		<td class=tableheader1 width=40% height=30>ip:</td>
	            <td width=150 bgColor=#f1f9fd height=30>&nbsp;
           			<input type="text" id="ip" name="ip" value="" />
           			<p style="color: red;">多个ip用英文逗号','隔开</p>
	            </td>
	        </tr>
	        <!-- <tr>
              <td width="40%" height="25" bgcolor="#FFFFFF" class="tableheader1">窗口号:</td>
              <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          		<input name="barid" id="barid" type="text" class="inputface" value=""/>
             </td>
	        </tr> -->
          </table>
          <br/>
          <input name="queryy" type="button" class="button" value="添加" onClick="addLzck()"/>
          <br/>
	</form>
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
