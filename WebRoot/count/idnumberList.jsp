<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@page import="com.suntendy.queue.queue.domain.Number"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"  %>
<%@ page isELIgnored="false" import="java.util.*,com.suntendy.queue.count.domain.*"%>
<html>
  <head>
    <title>身份证异常预警</title>
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
<%
	List<Number> list = (List<Number>) request.getAttribute("list");
	request.getSession().setAttribute("list",list);
%>
function query() { 
	var ksrq=document.getElementById('ksrq').value;
	var jsrq=document.getElementById('jsrq').value;
	if(ksrq!=""){
		if(jsrq==""){
		alert("请选择日期");
		return false;
		}
	}
	if(jsrq!=""){
		if(ksrq==""){
		alert("请选择日期");
		return false;
		}
	}
	document.form1.action = "queryIdnumberDifference.action";
	document.form1.submit();
}
function hpExport(obj){
	window.location.href="count/idnumberList_Export_excel.jsp";
    return true;
}

function checkEmpty(){
	var result=false;
	var tjms=document.getElementById("tjms");
	for(var i=0;i<tjms.length;i++){
		if(tjms[i].selected==true){
			var text=tjms[i].text;
		};
	};
	var ksrq=document.getElementById("ksrq").attributes["value"].value;
	var jsrq=document.getElementById("jsrq").attributes["value"].value;
	var idnumber=document.getElementById("idnumber").attributes["value"].value;
	var idnumberb=document.getElementById("idnumberb").attributes["value"].value;
	var lsh=document.getElementById("lsh").attributes["value"].value;
	if(!(text=='手动汇总')){
		query();
	}else{
		if(!(ksrq==''||jsrq=='')){
			if(!(idnumber=='')){
				result=true;
			}
			if(!(idnumberb=='')){
				result=true;
			}
			if(!(lsh=='')){
				result=true;
			}
			if(result){
				query();
			}else{
				query();
			}
		}else{
			alert("请选择开始和结束时间");
		}
	};
};
</script>

<body>
<%
	String ksrq=(String)request.getAttribute("ksrq");
	String jsrq=(String)request.getAttribute("jsrq");
 %>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">身份证异常预警</span>
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
              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">起始时间</td>
              <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	         <%
          		if(null!=ksrq && !ksrq.equals("")){
          	 %>
	          <input name="ksrq" id="ksrq" type="text" class="inputface" value="<%=ksrq %>"
	          onClick="setday(this)" readonly/>
	          <%}else{ %>
	          <input name="ksrq" id="ksrq" type="text" class="inputface" value="${today }"
	          onClick="setday(this)" readonly/>
	          <%} %>
             </td>
            <td class=tableheader1 width=10% height=30>结束时间:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <%
          		if(null!=ksrq && !ksrq.equals("")){
          	 %>
	          <input name="jsrq" id="jsrq" type="text" class="inputface" value="<%=jsrq %>"
	          onClick="setday(this)" readonly/>
	           <%}else{ %>
	         <input name="jsrq" id="jsrq" type="text" class="inputface" value="${today }"
	          onClick="setday(this)" readonly/>
	          <%} %>
           </td>
           <td class=tableheader1 width=10% height=30>统计模式:</td>
           <td width=150 bgColor=#f1f9fd height=30>&nbsp;
           <select name="tjms" id="tjms" >
              		<option value="0">手动汇总</option>
				  	<option value="1">当天汇总</option>
				  	<option value="2">当月汇总</option>
				  	<option value="3">当季汇总</option>	
				  	<option value="4">当年汇总</option>	
            </select>
            </td>
            </tr>
            <tr>
              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">取号人证件:</td>
              <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          	<input name="idnumber" id="idnumber" type="text" class="inputface"/>
             </td>
            <td class=tableheader1 width=10% height=30>办理人证件:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          	<input name="idnumberb" id="idnumberb" type="text" class="inputface" style="text-transform: uppercase;"/>
           </td>
           <td class=tableheader1 width=10% height=30>流水号:</td>
           <td width=150 bgColor=#f1f9fd height=30>
           		<input name="lsh" id="lsh" type="text" class="inputface" style="text-transform: uppercase;"/>
           </td>
            </tr>
            <tr>
            </tr>

          </table>
          <input name="queryy" type="button" class="button" value="查询" onClick="checkEmpty()"/>
          <br/>
         
	</form>
    
	<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" >
	<tr>
	<td> 
		<ec:table items="list" var="list" 
					action="getBarId.action"
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
					<ec:column width="10%" property="serialNum" title="顺序号" style="text-align:center" editable="false" />
					<ec:column width="20%" property="enterTime" title="取号时间" style="text-align:center" editable="false" />
					<ec:column width="25%" property="IDNumber" title="取号人证件号码" style="text-align:center" editable="false" />
					<ec:column width="25%" property="IDNumberB" title="办理人证件号码" style="text-align:center" editable="false" />
					<ec:column width="20%" property="lsh" title="流水号" style="text-align:center" editable="false" />
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
