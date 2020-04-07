<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="/webwork" prefix="ww"%>

<html>
  <head>
    <title>LED设置</title>
  </head>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
function IsDigit(cCheck) { return (('0'<=cCheck) && (cCheck<='9')); }
function setLed() {
  /*
  var playSpeed =document.getElementById("playSpeed").value;
   
  for (var nIndex=0; nIndex<playSpeed.length; nIndex++)
	    {
	        cCheck = playSpeed.charAt(nIndex);

	        if (!IsDigit(cCheck))
	        {
	            alert("显示速度只能为数字");
	            document.getElementById("playSpeed").focus();
	            return;
	        }
  }
  if(playSpeed > 255){
  	alert("显示速度最大为255");
	document.getElementById("playSpeed").focus();
	return;
  }*/
  document.form1.action = "updateLED.action";
  document.form1.submit();

}
function setScreenPara() { 
	$.ajax({type: "POST", cache: false,
		url: "setScreenPara.action",
		success: function (req) {
           alert(req);		  
		}
	});
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
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">LED显示设置</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    
    <form action="" method="post" name="form1">
        <input type="hidden" name="isExist" value="${isExist}">
     <input name="flag" id="flag" type="hidden" value="1"/>
          <table width="80%" border="0" cellpadding="1" cellspacing="1" class="table_back">
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
	            <td class=tableheader1 width=10% height=30>高:</td>
	          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
		           <input name="sHeight" id="sHeight" type="text" class="inputface" value="${led.height }"/>
	            </td> 
	           
	            <td class=tableheader1 width=10% height=30>宽:</td>
	            <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	               <input name="sWidth" id="sWidth" type="text" class="inputface" value="${led.width }"/>
	            </td>
	            <td class=tableheader1 width=10% height=30><!--  显示速度:--></td>
	          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          		<!-- <input name="playSpeed" id="playSpeed" type="text" class="inputface" value="${led.playSpeed }"/>
	          		<font color="red">速度(0-255) 值越大速度越慢</font> -->
	            </td>
        	</tr>
        	<!-- 
        	<tr>
	            <td class=tableheader1 width=10% height=30>显示内容:</td>
	          	<td width=90% bgColor=#f1f9fd height=30 colspan="5">&nbsp;
		           <textarea name="content" rows="5" cols="5" style="width:90%">${led.content }</textarea>
	            </td> 
        	</tr>
        	<tr>
              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">是否滚动:</td>
              <td width=150 bgColor=#f1f9fd height=30>&nbsp;
                <select name="isOpenGunDong" id="isOpenGunDong">
				  <option value="0" <ww:if test="${led.isOpenGunDong=='0'}">selected</ww:if> >是</option>
				  <option value="1" <ww:if test="${led.isOpenGunDong=='1'}">selected</ww:if> >否</option>
				</select>
              </td>
              <td class=tableheader1 width=10% height=30></td>
          	  <td width=150 bgColor=#f1f9fd height=30>&nbsp;
              </td>
              <td class=tableheader1 width=10% height=30></td>
          	  <td width=150 bgColor=#f1f9fd height=30>&nbsp;
              </td>
 			</tr> -->
        	

          </table>
          <br>
          <input name="button" type="button" class="button" value="保存数据" onClick="setLed()"/>
          <input name="button" type="button" class="button" value="发送屏参" onClick="setScreenPara()"/>
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