document.oncontextmenu = function() { return false; } //禁止右键功能,单击右键将无任何反应
document.onselectstart = function() { return false; } //禁止选择,也就是无法复制
window.onhelp = function() { return false; } //屏蔽F1

dwr.engine.setActiveReverseAjax(true);
dwr.engine.setNotifyServerOnPageUnload(true);
function showMessage(data) {
	if(data.winNum < 10){
		data.winNum = "0"+data.winNum;
	}
    var oul = $('#date_u1');
    var oul2 = $('#date_u2');
    var liId = data.serialNum + "" + data.winNum;
    var newLi = "<li id='" + liId + "' style='font-size:20px;width:100%;text-align:center'><font color='green'>"+data.serialNum+"号请到"+data.winNum+"窗口</font></li>";
    if (14 <= oul.find("li").length) {
        oul.find("li:first").remove();
    }
    oul.append(newLi);
    setTimeout(function() {
        $("#"+liId).remove();
    }, 600000);
    
    var newLi2 = "<li id='" + liId +"2"+ "' style='font-size:20px;width:100%;text-align:center'><font color='red'>"+data.serialNum+"</font>号请到<font color='red'>"+data.winNum+"</font>窗口</li>";
    if (14 <= oul2.find("li").length) {
    	oul2.find("li:first").remove();
    }
    oul2.append(newLi2);
    setTimeout(function() {
        $("#"+liId+"2").remove();
    }, 600000);
    
    
    var a = $("#"+data.winNum+"ywl");
    $("#"+data.winNum+"ywl").html(data.ywl);
    $("#"+data.winNum+"hm").html(data.serialNum);
    var profix = data.serialNum.substring(0,1);
    var number = parseInt(data.serialNum.substring(1),10);
    var nextNum = number+1;
    var numStr = zfill(nextNum,4);
    var nextNumStr = profix+numStr;
    $("#"+data.ywlx+"nextNum").html(nextNumStr);
    var ddrs = $("#"+data.ywlx+"ddrs").html();
    var ddrstemp = parseInt(ddrs,10)-1;
    if(ddrstemp>=0){
    	$("#"+data.ywlx+"ddrs").html(ddrstemp);
    }else{
    	$("#"+data.ywlx+"ddrs").html("0");
    }
    //$("#"+data.ywlx+"ddrs").html(parseInt(ddrs,10)-1);
	ddrssum();
}
//补全0
function zfill(num, size) {
    var s = "000000000" + num;
    return s.substr(s.length-size);
}
//打开页面读取部分信息
function getInfo(){
	$.ajax({
		type: "POST", cache: false, async: false, dataType: "json",
        url: '/queue/number/getYWandScreenInfo.action',
        success: function(data) {
			//遍历业务类型对应的等待人数到页面
			var ywAndddrs = data.ywAndddrs;
			for(var i=0;i<ywAndddrs.length;i++){
				//var ywAndddrsTab =$("#ywAndddrs");
				//var temp = "<tr><td id='"+ywAndddrs[i].id+"name'>"+ywAndddrs[i].name+"</td><td id='"+ywAndddrs[i].id+"ddrs'>"+ywAndddrs[i].ddrs+"</td><td id='"+ywAndddrs[i].id+"nextNum'></td></tr>";
				//ywAndddrsTab.append(temp);
				$("#ywmc").append("<li style='font-size:14px;width:100%;height:12px;' id='"+ywAndddrs[i].id+"name'>"+ywAndddrs[i].name+"</li>");
				$("#ddrs").append("<li style='font-size:14px;width:100%;height:14px' id='"+ywAndddrs[i].id+"ddrs'>"+ywAndddrs[i].ddrs+"</li>");
				$("#nextNum").append("<li style='font-size:14px;width:100%;height:14px'  id='"+ywAndddrs[i].id+"nextNum'></li>");
			}
			//遍历窗口号及业务量到页面
			var screenAndywl = data.screenAndywl;
			for(var i=0;i<screenAndywl.length;i++){
				//var screenAndywlTab =$("#screenAndywl");
				//screenAndywlTab.append("<tr><td id='"+screenAndywl[i].windowNum+"winNum'>"+screenAndywl[i].windowNum+"</td><td id='"+screenAndywl[i].windowNum+"ywl'>"+screenAndywl[i].ywl+"</td><td id='"+screenAndywl[i].windowNum+"hm'></td></tr>");
				if(screenAndywl[i].windowNum < 10){
					screenAndywl[i].windowNum = "0"+screenAndywl[i].windowNum;
				}
				$("#windowNum").append("<li id='"+screenAndywl[i].windowNum+"'>"+screenAndywl[i].windowNum+"</li></br>");
				$("#ywl").append("<li id='"+screenAndywl[i].windowNum+"ywl'>"+screenAndywl[i].ywl+"</li></br>");
//				$("#windowNum").append("<li style='font-size:20px;width:100%;height:24px' id='"+screenAndywl[i].windowNum+"winNum'>"+screenAndywl[i].windowNum+"</li>");
//				$("#ywl").append("<li style='font-size:20px;width:100%;height:24px' id='"+screenAndywl[i].windowNum+"ywl'>"+screenAndywl[i].ywl+"</li>");
				$("#hm").append("<li style='font-size:20px;width:100%;height:24px' id='"+screenAndywl[i].windowNum+"hm'></li>");
				
			}
			ddrssum();
        }
	});
}
//取号更新等待人数
function showYWL(date){
	$("#"+date.ywlx+"ddrs").html(date.ddrs);
	ddrssum();
}
//更新等待总数
function ddrssum(){
	var sum=0;
	for(var i=0;i<$("#ddrs li").length;i++){
		sum+=parseFloat($("#ddrs li").get(i).innerHTML);
		}
	$("#ddrssum").html("各业务等待总数:"+sum+"人");
}