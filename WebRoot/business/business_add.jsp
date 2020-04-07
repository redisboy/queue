<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>添加业务类型</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/queue/js/jquery.js"></script>
<script type="text/javascript" src="/queue/js/ajax.js"></script>
<script type="text/javascript">
window.onload = function() {
	//为business输入框添加blur事件
	$("#business").blur(function(){
		var business = $("#business").val();
		//检查是否为空
		if(""==business){
		   $("#businessInfo").html("<img src='/queue/images/wrong.gif'/>业务类型不能为空!");
			return;
		}
		//检查唯一性
		var url = "checkBusiness.action";
		var param = "business="+business;
		var result = new MyJqueryAjax(url,param).request();
		if("1"==result){
	    	$("#businessInfo").html("<img src='/queue/images/wrong.gif'/>业务类型已被占用!");
	    	$("#business").val(""); 
		}else{
		    $("#businessInfo").html("<img src='/queue/images/right.gif'/>业务类型未占用!");
		}
	});
}
</script>
</head>
<body>
<form action="" name="form1" method="post">
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">添加业务类型</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    
    <table width="40%" border="0" cellpadding="1" cellspacing="1" class="table_back">
      <tr>
        <td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">业务类型</td>
        <td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;
          <input name="business" id="business" maxlength="20" style="width:200px"    />
          &nbsp;<font id="businessInfo" color="#ff0000"></font>
        </td>
      </tr>
      <tr>
        <td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">对应队列</td>
        <td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;
           <select name="queueCode" id="queueCode" >
             <c:forEach var="Queue" items="${list}" varStatus="vs">
                    <option value="${ Queue.code}">${ Queue.name}</option>
             </c:forEach>
           </select>
        </td>
      </tr>
      <tr>
        <td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">所属类型</td>
        <td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;
          <select id="flag" name="flag">
              <option value="01">机动车</option>
              <option value="02">驾驶人</option>
              <option value="03">互联网面签</option>
              <option value="04">违法</option>
          </select>
        </td>
      </tr>
      <tr>
        <td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">流转业务类型</td>
        <td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;
          <select id="liuzhuan" name="liuzhuan">
              <option value="0" selected="selected">==请选择==</option>
              <c:forEach var="bus" items="${businessList}" varStatus="vs">
                    <option value="${ bus.id}">${bus.name}</option>
             </c:forEach>
          </select>
          &nbsp;
        	<input type="radio" id="isautolz" name="isautolz" value="0" checked="checked"/>自动
			<input type="radio" id="isautolz" name="isautolz" value="1"/>手动
        </td>
      </tr>
      <tr>
        <td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">是否启用通知提档</td>
        <td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;
        	<select id="isOpenTztd" name="isOpenTztd">
              <option value="0">是</option>
              <option value="1" selected>否</option>
          	</select>
        </td>
      </tr>
      <tr>
        <td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">是否启用指纹采集</td>
        <td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;
        	<select id="isOpenZhiWen" name="isOpenZhiWen">
              <option value="0">是</option>
              <option value="1" selected>否</option>
          	</select>
        </td>
      </tr>
      <tr>
        <td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">接口专用</td>
        <td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;
        	<select id="outflag" name="outflag">
              <option value="0">是</option>
              <option value="1" selected>否</option>
          	</select>
        </td>
      </tr>
      <tr>
        <td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">每天发放的业务量</td>
        <td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;
        	<input id="ywl" name="ywl"  style="width:40px" value='1000'/>
        	<font color="red">格式：上午数量.中午数量.下午数量("."为分隔符,没有"."即默认全天数量)</font>
        </td>
      </tr>
      <tr>
        <td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">预计办理时长</td>
        <td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;
        	<input id="managemin" name="managemin"  style="width:40px" value='2'/>
        </td>
      </tr>
      <tr>
        <td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">帮助信息设置</td>
        <td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;
        	<textarea id="help_info" name="help_info"  style="width:80%;height:120px"></textarea>
        </td>
      </tr>
      <tr>
		<td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">不可办理状态</td>
		<td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;<span style="color: red;">机动车状态--</span>
			<c:forEach var="jdc" items="${listjdc}" varStatus="vs">
				<input type="checkbox" name="bkblyw" value="${jdc.sxh}" />${jdc.mc}&nbsp;
			</c:forEach><br><span style="color: red;">驾驶人状态--</span>
			<c:forEach var="jsr" items="${listjsr}" varStatus="vs">
				<input type="checkbox" name="bkblyw" value="${jsr.sxh}" />${jsr.mc}&nbsp;
			</c:forEach>
		</td>
      </tr>
      <tr>
		<td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">需要打印的表单</td>
		<td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;
			<input type="checkbox" name="dybd" value="jdcjsrstqksbb" />机动车驾驶人身体情况申报表&nbsp;
			<input type="checkbox" name="dybd" value="jdcjsrsttjzm" />机动车驾驶人身体条件证明&nbsp;
			<input type="checkbox" name="dybd" value="jdcjszsqb" />机动车驾驶证申报表&nbsp;
			<input type="checkbox" name="dybd" value="xcjszgsqb" />校车驾驶资格申请表&nbsp;
			<input type="checkbox" name="dybd" value="jdcbgdjbasqb" />机动车变更登记/备案申请表&nbsp;
			<input type="checkbox" name="dybd" value="jdcdydjzybasqb" />机动车抵押登记/质押备案申请表&nbsp;
			<input type="checkbox" name="dybd" value="jdcjybzsqb" />机动车检验标志申请表&nbsp;
			<input type="checkbox" name="dybd" value="jdcpzsqb" />机动车牌证申请表&nbsp;
			<input type="checkbox" name="dybd" value="jdczczyzxdjzrsqb" />机动车注册、转移、注销登记/转入申请表&nbsp;
		</td>
      </tr>
      <tr>
        <td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">对应预约业务名称(多个业务;隔开)</td>
        <td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;
