<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"%>
<%
	String clientIp = request.getHeader("x-forwarded-for");//客户端IP
	if (null == clientIp || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
		clientIp = request.getHeader("Proxy-Client-IP");
	}
	if (null == clientIp || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
		clientIp = request.getHeader("WL-Proxy-Client-IP");
	}
	if (null == clientIp || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
		clientIp = request.getRemoteAddr();
	}
%>
<html>
  <head>
    <title>领证发屏(发送取号人姓名)</title>
  </head>
  <meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/queue/plugs/ecside/css/ecside_style.css" />
<script type="text/javascript" src="/queue/plugs/ecside/js/prototype_mini.js" ></script>	
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside.js" ></script>
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside_msg_utf8_cn.js" ></script>
<script type="text/javascript" src="/queue/plugs/time/time.js" ></script> 
<body>
<%
	String rksj=(String)request.getAttribute("rksj");
 %>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">领证发屏(发送取号人姓名)</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
	<form action="" method="post" name="form1">
		 <!-- <table width="80%" border="0" cellpadding="1" cellspacing="1" class="table_back">
            <tr>
              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">姓名/号牌号码</td>
              <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="xm" id="xm" type="text" class="inputface" value=""/> 
              </td>
              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">证件号码</td>
              <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="idnumber" id="idnumber" type="text" class="inputface" value=""/> 
              </td>
            <td class=tableheader1 width=10% height=30>流水号</td>
	         <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	          <input name="lsh" id="lsh" type="text" class="inputface" value=""/> 
              </td>
            </tr>
             <tr>
              <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1">发证时间</td>
              <td width=150 bgColor=#f1f9fd height=30>&nbsp;
	         <%
          		if(null!=rksj && !rksj.equals("")){
          	 %>
	          <input name="rksj" id="rksj" type="text" class="inputface" value="<%=rksj %>"
	          onClick="setday(this)" readonly/>
	          <%}else{ %>
	          <input name="rksj" id="rksj" type="text" class="inputface" value="${today }"
	          onClick="setday(this)" readonly/>
	          <%} %>
             </td>
            <td class=tableheader1 width=10% height=30>资料类型:</td>
	         <td width=150 bgColor=#f1f9fd height=30>&nbsp;
          		<select name="zllx" id="zllx">
					<option value="">-请选择-</option>
					<option value="01">驾驶证</option>
					<option value="02">行驶证</option>
					<option value="03">登记证书</option>
					<option value="04">号牌</option>
					<option value="05">临时号牌</option>
					<option value="06">检验合格标志</option>
					<option value="07">人工输入</option>
				</select>
	           </td>
	           <td width="10%" height="25" bgcolor="#FFFFFF" class="tableheader1"></td>
              <td width=150 bgColor=#f1f9fd height=30>&nbsp;</td>
            </tr>
            
          </table><br/><br/> -->
          
          <table width="30%" border="0" cellpadding="1" cellspacing="1" class="table_back">
            <tr>
              <td width="40%" height="25" bgcolor="#FFFFFF" class="tableheader1"><p style="color: red;">手动查询:</p>姓名/号牌号码</td>
              <td width="60%" bgColor=#f1f9fd height=30 align="center" colspan="5">&nbsp;
	          <input style="width: 200px" name="lsxm" id="lsxm" type="text" class="inputface" value=""/> 
              </td>
            </tr>
          </table>
	
	          <input name="fsxm" id="fsxm" type="hidden" value="1" />
    <input name="button" type="button" class="button" value="上屏" onClick="shangping()"/><br><br>
     <table width="30%" border="0" cellpadding="1" cellspacing="1" class="table_back">
            <tr>
              <td width="40%" height="25" bgcolor="#FFFFFF" class="tableheader1"><p style="color: red;">扫描查询:</p>顺序号</td>
              <td width="60%" bgColor=#f1f9fd height=30 align="center" colspan="5">&nbsp;
	          <input style="width: 200px" name="sxh" id="sxh" type="text" class="inputface" value="" onkeydown='if(event.keyCode==13){checksxh();}'/>
	          <script language="JavaScript"> 
					document.getElementById('sxh').focus();
				</script> 
              </td>
            </tr>
          </table>
          </form>
    <table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" >
	<tr>
	<td> 
		<ec:table items="list" var="list" 
					action="queryAllLzxx.action"
					editable="false" 
					width="100%" 
					listWidth="100%"
					classic="true"
					doPreload="false"
					useAjax="false"
					resizeColWidth="false" 
					sortable="false"
					batchUpdate="false"
					nearPageNum="0"
					toolbarLocation="top"
					pageSizeList="15"
					showPrint="false"
					filterable="false"
					>
				<ec:row style="text-align:center">
					<ec:column width="5%" property="_0" title="序号" style="text-align:center" value="${GLOBALROWCOUNT}" editable="false" sortable="false"/>
				    <ec:column width="10%" property="xm" title="姓名/号牌号码" style="text-align:center" editable="false" />
				    <ec:column width="15%" property="idnumber" title="证件号码" style="text-align:center" editable="false" />
				    <ec:column width="20%" property="zllx" title="资料类型" style="text-align:center" editable="false" />
				    <ec:column width="25%" property="rksj" title="发证时间" style="text-align:center" editable="false" />
					<ec:column property="lsh" title="流水号" style="text-align:center" editable="false" />
					<ec:column property="ip" title="制证ip" style="text-align:center" editable="false" />
					<ec:column width="8%" property="operation" title="操   作" style="text-align:center" editable="false" />
				</ec:row>
				<c:if test="${msg != null}">
				<ec:extendrow>
			<tr style="background-color:#ffeedd" title="">
				<td colspan="10" style="text-align:center"><strong><font face='宋体' color='#5b92fa'><c:out value="${msg}"/></font></strong></td>
			</tr>
		</ec:extendrow>
		</c:if>
	</ec:table><br>
    </td>
	</tr>
	</table>
	<br>
    </td>
    <td width="8" background="/queue/images/t-5.jpg">&nbsp;</td>
  </tr>
  <tr>
    <td ><img src="/queue/images/t-6.jpg" width="7" height="11"></td>
    <td height="11" background="/queue/images/t-7.jpg"></td>
    <td ><img src="/queue/images/t-8.jpg" width="8" height="11"></td>
  </tr>
