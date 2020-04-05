﻿﻿<%@ page language="java" contentType="text/html;charset=UTF-8" %>
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
//修改
function upd() { 
	var idCard=document.getElementById("idCard").value;
	//var max_lshs=document.getElementById("max_lshs").value;
	//var max_times_byday=document.getElementById("max_times_byday").value;
	var name=document.getElementById("name").value;
	var cellphone=document.getElementById("cellphone").value;
	function IsDigit(cCheck) { return (('0'<=cCheck) && (cCheck<='9')); }
	function IsAlpha(cCheck) { return ((('a'<=cCheck) && (cCheck<='z')) || (('A'<=cCheck) && (cCheck<='Z'))) }  
	if(idCard==""){
		alert("请填写【身份证号码】");
		document.getElementById("idCard").focus();
		return false;
	}
//	 for (nIndex=0; nIndex<idCard.length; nIndex++)
//	    {
//	        cCheck = idCard.charAt(nIndex);

//	        if (!IsDigit(cCheck))
//	        {
//	            alert("【身份证】只包含数字");
//	            document.getElementById("idCard").focus();
//	            return false;
//	        }
//	    }
	if(idCard.length!=15&&idCard.length!=18){
		alert("身份证号码位数错误");
		document.getElementById("idCard").focus();
	    return false;
	}
	if(name==""){
		alert("请填写【姓名】");
		document.getElementById("name").focus();
		return false;
	}
	var reg = /^[\u4e00-\u9fa5]+$/i; 
	 if (!reg.test(name)){ 
	   	alert("请输入中文名字！");   
	 	document.getElementById("name").focus();   
	 	return false;   
	 }
	
    if(cellphone.length!=""){
		if (cellphone.length != 11 || cellphone.substring(0,1)!="1") {	
	        alert("您的【手机号码】输入错误，请仔细检查后再输入正确号码");
	        document.getElementById("cellphone").focus();
	        return false;
	    }
	    for (nIndex=0; nIndex<cellphone.length; nIndex++)
	    {
	        cCheck = cellphone.charAt(nIndex);
	
	        if (!IsDigit(cCheck))
	        {
	            alert("【手机号码】只包含数字");
	            document.getElementById("cellphone").focus();
	            return false;
	        }
	    }
    }
   /* if(max_times_byday==""){
		alert("请填写【单天最大办理数】");
		document.getElementById("max_times_byday").focus();
		return false;
	}
    if(max_lshs==""){
		alert("请填写【单号最大办理数】");
		document.getElementById("max_lshs").focus();
		return false;
	}
    for (nIndex=0; nIndex<max_lshs.length; nIndex++)
    {
        cCheck = max_lshs.charAt(nIndex);

        if (!IsDigit(cCheck))
        {
            alert("单号最大办理数只包含数字");
            document.getElementById("max_lshs").focus();
            return false;
        }
    }
    for (nIndex=0; nIndex<max_times_byday.length; nIndex++)
    {
        cCheck = max_times_byday.charAt(nIndex);

        if (!IsDigit(cCheck))
        {
            alert("单天最大办理数只包含数字");
            document.getElementById("max_times_byday").focus();
            return false;
        }
    }*/
	var id=${agent.id};
	document.form1.action = "agentUpd.action?id="+id;
	document.form1.submit();
}
function IsDigit(cCheck) { return (('0'<=cCheck) && (cCheck<='9')); }
//复选框
//function check(){	
//	var code=document.getElementsByName('code');//workid
//	var stroke=document.getElementsByName('stroke');
//	var s = '${stype}';		//业务ID
//	var yw = '${stroke}';    //业务笔数
//	var array=new Array();
//	array=s.split(",");
//	ywarray=yw.split(",");
//	for(var i=0;i<code.length;i++){
//	/*	for(var j=0;j<array.length;j++){
//			if(array[j]==code[i].value){
//				code[i].checked=true;
//			}
//		}*/
//		if(ywarray[i]==null){
//			stroke[i].value=0;
//		}else{
//			stroke[i].value=ywarray[i];
//		}
//		//stroke[i].value=ywarray[i];
//	}	
//}
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
function  btnSetFormat_onclick() {
    jsrzP.OnSetVideoFormat();
}
function  btnSelSource_onclick() {
    jsrzP.OnSelectSrc();
}
function  btnLoadDev_onclick() {
    jsrzP.nW = 300;
    jsrzP.nH = 320;
    jsrzP.OnContorlInit();
}
function btnCapture_onclick() {
	
    jsrzP.sZpImgFilePath = "c:\\88888.jpg";
    jsrzP.OnCaptureZp();
    showpic();
}
//照片
function showpic(){
	var imgstr=document.getElementById("base64Code").value;
	document.getElementById('picdiv').innerHTML = "<img id='pic' width=300 height=320 src='c:\\88888.jpg'>";
}
//摄像头
function upobj(){
	document.getElementById('jsrzP').width="300";
	document.getElementById('jsrzP').height="320";
	btnLoadDev_onclick();
}
function  btnCZ_onclick() {
    xhWebFingerCtrl.OnInitDev();
  	xhWebFingerCtrl.OnCaptureFinger();
}
function checkType(){
var codeval = document.getElementsByName("code");
var strokeval = document.getElementsByName("stroke");
	for (i = 0; i < codeval.length; i++) { 
		if (codeval[i].checked) { 
		}else{
			strokeval[i].value="";
		}
	}
}
</script>
<SCRIPT LANGUAGE=javascript FOR=jsrzP EVENT="FireCaptureZpEvent(pszInfo1)">
		document.getElementById("base64Code").value=pszInfo1;
