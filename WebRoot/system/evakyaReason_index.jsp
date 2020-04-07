<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"  %>
<%@ page isELIgnored="false"%>
<html>
  <head>
    <title>差评原因设置</title>
  </head>
  <meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/queue/plugs/ecside/css/ecside_style.css" />
<script type="text/javascript" src="/queue/plugs/ecside/js/prototype_mini.js" ></script>	
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside.js" ></script>
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside_msg_utf8_cn.js" ></script> 
<body>
	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">差评原因设置</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    
	<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" >
	<tr>
	<td> 
		<ec:table items="list" var="list" 
					action="getAllGredentialsEvaluaReason.action"
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
				    <ec:column width="10%" property="dm" title="编号" style="text-align:center" editable="false" />
					<ec:column width="10%" property="mc" title="原因" style="text-align:center" editable="false" />
                    <ec:column width="8%" property="operation" title="操作" style="text-align:center" editable="false" />
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
	<tr>
			<td style="text-align:center" colspan="2"><img src='/queue/images/button_add.jpg' style='cursor:hand' onclick="showAdd()"></td>
	</tr>
	<tr height="5"><td></td></tr>
	<tr>
		<td>
				<div id="addEv" name="addEv" style="text-align:center;display:none">
				 <form action="" method="post" name="form1">
				   <input type="hidden" name="mc" id="mc" value="${mc}"/>
				   <input type="hidden" name="maxid" id="maxid" value="${maxDmz }"/>
					<table width="50%" border="0" cellpadding="1" cellspacing="1" class="table_back">
						     <tr>
						        <td width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">编号</td>
						        <td width="80%" height="35" bgcolor="#F1F9FD">&nbsp;
						          <input name="id" id="id" maxlength="10" style="width:100px" value="<c:out value="${maxDmz }"></c:out>" disabled="disabled" />
						          &nbsp;<font color="#ff0000"></font>
						        </td>
						     </tr>
						     <tr>
						        <td rowspan="2" width="20%" height="35" bgcolor="#FFFFFF" class="tableheader1">原因</td>
						        <td width="80%" height="200" bgcolor="#F1F9FD">&nbsp;
						          <input name="content" id="content" maxlength="10" style="width:60%;height:160px;" value=""  />
						          &nbsp;<font color="#ff0000"></font>
						        </td>
						     </tr>
						     <tr>
						        <td  width="20%" height="35" bgcolor="#FFFFFF" style="text-align:center">
						        	<input type="button" name="subAdd" value="提交" onclick="addReason()">
						        </td>
						     </tr>
				    </table>
				    </form>
				</div>    
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
	function showAdd(){
		document.getElementById('addEv').style.display ="";
	}
	function addReason(){
		var name = document.form1.content.value;
		if(name.length == 0){
			alert("内容不能为空!");
			document.form1.name.focus();
			return false;
		}
		document.form1.action = "addGredentialsEvaluaReason.action"; 
		document.form1.submit();

	}
	
	function updateReason(id){ 
		document.form1.action = "toGredentialsEvaluaReason.action?id="+id; 
		document.form1.submit();

	}
	
	function delReason(id,lx,mc){
		if(confirm("确定删除此人员吗?")){ 
		document.form1.action = "delGredentialsEvaluaReason.action?id="+id+"&lx="+lx;
		document.form1.submit();
		}
	}
	

</script>
