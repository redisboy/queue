<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="/webwork" prefix="ww"%>

<html>
  <head>
    <title>LED队列屏修改</title>
  </head>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
//function window.onload(){
//	var content = document.getElementById("content").value;
//	var content1 = document.getElementById("nrmb1").value = content.substring(0,content.indexOf("号到"));
//	var content2 = document.getElementById("nrmb2").value = content.substring(content.indexOf("号到")+2,content.indexOf("号窗口"));
//}
function setLed() { 
	var address = document.getElementById("address").value;
	if(address == ""){
		alert("LED地址不能为空!");
		return;
	}
//	var nrmb1 = document.getElementById("nrmb1").value;
//	var nrmb2 = document.getElementById("nrmb2").value;
//	document.getElementById("content").value =nrmb1+"号到"+nrmb2+"号窗口";
 	document.form1.action = "updateLED_TV.action";
  	document.form1.submit();

}
</script>
<body>
<form action="" method="post" name="form1">
<input type="hidden" name="isExist" value="${isExist}">
<input name="flag" id="flag" type="hidden" value="1"/>
<input name="flag1" id="flag1" type="hidden" value="${led.flag}"/>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">LED队列屏修改</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
        <table width="80%" border="0" cellpadding="1" cellspacing="1" class="table_back">
	        <tr>
	              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">LED屏地址:</td>
	              <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	              		<input name="address" id="address" type="text" class="inputface" value="${led.address }"/>
	              </td>
	             <td class=tableheader1 width=10% height=30>COM口:</td>
	          	 <td width=150 bgColor=#f1f9fd height=30>&nbsp;
		          		<select name="com1" id="com1">
							<option value="">-请选择-</option>
							<option value="1" <ww:if test="${led.com1=='1'}">selected</ww:if>>COM-1</option>
							<option value="2" <ww:if test="${led.com1=='2'}">selected</ww:if>>COM-2</option>
							<option value="3" <ww:if test="${led.com1=='3'}">selected</ww:if>>COM-3</option>
							<option value="4" <ww:if test="${led.com1=='4'}">selected</ww:if>>COM-4</option>
							<option value="5" <ww:if test="${led.com1=='5'}">selected</ww:if>>COM-5</option>
							<option value="6" <ww:if test="${led.com1=='6'}">selected</ww:if>>COM-6</option>
							<option value="7" <ww:if test="${led.com1=='7'}">selected</ww:if>>COM-7</option>
							<option value="8" <ww:if test="${led.com1=='8'}">selected</ww:if>>COM-8</option>
							<option value="9" <ww:if test="${led.com1=='9'}">selected</ww:if>>COM-9</option>
							<option value="10" <ww:if test="${led.com1=='10'}">selected</ww:if>>COM-10</option>
						</select>
	             </td>
	             <td class=tableheader1 width=10% height=30>字体颜色:</td>
	          	 <td width=150 bgColor=#f1f9fd height=30>&nbsp;
		          	<select name="fontColor" id="fontColor">
						<option value="1" <ww:if test="${led.fontColor=='1'}">selected</ww:if>>红色</option>
						<option value="2" <ww:if test="${led.fontColor=='2'}">selected</ww:if>>绿色</option>
						<!--	<option value="3">黄色</option> -->
					</select>
	             </td>
	 		</tr>
            <tr>
              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">通讯模式:</td>
              <td width=150 bgColor=#f1f9fd height=30>&nbsp;
                <select name="transmode" id="transmode">
				  <option value="2" <ww:if test="${led.transmode=='2'}">selected</ww:if> >232通讯</option>
				  <option value="3" <ww:if test="${led.transmode=='3'}">selected</ww:if> >485通讯</option>
				</select>
              </td>
             <td class=tableheader1 width=10% height=30>单双色:</td>
          	 <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <select name="dbcolor" id="dbcolor">
				  <option value="1" <ww:if test="${led.dbcolor=='1'}">selected</ww:if> >单色</option>
				  <option value="2" <ww:if test="${led.dbcolor=='2'}">selected</ww:if> >双色</option>
			  </select>
             </td>
             <td class=tableheader1 width=10% height=30>波特率:</td>
          	 <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <select name="sBaud" id="sBaud">
				  <option value="9600" <ww:if test="${led.baud=='9600'}">selected</ww:if> >9600</option>
				  <option value="115200" <ww:if test="${led.baud=='115200'}">selected</ww:if> >115200</option>
			  </select>
             </td>
 		  </tr>
          <tr>
             <td class=tableheader1 width=10% height=30>屏高:</td>
          	 <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	           <input name="sHeight" id="sHeight" type="text" class="inputface" value="${led.height }"/>
             </td> 
             <td class=tableheader1 width=10% height=30>屏宽:</td>
             <td width=150 bgColor=#f1f9fd height=30>&nbsp;
               <input name="sWidth" id="sWidth" type="text" class="inputface" value="${led.width }"/>
             </td>
             <td class=tableheader1 width=10% height=30>内容模板：</td>
          	 <td width=150 bgColor=#f1f9fd height=30>&nbsp;
          		<input name="content" id="content" value="${led.content}"/>
          		<!-- <input name="nrmb1" id="nrmb1" type="text" class="inputface" value="" style="width: 30px"/>号到<input name="nrmb2" id="nrmb2" type="text" class="inputface" value="" style="width: 30px;"/>号窗口
              -->
             </td>
           </tr>
           <tr>
           	 <td class=tableheader1 width=10% height=30>间隔数</td>
          	 <td width=150 bgColor=#f1f9fd height=30>&nbsp;
          	 	<input name="space" id="space" type="text" class="inputface" value="${led.space}"/>
             </td>
             <td class=tableheader1 width=10% height=30>使用的字库：</td>
          	 <td width=150 bgColor=#f1f9fd height=30>&nbsp;
          	 	<select name="lattice" id="lattice">
					<option value="16" <ww:if test="${led.lattice=='16'}">selected</ww:if> >16</option>
					<option value="32" <ww:if test="${led.lattice=='32'}">selected</ww:if> >32</option>
				</select>
             </td> 
             <td class=tableheader1 width=10% height=30>所属大厅</td>
             <td width=150 bgColor=#f1f9fd height=30>&nbsp;
             	<select name="waitingArea" id="waitingArea" >
             	 <option value="">请选择</option>
	             <c:forEach var="item" items="${businessList}" varStatus="vs">
	                    <option value="${item.waitingarea}" <c:if test="${item.waitingarea==waitArea }">selected</c:if>>${item.waitingarea}</option>
	             </c:forEach>
	           	</select>
             </td>
           </tr>

          </table>
          <br>
          <input name="button" type="button" class="button" value="保存" onClick="setLed()"/>
          <input name="bt2" class="button" type="button" value=" 返回 " onClick="window.history.back(-1);" />
          <br/>
         
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

</body>
</html>