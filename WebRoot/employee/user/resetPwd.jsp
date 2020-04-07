<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"%>
<%@ taglib uri="/webwork" prefix="ww"%>
<html>
<head>
<title>密码修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/queue/js/jquery.js"></script>
<script type="text/javascript" src="/queue/js/ajax.js"></script>
</head>
<body>
	<form action="" name="form1" method="post">
		<input type="hidden" name="id" value="${id }" />
		<table width="98%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="5" colspan="3"></td>
			</tr>
			<tr>
				<td width="7" height="51"><img src="/queue/images/t-1.jpg"
					width="7" height="51"></td>
				<td height="51" valign="middle" background="/queue/images/t-2.jpg">
					<span
					style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">密码修改</span>
				</td>
				<td width="3" height="51"><img src="/queue/images/t-3.jpg"
					width="8" height="51"></td>
			</tr>
			<tr>
				<td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
				<td height="100" style="text-align: center" valign="middle"
					bgcolor="#f7f7f7">
					<table width="50%" border="0" cellpadding="1" cellspacing="1"
						class="table_back">
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">账号</td>
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <input
								type="text" id="code" name="code" style="width: 130px"
								value="${code }" disabled="disabled" />
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">原密码</td>
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <input
								type="password" id="_before" name="_before" style="width: 130px"
								onblur="checkBeforePwd();" /> <input type="hidden" id="isRight"
								value="false" /> <font style="font-size: 12px;color: red;"
								id="before_pwd"></font>
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">新密码</td>
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <input
								type="password" name="password" id="password"
								style="width: 130px" /> <font
								style="font-size: 12px;color: red;" id="pwd_new"></font>
							</td>
						</tr>
						<tr>
							<td width="20%" height="35" bgcolor="#FFFFFF"
								class="tableheader1">确认密码</td>
							<td width="80%" height="35" bgcolor="#F1F9FD">&nbsp; <input
								type="password" name="pwd" id="pwd" style="width: 130px" /> <font
								style="font-size: 12px;color: red;" id="pwd_qr"></font>
							</td>
						</tr>
					</table> <br />
				<br /> <input name="bt1" class="button" type="button" value="修 改"
					onClick="updateStaff()" /> <br />
				<br />
				</td>
				<td width="8" background="/queue/images/t-5.jpg">&nbsp;</td>
			</tr>
			<tr>
				<td><img src="/queue/images/t-6.jpg" width="7" height="11"></td>
				<td height="11" background="/queue/images/t-7.jpg"></td>
				<td><img src="/queue/images/t-8.jpg" width="8" height="11"></td>
			</tr>
		</table>
	</form>

	<script type="text/javascript">
		function updateStaff() {
			if ($('#_before').val() == '') {
				$('#before_pwd').html("原密码不能为空！");
				$('#_before').focus();
				return;
			}
			if ($('#password').val() == '') {
				$('#pwd_new').html("新密码不能为空！");
				$('#password').focus();
				return;
			}
			if ($('#pwd').val() == '') {
				$('#pwd_qr').html("确认密码不能为空！");
				$('#pwd').focus();
				return;
			}
			if ($('#password').val() != $('#pwd').val()) {
				$('#pwd_qr').html("两次输入密码不一致！");
				$('#pwd').focus();
				return;
			}
			if ('false' == $('#isRight').val()) {
				$('#before_pwd').html("原密码输入错误！");
				return;
			}
			var pas = $('#password').val();
			var paslen = pas.length;
			if (8 > paslen) {
				$('#pwd_qr').html("密码长度不能少于【8位数】!");
				$('#password').focus();
				return false;
			}
			var regex = new RegExp(
					'(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,30}');
			if (!regex.test($('#password').val())) {
				$('#pwd_qr').html("密码必须包含【英文字母】、【符号】、【数字】!");
				$('#password').focus();
				return false;
			}
			form1.action = "employee!updatePwd.action";
			form1.submit();
		}

		function checkBeforePwd() {
			if ($('#_before').val() == '') {
				$('#before_pwd').html("原密码不能为空！");
				$('#_before').focus();
				return;
			}
			var id = $("input[name='id']").val();
			var _before = $('#_before').val();
			var url = "employee!checkBeforePwd.action?id=" + id + '&_before='
					+ encodeURIComponent(_before);
			var result = new MyJqueryAjax(url).request();
			if (result > 0) {
				$('#before_pwd').html("原密码输入正确！");
				$('#isRight').val("true");
			} else {
				$('#before_pwd').html("原密码输入错误！");
				$('#isRight').val("false");
			}
		}
	</script>
</body>
</html>