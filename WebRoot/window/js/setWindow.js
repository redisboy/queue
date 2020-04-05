$(function() {
	//为address输入框添加blur事件
	$("#address").blur(function(){
		var barid = $("#address").val();
		//检查是否为空
		if("" == barid){ return; }
		
		//检查唯一性
		$.post("checkBarid.action", {barid: barid}, function(data) {
	        if (eval(data)) {
	            $("#barinfo").html("<img src='/queue/images/wrong.gif'/>地址已被占用!");
	    		$("#address").val("");
	        } else {
	            $("#barinfo").html("<img src='/queue/images/right.gif'/>地址可用!");
	        }
	    });
	});
	
	$("#windowId").blur(function() {
	    var windowCode = $("#windowId").val();
	    if ("" == windowCode) { return; }
	    $.post("checkWindowCode.action", {windowCode: windowCode}, function(data) {
	        if (eval(data)) {
	            $("#winc").html("<img src='/queue/images/wrong.gif'/>此窗口号已存在!");
	            $("#windowId").val("");
	        } else {
	            $("#winc").html("<img src='/queue/images/right.gif'/>此窗口号可用!");
	        }
	    });
	});
	
	//为ipaddress输入框添加blur事件
	$("#ipaddress").blur(function(){
		var ipaddress = $("#ipaddress").val();
		var hip = $("#hip").val();
		if ("" == ipaddress) {
			$("#ipinfo").html("BarIP地址不能为空!");
			$("#ipaddress").focus();
			return;
		}
	    if ("" != hip && hip == ipaddress) { 
		    $("#ipinfo").html("<img src='/queue/images/right.gif'/>BarIP地址可用!");
		    return;
	    }
		//检查唯一性
		$.post("checkIP.action", {ipaddress: ipaddress}, function(data) {
	        if (eval(data)) {
	            $("#ipinfo").html("<img src='/queue/images/wrong.gif'/>BarIP地址已被占用!");
	    		$("#ipaddress").val("");
	        } else {
	            $("#ipinfo").html("<img src='/queue/images/right.gif'/>BarIP地址可用!");
	        }
	    });
	});
	
	if (1 == $("#type").val()) {
	    if ("" == $("#com1").val()) {
		    $("input[name='mainWindow']").get(0).checked = true;
		    isMainWindow(0);
		} else {
			$("input[name='mainWindow']").get(1).checked = true;
		}
	}
	
	var user_role_ids = $("#user_role_ids").val();
	if (undefined != user_role_ids && "" != user_role_ids) {
		var datas = user_role_ids.split(',');
		$('input[name=queue]').each(function(i) {
   	    	for (var j = 0, len = datas.length; j < len; ++ j) {
	   			if (datas[j] == $(this).val()){
	   				$(this).attr('checked','true');
	   				break;
	   			}
	   		}
   	    });
	}
});

function isMainWindow(val) {
    var speed = 400;
    if (0 == val) {
	    var offset1 = $("#begin").offset();
	    $("#divPop").css({'position':'absolute','top':offset1.top,'left':offset1.left,
	    	'width':$("#begin").width(),'height':$("#myTable").height()-70});
	    $("#divIframe").css({'width':$("#begin").width(),'height':$("#myTable").height()-70});
	    if ($("#divPop").css("display") == "none") {
	    	$("#divPop").slideDown(speed);
	        $("#divPop").fadeTo(0, 0.4);
	    } else {
	        $("#divPop").fadeIn(speed);
	    }
	    
	    $("#address").val("");
	    $.each($("select"), function() { $(this).val(""); });
	    $.each($("input[name='showNum']"), function() { $(this).attr("checked", false); });
	    $.each($("input[type='checkbox']"), function() { $(this).attr("checked", false); });
	    $.each($("#myTable input[type='text']"), function(i) { if (0 != i) { $(this).val(""); } });
    } else if (1 == val) {
        $("#divPop").slideUp(speed);
    }
}

