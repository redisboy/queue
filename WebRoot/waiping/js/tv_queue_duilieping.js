document.oncontextmenu = function() { return false; } //禁止右键功能,单击右键将无任何反应
document.onselectstart = function() { return false; } //禁止选择,也就是无法复制
window.onhelp = function() { return false; } //屏蔽F1

dwr.engine.setActiveReverseAjax(true);
dwr.engine.setNotifyServerOnPageUnload(true);
function showLzxx(data) {
		var xm = data.xm;
		if(xm.length>8){
			xm = xm.substr(0,8);
		}
	    var ou1 = $('#date_u1');
	    var ou2 = $('#date_u2');
	    var newLi1 = "<li>"+xm+"</li>";
	    var newLi2 = "<li>"+data.barid+"</li>";
	    if (10 <= ou1.find("li").length) {
	        ou1.find("li:first").remove();
	        ou2.find("li:first").remove();
	    }
	    ou1.append(newLi1);
	    ou2.append(newLi2);
	}

function showMessage(data) {
if(data.winNum < 10){
	data.winNum = "0"+data.winNum;
}   
    var ou1 = $('#date_u3');
    var newLi1 = "<li><span class='sp1'>"+data.serialNum.toLocaleUpperCase()+"</span><span class='sp2'>"+data.winNum+"</span></li>";
    if (10 <= ou1.find("li").length) {
        ou1.find("li:first").remove();
    }
    ou1.append(newLi1);
}