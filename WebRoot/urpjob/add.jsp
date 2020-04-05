<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="ww" uri="/webwork"%>
<%String path = request.getContextPath();%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/> 
<title>添加【定时任务】</title>
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

<body>

<!--当前位置start-->
<div id="positionDiv" class="positionDiv">&nbsp;<span class="positionDivPre">&nbsp;&nbsp;&nbsp;</span>当前位置：添加【定时任务】</div>
<!--当前位置end-->
<div id="gridSearchDiv" class="gridSearchDiv">
<form  name="addForm" id="addForm" action="<%=path%>/urpjob/saveUrpJob.action" method="post">
<table border="0"  cellspacing="0" cellpadding="0"  class="searchTable"  width="100%">
	<ww:token name="addUrpJob.token" />
	<tr>
		<td class="title_left" width="10%">
		任务名称：</td>
		<td class="title_right" width="40%">
		<input value=""  name="urpJob.jobname"  id="jobname" class="g-ipt-text g-corner pecent90"/> <span class="bitian">*</span>
		</td>
        <td class="title_left" width="10%">
         任务组：</td>
        <td class="title_right" width="40%">
         <select name="urpJob.jobgroup" id="jobgroup" >
           			<option value="">请选择</option>
              		<option value="A">内部定时任务</option>
				  	
            </select>
        <span class="bitian">*</span>
        </td>
	</tr>	
	<tr>
		<td class="title_left" width="10%">
		调度规则：</td>
		<td class="title_right" width="40%" onmouseover="javascript:">
		<input value=""  name="urpJob.cron"  id="cron" class="g-ipt-text g-corner pecent90"/> <span class="bitian">*</span>
		</td>
		<td class="title_left" width="10%">
		任务执行类：</td>
		<td class="title_right" width="40%">
		<input value=""  name="urpJob.jobclass"  id="jobclass" class="g-ipt-text g-corner pecent90"/>
        <span class="bitian">*</span>
		</td>
	</tr>  	
	<tr>
		<td class="title_left" width="10%">
		任务类型：</td>
		<td class="title_right" width="40%">
		<select name="urpJob.jobtype" id="jobtype" >
           			<option value="">请选择</option>
              		<option value="0">同步</option>
              		<option value="1">异步</option>
				  	
            </select>
        <span class="bitian">*</span>
		</td>
        <td class="title_left" width="10%">
         任务描述：</td>
        <td class="title_right" width="40%">
        <input value=""  name="urpJob.jobdesc"  id="jobdesc" class="g-ipt-text g-corner pecent90"/> <span class="bitian">*</span>
        </td>
	</tr>
</table>


<div id="gridOperDiv" class="gridOperDiv" style="text-align:center;">
    <span id="bu-submit"></span>&nbsp;
    
    <span id="bu-back"></span>
</div>

<div style="text-align:left;margin:6px;">
<font color="blue">【调度规则】提示信息</font><p/>
0 0/5 * * * ?   每隔5分钟触发<br>
0 0 10,14,16 * * ? 每天上午10点，下午2点，4点触发<br>
0 0/30 9-17 * * ?   朝九晚五工作时间内每半小时触发<br>
0 0 12 ? * WED 表示每个星期三中午12点 <br>
0 0 12 * * ? 每天中午12点触发 <br>
0 15 10 ? * *  每天上午10:15触发 <br>
0 15 10 * * ? 每天上午10:15触发 <br>
0 15 10 * * ? *  每天上午10:15触发 <br>
0 15 10 * * ? 2005  2005年的每天上午10:15触发<br> 
0 * 14 * * ? 在每天下午2点到下午2:59期间的每1分钟触发<br> 
0 0/5 14 * * ? 在每天下午2点到下午2:55期间的每5分钟触发 <br>
0 0/5 14,18 * * ? 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发<br> 
0 0-5 14 * * ? 在每天下午2点到下午2:05期间的每1分钟触发 <br>
0 10,44 14 ? 3 WED  每年三月的星期三的下午2:10和2:44触发 <br>
0 15 10 ? * MON-FRI  周一至周五的上午10:15触发 <br>
0 15 10 15 * ? 每月15日上午10:15触发 <br>
0 15 10 L * ? 每月最后一日的上午10:15触发 <br>
0 15 10 ? * 6L  每月的最后一个星期五上午10:15触发<br> 
0 15 10 ? * 6L 2002-2005  2002年至2005年的每月的最后一个星期五上午10:15触发<br> 
0 15 10 ? * 6#3  每月的第三个星期五上午10:15触发<br>
</div>
</form>	
</body>
</html>

<script type="text/javascript">
$(document).ready(function(){
  new CC.ui.Button({showTo:'bu-submit',title:'提 交',autoRender:true,css:'w:50',onclick: function(){ECSideUtil.submit('addForm');}});
 
    //渲染页面
    //为所有input增加class并设置focus、blur事件
    $("input[type=text],input[type=password],textarea").focus(function(){$(this).addClass("g-ipt-text-hover")}).blur(function(){$(this).removeClass("g-ipt-text-hover")});      
   
    $.formValidator.initConfig({formID:"addForm",alertMessage:true,tidyMode:true,onSuccess:function(){
           //校验定时任务名称
           var verifyRst = false;
           
           var _data = "urpJob.jobname="+$("#jobname").attr("value");
          
           var result = false;
           $.ajax({
               type : "POST",
               url : "<%=path%>/urpjob/c_verifyJobname.action",
               data: _data,
               async : false,
               success: function(data, textStatus, jqXHR){
                   if(data == "false" ){
                       result = false;
                       top.CC.Util.alert("该【定时任务名称】已存在，请更换!");
                   }else if(data == "true" ){
                       result = true;
                   }
               },
               error: function(jqXHR, textStatus, errorThrown){top.CC.Util.alert("服务器没有返回数据，可能服务器忙，请重试!");},
               onWait : "正在对【定时任务名称】进行合法性校验，请稍候..."
           }); 
           if(result){
               loading();
               return true;
           }
           else{
               return false;
           }
           
        },onError:function(msg){
            top.CC.Util.alert(msg);
        }
    });
    $("#jobname").formValidator().inputValidator({min:1,max:256,onError:"【任务名称】不能为空且不能大于256个字符,请确认!"});
    $("#jobgroup").formValidator().inputValidator({min:1,max:256,onError:"【任务组】不能为空且不能大于256个字符,请确认!"});
    $("#cron").formValidator().inputValidator({min:1,max:32,onError:"【调度规则】不能为空且不能大于32个字符,请确认!"});
    $("#jobclass").formValidator().inputValidator({min:1,max:256,onError:"【任务执行类】不能为空且不能大于256个字符,请确认!"});
    $("#jobtype").formValidator().inputValidator({min:1,max:32,onError:"【任务类型】不能为空且不能大于32个字符,请确认!"});
    $("#jobdesc").formValidator().inputValidator({min:1,max:256,onError:"【任务描述】不能为空且不能大于256个字符,请确认!"});
});
//等待
function loading(){
    //new CC.ui.Mask({target:top.document.body, opacity:.3});
    var loading = new CC.ui.Loading({showTo:document.body,targetLoadCS:true,autoRender:true});
};
</script>
