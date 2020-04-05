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
<title>机动车驾驶人身体条件证明</title>
<style id="style1">
*{
margin:0;
padding:0;
}
body,td{
font-family:"黑体",sans-serif;
font-size:12px;
text-align:center;
font-weight:bold;
}
.title{	
font-size: 30px;
font-weight: bold;
font-family: "黑体";
text-align:center;
}
table{
border-collapse:collapse;
}
.tab1{
width:700px;
margin:0 auto;
}
.tab1 td{
border:1px solid #000;
height:46px;
}
.tab2{
width:100%;
}
.tab2 td{
border:1px solid #fff;
height:18px;
text-align:left;
font-weight:normal;
}
.td1{
width:4%;
}
.td2{
width:4%;
}
.td3{
width:7%;
}
.td4{
width:14%;
}
.td5{
width:6%;
}
.td6{
width:6%;
}
.td7{
width:3%;
}
.td8{
width:3%;
}
.td9{
width:5%;
}
.td10{
width:5%;
}
.td11{
width:9%;
}
.td12{
width:9%;
}
.td13{
width:6%;
}
.td14{
width:4%;
}
.td15{
width:15%;
}
#hiddenrow td{
height:0;	
}
.input_line{
font-size:18px;
border:0;
border-bottom:1px solid #000;
}
.ziti {	font-family:"黑体";
	font-size:12px;
}
.tab3{
width:100%;
}
.tab3,
.tab3 td{
border:1px solid #fff;
}
.tab3 .border-r-x {	
border-right:1px solid #000;
}
.tab4{
width:700px;
margin:0 auto;
margin-top:10px;
}
.tab4 td{
text-align:left;
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
				var strFormHtml=strBodyStyle+"<body>"+document.getElementById("jdcjsrsttjzm").innerHTML+"</body>";
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
<form id="jdcjsrsttjzm">
	<div id="form1">
	<h1 class="title">机动车驾驶人身体条件证明</h1>
<table class="tab1">
  <tr id="hiddenrow">
    <td class="td1"></td>
    <td class="td2"></td>
    <td class="td3"></td>
    <td class="td4"></td>
    <td class="td5"></td>
    <td class="td6"></td>
    <td class="td7"></td>
    <td class="td8"></td>
    <td class="td9"></td>
    <td class="td10"></td>
    <td class="td11"></td>
    <td class="td12"></td>
    <td class="td13"></td>
    <td class="td14"></td>
    <td class="td15"></td>
  </tr>
  <tr>
    <td rowspan="6">申<br />
    请<br />
人<br />
填<br />
报<br />
事<br />
项</td>
    <td rowspan="4">申<br />
请<br />
人<br />
信<br />
息</td>
    <td>姓 名</td>
    <td colspan="2"><%=URLDecoder.decode(request.getParameter("nameA"),"utf-8") %></td>
    <td>性别</td>
    <td colspan="2">
		<c:if test="${param.Sex eq '1'}">男</c:if>
		<c:if test="${param.Sex eq '2'}">女</c:if>
	</td>
    <td colspan="2">出生日期</td>
    <td colspan="2"><%=request.getParameter("number").substring(6,14) %></td>
    <td colspan="2">国 籍</td>
    <td>中国</td>
  </tr>
  <tr>
    <td>身份证明名称</td>
    <td colspan="3">身份证</td>
    <td colspan="2">号码</td>
    <td colspan="7">
    <table class="tab3">
      <tr>
        <td width="5%" align="center" class="border-r-x"><%=request.getParameter("number").substring(0,1) %></td>
        <td width="5%" align="center" class="border-r-x"><%=request.getParameter("number").substring(1,2) %></td>
        <td width="5%" align="center" class="border-r-x"><%=request.getParameter("number").substring(2,3) %></td>
        <td width="5%" align="center" class="border-r-x"><%=request.getParameter("number").substring(3,4) %></td>
        <td width="5%" align="center" class="border-r-x"><%=request.getParameter("number").substring(4,5) %></td>
        <td width="5%" align="center" class="border-r-x"><%=request.getParameter("number").substring(5,6) %></td>
        <td width="5%" align="center" class="border-r-x"><%=request.getParameter("number").substring(6,7) %></td>
        <td width="5%" align="center" class="border-r-x"><%=request.getParameter("number").substring(7,8) %></td>
        <td width="5%" align="center" class="border-r-x"><%=request.getParameter("number").substring(8,9) %></td>
        <td width="5%" align="center" class="border-r-x"><%=request.getParameter("number").substring(9,10) %></td>
        <td width="5%" align="center" class="border-r-x"><%=request.getParameter("number").substring(10,11) %></td>
        <td width="5%" align="center" class="border-r-x"><%=request.getParameter("number").substring(11,12) %></td>
        <td width="5%" align="center" class="border-r-x"><%=request.getParameter("number").substring(12,13) %></td>
        <td width="5%" align="center" class="border-r-x"><%=request.getParameter("number").substring(13,14) %></td>
        <td width="5%" align="center" class="border-r-x"><%=request.getParameter("number").substring(14,15) %></td>
        <td width="5%" align="center" class="border-r-x"><%=request.getParameter("number").substring(15,16) %></td>
        <td width="5%" align="center" class="border-r-x"><%=request.getParameter("number").substring(16,17) %></td>
        <td width="5%" align="center"><%=request.getParameter("number").substring(17,18) %></td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td colspan="2">申 请 / 已 具 有 的准驾车型代号</td>
    <td colspan="4">&nbsp;</td>
    <td colspan="2">档案编号</td>
    <td colspan="3">&nbsp;</td>
    <td colspan="2" rowspan="4">照片</td>
  </tr>
  <tr>
    <td>邮寄地址</td>
    <td colspan="5">&nbsp;</td>
    <td colspan="2">联系电话</td>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td rowspan="2">申<br />
告<br />
事<br />
项</td>
    <td colspan="11">本人如实申告   
      <input type="checkbox" name="checkbox6" id="checkbox6" />
    具有    
    <input type="checkbox" name="checkbox" id="checkbox" />
    不具有   下列疾病或者情况</td>
  </tr>
  <tr>
    <td colspan="11">
    <table class="tab2">
      <tr>
        <td width="25%"><input type="checkbox" name="checkbox2" id="checkbox2" />器质性心脏病 </td>
        <td width="25%"><input type="checkbox" name="checkbox3" id="checkbox3" />癫 痫 </td>
        <td width="25%"><input type="checkbox" name="checkbox4" id="checkbox4" />美尼尔氏症</td>
        <td width="25%"><input type="checkbox" name="checkbox5" id="checkbox5" />眩 晕 </td>
      </tr>
      <tr>
        <td><input type="checkbox" name="checkbox7" id="checkbox7" />癔 病 </td>
        <td><input type="checkbox" name="checkbox8" id="checkbox8" />震颤麻痹 </td>
        <td><input type="checkbox" name="checkbox9" id="checkbox9" />精神病</td>
        <td><input type="checkbox" name="checkbox10" id="checkbox10" />痴 呆</td>
      </tr>
      <tr>
        <td colspan="4"><input type="checkbox" name="checkbox11" id="checkbox11" />影响肢体活动的神经系统疾病等妨碍安全驾驶疾病 </td>
        </tr>
      <tr>
        <td colspan="4"><input type="checkbox" name="checkbox12" id="checkbox12"  />三年内有吸食、注射毒品行为或者解除强制隔离戒毒措施未满三年，或者长期服用依赖性精神药品成瘾尚未戒除</td>
        </tr>
      <tr>
        <td colspan="4">上述申告为本人真实情况和真实意思表示，如果不属实本人自愿承担相应的法律责任。</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="2" rowspan="8">医    <br />
    疗<br />
机<br />
构<br />
填<br />
写<br />
事<br />
项</td>
    <td colspan="2">身高(cm)</td>
    <td colspan="5">&nbsp;</td>
    <td colspan="2">辨色力</td>
    <td colspan="2"><p>红 绿 色 盲</p>
    <p>
      <input type="checkbox" name="checkbox15" id="checkbox15" />有   
    <input type="checkbox" name="checkbox16" id="checkbox16" />无</p></td>
    <td colspan="2" rowspan="3"><p>（医疗机构章）</p>
      <p>&nbsp;</p>
      <p><br />
    年  月  日</p></td>
  </tr>
  <tr>
    <td rowspan="2">视   力</td>
    <td>左眼</td>
    <td colspan="2" rowspan="2">单眼视力障碍
      <br />
      <input type="checkbox" name="checkbox26" id="checkbox27" />
      是
      <input type="checkbox" name="checkbox26" id="checkbox28" />
    否</td>
    <td colspan="3">优眼水平视野</td>
    <td colspan="2" rowspan="2">是否矫正</td>
    <td colspan="2"><input type="checkbox" name="checkbox17" id="checkbox17" />是
      <input type="checkbox" name="checkbox18" id="checkbox18" />否</td>
  </tr>
  <tr>
    <td>右眼</td>
    <td colspan="3">&nbsp;</td>
    <td colspan="2"><input type="checkbox" name="checkbox19" id="checkbox19" />是
      <input type="checkbox" name="checkbox19" id="checkbox20" />否</td>
  </tr>
  <tr>
    <td rowspan="2">听  <br />
    力</td>
    <td rowspan="2">佩戴助听装置<br />
      <input type="checkbox" name="checkbox13" id="checkbox13" />是
    <input type="checkbox" name="checkbox14" id="checkbox14" />否</td>
    <td colspan="5">左耳</td>
    <td colspan="2" rowspan="2">躯干和颈部</td>
    <td colspan="4" rowspan="2"><p>运 动 功 能 障 碍</p>
    <p>
      <input type="checkbox" name="checkbox20" id="checkbox21" />有     
    <input type="checkbox" name="checkbox21" id="checkbox22" />无</p></td>
  </tr>
  <tr>
    <td colspan="5">右耳</td>
  </tr>
  <tr>
    <td rowspan="3">上<br />
肢</td>
    <td>左上肢</td>
    <td colspan="5">&nbsp;</td>
    <td colspan="2" rowspan="3">下   肢</td>
    <td>左下肢</td>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td rowspan="2">右上肢</td>
    <td colspan="5" rowspan="2">&nbsp;</td>
    <td>右下肢</td>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="4" style="padding:3px;">双下肢缺失或者丧失运动功能障碍是否能够自主坐立     
      <input type="checkbox" name="checkbox22" id="checkbox23" />是    
    <input type="checkbox" name="checkbox23" id="checkbox24" />否</td>
  </tr>
  <tr>
    <td colspan="3">申请方式</td>
    <td colspan="12"><input type="checkbox" name="checkbox24" id="checkbox25" <c:if test="${param.DBbdbj eq '0'}">checked</c:if> />本人申请                  
      <input type="checkbox" name="checkbox25" id="checkbox26" style="margin-left:25px;" <c:if test="${param.DBbdbj eq '1'}">checked</c:if>/>委托
      <input type="text"  class="input_line" value="<%=URLDecoder.decode(request.getParameter("DBnameA"),"utf-8") %>"/>
代理申请 </td>
  </tr>
  <tr>
    <td colspan="3" rowspan="2">委托代理人信息</td>
    <td>姓名</td>
    <td colspan="3"><%=URLDecoder.decode(request.getParameter("DBnameA"),"utf-8") %></td>
    <td colspan="2">身份证明名称</td>
    <td colspan="2">身份证</td>
    <td>号码</td>
    <td colspan="3"><%=request.getParameter("DBnumber")%></td>
  </tr>
  <tr>
    <td>联系地址</td>
    <td colspan="7"><%=URLDecoder.decode(request.getParameter("DBAddress"),"utf-8") %></td>
    <td>电话</td>
    <td colspan="3">&nbsp;</td>
  </tr>  
</table>
<table class="tab4">
  <tr>
    <td>备注：《机动车驾驶人身体条件证明》自体检之日起6个月内有效。</td>
  </tr>
</table>
<table class="tab4">
  <tr>
    <td width="192">申请人签字：</td>
    <td width="22">&nbsp;</td>
    <td width="160">医生签字：</td>
    <td width="22">&nbsp;</td>
    <td width="192">代理人签字：</td>
    <td width="22">&nbsp;</td>
  </tr>
</table>
</div>
</form>
<input type="button" name="打印" value="打印" onclick="anjian()" />
</body>
</html>