<%@ page language="java" contentType="text/html;charset=UTF-8" import="java.util.*,com.suntendy.queue.agent.vo.ErrorVO;" %>
<%@ taglib prefix="ec" uri="http://www.ecside.org"  %>
<html>
  <head>
    <title>代理人管理</title>
  </head>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/queue/plugs/ecside/css/ecside_style.css" />
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside.js" ></script>
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside_msg_utf8_cn.js" ></script> 
<script type="text/javascript">
function downexcel(){
	window.location="downExcel.action";
}
function LimitAttach(form, file) {
	var docV = document.getElementById("excelfile").value;
	if(docV==null || docV==""){
		alert("请上传EXCEL文件");
		return false;
	}
	
	var allowSubmit = false;
	if (!file) return;
	while (file.indexOf("\\") != -1)
	file = file.slice(file.indexOf("\\") + 1);
	//alert(file);
	document.getElementById("filename").value=file;
	ext = file.slice(file.indexOf(".")).toLowerCase();
	
	if (".xls" == ext) {
		document.getElementById("loadImg").style.display="";
		form.submit();
	}
	else {
		alert("只能上传Excle文件");
		//window.close();
		return false;
	}
}
</script>
<%
	String msg=(String)request.getAttribute("msg");
	List<ErrorVO> list=(List)request.getSession().getAttribute("listaddexcel");
	List listVO=(List)request.getAttribute("listVO");
%>
<body>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">代理人批量导入</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
   	<form action="agentexcel.action" method="post" enctype="multipart/form-data" name="form" target="_self">
   	<br/>
   	<br/>
   	<br/>
   	<div style="color: red;">${msg }</div>
   	<br/>
    <input type="button" value="模板下载" class="button" onclick="downexcel()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input id="excelfile" type="file"  size="20" name="excelfile"/>
    <input type="hidden" name="filename" id="filename">
	<input type="button" value=" 上传"    class="button" onclick="LimitAttach(this.form, this.form.excelfile.value)" />
     </form>
	<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" >
	
	<div style="color: red;float: right;" >
	<ul>
		<li>请先下载数据模板，填写后再上传文件</li>
		<li>只能上传EXCEL文件，文件格式必须与模板格式相同</li>
		<li>请去除EXCEL文件中的下拉框</li>
		<li>请去除EXCEL中的宏以及筛选等操作</li>
		<li>请正确核对EXCEL中的数据后再上传</li>
		<li>请避免空白行的出现</li>
	</ul>
	</div>
	</table>
	<div align="center">
			<img id="loadImg" src="/queue/images/loadexcel.gif" style="display: none;"></img>
	</div>
	 <%if(list!=null){ %>
	 <div align="center" style="font-size: 35px;">代理人批量录入信息</div>
	<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" >
	<tr>
	<td> 
		<ec:table tableId="agent"  items="listaddexcel" var="listaddexcel" 
					action="agentexcel.action?flg=1"
					editable="false" 
					width="100%" 
					listWidth="100%"
					classic="true"
					doPreload="false"
					useAjax="true"
					resizeColWidth="false" 
					sortable="false"
					batchUpdate="false"
					nearPageNum="0"
					toolbarLocation="top"
					pageSizeList="15"
					showPrint="false"
					filterable="false"
					xlsFileName="代理人批量录入信息.xls"
					>
				<ec:row style="text-align:center">
					<ec:column width="1%" property="_0" title="序号" value="${GLOBALROWCOUNT}" style="text-align:center" editable="false"/>
					<ec:column width="50%" property="error" title="批量上传数据信息" editable="false" />
				</ec:row>
	</ec:table><br>
</td>
	</tr>
	</table>
	   	<%}%>
	<br>
	<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" >
	<tr>
	<td> 
	<%if(listVO!=null){ %>
	<div align="center" style="font-size: 35px;">代理人无业务、照片、指纹数据</div>
	<input type="button" value="审核所有" class="button" onclick="window.location='agentExcelUpAll.action'" />
		<ec:table tableId="agent2" items="listVO" var="listVO" 
					action="agentexcel.action"
					editable="false" 
					width="100%" 
					listWidth="100%"
					classic="true"
					doPreload="false"
					useAjax="true"
					resizeColWidth="false" 
					sortable="false"
					batchUpdate="false"
					nearPageNum="0"
					toolbarLocation="top"
					pageSizeList="15"
					showPrint="false"
					filterable="false"
					xlsFileName="代理人无业务照片指纹数据.xls"
					>
				<ec:row style="text-align:center">
					<ec:column width="5%" property="id" title="编号" editable="false" style="text-align:center" />
					<ec:column width="10%" property="idCard" title="身份证号" style="text-align:center" editable="false" />
					<ec:column width="7%" property="name" title="姓名" style="text-align:center" editable="false" />
					<ec:column width="5%" property="register_date" title="初次登记日期" style="text-align:center" editable="false" />
					<ec:column width="8%" property="unit" title="服务单位" style="text-align:center" editable="false" />
					<ec:column width="8%" property="cellphone" title="手机号" style="text-align:center" editable="false" />
                    <ec:column width="10%" property="unit_phone" title="单位电话" style="text-align:center" editable="false" />
                    <ec:column width="7%" property="incorporator" title="法人代表" style="text-align:center" editable="false" />
                    <ec:column width="5%" property="validity" title="有效期" style="text-align:center" editable="false" />
                    <ec:column width="5%" property="status" title="状态" style="text-align:center" editable="false" />
                    <ec:column width="5%" property="selexcel" title="添加业务" style="text-align:center" editable="false" />
				</ec:row>
	</ec:table><br>
</td>
	</tr>
	</table>
	<%}%>
	<br/>
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