<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"  %>
<%@ page isELIgnored="false" import="java.util.*,com.suntendy.queue.warn.domain.*"%>
<html>
  <head>
    <title>警告人员列表</title>
    <style type="text/css">
    	.odd {background-color: #FFFFFF;}
    	.even {background-color: #f3f4f3;}
    	.tdlighthigh {background-color: #ddeeff;}
    </style>
  </head>
  <meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/queue/plugs/ecside/css/ecside_style.css" />
<script type="text/javascript" src="/queue/plugs/ecside/js/prototype_mini.js" ></script>	
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside.js" ></script>
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside_msg_utf8_cn.js" ></script> 
<script type="text/javascript" src="/queue/plugs/time/time.js" ></script> 
<script type="text/javascript" src="/queue/plugs/FusionCharts/FusionCharts.js"></script>
<script language="JavaScript">
 function myrefresh(){
   window.location.reload();
 }
 setTimeout('myrefresh()',5000*60); //指定五分钟刷新一次
</script>
<script type="text/javascript">
<%
	List<Warn> list = (List<Warn>) request.getAttribute("list");
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
	document.form1.action = "searchWarn.action";
	document.form1.submit();
}
function hpExport(obj){
	window.location.href="/queue/warn/warning_index_Export.jsp";
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
	if(!(text=='手动汇总')){
		query();
	}else{
		if(!(ksrq==''||jsrq=='')){
			query();
		}else{
			alert("请选择开始和结束时间");
		}
	};
};
//鼠标移入td高亮
function moveInTdLight(object){
	var parent = object.parentNode;
	var childs = parent.childNodes;
	var childlength = parent.childNodes.length;
	//判断childlength=7为管理部门+大厅, 6为大厅 
	if(childlength==3){
		for(var i=0;i<childlength;i++){
			if(childs[i].className){
				childs[i].className +=" tdlighthigh";
			}else{
				childs[i].className = "tdlighthigh";
			}
		}
	}
}
//鼠标移出td取消高亮
function moveOutTdUnLight(object){
	var parent = object.parentNode;
	var childs = parent.childNodes;
	var childlength = parent.childNodes.length;
	//判断childlength=7为管理部门+大厅, 6为大厅 
	if(childlength==3){
		for(var i=0;i<childlength;i++){
			var classNames=childs[i].className.split(" ");
			childs[i].className=classNames[0];
		}
	}	
}
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
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">警告人员列表</span>
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
	          <input type="hidden" id="hksrq" name="hksrq" value="${ksrq}">
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
	          <input type="hidden" id="hjsrq" name="hjsrq" value="${jsrq}">
           </td>
           <td class=tableheader1 width=10% height=30>统计模式:</td>
           <td width=150 bgColor=#f1f9fd height=30>&nbsp;
           <select name="tjms" id="tjms" >
              		<option value="0" <c:if test="${tjms eq 0}">selected="selected"</c:if>>手动汇总</option>
				  	<option value="1" <c:if test="${tjms eq 1}">selected="selected"</c:if>>当天汇总</option>
				  	<option value="2" <c:if test="${tjms eq 2}">selected="selected"</c:if>>当月汇总</option>
				  	<option value="3" <c:if test="${tjms eq 3}">selected="selected"</c:if>>当季汇总</option>	
				  	<option value="4" <c:if test="${tjms eq 4}">selected="selected"</c:if>>当年汇总</option>	
            </select>
            </td>
            </tr>
            <tr>
            </tr>

          </table>
          <input name="queryy" type="button" class="button" value="统计" onClick="checkEmpty()"/>
          <br/>
         
	</form>
		<ec:table items="list" var="list" 
					action="searchWarn.action"
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
					pageSizeList="15,30,50,100"
					showPrint="false"
					filterable="false"
					>
					<ec:row style="text-align:center">
						<ec:column width="8%" property="barid" title="窗口名称" style="text-align:center" editable="false" />
						<ec:column width="8%" property="name" title="姓名" style="text-align:center" editable="false" />
						<ec:column width="8%" property="jgyy" title="警告原因" style="text-align:center" editable="false" />
					</ec:row>
		</ec:table>
    	
    
	<c:if test="${!empty(list)}">
		<div style="text-align:center">
		<input type="button" value="导出EXCEL" onclick="return hpExport(this)">
		</div>
	</c:if>	
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
