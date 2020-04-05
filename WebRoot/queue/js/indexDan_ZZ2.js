﻿document.oncontextmenu = function() { return false; } //禁止右键功能,单击右键将无任何反应
document.onselectstart = function() { return false; } //禁止选择,也就是无法复制
window.onhelp = function() { return false; } //屏蔽F1


function isEmpty(val) {
    return "undefined" == typeof(val) || "" == val || null == val;
}
jQuery.ajaxSetup ({cache:false}) ;
GenerateNumber = (function() {
    var instance;
    var bsnsTypes; //缓存业务类型
    var agentfalg;//判断是不是读卡器取号
    var isAgen=0;
    var banliTypes=0,banliTypesVo=0;//1为代办人
    var iscywf = 1,cywffs=1;//是否查验违法
    var isOpenTztd=1;
    var cfqhbj=0;//重复取号标记
    var qzbj = 0;//缺纸标记0正常 1警告 2禁止
    var tdtzjdcnum="",tdtzjdctype="";
    var count = 1,personType = 1;
    var objNUM = "", idN="";
    var IDType, IDNumber,IDNumberB="",NameA,nameB,hmdbj=0,Unit="",waitingarea=""; //证件类型及证件号码
    var mainType = "<li><a href='javascript:void(0);' class='btn_ywlx' onfocus='this.blur()' onclick='gn.showChildType(\"01\")'>机动车<a></li>"
    	+ "<li><a href='javascript:void(0);' class='btn_ywlx' onfocus='this.blur()' onclick='gn.showChildType(\"02\")'>驾驶人<a></li>"
    	+ "<li><a href='javascript:void(0);' class='btn_ywlx' onfocus='this.blur()' onclick='gn.showChildType(\"04\")'>违法<a></li>";
	
    var jdctype = "<div id='jdctype' style='z-index:-1;position:absolute;left:110px;top:200px;'><table width='100%' height='200' border='1' style='background-color:#7AB4E2' cellpadding='0' cellspacing='0'><tr><td>号牌种类:</td><td><div id='hpzl'></div></td><td>号牌号码:</td><td><div id='hphm'></div></td></tr>"
    				+"<tr><td>所有人:</td><td><div id='syr'></div></td><td>状态:</td><td><div id='zt'></div></td></tr>"
    				+"<tr><td>抵押标记:</td><td><div id='dybj'></div></td><td>有效期止:</td><td><div id='yxqz'></div></td></tr>"
    				+"<tr><td>强制报废期止:</td><td><div id='qzbfqz'></div></td><td>登记证书编号:</td><td><div id='djzsbh'></div></td></tr>"
    				+"</table><input type='button' class='button' value='下一步' onclick='gn.checkOk(\"01\")'/></div>";
    
    var jsrtype = "<div id='jsrtype' style='z-index:-1;position:absolute;left:110px;top:200px;'><table width='100%' height='200' border='1' style='background-color:#7AB4E2' cellpadding='0' cellspacing='0'><tr><td>姓名:</td><td><div id='xm'></div></td><td>准驾车型:</td><td><div id='zjcx'></div></td></tr>"
    				+"<tr><td>状态:</td><td><div id='zt'></div></td><td>累计积分:</td><td><div id='ljjf'></div></td></tr>"
    				+"<tr><td>有效期止:</td><td><div id='yxqz'></div></td><td>审验有效期止:</td><td><div id='syyxqz'></div></td></tr>"
    				+"</table><input type='button' class='button' value='下一步' onclick='gn.checkOk(\"02\")'/></div>";
    
    var jdcxinxi = "<div class='inner_fsfz' id='jdctype'><p style='color: red;font-size: 30px;'>请输入查验机动车信息</p>"
						+"<select class='sel1' id='jdcsel'>"
						+"<option value=''>-车辆类型-</option>"
						+"<option value='01'>大型汽车</option>"
						+"<option value='02' selected='selected'>小型汽车</option>"
						+"<option value='15'>挂车</option>"
						+"<option value='16'>教练汽车</option>"
						+"<option value='23'>警用汽车</option></select>"
						+"<p><input id='jdcnum' class='input1' onfocus='jdchqjp()'/></p>"
						+"<p><input type='button' value='查询' onclick='jdcbtnOk()'/><img src='images/queue/ok.jpg' style='border: 0px; cursor: pointer;' onclick='jdcbtnOk()' />&nbsp;&nbsp;"
				    	+"<input type='button' value='返回' onclick='jdcbtnCancel()'/><img src='images/queue/cancel.jpg' style='border: 0px; cursor: pointer;' onclick='jdcbtnCancel()' /></p></div>";
    
     var tdtzxinxi = "<div class='inner_fsfz' id='jdctype'><p style='color: red;font-size: 30px;'>请输入提档机动车信息</p>"
						+"<select class='sel1' id='tztdjdcsel'>"
						+"<option value=''>-车辆类型-</option>"
						+"<option value='01'>大型汽车</option>"
						+"<option value='02' selected='selected'>小型汽车</option>"
						+"<option value='15'>挂车</option>"
						+"<option value='16'>教练汽车</option>"
						+"<option value='23'>警用汽车</option></select>"
						+"<p><input id='tztdjdcnum' class='input1' onfocus='tztdhqjp()'/></p>"
						+"<p><input type='button' value='提交' onclick='tdtzbtnOk()'/><img src='images/queue/ok.jpg' style='border: 0px; cursor: pointer;' onclick='tdtzbtnOk()'/>&nbsp;&nbsp;"
				    	+"<input type='button' value='返回' onclick='tdtzbtnCancel()'/><img src='images/queue/cancel.jpg' style='border: 0px; cursor: pointer;' onclick='tdtzbtnCancel()' /></p></div>";
  
    
    
    
    
	/*隐藏证件类型*/
	function hidden() {
	    $("#inner_fsfz").css("display", "none");
		$("#tIDType").val("");
	}
	
	/*
	 * 设置证件类型及证件号码并显示业务类型界面
	 * @param type 证件类型
	 * @param number 证件号码
	 */
	function displayType(type, number,NameA,waitingarea) {
	    IDType = type;
	    IDNumber = number;
	    
	    var isSuccess = true;
	    var isChange = false;
	 //   if (isEmpty(bsnsTypes)) {
	        $.ajax({type: "POST", cache: false, async: false, dataType: "json",
		        url: 'number/bsnsType.action?'+parseInt(Math.random()*100000),
		        success: function(request) {
	        		iscywf = request.iscywf;
	        		cywffs = request.cywffs;
	        		isOpenTztd = request.isOpenTztd;
		            if (request.isSuccess) {
		                isChange = true;
		                delete request.isSuccess;
						bsnsTypes = request;
					} else {
						
						//显示身份证取号界面
						hidden();
						$("#type").css("display", "none");
						$("#index").css("display", "");
						$("#backDiv").css("z-index", "-1");
						isSuccess = request.isSuccess;
						window.alert(request.error);
					}
		        },
		        error: function() {
		            isSuccess = false;
		            window.alert("连接服务器失败，请稍候再试");
		        }
		    });
	  //  }
	    if (!isSuccess) { return; }
	    if (isChange) {
//		    $("#tdiv").empty().html("<div class='tab'>" + mainType + "</div>").css("padding-left", "70px");
	    	gn.showChildType('01');
	    }
	    $("#index").css("display", "none");
	  	$("#type").css("display", "");
	}
	
	 //显示二级菜单
	function showTwoType(flag){
		if(iscywf=='0'){
			if(cywffs=='0'){//人工查验
				/*显示违法信息*/
				if(flag=='01'){
				var objDiv = $("#tdiv").empty();
				objDiv.html(jdcxinxi);
				this.jdcbtnOk = function(){
					var jdctypes = $("#jdcsel").val();
				    var jdcnum = $("#jdcnum").val();
					$("#jdcnum").val().replace(/[^A-Za-z0-9_]/g, "");
					objDiv.html(jdctype);
			        objDiv.css("padding-left", "70px");
					 $.ajax({type: "POST", cache: false, async: false, dataType: "json",
					        url: 'number/checkStatus.action?flag='+flag+"&chepainum="+jdcnum+"&jdctypes="+jdctypes,
					        success: function(data) {
						 	if(data.success){
					                $("#hpzl").html(data.hpzl);
					                $("#hphm").html(data.hphm);
					                $("#syr").html(data.syr);
					                $("#zt").html(data.zt);
					                $("#dybj").html(data.dybj);
					                $("#yxqz").html(data.yxqz);
					                $("#qzbfqz").html(data.qzbfqz);
					                $("#djzsbh").html(data.djzsbh);
							 	}else{
							 		check(flag);
							 	}
					        },
					        error: function() {
					            isSuccess = false;
					            window.alert("连接服务器失败，请稍候再试");
					        }
					    });
				    
					}
					this.jdchqjp = function(){
						$("#jdcnum").alxckeyboard();
					}
				
					this.jdcbtnCancel = function(){
						check(flag);
					}
				
				}else if(flag=='02'){
					var objDiv = $("#tdiv").empty();
					objDiv.html(jsrtype);
		        	objDiv.css("padding-left", "70px");
				 	$.ajax({type: "POST", cache: false, async: false, dataType: "json",
				        url: 'number/checkStatus.action?flag='+flag+"&IDNumber="+IDNumber,
				        success: function(data) {
					 	if(data.success){
				                $("#xm").html(data.xm);
				                $("#zjcx").html(data.zjcx);
				                $("#zt").html(data.zt);
				                $("#ljjf").html(data.ljjf);
				                $("#yxqz").html(data.yxqz);
				                $("#syyxqz").html(data.syyxqz);
						 	}else{
						 		check(flag);
						 	}
				        },
				        error: function() {
				            isSuccess = false;
				            window.alert("连接服务器失败，请稍候再试");
				        }
				    });
					
				}else{
					check(flag);
				}
				}else{//自动查验
					if(flag=='01'){
				var objDiv = $("#tdiv").empty();
				objDiv.html(jdcxinxi);
				this.jdcbtnOk = function(){
					var jdctypes = $("#jdcsel").val();
				    var jdcnum = $("#jdcnum").val();
					$("#jdcnum").val().replace(/[^A-Za-z0-9_]/g, "");
					 $.ajax({type: "POST", cache: false, async: false, dataType: "json",
					        url: 'number/checkStatusZD.action?flag='+flag+"&chepainum="+jdcnum+"&jdctypes="+jdctypes,
					        success: function(data) {
						 	if(data.success){
							 		check(flag);
							 	}else{
							 		alert("所办理机动车状态为:"+data.zt +"\n 不允许办理相关业务!");
							 		gn.returnindex();
							 	}
					        },
					        error: function() {
					            isSuccess = false;
					            window.alert("连接服务器失败，请稍候再试");
					        }
					    });
				    
					}
					this.jdchqjp = function(){
						$("#jdcnum").alxckeyboard();
					}
				
					this.jdcbtnCancel = function(){
						check(flag);
					}
				
				}else if(flag=='02'){
				 	$.ajax({type: "POST", cache: false, async: false, dataType: "json",
				        url: 'number/checkStatusZD.action?flag='+flag+"&IDNumber="+IDNumber,
				        success: function(data) {
					 		if(data.success){
						 		check(flag);
						 	}else{
						 		alert("所办理驾驶人状态为:"+data.zt +"\n 不允许办理相关业务!");
						 		gn.returnindex();
						 	}
				        },
				        error: function() {
				            isSuccess = false;
				            window.alert("连接服务器失败，请稍候再试");
				        }
				    });
					
				}else{
					check(flag);
				}
				}
		}else{
			check(flag);
		}
		
//		var index = 0 ,newLi="", content1 = "" , content2 = "" , bj=0;
		
//		$.each(bsnsTypes, function(key, obj) {
//			if(!isEmpty(obj.twotype) && obj.twotype == flag){
//				var names = obj.name.split("@");
//					//isOpenTztd 是否开启通知提档 0开启 1关闭
//					if("0"==obj.isOpenTztd){
//         				newLi = "<li><a href='javascript:void(0);' class='btn_ywlx' onfocus='this.blur()' onclick='gn.checkCl(\""+key+"\",\""+obj.id+"\",\""+flag+"\",\""+obj.isOpenZhiWen+"\")'>"+names[0]+" "+names[1]+"<a></li>";
//	         		}else{
//	         			newLi = "<li><a href='javascript:void(0);' class='btn_ywlx' onfocus='this.blur()' onclick='gn.saveZhiWen(\""+key+"\",\""+obj.id+"\",\""+flag+"\",\""+obj.isOpenZhiWen+"\")'>"+names[0]+" "+names[1]+"<a></li>";
//	         		}
//	         		if(4 >= index){content1 += newLi;} 
//	         		if(10 >= index && 5 <= index){content2 += newLi;}
//         			index ++;
//         			bj=1;
//         		}
//	     });
	
//	   if(bj == 0){
//	   		alert("没有可办理业务");
//	   		gn.returns();
//	   }else{
//	   		var divBack ="<table border='0' cellspacing='0' cellpadding='0' class='tab_nav'><tr>"+
//                    "<td><a class='back' onclick='gn.returns()' onfocus='this.blur()'>返回</a></td></tr></table>"
//	   		if (5 >= index) {
//		        objDiv.html("<div class='tab'>" + content1 + "</div>");
//		    } else {
//		        var newDiv1 = "<div class='tab tdiv'>" + content1 + "</div>";
//		        var newDiv2 = "<div class='tab tdiv'>" + content2 + "</div>";
//		        objDiv.html(newDiv1 + newDiv2);
//		        objDiv.css("padding-left", "70px");
//		    }
//		    //返回按钮显示
//		    $("#backDiv").empty().html(divBack);
//		    $("#backDiv").css("z-index", "");
//	   }
		    
	}
   
	function check(flag){
		var objDiv = $("#tdiv").empty();
		var index = 0 ,newLi="", content1 = "" , content2 = "" , bj=0;
		$.each(bsnsTypes, function(key, obj) {
			if(!isEmpty(obj.twotype)){
				 flag = obj.twotype;
				var names = obj.name.split("@");
					//isOpenTztd 是否开启通知提档 0开启 1关闭
					if("0"==obj.isOpenTztd){
         				newLi = "<li><a href='javascript:void(0);' class='btn_ywlx' onfocus='this.blur()' onclick='gn.checkCl(\""+key+"\",\""+obj.id+"\",\""+flag+"\",\""+obj.isOpenZhiWen+"\")'>"+names[0]+" "+names[1]+"<a></li>";
	         		}else{
	         			newLi = "<li><a href='javascript:void(0);' class='btn_ywlx' onfocus='this.blur()' onclick='gn.saveZhiWen(\""+key+"\",\""+obj.id+"\",\""+flag+"\",\""+obj.isOpenZhiWen+"\")'>"+names[0]+" "+names[1]+"<a></li>";
	         		}
	         		if(4 >= index){content1 += newLi;} 
	         		if(10 >= index && 5 <= index){content2 += newLi;}
         			index ++;
         			bj=1;
				}
	     });
		
		if(bj == 0){
	   		alert("没有可办理业务");
	   		gn.returns();
	   }else{
	   		var divBack ="<table border='0' cellspacing='0' cellpadding='0' class='tab_nav'><tr>"+
                    "<td><a class='back' onclick='gn.returnindex()' onfocus='this.blur()'>返回</a></td></tr></table>"
	   		if (5 >= index) {
		        objDiv.html("<div class='tab'>" + content1 + "</div>");
		    } else {
		        var newDiv1 = "<div class='tab tdiv'>" + content1 + "</div>";
		        var newDiv2 = "<div class='tab tdiv'>" + content2 + "</div>";
		        objDiv.html(newDiv1 + newDiv2);
		        objDiv.css("padding-left", "70px");
		    }
		    //返回按钮显示
		    $("#backDiv").empty().html(divBack);
		    $("#backDiv").css("z-index", "");
	   }
		
	}
	
	
	
	
    function constructor() {
        var agentInfo; //缓存代理人信息
	    var credentials; //缓存证件类型
         var readSysCard =SynCardOcx1;// IDCard1;//读卡器类型 0华视 1华旭
       
         
         /*本人办理*/
         this.benren = function(){
         	 //初始化参数  
			 banliTypesVo = 0;
			 isAgen=0;Unit="";IDType="";IDNumber="";IDNumberB="",NameA="";nameB="";hmdbj=0;waitingarea="";
        	 banliTypes=0;
        	 gn.banLi();
        	 showBackBtn();
        	 //验证缺纸
			 gn.yzquez();
			 if(qzbj==1){
				 alert("缺纸警告！");
			 }else if(qzbj==2){
				 alert("请更换打印纸！");
				 gn.returnindex();
			 }
         };
         
         /*代办人办理*/
         this.daibanren = function(){
         	 //初始化参数  
			 banliTypesVo = 0;
			 isAgen=0;Unit="";IDType="";IDNumber="";IDNumberB="",NameA="";nameB="";hmdbj=0;waitingarea="";
        	 banliTypes=1;
        	 gn.banLi();
        	 showBackBtn();
        	 //验证缺纸
			 gn.yzquez();
			 if(qzbj==1){
				 alert("缺纸警告！");
			 }else if(qzbj==2){
				 alert("请更换打印纸！");
				 gn.returnindex();
			 }
         };
         
         
         
        /*身份证取号*/
        this.card = function() {
        	agentfalg=1;
        	document.getElementById("msg").innerHTML ="";
		  	//判断用的什么类型的读卡器
			$.ajax({type: "POST", cache: false,data: "9", async: false, dataType: "text",
			        url: 'system/getSystemById.action?'+parseInt(Math.random()*100000),
			        success: function(data) {
			            if (data != null || data !="") {
							//判断读卡器类型
							if(data == 1){
								readSysCard = SynCardOcx2;
							}
							var ts = new Date(); 
							var ss = parseInt(ts / 1000 % 60, 10)+4;
							var timer= window.setInterval(function(){
//								$("#msg").css("display", "");
								document.getElementById("msg").innerHTML = "请将二代身份证放置识读区"+"<span style='color:#bf0202;font-size:45px;margin:0 10px;'>"+(ss-(parseInt(new Date() / 1000 % 60, 10)))+"</span>";
							 },1000);
							var nRet = "";
							window.setTimeout(function(){
								 clearInterval(timer);
								 document.getElementById("msg").innerHTML ="";
								 
								if (0 >= readSysCard.FindReader()) {
									clearInterval(timer);
							  		window.alert("没有找到读卡器");
							  		return;
							  	}
							  //	var nRet = readSysCard.Syn_ReadMsg(1);//读卡
							//	readSysCard.GetSAMID(); //读卡器SAMID
							  //	readSysCard.SetReadType(0); //读卡
							  	nRet = readSysCard.ReadCardMsg();
							  	var tempCapt ="";
							  	if (0 == nRet) {
							  	    var number = readSysCard.CardNo;
								    NameA = readSysCard.NameA;
								   // var number = readSysCard.IDCardNo;
								  //  NameA = readSysCard.PeopleName;
								  //  IDCard1.StrToJpg(readSysCard.PhotoStr);
								    SynCardOcx1.SetPhotoType(2);//用base64
								    upIcardPic(readSysCard.Base64Photo,number);//获取身份证照片显示
								 //验证黑名单
								 gn.validateBlack(number);
								 if(hmdbj == 1){
									alert("此人已在黑名单中存在，无法取号！！！");
									hmdbj = 0;
									return;
								}
								 //验证重复取号
								 gn.checkcfqh(number);
								 if(cfqhbj==1){
									 alert("您今天取的号尚未办结，请勿重复取号!");
									 cfqhbj=0;
									 gn.returnindex();
									 return;
								 }
								 //判断代办人
								 if(banliTypes==0){
									gn.returns();
						  	        showBackBtn();
	//								agentfalg=1;
	//								isAgen = 0;
						  	        displayType("A", number,NameA); 
								 }else{
									 IDNumberB = number;
									 nameB = "";
								 	nameB = NameA;
							  	//判断是不是代理人
							  	$.post("number/searchAgent.action?"+parseInt(Math.random()*100000), {IDNumber: number}, function(data) {
							  	        if (data.isAgnet) {
								  	        agentInfo = data;
	//							  	        gn.returns();
								  	        Unit = agentInfo.nuit;//获取单位
								  	        isAgen = 1;//判断是不是代理人
	//							  	        agentfalg=1;//判断是否为身份证读卡器取号
								  	       //判断是否启用静脉
								  	       if("0" == data.isOpenJm){
								  	            window.alert("请将指定的手指放入指静脉仪进行身份验证！");
								  	          //  tempCapt = xhWebFingerCtrl.OnCaptureFinger();
												var stat = xhWebFingerCtrl.OnIdentifyFinger(agentInfo.fingerprint);
										    	if (0 == stat && "" != tempCapt ) {
	//									    		gn.returns();
	//									    		showBackBtn();
	//												agentfalg=1;
	//									    	    displayType("A", agentInfo.IDNumber,NameA); 
										    		banliTypesVo = banliTypes;
													banliTypes = 0;
													hidden();
													document.getElementById("msg").innerHTML ="请读取车主身份证！";
												  } else { 
													  gn.returnindex();
													  agentInfo = "";
												  }
											 }else{
	//										 	displayType("A", agentInfo.IDNumber);
												 banliTypesVo = banliTypes;
												banliTypes = 0;
												hidden();
												document.getElementById("msg").innerHTML ="请读取车主身份证！";
											 }
							  	 		 } else { 
	//								  	        gn.returns();
	//								  	        showBackBtn();
	//											agentfalg=1;
												isAgen = 0;
	//								  	        displayType("A", number,NameA); 
												banliTypesVo = banliTypes;
												banliTypes = 0;
												hidden();
												document.getElementById("msg").innerHTML ="请读取车主身份证！";
							  	  		}
							  	}, "json");
							  	}
							} else {
								clearInterval(timer);
								window.alert("未放置身份证或身份无效"); 
							
								}
							},3000);
							
					} else {
						window.alert("读卡器类型错误");
					}
			        },
			        error: function() {
			            isSuccess = false;
			            window.alert("连接服务器失败，请稍候再试");
			        }
			        
			 });
		
        };
        
        /*非身份证取号,显示证件类型*/
		this.noCard = function() {
		    agentfalg=0;
		    var isSuccess = true;
		    if (isEmpty(credentials)) {
		        $.ajax({type: "POST", cache: false, async: false, dataType: "json",
			        url: 'number/credentials.action?'+parseInt(Math.random()*100000),
			        success: function(request) {
			            if (request.isSuccess) {
							credentials = request.datas;
						} else {
							isSuccess = request.isSuccess;
							window.alert(request.error);
						}
			        },
			        error: function() {
			            isSuccess = false;
			            window.alert("连接服务器失败，请稍候再试");
			        }
			    });
		    }
		    
		    if (!isSuccess) { return; }
		    
		    $("#tIDNumber").val("");
		    var oSelect = $("#tIDType").empty();
			oSelect.append($("<option>", {value: ""}).html("-证件类型-"));
	        $.each(credentials, function(i, obj) {
	            var oOpt = $("<option>", {value: obj.dm}).html(obj.mc);
	            oSelect.append(oOpt);
	        });
	        $("#inner_fsfz").css("display", "");
		};
		
		/*调用键盘*/
		this.hqjp = function(){
			$("#tIDNumber").alxckeyboard();
		};
		
		/*获取证件类型及证件号码*/
		this.btnOk = function() {
		    var type = $("#tIDType").val();
		    var number = $("#tIDNumber").val();
		     document.getElementById("msg").innerHTML ="";
		    if (isEmpty(type) || isEmpty(number)) {
		        window.alert("证件类型及证件号码不能为空，\n请正确填写信息");
		        return;
		    }
		    
		    //验证黑名单----begin
						gn.validateBlack(number);
						if(hmdbj == 1){
							alert("此人已在黑名单中存在，无法取号！！！");
							hmdbj = 0;
							return;
						}
						//验证重复取号
						 gn.checkcfqh(number);
						 if(cfqhbj==1){
							 alert("您今天取的号尚未办结，请勿重复取号!");
							 cfqhbj=0;
							 gn.returnindex();
							 return;
						 }
						//-------------end
		    
			 //判断代办人
				 if(banliTypes==0){
					 hidden();
					gn.returns();
		  	        showBackBtn();
//					agentfalg=1;
//					isAgen = 0;
		  	        displayType("A", number,NameA); 
				 }else{
					 IDNumberB = number;
					 //代办人
					 if (type == "A" || type == "M") {
						var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
						if (!reg.test(number)) {
			       			alert("身份证输入不合法");  
			       			return;
						}
						
						//判断是不是代理人
			  	$.post("number/searchAgent.action?"+parseInt(Math.random()*100000), {IDNumber: IDNumberB}, function(data) {
			  	        if (data.isAgnet) {
				  	        agentInfo = data;
//							  	        gn.returns();
				  	        Unit = agentInfo.nuit;//获取单位
				  	        isAgen = 1;//判断是不是代理人
//			  	        agentfalg=1;//判断是否为身份证读卡器取号
				  	      //判断是否启用静脉
				  	       if("0" == data.isOpenJm){
				  	            window.alert("请将指定的手指放入指静脉仪进行身份验证！");
				  	           // tempCapt = xhWebFingerCtrl.OnCaptureFinger();
								var stat = xhWebFingerCtrl.OnIdentifyFinger(agentInfo.fingerprint);
						    	if (0 == stat && "" != tempCapt ) {
//									    		gn.returns();
//									    		showBackBtn();
//									agentfalg=1;
//									    	    displayType("A", agentInfo.IDNumber,NameA); 
						    		banliTypesVo = banliTypes;
									banliTypes = 0;
									hidden();
									document.getElementById("msg").innerHTML ="请读取车主身份证！";
								  } else { 
									  gn.returnindex();
									  agentInfo = "";
								  }
							 }else{
//										 	displayType("A", agentInfo.IDNumber);
								 banliTypesVo = banliTypes;
								banliTypes = 0;
								hidden();
								document.getElementById("msg").innerHTML ="请读取车主身份证！";
							 }
			  	 		 } else { 
//								  	        gn.returns();
//								  	        showBackBtn();
//								agentfalg=1;
								isAgen = 0;
//								  	        displayType("A", number,NameA); 
								banliTypesVo = banliTypes;
								banliTypes = 0;
								hidden();
								document.getElementById("msg").innerHTML ="请读取车主身份证！";
			  	  		}
			  	}, "json");
						
						
						
					  } else {
						  banliTypesVo = banliTypes;
							banliTypes = 0;
							hidden();
							document.getElementById("msg").innerHTML ="请读取车主身份证！";
					  }
						
				 }
//		    gn.returns();
//		  	showBackBtn();
		};
		
		/*隐藏证件类型*/
		this.btnCancel = function() {
			hidden();
		};
		
		/*
		 * 取号
		 * @param id 业务类型编号
		 */
		 var isCountSet=0;
			this.createNumber = function(id) {
	
				//查询身份证的未办理业务
				 $.ajax({type: "POST", cache: false, async: false, dataType: "json",
				        url: "number/wclh.action?IDNumber="+IDNumber+"&"+parseInt(Math.random()*100000),
				        success: function(req) {
					 if(req.status==2){
						if(confirm(req.msg)){
							qhao(id);
						}else{
						//返回按钮隐藏
				  		 $("#backDiv").css("z-index", "-1");
				  		  agentInfo="";
				  		  gn.returnindex();
				  		 //window.location.reload();
					    }
					 }else{
						 qhao(id);
					 }
		 },
		        error: function() {
		            isSuccess = false;
		            window.alert("连接服务器失败，请稍候再试");
		        }
		    });
		};		
		
		function qhao(id){

			idN=id;
			//显示身份证取号界面
			hidden();
			$("#type").css("display", "none");
		    objNUM = eval("bsnsTypes." + id);
		    //业务笔数及人员类型
			count = 1, personType = 1;
		    //判断代理人可办的业务及办理笔数
		    if (!isEmpty(agentInfo) && agentInfo.isAgnet) {
			$("#indexYWBS").css("display", "");
		    	
		    	$("#tNumber").val("1");
		        $("#inner_ywsl").css("display", "");
		    	this.srbs = function(){
		       		$("#tNumber").alxckeyboard();
					}
		    }else{
		    	$("#tNumber").val("1");
		    	gn.bnumOk();
		    }
//		    if(isCountSet == 1){
//		     	//返回按钮隐藏
//	  		 	$("#backDiv").css("z-index", "-1");
//		    	return;
//		    }
		
		}
		this.bnumCancel = function(){
			$("#indexYWBS").css("display", "none");
			$("#inner_ywsl").css("display", "none");
			gn.returnindex();
		};
		this.bnumOk = function(){
			$("#index").css("display", "");
			$("#indexYWBS").css("display", "none");
			$("#inner_ywsl").css("display", "none");
			 	count = $("#tNumber").val();
			    if(isNaN(count)){
			    	alert("请输入数字");
			    	gn.returnindex();
			    	return;
			    } else if(count == null){
			    	count = 1;
			    }else if (count >10){
			    	alert("此业务每次最多办理10笔!");
			    	gn.returnindex();
			    	return;
			    }
			    
			     if (!isEmpty(agentInfo) && agentInfo.isAgnet) {
				    //判断取号次数
			    	 if(banliTypesVo==1){
			    		 gn.numberCount(IDNumberB,0,idN.substring(1),agentInfo.agentId,count);
			    	 }else{
					    gn.numberCount(IDNumber,0,idN.substring(1),agentInfo.agentId,count);
			    	 }
				    personType = 2; //代理人
			     }else{
			    	 if(banliTypesVo==1){
			    		 gn.numberCount(IDNumberB,1,idN.substring(1),"",count);//判断取号次数
			    	 }else{
				    	 gn.numberCount(IDNumber,1,idN.substring(1),"",count);//判断取号次数
			    	 }
			     }
			     
			    if(isCountSet == 1){
			     	agentInfo="";
			     	//返回按钮隐藏
		  		 	$("#backDiv").css("z-index", "-1");
			    	return;
		    	}
		    if(!isEmpty(count) && count != 0){
		    	var params = {businessType:objNUM.id, IDType:IDType, IDNumber:IDNumber,IDNumberB:IDNumberB, businessCount:count,
					personType:personType, prefix:objNUM.prefix, code:objNUM.code, typeName:objNUM.name, flag:objNUM.flag};
				$.post("number/generateNumber.action?"+parseInt(Math.random()*100000), params, function (data, status) {
					if (data.isSuccess) {
						IDNumberB="";//清空IDNumberB
						if("2" == data.isOpenIndexCamera){//判断是否开启取号拍照功能 开启修改值为0
							document.getElementById("sxh").value = data.deptCode + data.deptHall + data.serialNum;//给子窗口传值（取号拍照窗口）
							document.getElementById("sxhid").value = data.id;//给子窗口传值（取号拍照窗口）
							var features = "status=no,resizable=yes,top=0,left=0,scrollbars=no," 
					        + "titlebar=no,menubar=no,location=no,toolbar=no,z-look=yes," 
					        + "width=600,height=600";
							var newWin = window.open("index_pz.jsp", "_blank", features);
						}
						//大厅等待人数
						$('#showWaitRs').html("<font size='5'>大厅等待人数:"+data.countAll+"人</br></font>");
						var vToday = new Date();
						var vYear = vToday.getFullYear();
						var vMon = vToday.getMonth() + 1;
						var vDay = vToday.getDate();
						var vHours = vToday.getHours();
						var vMinutes = vToday.getMinutes();
						var vSeconds = vToday.getSeconds();
						var vDate = vYear + "-" + (vMon < 10 ? "0" + vMon : vMon) + "-" + (vDay < 10 ? "0" + vDay : vDay);
						var vTime = (vHours < 10 ? "0" + vHours : vHours) + ":" + (vMinutes < 10 ? "0" + vMinutes : vMinutes) +
								":" + (vSeconds < 10 ? "0" + vSeconds : vSeconds);
								
						//截取身份证号
						IDNumber = data.idNumber;
						IDNumber = IDNumber.substring(0,5)+"****"+IDNumber.substring(14);
						//获取等待区域
//						$.each(bsnsTypes, function(key, objNUM) {
//							if(data.businessType == objNUM.id){
//								if(objNUM.waitingarea!=""){
//				         			waitingarea = objNUM.waitingarea;
//				         		}else{
//				         			waitingarea = "等候区域";
//				         		}
//							}
//					     });
						waitingarea = data.waitingarea;
						if(waitingarea=="" || waitingarea == "undefined" || waitingarea==null){
							waitingarea="等待区域";
						}
						//退办信息
						var tb = "";
						tb = data.tb;
						if(tb=='1'){
							tb = "有退办!";
						}else{
							tb="";
						}
						
						
						
						if(agentfalg == 1){
							//判断是不是代理人取号
							if(banliTypesVo==1){
								if(isAgen == 1){//0非代理人 1代理人 
									var printInfo=data.str.replace("@",data.queueName).replace("*",data.serialNum).replace("#",data.peoNum).replace("+","单位："+Unit).replace("!",count+"笔").replace("$",vDate+" "+vTime).replace("^",IDNumber).replace("%","<font size=3>代办人：</font>"+nameB+"<font size=3>,车主：</font>"+NameA+"").replace("?",waitingarea).replace("~",tb);
									//初始化变量
									isAgen=0;Unit="";IDType="";IDNumber="";IDNumberB="",NameA="";nameB="";hmdbj=0;waitingarea="";objNUM = ""; idN="";
								}else{
									var printInfo=data.str.replace("@",data.queueName).replace("*",data.serialNum).replace("#",data.peoNum).replace("+","").replace("!",count+"笔").replace("$",vDate+" "+vTime).replace("^",IDNumber).replace("%","<font size=3>代办人：</font>"+nameB+"<font size=3>,车主：</font>"+NameA+"").replace("?",waitingarea).replace("~",tb);
									//初始化变量
									isAgen=0;Unit="";IDType="";IDNumber="";IDNumberB="",NameA="";nameB="";hmdbj=0;waitingarea="";objNUM = ""; idN="";
								}
								banliTypesVo = 0;
							}else{
								var printInfo=data.str.replace("@",data.queueName).replace("*",data.serialNum).replace("#",data.peoNum).replace("+","").replace("!",count+"笔").replace("$",vDate+" "+vTime).replace("^",IDNumber).replace("%","<font size=3>姓名：</font>"+NameA+"").replace("?",waitingarea).replace("~",tb);
								//初始化变量
								isAgen=0;Unit="";IDType="";IDNumber="";IDNumberB="",NameA="";nameB="";hmdbj=0;waitingarea="";objNUM = ""; idN="";
							}
						}else{
							
							if(banliTypesVo==1){
								if(isAgen == 1){//0非代理人 1代理人 
									var printInfo=data.str.replace("@",data.queueName).replace("*",data.serialNum).replace("#",data.peoNum).replace("+","单位："+Unit).replace("!",count+"笔").replace("$",vDate+" "+vTime).replace("^",IDNumber).replace("%","").replace("?",waitingarea).replace("~",tb);
									//初始化变量
									isAgen=0;Unit="";IDType="";IDNumber="";IDNumberB="",NameA="";nameB="";hmdbj=0;waitingarea="";objNUM = ""; idN="";
								}else{
									var printInfo=data.str.replace("@",data.queueName).replace("*",data.serialNum).replace("#",data.peoNum).replace("+","").replace("!",count+"笔").replace("$",vDate+" "+vTime).replace("^",IDNumber).replace("%","").replace("?",waitingarea).replace("~",tb);
									//初始化变量
									isAgen=0;Unit="";IDType="";IDNumber="";IDNumberB="",NameA="";nameB="";hmdbj=0;waitingarea="";objNUM = ""; idN="";
								}
								banliTypesVo = 0;
							}else{
								var printInfo=data.str.replace("@",data.queueName).replace("*",data.serialNum).replace("#",data.peoNum).replace("+","").replace("!",count+"笔").replace("$",vDate+" "+vTime).replace("^",IDNumber).replace("%","").replace("?",waitingarea).replace("~",tb);
								//初始化变量
								isAgen=0;Unit="";IDType="";IDNumber="";IDNumberB="",NameA="";nameB="";hmdbj=0;waitingarea="";objNUM = ""; idN="";
							}
						}
						//小票信息
						var bgObj=document.createElement("div");
		                    bgObj.setAttribute('id','page1');
		                    bgObj.style.position="absolute";
		                    bgObj.style.width="300px";
		                    bgObj.style.height="300px";
		                document.body.appendChild(bgObj);
						$('#page1').html(printInfo);
						var myDoc = {
							settings:{topMargin:10,
					                  leftMargin:10,
					                  bottomMargin:10,
					                  rightMargin:10},
	         		        documents: document,
	         		        copyrights: '杰创软件拥有版权  www.jatools.com'
	                 	};
						jatoolsPrinter.print(myDoc ,false);
			           
						window.setTimeout("$('#page1').remove()", 1000);
					} else { window.alert(data.error); }
				}, "json");
		    }
			//返回按钮隐藏
	  		 $("#backDiv").css("z-index", "-1");
	  		  agentInfo="";
	  		  gn.returnindex();
	  		 //window.location.reload();
		};
		
		
//		this.bnumOk = function(){
//			alert("测试");
//		};
		
			//办理页面
		function banLiYeMian(){
			$("#indexBL").css("display", "none");
			$("#index").css("display", "");
			document.getElementById("msg").innerHTML ="请将身份证放置读卡区域后选择取号方式！ <br/> 取号后请将身份证带走。";
//			$("#timers").css("display", "none");
//			document.getElementById("timers").innerHTML ="请将身份证放置读卡区域后选择取号方式！ <br/> 取号后请将身份证带走。";
		}
		
		//返回上一页
		function returnInit(){
			 $("#tdiv").empty().html("<div class='tab'>" + mainType + "</div>").css("padding-left", "70px");
		     $("#index").css("display", "none");
	  		 $("#type").css("display", "");
	  		 showBackBtn();
	    }
		//显示返回按钮
		function showBackBtn(){
			var divBackIndex ="<table border='0' cellspacing='0' cellpadding='0' class='tab_nav'><tr>"+
                    "<td><a class='back' onclick='gn.returnindex()' onfocus='this.blur()'>返回</a></td></tr></table>"
            //返回按钮显示
//			$("#index").css("display", "none");
		    $("#backDiv").empty().html(divBackIndex);
		    $("#backDiv").css("z-index", "");
		}
		
		//返回首页
		function backIndex(){
			hidden();
			$("#type").css("display", "none");
			$("#index").css("display", "");
			$("#backDiv").css("z-index", "-1");
			$("#indexBL").css("display", "");
			$("#index").css("display", "none");
		}
		
		//根据身份证获取本月的取号次数
		function numberCount(IDNumber,isAnget,typeId,agentId,count){
				$.ajax({type: "POST", cache: false,dataType: "json",async: false,
					url: 'number/countSet.action?IDNumber='+IDNumber+"&isAnget="+isAnget+"&typeId="+typeId+"&agentId="+agentId+"&count="+count+"&"+parseInt(Math.random()*100000),
					success: function (req) {
					if(req.msg != "true"){
						idN="";
						alert(req.msg);
						gn.returnindex();
					}
					isCountSet=req.countcs;
					}
				});
		}
		
		//验证黑名单
		function yzhmd(IDNumber){
			$.ajax({type: "POST", cache: false,dataType: "json",async: false,
					url: 'number/validateBlack.action?IDNumber='+IDNumber+"&"+parseInt(Math.random()*100000),
					success: function (req) {
						if(req.hmdflag == true){
							hmdbj = 1;
						}
					}
				});	
		}
		
		//验证缺纸
		function yzquezhi(){
			$.ajax({type: "POST", cache: false,dataType: "json",async: false,
					url: 'zzj/YzZzj.action?'+parseInt(Math.random()*100000),
					success: function (req) {
						if(req.flag == '0'){
							qzbj = 0;
						}else if(req.flag == '1'){
							qzbj = 1;
						}else if(req.flag == '2'){
							qzbj = 2;
						}
					}
				});	
		}
		
		//验证重复取号
		function yzcfqh(IDnumber){
			$.ajax({type: "POST", cache: false,dataType: "json",async: false,
					url: 'number/yzcfqh.action?IDNumber='+IDnumber+"&"+parseInt(Math.random()*100000),
					success: function (req) {
						if(req.cfqh == true){
							cfqhbj = 1;
						}
					}
				});
		}
		//是否开启通知提档
		function zxcl(key,id,flag,isOpenZhiWen){
//			$.ajax({type: "POST", cache: false,dataType: "json",async: false,
//					url: 'number/getIsOpenTztd.action?IDNumber='+IDNumber+"&"+parseInt(Math.random()*100000),
//					success: function (req) {
//						//0开启 1 关闭
//						if("0"== req.isOpenTztd){
//							//id说明 1 1机动车 2驾驶人
//							if("01"==flag){
//								
//								var objDiv = $("#tdiv").empty();
//									objDiv.html(tdtzxinxi);
//									this.tdtzbtnOk =function(){
//										var jdctypes = $("#tztdjdcsel").val();
//									    var jdcnum = $("#tztdjdcnum").val();
//									    
//									    alert(jdctypes);
//									    alert(jdcnum);
//									    gn.bcTztd(jdcnum,key,id,jdctypes,isOpenZhiWen);
//									}
//								
//								$.ajax({type: "POST", cache: false,dataType: "json",async: false,
//									url: 'number/getinterFaceTztd.action?flag='+id+"&"+parseInt(Math.random()*100000),
//									success: function (req) {
//										if(true==req.isSuccess){
//											var index = 0 ,newLi="", content1 = "" , content2 = "";
//											var objDiv = $("#tdiv").empty();
//											$.each(req.datas, function(tdkey, obj) {
//									         		newLi = "<li><a href='javascript:void(0);' class='btn_ywlx' onfocus='this.blur()' onclick='gn.bcTztd(\""+IDNumber+"\",\""+key+"\",\""+id+"\",\""+flag+"\",\""+obj.isOpenZhiWen+"\")'>"+obj.hphm+"--"+obj.hpzl+"<a></li>";
//										         		if(4 >= index){} 
//										         		if(10 >= index && 5 <= index){content2 += newLi;}
//										         		content1 += newLi;
//									         			index ++;
//										     });
//										   		var divBack ="<table border='0' cellspacing='0' cellpadding='0' class='tab_nav'><tr>"+
//									                    "<td><a class='back' onclick='gn.returns()' onfocus='this.blur()'>返回</a></td></tr></table>"
//										   		if (5 >= index) {
//											        objDiv.html("<div class='tab'>" + content1 + "</div>");
//											    } else {
//											        var newDiv1 = "<div class='tab tdiv'>" + content1 + "</div>";
//											        var newDiv2 = "<div class='tab tdiv'>" + content2 + "</div>";
//											        objDiv.html(newDiv1 + newDiv2);
//											        objDiv.css("padding-left", "70px");
//											    }
//											    //返回按钮显示
//										    $("#backDiv").empty().html(divBack);
//										    $("#backDiv").css("z-index", "");
//										}else{
//											alert("失败");
//											return;
//										}
//									}
//								});	
//							}else{
//								gn.bcTztd(IDNumber,key,id,flag,isOpenZhiWen);
//							}
//						}else{
//							gn.saveZhiWen(key,id,flag,isOpenZhiWen);
//						}
//					}
//			});	
			var jdctypes = "";
			var jdcnum ="";
			if(isOpenTztd==0){
				var objDiv = $("#tdiv").empty();
					objDiv.html(tdtzxinxi);
					
					this.tztdhqjp = function(){
						$("#tztdjdcnum").alxckeyboard();						
					}
					
					this.tdtzbtnOk =function(){
						jdctypes = $("#tztdjdcsel").val();
					    jdcnum = $("#tztdjdcnum").val();
					    
					    savaTztd(jdcnum,key,id,jdctypes,isOpenZhiWen);
					}
				
			}else{
//				savaTztd(jdcnum,key,id,jdctypes,isOpenZhiWen);
				gn.saveZhiWen(key,id,isOpenZhiWen);
			}
		
		
		}
		
		//保存指纹信息
		function saveZwBase64(key,id,flag,zwbase64){
			$.ajax({type: "POST", cache: false,dataType: "json",async: false,
					url: 'number/saveZWBase64.action?idNumber='+IDNumber+'&zwbase64='+encodeURIComponent(zwbase64)+"&"+parseInt(Math.random()*100000),
					success: function (req) {
						if(req.res == true){
							$("#zhiwenDiv").css("display","none");
							gn.createNumber(key,id);
							
						}else{
							alert("指纹信息保存失败!!!");
							return;
						}
					}
					
				});	
		}
		
		function savaTztd(jdcnum,key,id,jdctypes,isOpenZhiWen){
			$.ajax({type: "POST", cache: false,dataType: "json",async: false,
				url: 'number/saveTztd.action?IDNumber='+jdcnum+"&flag="+jdctypes+"&"+parseInt(Math.random()*100000),
					success: function (req) {
						if("true"==req.msg){
							gn.saveZhiWen(key,id,isOpenZhiWen);
						}else{
							alert("失败");
					}
				}
			});	
		}
		
		
		
		this.banLi = function(){
			banLiYeMian();
		};
		
		this.clears = function(obj) {
//			obj.value = obj.value.replace(/[^A-Za-z0-9_]/g, "");
		};
				
		this.showChildType = function(key) {
			showTwoType(key);
		};
		this.checkOk = function(flag){
			check(flag);
		};
		this.returns =function(){
			returnInit();
		}
		this.returnindex =function(){
			backIndex();
		}
		this.checkCl =function(key,id,flag,isOpenZhiWen){
			zxcl(key,id,flag,isOpenZhiWen);
		}	
		this.numberCount=function(IDNumber,isAnget,typeId,agentId,count){
			numberCount(IDNumber,isAnget,typeId,agentId,count);
		}
		this.saveZhiWen=function(key,id,flag,isOpenZhiWen,IDNumber){
			//判断是否录入指纹
			if(0 == isOpenZhiWen){
				//判断是否需要保存指纹
				$.ajax({type: "POST", cache: false,dataType: "json",async: false,
					url: 'number/getZWBase64ByIdNumber.action?idNumber='+IDNumber,
						success: function (req) {
						$("#zwbase64").val("");
							if(req.res == true){//true需要录入 false不需要
									$("#zhiwenDiv").css("display","");
										zwctl.CloseZwDev();//关闭指纹仪
										///初始化采集指纹
										if( true == zwctl.InitZwDev()){
											zwctl.StartCaptureZw("1");//开始采集指纹
											alert("指静脉信息采集中，请将食指放在指静脉仪上，采集完成后点击确定！");
											//isLrZHiWen(key,id,flag);
										}else{
											alert("初始化设备失败");
											return;
										}
									
							}else if(req.res == false){alert("再次");
								if(confirm("指静脉信息已经存在，是否再次采集！！！")){
									$("#zhiwenDiv").css("display","");
									zwctl.CloseZwDev();//关闭指纹仪
									//初始化采集指纹
										if( true == zwctl.InitZwDev()){
											zwctl.StartCaptureZw("1");//开始采集指纹
										alert("指静脉信息采集中，请将食指放在指静脉仪上，采集完成后点击确定！");
										//isLrZHiWen(key,id,flag);
										}else{
											alert("初始化设备失败");
											return;
										}
								}else{
									//gn.createNumber(key,id);
								}
							}
					}
				});	
			}else{
				gn.createNumber(key,id);
			}
		}
		
		this.validateBlack = function (IDNumber){
			yzhmd(IDNumber);
		}
		this.yzquez = function(){
			yzquezhi();
		}
		this.checkcfqh = function(IDNumber){
			yzcfqh(IDNumber);
		}
		this.bcTztd = function (jdcnum,key,id,jdctypes,isOpenZhiWen){
			savaTztd(IDNumber,key,id,flag,isOpenZhiWen);
		}
		this.validateZw = function (key,id,flag,baseVal){
			saveZwBase64(key,id,flag,baseVal);
		}
	}


		
    return {
        getInstance: function() {
			if (!instance) {
				instance = new constructor();
			}
			return instance;
		}
    };
})();