<!--         	<input id="yyywmc" name="yyywmc" value="${yyywmc}" style="width:200px"/> -->
        	<textarea id="yyywmc" name="yyywmc" style="width:80%;height:120px"></textarea>
        </td>
      </tr>
      <tr>
        <td width="10%" height="35" bgcolor="#FFFFFF" class="tableheader1">打印表单业务名称</td>
        <td width="30%" height="35" bgcolor="#F1F9FD">&nbsp;
          <input name="bdywmc" id="bdywmc" maxlength="100" style="width:200px"    />
          &nbsp;<font id="" color="#ff0000">示例:初次申领@ccsl(多个用半角','隔开)</font>
        </td>
      </tr>
      
    </table>
    <br>
      <br>
	  <input name="bt1" class="button" type="button" value=" 添加 " onClick="addBusiness()"/>
	  <input name="bt2" class="button" type="button" value=" 返回 " onClick="window.history.back(-1);"/>
      <br>
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
</form>

<script type="text/javascript">
function addBusiness(){
      var business = document.form1.business.value;
      
	  if(business.length == 0){
		alert("业务类型不能为空!");
		return false;
	  }
	  var ywl =$("#ywl").val();
	  var apmywl=ywl.split(".");
	  if(ywl==""||ywl==null||ywl==undefined){
	  	alert("请填写业务数量");
	  	return false;
	  }
	  	var c = "\\."; // 要计算的字符
		var regex = new RegExp(c, 'g'); // 使用g表示整个字符串都要匹配
		var result = ywl.match(regex);
		var count = !result ? 0 : result.length;
		if(parseInt(count)>2 || parseInt(count)==1){
			alert("每日业务数量分隔符必须是2个点！");
			return false;
		}
	  for(var i =0;i<apmywl.length;i++){
		if(apmywl[i]==""||apmywl[i]==null||apmywl[i]==undefined){
			alert("请确认每日业务数量格式是否正确！");
			return false;
	 	 }
	  } 
    document.form1.action = "addBusiness.action";
	document.form1.submit();
}

</script>
</body>
</html>
