﻿document.oncontextmenu = function() { return false; } //禁止右键功能,单击右键将无任何反应
document.onselectstart = function() { return false; } //禁止选择,也就是无法复制
window.onhelp = function() { return false; } //屏蔽F1

dwr.engine.setActiveReverseAjax(true);
dwr.engine.setNotifyServerOnPageUnload(true);
function showMessage(data) {
if(data.winNum < 10){
	data.winNum = "0"+data.winNum;
}
//    var ou2 = $('#date_u2');
//    var liId = data.serialNum + "" + data.winNum;
//    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:25px;height:22px;font-size: 25px;'>"+data.serialNum.toLocaleUpperCase()+"号请到"+data.winNum+"号窗口</li>";
//    if (16 <= ou2.find("li").length) {
//        ou2.find("li:first").remove();
//    }
//    ou2.append(newLi);
    
    var ou1 = $('#date_u1');
    var liId1 = data.serialNum + "" + data.winNum;
    var newLi1 = "<li id='" + liId1 + "'style='font-size: 65px;width: 100%;text-align:justify;text-justify:distribute-all-lines;text-align-last:justify'>"+data.serialNum.toLocaleUpperCase()+"号请到"+data.winNum+"号窗口办理</li>";
    if (9 <= ou1.find("li").length) {
        ou1.find("li:first").remove();
    }
    ou1.append(newLi1);
}

function showLzxx(data) {
	var xm = data.xm;
	if(xm.length>6){
		xm = xm.substr(0,6);
	}
//    var ou1 = $('#date_u3');
    var ou2 = $('#date_u2');
//	if(lzxxNum%2==1){
//	    var liId1 = data.xm + "" + data.barid;
//	    var newLi1 = "<li id='" + liId1 + "' style='width:100%;list-style:none;border-bottom: none;line-height:19px;height:18px;font-size: 18px;'>"+xm+"请到"+data.barid+"号窗口领证</li>";
//	    if (13 <= ou1.find("li").length) {
//	        ou1.find("li:first").remove();
//	    }
//	    ou1.append(newLi1);
//	}
//	if(lzxxNum%2==0){
		var liId2 = data.xm + "" + data.barid;
		var newLi2 = "<li id='" + liId2 + "'style='font-size: 65px;width: 100%;text-align:justify;text-justify:distribute-all-lines;text-align-last:justify'>"+xm+"请到"+data.barid+"号窗口</li>";
	    if (9 <= ou2.find("li").length) {
	        ou2.find("li:first").remove();
	    }
	    ou2.append(newLi2);
	    setTimeout(function() {
        $("#"+liId2).remove();
    }, 1800000);
//	}
}



function showLzxxData(data) {
	var ou1 = $('#lzxx');
    var liId = data.serialNum + "" + data.winNum;
    var message = "<li style='font-size: 65px;width: 100%;text-align:justify;text-justify:distribute-all-lines;text-align-last:justify'>"+data.jsz+":"+data.xsz+":"+data.djzs+"</li>";
    if (9 <= oul.find("li").length) {
        oul.find("li:first").remove();
    }
    oul.append(message);
    setTimeout(function() {
        $("#"+liId).remove();
    }, 600000);
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
function GuoHaoTuiSong(data){
	if(data.winNum < 10){
		data.winNum = "0"+data.winNum;
	}
	var liId = data.serialNum + "" + data.winNum;
	//var lis = "<li id='" + liId + "' style='font-size:20px;width:100%;text-align:center'><font color='red'>"+data.serialNum+"</font>号请到<font color='red'>"+data.winNum+"</font>窗口(已过号)</li>";
	//$("#"+liId).html(lis);
	$("#"+liId).remove();
}
function GuaQiTuiSong(data){
	if(data.winNum < 10){
		data.winNum = "0"+data.winNum;
	}
	var liId = data.serialNum + "" + data.winNum;
	$("#"+liId).remove();
}
function GuaQiHuiFuTuiSong(data){
	if(data.winNum < 10){
		data.winNum = "0"+data.winNum;
	}
	var oul = $('#date_u1');
	var liId = data.serialNum + "" + data.winNum;
	var newLi = "<li id='" + liId + "' style='font-size:20px;width:100%;text-align:center'><font color='red'>"+data.serialNum+"</font>号请到<font color='red'>"+data.winNum+"</font>窗口</li>";
	if (13 <= oul.find("li").length) {
        oul.find("li:first").remove();
    }
    oul.append(newLi);
}