var gn = GenerateNumber.getInstance();

function upIcardPic(base64,cardNumber){
	var params = "base64picPhoto=" + encodeURIComponent(base64) + "&cardNumber="+cardNumber;
	$.ajax({type:"POST", cache:false, data:params,
			url:"number/uploadPic.action",
			success:function (picres) {
			if(0 == picres){
				var features = "status=no,resizable=no,top=0,left=0,scrollbars=no,width=300,height=400"
				+ "titlebar=no,menubar=no,location=no,toolbar=no,z-look=yes" 
				var newWin = window.open("/queue/showPhotoPic.jsp?cardNumber="+cardNumber, "_blank", features);
			}
		}
	});      
}
function showRs(){
	$.ajax({type: "POST", cache: false,dataType: "json",async: false,
						url: "number/showWaitRs.action?"+parseInt(Math.random()*100000),
						success: function (req) {
							if(req.isSuccess == true){
							$.each(req.datas, function(i, obj) {
								contAllRs = "大厅等待人数:"+obj.countAll+"人</br>";
							     });
							    //显示等待人数
								$('#showWaitRs').html("<font size='5'>"+contAllRs+"</font>");
							}else{
								$('#showWaitRs').html("大厅等待人数:0人</br>");
							}
						}
	});  
}
	
function isLrZHiWen(key,id,flag){
	waitZhiWen(key,id,flag)
}
function waitZhiWen(key,id,flag){
	
	var baseVal = document.getElementById("zwbase64").value;
	if("" != baseVal && "undefined" != baseVal){
		gn.validateZw(key,id,flag,baseVal);//保存指纹信息
	}else{
		if(confirm("是否重新录入！！！")){
			isLrZHiWen(key,id,flag);
		}else{
			gn.validateZw(key,id,flag,baseVal);//保存指纹信息
		}
	}
}
window.onload = function(){
		window.setInterval("showRs()",300000);
}