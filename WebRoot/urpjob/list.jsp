<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.suntendy.core.page.Page"%>
<%@page import="com.suntendy.urp.job.model.UrpJob" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/> 
<title>【定时任务】列表</title>
<script src="<%=path%>/js/jquery.js" type="text/javascript" language="javascript" ></script>
<script src="<%=path%>/plugins/bgjs/cicylib-all-debug.js" type="text/javascript"  language="javascript" ></script>
<script src="<%=path%>/plugins/bgjs/base/cc.js" type="text/javascript"  language="javascript" ></script>
<link id="globalCss" rel="stylesheet" href="<%=path%>/plugins/bgjs/themes/global.css" type="text/css"/>
<script src="<%=path%>/plugins/My97DatePicker/WdatePicker.js" type="text/javascript" language="javascript" ></script>

<link rel="stylesheet" href="<%=path%>/style/common.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/plugins/ecside/style/ecside_style.css" />
<script type="text/javascript" src="<%=path%>/plugins/ecside/ecside.js" ></script>


<link rel="stylesheet" href="<%=path%>/plugins/formValidator/style/validator.css" type="text/css" />
<script language="javascript" src="<%=path%>/plugins/formValidator/formValidator.js"></script> 



</head>

<%
	//取分页数据类
 Page pageRequest = (Page)request.getAttribute("pageRequest");
 //取查询结果
 List resultList = pageRequest.getResult();
 //排序
 String sortColumn = (String)request.getAttribute("sortColumn");
 String sortOrder = (String)request.getAttribute("sortOrder");
 //总数
 String totalRows  = (String)request.getAttribute("totalRows");
 //取查询条件对象
 UrpJob urpJob = (UrpJob)request.getAttribute("urpJob");

 //任务状态下拉框
 String jobstatusOption = (String)request.getAttribute("jobstatusOption");
 //任务类别下拉框
 String jobtypeOption = (String)request.getAttribute("jobtypeOption");
 //任务组下拉框
 String jobgroupOption = (String)request.getAttribute("jobgroupOption");

%>
<body>
<form name="listForm" id="listForm" method="post" action="">
<div style="display:none;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<%=pageRequest.getPageNumber()%>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<%=pageRequest.getPageSize()%>"/>
	<input type="hidden" id="listAction" name="listAction" value="<%=path%>/urpjob/c_list.action"/>
	<input type="hidden" id="viewAction" name="viewAction" value="<%=path%>/urpjob/c_view.action"/>
	<input type="hidden" id="addAction" name="addAction" value="<%=path%>/urpjob/c_add.action"/>
	<input type="hidden" id="editAction" name="editAction" value="<%=path%>/urpjob/c_edit.action"/>
	<input type="hidden" id="delAction"  name="delAction"  value="<%=path%>/urpjob/c_deleteByIds.action"/>
	<input type="hidden" id="expAction" name="expAction" value="<%=path%>/urpjob/c_expExcel.action"/>
	<input type="hidden" id="sortColumn" name="sortColumn" value="<%=sortColumn%>"/>
	<input type="hidden" id="sortOrder" name="sortOrder" value="<%=sortOrder%>"/>
</div>

<!--当前位置start-->
<div id="positionDiv" class="positionDiv">&nbsp;<span class="positionDivPre">&nbsp;&nbsp;&nbsp;</span>当前位置：【定时任务】列表</div>
<!--当前位置end-->
<!--搜索start-->
<div id="gridSearchDiv" class="gridSearchDiv">
	<table border="0"  cellspacing="0" cellpadding="0"  class="searchTable"  width="100%">
		<tr>
			<td class="title_left" width="10%">
			任务名称：</td>
			<td class="title_right" width="40%">
			<input value="<%=urpJob.getJobname()==null?"":urpJob.getJobname()%>"  name="urpJob.jobname"  id="jobname" class="g-ipt-text g-corner pecent90"/>				
			</td>
            <td class="title_left" width="10%">
              任务组：</td>
            <td class="title_right" width="40%">
            <select name="urpJob.jobgroup" id="jobgroup" >
           			<option value="">请选择</option>
              		<option value="A">内部定时任务</option>
				  	
            </select>
            </td>
		</tr>
	</table>
