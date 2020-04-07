﻿<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"  %>
<html>
  <head>
    <title>代理人管理</title>
  </head>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/queue/ecside/css/ecside_style.css" />
<script type="text/javascript" src="/queue/plugs/time/time.js" ></script> 
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="/queue/js/ajax.js"></script>	
<object id="xhWebFingerCtrl" width="0" height="0" classid="clsid:BB16B5DF-898F-4E1D-AABA-B23DB9E456A8" CODEBASE="/queue/plugs/cab/JhFinger.cab#version=1,0,0,1"></OBJECT>
<body>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">代理人信息登记</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    <form action="" method="post" name="form1" > 
		<input type="hidden" id="base64Code" name="base64Code" /><%--照片 --%>
     	<input type="hidden" id="base64Code2" name="base64Code2" /><%--指纹 --%>
     	<input type="hidden" id="ply" name="" />
     <input name="flag" id="flag" type="hidden" value="1"/>
          <table width="80%" border="0" cellpadding="1" cellspacing="1" class="table_back">
            <tr>
              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">资格证编号</td>
              <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="id" id="id" type="text" class="inputface" value="" maxlength="30"/><span style="color: red;">*</span> 
              </td>
            <td class=tableheader1 width=10% height=30>姓名:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="name" id="name" type="text" class="inputface" value="${agent.name }" maxlength="8" /><span style="color: red;">*</span>
           </td>
           <td class=tableheader1 width=10% height=30 >状态:</td>
           <td width=150 bgColor=#f1f9fd height=30  colspan="1">&nbsp;
           <select name="status" id="status" >
           			<option value="">请选择</option>
              		<option value="0">注销</option>
				  	<option value="1">正常</option>
				  	<option value="2">暂停</option>
            </select><span style="color: red;">*</span>
            </td>
        	</tr>
            <tr>
               <td class=tableheader1 width=10% height=30>身份证号码:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="idCard" id="idCard" type="text" class="inputface" value="${agent.idCard }" maxlength="18"/><span style="color: red;">*</span>
           </td>   
            
               <td class=tableheader1 width=10% height=30>服务单位:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="unit" id="unit" type="text" class="inputface" value="${agent.unit }" maxlength="45"/>
           </td> 
            	<td class=tableheader1 width=10% height=30>手机号码:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="cellphone" id="cellphone" type="text" class="inputface" value="${agent.cellphone }" maxlength="11"/>
           </td>   
           </tr>
           <tr>
            <td class=tableheader1 width=10% height=30>单位电话:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="unit_phone" id="unit_phone" type="text" class="inputface" value="${agent.unit_phone }" maxlength="20"/>
           </td> 
           <td class=tableheader1 width=10% height=30>法人代表:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="incorporator" id="incorporator" type="text" class="inputface" value="${agent.incorporator }" maxlength="20"/>
           </td>
           <td class=tableheader1 width=10% height=30>有效期止:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="validity" id="validity" type="text" class="inputface" readonly value="${agent.validity }" onclick="setday(this)" /><span style="color: red;">*</span>
           </td> 
           </tr> 
           <!-- 
           <tr>
           <td class=tableheader1 width=10% height=30 width=150>单号最大办理数:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="max_lshs" id="max_lshs" type="text" class="inputface" value="${agent.max_lshs }" maxlength="4"/><span style="color: red;">*</span>
           </td>  
           <td class=tableheader1 width=10% height=30>单天最大办理数:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="max_times_byday" id="max_times_byday" type="text" class="inputface" value="${agent.max_times_byday }" maxlength="4"/><span style="color: red;">*</span>
           </td>  
           <td class=tableheader1 width=10% height=30>备注:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="remark" id="remark" type="text" class="inputface" value="${agent.remark }" maxlength="100"/>
	        </td>  
            </tr>
             -->
           <tr>
           	<td class=tableheader1 width=10% height=30>备注:</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="remark" id="remark" type="text" class="inputface" value="${agent.remark }" maxlength="100"/>
	        </td>  
           	<td class=tableheader1 width=10% height=30>每天取号次数</td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;
          		<input name="dlrqhcs" id="dlrqhcs" type="text" class="inputface" value="${agent.dlrqhcs }" maxlength="100"/>次
          	</td>  
           	<td class=tableheader1 width=10% height=30></td>
          	<td width=150 bgColor=#f1f9fd height=30>&nbsp;</td>  
           </tr>  
           <tr>    
           <td class=tableheader1 width=10% height=30 >指纹：</td>
          	<td width=200 bgColor=#f1f9fd height=30  >&nbsp;
				 <INPUT id=btnCZ type=button value="采集" name=btnCZ onclick="return btnCZ_onclick();"><span style="color: red;">*</span>
           </td>  
           <td class=tableheader1 width=10% height=30 >照片：</td>
          	<td width=200 bgColor=#f1f9fd height=30 colspan="4">&nbsp;
          		<input type="button" value="打开摄像头" onclick="upobj()">
	    		<input id=btnCapture type=button value="拍照" name=btnCapture onclick="return btnCapture_onclick()" /><span style="color: red;">*</span>
           </td>  
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
          <div style="color: red; float: inherit" >带*的为必填项</div>
          <input name="button" type="button" class="button" value="添加" onClick="add()"/>
          <input name="button" type="button" class="button" value="返回" onClick="window.location='dlrdj.action'"/>
          <br/>
         </form>
    <td width="8" background="/queue/images/t-5.jpg">&nbsp;</td>
    </tr>
  <tr>
    <td ><img src="/queue/images/t-6.jpg" width="7" height="11"></td>
    <td height="11" background="/queue/images/t-7.jpg"></td>
    <td ><img src="/queue/images/t-8.jpg" width="8" height="11"></td>
  </tr>
