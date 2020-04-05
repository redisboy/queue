document.oncontextmenu = function() { return false; } //禁止右键功能,单击右键将无任何反应
document.onselectstart = function() { return false; } //禁止选择,也就是无法复制
window.onhelp = function() { return false; } //屏蔽F1

dwr.engine.setActiveReverseAjax(true);
dwr.engine.setNotifyServerOnPageUnload(true);
function showMessage(data) {
    var oul = $('#top_ul');
    var liId = data.serialNum + "" + data.winNum;
    var newLi = "<li id='" + liId + "' style='font-size:55px;width:500px;'>"+data.serialNum+"号到"+data.winNum+"号窗口</li>";
    if (10 <= oul.find("li").length) {
        oul.find("li:first").remove();
    }
    oul.append(newLi);
}
window.onload = function (){
	$.ajax({type: "POST", cache: false, async: false, dataType: "json",
		   url: '/queue/number/getAllcomprehensiveScreen.action',
		    success: function(request) {
		       if (request.isSuccess) {
		        isChange = true;
		        delete request.isSuccess;
		        var cont="";
				$.each(request.datas, function(i, obj) {
					cont+="<div id='dropmsg"+i+"' style='color:#f00;'>"+obj.message+"</div>";
				});
				$('#contnetdiv').html(cont);
				//加载内容自动换
				if (window.addEventListener)window.addEventListener("load", startscroller, false);
				else if (window.attachEvent)window.attachEvent("onload", startscroller);
				}
		  }
	});
}