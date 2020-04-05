dwr.engine.setActiveReverseAjax(true);
dwr.engine.setNotifyServerOnPageUnload(true);
var instance;
photo = (function() {
	var _serverIP;
	//打开摄像头
	function  openCamera(serverIP,flag){
		_serverIP = serverIP
			$.ajax({type:"POST", cache:false,dataType: "json",
		        url: "http://" + serverIP + "/queue/number/openCamera.action",
		        success: function(data) {
		        
			        if(0==flag){//flag标记 0：摄像头 1:手写板
			           if(0 == data.isCamera){
			           		$("#openSxt").css("display","none");
			           		$("#showzp").html("<object id='jsrzP' classid='clsid:C3FF8650-C1AC-4E41-9474-6C27A58502D6' width='300' height='320' align='middle' CODEBASE='http://"+_serverIP+"/queue/plugs/camera/zdywzp.CAB#version=1.0.0.3'></object>");
							$("#pz").html("<INPUT id=btnCapture type=button value='拍照' name=btnCapture LANGUAGE=javascript onclick='btnCapture_onclick() 'style='width:100px; height:50px; ; ; text-align: center ; font-family:'Arial'; font-size: 13px; color:#000000; font-style:normal; font-weight:normal; text-decoration:none'>&nbsp;&nbsp;"+
						           		"<input name='subpic' type='button' value='保存照片' onclick='po.save(0)'style='width:100px; height:50px; ; ; text-align: center ; font-family:'Arial'; font-size: 13px; color:#000000; font-style:normal; font-weight:normal; text-decoration:none' >");
							 jsrzP.nW = 300;
							 jsrzP.nH = 320;
							 
							 if(false == jsrzP.OnCloseDev()){//关闭摄像头
									jsrzP.OnCloseDev();
							 }
							 var openres = jsrzP.OnContorlInit();
							 if(false == openres) {
							 	jsrzP.OnContorlInit();
							 }
						 }else{
						 	alert("未开启摄像功能，如有需要请联系管理员！！！");
						 	return;
						 }
						 
					}else if(1==flag){
					  	if(0 == data.isSignature){
					  		 //obj.HWInitialize();
							 $("#showSignature").css("display","");
							 $("#openSignature").css("display","none");
			  				 $("#showImg").css("display","none");
			  				 $("#SignatureSave").html("<INPUT id=btnCapture type=button value='初始化' name=btnCapture LANGUAGE=javascript onclick='openHW() 'style='width:100px; height:50px; ; ; text-align: center ; font-family:'Arial'; font-size: 13px; color:#000000; font-style:normal; font-weight:normal; text-decoration:none'>&nbsp;&nbsp;"+
						           		"<input name='subpic' type='button' value='保存' onclick='po.save(1)'style='width:100px; height:50px; ; ; text-align: center ; font-family:'Arial'; font-size: 13px; color:#000000; font-style:normal; font-weight:normal; text-decoration:none' >");
						 }else{
						 	alert("未开启手写板功能，如有需要请联系管理员！！！");
						 	return;
					 	}
					}else if(2==flag){
					  	//if(0 == data.isCamera){
			           		$("#openGpy").css("display","none");
			           		$("#showGpy").html("<OBJECT id='CmCaptureOcx' style='MARGIN-LEFT:5px; WIDTH: 300px; HEIGHT:320px' classid='clsid:3CA842C5-9B56-4329-A7CA-35CA77C7128D' ></OBJECT>");
							$("#gpy").html("<INPUT id=btnCapture type=button value='拍照' name=btnCapture LANGUAGE=javascript onclick='Capture() 'style='width:100px; height:50px; ; ; text-align: center ; font-family:'Arial'; font-size: 13px; color:#000000; font-style:normal; font-weight:normal; text-decoration:none'>&nbsp;&nbsp;"+
						           		"<input name='subpic' type='button' value='保存照片' onclick='po.save(2)'style='width:100px; height:50px; ; ; text-align: center ; font-family:'Arial'; font-size: 13px; color:#000000; font-style:normal; font-weight:normal; text-decoration:none' >");
							var index = CmCaptureOcx.Initial();
							//alert(index);
							if(index == -2)
							{
								alert("没有设备");
							}
							else if(index == -1)
							{
								alert("设备没有授权");
								index = 0;
							}
							CmCaptureOcx.StartRun(index);
							CmCaptureOcx.SetJpgQuanlity(100);
							CmCaptureOcx.SetResolution(2); 
						/*	 if(false == jsrzP.OnCloseDev()){//关闭摄像头
									jsrzP.OnCloseDev();
							 }
							 var openres = jsrzP.OnContorlInit();
							 if(false == openres) {
							 	jsrzP.OnContorlInit();
							 }
						 }else{
						 	alert("未开启摄像功能，如有需要请联系管理员！！！");
						 	return;
						 }*/
					}
				}
	        });
	}
	function subCameraPic(flag){//保存照片
			if(0 == flag){//flag标记 0：摄像头 1:手写板
				if(null == $("#base64Code").val() || "" == $("#base64Code").val()){
					alert("请拍照！！！");
					return;
				}
				var params = 'clientIp=' + $("#clientIp").val() +'&flag=' +flag+ '&photoBase64=' + encodeURIComponent($("#base64Code").val());
					$.ajax({type: "POST", cache: false,dataType: "json",async: false,data: params,
							url:"http://" + _serverIP + "/queue/number/savePhoto.action",
							success:function (picres) {
								if(0 == picres){
									alert("照片保存成功!!!");
									//----拍照后隐藏控件  begin
									$("#zpjg").html("<img src='images/u13.png' alt='' width='77' height='77'>");
									$("#pz").html("");
									$("#base64Code").val("");
									$("#openSxt").css("display","");
									if(false == jsrzP.OnCloseDev()){//关闭摄像头
										jsrzP.OnCloseDev();
									}
									$("#showzp").html("<img src='images/u13.png' width='420' height='420'>");
									//----拍照后隐藏控件   end
								}else if(1 == picres){
									alert("照片保存失败!!!");
									return;
								}else if(2 == picres){
									alert("照片上传六合一失败!!!");
									return;
								}else if(3 == picres){
									alert("未叫号，不能拍照！！！");
									$("#zpjg").html("<img src='images/u13.png' alt='' width='77' height='77'>");
									$("#pz").html("");
									$("#base64Code").val("");
									$("#openSxt").css("display","");
									if(false == jsrzP.OnCloseDev()){//关闭摄像头
										jsrzP.OnCloseDev();
									}
									$("#showzp").html("<img src='images/u13.png' width='420' height='420'>");
									return;
								}
							}
					});
			}else if (1 == flag){
				//手写板
					if("" == encodeURIComponent(obj.HWGetBase64Stream(2))){
						 if(confirm("签名为空，是否继续保存！！！")){}
						 else{
						 	$("#SignatureSave").html("");
						 	$("#openSignature").css("display","");
						 	$("#showSignature").css("display","none");
		  					$("#showImg").css("display","");
		  					obj.HWFinalize();//关闭手写板
		  					obj.HWClearPenSign();
						 	return;
						 }
					}
					var params = 'clientIp=' + $("#clientIp").val() +'&flag=' +flag+ '&photoBase64=' + encodeURIComponent(obj.HWGetBase64Stream(2));
					$.ajax({type: "POST", cache: false,dataType: "json",async: false,data: params,
							url:"http://" + _serverIP + "/queue/number/savePhoto.action",
							success:function (picres) {
								if(0 == picres){
									alert("图片保存成功!!!");
									$("#SignatureSave").html("");
									$("#openSignature").css("display","");
									$("#showSignature").css("display","none");
		  				 			$("#showImg").css("display","");
		  				 			obj.HWFinalize();//关闭手写板
		  				 			obj.HWClearPenSign();
								}else if(1 == picres){
									alert("图片保存失败!!!");
									obj.HWFinalize();//关闭手写板
									obj.HWClearPenSign();
									return;
								}else if(2 == picres){
									alert("图片上传六合一失败!!!");
									obj.HWFinalize();//关闭手写板
									obj.HWClearPenSign();
									return;
								}else if(3 == picres){
									alert("未叫号，不能保存签名！！！");
									$("#SignatureSave").html("");
									$("#openSignature").css("display","");
									$("#showSignature").css("display","none");
		  				 			$("#showImg").css("display","");
		  				 			obj.HWFinalize();//关闭手写板
									obj.HWClearPenSign();
									return;
								}
							}
					});
				}else if (2 == flag){
				//高拍仪
					var gpybase64 = CmCaptureOcx.Base64Encode("C:\\333.jpg");
					if("" == gpybase64){
						 if(confirm("图片信息为空，是否继续保存！！！")){}
						 else{
						 	$("#gpyjg").html("<img src='images/u13.png' alt='' width='77' height='77'>");
							$("#gpy").html("");
							$("#openGpy").css("display","");
							$("#showGpy").html("<img src='images/u13.png' width='420' height='420'>");
							CmCaptureOcx.Destory();//关闭摄像头
						 	return;
						 }
					}
					var params = 'clientIp=' + $("#clientIp").val() +'&flag=' +flag+ '&photoBase64=' + encodeURIComponent(gpybase64)+'&zllx='+$("#zllx").val();
					$.ajax({type: "POST", cache: false,dataType: "json",async: false,data: params,
							url:"http://" + _serverIP + "/queue/number/savePhoto.action",
							success:function (picres) {
								if(0 == picres){
									alert("图片保存成功!!!");
									CmCaptureOcx.Destory();//关闭摄像头
									$("#gpyjg").html("<img src='images/u13.png' alt='' width='77' height='77'>");
									$("#gpy").html("");
									$("#openGpy").css("display","");
									$("#showGpy").html("<img src='images/u13.png' width='420' height='420'>");
		  				 			CmCaptureOcx.DeleteFile("C:\\333.jpg");
								}else if(1 == picres){
									alert("图片保存失败!!!");
									CmCaptureOcx.Destory();
									CmCaptureOcx.DeleteFile("C:\\333.jpg");
									return;
								}else if(2 == picres){
									alert("图片上传六合一失败!!!");
									CmCaptureOcx.Destory();
									CmCaptureOcx.DeleteFile("C:\\333.jpg");
									return;
								}else if(3 == picres){
									alert("未叫号，不能保存信息！！！");
									CmCaptureOcx.Destory();
									CmCaptureOcx.DeleteFile("C:\\333.jpg");
									$("#gpyjg").html("<img src='images/u13.png' alt='' width='77' height='77'>");
									$("#gpy").html("");
									$("#openGpy").css("display","");
									$("#showGpy").html("<img src='images/u13.png' width='420' height='420'>");
									return;
								}
							}
					});
				}
	}
	function cameraPhoto (){
		this.open = function(serverIP,flag) {
			openCamera(serverIP,flag);
		}
		this.save = function(flag) {
			subCameraPic(flag);
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
			$("#showSignature").html("<object id='HWPenSign' name='HWPenSign' classid='clsid:E8F5278C-0C72-4561-8F7E-CCBC3E48C2E3' width='300' height='320'></object>");
            obj = document.getElementById("HWPenSign"); 
            obj.HWSetBkColor(0xE0F8E0);
		  	obj.HWSetCtlFrame(2, 0x000000);
		  	$("#showSignature").css("display","none");
		  	$("#showImg").css("display","");
}
function openHW() {
  obj.HWInitialize();
  obj.HWClearPenSign();
}
var setTimeoutReturn;
function showMessage(req){
	$("#blink").html("<a style='margin-left:90px;font-size:50px' >"+req.msg+"</a>");
	setInterval(fn, 1000);
	window.onblur = function (){
		window.focus();
		setTimeout(closeblink, 15000);
	};
}
function Capture(){
		CmCaptureOcx.SetFileType(1);
		var strFile = "c:\\333.jpg";
		//alert(strFile);
		CmCaptureOcx.SetJpgQuanlity(10);
		CmCaptureOcx.CaptureImage(strFile);
		$("#gpyjg").html("<img name='zp' style='width:150px;height:170px;' src='"+strFile+"'/>");
}


		
		
