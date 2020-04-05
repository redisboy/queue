<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"  %>
<html>
  <head>
    <title>代理人管理</title>
  </head>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/queue/ecside/css/ecside_style.css" />
<script type="text/javascript" src="/queue/plugs/time/time.js" ></script> 

<body onload="loc();">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">代理人年检</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    
    <form action="" method="post" name="form1" enctype="multipart/form-data">
        <input type="hidden" name="name" value="${agent.name}" />
        <input type="hidden" name="idCard" value="${agent.idCard}" />
        <input name="flag" id="flag" type="hidden" value="1"/>
          <table width="80%" border="0" cellpadding="1" cellspacing="1" class="table_back">
           <tr>
            <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">资格证编号</td>
              <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input type="text" class="inputface" value="${agent.id}" disabled="disabled"/>  </td>
            <td class=tableheader1 width=10% height=30>身份证号码:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input type="text" class="inputface" value="${agent.idCard}" disabled="disabled"/>
           </td>   
            <td class=tableheader1 width=10% height=30>备注:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input type="text" class="inputface" value="${agent.remark }" disabled="disabled"/>
           </td>
          </tr>
            <tr>
            <td class=tableheader1 width=10% height=30>姓名:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input type="text" class="inputface" value="${agent.name}" disabled="disabled"/>
           </td>
           <td class=tableheader1 width=10% height=30>登记日期:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input type="text" class="inputface" value="${agent.register_date }" disabled="disabled"/>
           </td>   
           <td class=tableheader1 width=10% height=30 >状态 :</td>
           <td width=150 bgColor=#f1f9fd height=30 >&nbsp;
           <select name="status" id="status" >
              		<option value="0">注销</option>
				  	<option value="1">正常</option>
				  	<option value="2">暂停</option>
            </select><span style="color: red;">*</span>
            </td>
            
        	</tr>
            <tr>
               
            
               <td class=tableheader1 width=10% height=30>服务单位:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input type="text" class="inputface" value="${agent.unit }" disabled="disabled"/>
           </td> 
            	<td class=tableheader1 width=10% height=30>手机号码:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input type="text" class="inputface" value="${agent.cellphone }" disabled="disabled"/>
           </td>   
           <td class=tableheader1 width=10% height=30>单位电话:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input type="text" class="inputface" value="${agent.unit_phone }" disabled="disabled"/>
           </td> 
           </tr>
           <tr>
           <td class=tableheader1 width=10% height=30>有效期止:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="validity" id="validity" type="text" class="inputface" readonly value="${agent.validity}" onclick="setday(this)" /><span style="color: red;">*</span>
           </td> 
           <td class=tableheader1 width=15% height=30>单号最大办理数:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input type="text" class="inputface" value="${agent.max_lshs }" disabled="disabled"/>
           </td>  
          <td class=tableheader1 width=15% height=30>单天最大办理数:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input type="text" class="inputface" value="${agent.max_times_byday }" disabled="disabled"/>
           </td>     
            </tr>
            <tr>
             <td class=tableheader1 width=10% height=30>法人代表:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input type="text" class="inputface" value="${agent.incorporator }" disabled="disabled"/>
           </td>
            <td bgColor=#f1f9fd	 height=30 colspan="4">&nbsp;</td>
          	
            </tr>
            <tr>
          		<td colspan="6" bgcolor="#FFFFFF" class="tableheader1" height="30">代理业务</td>
          	</tr>
          </table>
          <table cellpadding="0" cellspacing="0"  style="width:80%">
	      		<tr>
	      			<td width="10%"></td>
	      			<td width="150"></td>
	      			<td width="10%"></td>
	      			<td width="150"></td>
	      			<td width="10%"></td>
	      			<td width="150"></td>
	      		</tr>
	   			<c:forEach var="item" items="${result}">
	   				<tr height="75px">
	   					<td bgcolor="#FFFFFF" class="tableheader1" colspan="2" style="border:solid #4482d3;border-width:0px 1px 1px 1px;color: #10418c;font-weight:bold;text-align:center">${item.key}</td>
	   					<td bgColor=#f1f9fd colspan="4" style="border:solid #4482d3;border-width:0px 1px 1px 0px;color: #10418c;font-weight:bold;padding-left:10px">
	   						<c:forEach var="business" items="${item.value}">
	   							${business.name}
	   							<input  name="code" id="${business.id }" type="hidden" value="${business.id}"/>
	   						<input  name="stroke" id="${business.id }" type="text" class="inputface" style="width:30px" value="${business.ywbs}"/>笔&nbsp;&nbsp;&nbsp;
	   						</c:forEach>
	   						&nbsp;
	   					</td>
	   				</tr>
	   			</c:forEach>
	   	  </table>
          <div style="color: red; float: inherit" >带*的为可修改项</div>
          <input type="button" class="button" value="审批" onClick="upd()"/>
          <input type="button" class="button" value="返回" onClick="window.location='dlrnj.action'"/>
          <br/>
	</form>
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
<script type="text/javascript">
function upd() { 
	//验证数据
	//var code=document.getElementsByName("code");
	//var checked=false;
	//for(var i=0;i<code.length;i++){	
	//		if(code[i].checked){
	//			checked=true;
	//		}		
	//	}
	//if(!checked){
	//	alert("请选择资质");
	//	return false;
	//}
	var id=${agent.id};
	document.form1.action = "agentnjUpd.action?id="+id;
	document.form1.submit();
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
</html>
