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
<title>校车驾驶资格申请表</title>
<style id="style1">
body,td{
	font-family:"宋体";
	font-size:12px;
	font-weight:bold;
}
table{
border-collapse:collapse;	
}
td{
	height:40px;
}
.title{	
    font-size: 30px;
	font-weight: bold;
	font-family: "宋体";
	letter-spacing:4px;
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
.border-r{
	border-right:1px solid #000;
}
.border-b{
	border-bottom:1px solid #000;
}
.border-t{
	border-top:1px solid #000;
}
.border-r-x{
	border-right:1px dotted #000;
}
.border-lt{
	border:1px solid #000;
	border-width:1px 0 0 1px;
}
.border_l{
	border-left:1px solid #000;
}
.border_ltr{
	border:1px solid #000;
	border-width:1px 1px 0 1px;
}
.border_rb{
	border:1px solid #000;
	border-width:0 1px 1px 0;
}
.input_line_b{
	border:none;
	border-bottom:1px solid #000;
}
p{
	margin:0;
	padding:0;
}
.p1{
	height:17px;
}
.p2{
	text-indent:2em;
	line-height:21px;
	font-weight:normal;
}
.ziti{
	font-family:"宋体";
	font-size:12px;
}
.tab_1 td{
text-align:left;	
}
.tal{
text-align:left;	
}
.tab_2,
.tab_3{
width:100%;
}
.tab_2 td,
.tab_3 td{
text-align:center;
}
.tab_3{
height:100px;
}
.tab_4{
border:0;	
}
.tab_4 td{
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
				var strFormHtml=strBodyStyle+"<body>"+document.getElementById("xcjszgsqb").innerHTML+"</body>";
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
<form id="xcjszgsqb">
	<div id="form1">
<P align=center class="title" style="margin-top:90px;">校车驾驶资格申请表</P>
<table width="700" border="0" cellpadding="0" cellspacing="0" style="margin:0 auto;">
  <tr>
    <td width="397" align="center">&nbsp;</td>
    <td width="97" align="center" class="border-lt ziti" style="border-left:3px solid #000;border-right:3px solid #000;border-top:3px solid #000;">受理岗签字签章</td>
    <td width="146" align="center" class="border_ltr" style="border-right:3px solid #000;border-top:3px solid #000;">&nbsp;</td>
  </tr>
</table>
<table width="700" border="1" bordercolor="#000000" cellpadding="0" cellspacing="0" style="border-collapse:collapse;margin:0 auto;border:3px solid #000;">
  <tr>
    <td width="3%" rowspan="8" align="center" class="ziti" style="border-right:3px solid #000;">申<br>
      请<br>
      人<br>
      信<br>
      息</td>
    <td width="5%" align="center" class="ziti" >姓名</td>
    <td height="25" colspan="5" align="center" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="20%" align="center" class="border-r"><%=URLDecoder.decode(request.getParameter("nameA"),"utf-8") %></td>
        <td width="8%" align="center" class="border-r ziti">性别</td>
        <td width="10%" align="center" class="border-r">
        	<c:if test="${param.Sex eq '1'}">男</c:if>
			<c:if test="${param.Sex eq '2'}">女</c:if>
        </td>
        <td width="16%" align="center" class="ziti">出生日期</td>
        <td width="15%" align="center" class="border_l"><%=request.getParameter("number").substring(6,14) %></td>
      </tr>
    </table></td>
    <td width="23%" height="25" align="center" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="39%" align="center" class="ziti">国籍</td>
        <td width="61%" align="center" class="border_l">中国</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td rowspan="2" align="center" class="ziti" >身份<br>
      证明<br>
      名称</td>
    <td height="25" colspan="2" align="center" >居民身份证</td>
    <td height="25" colspan="3" align="center" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="10%" align="center" class="border-r-x ziti">号码</td>
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
    </table></td>
    <td rowspan="3" align="center" class="ziti" >照片</td>
  </tr>
  <tr>
    <td height="25" colspan="2" align="center" >&nbsp;</td>
    <td height="25" colspan="3" align="center" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="10%" align="center" class="border-r-x ziti">号码</td>
        <td width="5%" align="center" class="border-r-x">&nbsp;</td>
        <td width="5%" align="center" class="border-r-x">&nbsp;</td>
        <td width="5%" align="center" class="border-r-x">&nbsp;</td>
        <td width="5%" align="center" class="border-r-x">&nbsp;</td>
        <td width="5%" align="center" class="border-r-x">&nbsp;</td>
        <td width="5%" align="center" class="border-r-x">&nbsp;</td>
        <td width="5%" align="center" class="border-r-x">&nbsp;</td>
        <td width="5%" align="center" class="border-r-x">&nbsp;</td>
        <td width="5%" align="center" class="border-r-x">&nbsp;</td>
        <td width="5%" align="center" class="border-r-x">&nbsp;</td>
        <td width="5%" align="center" class="border-r-x">&nbsp;</td>
        <td width="5%" align="center" class="border-r-x">&nbsp;</td>
        <td width="5%" align="center" class="border-r-x">&nbsp;</td>
        <td width="5%" align="center" class="border-r-x">&nbsp;</td>
        <td width="5%" align="center" class="border-r-x">&nbsp;</td>
        <td width="5%" align="center" class="border-r-x">&nbsp;</td>
        <td width="5%" align="center" class="border-r-x">&nbsp;</td>
        <td width="5%" align="center">&nbsp;</td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td align="center" class="ziti" >身份<br>
      证明<br>
      记载<br>
      地址</td>
    <td height="25" colspan="5" align="center" ><%=URLDecoder.decode(request.getParameter("Address"),"utf-8") %></td>
  </tr>
  <tr>
    <td align="center" class="ziti" >联系地址</td>
    <td height="25" colspan="6" align="center" >&nbsp;</td>
  </tr>
  <tr>
    <td align="center" class="ziti" >固定电话</td>
    <td height="25" colspan="6" align="center" ><table width="100%" height="25" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="40%" align="center" class="border-r">&nbsp;</td>
        <td width="20%" align="center" class="border-r ziti">电子信箱</td>
        <td width="40%" align="center">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td align="center" class="ziti" >移动<br>
      电话</td>
    <td height="25" colspan="6" align="center" ><table width="100%" height="25" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="40%" align="center" class="border-r">&nbsp;</td>
        <td width="20%" align="center" class="border-r ziti" >邮政编码</td>
        <td width="40%" align="center">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td align="center" class="ziti" >驾驶证情况</td>
    <td height="25" colspan="6" align="center" >
		<table class="tab_2" cellspacing="0" cellpadding="0">
			<tr>
				<td width="15%" class="border_rb">档案编号</td>
				<td width="23%" class="border_rb"></td>
				<td width="14%" class="border_rb">准驾车型</td>
				<td width="23%" class="border_rb"></td>
				<td width="25%" rowspan="3">
                <table width="100%" class="table3">
				  <tr>
				    <td  style="text-align:left;" valign="top">本人签名：</td>
			      </tr>
				  <tr>
				    <td valign="bottom" style="text-align:right;">&nbsp;</td>
			      </tr>
				  <tr>
				    <td valign="bottom" style="text-align:right;">年月日</td>
			      </tr>
			    </table>
                </td>
			</tr>
			<tr>
				<td class="border_rb">有效期起始日期</td>
				<td class="border_rb"></td>
				<td class="border_rb">有效期限</td>
				<td class="border_rb"></td>
			</tr>
			<tr>
				<td class="border-r">初次领证日期</td>
				<td colspan="3" class="border-r"></td>
		    </tr>
		</table>
    </td>
  </tr>
  <tr>
    <td align="center" class="ziti" style="border-top:3px solid #000;border-right:3px solid #000;">申<br>
      请<br>
      业<br>
      务<br>
      种<br>
      类</td>
    <td colspan="7" align="center" class="ziti" style="border-top:3px solid #000;">
	  <table class="tab_3" cellspacing="0" cellpadding="0">
			<tr>
				<td width="205" class="border_rb"><input id="sqxcjszg" name="input" type="checkbox"  value=""/>
			    申请校车驾驶资格</td>
				<td width="20%" class="border_rb">申请的准驾车型</td>
			  <td width="605" class="border-b"></td>
			</tr>
			<tr>
				<td class="border-r"><input id="zxxcjszg" name="input2" type="checkbox"  value=""/>
			    注销校车驾驶资格</td>
				<td colspan="2"  style="text-align:left;font-weight:normal;"><input name="input3" type="checkbox"  value="" />
			    本人申请  
			      <input name="input4" type="checkbox"  value="" />
		        年龄条件不适合  
		        <input name="input5" type="checkbox"  value="" />
		        身体条件不适合  
		        <input name="input6" type="checkbox"  value="" />
		        在致人死亡或者重伤的交通事故负有责任  
		        <input name="input7" type="checkbox"  value="" />
		        有严重交通违法行为  
		        <input name="input8" type="checkbox"  value="" />
		        有记满12分或者犯罪记录  
		        <input name="input9" type="checkbox"  value="" />
		        其他</td>
			</tr>
		</table>
	</td>
  </tr>
  <tr>
    <td align="center" class="ziti" style="border-top:3px solid #000;border-right:3px solid #000;">申告内容<br></td>
    <td colspan="7" align="center" style="border-top:3px solid #000;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="2" class="tal">
          <p class="p2" style="text-align:center;margin:15px 0 0; font-weight:bold;">校车驾驶许可申请人申告内容：</p>
          <p class="p2">一、本人取得相应准驾车型驾驶证并具有3年以上驾驶经历； </p>
          <p class="p2">二、截止本记分周期，本人在最近连续3个记分周期内没有满分记录；</p>
          <p class="p2">三、本人驾驶机动车没有致人死亡或者重伤的交通事故责任记录;</p>
          <p class="p2"> 四、本人没有饮酒后驾驶或醉酒驾驶机动车的记录,最近1年内没有驾驶客运车辆超员、超速等严重交通违法行为;</p>
          <p class="p2">五、本人身体健康,无传染疾病,无癫痫、精神病等可能危及行车安全的疾病病史,无酗酒、吸毒行为记录;</p>
          <p class="p2">六、没有犯罪记录。</p>
          </td>
        </tr>
      <tr>
        <td width="75%" class="border-t">
        <p class="p2" style="text-align:center;font-size:20px;margin:15px 0;"> 本人郑重承诺： </p>
          <p class="p2" style="text-align:center;font-size:19x;"> 以上申告内容完全为真实情况，如发现本人存在违反规定的情形，本人自愿承担相应的法律责任。</p>
          <p style="text-indent:2em;">&nbsp;</p>
        </td>
        <td width="25%" class=" border-lt tal" style="border-top:1px solid #000;border-left:1px solid #000;"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" style="text-align:left;">本人签名：</td>
          </tr>
          <tr>
            <td style="text-align:right;" valign="bottom">年        月        日</td>
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