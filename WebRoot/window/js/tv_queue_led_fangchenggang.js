document.oncontextmenu = function() {
	return false;
} // 禁止右键功能,单击右键将无任何反应
document.onselectstart = function() {
	return false;
} // 禁止选择,也就是无法复制
window.onhelp = function() {
	return false;
} // 屏蔽F1

dwr.engine.setActiveReverseAjax(true);
dwr.engine.setNotifyServerOnPageUnload(true);
function showLzxx(data) {
	var xm = data.xm;
	if (xm.length > 10) {
		xm = xm.substr(0, 10);
	}
	var ou1 = $('#lzxx');
	var liId1 = data.xm + "" + data.barid;
	var newLi1 = "<li id='"
			+ liId1
			+ "' style='width:100%;list-style:none;border-bottom: none;line-height:19px;height:18px;font-size: 17px;'>"
			+ xm + "请到" + data.barid + "号窗口领证</li>";
	if (17 <= ou1.find("li").length) {
		ou1.find("li:first").remove();
	}
	ou1.append(newLi1);
}

function showLzxx1(data) {
	alert(data.xm);
	alert(data.barid);
	var ou1 = $('#lzxx');
	var message = "<li style='width:100%;list-style:none;border-bottom: none;'>"
			+ data.xm + ":" + data.barid + "</li>";
	if (6 <= oul.find("li").length) {
		oul.find("li:first").remove();
	}
	oul.append(message);
}

function showPJXX(data) {
	var contentAll = data.content;
	// alert(contentAll);
	var userContentArray = contentAll.split("@");
	var ou1 = $('#name');
	var ou2 = $('#myl');
	var ou3 = $('#jhl');
	for ( var i = 0; i < (userContentArray.length - 1); i++) {
		var userContent = userContentArray[i].split(":");
		var flag = userIsShow(userContent[0]);
		if (flag) {
			$("#myl").find("li[id='" + userContent[0] + "']").text(
					userContent[1] + "%");
			$("#jhl").find("li[id='" + userContent[0] + "']").text(
					userContent[2]);
		} else {
			$("#name").append(
					"<li id='" + userContent[0]
							+ "' style='font-size:15px;text-align:center;'>"
							+ userContent[0] + "</li>");
			$("#myl").append(
					"<li id='" + userContent[0]
							+ "' style='font-size:15px;text-align:center;'>"
							+ userContent[1] + "%" + "</li>");
			$("#jhl").append(
					"<li id='" + userContent[0]
							+ "' style='font-size:15px;text-align:center;'>"
							+ userContent[2] + "</li>");
		}
	}
	if (17 <= ou1.find("li").length) {
		ou1.find("li:first").remove();
	}
	if (17 <= ou2.find("li").length) {
		ou2.find("li:first").remove();
	}
	if (17 <= ou3.find("li").length) {
		ou3.find("li:first").remove();
	}
}

function showMessage(data) {
	if (data.winNum < 10) {
		data.winNum = "0" + data.winNum;
	}
	/*
	 * var ou2 = $('#date_u2'); var liId = data.serialNum + "" + data.winNum;
	 * var newLi = "<li id='" + liId + "' style='width:100%;list-style:none;border-bottom: none;line-height:13px;height:14px;font-size: 14px;'>"+data.serialNum.toLocaleUpperCase()+"号请到"+data.winNum+"号窗口</li>";
	 * if (14 <= ou2.find("li").length) { ou2.find("li:first").remove(); }
	 * ou2.append(newLi);
	 */

	var ou1 = $('#date_u1');
	var liId1 = data.serialNum + "" + data.winNum;
	var newLi1 = "<li id='"
			+ liId1
			+ "' style='width:100%;list-style:none;border-bottom: none;line-height:19px;height:18px;font-size: 20px;'>"
			+ data.serialNum.toLocaleUpperCase() + "号请到" + data.winNum
			+ "号窗口</li>";
	if (14 <= ou1.find("li").length) {
		ou1.find("li:first").remove();
	}
	ou1.append(newLi1);
}

function showLzxxData(data) {
	var ou1 = $('#lzxx');
	var liId = data.serialNum + "" + data.winNum;
	var message = "<li style='width:100%;list-style:none;border-bottom: none;'>"
			+ data.jsz + ":" + data.xsz + ":" + data.djzs + "</li>";
	if (6 <= oul.find("li").length) {
		oul.find("li:first").remove();
	}
	oul.append(message);
	setTimeout(function() {
		$("#" + liId).remove();
	}, 600000);
}

function showZhxxData(data) {
	window.location.href = 'http://192.168.198.1:8080/queue/redirect.jsp?tv=led2';
	// var params = 'flag='+data.flag;
	// $.ajax({type:"POST", cache:false, data:params,
	// url:"/queue/number/ShowZHP.action",
	// success:function (req) {
	// if (req != null || req != "") {
	// $("#bjqnr").html(req);
	// }
	// }
	// });
}

// 判断该用户是否已经显示在页面上
function userIsShow(name) {
	var flag = $("li[id='" + name + "']");
	if (flag.length != 0) {
		return true;
	} else {
		return false;
	}
}