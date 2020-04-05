﻿<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="/webwork" prefix="ww"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>更改窗口</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
<meta http-equiv="expires" content="0">
<link href="css/mainframe.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<table width="98%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="5" colspan="3"></td>
		</tr>
		<tr>
			<td width="7" height="51"><img src="images/t-1.jpg" width="7"
				height="51"></td>
			<td height="51" valign="middle" background="images/t-2.jpg"><span
				style="font-family: '黑体', '宋体'; font-size: 18px; color: #fff; padding-left: 15px; letter-spacing: 10px; letter-spacing: 10px;">更改窗口</span>
			</td>
			<td width="3" height="51"><img src="images/t-3.jpg" width="8"
				height="51"></td>
		</tr>
		<tr>
			<td width="7" background="images/t-4.jpg">&nbsp;</td>
			<td height="100" style="text-align: center" valign="middle"
				bgcolor="#f7f7f7">
				<form action="" name="form1" method="post">
					<input type="hidden" id="type" value="1" />
					<!-- 用于判断是否主窗口标识 -->
					<input type="hidden" name="id" value="${screen.id}" />
					<!-- 作用1：判断填写的IP是否与此IP相同，相同则不做判断；作用2：当此窗口变为主窗口时，通过此IP删除缓存中的窗口 -->
					<input type="hidden" id="hip" name="oldIP" value="${screen.barip}" />
					<!-- 窗口号与地址不可修改，设置为隐藏域 -->
					<input type="hidden" name="windowId" value="${screen.windowId}" />
					<input type="hidden" id="address" name="address"
						value="${screen.address}" />
					<!-- 可办业务的隐藏域 -->
					<input type="hidden" id="user_role_ids"
						value="${screen.businessid}" /> <input type="hidden"
						id="isUseOldDevice" value="${isUseOldDevice}" />
					<table id="myTable" width="60%" border="0" cellpadding="1"
						cellspacing="1" class="table_back">
						<!-- 刘东 add -->
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">是否主窗口</td>
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <input
								type="radio" name="mainWindow" value="0"
								onclick="isMainWindow(this.value)" />是 <input type="radio"
								name="mainWindow" value="1" onclick="isMainWindow(this.value)" />否
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">窗口号</td>
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <input
								disabled style="width: 100px" value="${screen.windowId}"
								onkeyup="clearNoNum(this)" onafterpaste="clearNoNum(this)" />
								&nbsp;<font id="winc" color="#ff0000">*请录入数字</font>
							</td>
						</tr>
						<tr id="begin">
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">COM口</td>
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <select
								name="com1" id="com1">
									<option value="">-请选择-</option>
									<option value="1">COM-1</option>
									<option value="2">COM-2</option>
									<option value="3">COM-3</option>
									<option value="4">COM-4</option>
									<option value="5">COM-5</option>
									<option value="6">COM-6</option>
									<option value="7">COM-7</option>
									<option value="8">COM-8</option>
									<option value="9">COM-9</option>
									<option value="10">COM-10</option>
							</select>
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">窗口高</td>
							<td width="20%" height="35" bgcolor="#F1F9FD">&nbsp; <input
								name="ledWindowHeight" id="ledWindowHeight" maxlength="10"
								value="${screen.ledWindowHeight}" style="width: 100px"
								onkeyup="clearNoNum(this)" onafterpaste="clearNoNum(this)" />
								&nbsp;<font id="winc" color="#ff0000">*请录入数字</font>
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">窗口宽</td>
							<td width="20%" height="35" bgcolor="#F1F9FD">&nbsp; <input
								name="ledWindowWidth" id="ledWindowWidth" maxlength="10"
								value="${screen.ledWindowWidth}" style="width: 100px"
								onkeyup="clearNoNum(this)" onafterpaste="clearNoNum(this)" />
								&nbsp;<font id="winc" color="#ff0000">*请录入数字</font>
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">LED屏地址</td>
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <input
								disabled style="width: 100px" value="${screen.address}"
								onkeyup="clearNoNum(this)" onafterpaste="clearNoNum(this)" />
								&nbsp;<font id="barinfo" color="#ff0000">*请录入数字</font>
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">对齐方式</td>
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <select
								name="align" id="align">
									<option value="">-请选择-</option>
									<option value="1">左对齐</option>
									<option value="2">居中</option>
									<option value="3">右对齐</option>
							</select>
							</td>
						</tr>
						<!--							<tr>-->
						<!--								<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">停顿</td>-->
						<!--								<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;-->
						<!--									<input name="stoptime" id="stoptime" style="width: 100px" value="${screen.stoptime}"-->
						<!--										onkeyup="clearNoNum(this)" onafterpaste="clearNoNum(this)" />-->
						<!--									&nbsp;<font color="#ff0000">*请录入数字</font>-->
						<!--								</td>-->
						<!--							</tr>-->
						<!--							<tr>-->
						<!--								<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">间隔</td>-->
						<!--								<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;-->
						<!--									<input name="interval" id="interval" style="width: 100px" value="${screen.interval}"-->
						<!--										onkeyup="clearNoNum(this)" onafterpaste="clearNoNum(this)" />-->
						<!--									&nbsp;<font color="#ff0000">*请录入数字</font>-->
						<!--								</td>-->
						<!--							</tr>-->
						<!--							<tr>-->
						<!--								<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">字数</td>-->
						<!--								<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;-->
						<!--									<input name="word" id="word" style="width: 100px" value="${screen.word}"-->
						<!--										onkeyup="clearNoNum(this)" onafterpaste="clearNoNum(this)" />-->
						<!--									&nbsp;<font color="#ff0000">*请录入数字</font>-->
						<!--								</td>-->
						<!--							</tr>-->
						<!--							<tr>-->
						<!--								<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">速度</td>-->
						<!--								<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;-->
						<!--									<input name="speed" id="speed" style="width: 100px" value="${screen.speed}"-->
						<!--										onkeyup="clearNoNum(this)" onafterpaste="clearNoNum(this)" />-->
						<!--									&nbsp;<font color="#ff0000">*请录入数字(0-255)</font>-->
						<!--								</td>-->
						<!--							</tr>-->
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">BarIP地址</td>
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <input
								name="ipaddress" id="ipaddress" maxlength="15"
								style="width:100px" value="${screen.barip}" /> &nbsp;<font
								id="ipinfo" color="#ff0000"></font>
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">窗口屏IP地址</td>
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <input
								name="ckip" id="ckip" maxlength="15"
								style="width:100px" value="${screen.ckip}" /> &nbsp;<font
								 color="#ff0000">没有时不填,窗口选择双行!</font>
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">默认评价</td>
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <select
								name="defaultvalue" id="defaultvalue">
									<option value="">-请选择-</option>
									<c:forEach var="Value" items="${list}" varStatus="vss">
										<option value="${Value.id}"
											${Value.id eq screen.valuemust ? 'selected' : ''}>
											${Value.name}</option>
									</c:forEach>
							</select>
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">硬件评价器地址</td>
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <input
								name="com2" id="com2" style="width: 100px"
								value="${screen.com2}" onkeyup="clearNoNum(this)"
								onafterpaste="clearNoNum(this)" /> &nbsp;<font id="barinfo"
								color="#ff0000">*请录入数字</font>
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">可办业务</td>
							<td width="80%" height="35" bgcolor="#F1F9FD"><c:forEach
									var="Business" items="${businessList}" varStatus="vs">
									<input type="checkbox" name="queue" value="${Business.id}" />${Business.id}：${Business.name}
									</c:forEach> <input type="checkbox" title="全选"
								onclick="checkboxCheckedAll('queue')" />全选</td>
						</tr>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">业务优先级</td>
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <input
								name="priority" id="priority" style="width: 300px"
								value="${screen.priority}"
								onkeyup="this.value=this.value.replace(/[^0-9,;]/g, '')" /> <br />&nbsp;&nbsp;<font
								color="#ff0000">优先级编号之间用";"分隔，无优先级编号用","分隔</font> <br />&nbsp;&nbsp;例：1：业务1、2：业务2、3：业务3、4：业务4&nbsp;&nbsp;&nbsp;优先级：1;2;3,4
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">单双行显示</td>
							<td width="80%" height="35" bgcolor="#F1F9FD"
								onclick="changeColor()">&nbsp; <input type="radio"
								name="showNum" value="1" />单行 <input type="radio"
								name="showNum" value="2" />双行 <input type="radio"
								name="showNum" value="3" />三行 <input type="radio"
								name="showNum" value="4" />四行
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">字体颜色</td>
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <select
								name="color" id="color">
									<option value="1" checked>红色</option>
									<option value="2">绿色</option>
									<option value="3">黄色</option>
							</select> <select name="color1" id="color1" style="display:none">
									<option value="1" checked>红色</option>
									<option value="2">绿色</option>
									<option value="3">黄色</option>
							</select> <select name="color2" id="color2" style="display:none">
									<option value="1" checked>红色</option>
									<option value="2">绿色</option>
									<option value="3">黄色</option>
							</select> <select name="color3" id="color3" style="display:none">
									<option value="1" checked>红色</option>
									<option value="2">绿色</option>
									<option value="3">黄色</option>
							</select>
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">使用的字库</td>
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <select
								name="lattice" id="lattice">
									<option value="16"
										<ww:if test="${screen.lattice=='16'}">selected</ww:if>>16</option>
									<option value="32"
										<ww:if test="${screen.lattice=='32'}">selected</ww:if>>32</option>
							</select>
							</td>
						</tr>
						<%
							if (request.getAttribute("userCode").equals("0")) {
						%>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">是否开启接口</td>
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <input
								type="radio" name="openInterFace" value="0" />是 <input
								type="radio" name="openInterFace" value="1" />否
							</td>
						</tr>
						<%
							}
						%>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">是否开启评价器</td>
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <input
								type="radio" name="isOpenOldDevice" value="0" />是 <input
								type="radio" name="isOpenOldDevice" value="1" checked />否
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">是否可以处理疑难业务</td>
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <input
								type="radio" name="isOpenInformation" value="0" />是 <input
								type="radio" name="isOpenInformation" value="1" checked />否
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">默认显示内容</td>
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <input
								name="content" id="content" style="width: 200px"
								value="${screen.content}" />
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">完成评价<br />提示信息
							</td>
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <input
								name="nextWindow" id="nextWindow" maxlength="40"
								style="width: 200px" value="${screen.nextWindow}" />
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">窗口滚动内容
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <input
								name="windowGDContent" id="windowGDContent" maxlength="40"
								style="width: 200px" value="${screen.windowGDContent}" /> <input
								type="radio" value="0" name="isgd" checked="checked" />是&nbsp;
								<input type="radio" value="1" name="isgd" />否
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">第三行内容</td>
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <input
								name="content3" id="content3" style="width: 200px"
								value="${screen.content3}" /> <input type="radio" value="0"
								name="threegd" checked="checked" />否&nbsp; <input type="radio"
								value="1" name="threegd" />是
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">第四行内容</td>
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <input
								name="content4" id="content4" style="width: 200px"
								value="${screen.content4}" /> <input type="radio" value="0"
								name="fourgd" checked="checked" />否&nbsp; <input type="radio"
								value="1" name="fourgd" />是
							</td>
						</tr>
					</table>
				</form> <input name="bt1" class="button" type="button" value=" 更改 "
				onClick="updateWindowByCode(1)" /> <input name="bt2" class="button"
				type="button" value=" 返回 " onClick="window.history.back(-1);" /> <br>
			</td>
			<td width="8" background="images/t-5.jpg">&nbsp;</td>
		</tr>
		<tr>
			<td><img src="images/t-6.jpg" width="7" height="11"></td>
			<td height="11" background="images/t-7.jpg"></td>
			<td><img src="images/t-8.jpg" width="8" height="11"></td>
		</tr>
	</table>
	<div id="divPop" style="background-color:#f0f0f0; display:none;">
		<iframe id="divIframe"
			style="position:absolute;z-index:9;background-color:#f0f0f0;"
			frameborder="0"></iframe>
	</div>
