<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/webwork" prefix="ww"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>系统设置</title>
		<link href="css/mainframe.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr><td height="5" colspan="3"></td></tr>
			<tr>
				<td width="7" height="51"><img src="images/t-1.jpg" width="7" height="51"></td>
				<td height="51" valign="middle" background="images/t-2.jpg">
					<span style="font-family: '黑体', '宋体'; font-size: 18px; color: #fff; padding-left: 15px; letter-spacing: 10px; letter-spacing: 10px;">系统设置</span>
				</td>
				<td width="3" height="51"><img src="images/t-3.jpg" width="8" height="51"></td>
			</tr>
			<tr>
				<td width="7" background="images/t-4.jpg">&nbsp;</td>
				<td height="100" style="text-align: center" valign="middle" bgcolor="#f7f7f7">
					<table width="55%" border="0" cellpadding="1" cellspacing="1" class="table_back">
						<tr>
							<td colspan="2" width="78%" height="35" bgcolor="#F1F9FD" style="text-align:center">
							    &nbsp;<input type="button" value="初始化LED屏" onclick="initLED(1)" />&nbsp;&nbsp;&nbsp;
							    &nbsp;员工编号:<input id="operNum" size="3"/><input type="button" value="初始化号码缓存" onclick="initLED(2)" />
							</td>
						</tr>
						<tr>
							<td width="22%" height="35" bgcolor="#FFFFFF" class="tableheader1">是否启用取号拍照</td>
							<td width="78%" height="35" bgcolor="#F1F9FD">
								<input type="radio" name="isOpenIndexCamera" value="0" <ww:if test="${isOpenIndexCamera eq 0}">checked</ww:if>  onclick="isShowPz(0)"/>是
								<input type="radio" name="isOpenIndexCamera" value="1" <ww:if test="${isOpenIndexCamera eq 1}">checked</ww:if>  onclick="isShowPz(1)"/>否
							</td>
						</tr>
						<tr id="autoOrManual" <ww:if test="${isOpenIndexCamera eq 1}">style="display:none"</ww:if>>
							<td width="22%" height="35" bgcolor="#FFFFFF" class="tableheader1">取号拍照方式(手动和自动)</td>
							<td width="78%" height="35" bgcolor="#F1F9FD">
								<input type="radio" name="autoOrManualCapture" value="0" <ww:if test="${autoOrManualCapture eq 0}">checked</ww:if>/>自动
								<input type="radio" name="autoOrManualCapture" value="1" <ww:if test="${autoOrManualCapture eq 1}">checked</ww:if>/>手动
							</td>
						</tr>
						<tr id="resetSysConfig" >
							<input id="currentDept" type="hidden" value="${currentDept}"/>
							<td width="22%" height="35" bgcolor="#FFFFFF" class="tableheader1">重新加载系统设置到缓存</td>
							<td width="78%" height="35" bgcolor="#F1F9FD">
								 &nbsp;<input type="button" value="重载系统设置缓存" onclick="initSystemSet()" />
							    <font color="red">（只能重载本地系统设置）</font>
							</td>
						</tr>
					</table>
					<br/>
					<input name="bt1" class="button" type="button" value=" 保存 " onClick="save()" />
					<br/><br/>
				</td>
				<td width="8" background="images/t-5.jpg">&nbsp;</td>
			</tr>
			<tr>
				<td><img src="images/t-6.jpg" width="7" height="11"></td>
				<td height="11" background="images/t-7.jpg"></td>
				<td><img src="images/t-8.jpg" width="8" height="11"></td>
			</tr>
		</table>
	</body>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
	function save(){
		var isOpenIndexCamera = $("input:radio[name='isOpenIndexCamera']:checked").val();
	    var autoOrManualCapture = $("input:radio[name='autoOrManualCapture']:checked").val();
	    
	    var params = {isOpenIndexCamera:isOpenIndexCamera,autoOrManualCapture:autoOrManualCapture};
	    
	     $.post("system/updateClearCache.action", params, function(data)　{
	        if (eval(data)) {
	            window.alert("保存成功");
	        } else {
	            window.alert("保存失败，请稍候再试");
	        }
	    });
	}
	function initLED(val) {
	    var operNum = $("#operNum").val();
	    if (2 == val && "" == operNum) {
	        alert("请填写员工编号");
	        return;
	    }
	    $.post("system/initLED.action", {val: val, operNum: operNum}, function(data)　{
	        if (-1 == data) {
	            window.alert("初始化完成");
	        } else {
	            window.alert("有未能成功初始化LED屏");
	        }
	    });
	}
	function isShowPz(isValue){
		if("0"==isValue){
			$("#autoOrManual").css("display","")
		}else{
			$("#autoOrManual").css("display","none")
		}
	}
	//重新加载系统设置到缓存
	function initSystemSet(){
		var currentDept = $("#currentDept").val().split("-");
		var deptCode = currentDept[0];
		var deptHall = currentDept[1];
		$.ajax({
			type:"post",
			cache:false,
			url:"system/resetSystemSetInfo.action",
			data:{deptCode:deptCode,deptHall:deptHall},
			dataType:"json",
			success:function(data){
				if(data.resetMsg=="success"){
					alert("重载成功");
				}else{
					alert("重载失败")
				};
			}
		});
	}
	</script>
</html>