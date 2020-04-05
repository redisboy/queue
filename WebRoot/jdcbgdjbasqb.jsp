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
<title>机动车变更登记/备案申请表</title>
<style id="style1">
.fuze1 {	
	font-size:18px;
	text-indent:2em;
	text-align:left;
	padding-left:5px;
	font-weight:bold;
}
.fuze1 {	
	font-size:18px;
	text-indent:2em;
	text-align:left;
	padding-left:5px;
	font-weight:bold;
}
body,td{
	font-family:"宋体";
	font-size:13px;
}
*{
margin:0;
padding:2px;	
}
td{
	height:28px;
}
.table_inner td{
	height:34px;
	text-align:left;
}
.title{	
    font-size: 26px;
	font-weight: bold;
	font-family: "宋体";
	margin-top:90px;
}
.blackkuang {
	border: 1px solid #000000;
}
.fuze{
	font-size:18px;
	text-indent:2em;
	text-align:left;
	padding-left:5px;
	font-weight:bold;
}
hr{
	border:1px dashed #000;
	height:1px;
}
.tal{
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
		var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
				var strBodyStyle="<style>"+document.getElementById("style1").innerHTML+"</style>";
				var strFormHtml=strBodyStyle+"<body>"+document.getElementById("jdcbgdjbasqb").innerHTML+"</body>";
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
<form id="jdcbgdjbasqb">
	<div id="form1">
	<P align=center class="title" style="margin-bottom:15px;"> 机动车变更登记/备案申请表</P>
 <table width="670" border="1" bordercolor="#000000" align="center" cellpadding="0" cellspacing="0" style="border-collapse:collapse;margin:0 auto;border:3px solid #000;">
        <tr>
          <td height="25" colspan="3" align="center" >号牌种类</td>
          <td height="25" colspan="2" align="center" >&nbsp;</td>
          <td align="center" id="zdyzt">号牌号码</td>
          <td width="25%" align="center" id="zdyzt">&nbsp;</td>
        </tr>
        <tr>
          <td height="25" colspan="3" align="center" style="border-top:3px solid #000;border-bottom:3px solid #000;">申请事项</td>
          <td height="25" colspan="4" align="center" style="border-top:3px solid #000;border-bottom:3px solid #000;">变更后的信息</td>
        </tr>
        <tr>
          <td height="25" colspan="3" class="tal" ><input type="checkbox" name="checkbox" id="checkbox">变更机动车所有人姓名/名称</td>
          <td height="25" colspan="4" align="center" >&nbsp;</td>
   </tr>
        <tr>
          <td height="25" colspan="3" class="tal" ><input type="checkbox" name="checkbox2" id="checkbox2">共同所有的机动车变更所有人</td>
          <td height="25" colspan="4" align="center" >&nbsp;</td>
   </tr>
        <tr>
          <td height="25" colspan="3" class="tal" ><input type="checkbox" name="checkbox3" id="checkbox3">住所在车辆管理所辖区内迁移</td>
          <td height="25" colspan="4" align="center" >&nbsp;</td>
   </tr>
        <tr>
          <td height="25" colspan="3" class="tal" ><input type="checkbox" name="checkbox4" id="checkbox4">变更联系方式</td>
          <td height="25" colspan="4" align="center" ><table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_inner">
            <tr>
              <td width="25%" align="center" class="border-r ziti">邮寄地址：</td>
              <td align="center" class="border-r">&nbsp;</td>
              <td align="center" class="border-r">手机号码：</td>
              <td align="center" class="border-r">&nbsp;</td>
            </tr>
            <tr>
              <td width="25%" align="center" class="border-r ziti"> 邮政编码：</td>
              <td width="25%" align="center" class="border-r">&nbsp;</td>
              <td width="25%" align="center" class="ziti">固定电话：</td>
              <td width="25%" align="center" class="ziti">&nbsp;</td>
            </tr>
          </table></td>
   </tr>
        <tr>
          <td height="25" colspan="3" class="tal" ><input type="checkbox" name="checkbox5" id="checkbox5">住所迁出车辆管理所管辖区域</td>
          <td height="25" colspan="4" align="center" ><table width="98%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="12%" align="center">转入：</td>
              <td width="44%" align="right"> 省(自治区、直辖市)</td>
              <td width="44%" align="right">市（地、州）</td>
            </tr>
          </table></td>
   </tr>
        <tr>
          <td height="25" colspan="3" class="tal" ><input type="checkbox" name="checkbox6" id="checkbox6">变更后的使用性质</td>
          <td height="25" colspan="4" align="center" ><table width="98%" border="0" cellpadding="0" cellspacing="0" class="table_inner">
            <tr>
              <td><input name="input2" type="checkbox" id="input2" value="" />公路客运</td>
              <td><input name="input3" type="checkbox" id="input3" value="" />公交客运</td>
              <td><input name="input4" type="checkbox" id="input4" value="" />出租客运</td>
              <td><input name="input5" type="checkbox" id="input5" value="" />旅游客运  </td>
              <td><input name="input6" type="checkbox" id="input6" value="" />租赁</td>
            </tr>
            <tr>
              <td><input name="input7" type="checkbox" id="input7" value="" />
              货运 </td>
              <td><input name="input" type="checkbox" id="input" value="" />教练</td>
              <td><input name="input13" type="checkbox" id="input13" value="" />营转非</td>
              <td><input name="input14" type="checkbox" id="input14" value="" />出租营转非</td>
              <td><input name="input8" type="checkbox" id="input8" value="" />危险货物运输</td>
            </tr>
            <tr>
              <td><input name="input9" type="checkbox" id="input9" value="" />警用</td>
              <td><input name="input10" type="checkbox" id="input10" value="" />消防</td>
              <td><input name="input12" type="checkbox" id="input12" value="" />工程救险</td>
              <td><input name="input11" type="checkbox" id="input11" value="" />救护</td>
              <td><input name="input15" type="checkbox" id="input15" value="" />接送幼儿</td>
            </tr>
            <tr>
              <td><input name="input16" type="checkbox" id="input16" value="" />接送小学生</td>
              <td><input name="input17" type="checkbox" id="input17" value="" />接送中学生</td>
              <td><input name="input18" type="checkbox" id="input18" value="" />接送初中生</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </table></td>
   </tr>
        <tr>
          <td height="25" colspan="3" class="tal" ><input type="checkbox" name="checkbox7" id="checkbox7">更换发动机</td>
          <td colspan="3" rowspan="7" valign="top" style="padding:15px 0 0 15px;border-bottom:3px solid #000;">变更后的信息：</td>
          <td rowspan="7"  style="padding-left:10px;border-bottom:3px solid #000;"><span class="fuze1">机动车所有人及代理人对申请材料的真实有效性负责。</span></td>
   </tr>
        <tr class="tal">
          <td height="25" colspan="3" class="tal" ><input type="checkbox" name="checkbox8" id="checkbox8">更换车身/车架</td>
        </tr>
        <tr class="tal">
          <td height="25" colspan="3" class="tal" ><input type="checkbox" name="checkbox9" id="checkbox9">变更车身颜色</td>
   </tr>
        <tr>
          <td height="25" colspan="3" class="tal" ><input type="checkbox" name="checkbox10" id="checkbox10">
          更换整车</td>
   </tr>
        <tr class="tal">
          <td height="25" colspan="3" class="tal" ><input type="checkbox" name="checkbox11" id="checkbox11">
          重新打刻发动机号码</td>
        </tr>
        <tr class="tal">
          <td height="25" colspan="3" class="tal" ><input type="checkbox" name="checkbox12" id="checkbox12">
          重新打刻车辆识别代号</td>
   </tr>
        <tr class="tal">
          <td height="25" colspan="3" class="tal" style="border-bottom:3px solid #000;"><input type="checkbox" name="checkbox13" id="checkbox13">
          变更身份证明名称/号码</td>
   </tr>
        <tr>
          <td width="4%" rowspan="3" align="center" >代<br>
          理<br>
          人</td>
          <td width="10%" align="center" >姓名/名称</td>
          <td height="25" colspan="4" align="center" ><%=URLDecoder.decode(request.getParameter("DBnameA"),"utf-8") %></td>
          <td rowspan="3" align="center" ><br><p>              
            <table width="90%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td class="tal">机动车所有人（代理人）签字：：</td>
            </tr>
            <tr>
              <td align="right" valign="bottom" style="height:100px;">年   　月   　日</td>
            </tr>
          </table>
            <br>
          <p align="right">&nbsp;</p></td>
   </tr>
        <tr>
          <td align="center" >邮寄地址</td>
          <td height="25" colspan="4" align="center" >&nbsp;</td>
        </tr>
        <tr>
          <td align="center" >邮政编码</td>
          <td height="25" colspan="2" align="center" >&nbsp;</td>
          <td width="18%" align="center" id="zdyzt">手机号码</td>
          <td width="17%" align="center" id="zdyzt">&nbsp;</td>
        </tr>
</table>
</div>
</form>
<input type="button" name="打印" value="打印" onclick="anjian()" />
</body>
</html>