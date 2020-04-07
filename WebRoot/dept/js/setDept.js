function addDept(){
	if(!checkData()){return;}
		form1.action = "dept/addDept.action";
		form1.submit();
		}

function checkData(){
//	var reg = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
//		/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
	if($("#deptname").val() == ""){
		window.alert("大厅名称不能为空！");
		return false;
	}else if($("#depthall").val() ==""){
		window.alert("大厅编号不能为空！");
		return false;
	}else if($("#deptcode").val() ==""){
		window.alert("管理部门不能为空！");
		return false
	}else if($("#serversip").val() ==""){
	 	window.alert("应用服务器IP不能为空！");
		return false
	}else{
		return true;
	}
}