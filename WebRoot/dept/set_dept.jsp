<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"%>
<html>
  <head>
    <title>大厅基础信息设置</title>
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
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">大厅基础信息设置</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
	<form action="" method="post" name="form1">
		<table width="80%" border="0" cellpadding="1" cellspacing="1" class="table_back">
            <tr>
              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">大厅名称</td>
              <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="deptname" id="deptname" type="text" class="inputface" value=""/> 
              </td>
            <td class=tableheader1 width=10% height=30>管理部门代码</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="deptcode" id="deptcode" type="text" class="inputface" value=""/>
           </td>
           <td class=tableheader1 width=10% height=30>管理部门名称</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="deptcodename" id="deptcodename" type="text" class="inputface" value=""/>
           </td>
            </tr>
          </table>
	</form>
	<input name="button" type="button" class="button" value="查询" onClick="query()"/>
	<input name="button" type="button" class="button" value="添加" onClick="addDept()"/><br/><br/>
    <table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" >
	<tr>
	<td> 
		<ec:table items="list" var="list" 
					action="deptList.action"
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
				    <ec:column width="10%" property="deptname" title="大厅名称" style="text-align:center" editable="false" />
					<ec:column width="10%" property="depthall" title="大厅编号" style="text-align:center" editable="false" />
					<ec:column width="10%" property="deptcode" title="管理部门代码" style="text-align:center" editable="false" />
					<ec:column width="10%" property="deptcodename" title="管理部门名称" style="text-align:center" editable="false" />
					<ec:column width="10%" property="serversip" title="应用服务器 ip" style="text-align:center" editable="false" />
					<ec:column width="10%" property="website" title="访问地址" style="text-align:center" editable="false" />
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
	function addDept(){
		document.form1.action = "toAddDept.action"; 
		document.form1.submit();

	}
	function query(){
		document.form1.action = "deptList.action"; 
		document.form1.submit();
	}
	function deleteDept(id){
		if(confirm("确定删除吗?")){ 
			document.form1.action = "deleteDept.action?id="+id;
			document.form1.submit();
		}
	}
	function updateDept(id){ 
		document.form1.action = "toUpdateDept.action?id="+id; 
		document.form1.submit();
	}
	<%--	function openDept(website){
		window.open("http://127.0.0.1:8080")
	}
--%></script>
</html>