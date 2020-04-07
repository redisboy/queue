 //判断违法可用，通知提档可用
﻿function isEmpty(val) {
    return "undefined" == typeof(val) || "" == val || null == val;
}
jQuery.ajaxSetup ({cache:false}) ;
GenerateNumber = (function() {
	var phoneNumber;
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
    var qhstatus; //用于存储该时段是否可以取号的状态
    var IDType, IDNumber,IDNumberB="",NameA,nameB,hmdbj=0,Unit="",waitingarea=""; //证件类型及证件号码
    var mainType = "<li><a href='javascript:void(0);' class='btn_ywlx' onfocus='this.blur()' onclick='gn.checked(\"01\")'>机动车<a></li>"
    	+ "<li><a href='javascript:void(0);' class='btn_ywlx' onfocus='this.blur()' onclick='gn.checked(\"02\")'>驾驶人<a></li>"
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
						+"<p><input id='jdcnum' class='input1'/></p>"
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
						+"<p><input id='tztdjdcnum' class='input1'/></p>"
						+"<p><input type='button' value='提交' onclick='tdtzbtnOk()'/><img src='images/queue/ok.jpg' style='border: 0px; cursor: pointer;' onclick='tdtzbtnOk()' />&nbsp;&nbsp;"
				    	+"<input type='button' value='返回' onclick='tdtzbtnCancel()'/><img src='images/queue/cancel.jpg' style='border: 0px; cursor: pointer;' onclick='tdtzbtnCancel()' /></p></div>";
  
     var phonenumber = "<div class='inner_dxtz' ><p style='color: red;font-size: 30px;'>请输入办理人手机号码</p>"
			+"<p><input id='pnumber' class='input1'/></p>"
			+"<p><img src='images/queue/ok.jpg' style='border: 0px; cursor: pointer;' onclick='messagebtnOk()' />&nbsp;&nbsp;"
	    	+"<img src='images/queue/cancel.jpg' style='border: 0px; cursor: pointer;' onclick='messagebtnCancel()' /></p></div>";

     var phonenumber2 = "<div class='inner_dxtz' ><p style='color: red;font-size: 30px;'>请输入办理人手机号码</p>"
			+"<p><input id='pnumber' class='input1'/></p></div>";

     operator = '${user.name}';
    
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
		    $("#tdiv").empty().html("<div class='tab'>" + mainType + "</div>").css("padding-left", "70px");
	    }
	    $("#index").css("display", "none");
	  	$("#type").css("display", "");
	}
	
	 //显示二级菜单
	function showTwoType(key,id,flag){
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
					        url: 'number/checkStatusZD.action?flag='+flag+"&chepainum="+jdcnum+"&jdctypes="+jdctypes+"&id="+id,
					        success: function(data) {
							 	if(data.success){
//							 		check(flag);
							 		gn.createNumber(key,id);
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
				
				
					this.jdcbtnCancel = function(){
//						check(flag);
						gn.createNumber(key,id);
					}
				
				}else if(flag=='02'){
				 	$.ajax({type: "POST", cache: false, async: false, dataType: "json",
				        url: 'number/checkStatusZD.action?flag='+flag+"&IDNumber="+IDNumber+"&id="+id,
				        success: function(data) {
					 		if(data.success){
//						 		check(flag);
					 			gn.createNumber(key,id);
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
//					check(flag);
					gn.createNumber(key,id);
				}
			}
		}else{
//			check(flag);
			gn.createNumber(key,id);
		}
	}
	function check(flag){
		var objDiv = $("#tdiv").empty();
		var index = 0 ,newLi="", content1 = "" , content2 = "" , bj=0;
		$.each(bsnsTypes, function(key, obj) {
			if(!isEmpty(obj.twotype) && obj.twotype == flag){
				var dispy=isNull(obj.helps);
				if (obj.helps !== undefined  && !dispy){
					dispy="";
				}else{ dispy="none"; }
				var helps=enterString(obj.helps);
				var helps=spaceString(helps);
				var names = obj.name.split("@");
					//isOpenTztd 是否开启通知提档 0开启 1关闭
//					if("0"==obj.isOpenTztd){
//         				newLi = "<li><a href='javascript:void(0);' class='btn_ywlx' onfocus='this.blur()' onclick='gn.checkCl(\""+key+"\",\""+obj.id+"\",\""+flag+"\",\""+obj.isOpenZhiWen+"\")'>"+names[0]+" "+names[1]+"<a></li>";
//	         		}else{
//	         			newLi = "<li><a href='javascript:void(0);' class='btn_ywlx' onfocus='this.blur()' onclick='gn.saveZhiWen(\""+key+"\",\""+obj.id+"\",\""+obj.isOpenZhiWen+"\",\""+flag+"\")'>"+names[0]+" "+names[1]+"<a></li>";
//	         		}
				newLi = "<li><a href='javascript:void(0);' class='btn_ywlx' onmouseout='out()' onmouseover='over(\""+helps+"\",\""+dispy+"\")' onfocus='this.blur()' onclick='gn.checkCl(\""+key+"\",\""+obj.id+"\",\""+flag+"\",\""+obj.isOpenZhiWen+"\",\""+obj.isOpenTztd+"\",\""+obj.ismessage+"\")'>"+names[0]+" "+names[1]+"<a></li>";
					if(4 >= index){content1 += newLi;} 
	         		if(10 >= index && 5 <= index){content2 += newLi;}
         			index ++;
         			bj=1;
         		}
	     });
		if(bj == 0){
	   		alert("没有可办理业务1");
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
	//显示帮助信息
	this.over=function(busHelp,dispy){
	 		var oBus = $("#h1").empty();
	 		var divBus ="<p>"+busHelp+"</p>";
	 		oBus.append(divBus);
	 		$("#h1").css("display",dispy);
	 	//设置跟随鼠标
	 	$('#tdiv li').mousemove(function(e) { 
	 		var xx = e.originalEvent.x || e.originalEvent.layerX || 0; 
	 		var yy = e.originalEvent.y || e.originalEvent.layerY || 0; 
	 		$("#h1").css("top",yy).css("left",xx+180);
	 	}); 
	}
	 this.out=function(){
			$("#h1").css("display","none");
		}
	
	
	
    function constructor() {
        var agentInfo; //缓存代理人信息
	    var credentials; //缓存证件类型
         var readSysCard =SynCardOcx1;// IDCard1;//读卡器类型 0华视 1华旭
       	
         
         /*本人办理*/
         this.benren = function(){
         	 //检查是否在取号时间段内
         	 var qhstatus = yzqhsj();
         	 if(!qhstatus){
         	 	alert("当前时间段,不能取号");
         	 	return false;
         	 }
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
         	 //检查是否在取号时间段内
         	 var qhstatus = yzqhsj();
         	 if(!qhstatus){
         	 	alert("当前时间段,不能取号");
         	 	return false;
         	 }
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
							if (0 >= readSysCard.FindReader()) {
						  		window.alert("没有找到读卡器");
						  		return;
						  	}
						    //	var nRet = readSysCard.Syn_ReadMsg(1);//读卡
							//readSysCard.GetSAMID(); //读卡器SAMID
						  	//readSysCard.SetReadType(0); //读卡
						  	var nRet = readSysCard.ReadCardMsg();
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
						  	$.post("number/searchAgent.action?"+parseInt(Math.random()*100000), {IDNumber: number}, function(data) {//改动B
						  	        if (data.isAgnet) {
							  	        agentInfo = data;
//							  	        gn.returns();
							  	        Unit = agentInfo.nuit;//获取单位
							  	        isAgen = 1;//判断是不是代理人
//							  	        agentfalg=1;//判断是否为身份证读卡器取号
							  	       //判断是否启用静脉
							  	       if("0" == data.isOpenJm){
							  	            window.alert("请将指定的手指放入指静脉仪进行身份验证！");
							  	         //   tempCapt = xhWebFingerCtrl.OnCaptureFinger();
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
						} else {window.alert("未放置身份证或身份无效"); }
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
		
		/*获取证件类型及证件号码*/
		this.btnOk = function() {
		    var type = $("#tIDType").val();
		    var number = $("#tIDNumber").val();
		     document.getElementById("msg").innerHTML ="";
		    if (isEmpty(type) || isEmpty(number)) {
		        window.alert("证件类型及证件号码不能为空，\n请正确填写信息");
		        return;
		    }else if('A'==type || type == "M"){
		    	if(checkIdcardIsOrNo()=="1"){
					window.alert("非法身份证号！");
					return;
				}else if(checkIdcardIsOrNo()=="2"){
					window.alert("非法地区！");
					return;
				}else if(checkIdcardIsOrNo()=="3"){
					window.alert("非法生日！");
					return;
				}
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
			  	$.post("number/searchAgent.action?"+parseInt(Math.random()*100000), {IDNumber: IDNumberB}, function(data) {//改动B首个
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
				 
				//---------------------------------------------
					//验证身份证明是否符合规则
					//---------------------------------------------
					function checkIdcardIsOrNo(){ 
					  var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};
					  var iSum = 0;
					  //var info = "";
					  var strIDno = $("#tIDNumber").val();
					  var idCardLength = strIDno.length;
					  if(!/^\d{17}(\d|x)$/i.test(strIDno)&&!/^\d{15}$/i.test(strIDno))
					     return 1; //非法身份证号
					
					  if(aCity[parseInt(strIDno.substr(0,2))]==null)
					     return 2;// 非法地区
					
					  // 15位身份证转换为18位
					  if (idCardLength==15)
					 {
					    sBirthday = "19" + strIDno.substr(6,2) + "-" + Number(strIDno.substr(8,2)) + "-" + Number(strIDno.substr(10,2));
					    var d = new Date(sBirthday.replace(/-/g,"/"))
					    var dd = d.getFullYear().toString() + "-" + (d.getMonth()+1) + "-" + d.getDate();
					    if(sBirthday != dd)
					       return 3; //非法生日
					       strIDno=strIDno.substring(0,6)+"19"+strIDno.substring(6,15);
					       strIDno=strIDno+GetVerifyBit(strIDno);
					  }
					
					  // 判断是否大于2078年，小于1900年
					  var year =strIDno.substring(6,10);
					  if (year<1900 || year>2078 )
					      return 3;//非法生日
					
					  //18位身份证处理
					
					  //在后面的运算中x相当于数字10,所以转换成a
					  strIDno = strIDno.replace(/x$/i,"a");
					
					  sBirthday=strIDno.substr(6,4)+"-"+Number(strIDno.substr(10,2))+"-"+Number(strIDno.substr(12,2));
					  var d = new Date(sBirthday.replace(/-/g,"/"))
					  if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))
					                return 3; //非法生日
					  // 身份证编码规范验证
					  for(var i = 17;i>=0;i --)
					   iSum += (Math.pow(2,i) % 11) * parseInt(strIDno.charAt(17 - i),11);
					  if(iSum%11!=1)
					   return 1;// 非法身份证号
					
					  // 判断是否屏蔽身份证
					  var words = new Array();
					  words = new Array("11111119111111111","12121219121212121");
					
					  for(var k=0;k<words.length;k++){
					   if (strIDno.indexOf(words[k])!=-1){
					       return 1;
					     }
					   }
					       return 0;
					} 
					//15位转18位中,计算校验位即最后一位
					function GetVerifyBit(id){
					        var result;
					        var nNum=eval(id.charAt(0)*7+id.charAt(1)*9+id.charAt(2)*10+id.charAt(3)*5+id.charAt(4)*8+id.charAt(5)*4+id.charAt(6)*2+id.charAt(7)*1+id.charAt(8)*6+id.charAt(9)*3+id.charAt(10)*7+id.charAt(11)*9+id.charAt(12)*10+id.charAt(13)*5+id.charAt(14)*8+id.charAt(15)*4+id.charAt(16)*2);
					        nNum=nNum%11;
					        switch (nNum) {
					           case 0 :
					              result="1";
					              break;
					           case 1 :
					              result="0";
					              break;
					           case 2 :
					              result="X";
					              break;
					           case 3 :
					              result="9";
					              break;
					           case 4 :
					              result="8";
					              break;
					           case 5 :
					              result="7";
					              break;
					           case 6 :
					              result="6";
					              break;
					           case 7 :
					              result="5";
					              break;
					           case 8 :
					              result="4";
					              break;
					           case 9 :
					              result="3";
					              break;
					           case 10 :
					              result="2";
					              break;
					        }
					        //document.write(result);
					        return result;
					} 
					
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
		
		//取号
		function qhao(id){
			//显示身份证取号界面
			hidden();
			$("#type").css("display", "none");
			$("#index").css("display", "");
		    var obj = eval("bsnsTypes." + id);
		    //业务笔数及人员类型
			var count = 1, personType = 1;
		    //判断代理人可办的业务及办理笔数
		    if (!isEmpty(agentInfo) && agentInfo.isAgnet) {
			    count = window.prompt("请输入办理业务的笔数", "");
			    if(isNaN(count)){
			    	$("#backDiv").css("z-index", "-1");
			    	alert("请输入数字");
			    	return;
			    } else if(count == null){
			    	count = 1;
			    }
			    //判断取号次数
			    if(banliTypesVo==1){
			    	gn.numberCount(IDNumberB,0,id.substring(1),agentInfo.agentId,count);
			    }else{
				    gn.numberCount(IDNumber,0,id.substring(1),agentInfo.agentId,count);
			    }
			    personType = 2; //代理人
		    }else{
		    	if(banliTypesVo==1){
		    		gn.numberCount(IDNumberB,1,id.substring(1),"",count);//判断取号次数
		    	}else{
			    	gn.numberCount(IDNumber,1,id.substring(1),"",count);//判断取号次数
		    	}
		    }
		    if(isCountSet == 1){
		     	agentInfo="";
		     	//返回按钮隐藏
	  		 	$("#backDiv").css("z-index", "-1");
		    	return;
		    }
			
		    if(!isEmpty(count) && count != 0){
		    	var params = {businessType:obj.id, IDType:IDType, IDNumber:IDNumber,IDNumberB:IDNumberB, businessCount:count,
					personType:personType, prefix:obj.prefix, code:obj.code, typeName:obj.name, flag:obj.flag};
		    	$.post("number/generateNumber.action?PNumber="+phoneNumber+"&"+parseInt(Math.random()*100000), params, function (data, status) {
					if (data.isSuccess) {
						IDNumberB="";//清空IDNumberB
						if("0" == data.isOpenIndexCamera){//判断是否开启取号拍照功能
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
//						$.each(bsnsTypes, function(key, obj) {
//							if(data.businessType == obj.id){
//								if(obj.waitingarea!=""){
//				         			waitingarea = obj.waitingarea;
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
									var printInfo=data.str.replace("@",data.queueName).replace("*",data.serialNum).replace("#",data.peoNum).replace("+","单位："+Unit).replace("!",count+"笔").replace("$",operator+vDate+" "+vTime).replace("^",IDNumber).replace("%","<font size=3>姓名：</font>"+nameB+"<font size=3>,车主：</font>"+NameA+"").replace("?",waitingarea).replace("~",tb);
									//初始化变量
									isAgen=0;Unit="";IDType="";IDNumber="";IDNumberB="",NameA="";nameB="";hmdbj=0;waitingarea="";
								}else{
									var printInfo=data.str.replace("@",data.queueName).replace("*",data.serialNum).replace("#",data.peoNum).replace("+","").replace("!",count+"笔").replace("$",operator+vDate+" "+vTime).replace("^",IDNumber).replace("%","<font size=3>姓名：</font>"+nameB+"<font size=3>,车主：</font>"+NameA+"").replace("?",waitingarea).replace("~",tb);
									//初始化变量
									isAgen=0;Unit="";IDType="";IDNumber="";IDNumberB="",NameA="";nameB="";hmdbj=0;waitingarea="";
								}
								banliTypesVo = 0;
							}else{
								var printInfo=data.str.replace("@",data.queueName).replace("*",data.serialNum).replace("#",data.peoNum).replace("+","").replace("!",count+"笔").replace("$",operator+vDate+" "+vTime).replace("^",IDNumber).replace("%","<font size=3>姓名：</font>"+NameA+"").replace("?",waitingarea).replace("~",tb);
								//初始化变量
								isAgen=0;Unit="";IDType="";IDNumber="";IDNumberB="",NameA="";nameB="";hmdbj=0;waitingarea="";
							}
						}else{
							if(banliTypesVo==1){
								if(isAgen == 1){//0非代理人 1代理人 
									var printInfo=data.str.replace("@",data.queueName).replace("*",data.serialNum).replace("#",data.peoNum).replace("+","单位："+Unit).replace("!",count+"笔").replace("$",operator+vDate+" "+vTime).replace("^",IDNumber).replace("%","").replace("?",waitingarea).replace("~",tb);
									//初始化变量
									isAgen=0;Unit="";IDType="";IDNumber="";IDNumberB="",NameA="";nameB="";hmdbj=0;waitingarea="";
								}else{
									var printInfo=data.str.replace("@",data.queueName).replace("*",data.serialNum).replace("#",data.peoNum).replace("+","").replace("!",count+"笔").replace("$",operator+vDate+" "+vTime).replace("^",IDNumber).replace("%","").replace("?",waitingarea).replace("~",tb);
									//初始化变量
									isAgen=0;Unit="";IDType="";IDNumber="";IDNumberB="",NameA="";nameB="";hmdbj=0;waitingarea="";
								}
								banliTypesVo = 0;
							}else{
								var printInfo=data.str.replace("@",data.queueName).replace("*",data.serialNum).replace("#",data.peoNum).replace("+","").replace("!",count+"笔").replace("$",operator+vDate+" "+vTime).replace("^",IDNumber).replace("%","").replace("?",waitingarea).replace("~",tb);
								//初始化变量
								isAgen=0;Unit="";IDType="";IDNumber="";IDNumberB="",NameA="";nameB="";hmdbj=0;waitingarea="";
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
		//-----------
		//办理页面
		function banLiYeMian(){
			$("#indexBL").css("display", "none");
			$("#index").css("display", "");
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
		
		//是否开启通知提档或短信通知
		function zxcl(key,id,flag,isOpenZhiWen,isOpenTztd,ismessage){
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
			var objDiv = $("#tdiv").empty();
			
			if(ismessage==0 && isOpenTztd==0){
				objDiv.html(tdtzxinxi+phonenumber2);
				this.tdtzbtnOk =function(){
					jdctypes = $("#tztdjdcsel").val();
				    jdcnum = $("#tztdjdcnum").val();
				    phoneNumber = $("#pnumber").val();
				    savaTztd(jdcnum,key,id,jdctypes,isOpenZhiWen,flag);
				}
				this.tdtzbtnCancel = function(){
					check(flag);
				}
			}else if(isOpenTztd==0){
				objDiv.html(tdtzxinxi);
				this.tdtzbtnOk =function(){
					jdctypes = $("#tztdjdcsel").val();
				    jdcnum = $("#tztdjdcnum").val();
				    savaTztd(jdcnum,key,id,jdctypes,isOpenZhiWen,flag);
				}
				this.tdtzbtnCancel = function(){
					check(flag);
				}
		    }else if(ismessage==0){
				objDiv.html(phonenumber);
				this.messagebtnOk =function(){
					phoneNumber = $("#pnumber").val();
				    gn.saveZhiWen(key,id,isOpenZhiWen,flag);
				}
				this.messagebtnCancel = function(){
					check(flag);
				}
			}else{
//				savaTztd(jdcnum,key,id,jdctypes,isOpenZhiWen);
				gn.saveZhiWen(key,id,isOpenZhiWen,flag);
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
							alert("指静脉信息保存失败!!!");
							return;
						}
					}
					
				});	
		}
		
		function savaTztd(jdcnum,key,id,jdctypes,isOpenZhiWen,flag){
			$.ajax({type: "POST", cache: false,dataType: "json",async: false,
				url: 'number/saveTztd.action?IDNumber='+jdcnum+"&flag="+jdctypes+"&"+parseInt(Math.random()*100000),
					success: function (req) {
						if("true"==req.msg){
							gn.saveZhiWen(key,id,isOpenZhiWen,flag);
						}else{
							alert("失败");
					}
				}
			});	
		}
		
		function savaPnum(key,id,isOpenZhiWen,flag,num){
			$.ajax({type: "POST", cache: false,dataType: "json",async: false,
				url: 'number/saveTztd.action?IDNumber='+num+"&"+parseInt(Math.random()*100000),
					success: function (req) {
						if("true"==req.msg){
							gn.saveZhiWen(key,id,isOpenZhiWen,flag);
						}else{
							alert("失败");
					}
				}
			});	
		}
		//验证当前取号时间是否在取号时间段内
		function yzqhsj(){
			var yzqhsj;
			$.ajax({
				type:"post",
				cache:false,
				async:false,
				dataType:"text",
				url:"number/yzqhsj.action",
				success:function(req){
					if(req=="true"){
						yzqhsj = true;
					}else{
						yzqhsj = false;
					}
				}
			})
			if(yzqhsj == true){
				return true;
			}else{
				return false;	
			}
		}
		//判断取号量是否超过设置的最大取号量
		function pdqhl(id){
			var result;
			$.ajax({
				type:"post",
				cache:false,
				dataType:"json",
				data:{ywid:id},
				async:false,
				url:"number/pdqhl.action",
				success:function(data){
					if(data==true){
						result = [true];
					}else if(data==false){
						result = [false,data.msg];
					}
				}
			});
			return result;
		}
		
		
		this.banLi = function(){
			banLiYeMian();
		};
		
		this.clears = function(obj) {
//			obj.value = obj.value.replace(/[^A-Za-z0-9_]/g, "");
		};
				
		this.showChildType = function(key,id,flag) {
			showTwoType(key,id,flag);
		};
		this.checked = function(flag){
			check(flag);
		}
		this.checkOk = function(flag){
			check(flag);
		};
		this.returns =function(){
			returnInit();
		}
		this.returnindex =function(){
			backIndex();
		}
		this.checkCl =function(key,id,flag,isOpenZhiWen,isOpenTztd,ismessage){
			var result = pdqhl(id);
			if(result[0]){
				zxcl(key,id,flag,isOpenZhiWen,isOpenTztd,ismessage);
			}else{
				//alert(result[1]);
				alert("由于取号人数超过该业务每日受理数量,暂停取号");
				backIndex();
			}
		}	
		this.numberCount=function(IDNumber,isAnget,typeId,agentId,count){
			numberCount(IDNumber,isAnget,typeId,agentId,count);
		}
		this.saveZhiWen=function(key,id,isOpenZhiWen,flag){
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
				
				gn.showChildType(key,id,flag);
				
//				gn.createNumber(key,id);
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
//		this.yzqhsj = function(){
//			yzqhsj();
//		}
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

/*获取业务类型及帮助信息*/
this.helpShow =function(){
	var helpReply;//信息包装类
	var isSuccess = true;
	 if (isEmpty(helpReply)) {
	        $.ajax({
	        	type: "POST", 
	        	cache: false, 
	        	async: false, 
	        	dataType: "json",
		        url: "number/bsnsType.action?"+parseInt(Math.random()*100000),
		        success: function(request) {
		            if (request.isSuccess) {
		            	helpReply = request.datas;
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
	 	//设计提示框表头
	 	var oHelp = $("#tID1").empty();
	 	var divTb="<thead align='center'><tr><th>业务类型</th><th>业务说明</th> </tr></thead>";
		oHelp.append(divTb);
		//遍历提示框信息
		$.each(helpReply, function(i, obj) { 
			var o=isNull(obj.helps);
	 		if (obj.helps !== undefined  && !o) { 
	 		var helps=enterString(obj.helps);
			var helps=spaceString(helps);
	 		var divCon ="<tr><td width='30%' height='15%'>"+obj.names+"</td><td width='70%' height='15%'>"+helps+"</td></tr>";
			oHelp.append(divCon);
	 		}
	 	});
		$("#hdiv").css("display", "");
 		$("#hb1").css("display", "none");
 		
 	setTimeout(function(){
 		$("#hdiv").css("display","none");
 		$("#hb1").css("display", "");
 			},9000);
	 }
		
	 $("#hdiv").mouseleave(function(){
		 $(this).css("display","none");
		 $("#hb1").css("display", "");
	 });
	 
	//替换所有的回车换行  
	 function enterString(content){  
	     var string = content;  
	     try{  
	        string=string.replace(/\r\n/g,"<br>");  
	        string=string.replace(/\n/g,"<br>");  
	     }catch(e) {  
	         e.message;  
	     }  
	     return string;  
	 }  
	 
		//替换所有的空格字符串  
	 function spaceString(content){  
		     var string = content;  
		     try{  
		   string=string.replace(/(^s*)|(s*$)/g,"&nbsp;");
		     }catch(e) {  
		         e.message;  
		     }  
		     return string;  
		 }  
	 	//判断是否全是空格，是则true，否则false
	 function isNull( str ){
		 if ( str == "" ){return true;}
		 var regu = "^[ ]+$";
		 var re = new RegExp(regu);
		 return re.test(str);
		 }
	//判断是否存在操作人,作加入打印条处理
	function operatorSZ(a){
		if("undefined" == typeof(a) || "" == a || null == a){
			operator ="";
		 }else{
			operator ="操作人:"+a+"</br>";
		 }
	}