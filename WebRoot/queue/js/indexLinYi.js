﻿function isEmpty(val) {
    return "undefined" == typeof(val) || "" == val || null == val;
}
jQuery.ajaxSetup ({cache:false}) ;
GenerateNumber = (function() {
    var instance;
    var bsnsTypes; //缓存业务类型
    var agentfalg;//判断是不是读卡器取号
    var isAgen=0;
    var IDType, IDNumber,NameA,hmdbj=0,Unit="",waitingarea=""; //证件类型及证件号码
    var mainType = "<li><a href='javascript:void(0);' class='btn_ywlx' onfocus='this.blur()' onclick='gn.showChildType(\"01\")'>机动车<a></li>"
    	+ "<li><a href='javascript:void(0);' class='btn_ywlx' onfocus='this.blur()' onclick='gn.showChildType(\"02\")'>驾驶人<a></li>"
    	+ "<li><a href='javascript:void(0);' class='btn_ywlx' onfocus='this.blur()' onclick='gn.showChildType(\"04\")'>违法<a></li>";
	
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
		   // $("#tdiv").empty().html("<div class='tab'>" + mainType + "</div>").css("padding-left", "70px");
	    	gn.showChildType('01');
	    }
	    $("#index").css("display", "none");
	  	$("#type").css("display", "");
	}
	
	 //显示二级菜单
	function showTwoType(flag){
		var objDiv = $("#tdiv").empty();
		var index = 10 ,newLi="", content1 = "<lin><h1>东大厅</h1></li>" , content2 = "<lin><h1>西大厅</h1></li>" , bj=0;
		
		$.each(bsnsTypes, function(key, obj) {
//			if(!isEmpty(obj.twotype) && obj.twotype == flag){
			var names = obj.name.split("@");
         	newLi = "<li><a href='javascript:void(0);' class='btn_ywlx' onfocus='this.blur()' onclick='gn.createNumber(\""+key+"\",\""+obj.id+"\")'>"+names[0]+" "+names[1]+"<a></li>";
			if(obj.waitingarea=="东大厅"){
				content1 += newLi;
				bj=1;
			}else if(obj.waitingarea=="西大厅"){
				content2 += newLi;
				bj=1;
			}
//	         		if(4 >= index){content1 += newLi;} 
//	         		if(10 >= index && 5 <= index){content2 += newLi;}
//         			index ++;
//         			bj=1;
//        		}
	     });
	   if(bj == 0){
	   		alert("没有可办理业务");
	   		gn.returns();
	   }else{
	   		var divBack ="<table border='0' cellspacing='0' cellpadding='0' class='tab_nav'><tr>"+
                    "<td><a class='back' onclick='gn.returns()' onfocus='this.blur()'>返回</a></td></tr></table>"
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
       
        /*身份证取号*/
        this.card = function() {
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
							readSysCard.GetSAMID(); //读卡器SAMID
						  	readSysCard.SetReadType(0); //读卡
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
						  	//判断是不是代理人
						  	$.post("number/searchAgent.action?"+parseInt(Math.random()*100000), {IDNumber: number}, function(data) {
						  	        if (data.isAgnet) {
							  	        agentInfo = data;
							  	        gn.returns();
							  	        Unit = agentInfo.nuit;//获取单位
							  	        isAgen = 1;//判断是不是代理人
							  	        agentfalg=1;//判断是否为身份证读卡器取号
							  	       //判断是否启用静脉
							  	       if("0" == data.isOpenJm){
							  	            window.alert("请扫描指纹");
							  	            tempCapt = xhWebFingerCtrl.OnCaptureFinger();
											var stat = xhWebFingerCtrl.OnIdentifyFinger(agentInfo.fingerprint);
									    	if (0 == stat && "" != tempCapt ) {
									    		gn.returns();
									    		showBackBtn();
												agentfalg=1;
									    	    displayType("A", agentInfo.IDNumber,NameA); 
											  } else { 
												  gn.returnindex();
												  agentInfo = "";
											  }
										 }else{
										 	displayType("A", agentInfo.IDNumber);
										 }
						  	 		 } else { 
								  	        gn.returns();
								  	        showBackBtn();
											agentfalg=1;
											isAgen = 0;
								  	        displayType("A", number,NameA); 
						  	  		}
						  	}, "json");
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
		    if (isEmpty(type) || isEmpty(number)) {
		        window.alert("证件类型及证件号码不能为空，\n请正确填写信息");
		        return;
		    }
		    
			if (type == "A" || type == "M") {
				var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
				if (!reg.test(number)) {
	       			alert("身份证输入不合法");  
	       			return;
				}
				//验证黑名单----begin
				gn.validateBlack(number);
				if(hmdbj == 1){
					alert("此人已在黑名单中存在，无法取号！！！");
					hmdbj = 0;
					return;
				}
				//-------------end
				//判断是不是代理人
		  	    $.post("number/searchAgent.action?"+parseInt(Math.random()*100000), {IDNumber: number}, function(data) {
		  	        if (data.isAgnet) {
			  	        agentInfo = data;
			  	        Unit = agentInfo.nuit;
			  	        isAgen = 1;//判断是不是代理人
			  	        if (!isEmpty(agentInfo.agentStatus)) {
			  	        	window.alert(agentInfo.agentStatus);
			  	        } else {
			  	        	/*window.alert("请扫描指纹");
			  	          xhWebFingerCtrl.OnInitDev();
										xhWebFingerCtrl.sFingerInfo = agentInfo.fingerprint;
							    	if (xhWebFingerCtrl.OnIdentifyOneToOne()) {*/
							    	    displayType(type, agentInfo.IDNumber);
							    	//} else { agentInfo = ""; }
			  	        }
		  	        } else {
		  	        	isAgen = 0;
		  	        	displayType(type, number); 
		  	        }
		  	    }, "json");
			  } else {
				isAgen = 0;
			  	displayType(type, number);
			  }
//		    gn.returns();
		  	showBackBtn();
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
			    gn.numberCount(IDNumber,0,id.substring(1),agentInfo.agentId,count);
			    personType = 2; //代理人
		    }else{
		    	gn.numberCount(IDNumber,1,id.substring(1),"",count);//判断取号次数
		    }
		    if(isCountSet == 1){
		     	agentInfo="";
		     	//返回按钮隐藏
	  		 	$("#backDiv").css("z-index", "-1");
		    	return;
		    }
			
		    if(!isEmpty(count) && count != 0){
		    	var params = {businessType:obj.id, IDType:IDType, IDNumber:IDNumber, businessCount:count,
					personType:personType, prefix:obj.prefix, code:obj.code, typeName:obj.name, flag:obj.flag};
				$.post("number/generateNumber.action?"+parseInt(Math.random()*100000), params, function (data, status) {
					if (data.isSuccess) {
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
						if(agentfalg == 1){
							//判断是不是代理人取号
							if(isAgen == 1){//0非代理人 1代理人 
								var printInfo=data.str.replace("@",data.queueName).replace("*",data.serialNum).replace("#",data.peoNum).replace("+","单位："+Unit).replace("!",count+"笔").replace("$",vDate+" "+vTime).replace("^",IDNumber).replace("%","<font size=3>姓名：</font>"+NameA+"").replace("?",waitingarea);
							}else{
								var printInfo=data.str.replace("@",data.queueName).replace("*",data.serialNum).replace("#",data.peoNum).replace("+","").replace("!",count+"笔").replace("$",vDate+" "+vTime).replace("^",IDNumber).replace("%","<font size=3>姓名：</font>"+NameA+"").replace("?",waitingarea);
							}
						}else{
							var printInfo=data.str.replace("@",data.queueName).replace("*",data.serialNum).replace("#",data.peoNum).replace("+","").replace("!",count+"笔").replace("$",vDate+" "+vTime).replace("^",IDNumber).replace("%","").replace("?",waitingarea);
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
	  		 //window.location.reload();
		};
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
			$("#index").css("display", "none");
		    $("#backDiv").empty().html(divBackIndex);
		    $("#backDiv").css("z-index", "");
		}
		
		//返回首页
		function backIndex(){
			hidden();
			$("#type").css("display", "none");
			$("#index").css("display", "");
			$("#backDiv").css("z-index", "-1");
		}
		
		//根据身份证获取本月的取号次数
		function numberCount(IDNumber,isAnget,typeId,agentId,count){
				$.ajax({type: "POST", cache: false,dataType: "json",async: false,
					url: 'number/countSet.action?IDNumber='+IDNumber+"&isAnget="+isAnget+"&typeId="+typeId+"&agentId="+agentId+"&count="+count+"&"+parseInt(Math.random()*100000),
					success: function (req) {
					if(req.msg != "true"){
						alert(req.msg);
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
		
				this.clears = function(obj) {
					obj.value = obj.value.replace(/[^A-Za-z0-9_]/g, "");
				};
				
				this.showChildType = function(key) {
				    showTwoType(key);
				};
				this.returns =function(){
//					returnInit();
					backIndex();
				}
				this.returnindex =function(){
					backIndex();
				}
				
				this.numberCount=function(IDNumber,isAnget,typeId,agentId,count){
					numberCount(IDNumber,isAnget,typeId,agentId,count);
				}
				this.validateBlack = function (IDNumber){
					yzhmd(IDNumber);
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
window.onload = function(){
		window.setInterval("showRs()",300000);
}