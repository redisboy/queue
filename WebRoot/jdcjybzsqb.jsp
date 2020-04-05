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
<title>机动车检验标志申请表</title>
<style id="style1">
body{
font-family:"宋体";
font-size:14px;
}
*{
margin:0;
padding:0;
}
.p1{
font-size:25px;
font-weight:bold;
font-family:"宋体",sans-serif;
text-align:center;
}
.p2{
font-size:20px;
font-weight:bold;
font-family:"宋体",sans-serif;
text-align:center;
margin-bottom:15px;
}
.tab1{
width:700px;
border:1px solid #000;
border-collapse:collapse;
margin:0 auto;
text-align:center;
}
.tab1 td{
border:1px solid #000;
height:64px;
}
.tab2{
width:100%;	
}
.tab2 td{
border:none;	
}
.td1{
font-weight:bold;
font-size:20px;
}
.tab3 td{
border:0;
}
.tab3 .border_r{
border-right:1px solid #000;
}
.tab3 .border_b{
border-bottom:1px solid #000;
}
.p3{
text-align:left;
margin:5px 0 15px 0;
text-indent:2em;
line-height:25px;
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
				var strFormHtml=strBodyStyle+"<body>"+document.getElementById("jdcjybzsqb").innerHTML+"</body>";
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
<form id="jdcjybzsqb">
	<div id="form1">
	<p class="p1">机动车检验标志申请表</p>
<p class="p2">（适用于6年内免检车）</p>
<table class="tab1">
    <tr>
        <td colspan="7" class="td1">申请人信息栏</td>
    </tr>
    <tr>
        <td width="5%" rowspan="3">机<br />
        动<br />
        车<br />
        所<br />
        有<br />
        人</td>
        <td width="20%">姓名/名称</td>
        <td colspan="2"><%=URLDecoder.decode(request.getParameter("nameA"),"utf-8") %></td>
        <td width="20%">邮政编码</td>
        <td width="20%"></td>
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
        <td>代<br />
        理<br />
        人</td>
        <td>姓名/名称</td>
        <td width="20%"><%=URLDecoder.decode(request.getParameter("DBnameA"),"utf-8") %></td>
        <td width="15%">手机号码</td>
        <td colspan="2"></td>
    </tr>
    <tr>
        <td colspan="2">机动车号牌号码</td>
        <td colspan="5">&nbsp;</td>
    </tr>
    <tr>
      <td colspan="7">
            <table class="tab3">
                <tr>
                    <td width="20%" class="border_r border_b">机动车所有人<br />
                    （代理人）承诺</td>
                    <td width="80%" class="border_b">
                    <p class="p3">1、驾驶机动车上道路行驶前，对机动车安全技术性能进行认真检查，不驾驶安全设施不全等具有安全隐患的机动车，定期到专业机构对机动车制动、轮胎、灯光等安全项目进行检查保养，保证机动车安全性能；</p>
            <p class="p3">2、不违法改装机动车，不擅自改变机动车已登记的结构、构造或者特征。</p>
            <p class="p3">3、对申请材料的真实有效性负责。</p>
                    </td>
                </tr>
                <tr>
                    <td class="border_r">公安交管部门提示</td>
                    <td>
                        <p class="p3">《中华人民共和国道路交通安全法》第十六条规定，任何单位或者个人不得拼装机动车或者擅自改变机动车已登记的结构、构造或者特征。《机动车登记规定》（公安部令第124号）第五十七条规定，擅自改变机动车外形和已登记的有关技术数据的，由公安机关交通管理部门责令恢复原状，并处警告或者五百元以下罚款。擅自改装机动车属于违法行为，应承担法律责任，因非法改装造成交通事故的，还应承当相应交通事故责任。
                        </p>
                    </td>
                </tr>
            </table>		  
      </td>
  </tr>
    <tr>
        <td colspan="7" style="padding:20px;">
            <table class="tab2">
              <tr>
                <td  style="text-align:left;" valign="top">机动车所有人（代理人）签字：</td>
              </tr>
              <tr>
                <td valign="bottom" style="text-align:right;">年       月        日</td>
              </tr>
            </table>
        </td>
    </tr>
</table>
</div>
</form>
<input type="button" name="打印" value="打印" onclick="anjian()" />
</body>
</html>