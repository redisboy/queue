//document.oncontextmenu = function() { return false; } //禁止右键功能,单击右键将无任何反应
document.onselectstart = function() { return false; } //禁止选择,也就是无法复制
window.onhelp = function() { return false; } //屏蔽F1

dwr.engine.setActiveReverseAjax(true);
dwr.engine.setNotifyServerOnPageUnload(true);

function showMessage(data) {
if(data.winNum < 10){
	data.winNum = "0"+data.winNum;
}
    var newJLi = "<li style='font-size:110px;width:100%;'>"+data.serialNum+"号请到"+data.winNum+"号窗口</li>";
    var oul = $('#top_ul');
 /*   if (7 > ocul.find("li").length) {
    	ocul.append(newJLi);
	} else {
        var ocul2 = $('#top_ul');
        if (7 <= ocul2.find("li").length) {
	        ocul.find("li:first").remove();
	        var oli = ocul2.find("li:first");
		    ocul.append("<li style='font-size:60px;width:610px;'>"+oli.text()+"</li>");
		    oli.remove();
	    }
		ocul2.append(newJLi);
    }*/
    if (6 <= oul.find("li").length) {
        oul.find("li:first").remove();
    }
    oul.append(newJLi);
}