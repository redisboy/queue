document.oncontextmenu = function() { return false; } //禁止右键功能,单击右键将无任何反应
document.onselectstart = function() { return false; } //禁止选择,也就是无法复制
window.onhelp = function() { return false; } //屏蔽F1

dwr.engine.setActiveReverseAjax(true);
dwr.engine.setNotifyServerOnPageUnload(true);

window.onload=function(){
	//setTimeout(function() {   //取当前时间显示具体时间
	//	var date = new Date();       
	//   var y = date.getFullYear();       
	//    var m = date.getMonth()+1;       
	//    var d = date.getDate();       
	//    var h = date.getHours();       
	//    var i = date.getMinutes();       
	//    var s = date.getSeconds();
	//    alert(y+m+d+h+i+s);
	//    if(y==2015&&m==12&&d<=31&&h<24){
	//    	var djsd = 31-d;
	//    	var djsh = 24-h;
	//    	var djsf = 60-i;
	 //   	
	  //  }
    //}, 3000);
    	var date = new Date();       
	   var y = date.getFullYear();       
	    var m = date.getMonth()+1;       
	    var d = date.getDate();       
	    var h = date.getHours();       
	    var i = date.getMinutes();       
	    var s = date.getSeconds();
	    alert(y+m+d+h+i+s);
}

function showPJXX(data) {
	var contentAll = data.content;
	//alert(contentAll);
		
	var userContentArray = contentAll.split("@");
	for(var i=0;i<(userContentArray.length-1);i++){
		var userContent = userContentArray[i].split(":");
		var flag = userIsShow(userContent[0]);
		if(flag){
			$("#myl").find("li[id='"+userContent[0]+"']").text(userContent[1]+"%");
			$("#jhl").find("li[id='"+userContent[0]+"']").text(userContent[2]);
		}else{
			$("#name").append("<li id='"+userContent[0]+"' style='font-size:18px;text-align:center;'>"+userContent[0]+"</li>")
			$("#myl").append("<li id='"+userContent[0]+"' style='font-size:18px;text-align:center;'>"+userContent[1]+"%"+"</li>")
			$("#jhl").append("<li id='"+userContent[0]+"' style='font-size:18px;text-align:center;'>"+userContent[2]+"</li>")
		}
	}
}

//判断该用户是否已经显示在页面上
function userIsShow(name){
	var flag = $("li[id='"+name+"']");
	if(flag.length!=0){
		return true;
	}else{
		return false;
	}
}

//获取星期星期名称
function getXq(day){
	if(day==0){
		$("#xq").text("星期日");
	}else if(day==1){
		$("#xq").text("星期一");
	}else if(day==2){
		$("#xq").text("星期二");
	}else if(day==3){
		$("#xq").text("星期三");
	}else if(day==4){
		$("#xq").text("星期四");
	}else if(day==5){
		$("#xq").text("星期五");
	}else if(day==6){
		$("#xq").text("星期六");
	}
	
}