</table>
</body>
<script type="text/javascript">
	window.onload = function(){
		window.setInterval("query()",300000);
	}
	function query(){
		document.form1.action = "queryAllLzxx.action"; 
		document.form1.submit();
	}
	function sendLzxx(id){
		if(confirm("确定上传吗?")){ 
			document.form1.action = "sendLzxx.action?lsh="+id+"&clientIp="+"<%=clientIp%>";
			document.form1.submit();
		}
	}
	function updatelzxx(id){
		if(confirm("确定标记已领取吗?")){ 
			document.form1.action = "updatelzxx.action?lsh="+id;
			document.form1.submit();
		}
	}
	function shangping(){
		document.form1.action = "sendLzxx.action?clientIp="+"<%=clientIp%>";
			document.form1.submit();
	}
	function checkEmpty(){
		var result=false;
		var idnumber=document.getElementById("idnumber").attributes["value"].value;
		var xm=document.getElementById("xm").attributes["value"].value;
		var lsh=document.getElementById("lsh").attributes["value"].value;
		var rksj=document.getElementById("rksj").attributes["value"].value;
		if(!((idnumber+xm+lsh+rksj)=='')){
			query();
		}else{
			alert("查询条件不足");
		}
	}
	function checksxh(){
		document.form1.action = "querynameLzxxBysxh.action?clientIp="+"<%=clientIp%>"; 
		document.form1.submit();
	}
</script>
</html>