</SCRIPT>
<script type="text/javascript" for="xhWebFingerCtrl" event="CaptureFingerEvent(lpBuffer,lpRst)">
    document.getElementById("base64Code2").value=lpBuffer;
</script>
<SCRIPT LANGUAGE=javascript FOR=xhWebFingerCtrl EVENT="CaptureFingerEvent(lpBuffer,lpRst)">
<!--
    //If lpRst = "1" Then
     //   alert("采集成功！");
	//	alert(lpBuffer);
		tempstr=lpBuffer;
	//	alert(tempstr);
   	//	document.getElementById("base64Code2").value = tempstr;
    //End If
//-->
</SCRIPT>
<SCRIPT ID=clientEventHandlersJS LANGUAGE=javascript>
var tempstr="";
	function  btnCZ_onclick()
	{
   		tempstr = xhWebFingerCtrl.OnCaptureFinger();
   		if("" == tempstr || null == tempstr){
   			alert("采集失败，请重新采集");
   			return;
   		}
   		document.getElementById("base64Code2").value = tempstr;
	}
//比对
	function  btnBD_onclick()
	{
    	var stat = xhWebFingerCtrl.OnIdentifyFinger(tempstr);
		//	alert(stat);
		if (stat==0){alert("成功");}else{alert("失败");}
	}
