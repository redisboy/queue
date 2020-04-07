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
    var ou1 = $('#date_u1');
    var ou2 = $('#date_u2');
	var ou3 = $('#date_u3');
	var ou4 = $('#date_u4');
	var ou5 = $('#date_u5');
	var ou6 = $('#date_u6');
	var ou7 = $('#date_u7');
	var ou8 = $('#date_u8');
	var ou9 = $('#date_u9');
	var ou10 = $('#date_u10');
	var ou11 = $('#date_u11');
	var ou12 = $('#date_u12');
    var ou13 = $('#date_u13');
	var ou14 = $('#date_u14');
	var ou15 = $('#date_u15');
	var ou16 = $('#date_u16');
	var ou17 = $('#date_u17');
	var ou18 = $('#date_u18');
	var ou19 = $('#date_u19');
	var ou20 = $('#date_u20');
	var ou21 = $('#date_u21');
	var ou22 = $('#date_u22');
	var ou23 = $('#date_u23');
	var ou24 = $('#date_u24');
	var ou25 = $('#date_u25');
	var ou26 = $('#date_u26');
	var ou27 = $('#date_u27');
    if(data.winNum==1){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>业务受理窗</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou1.find("li:first").remove();
	    ou1.append(newLi);
	   
    }
    if(data.winNum==2){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>业务受理窗</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou2.find("li:first").remove();
	    ou2.append(newLi);
	   
    }
    if(data.winNum==3){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>互联网注册</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou3.find("li:first").remove();
	    ou3.append(newLi);
	   
    }
    if(data.winNum==4){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>互联网注册</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou4.find("li:first").remove();
	    ou4.append(newLi);
	   
    }
    if(data.winNum==5){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>证件制作窗</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou5.find("li:first").remove();
	    ou5.append(newLi);
	   
    }
    if(data.winNum==6){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>证件制作窗</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou6.find("li:first").remove();
	    ou6.append(newLi);
	    
    }
    if(data.winNum==7){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>业务受理窗</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou7.find("li:first").remove();
	    ou7.append(newLi);
	   
    }
    if(data.winNum==8){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>业务受理窗</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou8.find("li:first").remove();
	    ou8.append(newLi);
	  
    }
    if(data.winNum==9){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>业务受理窗</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou9.find("li:first").remove();
	    ou9.append(newLi);
	    
    }
    if(data.winNum==10){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>业务受理窗</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou10.find("li:first").remove();
	    ou10.append(newLi);
	   
    }
    if(data.winNum==11){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>业务受理窗</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou11.find("li:first").remove();
	    ou11.append(newLi);
	    
    }
    if(data.winNum==12){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>绿色通道</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou12.find("li:first").remove();
	    ou12.append(newLi);
	   
    }
    if(data.winNum==13){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>业务受理窗</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou13.find("li:first").remove();
	    ou13.append(newLi);
	   
    }
    if(data.winNum==14){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>业务受理窗</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou14.find("li:first").remove();
	    ou14.append(newLi);
	   
    }
    if(data.winNum==15){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>发证窗</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou15.find("li:first").remove();
	    ou15.append(newLi);
	   
    }
    if(data.winNum==16){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>收费窗口</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou16.find("li:first").remove();
	    ou16.append(newLi);
	   
    }
    if(data.winNum==17){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>暂停服务</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou17.find("li:first").remove();
	    ou17.append(newLi);
	  
    }
    if(data.winNum==18){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>暂停服务</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou18.find("li:first").remove();
	    ou18.append(newLi);
	  
    }
    if(data.winNum==19){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>业务受理窗</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou19.find("li:first").remove();
	    ou19.append(newLi);
	    
    }
    if(data.winNum==20){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>业务受理窗</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou20.find("li:first").remove();
	    ou20.append(newLi);
	  
    }
    if(data.winNum==21){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>业务受理窗</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou21.find("li:first").remove();
	    ou21.append(newLi);
	    
    }
    if(data.winNum==22){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>业务受理窗</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou22.find("li:first").remove();
	    ou22.append(newLi);
	    
    }
    if(data.winNum==23){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>业务受理窗</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou23.find("li:first").remove();
	    ou23.append(newLi);
	   
    }
    if(data.winNum==24){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>业务受理窗</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou24.find("li:first").remove();
	    ou24.append(newLi);
	    
    }
    if(data.winNum==25){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>业务受理窗</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou25.find("li:first").remove();
	    ou25.append(newLi);
	   
    }
    if(data.winNum==26){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>业务受理窗</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou26.find("li:first").remove();
	    ou26.append(newLi);
	   
    }
    if(data.winNum==27){
	    var liId = data.serialNum + "" + data.winNum;
	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>业务受理窗</p>请"+data.serialNum.toLocaleUpperCase()+"号办理业务</li>";
	    ou27.find("li:first").remove();
	    ou27.append(newLi);
	    
    }
    
