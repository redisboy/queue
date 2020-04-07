<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"  %>
<%@ taglib uri="/webwork" prefix="ww"%>
<html>
  <head>
    <title>评价设置</title>
  </head>
  <meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/queue/ecside/css/ecside_style.css" />

<body>

<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">评价分值设置</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    
    <form action="" method="post" name="form1">
		<input type="hidden" name="isExist" value="${isExist}">
     <input name="flag" id="flag" type="hidden" value="1"/>
          <table width="50%" border="0" cellpadding="1" cellspacing="1" class="table_back">
           <tr>
           <td class=tableheader1 width=20% height=30>非常满意:</td>
           <td width=100 bgColor=#f1f9fd height=30>&nbsp;
           <select name="pj1" id="pj1" >
				  	<option value="4" <ww:if test="${value0=='4' }">selected</ww:if> >4</option>	
              		<option value="1" <ww:if test="${value0=='1' }">selected</ww:if> >1</option>
				  	<option value="2" <ww:if test="${value0=='2' }">selected</ww:if> >2</option>
				  	<option value="3" <ww:if test="${value0=='3' }">selected</ww:if> >3</option>
            </select>
            <select name="state1" id="state1" >
              		<option value="0" <ww:if test="${state0=='0' }">selected</ww:if> >无效</option>
                    <option value="1" <ww:if test="${state0=='1' }">selected</ww:if> >有效</option>
               </select>
            </td>
            
            </tr>
             <tr>
           <td class=tableheader1 width=10% height=30>满意:</td>
           <td width=100 bgColor=#f1f9fd height=30>&nbsp;
           <select name="pj2" id="pj2" >
				  	<option value="3" <ww:if test="${value1=='3' }">selected</ww:if> >3</option>
              		<option value="2" <ww:if test="${value1=='2' }">selected</ww:if> >2</option>
				  	<option value="1" <ww:if test="${value1=='1' }">selected</ww:if> >1</option>
				  	<option value="4" <ww:if test="${value1=='4' }">selected</ww:if> >4</option>	
            </select>
            <select name="state2" id="state2" >
              		<option value="0" <ww:if test="${state1=='0' }">selected</ww:if> >无效</option>
                    <option value="1" <ww:if test="${state1=='1' }">selected</ww:if> >有效</option>
               </select>
            </td>
            </tr>
            
             <tr>
           <td class=tableheader1 width=10% height=30>一般:</td>
           <td width=100 bgColor=#f1f9fd height=30>&nbsp;
           <select name="pj3" id="pj3" >
				  	<option value="2" <ww:if test="${value2=='2' }">selected</ww:if> >2</option>
              		<option value="3" <ww:if test="${value2=='3' }">selected</ww:if> >3</option>
				  	<option value="1" <ww:if test="${value2=='1' }">selected</ww:if> >1</option>
				  	<option value="4" <ww:if test="${value2=='4' }">selected</ww:if> >4</option>	
            </select>
            <select name="state3" id="state3" >
              		<option value="0" <ww:if test="${state2=='0' }">selected</ww:if> >无效</option>
                    <option value="1" <ww:if test="${state2=='1' }">selected</ww:if> >有效</option>
               </select>
            </td>
            </tr>
            
             <tr>
           <td class=tableheader1 width=10% height=30>不满意:</td>
           <td width=400 bgColor=#f1f9fd height=30>&nbsp;
           <select name="pj4" id="pj4" >
				  	<option value="1" <ww:if test="${value3=='1' }">selected</ww:if> >1</option>	
              		<option value="4" <ww:if test="${value3=='4' }">selected</ww:if> >4</option>
				  	<option value="2" <ww:if test="${value3=='2' }">selected</ww:if> >2</option>
				  	<option value="3" <ww:if test="${value3=='3' }">selected</ww:if> >3</option>
            </select>
             <select name="state4" id="state4" >
              		<option value="0" <ww:if test="${state3=='0' }">selected</ww:if> >无效</option>
                    <option value="1" <ww:if test="${state3=='1' }">selected</ww:if> >有效</option>
             </select>

            </td>
            </tr>
          </table>
          <input name="queryy" type="button" class="button" value="${isExist eq 0 ? '添加' : '保存'}" onClick="query()"/>
          <br/>
         
	</form>
    
	<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" >

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

<script type="text/javascript">
function query() { 
	document.form1.action = "setEvaluation!updateEvaluat.action";
	document.form1.submit();
}
</script>
</html>