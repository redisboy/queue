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
    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:36px;height:36px;font-size: 36px;'>"+data.serialNum.toLocaleUpperCase()+"号请到"+data.winNum+"号窗口办理业务</li>";
    if (9 <= ou2.find("li").length) {
        ou2.find("li:first").remove();
    }
    ou2.append(newLi);
    
    var ou1 = $('#date_u1');
    var liId1 = data.serialNum + "" + data.winNum;
    var newLi1 = "<li id='" + liId1 + "' style='width:100%;list-style:none;border-bottom: none;line-height:36px;height:36px;font-size: 36px;'>"+data.serialNum.toLocaleUpperCase()+"号请到"+data.winNum+"号窗口办理业务</li>";
    if (9 <= ou1.find("li").length) {
        ou1.find("li:first").remove();
    }
    ou1.append(newLi1);
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
	window.location.href='http://10.231.68.15:8080/queue/redirect.jsp?tv=led';
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
