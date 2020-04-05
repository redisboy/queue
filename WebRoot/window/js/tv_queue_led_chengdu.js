document.oncontextmenu = function() { return false; } //禁止右键功能,单击右键将无任何反应
document.onselectstart = function() { return false; } //禁止选择,也就是无法复制
window.onhelp = function() { return false; } //屏蔽F1

dwr.engine.setActiveReverseAjax(true);
dwr.engine.setNotifyServerOnPageUnload(true);
var a=1;
var id = "";
var timer = "";
function showMessage(data) {
if(data.winNum < 10){
	data.winNum = "0"+data.winNum;
}
//    var ou2 = $('#date_u2');
//    var liId = data.serialNum + "" + data.winNum;
//    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:19px;height:18px;font-size: 18px;'>"+data.serialNum.toLocaleUpperCase()+"号请到"+data.winNum+"号窗口</li>";
//    if (14 <= ou2.find("li").length) {
//        ou2.find("li:first").remove();
//    }
//    ou2.append(newLi);
    
    var ou1 = $('#date_u1');
    var liId1 = data.serialNum + "" + data.winNum;
    var newLi1 = "<li id='" + liId1 + "' style='width:100%;list-style:none;border-bottom: none;line-height:19px;height:18px;font-size: 18px;'>"+data.serialNum.toLocaleUpperCase()+"号请到"+data.winNum+"号窗口</li>";
    if (14 <= ou1.find("li").length) {
        ou1.find("li:first").remove();
    }
    ou1.append(newLi1);
}

function tsLzxx(data) {
	var ou2 = $('#date_u2');
	    var liId = data.xxbh;
	if('0' == data.bj){
	    var newLi1 = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:19px;height:18px;font-size: 18px;'>"+data.nr+"</li>";
	    ou2.append(newLi1);
	}else if('1' == data.bj){
		$("#"+liId).remove();
	}
	if(14 < ou2.find("li").length && a==1){
		dingshi();
		a=0;
	}
	if(14>=ou2.find("li").length && a==0){
		clearInterval(timer);
		a=1;
	}
}

function gun(){
	id = $("#date_u2 li:first").attr("id");
		for(var i=0;i<$('#date_u2').find("li").length-1;i++){
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

function showZhxxData(data){
	window.location.href='http://192.168.198.1:8080/queue/redirect.jsp?tv=led2';
//	var params = 'flag='+data.flag;
//	$.ajax({type:"POST", cache:false, data:params,
//		url:"/queue/number/ShowZHP.action",
//		success:function (req) {
//			if (req != null || req != "") {
//				$("#bjqnr").html(req);
//			}
//		}
//	});
}
