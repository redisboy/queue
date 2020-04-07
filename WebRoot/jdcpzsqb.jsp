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
<title>机动车牌证申请表</title>
<style id="style1">
body{
font-family:"宋体",sans-serif;
font-size:12px;
}
*{
margin:0;
padding:0;
}
.title{
text-align:center;
margin-bottom:25px;
}
h1{
font-size:30px;
}
td{
text-align:center;
height:40px;
}
.table1{
width:700px;
margin:0 auto;
border:1px solid #000;
border-collapse:collapse;
}
.table1 td{
border:1px solid #000;
}
.table2{
width:90%;
margin:0 auto;
}
.table2 td{
text-align:left;
border:0;
}
.p1{
text-align:left;
font-weight:bold;
text-indent:2em;
font-size:15px;
line-height:25px;
}
.table3{
width:100%;
}
.table3 td{
border:0;
}
.table4{
width:100%;
}
.table4 td{
text-align:left;
border:0;
}
.td1 {
font-weight:bold;
font-size:20px;
}
.tab1{
width:100%;
border-collapse:collapse;
text-align:center;
}
.tab1 td{
border:1px solid #000;
}
</style>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="/queue/js/jquery.js"></script>
<script type="text/javascript" src="/queue/js/ajax.js"></script>
<script type="text/javascript" src="jquery.PrintArea.js"></script>
<script language="javascript" src="js/LodopFuncs.js"></script>
<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0></object>
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
				var strFormHtml=strBodyStyle+"<body>"+document.getElementById("jdcpzsqb").innerHTML+"</body>";
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
			LODOP.PRINT_INIT("");
//			LODOP.SET_PRINT_PAGESIZE(1,0,0,'A4');
//			LODOP.ADD_PRINT_TEXT(50,50,260,39,"打印与显示样式一致：");
			LODOP.ADD_PRINT_HTM('0%','0%','100%','100%',strFormHtml);
//			LODOP.PREVIEW();
			LODOP.PRINT_DESIGN();
//			LODOP.PRINT();
		}
</script>
</head>
<body onload="printID()">
<form id="jdcpzsqb">
	<div id="form1">
<div class="title">
	<h1>机动车牌证申请表</h1>
</div>
<table class="table1">
  <tr>
    <td colspan="5" class="td1">申请人信息栏</td>
  </tr>
  <tr>
    <td colspan="5">
        <table class="tab1">
          <tr>
            <td width="5%" rowspan="3" style="border-width:0 1px 1px 0;">机<br />
              动<br />
              车<br />
              所<br />
              有<br />
              人</td>
            <td width="20%" style="border-width:0">姓名/名称</td>
            <td colspan="2" style="border-top-width:0"><%=URLDecoder.decode(request.getParameter("nameA"),"utf-8") %></td>
            <td width="20%" style="border-top-width:0">邮政编码</td>
            <td width="20%" style="border-width:0"></td>
          </tr>
          <tr>
            <td>邮寄地址</td>
            <td colspan="4"></td>
          </tr>
          <tr>
            <td>手机号码</td>
            <td colspan="2"></td>
            <td>固定电话</td>
            <td></td>
          </tr>
          <tr>
            <td style="border-width:0 1px 1px 0;">代<br />
              理<br />
              人</td>
            <td>姓名/名称</td>
            <td width="20%"><%=URLDecoder.decode(request.getParameter("DBnameA"),"utf-8") %></td>
            <td width="15%">手机号码</td>
            <td colspan="2"></td>
          </tr>
          <tr>
            <td colspan="2" style="border-width:0 1px 0 0;">机动车号牌号码</td>
            <td colspan="5" style="border-bottom-width:0;">&nbsp;</td>
          </tr>
        </table>
    </td>
  </tr>
  <tr>
    <td colspan="5" class="td1">申请业务类型</td>
  </tr>
  <tr>
    <td colspan="2">号牌种类</td>
    <td width="30%">小型汽车</td>
    <td width="20%">号牌号码</td>
    <td width="30%">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2">申请事项</td>
    <td colspan="3">申请原因及明细</td>
  </tr>
  <tr>
    <td width="5%" rowspan="2">号<br />
    牌</td>
    <td width="15%"><input type="checkbox" name="checkbox" id="checkbox" />
      补领</td>
    <td colspan="3">
        <table class="table2">
          <tr>
            <td width="25%"><input type="checkbox" name="checkbox6" id="checkbox6" />
              丢失</td>
            <td width="25%"><input type="checkbox" name="checkbox7" id="checkbox7" />
              灭失</td>
            <td width="25%"><input type="checkbox" name="checkbox8" id="checkbox8" />
            前号牌</td>
            <td width="25%"><input type="checkbox" name="checkbox9" id="checkbox9" />
            后号牌</td>
          </tr>
        </table>
    </td>
  </tr>
  <tr>
    <td><input type="checkbox" name="checkbox2" id="checkbox2" />
      换领</td>
    <td colspan="3"><table class="table2">
      <tr>
        <td width="50%"><input type="checkbox" name="checkbox10" id="checkbox10" />
          前号牌</td>
        <td width="50%"><input type="checkbox" name="checkbox10" id="checkbox12" />
          后号牌</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td rowspan="2">行<br />
    驶<br />
    证</td>
    <td><input type="checkbox" name="checkbox3" id="checkbox3" />
