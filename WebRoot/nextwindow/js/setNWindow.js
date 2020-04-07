function addNwindow(){
	if(!checkData()){return;}
		else if(!postnewxrweindow()){return;}
		form1.action = "nextwindow/addNwindow.action";
		form1.submit();
}

function checkData(){
	var nwindowDmlbValue = $("#nwindowDmlb").val();
	if(nwindowDmlbValue == "00"){
		window.alert("业务类型不能为空！");
		return false;
	}
	if($("#nwindowDmz").val() =="00"){
		window.alert("业务类型不能为空！");
		return false;
	}if($("#nwindowywyybh").val() =="00"){
		window.alert("业务原因不能为空！")
	}if($("#nextwindowval").val() == ""){
		window.alert("提示信息不能为空！");
		return false;
	}else{
		return true;
	}
}
function postnewxrweindow(){
	$.post("nextwindow/checkDMZ.action",
		{nwindowDmlb:$("#nwindowDmlb").val(),
		nwindowDmz:$("#nwindowDmz").val(),
		nwindowywyybh:$("#nwindowywyybh").val()},
		function(resultData){
			if(eval(resultData)){
				window.alert("业务原因重复！");
				falg= false;
			}else{
				falg = true;
			}
		});
	return falg;
}
$("#nwindowDmz").change(function(){
	var nwindowDmlbVal = $("#nwindowDmlb").val();
	var nwindowDmzVal = $("#nwindowDmz").val();
	$.ajax({type: "POST", cache: false, async: false, dataType: "json",data:{"nwindowDmlbVal":nwindowDmlbVal,"nwindowDmzVal":nwindowDmzVal},
		        url: 'nextwindow/queryYwyyForAdd.action',
		        success: function(request) {
		            if (request.isSuccess) {
		                isChange = true;
		                delete request.isSuccess;
		                $("#nwindowywyybh").empty();
		                $("#nwindowywyybh").append("<option value='00'>请选择</option>");
						$.each(request.datas, function(key, obj) {
						$("#nwindowywyybh").append("<option value="+obj.ywyy+">"+obj.ywmc+"</option>");
	     				});
					} else {
						 $("#nwindowywyybh").empty();
						 $("#nwindowywyybh").append("<option value='00'>请选择</option>");
						 isSuccess = request.isSuccess;
						 window.alert(request.error);
					}
		        },
		        error: function() {
		            isSuccess = false;
		            window.alert("连接服务器失败，请稍候再试");
		        }
		    });
});

$("#nwindowywyybh").change(function(){
	postnewxrweindow();
});


$("#nwindowDmlb").change(function(){
	var nwindowDmlbVal = $("#nwindowDmlb").val();
	$.ajax({type: "POST", cache: false, async: false, dataType: "json",data:{"nwindowDmlbVal":nwindowDmlbVal},
		        url: 'nextwindow/toAddNWindowAjax.action',
		        success: function(request) {
		            if (request.isSuccess) {
		                isChange = true;
		                delete request.isSuccess;
		                $("#nwindowDmz").empty();
		                $("#nwindowywyybh").empty();
		                $("#nwindowDmz").append("<option value='00'>请选择</option>");
		                $("#nwindowywyybh").append("<option value='00'>请选择</option>");
						$.each(request.datas, function(key, obj) {
						$("#nwindowDmz").append("<option value="+obj.dm+">"+obj.mc+"</option>");
	     				});
					} else {
						 $("#nwindowDmz").empty();
						 $("#nwindowywyybh").empty();
						 $("#nwindowDmz").append("<option value='00'>请选择</option>");
						 $("#nwindowywyybh").append("<option value='00'>请选择</option>");
						 isSuccess = request.isSuccess;
						 window.alert(request.error);
					}
		        },
		        error: function() {
		            isSuccess = false;
		            window.alert("连接服务器失败，请稍候再试");
		        }
		    });
});