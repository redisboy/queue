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
			<input type="hidden" id="flaga" name="flaga" />
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
								角色添加设置
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
									<tr>
										<td width="22%" height="35" bgcolor="#FFFFFF" class="tableheader1">角色类别</td>
										<td width="78%" height="35" bgcolor="#F1F9FD">
											<input type="radio" id="isCheckmodel" name="isCheckmodel" value="0" <ww:if test="${isCheckmodel eq 0}">checked</ww:if>  onclick="Checkmodel(0);"/>业务管理
											<input type="radio" id="isCheckmodel" name="isCheckmodel" value="1" <ww:if test="${isCheckmodel eq 1}">checked</ww:if>  onclick="Checkmodel(1);"/>系统管理
											<input type="radio" id="isCheckmodel" name="isCheckmodel" value="2" <ww:if test="${isCheckmodel eq 2}">checked</ww:if>  onclick="Checkmodel(2);"/>安全管理
											<input type="radio" id="isCheckmodel" name="isCheckmodel" value="3" <ww:if test="${isCheckmodel eq 3}">checked</ww:if>  onclick="Checkmodel(3);"/>审计管理
										</td>
									</tr>
							</table>
							
							<table id="rodelist" width="50%" style="border: 1 solid #4482D3;display: none;" align="center" cellpadding="0" cellspacing="1" >
								
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
							<table id="rodelist1" width="50%" style="border: 1 solid #4482D3;display: none;" align="center" cellpadding="0" cellspacing="1" >
								
								<tr>
									<td>
										<c:forEach var="menu1" items="${menuList1}" varStatus="vs1">
											<ww:if test="${menuList1[vs1.index-1].menuid==menu1.menuid}">
												<div style="width: 30%; display: inline; height: 20; font-size: 12;">
													<input type="checkbox" id="power" name="power" value="${menu1.levelid }"  />
													${menu1.leveltext }
												</div>
											</ww:if>
											<ww:else>
												<div style="${vs1.index==0 ? 'margin-top: -1px;' : '' }text-align: left; background-color: #D6E3F7; color: #10418C; width: 100%; height: 25; font-size: 12; padding-top: 3px; padding-left: 10px; font-weight: bold; border-bottom: 1px solid #4482D3; border-top: 1px solid #4482D3;">
													<c:forEach var="root1" items="${menuRootList1}" varStatus="vs1">
														<c:if test="${menu1.menuid==root1.menuid}">
														${root1.menutext }
														</c:if>
													</c:forEach>
												</div>
												<div style="width: 30%; display: inline; height: 20; font-size: 12;" >
													<input type="checkbox" id="power" name="power" value="${menu1.levelid }" />
													${menu1.leveltext }
												</div>
											</ww:else>
										</c:forEach>
									</td>
								</tr>
							</table>
							<table id="rodelist2" width="50%" style="border: 1 solid #4482D3;display: none;" align="center" cellpadding="0" cellspacing="1" >
								
								<tr>
									<td>
										<c:forEach var="menu2" items="${menuList2}" varStatus="vs2">
											<ww:if test="${menuList2[vs2.index-1].menuid==menu2.menuid}">
												<div style="width: 30%; display: inline; height: 20; font-size: 12;">
													<input type="checkbox" id="power" name="power" value="${menu2.levelid }"  />
													${menu2.leveltext }
												</div>
											</ww:if>
											<ww:else>
												<div style="${vs2.index==0 ? 'margin-top: -1px;' : '' }text-align: left; background-color: #D6E3F7; color: #10418C; width: 100%; height: 25; font-size: 12; padding-top: 3px; padding-left: 10px; font-weight: bold; border-bottom: 1px solid #4482D3; border-top: 1px solid #4482D3;">
													<c:forEach var="root2" items="${menuRootList2}" varStatus="vs2">
														<c:if test="${menu2.menuid==root2.menuid}">
														${root2.menutext }
														</c:if>
													</c:forEach>
												</div>
												<div style="width: 30%; display: inline; height: 20; font-size: 12;" >
													<input type="checkbox" id="power" name="power" value="${menu2.levelid }" />
													${menu2.leveltext }
												</div>
											</ww:else>
										</c:forEach>
									</td>
								</tr>
							</table>
							<table id="rodelist3" width="50%" style="border: 1 solid #4482D3;display: none;" align="center" cellpadding="0" cellspacing="1" >
								
								<tr>
									<td>
										<c:forEach var="menu3" items="${menuList3}" varStatus="vs3">
											<ww:if test="${menuList3[vs3.index-1].menuid==menu3.menuid}">
												<div style="width: 30%; display: inline; height: 20; font-size: 12;">
													<input type="checkbox" id="power" name="power" value="${menu3.levelid }"  />
													${menu3.leveltext }
												</div>
											</ww:if>
											<ww:else>
												<div style="${vs3.index==0 ? 'margin-top: -1px;' : '' }text-align: left; background-color: #D6E3F7; color: #10418C; width: 100%; height: 25; font-size: 12; padding-top: 3px; padding-left: 10px; font-weight: bold; border-bottom: 1px solid #4482D3; border-top: 1px solid #4482D3;">
													<c:forEach var="root3" items="${menuRootList3}" varStatus="vs3">
														<c:if test="${menu3.menuid==root3.menuid}">
														${root3.menutext }
														</c:if>
													</c:forEach>
												</div>
												<div style="width: 30%; display: inline; height: 20; font-size: 12;" >
													<input type="checkbox" id="power" name="power" value="${menu3.levelid }" />
													${menu3.leveltext }
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
						<input name="bt1" class="button" type="button" value="添加" onClick="addRole()" />
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
			
			function addRole(){
				var RoleID=document.getElementById("flaga").value;
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
				
				form1.action = "addRoled.action?RoleID="+RoleID;
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
			function Checkmodel(flagid){
				document.getElementById("flaga").value = flagid;
				jQuery.ajax({type: "POST", cache: false,dataType: "json",async: false,
					url: "levelidRold.action?flagid="+flagid+"&"+parseInt(Math.random()*100000),
					success: function () {
						if("0"==flagid){
							jQuery("#rodelist").css("display", "");
							jQuery("#rodelist1").css("display", "none");
							jQuery("#rodelist2").css("display", "none");
							jQuery("#rodelist3").css("display", "none");
							}else if("1"==flagid){
									jQuery("#rodelist").css("display", "none");
									jQuery("#rodelist1").css("display", "");
									jQuery("#rodelist2").css("display", "none");
									jQuery("#rodelist3").css("display", "none");
							}else if("2"==flagid){
								jQuery("#rodelist").css("display", "none");
								jQuery("#rodelist1").css("display", "none");
								jQuery("#rodelist2").css("display", "");
								jQuery("#rodelist3").css("display", "none");
						}else if("3"==flagid){
							jQuery("#rodelist").css("display", "none");
							jQuery("#rodelist1").css("display", "none");
							jQuery("#rodelist2").css("display", "none");
							jQuery("#rodelist3").css("display", "");
					}
						
					}
				});  
			}
		</script>
	</body>
</html>