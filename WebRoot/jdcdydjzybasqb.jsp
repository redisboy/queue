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
<title>机动车抵押登记/质押备案申请表</title>
<style id="style1">
*{
margin:0;
padding:0;	
}
body{
font-family:"宋体",sans-serif;
font-size:12px;
}
.title{
text-align:center;
margin-bottom:25px;
}
h1{
font-size:22px;
}
td{
text-align:center;
height:60px;
}
.table1{
width:640px;
margin:0 auto;
border:1px solid #000;
border-collapse:collapse;
}
.table1 td{
border:1px solid #000;
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
				var strFormHtml=strBodyStyle+"<body>"+document.getElementById("jdcdydjzybasqb").innerHTML+"</body>";
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
<form id="jdcdydjzybasqb">
	<div id="form1">
	<div class="title">
		<h1>机动车抵押登记/质押备案申请表</h1>
</div>
<table class="table1">
  <tr>
    <td colspan="2">号牌种类</td>
    <td colspan="2">&nbsp;</td>
    <td width="20%">号牌号码</td>
    <td width="25%">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2">申请事项</td>
    <td colspan="3"><table class="table2">
      <tr>
        <td><input type="checkbox" name="checkbox15" id="checkbox21" />
        抵押登记</td>
        <td><input type="checkbox" name="checkbox15" id="checkbox22" />
          解除抵押登记</td>
        <td><input type="checkbox" name="checkbox15" id="checkbox23" />
        质押</td>
        <td><input type="checkbox" name="checkbox15" id="checkbox24" />
        解除质押</td>
      </tr>
    </table></td>
    <td rowspan="2" style="padding-left:5px;">
    <p class="p1">机动车所有人及代理人对申请材料的真实有效性负责。</p>
    </td>
  </tr>
  <tr>
    <td colspan="2">机动车所有人<br />
    姓名/名称</td>
    <td colspan="3"><%=URLDecoder.decode(request.getParameter("nameA"),"utf-8") %></td>
  </tr>
  <tr>
    <td width="5%" rowspan="3">机<br />
    动<br />
    车<br />
    所<br />
    有<br />
    人<br />
    的<br />
    代<br />
    理<br />
    人</td>
    <td width="15%">姓名/名称</td>
    <td colspan="3"><%=URLDecoder.decode(request.getParameter("DBnameA"),"utf-8") %></td>
    <td rowspan="3"><table class="table3">
      <tr>
        <td  style="text-align:left;" valign="top">机动车所有人(代理人)签字：</td>
      </tr>
      <tr>
        <td valign="bottom" style="text-align:right;">&nbsp;</td>
      </tr>
      <tr>
        <td valign="bottom" style="text-align:right;">年       月        日</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>邮寄地址</td>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td>邮政编码</td>
    <td width="20%">&nbsp;</td>
    <td width="15%">联系电话</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td rowspan="3">抵<br />
押<br />
权<br />
人<br />
/<br />
典<br />
当<br />
行</td>
    <td>姓名/名称</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td rowspan="2" style="padding-left:5px;">
    <p class="p1">抵押权人/典当行及代理人对申请资料的真实有效性负责。</p>
    </td>
  </tr>
  <tr>
    <td>邮寄地址</td>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td>邮政编码</td>
    <td>&nbsp;</td>
    <td>联系电话</td>
    <td>&nbsp;</td>
    <td><table class="table3">
      <tr>
        <td  style="text-align:left;" valign="top"> 抵押权人/典当行签字：</td>
      </tr>
      <tr>
        <td valign="bottom" style="text-align:right;">年       月       日</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td rowspan="3">抵<br />
    押<br />
    权<br />
    人<br />
    /<br />
    典<br />
    当<br />
    行<br />
    的<br />
    代<br />
    理<br />
    人</td>
    <td>姓名/名称</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td rowspan="3" ><table class="table3">
      <tr>
        <td  style="text-align:left;" valign="top">代理人签字：</td>
      </tr>
      <tr>
        <td valign="bottom" style="text-align:right;">&nbsp;</td>
      </tr>
      <tr>
        <td valign="bottom" style="text-align:right;">&nbsp;</td>
      </tr>
      <tr>
        <td valign="bottom" style="text-align:right;">年       月       日</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>邮寄地址</td>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td>邮政编码</td>
    <td>&nbsp;</td>
    <td>联系电话</td>
    <td>&nbsp;</td>
  </tr>
</table>
</div>
</form>
<input type="button" name="打印" value="打印" onclick="anjian()" />
</body>
</html>