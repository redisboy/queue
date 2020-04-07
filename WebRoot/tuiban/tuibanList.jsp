<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"%>
<%@ page isELIgnored="false"%>
<%@ page import="java.util.*,com.suntendy.queue.tuiban.domain.*"%>
<html>
  <head>
    <title>退办信息设置</title>
  </head>
  <meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/queue/plugs/ecside/css/ecside_style.css" />
<script type="text/javascript" src="/queue/plugs/ecside/js/prototype_mini.js" ></script>	
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside.js" ></script>
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside_msg_utf8_cn.js" ></script>
<%
	List<TuiBan> list = (List<TuiBan>) request.getAttribute("list");
	request.getSession().setAttribute("list",list);
%>
<body>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">退办信息设置</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
	<form action="" method="post" name="form1">
		 <table width="40%" border="0" cellpadding="1" cellspacing="1" class="table_back">
            <tr>
              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">证件号码：</td>
              <td width=450 bgColor=#f1f9fd height=30 align="center">&nbsp;
	          <input style="width: 300px" name="idnumber" id="idnumber" type="text" class="inputface" value=""/> 
              </td>
            </tr>
          </table>
	</form>
    <input name="button" type="button" class="button" value="查询" onClick="query()"/>
    <input name="button" type="button" class="button" value="添加" onClick="addHmd()"/>
    <table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" >
	<tr>
	<td> 
		<ec:table items="list" var="list" 
					action="queryTuiban.action"
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
				    <ec:column width="20%" property="idnumber" title="身份证号" style="text-align:center" editable="false" />
				    <ec:column width="10%" property="jiluren" title="记录人员" style="text-align:center" editable="false" />
				    <ec:column width="10%" property="jiluip" title="记录IP" style="text-align:center" editable="false" />
					<ec:column property="jilutime" title="记录时间" style="text-align:center" editable="false" />
					<ec:column width="10%" property="operation" title="操   作" style="text-align:center" editable="false" />
				</ec:row>
				<c:if test="${msg != null}">
				<ec:extendrow>
			<tr style="background-color:#ffeedd" title="">
				<td colspan="10" style="text-align:center"><strong><font face='宋体' color='#5b92fa'><c:out value="${msg}"/></font></strong></td>
			</tr>
		</ec:extendrow>
		</c:if>
	</ec:table><br>
	<% if(null != list){ %>
	<div style="text-align:center">
		<input type="button" value="导出EXCEL" onclick="return hpExport(this)">
	</div>
	<%} %>
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
	function addHmd(){
		document.form1.action = "toAddTuiban.action"; 
		document.form1.submit();

	}
	function query(){
		document.form1.action = "queryTuiban.action"; 
		document.form1.submit();
	}
	function chakan(idnumber){
			document.form1.action = "detailTuiban.action?idnumber="+idnumber;
			document.form1.submit();
	}
	function hpExport(obj){
	window.location.href="/queue/tuiban/tuibanList_Export.jsp";
    return true;
	}
	function print(idnumber){
			document.form1.action = "printTuiban.action?idnumber="+idnumber;
			document.form1.submit();
	}
</script>
</html>