document.oncontextmenu = function() { return false; } //禁止右键功能,单击右键将无任何反应
document.onselectstart = function() { return false; } //禁止选择,也就是无法复制
window.onhelp = function() { return false; } //屏蔽F1

dwr.engine.setActiveReverseAjax(true);
dwr.engine.setNotifyServerOnPageUnload(true);
function showMessage(data) {
if(data.winNum < 10){
	data.winNum = "0"+data.winNum;
}
    var ou2 = $('#date_u2');
    var liId = data.serialNum + "" + data.winNum;
    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:19px;height:18px;font-size: 18px;'>"+data.serialNum.toLocaleUpperCase()+"号请到"+data.winNum+"号窗口</li>";
    if (14 <= ou2.find("li").length) {
        ou2.find("li:first").remove();
    }
    ou2.append(newLi);
    
    var ou1 = $('#date_u1');
    var liId1 = data.serialNum + "" + data.winNum;
    var newLi1 = "<li id='" + liId1 + "' style='width:100%;list-style:none;border-bottom: none;line-height:19px;height:18px;font-size: 18px;'>"+data.serialNum.toLocaleUpperCase()+"号请到"+data.winNum+"号窗口</li>";
    if (14 <= ou1.find("li").length) {
        ou1.find("li:first").remove();
    }
    ou1.append(newLi1);
}

function showLzxxData(data) {
	var xx = $('#lzxx');
    var message = "<li style='white-space:nowrap;display:inline;width:100%;list-style:none;border-bottom: none;'>"+data.hphm+"&nbsp;&nbsp;</li>";
    if (25 <= xx.find("li").length) {
        xx.find("li:first").remove();
    }
    xx.append(message);
}
