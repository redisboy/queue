<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"%>
<%
	String clientIp = request.getHeader("x-forwarded-for");//客户端IP
	if (null == clientIp || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
		clientIp = request.getHeader("Proxy-Client-IP");
	}
	if (null == clientIp || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
		clientIp = request.getHeader("WL-Proxy-Client-IP");
	}
	if (null == clientIp || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
		clientIp = request.getRemoteAddr();
	}
%>
<html>
  <head>
    <title>领证发屏</title>
  </head>
  <meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/queue/plugs/ecside/css/ecside_style.css" />
<script type="text/javascript" src="/queue/plugs/ecside/js/prototype_mini.js" ></script>	
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside.js" ></script>
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside_msg_utf8_cn.js" ></script>
<script type="text/javascript" src="/queue/plugs/time/time.js" ></script> 
<body>
<%
	String rksj=(String)request.getAttribute("rksj");
 %>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">领证发屏</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
	<form action="" method="post" name="form1">
          <table width="30%" border="0" cellpadding="1" cellspacing="1" class="table_back">
            <tr>
              <td width="40%" height="25" bgcolor="#FFFFFF" class="tableheader1">授权ip</td>
              <td width="60%" bgColor=#f1f9fd height=30 align="center" colspan="5">&nbsp;
	          <input style="width: 200px" name="khdip" id="khdip" type="text" class="inputface" value=""/> 
              </td>
            </tr>
            <tr>
              <td width="40%" height="25" bgcolor="#FFFFFF" class="tableheader1">授权信息</td>
              <td width="60%" bgColor=#f1f9fd height=30 align="center" colspan="5">&nbsp;
	          <input style="width: 200px" name="sqxx" id="sqxx" type="text" class="inputface" value=""/> 
              </td>
            </tr>
          </table>
    		<input name="button" type="button" class="button" value="添加" onClick="checkEmpty()"/><br><br>
          </form>
    <table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" >
	<tr>
	<td> 
		<ec:table items="list" var="list" 
					action="queryAllkhdsq.action"
					editable="false" 
					width="100%" 
					listWidth="100%"
					classic="true"
					doPreload="false"
					useAjax="false"
					resizeColWidth="false" 
					sortable="false"
					batchUpdate="false"
					nearPageNum="0"
					toolbarLocation="top"
					pageSizeList="15"
					showPrint="false"
					filterable="false"
					>
				<ec:row style="text-align:center">
					<ec:column width="5%" property="_0" title="序号" style="text-align:center" value="${GLOBALROWCOUNT}" editable="false" sortable="false"/>
				    <ec:column width="10%" property="ip" title="授权ip" style="text-align:center" editable="false" />
				    <ec:column width="15%" property="sqxx" title="授权信息" style="text-align:center" editable="false" />
				    <ec:column width="20%" property="sqm" title="授权码" style="text-align:center" editable="false" />
				</ec:row>
				<c:if test="${msg != null}">
				<ec:extendrow>
			<tr style="background-color:#ffeedd" title="">
				<td colspan="10" style="text-align:center"><strong><font face='宋体' color='#5b92fa'><c:out value="${msg}"/></font></strong></td>
			</tr>
		</ec:extendrow>
		</c:if>
	</ec:table><br>
    </td>
	</tr>
	</table>
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
	function add(){
		document.form1.action = "addkhdsq.action";
			document.form1.submit();
	}
	function checkEmpty(){
		var result=false;
		var khdip=document.getElementById("khdip").attributes["value"].value;
		var sqxx=document.getElementById("sqxx").attributes["value"].value;
		if(!(khdip=='') && !(sqxx=='')){
			add();
		}else{
			alert("添加信息不能为空!");
		}
	}
</script>
</html>