document.oncontextmenu = function() { return false; } //禁止右键功能,单击右键将无任何反应
document.onselectstart = function() { return false; } //禁止选择,也就是无法复制
window.onhelp = function() { return false; } //屏蔽F1

dwr.engine.setActiveReverseAjax(true);
dwr.engine.setNotifyServerOnPageUnload(true);

function showMessage(data) {
	$("#shownr").html(data.nameAndShul);
}
window.onload = function(){
	$.ajax({type:"POST", cache:false, async: false, dataType: "json",
		url:"/queue/number/getQuHaoContent.action",
		success:function (req) {
			$("#myMarq").html(req.gdMsg);
			if(null == req.message && ""==req.message){
				$("#mask_tip").css("display","");
			}else{
				$("#mask_tip").css("display","none");
				$("#message").html(req.message);
			}
		}
	});
}