document.oncontextmenu = function() { return false; } //禁止右键功能,单击右键将无任何反应
document.onselectstart = function() { return false; } //禁止选择,也就是无法复制
window.onhelp = function() { return false; } //屏蔽F1

dwr.engine.setActiveReverseAjax(true);
dwr.engine.setNotifyServerOnPageUnload(true);
function showMessage(data) {
    var oul = $('#top_ul');
    var liId = data.serialNum + "" + data.winNum;
    var newLi = "<li id='" + liId + "' style='font-size:115px;width:100%'><font color='red'>"+data.serialNum+"</font>号请到<font color='red'>"+data.winNum+"</font>号窗口</li>";
    if (7 <= oul.find("li").length) {
        oul.find("li:first").remove();
    }
    oul.append(newLi);
    setTimeout(function() {
        $("#"+liId).remove();
    }, 600000);
}