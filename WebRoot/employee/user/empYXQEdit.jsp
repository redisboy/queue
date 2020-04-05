<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"%>
<%@ taglib uri="/webwork" prefix="ww"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
	<head>
		<title>新增用户信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="/queue/js/jquery.js"></script>
		<script type="text/javascript" src="/queue/js/ajax.js"></script>
		<style type="text/css">
			.leg {
				font-size: 13px;
				color: #4482d3;
				font-weight: bold;
			}
			
			fieldset {
				width: 99.5%;
			}
			
			div {
				width: 97%;
				margin-top: 5px;
				font-size: 13px;
				text-align: left;
			}
			
			span {
				float: left;
				margin-left: 20px;
			}
		</style>
	</head>
	<body>
		<form action="" name="form1" method="post">
			<input type="hidden" id="method" name="method" value="${method }"/>
			<input type="hidden" id="flag_id" name="flag_id" value="${id }"/>
			<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td height="5" colspan="3">
						<input type="hidden" name="role" id="role" value="${role}" />
					</td>
				</tr>
				<tr>
					<td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
				    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
				    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">用户有效期管理</span>
				    </td>
				    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
				</tr>
				<tr>
					<td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
					<td height="100" style="text-align: center" valign="middle" bgcolor="#f7f7f7">
						<ww:if test="${param.method=='add'}">
							<fieldset>
								<legend class="leg">
									用户信息
								</legend>
								<table width="50%" border="0" cellpadding="1" cellspacing="1" class="table_back">
									<tr>
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											账号
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input type="text" id="code" name="code" style="width: 100px" maxlength="12" />
											&nbsp;
											<font color="#ff0000">*最长不超过12个字符</font>
										</td>
									</tr>
									<tr>
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											所属部门
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input name="dept" id="dept" maxlength="15" style="width: 100px"  />
											&nbsp;
											<font id="barinfo" color="#ff0000"></font>
										</td>
									</tr>
									<tr>
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											姓名
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input name="name" id="name" maxlength="15" style="width: 100px" />
											&nbsp;
											<font id="barinfo" color="#ff0000">*请录入姓名</font>
										</td>
									</tr>
									<tr>
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											性别
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<select id="sex" name="sex" style="width: 100px;">
												<option value="">请选择...</option>
												<option value="男">男</option>
												<option value="女">女</option>
											</select>
											&nbsp;
											<font color="#ff0000">*请选择性别</font>
										</td>
									</tr>
									<tr>
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											是否警员
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<select id="police" name="police" style="width: 100px;">
												<option value="0">是</option>
												<option value="1">否</option>
											</select>
											&nbsp;
											<font color="#ff0000">*请选择</font>
										</td>
									</tr>
									<tr>
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											身份证号码
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input name="idnumber" id="idnumber" maxlength="15" style="width: 100px" />
											&nbsp;
											<font color="#ff0000">*请录入身份证号码</font>
										</td>
									</tr>
									<tr>
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											密码
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input type="password" name="password" id="password" maxlength="12" style="width: 100px" />
											&nbsp;
											<font color="#ff0000">*由字母、数字、符号组合，长度至少8位(有效期为180天)</font>
										</td>
									</tr>
	
									<tr>
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											确认密码
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input type="password" name="pwd" id="pwd" maxlength="12" style="width: 100px" />
											&nbsp;
											<font color="#ff0000">*请再次录入密码</font>
										</td>
									</tr>
									<tr>
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											用户有效期
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input name="yhyxq" id="yhyxq" style="width: 100px" />
											&nbsp;
											<font color="#ff0000">*格式为2018-12-31(最后登录日期为2018.12.31)</font>
										</td>
									</tr>
									<tr>
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											可登录计算机ip
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input name="kdljsjip" id="kdljsjip" style="width: 100px" />
											&nbsp;
											<font color="#ff0000">*多个ip间用英文逗号隔开</font>
										</td>
									</tr>
									<tr>
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											可登录时间段
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input name="kdlsjd" id="kdlsjd" style="width: 100px" />
											&nbsp;
											<font color="#ff0000">*格式为9-17(代表早9到晚5)</font>
										</td>
									</tr>
									<tr id="roletr">
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											用户角色
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input type="radio" name="roleradio" id="roleradio" value="1" />系统管理员
											&nbsp;
											<input type="radio" name="roleradio" id="roleradio" value="2" checked="checked"/>普通用户
											&nbsp;
										</td>
									</tr>
								</table>
							</fieldset>
							<fieldset>
							<legend class="leg">
								用户权限设置
							</legend>
							<table width="50%" style="border: 1 solid #4482D3;" align="center" cellpadding="0" cellspacing="1">
								<tr>
									<td>
										<c:forEach var="menu" items="${menuList}" varStatus="vs">
											<ww:if test="${menuList[vs.index-1].menuid==menu.menuid}">
												<div style="width: 30%; display: inline; height: 20; font-size: 12;">
													<input type="checkbox" id="power" name="power" value="${menu.levelid }"/>
													${menu.leveltext }
												</div>
											</ww:if>
											<ww:else>
												<div style="${vs.index==0 ? 'margin-top: -1px;' : '' }text-align: left; background-color: #D6E3F7; color: #10418C; width: 100%; height: 25; font-size: 12; padding-top: 3px; padding-left: 10px; font-weight: bold; border-bottom: 1px solid #4482D3; border-top: 1px solid #4482D3;">
													<c:forEach var="root" items="${menuRootList}" varStatus="vs">
														<c:if test="${menu.menuid==root.menuid}">
														${root.menutext }
														</c:if>
													</c:forEach>
												</div>
												<div style="width: 30%; display: inline; height: 20; font-size: 12;" >
													<input type="checkbox" id="power" name="power" value="${menu.levelid }" />
													${menu.leveltext }
												</div>
											</ww:else>
										</c:forEach>
									</td>
								</tr>
							</table>
							<table width="50%" style="border: 0 solid #4482D3;" align="center" cellpadding="0" cellspacing="1">
								<tr>
									<td>
										<div>
											<input type="checkbox" onclick="checkAll();"/>全选&nbsp;/&nbsp;全不选
										</div>
									</td>
								</tr>
							</table>
						</fieldset>
						</ww:if>
						
						<ww:else>
					    <fieldset>
								<legend class="leg">
									用户信息
								</legend>
								<table width="50%" border="0" cellpadding="1" cellspacing="1" class="table_back">
								    <tr>
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											账号
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input type="text" id="code" name="code" style="width: 100px" maxlength="12" value=${code} } readonly />
											&nbsp;
											<font color="#ff0000"></font>
										</td>
									</tr>
									<tr style="display: none">
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											所属部门
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input name="dept" id="dept" maxlength="15"   style="width: 100px"  value="${department}" />
											&nbsp;
											<font id="barinfo" color="#ff0000"></font>
										</td>
									</tr>
								    <tr style="display: none">
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											姓名
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input name="name" id="name" maxlength="15" style="width: 100px" value=${name} readonly/>
											&nbsp;
											<font id="barinfo" color="#ff0000">*不能更改</font>
										</td>
									</tr>
									<tr style="display: none">
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											身份证号码
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input name="idnumber" id="idnumber" maxlength="15" style="width: 100px" value="${idnumber}" readonly="readonly">
											&nbsp;
											<font id="barinfo" color="#ff0000">*不能更改</font>
										</td>
									</tr>
									<tr style="display: none">
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											性别
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<select id="sex" name="sex" style="width: 100px;" >
												<option value="" >请选择...</option>
												<option value="男" <ww:if test="${sex=='男' }">selected</ww:if> >男</option>
												<option value="女" <ww:if test="${sex=='女' }">selected</ww:if> >女</option>
											</select>
											<input type="hidden" id="rolee" name="rolee" value="${roleradio}"/>
											&nbsp;
											<font color="#ff0000"></font>
										</td>
									</tr>
									<tr style="display: none">
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											是否警员
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<select id="police" name="police" style="width: 100px;" >
												<option value="0" <ww:if test="${police=='是' }">selected</ww:if> >是</option>
												<option value="1" <ww:if test="${police=='否' }">selected</ww:if> >否</option>
											</select>
											<input type="hidden" id="rolee" name="rolee" value="${roleradio}"/>
											&nbsp;
											<font color="#ff0000"></font>
										</td>
									</tr>
									<tr>
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											用户有效期
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input name="yhyxq" id="yhyq" style="width: 100px" value="${yhyxq}">
											&nbsp;
											<font id="barinfo" color="#ff0000"></font>
										</td>
									</tr>
									<tr>
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											密码有效期
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input name="passCode" id="passCode" style="width: 100px" value="${passCode}">
											&nbsp;
											<font id="barinfo" color="#ff0000"></font>
										</td>
									</tr>
									<tr style="display: none">
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											可登录ip
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input name="kdlip" id="kdlip" style="width: 100px" value="${kdlip}">
											&nbsp;
											<font id="barinfo" color="#ff0000"></font>
										</td>
									</tr>
									<tr style="display: none">
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											可登录时间段
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input name="kdlsjd" id="kdlsjd" style="width: 100px" value="${kdlsjd}">
											&nbsp;
											<font id="barinfo" color="#ff0000"></font>
										</td>
									</tr>
									<tr style="display: none;">
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											用户角色
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											<!-- &nbsp;
											<input type="radio" name="roleradio" id="roleradio" value="1" <ww:if test="${roleradio eq '1'}">checked='checked'</ww:if>/>系统管理员
											&nbsp;
											<input type="radio" name="roleradio" id="roleradio" value="2" <ww:if test="${roleradio eq '2'}">checked='checked'</ww:if>/>普通用户
											&nbsp; -->
										</td>
									</tr>
								</table>
						</fieldset>
						<fieldset style="display: none">
							<legend class="leg">
								用户权限设置
							</legend>
							<table width="50%" style="border: 1 solid #4482D3;" align="center" cellpadding="0" cellspacing="1">
								<tr>
									<td>
										<c:forEach var="menu" items="${menuList}" varStatus="vs">
											<ww:if test="${menuList[vs.index-1].menuid==menu.menuid}">
												<div style="width: 30%; display: inline; height: 20; font-size: 12;">
													<input type="checkbox" id="power" name="power" value="${menu.levelid }"  />
													${menu.leveltext }
												</div>
											</ww:if>
											<ww:else>
												<div style="${vs.index==0 ? 'margin-top: -1px;' : '' }text-align: left; background-color: #D6E3F7; color: #10418C; width: 100%; height: 25; font-size: 12; padding-top: 3px; padding-left: 10px; font-weight: bold; border-bottom: 1px solid #4482D3; border-top: 1px solid #4482D3;">
													<c:forEach var="root" items="${menuRootList}" varStatus="vs">
														<c:if test="${menu.menuid==root.menuid}">
														${root.menutext }
														</c:if>
													</c:forEach>
												</div>
												<div style="width: 30%; display: inline; height: 20; font-size: 12;" >
													<input type="checkbox" id="power" name="power" value="${menu.levelid }" />
													${menu.leveltext }
												</div>
											</ww:else>
										</c:forEach>
									</td>
								</tr>
							</table>
							<table width="50%" style="border: 0 solid #4482D3;" align="center" cellpadding="0" cellspacing="1">
								<tr>
									<td>
										<div>
											<input type="checkbox" onclick="checkAll();" checked="checked"/>全选&nbsp;/&nbsp;全不选
										</div>
									</td>
								</tr>
							</table>
						</fieldset>
						</ww:else>
						<ww:if test="${param.method=='add'}">
							<input name="bt1" class="button" type="button" value="添 加" onClick="addEmp()" />
						</ww:if>
						<ww:else>
							<input name="bt1" class="button" type="button" value="修 改" onClick="updateEmp()" />
						</ww:else>
						<input name="bt2" class="button" type="button" value="返 回" onClick="window.history.back(-1);" />
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
		</form>
		
		<script type="text/javascript">
			$(function() {
				if('${method}'=='edit'){
					var user_role_ids = '${levelid}'.split(',');
			   	    $('input[name=power]').each(function(i){
			   	    	for(var j=0;j<user_role_ids.length;j++){
				   			if(user_role_ids[j]==$(this).val()){
				   				$(this).attr('checked','true');
				   			}
				   		}
			   	    });
			    }
		
				var roles = $("#role").val();
				if(roles=='1'){
					document.getElementById("roletr").style.display='none';
				}
			});
			
			function addEmp(){
				if($('#code').val()==''){
					alert('请录入账号！');
					$('#code').focus();
					return false;
				}
				if(checkCode()>=1){
					alert('当前账号已存在，请重新输入！');
					$('#code').select();
					return false;
				}
				if($('#name').val()==''){
					alert('请录入姓名！');
					$('#name').focus();
					return false;
				}
				
				if($('#idnumber').val()==''){
					alert('请录入身份证号码!');
					$('#idnumber').focus();
					return false;
				}
				
				if($('#plice').val()==''){
					alert('请选择性别！');
					$('#police').focus();
					return false;
				}
				
				if($('#sex').val()==''){
					alert('请选择性别！');
					$('#sex').focus();
					return false;
				}
				
				if($('#password').val()==''){
					alert('请输入密码！');
					$('#password').focus();
					return false;
				}
				var regex = new RegExp('(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,30}');
				if(!regex.test($('#password').val())){
					alert("密码复杂性不通过!");
					$('#password').focus();
					return false;
				}
				
				if($('#pwd').val()==''){
					alert('请再次输入密码！');
					$('#pwd').focus();
					return false;
				}
				
				if($('#password').val() != $('#pwd').val()){
					alert('两次输入密码不一致！');
					$('#pwd').select();
					return false;
				}
				/* var checkbox = $("input[name='power']:checked").length ;
				if(checkbox<=0){
					alert('请选择相关权限！');
					return false;
				} */
				form1.action = "employee!saveEmp.action";
				form1.submit();
			}
			
			<%-- 用户编号唯一性校验 --%>
			function checkCode(){
				var code = $('#code').val();
				var url = 'employee!checkCode.action?code='+code;
				var result = new MyJqueryAjax(url).request();
				return result;
			}
			
			function updateEmp(){
			
		    	 if($('#name').val()==''){
					alert('请录入姓名！');
					$('#name').focus();
					return false;
				}
				
				if($('#sex').val()==''){
					alert('请选择性别！');
					$('#sex').focus();
					return false;
				}
				if($('#police').val()==''){
					alert('请选择性别！');
					$('#police').focus();
					return false;
				} 
				
				/* var checkbox = $("input[name='power']:checked").length ;
				if(checkbox<=0){
					alert('请选择相关权限！');
					return false;
				} */
				 form1.action = "employee!updateRoleYXQ.action";
				form1.submit(); 
			}
			
			function checkAll(){
				var checkboxs = document.getElementsByName("power");
				for(var i =0;i<checkboxs.length;i++){
						var e = checkboxs[i];
						if(e.checked==true){
							e.checked="";
						}else{
							e.checked="checked";
						}
				}
			}
		</script>
	</body>
</html>