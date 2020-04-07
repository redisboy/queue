<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"  %>
<%@ page isELIgnored="false" import="java.util.*,com.suntendy.queue.count.domain.*"%>
<html>
  <head>
    <title>员工排队统计</title>
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
<script type="text/javascript" src="/queue/plugs/time/My97DatePicker/WdatePicker.js" ></script> 
<script type="text/javascript" src="/queue/plugs/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript">
<%
	List<EmployeeWaitCount> list = (List<EmployeeWaitCount>) request.getAttribute("list");
	request.getSession().setAttribute("list",list);
	String role = (String)request.getAttribute("role");
	request.getSession().setAttribute("role",role);
	String deptCode=(String)request.getAttribute("deptCode");
	String deptHall=(String)request.getAttribute("deptHall");
	if(null != list && !list.isEmpty() && list.size()>0){
	%>
		window.onload=function(){
			var str1 = "", str2="", str3="", str4="";
			<% for (EmployeeWaitCount qwc : list) {%>
			str1 += "<category label='<%=qwc.getName()%>' />";
			str2 += "<set value='<%=qwc.getTotal()%>' />";
			str3 += "<set value='<%=qwc.getAve_waittime()%>' />";
			str4 += "<set value='<%=qwc.getAve_worktime()%>' />";
			<%}%>
			var strXML = "<chart xAxisName='' yAxisName='' caption='员工排队统计' showToolTip='0' shownames='1' showvalues='1' showSum='1' useRoundEdges='1'>";
			strXML += "<categories>"+str1+"</categories>";
			strXML += "<dataset seriesName='人数' color='' showValues=''>"+str2+"</dataset>";
			strXML += "<dataset seriesName='平均等待时间' color='' showValues=''>"+str3+"</dataset>";
			strXML += "<dataset seriesName='平均办理时间' color='' showValues=''>"+str4+"</dataset></chart>";
		
		    var chart1 = new FusionCharts("/queue/plugs/FusionCharts/MSColumn2D.swf","chart1ID","800","400","0","0");
		 	chart1.setDataXML(strXML);
		 	chart1.render("chart1div");
		 	
		 	//合并管理部门和大厅名称
		 	var table = document.getElementById("ec_table");
			if(table){
				var maxRow = table.rows.length;
				var maxCol = table.rows[0].cells.length;
				if(maxCol==10 && maxRow>1){
					for(var col=0;col<2;col++){
						var count=1;
						for(var i=1;i<(maxRow-1);i++){
							var tempDeptCode=table.rows[i].cells[col].innerText;
							var nextDeptCode=table.rows[i+1].cells[col].innerText;
							if(tempDeptCode==nextDeptCode){
								count++;
								if((i+2)==maxRow){
									table.rows[i+2-count].cells[col].rowSpan=count; 
									for(var k=(i+3-count);k<(i+2);k++){
										table.rows[k].cells[col].style.display = "none";
									}
								}
							}else{
								table.rows[i+1-count].cells[col].rowSpan=count;
								for(var j=(i+2-count);j<(i+1);j++){
									table.rows[j].cells[col].style.display = "none";
								}
								count=1;
							}
						}
					}
						
				}else if(maxCol==9 && maxRow>1){
					var count=1;
					for(var i=1;i<(maxRow-1);i++){
						var tempDeptCode=table.rows[i].cells[0].innerText;
						var nextDeptCode=table.rows[i+1].cells[0].innerText;
						if(tempDeptCode==nextDeptCode){
							count++;
							if((i+2)==maxRow){
								table.rows[i+2-count].cells[0].rowSpan=count; 
								for(var k=(i+3-count);k<(i+2);k++){
									table.rows[k].cells[0].style.display = "none";
								}
							}
						}else{
							table.rows[i+1-count].cells[0].rowSpan=count;
							for(var j=(i+2-count);j<(i+1);j++){
								table.rows[j].cells[0].style.display = "none";
							}
							count=1;
						}
					}
				}
			}
		}
	<%	    
	}
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
	document.form1.action = "ygpdtj.action";
	document.form1.submit();
}
function hpExport(obj){
	window.location.href="/queue/count/employeeWait_Count_Export_excel.jsp";
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
	//判断childlength=10为管理部门+大厅, 9为大厅 
	if(childlength==10){
		for(var i=2;i<childlength;i++){
			if(childs[i].className){
				childs[i].className +=" tdlighthigh";
			}else{
				childs[i].className = "tdlighthigh";
			}
		}
	}else if(childlength==9){
		for(var i=1;i<childlength;i++){
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
	//判断childlength=10为管理部门+大厅, 9为大厅 
	if(childlength==10){
		for(var i=2;i<childlength;i++){
			var classNames=childs[i].className.split(" ");
			childs[i].className=classNames[0];
		}
	}else if(childlength==9){
		for(var i=1;i<childlength;i++){
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
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">员工排队统计</span>
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
     <input name="deptCode" id="deptCode" type="hidden" value="<%=deptCode %>">
     <input name="deptHall" id="deptHall" type="hidden" value="<%=deptHall %>">
          <table width="80%" border="0" cellpadding="1" cellspacing="1" class="table_back">
            <tr>
              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">起始时间</td>
              <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	         <%
          		if(null!=ksrq && !ksrq.equals("")){
          	 %>
	          <input name="ksrq" id="ksrq" type="text" class="inputface" value="<%=ksrq %>"
	          onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" readonly/>
	          <%}else{ %>
	          <input name="ksrq" id="ksrq" type="text" class="inputface" value="${today }"
	          onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" readonly/>
	          <%} %>
             </td>
            <td class=tableheader1 width=10% height=30>结束时间:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <%
          		if(null!=ksrq && !ksrq.equals("")){
          	 %>
	          <input name="jsrq" id="jsrq" type="text" class="inputface" value="<%=jsrq %>"
	          onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" readonly/>
	           <%}else{ %>
	         <input name="jsrq" id="jsrq" type="text" class="inputface" value="${today }"
	          onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" readonly/>
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
            </tr>

          </table>
          <input name="queryy" type="button" class="button" value="统计" onClick="checkEmpty()"/>
          <br/>
         
	</form>
    <c:if test="${0 eq role && !empty(list)}">
    	<div class="ecSide" id="ec_main_content"  style="width:100%;">
    		<table id="ec_table" class="tableRegion" width="98%" align="center" border="0" cellpadding="0" cellspacing="0">
	    		<thead id="ec_table_head">
	    			<tr>
	    				<td valign="middle" style="text-align:center"  width="10%" class="tableHeader" onmouseover="ECSideUtil.lightHeader(this,'ec');" onmouseout="ECSideUtil.unlightHeader(this,'ec');"><div class="headerTitle" >部门名称</div></td>
	    				<td valign="middle" style="text-align:center"  width="10%" class="tableHeader" onmouseover="ECSideUtil.lightHeader(this,'ec');" onmouseout="ECSideUtil.unlightHeader(this,'ec');"><div class="headerTitle" >大厅名称</div></td>
	    				<td valign="middle" style="text-align:center"  width="10%" class="tableHeader" onmouseover="ECSideUtil.lightHeader(this,'ec');" onmouseout="ECSideUtil.unlightHeader(this,'ec');"><div class="headerTitle" >窗口编号</div></td>
	    				<td valign="middle" style="text-align:center"  width="10%" class="tableHeader" onmouseover="ECSideUtil.lightHeader(this,'ec');" onmouseout="ECSideUtil.unlightHeader(this,'ec');"><div class="headerTitle" >员工编号</div></td>
	    				<td valign="middle" style="text-align:center"  width="10%" class="tableHeader" onmouseover="ECSideUtil.lightHeader(this,'ec');" onmouseout="ECSideUtil.unlightHeader(this,'ec');"><div class="headerTitle" >员工名称</div></td>
	    				<td valign="middle" style="text-align:center"  width="10%" class="tableHeader" onmouseover="ECSideUtil.lightHeader(this,'ec');" onmouseout="ECSideUtil.unlightHeader(this,'ec');"><div class="headerTitle" >叫号量</div></td>
	    				<td valign="middle" style="text-align:center"  width="10%" class="tableHeader" onmouseover="ECSideUtil.lightHeader(this,'ec');" onmouseout="ECSideUtil.unlightHeader(this,'ec');"><div class="headerTitle" >过号量</div></td>
	    				<td valign="middle" style="text-align:center"  width="10%" class="tableHeader" onmouseover="ECSideUtil.lightHeader(this,'ec');" onmouseout="ECSideUtil.unlightHeader(this,'ec');"><div class="headerTitle" >实际办理量</div></td>
	    				<td valign="middle" style="text-align:center"  width="10%" class="tableHeader" onmouseover="ECSideUtil.lightHeader(this,'ec');" onmouseout="ECSideUtil.unlightHeader(this,'ec');"><div class="headerTitle" >满意量</div></td>
	    				<td valign="middle" style="text-align:center"  width="10%" class="tableHeader" onmouseover="ECSideUtil.lightHeader(this,'ec');" onmouseout="ECSideUtil.unlightHeader(this,'ec');"><div class="headerTitle" >业务量统计</div></td>
	    			</tr>
	    		</thead>
	    		<tbody id="ec_table_body">
	    			<c:forEach items="${list}" var="item" varStatus="status">
	    				<c:if test="${(status.index%2)!=0}">
	    					<tr  style="text-align:center">
		    					<td  style="text-align:center"  width="10%" >${item.deptCodeName}</td>
		    					<td  style="text-align:center"  width="10%" >${item.deptname}</td>
		    					<td class="even" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.windowid}</td>
		    					<td class="even" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.code}</td>
		    					<td class="even" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.name}</td>
		    					<td class="even" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.jiaohaocount}</td>
		    					<td class="even" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.guohaocount}</td>
		    					<td class="even" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.gongzuocount}</td>
		    					<td class="even" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.manyicount}</td>
		    					<td class="even" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.businesscount}</td>
		    				</tr>
	    				</c:if>
		    			<c:if test="${(status.index%2)==0}">
	    					<tr  style="text-align:center">
		    					<td  style="text-align:center"  width="10%" >${item.deptCodeName}</td>
		    					<td  style="text-align:center"  width="10%" >${item.deptname}</td>
		    					<td class="odd" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.windowid}</td>
		    					<td class="odd" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.code}</td>
		    					<td class="odd" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.name}</td>
		    					<td class="odd" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.jiaohaocount}</td>
		    					<td class="odd" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.guohaocount}</td>
		    					<td class="odd" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.gongzuocount}</td>
		    					<td class="odd" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.manyicount}</td>
		    					<td class="odd" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.businesscount}</td>
		    				</tr>
	    				</c:if>	
	    			</c:forEach>
	    		</tbody>
	    	</table>
    	</div>
	    	
    </c:if>
    <c:if test="${1 eq role && !empty(list)}">
    	<div class="ecSide" id="ec_main_content"  style="width:100%;">
    		<table id="ec_table" class="tableRegion" width="98%" align="center" border="0" cellpadding="0" cellspacing="0">
	    		<thead id="ec_table_head">
	    			<tr>
	    				<td valign="middle" style="text-align:center"  width="10%" class="tableHeader" onmouseover="ECSideUtil.lightHeader(this,'ec');" onmouseout="ECSideUtil.unlightHeader(this,'ec');"><div class="headerTitle" >大厅名称</div></td>
	    				<td valign="middle" style="text-align:center"  width="10%" class="tableHeader" onmouseover="ECSideUtil.lightHeader(this,'ec');" onmouseout="ECSideUtil.unlightHeader(this,'ec');"><div class="headerTitle" >窗口编号</div></td>
	    				<td valign="middle" style="text-align:center"  width="10%" class="tableHeader" onmouseover="ECSideUtil.lightHeader(this,'ec');" onmouseout="ECSideUtil.unlightHeader(this,'ec');"><div class="headerTitle" >员工编号</div></td>
	    				<td valign="middle" style="text-align:center"  width="10%" class="tableHeader" onmouseover="ECSideUtil.lightHeader(this,'ec');" onmouseout="ECSideUtil.unlightHeader(this,'ec');"><div class="headerTitle" >员工名称</div></td>
	    				<td valign="middle" style="text-align:center"  width="10%" class="tableHeader" onmouseover="ECSideUtil.lightHeader(this,'ec');" onmouseout="ECSideUtil.unlightHeader(this,'ec');"><div class="headerTitle" >叫号量</div></td>
	    				<td valign="middle" style="text-align:center"  width="10%" class="tableHeader" onmouseover="ECSideUtil.lightHeader(this,'ec');" onmouseout="ECSideUtil.unlightHeader(this,'ec');"><div class="headerTitle" >过号量</div></td>
	    				<td valign="middle" style="text-align:center"  width="10%" class="tableHeader" onmouseover="ECSideUtil.lightHeader(this,'ec');" onmouseout="ECSideUtil.unlightHeader(this,'ec');"><div class="headerTitle" >实际办理量</div></td>
	    				<td valign="middle" style="text-align:center"  width="10%" class="tableHeader" onmouseover="ECSideUtil.lightHeader(this,'ec');" onmouseout="ECSideUtil.unlightHeader(this,'ec');"><div class="headerTitle" >满意量</div></td>
	    				<td valign="middle" style="text-align:center"  width="10%" class="tableHeader" onmouseover="ECSideUtil.lightHeader(this,'ec');" onmouseout="ECSideUtil.unlightHeader(this,'ec');"><div class="headerTitle" >业务量统计</div></td>
	    			</tr>
	    		</thead>
	    		<tbody id="ec_table_body">
	    			<c:forEach items="${list}" var="item" varStatus="status">
	    				<c:if test="${(status.index%2)!=0}">
	    					<tr  style="text-align:center" >
		    					<td  style="text-align:center"  width="10%" >${item.deptname}</td>
		    					<td class="even" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.windowid}</td>
		    					<td class="even" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.code}</td>
		    					<td class="even" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.name}</td>
		    					<td class="even" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.jiaohaocount}</td>
		    					<td class="even" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.guohaocount}</td>
		    					<td class="even" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.gongzuocount}</td>
		    					<td class="even" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.manyicount}</td>
		    					<td class="even" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.businesscount}</td>
		    				</tr>
	    				</c:if>
		    			<c:if test="${(status.index%2)==0}">
	    					<tr  style="text-align:center" >
		    					<td  style="text-align:center"  width="10%" >${item.deptname}</td>
		    					<td class="odd" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.windowid}</td>
		    					<td class="odd" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.code}</td>
		    					<td class="odd" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.name}</td>
		    					<td class="odd" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.jiaohaocount}</td>
		    					<td class="odd" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.guohaocount}</td>
		    					<td class="odd" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.gongzuocount}</td>
		    					<td class="odd" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.manyicount}</td>
		    					<td class="odd" style="text-align:center"  width="10%" onmouseover="moveInTdLight(this)" onmouseout="moveOutTdUnLight(this)">${item.businesscount}</td>
		    				</tr>
	    				</c:if>	
	    			</c:forEach>
	    		</tbody>
	    	</table>
    	</div>
    </c:if>
    <c:if test="${2 eq role && !empty(list)}">
		<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" >
			<tr>
				<td> 
					<ec:table items="list" var="list" 
								action="ygpdtj.action"
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
								<ec:column width="8%" property="windowid" title="窗口编号" style="text-align:center" editable="false" />
								<ec:column width="8%" property="code" title="员工编号" style="text-align:center" editable="false" />
								<ec:column width="8%" property="name" title="员工名称" style="text-align:center" editable="false" />
								<ec:column width="8%" property="jiaohaocount" title="叫号量" style="text-align:center" editable="false" />
								<ec:column width="8%" property="guohaocount" title="过号量" style="text-align:center" editable="false" />
								<ec:column width="8%" property="gongzuocount" title="实际办理量" style="text-align:center" editable="false" />
								<ec:column width="8%" property="manyicount" title="满意量" style="text-align:center" editable="false" />
								<ec:column width="8%" property="businesscount" title="员工业务量统计" style="text-align:center" editable="false" />
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
	</c:if>
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