</table>
</body>
<span style="text-align: center;float:left">
<object id=jsrzP width="0" height="0" classid="clsid:C3FF8650-C1AC-4E41-9474-6C27A58502D6"  align="middle" id=OBJECT1" CODEBASE="/queue/plugs/camera/zdywzp.CAB#version=1.0.0.3"></OBJECT><br/>
</span>
<span id="picdiv" style="text-align: center;float:none"></span>
<script type="text/javascript">
function checkType(){
var codeval = document.getElementsByName("code");
var strokeval = document.getElementsByName("stroke");
	for (i = 0; i < codeval.length; i++) { 
		if (codeval[i].checked) { 
	 		strokeval[i].value="5";
		}else{
			strokeval[i].value="";
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
	
    jsrzP.sZpImgFilePath = "c:\\99999.jpg";
    jsrzP.OnCaptureZp();
    showpic();
}
function IsDigit(cCheck) { return (('0'<=cCheck) && (cCheck<='9')); }
function IsAlpha(cCheck) { return ((('a'<=cCheck) && (cCheck<='z')) || (('A'<=cCheck) && (cCheck<='Z'))) }  
function add() { 
	var id=document.getElementById("id").value;
	var idCard=document.getElementById("idCard").value;
	//var max_lshs=document.getElementById("max_lshs").value;
//	var max_times_byday=document.getElementById("max_times_byday").value;
	var cellphone=document.getElementById("cellphone").value;
	var unit_phone=document.getElementsByName("unit_phone");
	var code=document.getElementsByName("code");
	var name=document.getElementById("name").value;
	var validity=document.getElementById("validity").value;
	var status=document.getElementById("status").value;
	var base64Code=document.getElementById("base64Code").value;
	var base64Code2=document.getElementById("base64Code2").value;
	if(id==""){
		alert("请填写【资格证编号】");
		document.getElementById("id").focus();
		return false;
	}
	var reg = new RegExp("[0-9]+");
	if(!reg.test(id)){
		alert("编号填写有误,请写数字！");
		document.getElementById("id").focus();
		return false;
	}
	var url="dlrselect.action";
	var param='id='+id;
	var flag = new MyJqueryAjax(url,param).request();
	if(flag==1){
		alert("编号已经存在");
	//	document.getElementById("id").focus();
		return false;
	}
	if(idCard==""){
		alert("请填写【身份证号码】");
		document.getElementById("idCard").focus();
		return false;
	}
//	 for (nIndex=0; nIndex<idCard.length; nIndex++)
//	    {
//	        cCheck = idCard.charAt(nIndex);
////
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
	var url2="dlrselect2.action";
	var param2='idCard='+idCard;
	var flag2 = new MyJqueryAjax(url2,param2).request();
	
	if(flag2==1){
		alert("身份证号已经存在");
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
	if(status==""){
		alert("请选择【状态】");
		return false;
	}
	/*if(max_lshs==""){
		alert("请填写【单号最大办理数】");
		document.getElementById("max_lshs").focus();
		return false;
	} for (nIndex=0; nIndex<max_lshs.length; nIndex++)
    {
        cCheck = max_lshs.charAt(nIndex);

        if (!IsDigit(cCheck))
        {
            alert("单号最大办理数只包含数字");
            document.getElementById("max_lshs").focus();
            return false;
        }
    }
	if(max_times_byday==""){
		alert("请填写【单天最大办理数】");
		document.getElementById("max_times_byday").focus();
		return false;
	} for (nIndex=0; nIndex<max_times_byday.length; nIndex++)
    {
        cCheck = max_times_byday.charAt(nIndex);

        if (!IsDigit(cCheck))
        {
            alert("单天最大办理数只包含数字");
            document.getElementById("max_times_byday").focus();
            return false;
        }
    }*/
    if(cellphone!=""){
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
    if(validity==""){
    	alert("请选择【有效期止】");
        document.getElementById("validity").focus();
        return false;
    }
/*	var checked=false;
	for(var i=0;i<code.length;i++){	
			if(code[i].checked){
				checked=true;
			}		
		}
	if(!checked){
		alert("请选择代理业务");
		return false;
	}*/
	/*
	if(base64Code==""){
		alert("请拍照");
		return false;
	}
	if(base64Code2==""){
		alert("请录指纹");
		return false;
	}
	*/
		document.form1.action = "adddlr.action"; 
		document.form1.submit();
}
//照片
function showpic(){
	var imgstr=document.getElementById("base64Code").value;
	document.getElementById('picdiv').innerHTML = "<img id='pic' width=300 height=320 src='c:\\99999.jpg'>";
}
//摄像头
function upobj(){
	//document.getElementById('jsrzP').style.display = "block"
	document.getElementById('jsrzP').width="300";
	document.getElementById('jsrzP').height="320";
	btnLoadDev_onclick();
}
</script>
<SCRIPT LANGUAGE=javascript FOR=jsrzP EVENT="FireCaptureZpEvent(pszInfo1)">
		document.getElementById("base64Code").value=pszInfo1;
</SCRIPT>
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
			alert(stat);
		if (stat==0)
		{
			alert("成功");
		}
		else
		{
			alert("失败");
		}
	}
//-->
</script>
</html>