<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="/webwork" prefix="ww"%>

<html>
  <head>
    <title>考核类型选择</title>
  </head>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/queue/js/jquery.js"></script>
<script type="text/javascript">
function toDo() {
var urlFlag=document.getElementById("urlFlag").value;
var toFlag = document.getElementById("transmode").value;
if(urlFlag==0){// 办理时间
  document.form1.action = "getEmplooyTransactTimeOrder.action?flag="+toFlag+"&tjms=1";
  }
if(urlFlag==1){//差评
  document.form1.action = "getEmplooyBadReviewOrder.action?flag="+toFlag+"&tjms=1";
  }
if(urlFlag==2){//暂停时间
  document.form1.action = "getEmplooyPauseTimeOrder.action?flag="+toFlag+"&tjms=1";
  }
if(urlFlag==3){//办理时间预警
  document.form1.action = "getEmplooyTransactTimeWarning.action?flag="+toFlag+"&tjms=1";
  }
if(urlFlag==4){//差评率预警
  document.form1.action = "getEmplooyBadReviewWarning.action?flag="+toFlag+"&tjms=1";
  }
if(urlFlag==5){//暂停时间预警
  document.form1.action = "getEmplooyPauseTimeWarning.action?flag="+toFlag+"&tjms=1";
  }

 document.form1.submit();
}
//选择部门得到相应的大厅下拉框
function deptCodeChange(){
	var deptCode=$('#deptCode').children('option:selected').val();
	if(deptCode==''){
		$('#hall').empty();
		$('#depthalldiv').hide();
	}else{
		$('#hall').empty();
		$('#depthalldiv').show();
		$.ajax({
			url:"findDepthallbyDeptcode.action",
			async:false,
			data:"deptCode="+deptCode,
			success:function(data){
				$('#hall').html(data);
			}
		
		});
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
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">考核类型选择</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    
    <!-- urlFlag指定跳转到哪个url -->
    <input id="urlFlag" value="${urlFlag}" type="hidden"/>
    
    <form action="" method="post" name="form1">
     <input name="flag" id="flag" type="hidden" value="1"/>
     	<ww:if test="${role eq 0}">
     		<div id="area" >
     			<table width="50%" border="0" cellpadding="1" cellspacing="1" class="table_back">
            		<tr>
		              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">统计方式:</td>
		              <td width="10%" bgColor=#f1f9fd height=30>&nbsp;
		                <select name="transmode" id="transmode">
						  <option value="0"  >按照员工</option>
						  <option value="1"  >按照管理部门</option>
						</select>
		              </td>
<!--            		<tr>-->
<!--		              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">考核类型选择:</td>-->
<!--		              <td width="10%" bgColor=#f1f9fd height=30>&nbsp;-->
<!--		                <select name="transmode" id="transmode">-->
<!--						  <option value="0"  >大厅</option>-->
<!--						  <option value="1"  >管理部门</option>-->
<!--						</select>-->
<!--		              </td>-->
         		</table>
     		</div>
     		<div id="code" style="">
     			<table width="50%" border="0" cellpadding="1" cellspacing="1" class="table_back">
            		<tr>
		              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">部门选择:</td>
		              <td width="10%" bgColor=#f1f9fd height=30>&nbsp;
		                <select name="deptCode" id="deptCode" onchange="deptCodeChange()">
		                	<option value=""  >全部部门</option>
		                	<c:forEach items="${deptCodeInfoMap}" var="entry">
		                		<option value="${entry.key}"  >${entry.value}</option>
		                	</c:forEach>
						</select>
		              </td>
         		</table>
     		</div>
     		<div id="depthalldiv" style="display: none">
     			<table width="50%" border="0" cellpadding="1" cellspacing="1" class="table_back">
            		<tr>
		              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">大厅选择:</td>
		              <td width="10%" bgColor=#f1f9fd height=30>&nbsp;
		                <span id="hall">
		                	
		                </span>
		              </td>
         		</table>
     		</div>
     	</ww:if>
        
        <ww:if test="${role eq 1}">
        	<div id="area" >
     			<table width="50%" border="0" cellpadding="1" cellspacing="1" class="table_back">
            		<tr>
		              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">统计方式:</td>
		              <td width="10%" bgColor=#f1f9fd height=30>&nbsp;
		                <select name="transmode" id="transmode">
						  <option value="0"  >按照员工</option>
						  <option value="1"  >按照管理部门</option>
						</select>
		              </td>
		              
         		</table>
     		</div>
        	<div id="hall" style="">
     			<table width="50%" border="0" cellpadding="1" cellspacing="1" class="table_back">
            		<tr>
		              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">大厅选择:</td>
		              <td width="10%" bgColor=#f1f9fd height=30>&nbsp;
		                <select name="deptHall" id="deptHall">
		                	<option value=""  >全部大厅</option>
		                	<c:forEach items="${deptHallInfoMap}" var="entry">
		                		<option value="${entry.key}"  >${entry.value}</option>
		                	</c:forEach>
						</select>
		              </td>
         		</table>
     		</div>
        </ww:if>
        <ww:if test="${role eq 2}">
        	<div id="area" >
     			<table width="50%" border="0" cellpadding="1" cellspacing="1" class="table_back">
            		<tr>
		              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">统计方式:</td>
		              <td width="10%" bgColor=#f1f9fd height=30>&nbsp;
		                <select name="transmode" id="transmode">
						  <option value="0"  >按照员工</option>
						  <option value="1"  >按照管理部门</option>
						</select>
		              </td>
         		</table>
     		</div>
        </ww:if>
          <br>
          <input name="button" type="button" class="button" value="确定" onClick="toDo()"/>
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
</html>