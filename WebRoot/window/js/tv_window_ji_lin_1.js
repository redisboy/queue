﻿document.oncontextmenu = function() { return false; } //禁止右键功能,单击右键将无任何反应
document.onselectstart = function() { return false; } //禁止选择,也就是无法复制
window.onhelp = function() { return false; } //屏蔽F1

dwr.engine.setActiveReverseAjax(true);
dwr.engine.setNotifyServerOnPageUnload(true);
function showMessage(data) {
	if(data.winNum != '31' && data.winNum != '32'){
if(data.winNum < 10){
	data.winNum = "0"+data.winNum;
}
    var oul = $('#date_u1');
    var liId = data.serialNum + "" + data.winNum;
    var newLi = "<li id='" + liId + "' style='font-size: 103px;width: 100%;text-align:justify;text-justify:distribute-all-lines;text-align-last:justify'>"+data.serialNum.toLocaleUpperCase()+"号请到"+data.winNum+"号窗口</li>";
    if (7 <= oul.find("li").length) {
        oul.find("li:first").remove();
    }
    oul.append(newLi);
    }
    setTimeout(function() {
        $("#"+liId).remove();
    }, 300000);
    
    var oul = $('#numInfo');
    var liId = data.serialNum + "" + data.winNum;
    var newLi = "<li id='" + liId + "' style='font-size:90px;width: 500px; height: 110px; line-height: 110px;'>窗口<font color='red'>"+data.winNum+"</font>:<font color='red'>"+data.serialNum+"</font></li>";
    if (7 <= oul.find("li").length) {
        oul.find("li:first").remove();
    }
    oul.append(newLi);
    setTimeout(function() {
        $("#"+liId).remove();
    }, 300000);
}