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
<title>机动车注册、转移、注销登记/转入申请表</title>
<style id="style1">
*{
margin:0;
padding:0;
}
body,td{
	font-family:"宋体";
	font-size:14px;
}
td{
	text-align:center;
	height:63px;
}
.font01 {	
    font-size: 25px;
	font-weight: bold;
	font-family: "宋体";
}
.title{	
    font-size: 28px;
	font-weight: bold;
	font-family: "宋体";
	margin:15px auto;
}
.blackkuang {
	border: 1px solid #000000;
}
.tab_1{
	border-collapse:collapse;
	margin:0 auto;
	text-align:center;
	width:690px;
}
.td_xh{
	height:30px;
	text-align:left;
}
div{
height:650px;	
}
.insert_tab1 td{
text-align:left;
padding-left:5px;	
}
.insert_tab2 td{
height:35px;
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
				var strFormHtml=strBodyStyle+"<body>"+document.getElementById("jdczczyzxdjzrsqb").innerHTML+"</body>";
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
<form id="jdczczyzxdjzrsqb">
	<div id="form1">
<P align=center class="title" style="margin-top:60px;">机动车注册、转移、注销登记/转入申请表</P>
<table width="690" class="tab_1" style="border:3px solid #000;">
     <tr>
        <td colspan="7" class="blackkuang" style="font-size:25px;"><strong>申请人信息栏</strong></td>
      </tr>
      <tr>
        <td width="5%"  rowspan="3" class="blackkuang">机<br />
        动<br />
        车<br />
        所<br />
        有<br />
        人</td>
        <td colspan="2" class="blackkuang">姓名/名称</td>
        <td colspan="2" class="blackkuang"><%=URLDecoder.decode(request.getParameter("nameA"),"utf-8") %></td>
        <td  class="blackkuang">邮政编码</td>
        <td width="18%"  class="blackkuang">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2" class="blackkuang">邮寄地址</td>
        <td colspan="4" class="blackkuang">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2" class="blackkuang">手机号码</td>
        <td width="18%"  class="blackkuang">&nbsp;</td>
        <td  class="blackkuang">&nbsp;</td>
        <td width="18%" class="blackkuang">固定电话</td>
        <td class="blackkuang">&nbsp;</td>
      </tr>
      <tr>
        <td class="blackkuang">代<br />
        理<br />
        人</td>
        <td colspan="2" class="blackkuang">姓名/名称</td>
        <td class="blackkuang"><%=URLDecoder.decode(request.getParameter("DBnameA"),"utf-8") %></td>
        <td width="18%" class="blackkuang">手机号码</td>
        <td colspan="2" class="blackkuang">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="7" class="blackkuang" style="border-top:3px solid #000;font-size:25px;"><strong>申请业务事项</strong></td>
      </tr>
      <tr>
        <td colspan="2" class="blackkuang">申请事项</td>
        <td colspan="5" class="blackkuang">
        <table width="100%" class="insert_tab1">
          <tr>
            <td width="25%"><input name="xgzl63247" type="checkbox" id="xgzl63247" value="B" />
              注册登记</td>
            <td width="25%"><input name="xgzl63248" type="checkbox" id="xgzl63248" value="B" />
            注销登记</td>
            <td width="25%"><input name="xgzl63249" type="checkbox" id="xgzl63249" value="B" />
              转移登记</td>
            <td width="25%"><input name="xgzl632410" type="checkbox" id="xgzl632410" value="B" />
              车辆转入</td>
          </tr>
          <tr>
            <td><input name="xgzl" type="checkbox" id="xgzl" value="B" />
            车辆转出</td>
            <td colspan="3"> 转出至：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;省(自治区、直辖市)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市（地、州）</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td colspan="2" class="blackkuang">号牌种类</td>
        <td colspan="2" class="blackkuang">&nbsp;</td>
        <td class="blackkuang">号牌号码</td>
        <td colspan="2" class="blackkuang">&nbsp;</td>
      </tr>
      <tr>
        <td rowspan="2" class="blackkuang">机<br />
        动<br />
        车</td>
        <td width="12%" class="blackkuang">品牌型号</td>
        <td colspan="2" class="blackkuang">&nbsp;</td>
        <td class="blackkuang">车辆识别代号</td>
        <td colspan="2" class="blackkuang">&nbsp;</td>
      </tr>
      <tr>
        <td class="blackkuang">使用性质</td>
        <td colspan="5" class="blackkuang">
        <table width="100%" class="insert_tab1">
          <tr>
            <td width="14%"><input name="xgzl2" type="checkbox" id="xgzl2" value="B" />
            非营运</td>
            <td width="16%"><input name="xgzl2" type="checkbox" id="xgzl3" value="B" />
            公路客运</td>
            <td width="19%"><input name="xgzl2" type="checkbox" id="xgzl4" value="B" />
            公交客运</td>
            <td width="16%"><input name="xgzl2" type="checkbox" id="xgzl5" value="B" />
            出租客运</td>
            <td width="23%"><input name="xgzl3" type="checkbox" id="xgzl6" value="B" />
            旅游客运
            <input name="xgzl17" type="checkbox" id="xgzl20" value="B" />
            租赁</td>
            <td width="12%"><input name="xgzl4" type="checkbox" id="xgzl7" value="B" />
            教练</td>
          </tr>
          <tr>
            <td><input name="xgzl5" type="checkbox" id="xgzl8" value="B" />
            接送幼儿</td>
            <td><input name="xgzl6" type="checkbox" id="xgzl9" value="B" />
            接送小学生</td>
            <td><input name="xgzl7" type="checkbox" id="xgzl10" value="B" />
            接送中小学生</td>
            <td><input name="xgzl8" type="checkbox" id="xgzl11" value="B" />
            接送初中生</td>
            <td><input name="xgzl9" type="checkbox" id="xgzl12" value="B" />
            危险货物运输</td>
            <td><input name="xgzl10" type="checkbox" id="xgzl13" value="B" />
            货运</td>
          </tr>
          <tr>
            <td><input name="xgzl11" type="checkbox" id="xgzl14" value="B" />
            消防 </td>
            <td><input name="xgzl12" type="checkbox" id="xgzl15" value="B" />
            救护</td>
            <td><input name="xgzl13" type="checkbox" id="xgzl16" value="B" />
            工程救险</td>
            <td><input name="xgzl14" type="checkbox" id="xgzl17" value="B" />
            警用</td>
            <td><input name="xgzl15" type="checkbox" id="xgzl18" value="B" />
            出租营转非</td>
            <td><input name="xgzl16" type="checkbox" id="xgzl19" value="B" />
            营转非</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td colspan="4" class="blackkuang" style="font-size:20px;text-align:left;text-indent:2em;border-top:3px solid #000;"><strong>机动车所有人及代理人对申请材料的真实有效性负责。</strong></td>
        <td colspan="3" class="blackkuang" style="border-top:3px solid #000;">
        <table width="100%" class="insert_tab2">
          <tr>
            <td style="text-align:left;padding-left:5px;">机动车所有人（代理人）签字：</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td style="text-align:right;padding-right:6px;">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日</td>
          </tr>
        </table></td>
      </tr>
    </table>
</div>
</form>
<input type="button" name="打印" value="打印" onclick="anjian()" />
</body>
</html>