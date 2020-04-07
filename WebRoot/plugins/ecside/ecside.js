var ECSideUtil={};

var ECSideConstants={
	ROW_HIGHLIGHT_CLASS : "highlight",
	ROW_SELECTLIGHT_CLASS : "selectlight"
};

var ECSide=function(){
	var Me=this;
	Me.selectedRow=null;
}

ECSideUtil.lightHeader=function(tdObj){
	
	var className=tdObj.className;
	if (className){
		className=className.split(" ");
		className[0]+="Over";
	}
	tdObj.className=className.join(" ");

};

ECSideUtil.unlightHeader=function(tdObj){
	var className=tdObj.className;
	if (className){
		className=className.split(" ");
		if (className[0].lastIndexOf("Over")==className[0].length-"Over".length){
			className[0]=className[0].substring(0,className[0].length-"Over".length);
		}
	}
	tdObj.className=className.join(" ");
};

ECSideUtil.lightRow=function(rowObj){
	ECSideUtil.addClass(rowObj,ECSideConstants.ROW_HIGHLIGHT_CLASS);
};

ECSideUtil.unlightRow=function(rowObj){
	ECSideUtil.removeClass(rowObj,ECSideConstants.ROW_HIGHLIGHT_CLASS);
};
ECSideUtil.selectRow=function(rowObj){
	var selectlightClassName=ECSideConstants.ROW_SELECTLIGHT_CLASS;
	if (ECSideUtil.hasClass(rowObj,selectlightClassName)){
		ECSideUtil.removeClass(rowObj,selectlightClassName);
		return;
	}
	ECSideUtil.addClass(rowObj,selectlightClassName);
};

ECSideUtil.getGridObj=function(formid){
	return document.getElementById(formid);
};

ECSideUtil.changeRowsDisplayed=function(formid,selectObjValue){
	var form=document.getElementById(formid);
	form["pageSize"].value=selectObjValue;
	form["pageNumber"].value='1';
	form.action=$("#listAction").attr("value");
	form.submit();
};

/**
 	查询数据
 **/ 
ECSideUtil.search=function(){
	loading();
	$("#pageNumber").attr("value",1);
	var list_action = $("#listAction").attr("value");
	$('#listForm').attr("action",list_action);
	$('#listForm').attr("target","_self");
	return true;
	//$('#listForm').submit();
}
 	
 /**
 	添加数据
 **/ 
ECSideUtil.add=function(){
	loading();
  	var add_action = $("#addAction").attr("value");
	window.location.href = add_action;
}
  
 /**
 	修改数据
 **/ 
ECSideUtil.edit=function(){
	var id;
	var checkNum = 0;
	$("input[name='ids']").each(function(){ //由于复选框一般选中的是多个,所以可以循环输出   
		if($(this).attr("checked")){
			checkNum++;
			id =  $(this).val();
		};
	});   
	if(checkNum!=1){
		top.CC.Util.alert("请选择一项进行修改!");
	}else{
		loading();
		var edit_action = $("#editAction").attr("value");
		window.location.href = edit_action+"?id="+id;
	}
}
  
/**
 	删除数据
 **/ 
ECSideUtil.del=function(msg){
  	var checkNum = 0;
	$("input[name='ids']").each(function(){ //由于复选框一般选中的是多个,所以可以循环输出   
		if($(this).attr("checked")){
			checkNum++;
		};
	});   
	if(checkNum==0){
		top.CC.Util.alert("请至少选择一项!");
	}else{
		if(msg==undefined){
			msg = '确定要删除选中的项吗？';
		}else{
		}
		top.CC.Util.alert('<font color=red>'+msg+'</font>','删除确认',(function(){
			if(this.returnCode == "yes"){
				loading();
				var del_action = $("#delAction").attr("value");
				$('#listForm').attr("action",del_action);
				$('#listForm').submit();
			}else{
			}
	    }),'yes|no');
	}
}

