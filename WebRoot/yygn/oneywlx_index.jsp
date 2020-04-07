<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"  %>

<html>
 <head>
    <title>预约一级业务类型</title>
  </head>
   <meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/queue/plugs/ecside/css/ecside_style.css" />
<script type="text/javascript" src="/queue/plugs/ecside/js/prototype_mini.js" ></script>	
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside.js" ></script>
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside_msg_utf8_cn.js" ></script>
<script type="text/javascript" src="/queue/js/jquery.js" ></script>
<script type="text/javascript" src="/queue/js/ajax.js" ></script>  
<script type="text/javascript">

	function addOneywlx() {
		document.form1.action = "fromAddOneywlx.action"; 
		document.form1.submit();
	}
	
	function updateOneywlx(id){ 
		document.form1.action = "fromUpdateOneywlx.action?id="+id; 
		document.form1.submit();
	}
	function delOneywlx(id){

		/* 判断是否 有二级业务 */
		var url = 'checkTwo.action?id='+id;
		var result = new MyJqueryAjax(url).request();
		if(result==0){
			/* 有二级业务 */
			if(confirm("该业务包含二级业务，是否一起删除吗?")){ 
			document.form1.action = "delAllFormOneTwoywlx.action?id="+id;
			document.form1.submit();
			}
		}else{
			/* 没有二级业务 */
			if(confirm("确定删除吗?")){ 
			document.form1.action = "delOneywlx.action?id="+id;
			document.form1.submit();
			}
		}
		
	}


</script>
<body>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">预约一级业务类型</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    
   <form action="" method="post" name="form1">
   </form>
    <input name="button" type="button" class="button" value="添加" onClick="addOneywlx()"/>
	<table width="90%" align="center" border="0" cellpadding="0" cellspacing="0" >
	<tr>
	<td> 
		<ec:table items="list" var="list" 
					action="oneywlx.action"
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
				    <ec:column width="10%" property="name" title="业务名称" style="text-align:center" editable="false" />
					<ec:column width="10%" property="xtlb" title="系统类别" style="text-align:center" editable="false" 
					 />
					<ec:column width="10%" property="ejywzt" title="是否包含二级业务" style="text-align:center" editable="false" />
					<ec:column width="10%" property="dtyysl" title="大厅预约数量" style="text-align:center" editable="false" />
                    <ec:column width="10%" property="operation" title="操作" style="text-align:center" editable="false" />
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
	<br>
    <td width="8" background="/queue/images/t-5.jpg">&nbsp;</td>
  <tr>
    <td ><img src="/queue/images/t-6.jpg" width="7" height="11"></td>
    <td height="11" background="/queue/images/t-7.jpg"></td>
    <td ><img src="/queue/images/t-8.jpg" width="8" height="11"></td>
  </tr>
</html>