//-->
</script>
<body onload="loc();">
<object id="xhWebFingerCtrl" width="0" height="0" classid="clsid:BB16B5DF-898F-4E1D-AABA-B23DB9E456A8" CODEBASE="/queue/plugs/cab/JhFinger.cab#version=1,0,0,1"></OBJECT>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">代理人信息修改</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
   
    <form action="" method="post" name="form1">
     <input type="hidden" id="base64Code" name="base64Code" /><%--照片 --%>
     <input type="hidden" id="base64Code2" name="base64Code2" /><%--指纹 --%>
     
          <table width="90%" border="0" cellpadding="1" cellspacing="1" class="table_back">
            <tr>
            <td width="5%" height="25" bgcolor="#FFFFFF" class="tableheader1" rowspan="5">照片</td>
              	<td width=100 bgColor=#f1f9fd height=30 rowspan="5">&nbsp;
	          	 <img alt="照片" height="200px" width="150" src="agentgetphoto.action?id=${agent.id}">
              	</td>
              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">资格证编号</td>
              <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="id" id="id" type="text" class="inputface" value="${agent.id }" disabled="disabled"/> 
              </td>
            <td class=tableheader1 width=10% height=30>姓名:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="name" id="name" type="text" class="inputface" value="${agent.name }"/><span style="color: red;">*</span>
           </td>
           <td class=tableheader1 width=10% height=30>登记日期:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="register_date" id="register_date" type="text" class="inputface" value="${agent.register_date }" disabled="disabled"/>
           </td>   
          
        	</tr>
            <tr>
               <td class=tableheader1 width=10% height=30>身份证号码:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="idCard" id="idCard" type="text" class="inputface" value="${agent.idCard }" maxlength="18"/><span style="color: red;">*</span>
           </td>   
            
               <td class=tableheader1 width=10% height=30>服务单位:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="unit" id="unit" type="text" class="inputface" value="${agent.unit }"/><span style="color: red;">*</span>
           </td> 
            	<td class=tableheader1 width=10% height=30>手机号码:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="cellphone" id="cellphone" type="text" class="inputface" value="${agent.cellphone }" maxlength="11"/><span style="color: red;">*</span>
           </td>   
           
           </tr>
           <tr>
           <td class=tableheader1 width=10% height=30>有效期止:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="validity" id="validity" type="text" class="inputface" value="${agent.validity }" readonly="readonly"/>
           </td>  
           <!-- 
           <td class=tableheader1 width=10% height=30>单号最大办理数:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="max_lshs" id="max_lshs" type="text" class="inputface" value="${agent.max_lshs }" maxlength="4" /><span style="color: red;">*</span>
           </td>   -->
           <td class=tableheader1 width=10% height=30>单天最大办理数:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="max_times_byday" id="max_times_byday" type="text" class="inputface" value="${agent.max_times_byday }" maxlength="4" /><span style="color: red;">*</span>
           </td>
           <td class=tableheader1 width=10% height=30>备注:</td>
          		<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          	<input name="remark" id="remark" type="text" class="inputface" value="${agent.remark }"/><span style="color: red;">*</span>
           </td>          
           </tr>
           <tr>
           <td class=tableheader1 width=10% height=30 >状态:</td>
           <td width=150 bgColor=#f1f9fd height=30 >&nbsp;
           <select name="status" id="status"  >
                    <option value="0">注销</option>
				  	<option value="1">正常</option>
				  	<option value="2">暂停</option>
            </select>
            </td>
            <td class=tableheader1 width=10% height=30>单位电话:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="unit_phone" id="unit_phone" type="text" class="inputface" value="${agent.unit_phone }"/><span style="color: red;">*</span>
           </td> 
           <td class=tableheader1 width=10% height=30>法人代表:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="incorporator" id="incorporator" type="text" class="inputface" value="${agent.incorporator }"/><span style="color: red;">*</span>
           </td>
          </tr>
           <tr>
              	<td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">每天取号次数</td>
              	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
					<input name="dlrqhcs" id="dlrqhcs" type="text" class="inputface" value="${agent.dlrqhcs }" maxlength="100"/>次
              	</td>	
              	<td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">照片修改</td>
              	<td width=150 bgColor=#f1f9fd height=30 colspan="1">&nbsp;
              	<input type="button" value="打开摄像头" onclick="upobj()">
              	<input type="hidden" id="base64Code" name="base64Code" />
              	<input id=btnCapture type=button value="拍照" name=btnCapture onclick="return btnCapture_onclick()" /><span style="color: red;">*</span>
        		</td>
        		 <td class=tableheader1 width=10% height=30 >指纹：</td>
          		<td width=200 bgColor=#f1f9fd height=30  >&nbsp;
				 <INPUT id=btnCZ type=button value="采集" name=btnCZ onclick="return btnCZ_onclick()"><span style="color: red;">*</span>
          		</td>  
          </tr>
          <tr>
          		<td colspan="8" bgcolor="#FFFFFF" class="tableheader1" height="30">代理业务</td>
          </tr>
          </table>
	      <table cellpadding="0" cellspacing="0"  style="width:90%">
	      		<tr>
	      			<td width="5%"></td>
	      			<td width="150px"></td>
	      			<td width="10%"></td>
	      			<td width="150"></td>
	      			<td width="10%"></td>
	      			<td width="150"></td>
	      			<td width="10%"></td>
	      			<td width="150"></td>
	      		</tr>
	   			<c:forEach var="item" items="${result}">
	   				<tr height="75px">
	   					<td bgcolor="#FFFFFF" class="tableheader1" colspan="3" style="border:solid #4482d3;border-width:0px 1px 1px 1px;color: #10418c;font-weight:bold;text-align:center">${item.key}</td>
	   					<td bgColor=#f1f9fd colspan="5" style="border:solid #4482d3;border-width:0px 1px 1px 0px;color: #10418c;font-weight:bold;padding-left:10px">
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
          <input name="button" type="button" class="button" value="保存" onClick="upd()"/>
          <input name="button" type="button" class="button" value="返回" onClick="window.location='dlrdj.action'"/>
         
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
<span style="text-align: center;float:left">
	<object id=jsrzP classid="clsid:C3FF8650-C1AC-4E41-9474-6C27A58502D6" width="0" height="0" align="middle" id=OBJECT1" CODEBASE="/queue/plugs/camera/zdywzp.CAB#version=1.0.0.3"></OBJECT><br/>
	</span>  
	<span id="picdiv" style="text-align: center;float:none"></span>
</html>