</body>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="window/js/setWindow.js"></script>
<script type="text/javascript">
	toSelected("com1", "${screen.comnum}");
	toSelected("color", "${screen.color}");
	toSelected("color1", "${screen.color1}");
	toSelected("color2", "${screen.color2}");
	toSelected("color3", "${screen.color3}");
	toSelected("align", "${screen.align}");
	if (1 == ${screen.showNum}) {
		$("input[name='showNum']").get(0).checked = true;
		$("#color1").css("display", "none")
		$("#color2").css("display", "none")
		$("#color3").css("display", "none")
	} else if (2 == ${screen.showNum}) {
		$("input[name='showNum']").get(1).checked = true;
		$("#color1").css("display", "")
		$("#color2").css("display", "none")
		$("#color3").css("display", "none")
	} else if (3 == ${screen.showNum}) {
		$("input[name='showNum']").get(2).checked = true;
		$("#color1").css("display", "")
		$("#color2").css("display", "")
		$("#color3").css("display", "none")
	} else if (4 == ${screen.showNum}) {
		$("input[name='showNum']").get(3).checked = true;
		$("#color1").css("display", "")
		$("#color2").css("display", "")
		$("#color3").css("display", "")
	}
<%if (request.getAttribute("userCode").equals("0")) {%>
	if (0 == ${screen.openInterFace}) {
		$("input[name='openInterFace']").get(0).checked = true;
	} else {
		$("input[name='openInterFace']").get(1).checked = true;
	}
<%}%>
	if (0 == ${screen.isOpenOldDevice}) {
		$("input[name='isOpenOldDevice']").get(0).checked = true;
	} else {
		$("input[name='isOpenOldDevice']").get(1).checked = true;
	}
	if (0 == ${screen.isOpenInformation}) {
		$("input[name='isOpenInformation']").get(0).checked = true;
	} else {
		$("input[name='isOpenInformation']").get(1).checked = true;
	}
	if (0 == ${screen.isgd}) {
		$("input[name='isgd']").get(0).checked = true;
	} else {
		$("input[name='isgd']").get(1).checked = true;
	}
	if (0 == ${screen.threegd}) {
		$("input[name='threegd']").get(0).checked = true;
	} else {
		$("input[name='threegd']").get(1).checked = true;
	}
	if (0 == ${screen.fourgd}) {
		$("input[name='fourgd']").get(0).checked = true;
	} else {
		$("input[name='fourgd']").get(1).checked = true;
	}
	document.getElementById("priority").onblur = function() {
		//document.getElementById("priority").value
		var business = document.getElementsByName("queue")
		var priority = document.getElementById("priority").value
		var priority1 = document.getElementById("priority").value.split("")
		var priority2 = document.getElementById("priority").value.split(",")
		var priority3 = document.getElementById("priority").value.split(";")
		var a = 0;
		if (priority.indexOf(";") > -1) {
			if (priority.indexOf(",") > -1) {
				alert("输入的格式有误（输入的内容中“;”与“,”不能在同一窗口出现）")
			} else {
				if (priority1[0] == ";"
						|| priority1[priority1.length - 1] == ";") {
					alert("输入的格式有误（“;”是否出现在内容首尾）");
				} else {
					if (priority.indexOf(";;") > -1) {
						alert("输入的格式有误（查看;是否有连续出现）");
					} else {

						for (var i = 0; i < priority3.length; i++) {
							for (var j = 0; j < business.length; j++) {
								if (business[j].value == priority3[i]) {
									a++;
								}
							}
						}
						if (a != priority3.length) {
							alert("输入的格式有误（输入的业务id不存在）");
						}
					}

				}
			}
		} else {
			if (priority1[0] == "," || priority1[priority1.length - 1] == ",") {
				alert("输入的格式有误（“,”是否出现在内容首尾）");
			} else {
				if (priority.indexOf(",,") > -1) {
					alert("输入的格式有误（查看,是否有连续出现）");
				} else {

					for (var i = 0; i < priority2.length; i++) {
						for (var j = 0; j < business.length; j++) {
							if (business[j].value == priority2[i]) {
								a++;
							}
						}
					}
					if (a != priority2.length) {
						alert("输入的格式有误（输入的业务id不存在）");
					}
				}

			}
		}
	}
</script>
</html>