//    if(num%3==2){
//	    var liId1 = data.serialNum + "" + data.winNum;
//	    var newLi1 = "<li id='" + liId1 + "' style='width:100%;list-style:none;border-bottom: none;line-height:31px;height:30px;font-size: 30px;'>"+data.serialNum.toLocaleUpperCase()+"号请到"+data.winNum+"号窗口</li>";
//	    if (6 <= ou1.find("li").length) {
//	        ou1.find("li:first").remove();
//	    }
//	    ou1.append(newLi1);
//	    setTimeout(function() {
//        $("#"+liId1).remove();
//    }, 1800000);
//    }
//    if(num%3==0){
//	    var liId2 = data.serialNum + "" + data.winNum;
//	    var newLi2 = "<li id='" + liId2 + "' style='width:100%;list-style:none;border-bottom: none;line-height:31px;height:30px;font-size: 30px;'>"+data.serialNum.toLocaleUpperCase()+"号请到"+data.winNum+"号窗口</li>";
//	    if (6 <= ou3.find("li").length) {
//	        ou3.find("li:first").remove();
//	    }
//	    ou3.append(newLi2);
//	    setTimeout(function() {
//        $("#"+liId2).remove();
//    }, 1800000);
//    }
//    
//    if(num%3==1){
//	    var liId = data.serialNum + "" + data.winNum;
//	    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:31px;height:30px;font-size: 30px;'>"+data.serialNum.toLocaleUpperCase()+"号请到"+data.winNum+"号窗口</li>";
//	    if (6 <= ou12.find("li").length) {
//	        ou12.find("li:first").remove();
//	    }
//	    ou12.append(newLi);
//	    setTimeout(function() {
//        $("#"+liId).remove();
//    }, 1800000);
//    }
//    if(num%3==2){
//	    var liId1 = data.serialNum + "" + data.winNum;
//	    var newLi1 = "<li id='" + liId1 + "' style='width:100%;list-style:none;border-bottom: none;line-height:31px;height:30px;font-size: 30px;'>"+data.serialNum.toLocaleUpperCase()+"号请到"+data.winNum+"号窗口</li>";
//	    if (6 <= ou11.find("li").length) {
//	        ou11.find("li:first").remove();
//	    }
//	    ou11.append(newLi1);
//	    setTimeout(function() {
//        $("#"+liId1).remove();
//    }, 1800000);
//    }
//    if(num%3==0){
//	    var liId2 = data.serialNum + "" + data.winNum;
//	    var newLi2 = "<li id='" + liId2 + "' style='width:100%;list-style:none;border-bottom: none;line-height:31px;height:30px;font-size: 30px;'>"+data.serialNum.toLocaleUpperCase()+"号请到"+data.winNum+"号窗口</li>";
//	    if (6 <= ou13.find("li").length) {
//	        ou13.find("li:first").remove();
//	    }
//	    ou13.append(newLi2);
//	    setTimeout(function() {
//        $("#"+liId2).remove();
//    }, 1800000);
//    }
//    
//	if(data.winNum == '01'){
//		yg01 = yg01+1;
//		$("#yg01").html("01:"+yg01);
//		$("#yg101").html("01:"+yg01);
//	}else if(data.winNum == '02'){
//		yg02 = yg02+1;
//		$("#yg02").html("02:"+yg02);
//		$("#yg102").html("02:"+yg02);
//	}else if(data.winNum == '03'){
//		yg03 = yg03+1;
//		$("#yg03").html("03:"+yg03);
//		$("#yg103").html("03:"+yg03);
//	}else if(data.winNum == '04'){
//		yg04 = yg04+1;
//		$("#yg04").html("04:"+yg04);
//		$("#yg104").html("04:"+yg04);
//	}else if(data.winNum == '05'){
//		yg05 = yg05+1;
//		$("#yg05").html("05:"+yg05);
//		$("#yg105").html("05:"+yg05);
//	}else if(data.winNum == '06'){
//		yg06 = yg06+1;
//		$("#yg06").html("06:"+yg06);
//		$("#yg106").html("06:"+yg06);
//	}else if(data.winNum == '07'){
//		yg07 = yg07+1;
//		$("#yg07").html("07:"+yg07);
//		$("#yg107").html("07:"+yg07);
//	}else if(data.winNum == '08'){
//		yg08 = yg08+1;
//		$("#yg08").html("08:"+yg08);
//		$("#yg108").html("08:"+yg08);
//	}else if(data.winNum == '09'){
//		yg09 = yg09+1;
//		$("#yg09").html("09:"+yg09);
//		$("#yg109").html("09:"+yg09);
//	}else if(data.winNum == '10'){
//		yg10 = yg10+1;
//		$("#yg10").html("10:"+yg10);
//		$("#yg110").html("10:"+yg10);
//	}else if(data.winNum == '11'){
//		yg11 = yg11+1;
//		$("#yg11").html("11:"+yg11);
//		$("#yg111").html("11:"+yg11);
//	}else if(data.winNum == '12'){
//		yg12 = yg12+1;
//		$("#yg12").html("12:"+yg12);
//		$("#yg112").html("12:"+yg12);
//	}else if(data.winNum == '13'){
//		yg13 = yg13+1;
//		$("#yg13").html("13:"+yg13);
//		$("#yg113").html("13:"+yg13);
//	}else if(data.winNum == '14'){
//		yg14 = yg14+1;
//		$("#yg14").html("14:"+yg14);
//		$("#yg114").html("14:"+yg14);
//	}else if(data.winNum == '15'){
//		yg15 = yg15+1;
//		$("#yg15").html("15:"+yg15);
//		$("#yg115").html("15:"+yg15);
//	}else if(data.winNum == '16'){
//		yg16 = yg16+1;
//		$("#yg16").html("16:"+yg16);
//		$("#yg116").html("16:"+yg16);
//	}
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