/**
 	提交表单
**/ 
ECSideUtil.submit=function(submitForm){
	$('#'+submitForm+'').submit();
}

//导出excel
ECSideUtil.expExcel=function(){
	var exp_action = $("#expAction").attr("value");
	$('#listForm').attr("action",exp_action);
	$('#listForm').submit();
}

/**
  创建分页工具条
**/
ECSideUtil.createPagination=function(totalRecord,perPage,currentPage){
  	var toolbar = '';
	var totalRecord = totalRecord > 0 ? totalRecord : 0;//总记录数
	
	var strHtml = '<table id="ec_toolbarTable" class="toolbarTable"  border="0"  cellpadding="0"  cellspacing="0" >';
	strHtml += '<tr>';
	
	if(totalRecord == 0){
		strHtml = '<table id="ec_toolbarTable" border="0"  cellpadding="0"  cellspacing="0" width="100%" >';
		strHtml += '<tr>';
		strHtml += '<td style="text-align:center;"><span style="line-height:2;">没有检索到任何记录！</span></td>';	
		strHtml += '</tr>';
		strHtml += '</table>';
		return strHtml;
	}
	
	var perPage =  perPage > 0 ?perPage : 10;//每页显示记录数
	//私有变量
	var totalPage = Math.ceil(totalRecord/perPage);																//总页数
	var currentPage = currentPage;//当前页码
	var endPage = totalPage;//最后一页
	var startRecord = (currentPage*1-1)*perPage+1;																							//每页起始记录
	var endRecord = totalRecord-currentPage*perPage<0?totalRecord:currentPage*perPage;	 																							//每页结束记录
	
	//生成html代码
	prevPage = currentPage*1 - 1, nextPage = currentPage*1 + 1;
	
	//strHtml += '<span class="number">';
	//strHtml += '<td class="pageNavigationTool"  nowrap="nowrap" >';
	strHtml += '<td nowrap="nowrap"  class="statusTool" style="10%">';
	if (prevPage < 1) {
	strHtml += '&nbsp;<span title="First Page">首页</span>&nbsp;';
	strHtml += '<span title="Prev Page">上一页</span>&nbsp;';			
	} else {
		strHtml += '&nbsp;<span title="First Page"><a href="javascript:ECSideUtil.togglePage(1);">首页</a></span>&nbsp;';
		strHtml += '<span title="Prev Page"><a href="javascript:ECSideUtil.togglePage(' + prevPage + ');">上一页</a></span>&nbsp;';
	}
	if (currentPage != 1) strHtml += '<span title="Page 1"><a href="javascript:ECSideUtil.togglePage(1);">[1]</a></span>';
	if (currentPage >= 5) strHtml += '<span>...</span>';
	
	if (totalPage > (currentPage*1 + 2)*1) {
		 endPage = currentPage*1 + 2;
	} else {
		 endPage = totalPage;
	}
	for (var i = currentPage*1 - 2; i <= endPage; i++) {
		if (i > 0) {
			if (i == currentPage) {
				strHtml += '<span title="Page ' + i + '" style="color:red">[' + i + ']</span>';
			} else {
				if (i != 1 && i != totalPage) {
					strHtml += '<span title="Page ' + i + '"><a href="javascript:ECSideUtil.togglePage(' + i + ');">[' + i + ']</a></span>';
				}
			}
		}
	}
	if (currentPage*1 + 3 < totalPage) strHtml += '<span>...</span>';
	if (currentPage != totalPage) strHtml += '<span title="Page ' + totalPage + '"><a href="javascript:ECSideUtil.togglePage(' + totalPage + ');">[' + totalPage + ']</a></span>';
	
	if (nextPage > totalPage) {
		strHtml += '&nbsp;<span title="Next Page">下一页</span>';
		strHtml += '&nbsp;<span title="Last Page">尾页</span>&nbsp;';
	}else{
		strHtml += '&nbsp;<span title="Next Page"><a href="javascript:ECSideUtil.togglePage(' + nextPage + ');">下一页</a></span>';
		strHtml += '&nbsp;<span title="Last Page"><a href="javascript:ECSideUtil.togglePage(' + totalPage + ');">尾页</a></span>&nbsp;';
			
	}
	strHtml += ' 每页';
	strHtml += '</td>';	
	
	//添加工具条
	strHtml += '<td id="pagesize_span" style="text-align:left;" ></td>';	
	strHtml += '<td class="statusTool" style="text-align:left;" ><select onchange="ECSideUtil.changeRowsDisplayed(\'listForm\',this.value);loading();">';
	var str10="";str15="";str20="";str25="";str30="";str50="";
	switch(perPage){
	   case "10":
			str10 = "selected";
			break;
	   case "15":
			str15 = "selected";
			break;
        case "20":
			str20 = "selected";
			break;
        case "25":
	        str25 = "selected";
	        break;
         case "30":
	        str30 = "selected";
	        break;
         case "50":
	        str50 = "selected";
	        break;
	}

	strHtml += '<option value="10" '+str10+'>10</option>';
	strHtml += '<option value="15" '+str15+'>15</option>';
	strHtml += '<option value="20" '+str20+'>20</option>';
	strHtml += '<option value="25" '+str25+'>25</option>';
	strHtml += '<option value="30" '+str30+'>30</option>';
	strHtml += '<option value="50" '+str50+'>50</option>';
	strHtml += '</select>条</td>';
	strHtml += '<td class="separatorTool"></td>';
	strHtml += '<td >&nbsp;</td>';
	strHtml += '<td nowrap="nowrap" class="statusTool" style="text-align:right;width:75%"><nobr>共&nbsp;<span class="pgTotalRecord">' + totalRecord + '</span>&nbsp;条记录</nobr></td>';	
	strHtml += '</tr>';
	strHtml += '</table>';
	return strHtml;
}

