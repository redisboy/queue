<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"%>
<%@ taglib uri="/webwork" prefix="ww"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
	<head>
		<title>权限管理信息</title>
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
			<input type="hidden" id="police" name="police" value="${police }"/>
			<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td height="5" colspan="3">
						<input type="hidden" name="role" id="role" value="${role}" />
					</td>
				</tr>
				<tr>
					<td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
				    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
				    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">用户角色管理</span>
				    </td>
				    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
				</tr>
				<tr>
					<td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
					<td height="100" style="text-align: center" valign="middle" bgcolor="#f7f7f7">
						<fieldset>
							<legend class="leg">
								角色修改设置
							</legend>
							<table width="50%" style="border: 1 solid #4482D3;" align="center" cellpadding="0" cellspacing="1">
							<tr>
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											角色名称
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
											<input type="text" id="code" name="code" style="width: 150px" maxlength="9" value="${code}"/>
											&nbsp;
										</td>
									</tr>
									<tr>
										<td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">
											角色描述
										</td>
										<td width="80%" height="35" bgcolor="#F1F9FD">
											&nbsp;
										
											<textarea rows="5"  id="content" name="content" style="width: 300px;height: 100px; font-size: 14px;font-weight: 680;"    >${content}</textarea><br/>
										</td>
									</tr>
							</table>
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
						<input name="bt1" class="button" type="button" value="修 改" onClick="updateEmp()" />
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
					var user_role_ids = '${levelid}'.split(',');
			   	    $('input[name=power]').each(function(i){
			   	    	for(var j=0;j<user_role_ids.length;j++){
				   			if(user_role_ids[j]==$(this).val()){
				   				$(this).attr('checked','true');
				   			}
				   		}
			   	    });
			});
			
			function updateEmp(){
				var checkbox = $("input[name='power']:checked").length ;
				if(checkbox<=0){
					alert('请选择相关权限！');
					return false;
				}
				/* 检查 角色名称是否符合规范 */
				var code= $("#code").val().length;
				 if(code>6){
					alert('角色名称长度过长，名称长度不得大于6！');
					return false;
				} else if(code==0){
					alert('角色名称不能为空！');
					return false;
				}
				/* 检查 角色描述是否符合规范 */
				var content= $("#content").val().length;
				 if(content>20){
					alert('角色描述长度过长，名称长度不得大于20！');
					return false;
				}else if(content==0){
					alert('角色描述不能为空！');
					return false;
				}
				 form1.action = "saveRdacls.action";
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