补领</td>
    <td colspan="3"><table class="table2">
      <tr>
        <td width="25%"><input type="checkbox" name="checkbox11" id="checkbox11" />
          丢失</td>
        <td width="25%">&nbsp;</td>
        <td width="25%"><input type="checkbox" name="checkbox11" id="checkbox14" />
        灭失</td>
        <td width="25%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><input type="checkbox" name="checkbox4" id="checkbox4" />
换领</td>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td rowspan="3">登<br />
    记<br />
    证<br />
    书</td>
    <td><input type="checkbox" name="checkbox5" id="checkbox5" />
      申领</td>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td><input type="checkbox" name="checkbox3" id="checkbox3" />
      补领</td>
    <td colspan="3"><table class="table2">
      <tr>
        <td width="25%"><input type="checkbox" name="checkbox12" id="checkbox13" />
          丢失</td>
        <td width="25%"><input type="checkbox" name="checkbox12" id="checkbox15" />
          灭失</td>
        <td width="25%"><input type="checkbox" name="checkbox12" id="checkbox16" />
        未获得</td>
        <td width="25%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><input type="checkbox" name="checkbox4" id="checkbox4" />
      换领</td>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td rowspan="3"><p>检<br />
    验<br />
    合<br />
    格<br />
    标<br />
    志</p></td>
    <td><input type="checkbox" name="checkbox5" id="checkbox5" />
      申领</td>
    <td colspan="3"><table class="table2">
      <tr>
        <td width="50%"><input type="checkbox" name="checkbox13" id="checkbox17" />
          在登记地车辆管理所申请</td>
        <td width="50%"><input type="checkbox" name="checkbox13" id="checkbox19" />
          在登记地以外车辆管理所申请</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><input type="checkbox" name="checkbox3" id="checkbox3" />
      补领</td>
    <td colspan="3"><table class="table2">
      <tr>
        <td width="25%"><input type="checkbox" name="checkbox14" id="checkbox18" />
          丢失</td>
        <td width="25%">&nbsp;</td>
        <td width="25%"><input type="checkbox" name="checkbox14" id="checkbox20" />
          灭失</td>
        <td width="25%">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><input type="checkbox" name="checkbox4" id="checkbox4" />
      换领</td>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="5"><table class="table4">
      <tr>
        <td width="40%" style="padding-left:5px;border-right:1px solid #000;"><p class="p1">机动车所有人及代理人对申请材料的真实有效性负责。</p></td>
        <td><table class="table3">
          <tr>
            <td  style="text-align:left;padding:15px" valign="top">机动车所有人(代理人)签字：</td>
          </tr>
          <tr>
            <td valign="bottom" style="text-align:right;padding-right:15px">年        月        日</td>
          </tr>
          </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</div>
</form>
<input type="button" name="打印" value="打印" onclick="anjian()" />
</body>
</html>