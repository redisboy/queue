<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>机动车驾驶人身体情况申报表</title>
<style id="style1">
body{
font-family:"宋体",sans-serif;
font-size:12px;
}
.title{
width:660px;
margin:120px auto 0;
text-align:center;
margin-bottom:25px;
}
h1{
font-size:27px;
}
td{
text-align:center;
height:52px;
}
.table1{
width:660px;
margin:0 auto;
border:1px solid #000;
border-collapse:collapse;
}
.table1 td{
border:1px solid #000;
font-weight:bold;
}
.table2{
width:100%;
}
.table2 td{
text-align:left;
border:0;
}
.p1{
text-align:left;
font-weight:bold;
font-size:20px;
line-height:25px;
}
.p2{
text-align:right;
font-size:17px;
line-height:25px;
}
.table3{
width:100%;
}
.table3 td{
border:0;
}
.border_l{
border-left:1px solid #000;
}
.border_r{
border-right:1px solid #000;
}
.tab_sfz{
width:100%;
border:0;	
}
.table1 .tab_sfz td{
border:0;	
}
.table1 .tab_sfz td.border_r{
border-right:1px solid #000;
}
</style>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="/queue/js/jquery.js"></script>
<script type="text/javascript" src="/queue/js/ajax.js"></script>
<script type="text/javascript" src="jquery.PrintArea.js"></script>
<script language="javascript" src="js/LodopFuncs.js"></script>
<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0><embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed></object>
<object id="WebBrowser" classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height="0" width="0"></object>
<script type="text/javascript" defer="defer">
	function printID(){
				//勾选业务类型
		var ubdywmcVo = '<%=request.getParameter("ubdywmc")%>';
		var bdywmc = ubdywmcVo.split("H");
		for(var i=0;i<bdywmc.length;i++){
			$("[id = "+bdywmc[i]+"]:checkbox").attr("checked", true);
		}
		var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
				var strBodyStyle="<style>"+document.getElementById("style1").innerHTML+"</style>";
				var strFormHtml=strBodyStyle+"<body>"+document.getElementById("jdcjsrstqksbb").innerHTML+"</body>";
				LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_表单一");
				LODOP.ADD_PRINT_HTM('0%','0%','100%','100%',strFormHtml);
				if (LODOP.SET_PRINTER_INDEX("-1")){
					LODOP.PRINT();
				}
				var udybdVo = '<%=request.getParameter("dybdVo")%>';
				var datas = udybdVo.split("H");
				var dybdVo = "";
				for(var i=1;i<(datas.length-1);i++){
					dybdVo += datas[i] + "H";
				}
				if((datas.length-1) != 0){
					var features = "status=no,resizable=no,top=0,left=0,scrollbars=no"
							+ "titlebar=no,menubar=no,location=no,toolbar=no,z-look=yes";
							window.open(datas[0]+".jsp?nameA="+encodeURIComponent(encodeURIComponent('<%=URLDecoder.decode(request.getParameter("nameA"),"utf-8") %>','utf-8'))
								+"&DBnameA="+encodeURIComponent(encodeURIComponent('<%=URLDecoder.decode(request.getParameter("DBnameA"),"utf-8") %>','utf-8'))
								+"&number="+'<%=request.getParameter("number")%>'
								+"&DBnumber="+'<%=request.getParameter("DBnumber")%>'
								+"&Sex="+'<%=request.getParameter("Sex")%>'
								+"&DBSex="+'<%=request.getParameter("DBSex")%>'
								+"&DBbdbj="+'<%=request.getParameter("DBbdbj")%>'
								+"&ubdywmc="+'<%=request.getParameter("ubdywmc")%>'
								+"&dybdVo="+dybdVo
								+"&DBAddress="+encodeURIComponent(encodeURIComponent('<%=URLDecoder.decode(request.getParameter("DBAddress"),"utf-8") %>','utf-8'))
								+"&Address="+encodeURIComponent(encodeURIComponent('<%=URLDecoder.decode(request.getParameter("Address"),"utf-8") %>','utf-8')), "_blank", features);
				}
		window.close();
	}
		
		
		function anjian(){
			var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
			var strBodyStyle="<style>"+document.getElementById("style1").innerHTML+"</style>";
			var strFormHtml=strBodyStyle+"<body>"+document.getElementById("form1").innerHTML+"</body>";
			LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_样式风格");	
//			LODOP.ADD_PRINT_TEXT(50,50,260,39,"打印与显示样式一致：");
			LODOP.ADD_PRINT_HTM(20,20,270,970,strFormHtml);
			LODOP.PREVIEW();
		}
</script>
</head>
<body onload="printID()">
<form id="jdcjsrstqksbb">
	<div id="form1">
<div class="title">
		<h1>机动车驾驶人身体情况申报表</h1>
