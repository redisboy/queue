<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@page import="com.suntendy.queue.queue.domain.Number"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"  %>
<%@ page isELIgnored="false" import="java.util.*,com.suntendy.queue.count.domain.*"%>
<html>
  <head>
    <title>领证信息窗口设置</title>
  </head>
  <meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/queue/plugs/ecside/css/ecside_style.css" />
<script type="text/javascript" src="/queue/plugs/ecside/js/prototype_mini.js" ></script>	
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside.js" ></script>
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside_msg_utf8_cn.js" ></script> 
<script type="text/javascript" src="/queue/plugs/time/time.js" ></script> 
<script type="text/javascript" src="/queue/plugs/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript">
function delLzck(zllx){
	if(confirm("确定删除吗?")){ 
		document.form1.action = "delLzck.action?zllx="+zllx;
		document.form1.submit();
	}
}
function delpzlz(zllx){
	if(confirm("确定删除吗?")){ 
		document.form1.action = "delpzlz.action?zllx="+zllx;
		document.form1.submit();
	}
}
function addLzck(){
	if(document.getElementById("barid").value == ""){
		alert("窗口号不能为空!");
		return;
	}
		document.form1.action = "addLzck.action";
		document.form1.submit();
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
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">领证信息窗口设置</span>
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
            	<td class=tableheader1 width=10% height=30>资料类型:</td>
           		<td width=150 bgColor=#f1f9fd height=30>&nbsp;
           			<select name="zllx" id="zllx" >
	              		<option value="01">驾驶证</option>
					  	<option value="02">行驶证</option>
					  	<option value="03">登记证书</option>
					  	<option value="04">号牌</option>	
					  	<option value="05">临时号牌</option>	
					  	<option value="06">检验合格标志</option>	
					  	<option value="07">人工输入</option>
					  	<option value="08">六合一开</option>
						<option value="09">六合一关</option>
	           		</select>
	            </td>
              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">窗口号:</td>
              <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          		<input name="barid" id="barid" type="text" class="inputface" value=""/>
             </td>
              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">ip:</td>
              <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          		<input name="ip" id="ip" type="text" class="inputface" value=""/>
             </td>
            </tr>
            <tr>
            </tr>

          </table>
          <br/><br/>
          
          <!-- <input name="queryy" type="button" class="button" value="添加同步类型" onClick="addLzck()"/>&nbsp;&nbsp;&nbsp;&nbsp; -->
          <a name="设置同步类型"><img src='/queue/images/button_xz.jpg' style='cursor:hand' onclick="addLzck()"></a>&nbsp;&nbsp;&nbsp;&nbsp;
          <a href="addLzck.jsp" name="设置同步类型"><img src='/queue/images/button_tb.jpg' style='cursor:hand'></a><br /><br />
          <br/>
         
	</form>
	<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" >
	<tr>
	<td> 
		<ec:table items="list" var="list" 
					action="queryAllLzck.action"
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
					<ec:column width="3%" property="_0" title="序号" style="text-align:center" value="${GLOBALROWCOUNT}" editable="false" sortable="false"/>
					<ec:column width="10%" property="zllx" title="资料类型" style="text-align:center" editable="false" />
					<ec:column width="10%" property="barid" title="窗口号" style="text-align:center" editable="false" />
					<ec:column width="20%" property="ip" title="领证窗口ip" style="text-align:center" editable="false" />
					<ec:column width="3%" property="operation" title="操作" style="text-align:center" editable="false" />
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
	<br/>
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
