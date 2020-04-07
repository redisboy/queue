document.oncontextmenu = function() { return false; } //禁止右键功能,单击右键将无任何反应
document.onselectstart = function() { return false; } //禁止选择,也就是无法复制
window.onhelp = function() { return false; } //屏蔽F1

dwr.engine.setActiveReverseAjax(true);
dwr.engine.setNotifyServerOnPageUnload(true);
function showLzxx(data) {
if(data.winNum < 10){
}
//    var ou2 = $('#date_u2');
//    var liId = data.serialNum + "" + data.winNum;
//    var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:19px;height:18px;font-size: 18px;'>"+data.xm+"号请到"+data.barid+"号窗口</li>";
//    if (14 <= ou2.find("li").length) {
//        ou2.find("li:first").remove();
//    }
//    ou2.append(newLi);
	var xm = data.xm;
	if(xm.length>10){
		xm = xm.substr(0,10);
	}
    var ou1 = $('#date_u1');
    var liId1 = data.xm + "" + data.barid;
    var newLi1 = "<li id='" + liId1 + "' style='width:100%;list-style:none;border-bottom: none;line-height:19px;height:18px;font-size: 18px;'>"+xm+"请到"+data.barid+"号窗口领证</li>";
    if (14 <= ou1.find("li").length) {
        ou1.find("li:first").remove();
    }
    ou1.append(newLi1);
}

function showLzxx1(data) {
	alert(data.xm);
	alert(data.barid);
	var ou1 = $('#date_u1');
    var message = "<li style='width:100%;list-style:none;border-bottom: none;'>"+data.xm+":"+data.barid+"</li>";
    if (6 <= oul.find("li").length) {
        oul.find("li:first").remove();
    }
    oul.append(message);
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
