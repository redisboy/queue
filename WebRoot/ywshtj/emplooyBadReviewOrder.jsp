<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"  %>
<%@ page isELIgnored="false" import="java.util.*,com.suntendy.queue.ywshtj.domain.*"%>
<html>
  <head>
    <title>业务人员差评率考核</title>
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
	List<YwShTj> list = (List<YwShTj>) request.getAttribute("list");
	String flag = (String) request.getAttribute("flag");
	request.getSession().setAttribute("list",list);
	request.getSession().setAttribute("flag",flag);
	String deptCode=(String)request.getAttribute("deptCode");
	String deptHall=(String)request.getAttribute("deptHall");
	if(null != list && !list.isEmpty()&& list.size()>0){
	%>
		window.onload=function(){
			var str1 = "", str2="", str3="", str4="", str5="";
			<% 
			for (YwShTj count : list) {
			if(flag.equals("0")){
			%>
			str1 += "<category label='<%=count.getName()%>' />";
			<% }else{%>
			str1 += "<category label='<%=count.getDeptcode()%>' />";
			<%}%>
			str2 += "<set value='<%=count.getA1()%>' />";
			str3 += "<set value='<%=count.getA2()%>' />";
			str4 += "<set value='<%=count.getA3()%>' />";
			str5 += "<set value='<%=count.getA4()%>' />";
			<%}%>
			var strXML = "<chart xAxisName='' yAxisName='' caption='业务人员差评率考核' showToolTip='0' shownames='1' showvalues='1' showSum='1' useRoundEdges='1'>";
			strXML += "<categories>"+str1+"</categories>";
			strXML += "<dataset seriesName='非常满意' color='' showValues=''>"+str2+"</dataset>";
			strXML += "<dataset seriesName='满意' color='' showValues=''>"+str3+"</dataset>";
			strXML += "<dataset seriesName='一般' color='' showValues=''>"+str4+"</dataset>";
			strXML += "<dataset seriesName='不满意' color='' showValues=''>"+str5+"</dataset></chart>";
		    var chart1 = new FusionCharts("/queue/plugs/FusionCharts/MSColumn2D.swf","chart1ID","800","400","0","0");
		 	chart1.setDataXML(strXML);
		 	chart1.render("chart1div");
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
	<%if(flag.equals("0")){ %>
	document.form1.action = "getEmplooyBadReviewOrder.action?flag=0";
	<%}else{%>
	document.form1.action = "getEmplooyBadReviewOrder.action?flag=1";
	<%}%>
	document.form1.submit();
}
function hpExport(obj){
	window.location.href="/queue/ywshtj/emplooyBadReviewOrder_Export_excel.jsp";
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
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">业务人员差评率考核</span>
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
              <td align=middle width=150 bgColor=#f1f9fd height=30>&nbsp;
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
            <td class=tableheader1 align=middle width=10% height=30>结束时间:</td>
          	<td align=middle width=150 bgColor=#f1f9fd height=30>&nbsp;
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
           <td class=tableheader1 align=middle width=10% height=30>业务类型:</td>
           	<td align=middle width=150 bgColor=#f1f9fd height=30>&nbsp;
         		 <select name="ywlx" id="ywlx" >
              		<option value="">-请选择-</option>
             		<c:forEach var="business" items="${businessList}" varStatus="vs">
					<option value="${business.id}">${business.name}</option>
             	</c:forEach>
            </select>
            </td>
          <td class=tableheader1 align=middle width=10% height=30>统计模式:</td>
           <td align=middle width=150 bgColor=#f1f9fd height=30>&nbsp;
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
    
	<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" >
	<tr>
	<td> 
	<% if(flag.equals("0")){ %> 
		<ec:table items="list" var="list" 
					action="getEmplooyBadReviewOrder.action?flag=0"
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
					pageSizeList="5"
					showPrint="false"
					filterable="false"
					>
				<ec:row style="text-align:center">
					<ec:column width="10%" property="code" title="员工编号" style="text-align:center" editable="false" />
					<ec:column width="10%" property="name" title="姓名" style="text-align:center" editable="false" />
					<ec:column width="8%" property="a1" title="非常满意" style="text-align:center" editable="false" />
					<ec:column width="10%" property="a2" title="满意" style="text-align:center" editable="false" />
					<ec:column width="10%" property="a3" title="一般" style="text-align:center" editable="false" />
					<ec:column width="10%" property="a4" title="不满意" style="text-align:center" editable="false" />
					<ec:column width="10%" property="avg_badReview" title="差评率" style="text-align:center" editable="false" />
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
	<%}}else{ %>
		<ec:table items="list" var="list" 
					action="getEmplooyBadReviewOrder.action?flag=1"
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
					pageSizeList="5"
					showPrint="false"
					filterable="false"
					>
				<ec:row style="text-align:center">
					<ec:column width="10%" property="deptcode" title="部门编号" style="text-align:center" editable="false" />
					<ec:column width="8%" property="a1" title="非常满意" style="text-align:center" editable="false" />
					<ec:column width="10%" property="a2" title="满意" style="text-align:center" editable="false" />
					<ec:column width="10%" property="a3" title="一般" style="text-align:center" editable="false" />
					<ec:column width="10%" property="a4" title="不满意" style="text-align:center" editable="false" />
					<ec:column width="10%" property="avg_badReview" title="差评率" style="text-align:center" editable="false" />
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
	<%}}%>
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
