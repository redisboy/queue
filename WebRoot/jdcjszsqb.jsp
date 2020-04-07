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
<title>机动车驾驶证申报表</title>
<style id="style1">
body,td{
	font-family:"宋体";
	font-size:11px;
	font-weight:bold;
}
td{
	height:25px;
}
.title{	
    font-size: 25px;
	font-weight: bold;
	font-family: "宋体";
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
	border-right:1px solid #000;
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
		
		//打印任务
		var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
				var strBodyStyle="<style>"+document.getElementById("style1").innerHTML+"</style>";
				var strFormHtml=strBodyStyle+"<body>"+document.getElementById("jdcjszsqb").innerHTML+"</body>";
				LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_表单一");
				LODOP.ADD_PRINT_HTM('0%','0%','100%','100%',strFormHtml);
				if (LODOP.SET_PRINTER_INDEX("-1")){
					LODOP.PRINT();
				}
				//打开另一个表单页面
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
<form id="jdcjszsqb">
	<div id="form1">
<P align=center class="title" style="margin-top:25px;margin-bottom:10px;">机 动 车 驾 驶 证 申 请 表</P>
<table width="740" border="0" cellpadding="0" cellspacing="0" style="margin:0 auto;">
  <tr>
    <td width="174" align="center">&nbsp;</td>
    <td width="131" align="center" class="border-lt ziti" style="border-left:3px solid #000;border-top:3px solid #000;">受理岗签字签章</td>
    <td width="175" align="center" class="border-lt" style="border-left:3px solid #000;border-right:3px solid #000;border-top:3px solid #000;">&nbsp;</td>
    <td width="92" align="center" class="border-lt ziti" style="border-top:3px solid #000;">档案编号</td>
    <td width="126" align="center" class="border_ltr" style="border-left:3px solid #000;border-right:3px solid #000;border-top:3px solid #000;">&nbsp;</td>
  </tr>
</table>
<table width="740" border="3" bordercolor="#000000" cellpadding="0" cellspacing="0" style="border-collapse:collapse;margin:0 auto;">
  <tr>
    <td width="3%" rowspan="6" align="center" class="ziti">申<br/>
      请<br/>
      人<br/>
      信<br/>
      息</td>
    <td width="5%" align="center" class="ziti" >姓名</td>
    <td height="25" colspan="5" align="center" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="40%" align="center" class="border-r"><%=URLDecoder.decode(request.getParameter("nameA"),"utf-8") %></td>
        <td width="8%" align="center" class="border-r ziti">性别</td>
        <td width="20%" align="center" class="border-r">
			<c:if test="${param.Sex eq '1'}">男</c:if>
			<c:if test="${param.Sex eq '2'}">女</c:if>
		</td>
        <td width="14%" align="center" class="ziti">出生日期</td>
        <td width="18%" align="center" class="border_l"><%=request.getParameter("number").substring(6,14) %></td>
      </tr>
    </table></td>
    <td width="18%" height="25" align="center" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="39%" align="center" class="ziti">国籍</td>
        <td width="61%" align="center" class="border_l">中国</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td rowspan="2" align="center" class="ziti" >身份<br/>
      证明<br/>
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
    <td rowspan="5" align="center" class="ziti" >照片</td>
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
    <td align="center" class="ziti" >邮寄<br/>
      地址</td>
    <td height="25" colspan="5" align="center" >&nbsp;</td>
  </tr>
  <tr>
    <td align="center" class="ziti" >固定电话</td>
    <td height="25" colspan="5" align="center" ><table width="100%" height="25" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="65%" align="center" class="border-r">&nbsp;</td>
        <td width="16%" align="center" class="border-r ziti">电子信箱</td>
        <td width="19%" align="center">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td align="center" class="ziti" >移动<br/>
      电话</td>
    <td height="25" colspan="5" align="center" ><table width="100%" height="25" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="65%" align="center" class="border-r">&nbsp;</td>
        <td width="16%" align="center" class="border-r ziti" >邮政编码</td>
        <td width="19%" align="center">&nbsp;</td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td rowspan="2" align="center" class="ziti" style="border-top:3px solid #000;border-bottom:3px solid #000">申<br/>
      请<br/>
      业<br/>
      务<br/>
      种<br/>
      类</td>
    <td height="25" colspan="7" align="center" class="ziti" style="border-top:3px solid #000">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tab_1">
      <tr>
        <td width="128" align="center" class="border-r border-b"><input id="ccsl" name="input" type="checkbox"  value=""/>初次申领</td>
        <td width="40" rowspan="4" align="center" class="border-r">申请的<br/>
          准驾车<br/>
          型代号</td>
        <td width="50" rowspan="4" class="border-r ">&nbsp;</td>
        <td  colspan="2" align="center" class="border-b" ><input name="input13" type="checkbox"  value="" />驾校培训
    	  <input name="input14" type="checkbox"  value="" />有驾驶经历
    	  <input name="input15" type="checkbox"  value="" />自学直考        </td>
      </tr>
      <tr>
        <td class="border-r border-b"><input name="input2" id="zjzjcx" type="checkbox"  value=""/>增加准驾车型</td>
        <td  colspan="2" align="center"  class="border-b" ><input name="input16" type="checkbox"  value="" />驾校培训
          <input name="input16" type="checkbox"  value="" />全日制职业教育
          <input name="input16" type="checkbox"  value="" />最高准驾车型被注销
          <input name="input16" type="checkbox"  value="" />自学直考 </td>
      </tr>
      <tr>
        <td class="border-r border-b"><input id="cjjjszsl" name="input3" type="checkbox"  value=""/>持军警驾驶证申领</td>
        <td  colspan="2" align="center"  class="border-b" ><input name="input45" type="checkbox"  value="" />军队驾驶证
          <input name="input45" type="checkbox"  value="" />武警驾驶证 </td>
      </tr>
      <tr>
        <td class="border-r"><input id="cjwjszsl" name="input4" type="checkbox"  value=""/>持境外驾驶证申领</td>
        <td  colspan="2" align="center"><input name="input17" type="checkbox"  value="" />香港驾驶证
          <input name="input17" type="checkbox"  value="" />澳门驾驶证
          <input name="input17" type="checkbox"  value="" />台湾驾驶证
          <input name="input18" type="checkbox"  value="" />外国驾驶证 </td>
      </tr>
      <tr>
        <td colspan="5" class="border-r border-t"><input id="zjshhz" name="input5" type="checkbox"  value=""/>证件损毁换证
<input id="zrhz" name="input24" type="checkbox"  value=""/>转入换证
  <input id="yxqmhz" name="input25" type="checkbox"  value=""/>有效期满换证</td>
        </tr>
      <tr>
        <td colspan="3" class="border-r border-t"><input id="ddgdnlhz" name="input26" type="checkbox"  value=""/>达到规定年龄换证
          <input id="zyjdzjcxhz" name="input27" type="checkbox"  value=""/>自愿降低准驾车型换证</td>
        <td width="146" rowspan="2" class="border-t border-r" style="text-align:center;">申请的准驾车型代号</td>
        <td width="219" rowspan="2" class="border-t">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="3" class="border-r border-t"><input id="ysttjbhjdzjcxhz" name="input37" type="checkbox"  value=""/>因身体条件变化降低准驾车型换证</td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td height="25" colspan="7" align="center" class="ziti" style="border-bottom:3px solid #000"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td colspan="2" class="tal border-b"><input id="xxbhhz" name="input6" type="checkbox"  value=""/>信息变化换证</td>
        <td colspan="2" align="center" class="border_l border-b">变更事项</td>
        <td width="18%" class="border_l">&nbsp;</td>
        <td width="12%" align="center" class="border_l">变更内容</td>
        <td width="39%" class="border_l">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2" class="tal  border-b"><input id="xxba" name="input38" type="checkbox"  value=""/>信息备案</td>
        <td colspan="2" align="center" class="border_l border-b">从业单位</td>
        <td colspan="3" class="border_l border-t">&nbsp;</td>
        </tr>
      <tr>
        <td colspan="3" class="tal  border-b"><input id="bz" name="input7" type="checkbox"  value=""/>补证</td>
        <td width="5%" rowspan="9" align="center" class="border-lt">原<br/>
          因</td>
        <td colspan="3" class="tal border-lt"><input name="input12" type="checkbox"  value="" />丢失
          <input name="input19" type="checkbox"  value="" />其他</td>
        </tr>
      <tr class="tal">
        <td colspan="3" class="tal"><input id="zx" name="input8" type="checkbox"  value=""/>注销</td>
        <td colspan="3" class="border-lt"><input name="input20" type="checkbox"  value="" />本人申请
<input name="input28" type="checkbox"  value="" />
死亡
<input name="input29" type="checkbox"  value="" />
身体条件不适合
<input name="input30" type="checkbox"  value="" />
丧失民事行为能力
<input name="input31" type="checkbox"  value="" />
其他</td>
        </tr>
      <tr class="tal">
        <td colspan="3" class="tal"><input id="zxzgzjcx" name="input39" type="checkbox"  value=""/>注销最高准驾车型</td>
        <td colspan="3" rowspan="2" class="border-lt"><input name="input42" type="checkbox"  value="" />
          发生交通事故造成人员死亡，承担同等以上责任<br/>
          <input name="input43" type="checkbox"  value="" />
          连续三个记分周期不参加审验  □记满12分<br/>
          <input name="input44" type="checkbox"  value="" />
          延长的实习期内再次记6分以上但未达到12分</td>
      </tr>
      <tr class="tal">
        <td colspan="3" class="border-t border-b"><input id="zxsxzjcx" name="input40" type="checkbox"  value=""/>注销实习准驾车型</td>
        </tr>
      <tr>
        <td class="tal border-b"><input id="hfjszg" name="input9" type="checkbox"  value=""/>恢复驾驶资格</td>
        <td class=" border-lt border-r">准驾车<br/>
          型代号</td>
        <td class="border-lt">&nbsp;</td>
        <td colspan="3" class="tal border-lt"><input name="input32" type="checkbox"  value="" />
超过有效期一年以上未换证被注销未满两年<br/>
<input name="input33" type="checkbox"  value="" />
未按规定提交体检证明被注销未满两年</td>
      </tr>
      <tr class="tal">
        <td colspan="3" class="tal border-t"><input id="yqhz" name="input10" type="checkbox"  value=""/>延期换证
  <input id="yqsy" name="input41" type="checkbox"  value=""/>延期审验<br/>
<input id="yqtjsttjzm" name="input11" type="checkbox"  value=""/>延期提交身体条件证明</td>
        <td colspan="3" class="border-lt"><input name="input34" type="checkbox"  value="" />服兵役
  <input name="input35" type="checkbox"  value="" />出国（境）
<input name="input36" type="checkbox"  value="" />其他</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="25" colspan="3" align="center" class="ziti" >申请方式</td>
    <td height="25" colspan="5" align="center" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="center"><input name="input21" type="checkbox"  value="" <c:if test="${param.DBbdbj eq '0'}">checked</c:if>/>
          本人申请
          <input name="input22" type="checkbox"  value="" />
          监护人申请 
          <input name="input23" type="checkbox"  value="" <c:if test="${param.DBbdbj eq '1'}">checked</c:if>/>
          委托
          <input name="textfield5" type="text" class="input_line_b" id="textfield5" style="width:250px;" value="<%=URLDecoder.decode(request.getParameter("DBnameA"),"utf-8") %>"/>
          代理申请<br/></td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="3" align="center" class="ziti" style="border-bottom:3px solid #000">委托代理人监护人信息</td>
    <td height="25" colspan="5" align="center" style="border-bottom:3px solid #000"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="18%" align="center" class="border-r">代理人/监护人姓名</td>
        <td width="15%" align="center" class="border-r"><%=URLDecoder.decode(request.getParameter("DBnameA"),"utf-8") %></td>
        <td width="16%" align="center" class="border-r">身份证明名称</td>
        <td width="18%" align="center" class="border-r">身份证</td>
        <td width="13%" class="border-r">身份证明号码</td>
        <td width="22%" align="center"><%=request.getParameter("DBnumber")%></td>
        </tr>
      <tr>
        <td align="center" class="border-t">联系地址</td>
        <td colspan="3" align="center" class="border-lt"><%=URLDecoder.decode(request.getParameter("DBAddress"),"utf-8") %></td>
        <td align="center" class="border-lt">联系电话 </td>
        <td align="center" class="border-lt">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td align="center" class="ziti" style="border-right:3px solid #000">申<br/>
      告<br/>
      的<br/>
      义<br/>
      务<br/>
      和<br/>
      内<br/>
    容<br/></td>
    <td colspan="7" align="center" ><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="70%" rowspan="3" class="border-r tal" style="border-right:3px solid #000;height:300px;">
        <p class="p2">申请人应当如实申告是否具有下列不准申请机动车驾驶证的情形：</p>
          <p class="p2">一、器质性心脏病、癫痫病、美尼尔氏症、眩晕症、癔病、震颤麻痹、精神病、痴呆以及影响肢体活动的神经系统疾病等妨碍安全驾驶疾病；</p>
          <p class="p2">二、三年内有吸食、注射毒品行为或者解除强制隔离戒毒措施未满三年，或者长期服用依赖性精神药品成瘾尚未戒除；</p>
          <p class="p2"> 三、提供虚假申请材料，以欺骗等不正当手段申领机动车驾驶证；</p>
          <p class="p2"> 四、造成交通事故后逃逸构成犯罪；</p>
          <p class="p2"> 五、饮酒后或者醉酒驾驶机动车发生重大交通事故构成犯罪；</p>
          <p class="p2">六、醉酒驾驶机动车或者饮酒后驾驶营运机动车依法被吊销机动车驾驶证未满五年；</p>
          <p class="p2"> 七、醉酒驾驶营运机动车依法被吊销机动车驾驶证未满十年； </p>
          <p class="p2"> 八、因其他情形依法被吊销机动车驾驶证未满二年</p>
          <p class="p2">九、驾驶许可依法被撤销未满三年</p>
          <p class="p2"> 十、法律和行政法规规定的其他不准申请的情形。</p>
          <p class="p2"> 上述内容本人已认真阅读，本人不具有所列的不准申请的情形。</p></td>
        <td width="30%" class="border-b">
        <p style="text-indent:2em;">申请人及代理人对申请材料的真实有效性负责。</p></td>
      </tr>
      <tr>
        <td class="border-b"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="left" valign="top">申请人签字：</td>
          </tr>
          <tr>
            <td align="right" valign="bottom">年月日</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="left" valign="top">代理人/监护人签字: </td>
          </tr>
          <tr>
            <td align="right" valign="bottom">年月日</td>
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