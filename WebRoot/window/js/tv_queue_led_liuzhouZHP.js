document.oncontextmenu = function() { return false; } //禁止右键功能,单击右键将无任何反应
document.onselectstart = function() { return false; } //禁止选择,也就是无法复制
window.onhelp = function() { return false; } //屏蔽F1

dwr.engine.setActiveReverseAjax(true);
dwr.engine.setNotifyServerOnPageUnload(true);
var a=1;
var id = "";
var timer = "";
function showLzxx(data) {
	var ou2 = $('#date_u1');
	var liId = data.lsh;
	
	var xm=data.xm;
	if(xm.length>5){
		xm=xm.substring(0,5);
	}
	if('0' == data.bj){
	    var newLi1 = "<li id='" + liId + "' style='height:30px;font-size: 28px;width: 33%;text-align:justify;text-justify:distribute-all-lines;text-align-last:justify'>"+xm+"请到"+data.barid+"号窗口领证&nbsp;&nbsp;"+"</li>";
	    ou2.append(newLi1);
	}else if('1' == data.bj){
		$("#"+liId).remove();
	}
	if(12 < ou2.find("li").length && a==1){
		dingshi();
		a=0;
	}
	if(12>=ou2.find("li").length && a==0){
		clearInterval(timer);
		a=1;
	}
}

function gun(){
	id = $("#date_u1 li:first").attr("id");
		for(var i=0;i<$('#date_u1').find("li").length-1;i++){
			if($("#"+id).next()){
				$("#"+id).next().after($("#"+id));
			}
		}
}
function dingshi(){
	timer = window.setInterval("gun()",500);
}


function queding(){
			$('#data_m2').css("scrollamount","0");
			
		}
function quxiao(){
	$('#data_m2').css("scrollamount","0");
			
}