<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="/webwork" prefix="ww"%>

<html>
  <head>
    <title>LED信息屏设置</title>
  </head>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
function setLed() { 
	var address = document.getElementById("address").value;
	if(address == ""){
		alert("LED地址不能为空!");
		return;
	}
//	var nrmb1 = document.getElementById("nrmb1").value;
//	var nrmb2 = document.getElementById("nrmb2").value;
//	document.getElementById("content").value =nrmb1+"号到"+nrmb2+"号窗口" ;
	  document.form1.action = "insertLED_TV.action?flag1=2";
	  document.form1.submit();
}
function setLzLed() { 
	var address = document.getElementById("address").value;
	if(address == ""){
		alert("LED地址不能为空!");
		return;
	}
//	var nrmb1 = document.getElementById("nrmb1").value;
//	var nrmb2 = document.getElementById("nrmb2").value;
//	document.getElementById("content").value =nrmb1+"号到"+nrmb2+"号窗口" ;
	  document.form1.action = "insertLED_TV.action?flag1=1";
	  document.form1.submit();
}
function updateLED(id) { 
	  document.form1.action = "toUpdateLED_TV.action?id="+id;
	  document.form1.submit();

}
function delLED_tv(id){
	if(confirm("确定删除该信息吗?")){
		document.form1.action = "delLED_TV.action?address="+id;
	 	document.form1.submit();
	}
}
function setLED_tv(id) { 
	$.ajax({type: "POST", cache: false,
		url: 'setLED_tv1.action?id='+id,
		success: function (req) {
           alert(req);		  
		}
	});
}
</script>
<body>
<form action="" method="post" name="form1">
<input type="hidden" name="isExist" value="${isExist}">
<input name="flag" id="flag" type="hidden" value="1"/>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">LED信息屏设置</span>
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
	              		<input name="address" id="address" type="text" class="inputface" value=""/>
	              </td>
	             <td class=tableheader1 width=10% height=30>COM口:</td>
	          	 <td width=150 bgColor=#f1f9fd height=30>&nbsp;
		          		<select name="com1" id="com1">
							<option value="">-请选择-</option>
							<option value="1">COM-1</option>
							<option value="2">COM-2</option>
							<option value="3">COM-3</option>
							<option value="4">COM-4</option>
							<option value="5">COM-5</option>
							<option value="6">COM-6</option>
							<option value="7">COM-7</option>
							<option value="8">COM-8</option>
							<option value="9">COM-9</option>
							<option value="10">COM-10</option>
						</select>
	             </td>
	             <td class=tableheader1 width=10% height=30>字体颜色:</td>
	          	 <td width=150 bgColor=#f1f9fd height=30>&nbsp;
		          	<select name="fontColor" id="fontColor">
						<option value="1">红色</option>
						<option value="2">绿色</option>
						<!--	<option value="3">黄色</option> -->
					</select>
	             </td>
	 		</tr>
            <tr>
              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">通讯模式:</td>
              <td width=150 bgColor=#f1f9fd height=30>&nbsp;
                <select name="transmode" id="transmode">
				  <option value="2">232通讯</option>
				  <option value="3">485通讯</option>
				</select>
              </td>
             <td class=tableheader1 width=10% height=30>单双色:</td>
          	 <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <select name="dbcolor" id="dbcolor">
				  <option value="1">单色</option>
				  <option value="2">双色</option>
			  </select>
             </td>
             <td class=tableheader1 width=10% height=30>波特率:</td>
          	 <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <select name="sBaud" id="sBaud">
				  <option value="9600">9600</option>
				  <option value="115200">115200</option>
			  </select>
             </td>
 		  </tr>
          <tr>
             <td class=tableheader1 width=10% height=30>屏高:</td>
          	 <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	           <input name="sHeight" id="sHeight" type="text" class="inputface" value=""/>
             </td> 
             <td class=tableheader1 width=10% height=30>屏宽:</td>
             <td width=150 bgColor=#f1f9fd height=30>&nbsp;
               <input name="sWidth" id="sWidth" type="text" class="inputface" value=""/>
             </td>
             <td class=tableheader1 width=10% height=30>内容模板：</td>
          	 <td width=150 bgColor=#f1f9fd height=30>&nbsp;
          	 	<input name="content" id="content" type="text" class="inputface" value=""/>
          		<!-- <input name="nrmb1" id="nrmb1" type="text" class="inputface" value="" style="width: 40px"/>号到<input name="nrmb2" id="nrmb2" type="text" class="inputface" value="" style="width: 40px;"/>号窗口 -->
             </td>
           </tr>
           <tr>
             <td class=tableheader1 width=10% height=30>间隔数</td>
          	 <td width=150 bgColor=#f1f9fd height=30>&nbsp;
          	 	<input name="space" id="space" type="text" class="inputface" value=""/>&nbsp;<font id="winc" color="#ff0000">*请录入数字(必填)</font>
             </td> 
             <td class=tableheader1 width=10% height=30>使用的字库：</td>
          	 <td width=150 bgColor=#f1f9fd height=30>&nbsp;
          	 	<select name="lattice" id="lattice">
					<option value="16">16</option>
					<option value="32">32</option>
				</select>
             </td>
             <td class=tableheader1 width=10% height=30>所属大厅</td>
             <td width=150 bgColor=#f1f9fd height=30>&nbsp;
             	<select name="waitingArea" id="waitingArea" >
             	 <option value="">请选择</option>
	             <c:forEach var="item" items="${businessList}" varStatus="vs">
	                    <option value="${item.waitingarea}">${item.waitingarea}</option>
	             </c:forEach>
	           </select>
             </td>
           </tr>
          </table>
          <br>
          <input name="button" type="button" class="button" value="添加队列屏" onClick="setLed()"/>
          <input name="button" type="button" class="button" value="添加领证屏" onclick="setLzLed()">
          <!-- <input name="button" type="button" class="button" value="设置屏参" onClick="setLED_tv()"/> -->
          <br/><br/>
          <table width="98%" align="center" border="1" cellpadding="0" cellspacing="0" >
			<tr class="odd "  style="text-align:center">
				<td width="8%" height="35" bgcolor="#F1F9FD" class="tableheader1" align="center"><div align="center">LED屏地址</div></td>
				<td width="5%" height="35" bgcolor="#F1F9FD" class="tableheader1" align="center"><div align="center">COM口</div></td>
				<td width="8%" height="35" bgcolor="#F1F9FD" class="tableheader1" align="center"><div align="center">字体颜色</div></td>
				<td width="8%" height="35" bgcolor="#F1F9FD" class="tableheader1" align="center"><div align="center">通讯模式</div></td>
				<td width="6%" height="35" bgcolor="#F1F9FD" class="tableheader1" align="center"><div align="center">单双色</div></td>
				<td width="6%" height="35" bgcolor="#F1F9FD" class="tableheader1" align="center"><div align="center">波特率</div></td>
				<td width="6%" height="35" bgcolor="#F1F9FD" class="tableheader1" align="center"><div align="center">屏高</div></td>
				<td width="6%" height="35" bgcolor="#F1F9FD" class="tableheader1" align="center"><div align="center">屏宽</div></td>
				<td width="6%" height="35" bgcolor="#F1F9FD" class="tableheader1" align="center"><div align="center">内容模板</div></td>
				<td width="6%" height="35" bgcolor="#F1F9FD" class="tableheader1" align="center"><div align="center">间隔数</div></td>
				<td width="6%" height="35" bgcolor="#F1F9FD" class="tableheader1" align="center"><div align="center">使用的字库</div></td>
				<td width="6%" height="35" bgcolor="#F1F9FD" class="tableheader1" align="center"><div align="center">所属大厅</div></td>
				<td width="16%" height="35" bgcolor="#F1F9FD" class="tableheader1" align="center"><div align="center">操作</div></td>
			</tr>
			<c:forEach items="${list}" var="list">
				<tr class="odd "  style="text-align:center">
				<td width="8%" height="35" bgcolor="#F1F9FD" align="center"><div align="center">${list.address}</div></td>
				<td width="5%" height="35" bgcolor="#F1F9FD" align="center"><div align="center">COM-${list.com1}</div></td>
				<td width="8%" height="35" bgcolor="#F1F9FD" align="center"><div align="center">${list.fontColor}</div></td>
				<td width="8%" height="35" bgcolor="#F1F9FD" align="center"><div align="center">${list.transmode}</div></td>
				<td width="6%" height="35" bgcolor="#F1F9FD" align="center"><div align="center">${list.dbcolor}</div></td>
				<td width="6%" height="35" bgcolor="#F1F9FD" align="center"><div align="center">${list.baud}</div></td>
				<td width="6%" height="35" bgcolor="#F1F9FD" align="center"><div align="center">${list.height}</div></td>
				<td width="6%" height="35" bgcolor="#F1F9FD" align="center"><div align="center">${list.width}</div></td>
				<td width="12%" height="35" bgcolor="#F1F9FD" align="center"><div align="center">${list.content}</div></td>
				<td width="6%" height="35" bgcolor="#F1F9FD" align="center"><div align="center">${list.space}</div></td>
				<td width="6%" height="35" bgcolor="#F1F9FD" align="center"><div align="center">${list.lattice}</div></td>
				<td width="16%" height="35" bgcolor="#F1F9FD" align="center"><div align="center">${list.waitingArea}</div></td>
				<td width="16%" height="35" bgcolor="#F1F9FD" align="center"><div align="center">${list.operation}</div></td>
			</tr>
			</c:forEach>
		  </table>
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

</form>

</body>
</html>