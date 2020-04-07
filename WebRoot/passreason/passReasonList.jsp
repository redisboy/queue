<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"%>
<html>
	<head>
		<title>过号原因设置</title>
	</head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css"
		href="/queue/plugs/ecside/css/ecside_style.css" />
	<script type="text/javascript"
		src="/queue/plugs/ecside/js/prototype_mini.js"></script>
	<script type="text/javascript" src="/queue/plugs/ecside/js/ecside.js"></script>
	<script type="text/javascript"
		src="/queue/plugs/ecside/js/ecside_msg_utf8_cn.js"></script>
	<script type="text/javascript" src="/queue/js/jquery.js"></script>
	<body onload="currentDept()">
		<table width="98%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<input id="currentDept" type="hidden" value="${currentDept}"/>
			<tr>
				<td height="5" colspan="3"></td>
			</tr>
			<tr>
				<td width="7" height="51">
					<img src="/queue/images/t-1.jpg" width="7" height="51">
				</td>
				<td height="51" valign="middle" background="/queue/images/t-2.jpg">
					<span
						style="font-family: '黑体', '宋体'; font-size: 18px; color: #fff; padding-left: 15px; letter-spacing: 10px; letter-spacing: 10px;">过号原因设置</span>
				</td>
				<td width="3" height="51">
					<img src="/queue/images/t-3.jpg" width="8" height="51">
				</td>
			</tr>
			<tr>
				<td width="7" background="/queue/images/t-4.jpg">
					&nbsp;
				</td>
				<td height="100" style="text-align: center" valign="middle"
					bgcolor="#f7f7f7">
					<c:if test="${role eq 0}">
						大厅名称:
						<select id="codeAndHall" name="codeAndHall" onchange="selectHallName()">
							<option value="-" selected="selected">全部大厅</option>
							<c:forEach items="${deptInfo}" var="item" >
								<option id="${item.key}" value="${item.key}">${item.value}</option>
							</c:forEach>
						</select>
					</c:if>
					<br/><br/>
					<input name="button" type="button" class="button" value="添加" onClick="add()"/>
<!--					<form action="" method="post" name="form1">-->
<!--						<table width="80%" border="0" cellpadding="1" cellspacing="1"-->
<!--							class="table_back">-->
<!--							<tr>-->
<!--								<td width="10%" height="25" bgcolor="#FFFFFF"-->
<!--									class="tableheader1">-->
<!--									大厅名称-->
<!--								</td>-->
<!--								<td width=150 bgColor=#f1f9fd height=30>-->
<!--									&nbsp;-->
<!--									<input name="deptname" id="deptname" type="text"-->
<!--										class="inputface" value="" />-->
<!--								</td>-->
<!--								<td class=tableheader1 width=10% height=30>-->
<!--									管理部门代码-->
<!--								</td>-->
<!--								<td width=150 bgColor=#f1f9fd height=30>-->
<!--									&nbsp;-->
<!--									<input name="deptcode" id="deptcode" type="text"-->
<!--										class="inputface" value="" />-->
<!--								</td>-->
<!--								<td class=tableheader1 width=10% height=30>-->
<!--									管理部门名称-->
<!--								</td>-->
<!--								<td width=150 bgColor=#f1f9fd height=30>-->
<!--									&nbsp;-->
<!--									<input name="deptcodename" id="deptcodename" type="text"-->
<!--										class="inputface" value="" />-->
<!--								</td>-->
<!--							</tr>-->
<!--						</table>-->
<!--					</form>-->
<!--					<input name="button" type="button" class="button" value="查询"-->
<!--						onClick="query()" />-->
<!--					<input name="button" type="button" class="button" value="添加"-->
<!--						onClick="add()" />-->
					<br />
					<br />
					<table width="98%" align="center" border="0" cellpadding="0"
						cellspacing="0">
						<tr>
							<td>
								<ec:table items="list" var="list" action="selectGHReason.action"
									editable="false" width="100%" listWidth="100%" classic="true"
									doPreload="false" useAjax="false" resizeColWidth="false"
									sortable="false" batchUpdate="false" nearPageNum="0"
									toolbarLocation="top" pageSizeList="15" showPrint="false"
									filterable="false">
									<ec:row style="text-align:center">
										<ec:column width="10%" property="deptHall" title="大厅名称"
											style="text-align:center" editable="false" />
										<ec:column width="10%" property="tjrq" title="添加日期"
											style="text-align:center" editable="false" />
										<ec:column width="10%" property="reason" title="过号原因"
											style="text-align:center" editable="false" />
										<ec:column width="10%" property="opera" title="操   作"
											style="text-align:center" editable="false" />
									</ec:row>
								</ec:table>
								<br>
							</td>
						</tr>
					</table>
					<br>
				</td>
				<td width="8" background="/queue/images/t-5.jpg">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					<img src="/queue/images/t-6.jpg" width="7" height="11">
				</td>
				<td height="11" background="/queue/images/t-7.jpg"></td>
				<td>
					<img src="/queue/images/t-8.jpg" width="8" height="11">
				</td>
			</tr>
		</table>
	</body>
	<script type="text/javascript">
	//页面打开 使相应下拉框选项选中
	function currentDept(){
		var currentDept = $("#currentDept").val();
		$("option[value="+currentDept+"]").attr("selected",true);
		
	}
	function add(){
		var codeAndHall = $("#codeAndHall");
		if(codeAndHall.length==0){
			window.location.href="addPassReason.action";
		}else{
			var value = $("#codeAndHall option:selected").attr("value");
			var infoArray = value.split("-");
			window.location.href="addPassReason.action?deptCode="+infoArray[0]+"&deptHall="+infoArray[1];
		}

	}
	function query(){
		document.form1.action = "deptList.action"; 
		document.form1.submit();
	}
	function deletePassReason(id){
		if(confirm("确定删除吗?")){ 
			window.location.href="deletePassReason.action?id="+id;
			//document.form1.action = "deletePassReason.action?id="+id;
			//document.form1.submit();
		}
	}
	function updatePassReason(id){ 
		window.location.href="toUpdatePassReason.action?id="+id; 
		//document.form1.action = "toUpdatePassReason.action?id="+id; 
		//document.form1.submit();
	}
	//根据选择大厅选择 查询数据
	function selectHallName(){
			var value = $("#codeAndHall option:selected").attr("value");
			var infoArray = value.split("-");
			window.location.href="selectPassReason.action?deptCode="+infoArray[0]+"&deptHall="+infoArray[1];
	}
	</script>
</html>