//查看单个数据
ECSideUtil.view=function(){
	var id;
	var checkNum = 0;
	$("input[name='ids']").each(function(){ //由于复选框一般选中的是多个,所以可以循环输出   
		if($(this).attr("checked")){
			checkNum++;
			id =  $(this).val();
		};
	});   
	if(checkNum!=1){
		top.CC.Util.alert("请选择一项查看!");
	}else{
		loading();
		var view_action = $("#viewAction").attr("value");
		window.location.href = view_action+"?id="+id;
	}
}

//分页,转向页面pageNum
ECSideUtil.doJump=function(pageNumber,pageSize,sortColumn,sortOrder){
	$("#pageNumber").attr("value",pageNumber);
	$('#pageSize').attr("value",pageSize);
	$('#sortColumn').attr("value",sortColumn);
	$('#sortOrder').attr("value",sortOrder);
	var list_action = $("#listAction").attr("value");
	$('#listForm').attr("action",list_action);
	//alert('pageNumber:'+pageNumber+" pageSize:"+pageSize+" sortColumn:"+sortColumn+" sortOrder:"+sortOrder);
	loading();
	$('#listForm').submit();
}

	
	//跳转到某页
ECSideUtil.togglePage=function(pageNumber){
	ECSideUtil.doJump(pageNumber);
}
	//每页显示记录数
ECSideUtil.togglePageSize=function(pageSize){
	ECSideUtil.doJump(1,pageSize);
}

/*============ UTILS ============*/
function ECSideUtil_addEvent( obj, type, fn ) {  
  if ( obj.attachEvent ) {  
    obj['e'+type+fn] = fn;  
    obj[type+fn] = function(){obj['e'+type+fn]( window.event );};
    obj.attachEvent( 'on'+type, obj[type+fn] );  
  }else if(obj.addEventListener){
    obj.addEventListener( type, fn, false );  
  }
}
function ECSideUtil_stopEvent(e) {  
	if (e.stopPropagation){
		e.stopPropagation();
		e.preventDefault();
	} else {
		e.returnValue = false;
		e.cancelBubble = true;
	}
	return false;
}

