<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@page import="com.suntendy.queue.systemlog.domain.Operate"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"  %>
<%@ page isELIgnored="false" import="java.util.*,com.suntendy.queue.systemlog.domain.*"%>
<html>
  <head>
    <title>异常提示</title>
  </head>
  <meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/queue/plugs/ecside/css/ecside_style.css" />
<script type="text/javascript" src="/queue/plugs/ecside/js/prototype_mini.js" ></script>	
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside.js" ></script>
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside_msg_utf8_cn.js" ></script> 
<script type="text/javascript" src="/queue/plugs/time/time.js" ></script> 
<script type="text/javascript" src="/queue/plugs/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="/queue/js/jquery.js"></script>
<script type="text/javascript"></script>
<style type="text/css">
	div{
		position:absolute;
		left:550px;
		top:89px;
		width: 505px;
		height: 250px;
        margin: 60 180;
     	border-style:solid;
     	border-color:blue;
        background-color: white;
	}
	
	.hs5{
		width: 505px;
		height: 30px;
		font-size:30px;
		font-weight:600;
		background-color:#46A3FF
	}
	
	.tb1 td{
	text-align:left;
	font-size:20px;
	}
</style>


<body>
		<div><h5 class="hs5">系统提示</h5>
				<table class="tb1">
						<tr>
							<td style="color:red;line-height:25px;">错误代码：404</td>
						</tr>
						<tr>
							<td>抱歉,你访问的页面不存在!!!</td>
						</tr>
						<%--<tr>
							<td>请检测网络是否正常!</td>
						</tr>
						 <tr>
							<td><a onclick="msg()" style="color:blue;" href="#">查看详细信息</a></td>
						</tr>
						<tr id="error" style="display: none;">
							 <td><%=errorMessage%></td>
						</tr> --%>
				</table>
			</div>
			
		 
  <tr>
    <td ><img src="/queue/images/t-6.jpg" width="7" height="11"></td>
    <td height="11" background="/queue/images/t-7.jpg"></td>
    <td ><img src="/queue/images/t-8.jpg" width="8" height="11"></td>
  </tr>
  <script type="text/javascript">
  	function msg(){
  	    //点击弹出，点击缩回
  		$("#error").toggle();
  		
  	}
  	
  	
  </script>
</body>
</html>

