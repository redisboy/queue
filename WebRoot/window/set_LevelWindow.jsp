<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"%>
<html>
  <head>
    <title>子窗口设置</title>
  </head>
  <meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/queue/plugs/ecside/css/ecside_style.css" />
<script type="text/javascript" src="/queue/plugs/ecside/js/prototype_mini.js" ></script>	
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside.js" ></script>
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside_msg_utf8_cn.js" ></script> 
<body>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">子窗口设置</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
	<form action="" method="post" name="form1"></form>
	<input name="button" type="button" class="button" value="添加" onClick="addWindow()"/><br/><br/>
    <table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" >
	<tr>
	<td> 
		<ec:table items="list" var="list" 
					action="cksz.action"
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
					filterable="false">
				<ec:row style="text-align:center">
					<ec:column width="10%" property="windowId" title="子窗口编号" style="text-align:center" editable="false" />
					<ec:column width="10%" property="menuAddress" title="父窗口编号" style="text-align:center" editable="false" />
					<ec:column width="10%" property="address" title="LED屏地址" style="text-align:center" editable="false" />
					<ec:column width="7%" property="comnum" title="COM口" style="text-align:center" editable="false" />
					<ec:column width="8%" property="envaluevalue" title="默认评价" style="text-align:center" editable="false" />
					<ec:column width="16%" property="barip" title="BarIP地址" style="text-align:center" editable="false" />
					<ec:column property="queueId" title="可办业务" style="text-align:center" editable="false" />
					<ec:column width="15%" property="operation" title="操   作" style="text-align:center" editable="false" />
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
	function addWindow(){
		document.form1.action = "addLevel.action";
		document.form1.submit();
	}
	
	function updateWindow(id){
		document.form1.action = "updateLevelWindow.action?id="+id; 
		document.form1.submit();
	}
	
	function delWindow(id, ip){
		if(confirm("确定删除吗?")){ 
		document.form1.action = "delLevelWindow.action?id="+id+"&ip="+ip;
		document.form1.submit();
		}
	}
</script>
</html>