</div>
	<table class="table1">
	  <tr>
	    <td width="5%" rowspan="6">申<br>
      请<br>
      人<br>
      信<br>
      息</td>
	    <td width="9%">姓名</td>
	    <td width="10%"><%=URLDecoder.decode(request.getParameter("nameA"),"utf-8") %></td>
	    <td width="8%">性别</td>
	    <td width="6%">
			<c:if test="${param.Sex eq '1'}">男</c:if>
			<c:if test="${param.Sex eq '2'}">女</c:if>
		</td>
	    <td width="9%">出生日期</td>
	    <td width="14%"><%=request.getParameter("number").substring(6,14) %></td>
	    <td width="9%">国籍</td>
	    <td width="10%">中国</td>
	    <td width="20%" rowspan="3">照片</td>
      </tr>
	  <tr>
	    <td>身份证<br>
	      件名称</td>
	    <td>身份证</td>
	    <td>号码</td>
	    <td colspan="5">
            <table  class="tab_sfz" cellspacing="0" cellpadding="0">
              <tr>
                <td width="5%" align="center" class="border_r"><%=request.getParameter("number").substring(0,1) %></td>
                <td width="5%" align="center" class="border_r"><%=request.getParameter("number").substring(1,2) %></td>
                <td width="5%" align="center" class="border_r"><%=request.getParameter("number").substring(2,3) %></td>
                <td width="5%" align="center" class="border_r"><%=request.getParameter("number").substring(3,4) %></td>
                <td width="5%" align="center" class="border_r"><%=request.getParameter("number").substring(4,5) %></td>
                <td width="5%" align="center" class="border_r"><%=request.getParameter("number").substring(5,6) %></td>
                <td width="5%" align="center" class="border_r"><%=request.getParameter("number").substring(6,7) %></td>
                <td width="5%" align="center" class="border_r"><%=request.getParameter("number").substring(7,8) %></td>
                <td width="5%" align="center" class="border_r"><%=request.getParameter("number").substring(8,9) %></td>
                <td width="5%" align="center" class="border_r"><%=request.getParameter("number").substring(9,10) %></td>
                <td width="5%" align="center" class="border_r"><%=request.getParameter("number").substring(10,11) %></td>
                <td width="5%" align="center" class="border_r"><%=request.getParameter("number").substring(11,12) %></td>
                <td width="5%" align="center" class="border_r"><%=request.getParameter("number").substring(12,13) %></td>
                <td width="5%" align="center" class="border_r"><%=request.getParameter("number").substring(13,14) %></td>
                <td width="5%" align="center" class="border_r"><%=request.getParameter("number").substring(14,15) %></td>
                <td width="5%" align="center" class="border_r"><%=request.getParameter("number").substring(15,16) %></td>
                <td width="5%" align="center" class="border_r"><%=request.getParameter("number").substring(16,17) %></td>
                <td width="5%" align="center"><%=request.getParameter("number").substring(17,18) %></td>
              </tr>
            </table>
        </td>
      </tr>
	  <tr>
	    <td>身份证<br />
件名称</td>
	    <td>身份证</td>
	    <td>号码</td>
	    <td colspan="5"><table  class="tab_sfz" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="5%" align="center" class="border_r">&nbsp;</td>
	        <td width="5%" align="center" class="border_r">&nbsp;</td>
	        <td width="5%" align="center" class="border_r">&nbsp;</td>
	        <td width="5%" align="center" class="border_r">&nbsp;</td>
	        <td width="5%" align="center" class="border_r">&nbsp;</td>
	        <td width="5%" align="center" class="border_r">&nbsp;</td>
	        <td width="5%" align="center" class="border_r">&nbsp;</td>
	        <td width="5%" align="center" class="border_r">&nbsp;</td>
	        <td width="5%" align="center" class="border_r">&nbsp;</td>
	        <td width="5%" align="center" class="border_r">&nbsp;</td>
	        <td width="5%" align="center" class="border_r">&nbsp;</td>
	        <td width="5%" align="center" class="border_r">&nbsp;</td>
	        <td width="5%" align="center" class="border_r">&nbsp;</td>
	        <td width="5%" align="center" class="border_r">&nbsp;</td>
	        <td width="5%" align="center" class="border_r">&nbsp;</td>
	        <td width="5%" align="center" class="border_r">&nbsp;</td>
	        <td width="5%" align="center" class="border_r">&nbsp;</td>
	        <td width="5%" align="center">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
	  <tr>
	    <td>身份证件<br/>记载住址</td>
	    <td colspan="5"><%=URLDecoder.decode(request.getParameter("Address"),"utf-8") %></td>
	    <td>电子信箱</td>
	    <td colspan="2">&nbsp;</td>
      </tr>
	  <tr>
	    <td>邮寄地址</td>
	    <td colspan="5">&nbsp;</td>
	    <td>移动电话</td>
	    <td colspan="2">&nbsp;</td>
      </tr>
	  <tr>
	    <td>档案编号</td>
	    <td colspan="3">&nbsp;</td>
	    <td>准驾车<br/>
        型代号</td>
	    <td>&nbsp;</td>
	    <td>联系电话</td>
	    <td colspan="2">&nbsp;</td>
      </tr>
	  <tr>
	    <td rowspan="2">申<br />告<br />内<br />容</td>
	    <td colspan="9"><p class="p1" style="text-align:center;">申报内容</p></td>
      </tr>
	  <tr>
	    <td colspan="9"><p class="p1">一、本人身体条件符合中华人民共和国机动车驾驶证申请条件；</p>
          <p class="p1">二、本人不具有器质性心脏病、癫痫病、美尼尔氏症、眩晕症、癔病、震颤麻痹、精神病、痴呆、影响肢体活动的神经系统疾病等妨碍安全驾驶疾病,及三年内有吸食、注射毒品行为或者解除强制隔离戒毒措施未满三年，或者长期服用依赖性精神药品成瘾尚未戒除的情况；</p>
          <p class="p1">三、本人的身体条件如果发生不符合机动车驾驶许可条件的情形，本人将在30日内向公安机关申请降低准驾车型或注销机动车驾驶证；</p>
        <p class="p1">四、上述申告为本人真实情况和真实意思表示，如果不属实本人自愿承担相应的法律责任。</p>
        <p class="p2">申告人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日</p>
        </td>
      </tr>
</table>
</div>
</form>
<input type="button" name="打印" value="打印" onclick="anjian()" />
</body>
</html>