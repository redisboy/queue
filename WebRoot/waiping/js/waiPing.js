document.oncontextmenu = function() { return false; } //禁止右键功能,单击右键将无任何反应
document.onselectstart = function() { return false; } //禁止选择,也就是无法复制
window.onhelp = function() { return false; } //屏蔽F1

dwr.engine.setActiveReverseAjax(true);
dwr.engine.setNotifyServerOnPageUnload(true);

function waiPingXinXi(datas){
	var ckh = datas.ckidh;
	var ckm = datas.ckmch;
	var sx = datas.sxhh;
	if('暂停'==sx){
		$("#ckmc").text('暂停受理');
//		$("#ztsl").css("display","");
//		$("#sxh").css("display","none");
	}else if(' '==sx){
		$("#ckmc").text(ckm);
//		$("#ztsl").css("display","none");
//		$("#sxh").css("display","");
	}else{
		$("#ckmc").text(sx);
	}
//	$("#ckid").text(ckh);
//	$("#ckmc").text(ckm);
//	$("#sxh").text(sx);
	
	
	
//	if('暂停'==sx){
//		$("#ckmc").text('暂停受理');
//	}else if(' '==sx){
//		$("#ckmc").text(ckm);
//	}else {
//		$("#ckmc").text(sx);
//	}
}