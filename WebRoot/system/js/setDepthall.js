function checkData(){
	if($("#dmz").val() == ""){
		window.alert("大厅编号不能为空！");
		return false;
	}else if($("#dmsm1").val() == ""){
		window.alert("大厅名称不能为空！");
		return false;
	}else {
		return true;
	}
}

function addDepthall(){
	if(!checkData()){
		return;
	}else if(!dmzChange()){
		return;
	}else{
		document.form1.action = "addDepthall.action";
		document.form1.submit();
	}
}

function dmzChange(){
	$.post("checkDepthall.action",{dmz:$("#dmz").val()},function(resultData){
		if(eval(resultData)){
			window.alert("大厅编号不能重复！");
			falg= false;
		}else{
			falg= true;
		}
	});
	return falg;
}