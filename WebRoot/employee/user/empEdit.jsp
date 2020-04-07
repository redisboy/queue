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
				    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">用户管理</span>
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
											部门号
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
											警员或员工编号
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input name="policeCode" id="policeCode" maxlength="15" style="width: 100px"  />
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
											<input name="idnumber" id="idnumber" maxlength="18" style="width: 100px" />
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
											<input type="password" name="password" id="password" style="width: 100px" />
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
											<input type="password" name="pwd" id="pwd"  style="width: 100px" />
											&nbsp;
											<font color="#ff0000">*请再次录入密码</font>
										</td>
									</tr>
									<tr>
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											密码有效期
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input name="yhyxq" id="yhyxq" style="width: 100px" />
											&nbsp;
											<font color="#ff0000">*格式为2018-12-31</font>
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
									<tr id="roleEve">
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											角色功能
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											<select name="roleModule" id="roleModule" >
								             <c:forEach var="Queue" items="${listRole}" varStatus="vs">
								                    <option value="${ Queue.code}">${ Queue.code}</option>
								             </c:forEach>
								           </select>
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
											<font id="barinfo" color="#ff0000">*不能更改</font>
										</td>
									</tr>
									<tr>
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
									<tr>
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											警员或员工编号
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input name="policeCode" id="policeCode" maxlength="15" style="width: 100px" value="${policeCode}"  />
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
											<input name="name" id="name" maxlength="15" style="width: 100px" value=${name} readonly/>
											&nbsp;
											<font id="barinfo" color="#ff0000">*不能更改</font>
										</td>
									</tr>
									<tr>
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
									<tr>
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
									<tr>
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
											<input name="yhyxq" id="yhyq" style="width: 100px" value="${yhyxq}" readonly="readonly">
											&nbsp;
											<font id="barinfo" color="#ff0000">*有效期前不能更改</font>
										</td>
									</tr>
									<tr>
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											密码有效期
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input name="passcode" id="passcode" style="width: 100px" value="${passcode}" readonly="readonly">
											&nbsp;
											<font id="barinfo" color="#ff0000">*有效期前不能更改</font>
										</td>
									</tr>
									<tr>
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
									<tr>
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											可登录时间段
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input name="kdlsjd" id="kdlsjd" style="width: 100px" value="${kdlsjd}">
											&nbsp;
											<font color="#ff0000">*格式为9-17(代表早9到晚5,单位:小时)</font>
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
									<tr id="roleEve">
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											角色功能
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											<%-- &nbsp;
											<input type="radio" name="roleModule" id="roleModule" value="0" <ww:if test="${roleModule eq '0'}">checked='checked'</ww:if>/>业务管理
											&nbsp;
											<input type="radio" name="roleModule" id="roleModule" value="1" <ww:if test="${roleModule eq '1'}">checked='checked'</ww:if>/>系统管理
											&nbsp;
											<input type="radio" name="roleModule" id="roleModule" value="2" <ww:if test="${roleModule eq '2'}">checked='checked'</ww:if>/>安全管理
											&nbsp;
											<input type="radio" name="roleModule" id="roleModule" value="3" <ww:if test="${roleModule eq '3'}">checked='checked'</ww:if>/>审计管理
											&nbsp; --%>
											<select name="roleModule" id="roleModule" >
								             <c:forEach var="Queue" items="${listRole}" varStatus="vs">
								                    <option value="${ Queue.code}"  
								                     <c:if test="${Queue.code==roleModule}">selected</c:if> >${ Queue.code}</option>
								             </c:forEach>
								           </select>
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
				    <input id="test" type="hidden" value="${policeCode}" />
				 </tr>
				  
			</table>
		</form>
		
		<script type="text/javascript">
		
		
			function addEmp(){
			if($('#code').val()==''){
				alert('请录入当前【账号】！');
				$('#code').focus();
				return false;
			}else{
				if(checkCode()>=1){
					alert('当前【账号】已存在，请重新输入！');
					$('#code').select();
					return false;
				}
			}
			if($('#dept').val()==''){
				alert('请输入【部门号】！');
				$('#dept').focus();
				return false;
			}
			/* 警员编号校验 */
			if($('#policeCode').val()==''){
				alert('请录入【警员编号】!');
				$('#policeCode').focus();
				return false;
			}else{
				if(checkPoliceCode()>=1){
					alert('【警员编号】或【员工编号】已存在，请重新输入！');
					$('#policeCode').select();
					return false;
				}
			}
			if($('#name').val()==''){
				alert('请录入【姓名】！');
				$('#name').focus();
				return false;
			} 
			if($('#sex').val()==''){
				alert('请选择【性别】！');
				$('#sex').focus();
				return false;
			}
			 if($('#plice').val()==''){
				alert('请选择【警员】！');
				$('#police').focus();
				return false;
			}
				/* 身份证校验
				用于身份证校验的正则表达式 */
			var reg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
				if($('#idnumber').val()==''){
					alert('请录入【身份证号码】!');
					$('#idnumber').focus();
					return false;
				}
				
				if(checkIdcardIsOrNo()=="1"){
					window.alert("非法【身份证号】！");
					return;
				}else if(checkIdcardIsOrNo()=="2"){
					window.alert("身份证号码非法【地区】！");
					return;
				}else if(checkIdcardIsOrNo()=="3"){
					window.alert("身份证号码【非法生日】！");
					return;
				}
				if(!reg.test($('#idnumber').val())){
					alert('【身份证号码】不合法，请重新输入!');
					$('#idnumber').focus();
					return false;
				}else{
					if(checkIdNumber()>=1){
						alert('当前【身份证号码】已存在，请重新输入！');
						$('#idnumber').select();
						return false;
					}
				}
				if($('#password').val()==''){
					alert('请输入【密码】！');
					$('#password').focus();
					return false;
				}
				var pas = $('#password').val();
				var paslen = pas.length;
				if (8 > paslen) {
					alert("密码长度不能少于【8位数】!");
					$('#password').focus();
					return false;
				}
				var regex = new RegExp('(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,30}');
				 if(!regex.test($('#password').val())){
					alert("密码【复杂性】不通过!");
					$('#password').focus();
					return false;
				} 
				
				if($('#pwd').val()==''){
					alert('请再次输入【密码】！');
					$('#pwd').focus();
					return false;
				}
				
				if($('#password').val() != $('#pwd').val()){
					alert('两次输入密码不一致！');
					$('#pwd').select();
					return false;
				}
				<%-- IP校验 --%>
				 var IPregex = /^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/;
				 if($('#kdljsjip').val()==''){
					alert('请录入可登陆【IP】!');
					$('#policeCode').focus();
					return false;
				}else{
					var s=$('#kdljsjip').val().toString();
					 var a=s.split(',').length;
					var aa=s.split(',');
					for(var i=0;i<a;i++){
						if(IPregex.test(aa[i].toString())){
						}else{
						alert('第【'+(i+1)+'个IP】不合法!请重新输入！');
						return false;
						}
						
					}
				} 
				 if($('#kdlip').val()==''){
						alert('请录入可登陆【IP】!');
						$('#policeCode').focus();
						return false;
					}
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
			
			function checkIdNumber(){
				var idNumber = $('#idnumber').val();
				var url = 'employee!checkIdNumber.action?idNumber='+idNumber;
				var result = new MyJqueryAjax(url).request();
				return result;
			}
			
			function checkPoliceCode(){
				var policeCode = $('#policeCode').val();
				var url = 'employee!checkPoliceCode.action?policeCode='+policeCode;
				var result = new MyJqueryAjax(url).request();
				return result;
			}
			
			function updateEmp(){
				var test=$('#test').val();
		    	if($('#name').val()==''){
					alert('请录入【姓名】！');
					$('#name').focus();
					return false;
				}
				
				if($('#sex').val()==''){
					alert('请选择【性别】！');
					$('#sex').focus();
					return false;
				}
				if($('#police').val()==''){
					alert('请选择【警员】！');
					$('#police').focus();
					return false;
				}
				
				if($('#dept').val()==''){
					alert('请输入【部门号】！');
					$('#dept').focus();
					return false;
				}
				
				/* 警员编号校验 */
				
				if($('#policeCode').val()==''){
					alert('请录入【警员编号】!');
					$('#policeCode').focus();
					return false;
				}else if($('#policeCode').val()==test){  //判断是否修改了警员编号
				
					
				
				}else{
					if(checkPoliceCode()>=1){
						alert('【警员编号】或【员工编号】已存在，请重新输入！');
						$('#policeCode').select();
						return false;
					}
				}
				
				
				
				form1.action = "employee!updateRole.action";
				form1.submit();
			}
			
			function checkPoliceCode(){
				var policeCode = $('#policeCode').val();
				var url = 'employee!checkPoliceCode.action?policeCode='+policeCode;
				var result = new MyJqueryAjax(url).request();
				return result;
			}
			
			function checkAll(){
				var checkboxs = document.getElementsByName("roleModule");
				for(var i =0;i<checkboxs.length;i++){
						var e = checkboxs[i];
						if(e.checked==true){
							e.checked="";
						}else{
							e.checked="checked";
						}
				}
			}
			
			function checkIdcardIsOrNo(){ 
				  var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};
				  var iSum = 0;
				  //var info = "";
				  var strIDno = $('#idnumber').val();
				  var idCardLength = strIDno.length;
				  if(!/^\d{17}(\d|x)$/i.test(strIDno)&&!/^\d{15}$/i.test(strIDno))
				     return 1; //非法身份证号
				
				  if(aCity[parseInt(strIDno.substr(0,2))]==null)
				     return 2;// 非法地区
				
				  // 15位身份证转换为18位
				  if (idCardLength==15)
				 {
				    sBirthday = "19" + strIDno.substr(6,2) + "-" + Number(strIDno.substr(8,2)) + "-" + Number(strIDno.substr(10,2));
				    var d = new Date(sBirthday.replace(/-/g,"/"))
				    var dd = d.getFullYear().toString() + "-" + (d.getMonth()+1) + "-" + d.getDate();
				    if(sBirthday != dd)
				       return 3; //非法生日
				       strIDno=strIDno.substring(0,6)+"19"+strIDno.substring(6,15);
				       strIDno=strIDno+GetVerifyBit(strIDno);
				  }
				
				  // 判断是否大于2078年，小于1900年
				  var year =strIDno.substring(6,10);
				  if (year<1900 || year>2078 )
				      return 3;//非法生日
				
				  //18位身份证处理
				
				  //在后面的运算中x相当于数字10,所以转换成a
				  strIDno = strIDno.replace(/x$/i,"a");
				
				  sBirthday=strIDno.substr(6,4)+"-"+Number(strIDno.substr(10,2))+"-"+Number(strIDno.substr(12,2));
				  var d = new Date(sBirthday.replace(/-/g,"/"))
				  if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))
				                return 3; //非法生日
				  // 身份证编码规范验证
				  for(var i = 17;i>=0;i --)
				   iSum += (Math.pow(2,i) % 11) * parseInt(strIDno.charAt(17 - i),11);
				  if(iSum%11!=1)
				   return 1;// 非法身份证号
				
				  // 判断是否屏蔽身份证
				  var words = new Array();
				  words = new Array("11111119111111111","12121219121212121");
				
				  for(var k=0;k<words.length;k++){
				   if (strIDno.indexOf(words[k])!=-1){
				       return 1;
				     }
				   }
				       return 0;
				} 
		</script>
	</body>
</html>