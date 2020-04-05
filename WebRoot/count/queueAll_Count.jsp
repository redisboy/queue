<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"  %>
<%@ page import="java.util.*,com.suntendy.queue.count.domain.*"%>
<html>
  <head>
    <title>业务办理情况统计</title>
  </head>
  <meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/queue/plugs/ecside/css/ecside_style.css" />
<script type="text/javascript" src="/queue/plugs/ecside/js/prototype_mini.js" ></script>	
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside.js" ></script>
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside_msg_utf8_cn.js" ></script> 
<script type="text/javascript" src="/queue/plugs/time/time.js" ></script> 
<script type="text/javascript" src="/queue/plugs/FusionCharts/FusionCharts.js"></script>
<style type="text/css">
<!--
.tab1{
border:1px solid #3B7DD3;
border-collapse:collapse;
}
.tab1 td{
border:1px solid #3B7DD3;
}
-->
</style>
<script type="text/javascript">
<%
	List<QueueAllCount> list = (List<QueueAllCount>) request.getAttribute("list");
	Date d = new Date();   
	java.text.SimpleDateFormat dformat = new java.text.SimpleDateFormat("yyyy-MM-dd");   
	String datetime = dformat.format(d); 	
%>
function query() { 
	var ksrq=document.getElementById("ksrq").value;
	var jsrq=document.getElementById("jsrq").value;
	var tjms=document.getElementById("tjms").value;
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

	document.form1.action = "queueAllCount.action?ksrq="+ksrq+"&jsrq="+jsrq+"&tjms="+tjms;
	document.form1.submit();
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
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">业务办理情况统计</span>
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
              <td align=middle width=150 bgColor=#f1f9fd height=30>&nbsp;
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
            <td class=tableheader1 align=middle width=10% height=30>结束时间:</td>
          	<td align=middle width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <%
          		if(null!=jsrq && !jsrq.equals("")){
          	 %>
	          <input name="jsrq" id="jsrq" type="text" class="inputface" value="<%=jsrq %>"
	          onClick="setday(this)" readonly/>
	           <%}else{ %>
	         <input name="jsrq" id="jsrq" type="text" class="inputface" value="${today }"
	          onClick="setday(this)" readonly/>
	          <%} %>
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
          <input name="queryy" type="button" class="button" value="统计" onClick="query()"/>
          <br/>
         
	</form>
    <br/><br/><br/>
    <div id="alove">
	<table width="80%" align="center" border="1" border="0" cellpadding="0" cellspacing="0"  class="tab1" >
		  <tr>
		  	 <%
          		if(null!=ksrq && !ksrq.equals("") && null!=jsrq && !jsrq.equals("")){
          	 %>
		    <td height="35" colspan="14" align="left" bgcolor="#FFFFFF" valign="middle">统计时间： <%=ksrq %> -- <%=jsrq %> </td>
		    <%
		    }else{
		     %>
		     <td height="35" colspan="14" align="left" bgcolor="#FFFFFF" valign="middle">统计时间：<%=datetime %></td>
		     <%
		     }
		     %>
		  </tr>
		  <tr>
		    <td rowspan="2" style="text-align:center" bgcolor="#FFFFFF" valign="middle">窗口号</td>
		    <td rowspan="2" style="text-align:center" bgcolor="#FFFFFF" valign="middle">经办人</td>
		    <td height="29" colspan="3" bgcolor="#FFFFFF" style="text-align:center" valign="middle">排队情况</td>
		    <td rowspan="2" style="text-align:center" bgcolor="#FFFFFF" valign="middle">排队数合计</td>
		    <td colspan="2" style="text-align:center" bgcolor="#FFFFFF" valign="middle">拍照情况</td>
		    <td colspan="6" style="text-align:center" bgcolor="#FFFFFF" valign="middle">评价结果</td>
		  </tr>
		  <tr>
		    <td height="31" style="text-align:center" bgcolor="#FFFFFF" valign="middle">正常办理</td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle">过号</td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle">挂起</td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle">已拍照数</td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle">未拍照数</td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle">非常满意</td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle">满意</td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle">一般</td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle">不满意</td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle">已评价合计</td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle">为评价合计</td>
		  </tr>
		  <% 
		  if(null != list && list.size()>0){
		  	for(int i=0;i<list.size();i++){ 
		  		QueueAllCount allcount = list.get(i);
		  		if(null == allcount.getBarId()) allcount.setBarId("");
		  		if(null == allcount.getCode()) allcount.setCode("");
		  %>
		  <tr>
		    <td height="24" style="text-align:center" bgcolor="#FFFFFF" valign="middle"><%=allcount.getBarId() %></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"><%=allcount.getCode() %></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"><%=allcount.getZcbl() %></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"><%=allcount.getGuoh() %></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"><%=allcount.getGuaq() %></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"><%=allcount.getBlSum() %></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"><%=allcount.getYpzsl() %></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"><%=allcount.getNopzsl() %></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"><%=allcount.getEvalue1() %></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"><%=allcount.getEvalue2() %></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"><%=allcount.getEvalue3() %></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"><%=allcount.getEvalue4() %></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"><%=allcount.getEvalueSum() %></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"><%=allcount.getNoevalueSum() %></td>
		  </tr>
		  <%
		  	}
		  }else{ 
		  %>
		  <tr>
		    <td height="24" style="text-align:center" bgcolor="#FFFFFF" valign="middle"></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"></td>
		    <td style="text-align:center" bgcolor="#FFFFFF" valign="middle"></td>
		  </tr>
		  <%} %>
	</table>
	</div>	
	<br/><br/><br/>
	<input type="button" class="button" onclick="javascript:method1();" value="导出"/>
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
function method1()  { 
       var oXL = new ActiveXObject("Excel.Application"); 
       var oWB = oXL.Workbooks.Add(); 
       var oSheet = oWB.ActiveSheet; 
       var sel=document.body.createTextRange(); 
       sel.moveToElementText(alove); 
       sel.select(); 
       sel.execCommand("Copy"); 
       oSheet.Paste(); 
       oXL.Visible = true; 
      } 
</script>
</html>
