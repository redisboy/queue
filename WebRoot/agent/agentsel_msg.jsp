<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"  %>
<html>
  <head>
    <title>代理人管理</title>
  </head>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/queue/ecside/css/ecside_style.css" />
<script type="text/javascript" src="/queue/js/time.js" ></script> 
<script type="text/javascript">
//复选框
function check(){	
	var code=document.getElementsByName('code');
	var s = '${stype}';
	var array=new Array();
	array=s.split(",");
	for(var i=0;i<code.length;i++){
		for(var j=0;j<array.length;j++){
			if(array[j]==code[i].value){
				code[i].checked=true;
			}
		}
	}	
}
//下拉列表
function loc(){
	var status=${agent.status};
	var sta=document.getElementById("status");
	for(var i=0;i<sta.options.length;i++){
		if(sta.options[i].value==status){
			sta.options[i].selected=true;
		}
	}
}
</script>
<body onload="check();loc();">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">代理人信息查询</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
   
    <form action="" method="post" name="form1">
     <input type="hidden" id="base64Code" name="base64Code" /><%--照片 --%>
          <table width="90%" border="0" cellpadding="1" cellspacing="1" class="table_back">
            <tr>
            <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1" rowspan="6">照片</td>
              	<td width=100 bgColor=#f1f9fd height=30 rowspan="6">&nbsp;
	          	 <img alt="照片" height="200px" width="150px" src="agentgetphoto.action?id=${agent.id }">
              	</td>
              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">资格证编号</td>
              <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="id" id="id" type="text" class="inputface" value="${agent.id }" disabled="disabled"/> 
              </td>
            <td class=tableheader1 width=10% height=30>姓名:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="name" id="name" type="text" class="inputface" value="${agent.name }" disabled="disabled"/>
           </td>
           <td class=tableheader1 width=10% height=30>登记日期:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="register_date" id="register_date" type="text" class="inputface" value="${agent.register_date }" disabled="disabled"/>
           </td>   
          
        	</tr>
            <tr>
               <td class=tableheader1 width=10% height=30>身份证号码:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="idCard" id="idCard" type="text" class="inputface" value="${agent.idCard }" maxlength="18" disabled="disabled"/>
           </td>   
            
               <td class=tableheader1 width=10% height=30>服务单位:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="unit" id="unit" type="text" class="inputface" value="${agent.unit }" disabled="disabled"/>
           </td> 
            	<td class=tableheader1 width=10% height=30>手机号码:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="cellphone" id="cellphone" type="text" class="inputface" value="${agent.cellphone }" maxlength="11" disabled="disabled"/>
           </td>   
           
           </tr>
           <tr>
           <td class=tableheader1 width=10% height=30>有效期止:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="validity" id="validity" type="text" class="inputface" value="${agent.validity }" disabled="disabled"/>
           </td>  
           <td class=tableheader1 width=10% height=30>单号最大办理数:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="max_lshs" id="max_lshs" type="text" class="inputface" value="${agent.max_lshs }" maxlength="4" disabled="disabled" />
           </td>  
           <td class=tableheader1 width=10% height=30>单天最大办理数:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="max_times_byday" id="max_times_byday" type="text" class="inputface" value="${agent.max_times_byday }" maxlength="4" disabled="disabled" />
           </td>          
            </tr>
            <tr>
            	<td class=tableheader1 width=10% height=30>备注:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="remark" id="remark" type="text" class="inputface" value="${agent.remark }" disabled="disabled"/>
           </td>
           <td class=tableheader1 width=10% height=30 >代理业务:</td>
          	<td width=500 bgColor=#f1f9fd height=30 colspan="5">&nbsp;
          	
	         	<c:forEach var="business" items="${qlist }" >
	         		${business.name }<input  name="code" id="${business.id }" type="checkbox" class="inputface" value=${business.id } disabled="disabled" />
	         	</c:forEach>
           </td>
            </tr>
          <tr>
           <td class=tableheader1 width=10% height=30 >状态:</td>
           <td width=150 bgColor=#f1f9fd height=30 >&nbsp;
           <select name="status" id="status" disabled="disabled" >
              		<option value="0">注销</option>
				  	<option value="1">正常</option>
				  	<option value="2">暂停</option>
            </select>
            </td>
            <td class=tableheader1 width=10% height=30>单位电话:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="unit_phone" id="unit_phone" type="text" class="inputface" value="${agent.unit_phone }" disabled="disabled"/>
           </td> 
           <td class=tableheader1 width=10% height=30>法人代表:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="incorporator" id="incorporator" type="text" class="inputface" value="${agent.incorporator }" disabled="disabled"/>
           </td>
          </tr>
          </table>
          <input name="button" type="button" class="button" value="返回" onClick="window.location='agentmsg.action'"/>
         
	</form>
	</td>
	</tr>
  <tr>
    <td ><img src="/queue/images/t-6.jpg" width="7" height="11"></td>
    <td height="11" background="/queue/images/t-7.jpg"></td>
    <td ><img src="/queue/images/t-8.jpg" width="8" height="11"></td>
  </tr>
</table>
</body>
</html>