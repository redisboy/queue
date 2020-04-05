document.oncontextmenu = function() { return false; } //禁止右键功能,单击右键将无任何反应
document.onselectstart = function() { return false; } //禁止选择,也就是无法复制
window.onhelp = function() { return false; } //屏蔽F1

dwr.engine.setActiveReverseAjax(true);
dwr.engine.setNotifyServerOnPageUnload(true);
var lzxxNum = 0;
var num = 0;yg01 = 0;yg02 = 0;yg03 = 0;yg04 = 0;yg05 = 0;yg06 = 0;yg07 = 0;yg08 = 0;yg09 = 0;yg10 = 0;yg11 = 0;yg12 = 0;yg13 = 0;yg14 = 0;yg15 = 0;yg16 = 0;
function showLzxx(data) {
	lzxxNum++;
	var xm = data.xm;
	if(xm.length>6){
		xm = xm.substr(0,6);
	}
//    var ou1 = $('#date_u3');
    var ou2 = $('#date_u4');
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
		var newLi2 = "<li id='" + liId2 + "' style='width:100%;list-style:none;border-bottom: none;line-height:31px;height:30px;font-size: 30px;'>"+xm+"请到"+data.barid+"号窗口</li>";
	    if (8 <= ou2.find("li").length) {
	        ou2.find("li:first").remove();
	    }
	    ou2.append(newLi2);
	    setTimeout(function() {
        $("#"+liId2).remove();
    }, 1800000);
//	}
}
function showMessage(data) {
	num++;
if(data.winNum < 10){
	data.winNum = "0"+data.winNum;
}
    var ou2 = $('#date_u1');
    var ou1 = $('#date_u2');
	var ou3 = $('#date_u3');
	var ou12 = $('#date_u11');
    var ou11 = $('#date_u12');
	var ou13 = $('#date_u13');
    if(num%3==1){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:31px;height:30px;font-size: 30px;'>"+data.serialNum.toLocaleUpperCase()+"号请到"+data.winNum+"号窗口</li>";
	    if (6 <= ou2.find("li").length) {
	        ou2.find("li:first").remove();
	    }
	    ou2.append(newLi);
	    setTimeout(function() {
        $("#"+liId).remove();
    }, 1800000);
    }
    if(num%3==2){
	    var liId1 = data.serialNum + "" + data.winNum;
	    var newLi1 = "<li id='" + liId1 + "' style='width:100%;list-style:none;border-bottom: none;line-height:31px;height:30px;font-size: 30px;'>"+data.serialNum.toLocaleUpperCase()+"号请到"+data.winNum+"号窗口</li>";
	    if (6 <= ou1.find("li").length) {
	        ou1.find("li:first").remove();
	    }
	    ou1.append(newLi1);
	    setTimeout(function() {
        $("#"+liId1).remove();
    }, 1800000);
    }
    if(num%3==0){
	    var liId2 = data.serialNum + "" + data.winNum;
	    var newLi2 = "<li id='" + liId2 + "' style='width:100%;list-style:none;border-bottom: none;line-height:31px;height:30px;font-size: 30px;'>"+data.serialNum.toLocaleUpperCase()+"号请到"+data.winNum+"号窗口</li>";
	    if (6 <= ou3.find("li").length) {
	        ou3.find("li:first").remove();
	    }
	    ou3.append(newLi2);
	    setTimeout(function() {
        $("#"+liId2).remove();
    }, 1800000);
    }
    
    if(num%3==1){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:31px;height:30px;font-size: 30px;'>"+data.serialNum.toLocaleUpperCase()+"号请到"+data.winNum+"号窗口</li>";
	    if (6 <= ou12.find("li").length) {
	        ou12.find("li:first").remove();
	    }
	    ou12.append(newLi);
	    setTimeout(function() {
        $("#"+liId).remove();
    }, 1800000);
    }
    if(num%3==2){
	    var liId1 = data.serialNum + "" + data.winNum;
	    var newLi1 = "<li id='" + liId1 + "' style='width:100%;list-style:none;border-bottom: none;line-height:31px;height:30px;font-size: 30px;'>"+data.serialNum.toLocaleUpperCase()+"号请到"+data.winNum+"号窗口</li>";
	    if (6 <= ou11.find("li").length) {
	        ou11.find("li:first").remove();
	    }
	    ou11.append(newLi1);
	    setTimeout(function() {
        $("#"+liId1).remove();
    }, 1800000);
    }
    if(num%3==0){
	    var liId2 = data.serialNum + "" + data.winNum;
	    var newLi2 = "<li id='" + liId2 + "' style='width:100%;list-style:none;border-bottom: none;line-height:31px;height:30px;font-size: 30px;'>"+data.serialNum.toLocaleUpperCase()+"号请到"+data.winNum+"号窗口</li>";
	    if (6 <= ou13.find("li").length) {
	        ou13.find("li:first").remove();
	    }
	    ou13.append(newLi2);
	    setTimeout(function() {
        $("#"+liId2).remove();
    }, 1800000);
    }
    
	if(data.winNum == '01'){
		yg01 = yg01+1;
		$("#yg01").html("01:"+yg01);
		$("#yg101").html("01:"+yg01);
	}else if(data.winNum == '02'){
		yg02 = yg02+1;
		$("#yg02").html("02:"+yg02);
		$("#yg102").html("02:"+yg02);
	}else if(data.winNum == '03'){
		yg03 = yg03+1;
		$("#yg03").html("03:"+yg03);
		$("#yg103").html("03:"+yg03);
	}else if(data.winNum == '04'){
		yg04 = yg04+1;
		$("#yg04").html("04:"+yg04);
		$("#yg104").html("04:"+yg04);
	}else if(data.winNum == '05'){
		yg05 = yg05+1;
		$("#yg05").html("05:"+yg05);
		$("#yg105").html("05:"+yg05);
	}else if(data.winNum == '06'){
		yg06 = yg06+1;
		$("#yg06").html("06:"+yg06);
		$("#yg106").html("06:"+yg06);
	}else if(data.winNum == '07'){
		yg07 = yg07+1;
		$("#yg07").html("07:"+yg07);
		$("#yg107").html("07:"+yg07);
	}else if(data.winNum == '08'){
		yg08 = yg08+1;
		$("#yg08").html("08:"+yg08);
		$("#yg108").html("08:"+yg08);
	}else if(data.winNum == '09'){
		yg09 = yg09+1;
		$("#yg09").html("09:"+yg09);
		$("#yg109").html("09:"+yg09);
	}else if(data.winNum == '10'){
		yg10 = yg10+1;
		$("#yg10").html("10:"+yg10);
		$("#yg110").html("10:"+yg10);
	}else if(data.winNum == '11'){
		yg11 = yg11+1;
		$("#yg11").html("11:"+yg11);
		$("#yg111").html("11:"+yg11);
	}else if(data.winNum == '12'){
		yg12 = yg12+1;
		$("#yg12").html("12:"+yg12);
		$("#yg112").html("12:"+yg12);
	}else if(data.winNum == '13'){
		yg13 = yg13+1;
		$("#yg13").html("13:"+yg13);
		$("#yg113").html("13:"+yg13);
	}else if(data.winNum == '14'){
		yg14 = yg14+1;
		$("#yg14").html("14:"+yg14);
		$("#yg114").html("14:"+yg14);
	}else if(data.winNum == '15'){
		yg15 = yg15+1;
		$("#yg15").html("15:"+yg15);
		$("#yg115").html("15:"+yg15);
	}else if(data.winNum == '16'){
		yg16 = yg16+1;
		$("#yg16").html("16:"+yg16);
		$("#yg116").html("16:"+yg16);
	}
}

function showLzxxData(data) {
	var ou1 = $('#lzxx');
    var liId = data.serialNum + "" + data.winNum;
    var message = "<li style='width:100%;list-style:none;border-bottom: none;'>"+data.jsz+":"+data.xsz+":"+data.djzs+"</li>";
    if (6 <= oul.find("li").length) {
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