function ECSideUtil_removeEvent( obj, type, fn ) {  
  if ( obj.detachEvent ) {  
    obj.detachEvent( 'on'+type, obj[type+fn] );  
    obj[type+fn] = null;  
    obj['e'+type+fn] = null;
  }else if(obj.removeEventListener){
    obj.removeEventListener( type, fn, false ); 
  }
}

ECSideUtil.trimString=function(str, wh){
		if(!str.replace){ return str; }
		if(!str.length){ return str; }
		var re = (wh > 0) ? (/^\s+/) : (wh < 0) ? (/\s+$/) : (/^\s+|\s+$/g);
		return str.replace(re, "");
};


ECSideUtil.getPosTop=function(elm) {
	var top = elm.offsetTop;
	while((elm = elm.offsetParent) != null)	{
		top += (elm.offsetTop-elm.scrollTop);
	}
	return top;
}

ECSideUtil.getPosLeft=function(elm) {
	var left = elm.offsetLeft;
	while((elm = elm.offsetParent) != null)	{
		left += (elm.offsetLeft-elm.scrollLeft);
	}
	return left;
};

ECSideUtil.getPosRight=function(elm){
	return ECSideUtil.getPosLeft(elm)+elm.offsetWidth;
};
ECSideUtil.getPosBottom=function(elm){
	return ECSideUtil.getPosTop(elm)+elm.offsetHeight;
};

ECSideUtil.replaceAll=function(exstr,ov,value){
	var gc=ECSideUtil.escapeRegExp(ov);
	if (gc==null || gc==''){
		return exstr;
	}
	var reReplaceGene="/"+gc+"/gm";
	var r=null;
	var cmd="r=exstr.replace("+reReplaceGene+","+ECSideUtil.escapeString(value)+")";
	eval(cmd);
	return r;
};

ECSideUtil.escapeRegExp=function(str) {
	return !str?''+str:(''+str).replace(/\\/gm, "\\\\").replace(/([\f\b\n\t\r[\^$|?*+(){}])/gm, "\\$1");
};

ECSideUtil.escapeString=function(str){ 
	return !str?''+str:('"' + (''+str).replace(/(["\\])/g, '\\$1') + '"'
		).replace(/[\f]/g, "\\f"
		).replace(/[\b]/g, "\\b"
		).replace(/[\n]/g, "\\n"
		).replace(/[\t]/g, "\\t"
		).replace(/[\r]/g, "\\r");
};


ECSideUtil.hasClass=function(object, className) {
	if (!object.className) { return false;}
	return (object.className.search('(^|\\s)' + className + '(\\s|$)') != -1);
};

ECSideUtil.removeClass=function(object,className) {
	if (!object) {return;}
	object.className = object.className.replace(new RegExp('(^|\\s)'+className+'(\\s|$)'), ' ');
};

ECSideUtil.addClass=function(object,className) {
	if (!object || ECSideUtil.hasClass(object, className)){return;}
	if (object.className) {
		object.className += ' '+className;
	} else {
		object.className = className;
	}
};


ECSideUtil.parseIntOrZero=function(num){
	return ECSideUtil.parseInt(num,0);
};
ECSideUtil.parseIntOrOne=function(num){
	return ECSideUtil.parseInt(num,1);
};
ECSideUtil.parseInt=function(num,defaultNum){
	var t=parseInt(num);
	return isNaN(t)?defaultNum:t;
};

ECSideUtil.isCollection=function(obj){
	return obj && typeof(obj) != 'string' && typeof(obj.length) == 'number';

};

ECSideUtil.appendMap=function(destination,source){
  for (var property in source) {
	  if (property in destination) {
		  if (destination[property].constructor != Array) destination[property] = [destination[property]];
          destination[property].push(source[property]);
	  }else{
		destination[property] = source[property];
	  }
  }
};
/*============ UTILS ============*/