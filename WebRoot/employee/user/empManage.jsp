<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"%>
<%@ taglib uri="/webwork" prefix="ww"%>
<html>
	<head>
		<title>用户管理</title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
	<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/queue/plugs/ecside/css/ecside_style.css" />
	<script type="text/javascript" src="/queue/plugs/ecside/js/prototype_mini.js" ></script>	
	<script type="text/javascript" src="/queue/plugs/ecside/js/ecside.js" ></script>
	<script type="text/javascript" src="/queue/plugs/ecside/js/ecside_msg_utf8_cn.js" ></script>
	<script type="text/javascript" src="/queue/js/jquery.js" ></script>
	<script type="text/javascript" src="/queue/js/ajax.js" ></script> 
	
	<body>
		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td height="5" colspan="3">
					<input type="hidden" id="role" name="role" value="${role}" />
				</td>
			</tr>
			<tr>
				<td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
				<td height="51" valign="middle"  background="/queue/images/t-2.jpg">
					<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">${param.flag=='1' ? '用户管理' : '员工管理' }</span>
				</td>
				<td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
			</tr>
			<tr>
				<td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
				<td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
					<form action="" method="post" name="form1">
							<table width="80%" border="0" cellpadding="1" cellspacing="1" class="table_back">
								<tr>
									<td class=tableheader1 align=middle width=10% height=30>
										账号：
									</td>
									<td align=middle width=150 bgColor=#f1f9fd height=30>
										<input type="text" id="code" name="code" class="inputface" style="height: 22px;line-height: 21px;" />
										<input type="hidden" id="flag" name="flag" value="${param.flag}"/>
									</td>
									<td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">
										姓名：
									</td>
									<td align=middle width=150 bgColor=#f1f9fd height=30>
										<input type="text" id="name" name="name" class="inputface" style="height: 22px;line-height: 21px;"  />
									</td>
									<td class=tableheader1 align=middle width=10% height=30>
										是否有效：
									</td>
									<td align=middle width=150 bgColor=#f1f9fd height=30>
										<select name="status">
											<option value="">请选择...</option>
											<option value="1">有效</option>
											<option value="0">无效</option>
										</select>
									</td>
								</tr>
							</table>
							<input name="_query" type="button" class="button" value="查询" onClick="query()" />
							<ww:if test="${param.flag=='1'}">
								<input id="_add" name="_add" type="button" class="button" value="添加" onClick="add()" />
							</ww:if>
							<ww:if test="${param.flag=='0'}">
							<input id="addstaff" name="addstaff" type="button" class="button" value="添加员工" onClick="adds()"/>
							</ww:if>
							<br />
					</form>

					<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
								<ec:table items="empList" var="empList" action="employee!empManage.action?flag=${param.flag}"
									editable="false" width="100%" listWidth="100%" classic="true"
									doPreload="false" useAjax="true" resizeColWidth="false"
									sortable="false" batchUpdate="false" nearPageNum="0"
									toolbarLocation="top" pageSizeList="15" showPrint="false"
									filterable="false">
									<ec:row style="text-align:center">
										<ec:column width="1%" style="text-align: center;" property="_0" title="序号"  value="${GLOBALROWCOUNT}" editable="false" sortable="false" />
										<ec:column width="5%" property="code" title="账号" style="text-align:center" editable="false" />
										<ec:column width="5%" property="name" title="姓名" style="text-align:center" editable="false" />
										<ww:if test="${param.flag=='0'}">
											<ec:column width="5%" property="loginIp" title="关联ip" style="text-align:center" editable="false" />
										</ww:if>
										<ww:if test="${param.flag=='1'}">
											<ec:column width="5%" property="moduleRdac" title="角色" style="text-align:center" editable="false" />
											<ec:column width="5%" property="police" title="是否警员" style="text-align:center" editable="false" />
											<ec:column width="5%" property="sex" title="性别" style="text-align:center" editable="false" />
										<ww:if test="${role ne '2'}">
											<ec:column width="5%" property="fmt_status" title="状态" style="text-align:center" editable="false" />
											<%-- <ec:column width="5%" property="resetPwd" title="密码复位" style="text-align:center" editable="false" /> --%>
										</ww:if>
										</ww:if>
										<ww:else>
											<ec:column width="5%" property="comments" title="座右铭" style="text-align:center;" editable="false" />
											<ec:column width="5%" property="department" title="所属机构" style="text-align:center" editable="false" />
										</ww:else>
										<ww:if test="${role ne '2'}">
											<ec:column width="5%" property="operate" title="操作" style="text-align:center" editable="false" />
										</ww:if>
									</ec:row>
									<ww:if test="${msg != null}">
										<ec:extendrow>
											<tr style="background-color: #ffeedd" title="">
												<td colspan="10" style="text-align: center">
													<strong>
														<font face='宋体' color='#5b92fa'>
															<c:out value="${msg}" />
														</font>
													</strong>
												</td>
											</tr>
										</ec:extendrow>
									</ww:if>
								</ec:table>
								<br>
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
<script type="text/javascript">

	window.onload = function(){
		var roles = $("#role").val();
		if(roles=='2'){
			$("#_add").attr("disabled","disabled");
		}
	}
	
	<%-- 查询 --%>
	function query(){
		var flag = $("#flag").val();
		form1.action = "employee!empManage.action?flag="+flag;
		form1.submit();
	}
	
	<%-- 修改用户状态 --%>
	function editStatus(id,status){
		if(!confirm("确定要置为"+(status==1 ? '失效' : '有效')+"吗？")){
			return false;
		}
		var url = 'employee!updateEmp.action?id='+id+'&status='+status;
		var result = new MyJqueryAjax(url).request();
		if(result!=0){
			alert('操作成功!');
			form1.action = "employee!empManage.action?flag=1";
			form1.submit();
		}else{
			alert('操作失败，请重试');
			return false;
		}
	}
	
	<%-- 密码重置 --%>
	function resetPwd(id){
		if(!confirm("确定要重置密码吗？")){
			return false;
		}
		var url = 'employee!updateEmp.action?id='+id+'&password=123456&status=3';
		var result = new MyJqueryAjax(url).request();
		if(result!=0){
			alert('          操作成功！\n密码已经重置为:123456');
		}else{
			alert('操作失败，请重试！');
			return false;		
		}
	}
	
	<%-- 删除操作 --%>
	function deleteEmp(id){
		if(!confirm("确定要删除吗？")){
			return false;
		}
		var url = "employee!deleteEmp.action?id="+id;
		var result = new MyJqueryAjax(url).request();
		if(result!=0){
			alert('操作成功！');
			form1.action = "employee!empManage.action?flag=${param.flag}";
			form1.submit();
		}else{
			alert('数据操作失败，请重试！');
			return false;
		}
	}
	
	<%-- 新增操作 --%>
	function add(){
		form1.action="employee!editEmp.action?method=add";
		form1.submit();
	}
		<%-- 新增员工 --%>
	function adds(){
		form1.action="employee!editStaff.action";
		form1.submit();
	}
</script>