function checkData(flag) {
    var mianWindowValue = getCheckedRadioValue("mainWindow");
    if (1 == flag && -1 == mianWindowValue) {
	    window.alert("请选择是否主窗口");
	    return false;
    } else if (2 == flag) {
        mianWindowValue = 1;
    }
    
    if (2 == flag) {
        if ("" == $("#menuAddress").val()) {
            window.alert("请选择父窗口编号");
            return false;
        }
    }
    
    if ("add" == $("#operate").val()) {
        var windowid = $("#windowId").val();
		if (1 == flag) {
		    if ("" == windowid) {
		        window.alert("窗口号不能为空");
		        $("#windowId").focus();
				return false;
		    }
		    
		    if (1 > windowid || 50 < windowid) {
		        window.alert("窗口号只能在1-50之间");
		        $("#windowId").focus();
				return false;
		    }
		} else if (2 == flag) {
			if ("" == windowid) {
				alert("子窗口编号不能为空");
				return false;
			}
			
			if (null == windowid.match(/^[A-Z]/)) {
				alert("子窗口编号不合法");
				return false;
			}
		}
    }
   
    if (1 == mianWindowValue) {
		var com = $("#com1").val();
        if ("" == com) {
           window.alert("COM口不能为空");
           return false;
        }
        
	    var address = document.form1.address.value;
	    if(address.length == 0){
			alert("LED屏地址不能为空!");
			document.form1.address.focus();
			return false;
		}
		
		if ("" == $("#color").val()) {
		    window.alert("请选择字体颜色");
		    return false;
		}
		
		if ("" == $("#align").val()) {
		    window.alert("请选择对齐方式");
		    return false;
		}
		
		var exp = /^([1-9]|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(([0-9]|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.){2}([1-9]|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])$/;
		if (null == $("#ipaddress").val().match(exp)) {
			alert("IP地址不合法！");
		    return false;
		}
		if (null == $("#ckip").val().match(exp)) {
			alert("IP地址不合法！");
		    return false;
		}
		
		if ("" == $("#defaultvalue").val()) {
		    window.alert("请选择默认评价");
		    return false;
		}
		
		if (0 == $("#isUseOldDevice").val() && "" == $("#com2").val()) {
		    window.alert("请填写硬件评价器地址");
		    return false;
		}
		
		var singleVal = getCheckedRadioValue("showNum");
		if (-1 == singleVal) {
			window.alert("请选择单双行显示");
		    return false;
		}
		if ("" == $("#content").val()) {
		    window.alert("请填写默认显示内容");
		    return false;
		}
//		var stoptime = document.form1.stoptime.value;
//		if(stoptime.length == 0){
//			alert("请填写停顿值!");
//			document.form1.stoptime.focus();
//			return false;
//		}  
//		var interval = document.form1.interval.value;
//		if(interval.length == 0){
//			alert("请填写间隔值!");
//			document.form1.interval.focus();
//			return false;
//		} 
//		var word = document.form1.word.value;
//		if(word.length == 0){
//			alert("请填写字数!");
//			document.form1.word.focus();
//			return false;
//		}
//		var speed = document.form1.speed.value;
//		if(speed.length == 0){
//			alert("请填写速度!");
//			document.form1.speed.focus();
//			return false;
//		}
//		if( parseInt(speed) < 0 || parseInt(speed) > 255){
//			alert("速度必须在0-255之间!");
//			document.form1.address.focus();
//			return false;
//		}
	}
	return true;
}

function changeColor(){
	if($('input[name="showNum"]:checked').val()==1){
		$("#color1").css("display","none")
		$("#color2").css("display","none")
		$("#color3").css("display","none")
	}else if($('input[name="showNum"]:checked').val()==2){
		$("#color1").css("display","")
		$("#color2").css("display","none")
		$("#color3").css("display","none")
	}else if($('input[name="showNum"]:checked').val()==3){
		$("#color1").css("display","")
		$("#color2").css("display","")
		$("#color3").css("display","none")
	}else if($('input[name="showNum"]:checked').val()==4){
		$("#color1").css("display","")
		$("#color2").css("display","")
		$("#color3").css("display","")
	}
}

function addQueue(flag) {
	if (!checkData(flag)) { return; }
    document.form1.action = "addWindow.action";
	document.form1.submit();
}

function updateWindowByCode(flag) {
	if (!checkData(flag)) { return; }
    document.form1.action = "updateWindowByCode.action";
	document.form1.submit();
}

function exit(){
	document.form1.action = "/queue/index/welcome.html";
	document.form1.submit();
}