</div>
<div id="searchOperDiv" class="searchOperDiv">
	<input type="submit" value="查 询" id="bu-ckbd"  class="bu-ckbd" onclick="return ECSideUtil.search();" />
</div>
<!--搜索end-->    
<div style="margin-left:2px">    
    <span id="bu-add"></span>&nbsp;
    
    <span id="bu-view"></span>&nbsp;
    
    <span id="bu-edit"></span>&nbsp;
    
    <span id="bu-del"></span>&nbsp;

</div>  

<!-- 列表 -->
<div class="ecSide"  >
 <table border="0"  cellspacing="0"  cellpadding="0"  class="tableRegion"  width="100%">
   <thead>
    <tr style="cursor:hand">
    <td class="tableHeader" ><input type="checkbox" name="chk_all" id="chk_all"/></td>
    <td class="tableHeader" onmouseover="ECSideUtil.lightHeader(this);"  onmouseout="ECSideUtil.unlightHeader(this);">序号</td>
    <td class="tableHeader" onClick="toggleSort('jobname')"  onmouseover="ECSideUtil.lightHeader(this);"  onmouseout="ECSideUtil.unlightHeader(this);">任务名称<span id="span_jobname" <%= sortColumn.equals("jobname")?sortOrder.equals("ASC")?"class=sortAsc":"class=sortDesc":""%>>&nbsp;</span></td>
    <td class="tableHeader" onClick="toggleSort('jobgroup')"  onmouseover="ECSideUtil.lightHeader(this);"  onmouseout="ECSideUtil.unlightHeader(this);">任务组<span id="span_jobgroup" <%= sortColumn.equals("jobgroup")?sortOrder.equals("ASC")?"class=sortAsc":"class=sortDesc":""%>>&nbsp;</span></td>
    <td class="tableHeader" onClick="toggleSort('jobdesc')"  onmouseover="ECSideUtil.lightHeader(this);"  onmouseout="ECSideUtil.unlightHeader(this);">任务描述<span id="span_jobdesc" <%= sortColumn.equals("jobdesc")?sortOrder.equals("ASC")?"class=sortAsc":"class=sortDesc":""%>>&nbsp;</span></td>
    <td class="tableHeader" onClick="toggleSort('jobtype')"  onmouseover="ECSideUtil.lightHeader(this);"  onmouseout="ECSideUtil.unlightHeader(this);">任务类型<span id="span_jobtype" <%= sortColumn.equals("jobtype")?sortOrder.equals("ASC")?"class=sortAsc":"class=sortDesc":""%>>&nbsp;</span></td>
    <td class="tableHeader" onClick="toggleSort('status')"  onmouseover="ECSideUtil.lightHeader(this);"  onmouseout="ECSideUtil.unlightHeader(this);">状态<span id="span_status" <%= sortColumn.equals("status")?sortOrder.equals("ASC")?"class=sortAsc":"class=sortDesc":""%>>&nbsp;</span></td>
  	<td class="tableHeader" onmouseover="ECSideUtil.lightHeader(this);"  onmouseout="ECSideUtil.unlightHeader(this);">操作</td>
  	</thead>
  	<tbody>
  	<%
   	StringBuffer resultBuf = new StringBuffer(); 
   	for(int i=0;i<resultList.size();i++){
   		int xh = (pageRequest.getPageNumber()-1)*pageRequest.getPageSize()+i+1;
   		String trClass = "even";
   		if(i%2==0){
   			trClass = "odd";
   		}
   	    UrpJob _urpJob = (UrpJob)resultList.get(i);
   	 	resultBuf.append("<tr id=\"tr_"+i+"\" class='"+trClass+"' onmouseover=\"ECSideUtil.lightRow(this);\"  onmouseout=\"ECSideUtil.unlightRow(this);\">");
     	resultBuf.append("<td style=\"text-align:center;\"><input type=\"checkbox\" name=\"ids\" id=\"_"+i+"\" value=\""+_urpJob.getId()+"\" onclick=\"ECSideUtil.selectRow(document.getElementById('tr_"+i+"'));\"></td>");
   		resultBuf.append("<td style=\"text-align:center;\">"+xh+"</td>"); 
   		resultBuf.append("<td>");
   		resultBuf.append(_urpJob.getJobname()==null?"":_urpJob.getJobname());
   		resultBuf.append("</td>");
   		resultBuf.append("<td>");
   		resultBuf.append(_urpJob.getJobgroup()==null?"":(_urpJob.getJobgroup()).equals("A")?"内部定时任务":"其他");
   		resultBuf.append("</td>");
   		resultBuf.append("<td>");
   		resultBuf.append(_urpJob.getJobdesc()==null?"":_urpJob.getJobdesc());
   		resultBuf.append("</td>");
   		resultBuf.append("<td>");
   		resultBuf.append(_urpJob.getJobtype()==null?"":(_urpJob.getJobtype()).equals("0")?"同步":"异步");
   		resultBuf.append("</td>");
   		resultBuf.append("<td id=\"td5_"+i+"\">");
        if(_urpJob.getStatus()!=null && !"0".equals(_urpJob.getStatus()))
            resultBuf.append(_urpJob.getStatus()==null?"":"<font color='red'>"+"停用"+"</font>");
        else
           resultBuf.append(_urpJob.getStatus()==null?"":"启用");
   		resultBuf.append("</td>");
        resultBuf.append("<td style=\"text-align:center;\">");
        resultBuf.append("<a style=\"color:blue;\"  hideFocus href='javascript:void(0)'  onclick=\"doSchedule('"+_urpJob.getId()+"','"+_urpJob.getJobname()+"','0','td5_"+i+"')\"  unselectable='on'>启用</a>");
        resultBuf.append("&nbsp;<a style=\"color:blue;\"  hideFocus href='javascript:void(0)' onclick=\"doSchedule('"+_urpJob.getId()+"','"+_urpJob.getJobname()+"','1','td5_"+i+"')\" unselectable='on'>停用</a>");
        resultBuf.append("</td>");
		resultBuf.append("</tr>\n");
   	}
   	out.println(resultBuf.toString());
   %>
    </tbody>