function PauseOrRecover(data){
var newLi="<li style='width:100%;list-style:none;border-bottom: none;line-height:20px;height:30px;font-size: 20px;'>"+"<p style='color:yellow;'>暂停服务</p></li>";
	num++;
if(data.winNum < 10){
	data.winNum = "0"+data.winNum;
}
    var ou1 = $('#date_u1');
    var ou2 = $('#date_u2');
	var ou3 = $('#date_u3');
	var ou4 = $('#date_u4');
	var ou5 = $('#date_u5');
	var ou6 = $('#date_u6');
	var ou7 = $('#date_u7');
	var ou8 = $('#date_u8');
	var ou9 = $('#date_u9');
	var ou10 = $('#date_u10');
	var ou11 = $('#date_u11');
	var ou12 = $('#date_u12');
    var ou13 = $('#date_u13');
	var ou14 = $('#date_u14');
	var ou15 = $('#date_u15');
	var ou16 = $('#date_u16');
	var ou17 = $('#date_u17');
	var ou18 = $('#date_u18');
	var ou19 = $('#date_u19');
	var ou20 = $('#date_u20');
	var ou21 = $('#date_u21');
	var ou22 = $('#date_u22');
	var ou23 = $('#date_u23');
	var ou24 = $('#date_u24');
	var ou25 = $('#date_u25');
	var ou26 = $('#date_u26');
	var ou27 = $('#date_u27');
    if(data.winNum==1){
	    ou1.find("li:first").remove();
	    ou1.append(newLi);
	   
    }
    if(data.winNum==2){
	    ou2.find("li:first").remove();
	    ou2.append(newLi);
	   
    }
    if(data.winNum==3){
	    ou3.find("li:first").remove();
	    ou3.append(newLi);
	   
    }
    if(data.winNum==4){
	    ou4.find("li:first").remove();
	    ou4.append(newLi);
	   
    }
    if(data.winNum==5){
	    ou5.find("li:first").remove();
	    ou5.append(newLi);
	   
    }
    if(data.winNum==6){
	    ou6.find("li:first").remove();
	    ou6.append(newLi);
	    
    }
    if(data.winNum==7){
	    ou7.find("li:first").remove();
	    ou7.append(newLi);
	   
    }
    if(data.winNum==8){
	    ou8.find("li:first").remove();
	    ou8.append(newLi);
	  
    }
    if(data.winNum==9){
	    ou9.find("li:first").remove();
	    ou9.append(newLi);
	    
    }
    if(data.winNum==10){
	    ou10.find("li:first").remove();
	    ou10.append(newLi);
	   
    }
    if(data.winNum==11){
	    ou11.find("li:first").remove();
	    ou11.append(newLi);
	    
    }
    if(data.winNum==12){
	    ou12.find("li:first").remove();
	    ou12.append(newLi);
	   
    }
    if(data.winNum==13){
	    ou13.find("li:first").remove();
	    ou13.append(newLi);
	   
    }
    if(data.winNum==14){
	    ou14.find("li:first").remove();
	    ou14.append(newLi);
	   
    }
    if(data.winNum==15){
	    ou15.find("li:first").remove();
	    ou15.append(newLi);
	   
    }
    if(data.winNum==16){
	    ou16.find("li:first").remove();
	    ou16.append(newLi);
	   
    }
    if(data.winNum==17){
	    ou17.find("li:first").remove();
	    ou17.append(newLi);
	  
    }
    if(data.winNum==18){
	    ou18.find("li:first").remove();
	    ou18.append(newLi);
	  
    }
    if(data.winNum==19){
	    ou19.find("li:first").remove();
	    ou19.append(newLi);
	    
    }
    if(data.winNum==20){
	    ou20.find("li:first").remove();
	    ou20.append(newLi);
	  
    }
    if(data.winNum==21){
	    ou21.find("li:first").remove();
	    ou21.append(newLi);
	    
    }
    if(data.winNum==22){
	    ou22.find("li:first").remove();
	    ou22.append(newLi);
	    
    }
    if(data.winNum==23){
	    ou23.find("li:first").remove();
	    ou23.append(newLi);
	   
    }
    if(data.winNum==24){
	    ou24.find("li:first").remove();
	    ou24.append(newLi);
	    
    }
    if(data.winNum==25){
	    ou25.find("li:first").remove();
	    ou25.append(newLi);
	   
    }
    if(data.winNum==26){
	    ou26.find("li:first").remove();
	    ou26.append(newLi);
	   
    }
    if(data.winNum==27){
	    ou27.find("li:first").remove();
	    ou27.append(newLi);
	    
    }
    
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

