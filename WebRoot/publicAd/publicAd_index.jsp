<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"  %>
<%@ page isELIgnored="false"%>
<html>
  <head>
    <title>宣传材料设置</title>
  </head>
  <meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/queue/plugs/ecside/css/ecside_style.css" />
<script type="text/javascript" src="/queue/plugs/ecside/js/prototype_mini.js" ></script>	
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside.js" ></script>
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside_msg_utf8_cn.js" ></script>
<script type="text/javascript" src="/queue/js/jquery.js"></script> 
<body onload="currentDept()">
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
	<input id="currentDept" type="hidden" value="${currentDept}"/>
	<input id="role" type="hidden" value="${role}"/>
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">宣传材料设置</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    
   <form action="" method="post" name="form1">
   </form>
    <input name="button" type="button" class="button" value="添加" onClick="addPublicAd()"/>
    <br/><br/>
    <c:if test="${role eq 0}">
    	大厅名称:<select id="codeAndHall" onchange="xxcl()">
	    	<option value="-" selected="selected">全部大厅</option>
			<c:forEach items="${deptInfo}" var="item" >
				<option id="${item.key}" value="${item.key}">${item.value}</option>
			</c:forEach>
		</select>	
		<br/><br/>
    </c:if>
    
    
    
	<table width="90%" align="center" border="0" cellpadding="0" cellspacing="0" >
	<tr>
	<td> 
		<ec:table items="list1" var="list1" 
					action="xccl.action"
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
					
				    <ec:column width="10%" property="operator" title="设置用户" style="text-align:center;" editable="false" />
				    <ec:column width="8%" property="isUsing" title="启用状态" style="text-align:center;" editable="false" />
				    <ec:column width="8%" property="deptHall" title="大厅名称" style="text-align:center;" editable="false" />
				    <ec:column width="34%" property="message" title="宣传材料内容" style="text-align:center;" editable="false" />
					<ec:column width="10%" property="record_date" title="录入(修改)时间" style="text-align:center" editable="false" />
                   <ec:column width="8%" property="msg_state" title="是否滚动" style="text-align:center" editable="false" />
                    <ec:column width="22%" property="operation" title="操作" style="text-align:center" editable="false" />
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

</html>
<script type="text/javascript">
	function addPublicAd(){
		document.form1.action = "into_addPublicAd.action"; 
		document.form1.submit();
	}
	function updatePublicAd(id,operator,role){
		if(operator=="0"&&role!="0"){
			alert("您没有权限修改该宣传信息");
		}else{
			document.form1.action = "updatePublicAd.action?id="+id; 
			document.form1.submit();
		}
	}
	function delPublicAd(id,operator,role){
		if(operator=="0"&&role!="0"){
			alert("您没有权限删除该宣传信息");
		}else{
			if(confirm("确定删除吗?")){
				document.form1.action = "delPublicAd.action?id="+id;
				document.form1.submit();
			}
		}
	}
	//改变该宣传设置的启用
	function change(id,role,isUsing){
		var codeAndHall =$("#codeAndHall option:selected").val();
		var deptCode = codeAndHall.split("-")[0];
		var deptHall = codeAndHall.split("-")[1];
		window.location.href="updateAdbyId.action?deptCode="+deptCode+"&deptHall="+deptHall+"&id="+id+"&role="+role+"&isUsing="+isUsing;
	}
	//页面打开 使相应下拉框选项选中
	function currentDept(){
		var currentDept = $("#currentDept").val();
		//if(currentDept!="all"){
		//	$("option[value="+currentDept+"]").attr("selected",true);
		//}
		$("option[value="+currentDept+"]").attr("selected",true);
		
	}
	function msg(flag){
		if(flag=="1"){
			alert("停用失败,您无法停用总队设置的宣传信息  如需停止请联系总队");
		}
		if(flag=="2"){
			alert("启用失败,您无法操作总队设置的宣传信息");
		}
		if(flag=="3"){
			alert("请先停止 总队设置的宣传信息");
		}
	}
	function xxcl(){
		var codeAndHall =$("#codeAndHall option:selected").val();
		var deptCode = codeAndHall.split("-")[0];
		var deptHall = codeAndHall.split("-")[1];
		var selectFlag = '1';
		
		window.location.href="/queue/xccl.action?redirectFlag=1&deptCode="+deptCode+"&deptHall="+deptHall+"&selectFlag="+selectFlag;
		
	}
</script>