</table>
<!-- 分页工具条 -->
<div id="paginationBody">
<script>
	document.write(ECSideUtil.createPagination('<%=totalRows%>','<%=pageRequest.getPageSize()%>','<%=pageRequest.getPageNumber()%>'));
</script>
</div>

</div>

</form>

</body>
</html>

<script language="javascript" >
<!--
$(document).ready(
    function(){
    
  new CC.ui.Button({showTo:'bu-add',title:'新 增',autoRender:true,css:'w:50',onclick:function(){add();}});
  new CC.ui.Button({showTo:'bu-edit',title:'修 改',autoRender:true,css:'w:50',onclick:function(){edit();}});
  new CC.ui.Button({showTo:'bu-del',title:'删 除',autoRender:true,css:'w:50',onclick: function(){del('该操作将彻底删除该任务及其所有任务运行日志！要删除吗？');}});
  new CC.ui.Button({showTo:'bu-view',title:'查 看',autoRender:true,css:'w:50',onclick:ECSideUtil.view});
  
        //为所有input增加class并设置focus、blur事件
        $("input[type=text],input[type=password],textarea").focus(function(){$(this).addClass("g-ipt-text-hover")}).blur(function(){$(this).removeClass("g-ipt-text-hover")});      
        //为所有button增加class并设置focus、blur事件
        $("#bu-ckbd").addClass("bu-ckbd").mouseover(function(){$(this).addClass("bu-ckbd-over")}).mouseout(function(){$(this).removeClass("bu-ckbd-over")});
        
        //选择name=ids的所有checkbox
        $("#chk_all").click(function(){
            $("input[name='ids']").attr("checked",$(this).attr("checked"));
            
            $("input[name='ids']").each(function() {
                if($(this).attr("checked")){
                    ECSideUtil.addClass(document.getElementById("tr"+$(this).attr("id")),ECSideConstants.ROW_SELECTLIGHT_CLASS);
                }else{
                    ECSideUtil.removeClass(document.getElementById("tr"+$(this).attr("id")),ECSideConstants.ROW_SELECTLIGHT_CLASS);
                }
            }); 
        }); 
        
        $("input[name='ids']").each(function() {
            if($(this).attr("checked")){
                ECSideUtil.addClass(document.getElementById("tr"+$(this).attr("id")),ECSideConstants.ROW_SELECTLIGHT_CLASS);
            }else{
                ECSideUtil.removeClass(document.getElementById("tr"+$(this).attr("id")),ECSideConstants.ROW_SELECTLIGHT_CLASS);
            }
        });
        
    }
);
//处理排序
function toggleSort(sortColumn) {
    var sortOrder ="<%=sortOrder%>";
    if(sortOrder=="ASC")
        sortOrder = "DESC";
    else
        sortOrder = "ASC";
    ECSideUtil.doJump(1,"<%=pageRequest.getPageSize()%>",sortColumn,sortOrder);
};

