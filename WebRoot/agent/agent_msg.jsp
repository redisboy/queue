<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"  %>
<html>
  <head>
    <title>代理人管理</title>
  </head>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/queue/plugs/ecside/css/ecside_style.css" />
<script type="text/javascript" src="/queue/plugs/ecside/js/prototype_mini.js" ></script>	
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside.js" ></script>
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside_msg_utf8_cn.js" ></script> 
<script type="text/javascript" src="/queue/js/time.js" ></script> 
<script type="text/javascript">
<%
	String deptCode=(String)request.getAttribute("deptCode");
%>
function query() { 
	var id=document.getElementById("id").value;
	var reg = new RegExp("[0-9]+");
	if(reg.test(id)||id==""){
		document.form1.action = "agentmsg.action";
		document.form1.submit();
	}else{
		alert("编号填写错误");
	}
}
//-->
</script>
<body>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">代理人信息查询</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    
    <form action="" method="post" name="form1">
     <input name="flag" id="flag" type="hidden" value="1"/>
     <input name="deptCode" id="deptCode" type="hidden" value="<%=deptCode %>">
          <table width="80%" border="0" cellpadding="1" cellspacing="1" class="table_back">
            <tr>
              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">姓名</td>
              <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="name" id="name" type="text" class="inputface" value=""/> 
              </td>
            <td class=tableheader1 width=10% height=30>身份证:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="idCard" id="idCard" type="text" class="inputface" value=""/>
           </td>
           <td class=tableheader1 width=10% height=30>编号:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="id" id="id" type="text" class="inputface" value=""/>
           </td>
           <td class=tableheader1 width=10% height=30 rowspan="2">状态:</td>
           <td width=150 bgColor=#f1f9fd height=30 rowspan="2">&nbsp;
           <select name="status" id="status" >
           			<option value="">请选择</option>
              		<option value="0">注销</option>
				  	<option value="1">正常</option>
				  	<option value="2">暂停</option>
            </select>
            </td>
            </tr>
            <tr>
               <td class=tableheader1 width=10% height=30>单位:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="unit" id="unit" type="text" class="inputface" value=""/>
           </td>   
           <!-- 编号，服务单位，法人代表，状态，初登日期，有效期止，注销日期，注销经办人 -->
         	<td class=tableheader1 width=10% height=30>排序字段:</td>
           <td width=150 bgColor=#f1f9fd height=30>&nbsp;
           <select name="pxzd" id="status" >
              		<option value="id">编号</option>
				  	<option value="unit">服务单位</option>
				  	<option value="incorporator">法人代表</option>
				  	<option value="status">状态</option>
				  	<option value="register_date">初登日期</option>
				  	<option value="validity">有效期止</option>
				  	<option value="logout_date">注销日期</option>
				  	
				  	<!--  <option value="7">注销经办人</option> -->
				  	
            </select>
            </td>
           <td class=tableheader1 width=10% height=30>排序方式:</td>
           <td width=150 bgColor=#f1f9fd height=30>&nbsp;
           <select name="pxfs" id="pxfs" >
              		<option value="asc">升序</option>
				  	<option value="desc">降序</option>
            </select>
            </td>
            </tr>
          </table>
          <input name="button" type="button" class="button" value="查询" onClick="query()"/>
          <br/>
         
	</form>
    
	<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" >
	<tr>
	<td> 
		<ec:table items="list" var="list" 
					action="agentmsg.action"
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
					<ec:column width="3%" property="_0" title="序号" value="${GLOBALROWCOUNT}" style="text-align:center"  editable="false"/>
					<ec:column width="10%" property="idCard" title="身份证号" style="text-align:center" editable="false" />
					<ec:column width="7%" property="name" title="姓名" style="text-align:center" editable="false" />
					<ec:column width="10%" property="register_date" title="初次登记日期" style="text-align:center" editable="false" />
					<ec:column width="8%" property="unit" title="服务单位" style="text-align:center" editable="false" />
					<ec:column width="8%" property="cellphone" title="手机号" style="text-align:center" editable="false" />
                    <ec:column width="10%" property="unit_phone" title="单位电话" style="text-align:center" editable="false" />
                    <ec:column width="7%" property="incorporator" title="法人代表" style="text-align:center" editable="false" />
                    <ec:column width="10%" property="validity" title="有效期" style="text-align:center" editable="false" />
                    <ec:column width="5%" property="max_lshs" title="单号最大办理数" style="text-align:center" editable="false" />
                    <ec:column width="5%" property="max_times_byday" title="当天最大办理数" style="text-align:center" editable="false" />  
                    <ec:column width="10%" property="status" title="状态" style="text-align:center" editable="false" />
                    <ec:column width="5%" property="agentmsg" title="查看" style="text-align:center" editable="false" />
				</ec:row>
				
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

</html>