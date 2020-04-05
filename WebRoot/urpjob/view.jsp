<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.suntendy.urp.job.model.UrpJob" %>
<%String path = request.getContextPath();%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/> 
<title>【定时任务】详细信息</title>
<script src="<%=path%>/js/jquery.js" type="text/javascript" language="javascript" ></script>
<link rel="stylesheet" href="<%=path%>/plugins/formValidator/style/validator.css" type="text/css" />
<script language="javascript" src="<%=path%>/plugins/formValidator/formValidator.js"></script> 

<script src="<%=path%>/plugins/bgjs/cicylib-all-debug.js" type="text/javascript"  language="javascript" ></script>
<link id="globalCss" rel="stylesheet" href="<%=path%>/plugins/bgjs/themes/global.css" type="text/css"/>
<script src="<%=path%>/plugins/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>

<link rel="stylesheet" href="<%=path%>/style/common.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/plugins/ecside/style/ecside_style.css" />
<script type="text/javascript" src="<%=path%>/plugins/ecside/ecside.js" ></script>
</head>

<%
	//取查询条件对象
	UrpJob urpJob = (UrpJob)request.getAttribute("urpJob");

%>
<body>
<!--当前位置start-->
<div id="positionDiv" class="positionDiv">&nbsp;<span class="positionDivPre">&nbsp;&nbsp;&nbsp;</span>当前位置：【定时任务】详细信息</div>
<!--当前位置end-->
<div id="gridSearchDiv" class="gridSearchDiv">
<table border="0"  cellspacing="0" cellpadding="0"  class="searchTable"  width="100%">
	<tr>
		<td class="title_left" width="10%">
		任务名称：</td>
		<td class="title_right" width="40%">
			<input value="<%=urpJob.getJobname()==null?"":urpJob.getJobname()%>"  name="urpJob.jobname"  id="jobname" class="g-ipt-text g-corner pecent90" />
		</td>
		<td class="title_left" width="10%">
		任务组：</td>
		<td class="title_right" width="40%">
		<input value="<%=urpJob.getJobgroup()==null?"":(urpJob.getJobgroup()).equals("A")?"内部定时任务":"其他" %>"  name="urpJob.jobgroup"  id="jobgroup" class="g-ipt-text g-corner pecent90" />

		</td>
	</tr>	
	<tr>
		<td class="title_left" width="10%">
		调度规则：</td>
		<td class="title_right" width="40%">
			<input value="<%=urpJob.getCron()==null?"":urpJob.getCron()%>"  name="urpJob.cron"  id="cron" class="g-ipt-text g-corner pecent90" />
		</td>
		<td class="title_left" width="10%">
		任务执行主体：</td>
		<td class="title_right" width="40%">
			<input value="<%=urpJob.getJobclass()==null?"":urpJob.getJobclass()%>"  name="urpJob.jobclass"  id="jobclass" class="g-ipt-text g-corner pecent90" />
		</td>
	</tr>	
	<tr>
		<td class="title_left" width="10%">
		任务类型：</td>
		<td class="title_right" width="40%">
		<input value="<%=urpJob.getJobtype()==null?"":(urpJob.getJobtype()).equals("0")?"同步":"异步" %>"  name="urpJob.jobtype"  id="jobtype" class="g-ipt-text g-corner pecent90" />
        </td>
        <td class="title_left" width="10%">
         状态：</td>
        <td class="title_right" width="40%">   
        <input value="<%=urpJob.getStatus()==null?"":(urpJob.getStatus()).equals("0")?"启用":"停用" %>"  name="urpJob.status"  id="status" class="g-ipt-text g-corner pecent90" />

        </td>
	</tr>	
	<tr>
		<td class="title_left" width="10%">
		创建时间：</td>
		<td class="title_right" width="40%">
			<input value="<%=urpJob.getCreatetimeString()==null?"":urpJob.getCreatetimeString()%>"  name="urpJob.createtimeString"  id="createtimeString"  readonly  class="g-ipt-text g-corner pecent90 Wdate" onFocus="WdatePicker({isShowClear:true,readOnly:true})"/>
		</td>
		<td class="title_left" width="10%">
		更新时间：</td>
		<td class="title_right" width="40%">
			<input value="<%=urpJob.getUpdatetimeString()==null?"":urpJob.getUpdatetimeString()%>"  name="urpJob.updatetimeString"  id="updatetimeString"  readonly  class="g-ipt-text g-corner pecent90 Wdate" onFocus="WdatePicker({isShowClear:true,readOnly:true})"/>
		</td>
	</tr>	
	<tr>
        <td class="title_left" width="10%">
        任务描述：</td>
        <td class="title_right" width="40%" colspan="3">
            <input value="<%=urpJob.getJobdesc()==null?"":urpJob.getJobdesc()%>"  name="urpJob.jobdesc"  id="jobdesc" class="g-ipt-text g-corner pecent90" />
        </td>
	</tr>	
</table>
<div id="gridOperDiv" class="gridOperDiv" style="text-align:center;">
    <span id="bu-back"></span>
</div>
</body>
</html>

<script type="text/javascript">
$(document).ready(function(){
  new CC.ui.Button({showTo:'bu-back',title:'返 回',autoRender:true,css:'w:50',onclick: function(){loading();history.back();}});

    //渲染页面
    //为所有input增加class并设置focus、blur事件
    $("input[type=text],input[type=password],textarea").focus(function(){$(this).addClass("g-ipt-text-hover")}).blur(function(){$(this).removeClass("g-ipt-text-hover")});      
      
});

//等待
function loading(){
    //new CC.ui.Mask({target:top.document.body, opacity:.3});
    var loading = new CC.ui.Loading({showTo:document.body,targetLoadCS:true,autoRender:true});
};
</script>