//等待
function loading(){
    //new CC.ui.Mask({target:top.document.body, opacity:.3});
    var loading = new CC.ui.Loading({showTo:document.body,targetLoadCS:true,autoRender:true});
};

//启用停用定时任务
function doSchedule(jobId,jobName,jobStatus,td5id){
    var _data = "urpJob.id="+jobId+"&urpJob.status="+jobStatus;  
     var result = false;
     $.ajax({
         type : "POST",
         url : "<%=path%>/urpjob/c_doSchedule.action",
         data: _data,
         async : false,
         success: function(data, textStatus, jqXHR){
             if(data == "true" ){
                 result = false;
                 if(jobStatus=="0"){
                    alert("定时任务【"+jobName+"】已启动!");
                    $("#"+td5id).html("启用");
                 }else{
                    alert("定时任务【"+jobName+"】已停用!");
                    $("#"+td5id).html("<font color='red'>停用</font>");
                 }
             }else if(data == "false" ){
                 result = true;
             }
         },
         error: function(jqXHR, textStatus, errorThrown){alert("服务器没有返回数据，可能服务器忙，请重试!");},
         onWait : "正在管理定时任务【"+jobName+"】，请稍候..."
     }); 
     if(result){
         loading();
         return true;
     }
     else{
         return false;
     }
}
//新增定时任务
function add(){
	
	var add_Action = $("#addAction").attr("value");
	window.open(add_Action,"newword","height:100;width:100;top:0;left:0;toolbar:no;resizable:yes;status:no") 
}
//修改定时任务
function edit(){
	var id;
	var checkNum = 0;
	$("input[name='ids']").each(function(){ 
		if($(this).attr("checked")){
			checkNum++;
			id =  $(this).val();
		};
	});   
	if(checkNum!=1){
		alert("请选择一项");
	}else{
		var edit_action = $("#editAction").attr("value");
		window.open(edit_action+"?id="+id,"newword","height:100;width:100;top:0;left:0;toolbar:no;resizable:yes;status:no")
	}
}
//删除定时任务
function del(msg){
  	var checkNum = 0;
	$("input[name='ids']").each(function(){ 
		if($(this).attr("checked")){
			checkNum++;
		};
	});   
	if(checkNum==0){
		alert("请选择一项!");
	}else{
		if(msg==undefined){
			msg = '确定要删除选中的项吗？';
		}else{
		}
		
		if(confirm(msg)){
		var del_action = $("#delAction").attr("value");
				$('#listForm').attr("action",del_action);
				$('#listForm').submit();
		}

	}
}
//-->
</script>
