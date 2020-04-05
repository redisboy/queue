dwr.engine.setActiveReverseAjax(true);
dwr.engine.setNotifyServerOnPageUnload(true);
var instance;
photo = (function() {
	//打开摄像头
	function  openCamera(){
			$.ajax({type:"POST", cache:false,dataType: "json",
		        url: "number/openCamera.action",
		        success: function(data) {
		       		if(0 == data.isOpenIndexCamera){//是否开启取号摄像头
			        	if(0==data.autoOrManualCapture){//标记 0：自动 1:手动
			        		 $("#openSxt").css("display","none");
			        		    $("#showzp").html("<object id='jsrzP' classid='clsid:C3FF8650-C1AC-4E41-9474-6C27A58502D6' width='300' height='320' align='middle' CODEBASE='plugs/camera/zdywzp.CAB#version=1.0.0.3'></object>");
								jsrzP.nW = 300;
								jsrzP.nH = 320;
								
								if(false == jsrzP.OnCloseDev()){//关闭摄像头
										jsrzP.OnCloseDev();
								}
								 var openres = jsrzP.OnContorlInit();
								 if(false == openres) {
								 	jsrzP.OnContorlInit();
								 	jsrzP.OnContorlInit();
								 }
								 setTimeout("", 5000);
								btnCapture_onclick();
								setTimeout("po.save(0);", 2000);
								
							 
						}else{
				           		$("#openSxt").html("<INPUT id=u17  type=submit value='打开摄像头' onClick='po.manualCanera();' style='width:100px; height:50px; ; ; text-align: center ; font-family:'Arial'; font-size: 13px; color:#000000; font-style:normal; font-weight:normal; text-decoration:none' >");
				           		
						}
						
					}else{
						 	alert("未开启摄像功能，如有需要请联系管理员！！！");
						 	return;
					}
					
					
				}
	        });
	}
	function openCaneraManual(){
			$("#openSxt").css("display","none");
			$("#showzp").html("<object id='jsrzP' classid='clsid:C3FF8650-C1AC-4E41-9474-6C27A58502D6' width='300' height='320' align='middle' CODEBASE='plugs/camera/zdywzp.CAB#version=1.0.0.3'></object>");
			$("#pz").html("<INPUT id=btnCapture type=button value='拍照' name=btnCapture LANGUAGE=javascript onclick='btnCapture_onclick() 'style='width:100px; height:50px; ; ; text-align: center ; font-family:'Arial'; font-size: 13px; color:#000000; font-style:normal; font-weight:normal; text-decoration:none'>&nbsp;&nbsp;"+
							"<input name='subpic' type='button' value='保存照片' onclick='po.save(1)'style='width:100px; height:50px; ; ; text-align: center ; font-family:'Arial'; font-size: 13px; color:#000000; font-style:normal; font-weight:normal; text-decoration:none' >");
			jsrzP.nW = 300;
			jsrzP.nH = 320;
								 
			if(false == jsrzP.OnCloseDev()){//关闭摄像头
				jsrzP.OnCloseDev();
			}
			var openres = jsrzP.OnContorlInit();
			if(false == openres) {
				jsrzP.OnContorlInit();
			}
	}
	function subCameraPic(flag){//保存照片
				if(1 == flag){
					if(null == $("#base64Code").val() || "" == $("#base64Code").val()){
						alert("请拍照！！！");
						return;
					}
				}
				var params = '&sxh=' +sxh+ '&sxhid=' +sxhid+ '&photoBase64=' + encodeURIComponent($("#base64Code").val());
					$.ajax({type:"POST", cache:false, data:params,
							url:"number/saveQuHaoPhoto.action",
							success:function (picres) {
								if(0 == picres){
									alert("照片保存成功!!!");
									//----拍照后隐藏控件  begin
									$("#zpjg").html("<img src='right/images/u13.png' alt='' width='77' height='77'>");
									$("#pz").html("");
									$("#base64Code").val("");
									$("#openSxt").css("display","");
									if(false == jsrzP.OnCloseDev()){//关闭摄像头
										jsrzP.OnCloseDev();
									}
									$("#showzp").html("<img src='right/images/u13.png' width='420' height='420'>");
									//----拍照后隐藏控件   end
									window.close();
								}else if(1 == picres){
									alert("照片保存失败!!!");
									return;
								}
							}
					});
	}
	function cameraPhoto (){
		this.open = function() {
			openCamera();
		}
		this.save = function(flag) {
			subCameraPic(flag);
		}
		this.manualCanera = function (){
			openCaneraManual();
		}
	}
	return {
		getInstance: function() {
			if (!instance) {
				instance = new cameraPhoto();
			}
			return instance;
		}
	};
})();
var po = photo.getInstance();
window.onload = function(){
	po.open();
}
		
		
		
