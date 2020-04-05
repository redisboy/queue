document.oncontextmenu = function() { return false; } //禁止右键功能,单击右键将无任何反应
document.onselectstart = function() { return false; } //禁止选择,也就是无法复制
window.onhelp = function() { return false; } //屏蔽F1

dwr.engine.setActiveReverseAjax(true);
dwr.engine.setNotifyServerOnPageUnload(true);

function login(data) {
	//subSomething();//重启IE
	$("#_welcome").css("display", "none");
	$("#data_show").css("display", "");
	$("#operNum").val(data.code);
	$("#loginIP").val(data.loginIP);
	$("#police_code").html(data.code);
	$("#police_name").html(data.name);
	$("#div_zym").html(data.comments);
	$("#tootip_img").attr("src", data.photo);
	if ($("#myMarq").text() == "") {
		$("#mask_tip").css("display", "");
	} else {
		$("#mask_tip").css("display", "none");
	}
}

function aotologin(datas) {
	//subSomething();//重启IE
	var data = JSON.parse(datas);
	$("#_welcome").css("display", "none");
	$("#data_show").css("display", "");
	$("#operNum").val(data.code);
	$("#loginIP").val(data.loginIP);
	$("#police_code").html(data.code);
	$("#police_name").html(data.name);
	$("#div_zym").html(data.comments);
	$("#tootip_img").attr("src", data.photo);
	if ($("#myMarq").text() == "") {
		$("#mask_tip").css("display", "");
	} else {
		$("#mask_tip").css("display", "none");
	}
}

function reloadPcWindow(data){
	window.location.reload();
}
document.onreadystatechange = subSomething;//当页面加载状态改变的时候执行这个方法.
 function subSomething() { 
  if(document.readyState=="complete"){
   //alert("当前页面已加载完成！");
  }else{
  	subSomething();
  }
 }

function pause(data) {
	if (data == 1) {//暂停服务,显示暂停服务页,隐藏数据展示页
		/*珠海改动--将显示隐藏div改成替换图片*/
		/*$("#data_show").css("display", "none");
		$("#_pause").css("display", "");*/
		$("#imgzt").attr("src","/queue/images/queue/ztimg.jpg")
	} else {//暂停服务结束,开始服务
		/*$("#_pause").css("display", "none");
		$("#data_show").css("display", "");
		$("#mask_tip").css("display", "none");*/
		$("#imgzt").attr("src","/queue/images/queue/waitnext.jpg")
	}
}
function passshowdata(data) {
	$("#div1").css("display", "none");
	$("#div8").css("display", "");
	if ($("#myMarq").text() == "") {
		$("#mask_tip").css("display", "");
	} else {
		$("#mask_tip").css("display", "none");
	}
	$("#_jdc").css("display", "none");
	$("#_jsr").css("display", "none");
	$("#quhaophoto").find("img").remove();
	$("#div_dqhm").find("p").remove();
	$("#div_dqhm").find("br").remove();
}

function camera() {
	jsrzP.nW = 300;
	jsrzP.nH = 320;
	jsrzP.sZpImgFilePath = "c:\\99999.jpg";
	try{
		//jsrzP.OnCaptureZpNew();
		var pzpic = jsrzP.OnCaptureZpNew();
		if (pzpic == "" || pzpic == false) {
	    	//拍照失败
			okORerrorCamera(0);
		} else {
			$("#base64Code").val(pzpic);
		}
　	}catch(e){
　		okORerrorCamera(0);
　　}
}

//判断摄像头加载情况
function okORerrorCamera(params){
	//params:  0初始化失败，1拍照失败
	$.ajax({type: "POST", cache: false,
		url: '/queue/number/addLogContent.action?params=' + params,
		success: function (req) {}
	});
}

var setTimeoutReturn, isok, isSignatureisok,isOpenIndexCamera;
function toEvaluation(data) {
    //可以评价的状态下首先打开摄像头,拍取一张照片用于自动评价照片存录
	isok = data.isCamera; //判断是否启用摄像头
	if (isok == 0) {
		//camera();
	}
	var reasons = "";
	$.each(eval("(" + data.reason + ")"), function (key, val) {
		reasons += "<li><a href=\"#\" class=\"btn_ywlx\" onfocus=\"this.blur()\" onclick=\"nonePop('4','1','" + val + "')\">" + val + "</a></li>";
	});
	$("#reasons").html(reasons);
	
	//播放声音 请你对我的服务进行评价
	please.play();
	$("#_eval").css("display", "");
	
	if("1"==data.isOpenForceEnvalue){//判断是否启用强制评价 0启用 1不启用
		setTimeoutReturn = setTimeout("enterEval('','2','')", data.pt); //设置10秒默认评价10000
	}
}
/*
function dispNone(){
	$("#_eval").css("display", "none");
	$("#base64Code").val("");
	$("#div1").css("display", "none");
	$("#div8").css("display", "");
	if ($("#myMarq").text() == "") {
		$("#mask_tip").css("display", "");
	} else {
		$("#mask_tip").css("display", "none");
	}
	$("#div_dqhm").find("p").remove();
	$("#div_dqhm").find("br").remove();
}*/

//差评原因div
function nonePop(subvalue, sub_flag, reason) {
	$("#pop").css("display", "none");
	if (reason != "") {
		window.clearInterval(setTimeoutReturn);
	}
	enterEval(subvalue, sub_flag, reason);
}

function doEval(selectValue, name) {
	if (4 == selectValue) {
		window.clearInterval(setTimeoutReturn);
		$("#_eval").css("display", "none");
		if (0 < $("#reasons li").size()) {
			$("#pop").css("display", "");
			setTimeoutReturn = setTimeout("nonePop('4', '1', '')", 10000);//10秒后关闭差评原因div
		} else {
			nonePop("4", "1", "");
		}
	} else {
		enterEval(selectValue, 1, "");
	}
	$("#_jdc").css("display", "none");
	$("#_jsr").css("display", "none");
	$("#quhaophoto").find("img").remove();
	$("#div_dqhm").find("p").remove();
	$("#div_dqhm").find("br").remove();
}

/* 弹框确认提交评价
function rusure(selectValue, name){  
	question = confirm("确认评价结果吗?")
	setTimeout("self.close()", 2000);
	if (question !="1"){
	document.getElementById(obj).style.display = '';
//	$("#_eval").css("display", "");
	}
	if(question ="1"){
		if (4 == selectValue) {
			window.clearInterval(setTimeoutReturn);
			$("#_eval").css("display", "none");
			if (0 < $("#reasons li").size()) {
				$("#pop").css("display", "");
				setTimeoutReturn = setTimeout("nonePop('4', '1', '')", 10000);//10秒后关闭差评原因div
			} else {
				nonePop("4", "1", "");
			}
		} else {
			enterEval(selectValue, 1, "");
		}
		$("#div_dqhm").find("p").remove();
		$("#div_dqhm").find("br").remove();
	}
}*/ 

function confirmBox(selectValue, name){
	var hidden;
	hidden = document.getElementById("hidden").value;
	document.getElementById("hidden2").value=selectValue;
	//alert(hidden);
	if(hidden=="true"){
		//alert("@@@@@@@@@@@@@@@@@@@@");
		if (4 == selectValue) {
			window.clearInterval(setTimeoutReturn);
			$("#_eval").css("display", "none");
			if (0 < $("#reasons li").size()) {
				$("#pop").css("display", "");
				setTimeoutReturn = setTimeout("nonePop('4', '1', '')", 10000);//10秒后关闭差评原因div
			} else {
				nonePop("4", "1", "");
			}
		} else {
			enterEval(selectValue, 1, "");
		}
		$("#_jdc").css("display", "none");
		$("#_jsr").css("display", "none");
		$("#quhaophoto").find("img").remove();
		$("#div_dqhm").find("p").remove();
		$("#div_dqhm").find("br").remove();
	}
	//alert("#####################");
	$("#test").css("display", "");
	document.getElementById("hidden").value="";
	//return hidden;
}
function ok(){
	document.getElementById("hidden").value = "true";
	var ww=document.getElementById("hidden2").value;
	confirmBox(ww,'1');
	$("#_eval").css("display", "none");
	$("#test").css("display", "none");
}
function cancle(){ 
	document.getElementById("hidden").value = "false"; 
	$("#_eval").css("display", "");
	$("#test").css("display", "none");
}  

function showData(data) {
	$("#_welcome").css("display", "none");
	$("#mask_tip").css("display", "none");
	$("#div8").css("display", "none");
	$("#data_show").css("display", "");
	$("#div1").css("display", "");
	
	var index = 0, content = "<tr>";
//	, wfxxcontent = "<tr>";
	$.each(data.content, function (key, val) {
		content += "<th width='20%'>" + key + "</th><td width='30%'>" + val + "</td>";
		index = index + 1;
		if (index % 2 == 0) {
			content += "</tr><tr>";
		}
	});
	if (index % 2 != 0) {
		content += "<th></th><td></td></tr>";
	}
	content +="<tr><td colspan='4' align='center'><p style='color: red'>请核对您的信息，如有错误请及时与业务员联系！</p></td></tr>";
	
//	//违法信息
//	$.each(data.datas, function (key, val) {
//		wfxxcontent += "<tr><th width='20%'>违法时间</th><td width='20%'>" + val.wfsj + "</td><th width='20%'>违法地点</th><td width='20%'>" + val.wfdd + "</td></tr>"+
//					   "<tr><th width='20%'>罚款金额</th><td width='20%'>" + val.fkje + "</td><th width='20%'>是否缴款</th><td width='20%'>" + val.jkbj + "</td></tr>";
//	});
	$("#_showTitle").html(data.title);
	$("#tipTab").find("tr").remove();
	$("#tipTab").append(content);
//	$("#_showWfxxTitle").html("机动车违法信息");
//	$("#wfxxTab").find("tr").remove();
//	$("#wfxxTab").append(wfxxcontent);
}

//推送机动车信息到双屏
function showJDCMessage(datas){
	$("#_jdc").css("display", "");
	var lhpzl = datas.hpzl;
	var lhpzm = datas.hpzm;
	var lclpp1 = datas.clpp1;
	var lcllx = datas.cllx;
	var lzt=datas.zt;
	var ldjrq = datas.djrq;
	var lyxqz = datas.yxqz;
	
	var hpzl = document.getElementById('hpzl');
	hpzl.innerText = lhpzl;
	var hpzm = document.getElementById('hpzm');
	hpzm.innerText = lhpzm;
	var clpp1 = document.getElementById('clpp1');
	clpp1.innerText = lclpp1;
	var cllx = document.getElementById('cllx');
	cllx.innerText = lcllx;
	var yxqz = document.getElementById('yxqz');
	yxqz.innerText = lyxqz;
	var djrq = document.getElementById('djrq');
	djrq.innerText = ldjrq;
	var zt = document.getElementById('zt');
	zt.innerText = lzt;
	
}
//推送驾驶人信息到双屏
function showJSRMessage(datas){
	$("#_jsr").css("display", "");
	var lsfzmhm = datas.sfzmhm;
	var llxzsyzbm = datas.lxzsyzbm;
	var llxzsxxdz = datas.lxzsxxdz;
	var lxm = datas.xm;
	var llxdh = datas.lxdh;
	var lsjhm = datas.sjhm;
	var lbinss = datas.binss;
	
	var lxzsyzbm = document.getElementById('lxzsyzbm');
	lxzsyzbm.innerText = llxzsyzbm;
	var lxzsxxdz = document.getElementById('lxzsxxdz');
	lxzsxxdz.innerText = llxzsxxdz;
	var xm = document.getElementById('xm');
	xm.innerText = lxm;
	var lxdh = document.getElementById('lxdh');
	lxdh.innerText = llxdh;
	var sjhm = document.getElementById('sjhm');
	sjhm.innerText = lsjhm;
	var binss = document.getElementById('binss');
	binss.innerText =lbinss;
}

function showDualScreenInfo(data){
	isOpenIndexCamera=data.isOpenIndexCamera;
	if(isOpenIndexCamera==1){
		$("#quhaophoto").css("display", "none");
		document.getElementById('quhaophoto').innerHTML="";
		msg = "<p align='center' style='color: red;font-size: 30px'>当前办理号码为：</p>";
		msg +="<p id='numbers' align='center' style='color: red;font-size: 60px;font-weight: bold'>";
		msg +=data.number;
		msg +="</p>";
	}else{
		//贵港车管所需求：取号人检验
		msg = "<p align='center' style='color: red;font-size: 20px'>当前办理号码为：</p>";
		msg +="<p id='numbers' align='center' style='color: red;font-size: 35px;font-weight: bold'>";
		msg +=data.number;
		msg +="</p>";
		
		if(data.id!=null && data.id!=""){
			$("#quhaophoto").css("display", "");
			document.getElementById('quhaophoto').innerHTML="";
			onestr="'/queue/images/zwtp.jpg'";
			//str ='<img alt="取号人照片" src="http://www.baidu.com/img/bd_logo1.png" >';
		    str ='<img alt="取号人照片" src="/queue/getVQhPhoto.action?id='+data.id+'"onerror="this.src='+onestr+'"style="width:220px;height:190px;border:2px;" >';
			document.getElementById('quhaophoto').innerHTML=str;
		}
	}
	
//	$("#numbers").html(data.number);
	$("#div_dqhm").find("p").remove();
	$("#div_dqhm").find("br").remove();
	$("#div_dqhm").append(msg);
}
//验证双屏页面状态
function showDualScreenInfoYZ(datas){
	var yzxx = datas.yzxx;
	if(yzxx == 'cjs'){
//		$.post("/number/spyzxx.action",
//		function(data){
//		});
		
		var pos = window.document.location.href.indexOf(window.document.location.pathname);
		var path = window.document.location.href.substring(0,pos);
		$.ajax({type:"POST", cache:false,dataType: "json",
		        url: path+"/queue/number/spyzxx.action?"+parseInt(Math.random()*100000),
		        success: function(data) {
				}
	        });
	}
	
}

//推送照片
function showDualScreenInfoIMG(datas){
	var image = datas.image;
	var message = datas.message;
	var bj = datas.bj;
	$("#div8").css("display", "none");
	$("#mask_tip").css("display", "");
	document.getElementById('mask_tip').innerHTML="";
	$("#tipTab").find("tr").remove();
	if('1' == bj || '1#3' == bj){
		onestr="'/queue/images/queue/waitnext.jpg'";
				//str ='<img alt="取号人照片" src="http://www.baidu.com/img/bd_logo1.png" >';
		str ='<div align="center" style="font-size: 24px;color: red">'+message+'</div><br/><img align="middle" alt="取号人照片" src="'+image+'"onerror="this.src='+onestr+'"style="width:512px;height:512px;border:1px;" >';
		document.getElementById('mask_tip').innerHTML=str;
	}else if('2' == bj || '2#3' == bj){
		str ='<div align="center" style="font-size: 24px;color: red">'+message+'</div><br/><div id="mask_tip_1" align="center" style="width:512px;height:512px;border:1px;" >';
		document.getElementById('mask_tip').innerHTML=str;
		var qrcode = new QRCode('mask_tip_1', {
		text: image,
		width: 512,
		height: 512,
		colorDark : '#000000',
		colorLight : '#ffffff',
		correctLevel : QRCode.CorrectLevel.H
		});
	}
	if('1' == bj || '2' == bj){
		setTimeout(function(){
			$("#mask_tip").css("display", "");
		document.getElementById('mask_tip').innerHTML="";
	//	onestr="'/queue/images/queue/waitnext.jpg'";
	//	str ='<img align="middle" alt="取号人照片" src="/queue/images/queue/waitnext.jpg" style="width:744px;height:591px;border:1px;" >';
	//	document.getElementById('mask_tip').innerHTML=str;
		$("#div8").css("display", "");
		if ($("#myMarq").text() == "") {
			$("#mask_tip").css("display", "");
		} else {
			$("#mask_tip").css("display", "none");
		}
		}, 30000);
	}else if('1#4' == bj || '2#4' == bj){
		$("#mask_tip").css("display", "");
		document.getElementById('mask_tip').innerHTML="";
	//	onestr="'/queue/images/queue/waitnext.jpg'";
	//	str ='<img align="middle" alt="取号人照片" src="/queue/images/queue/waitnext.jpg" style="width:744px;height:591px;border:1px;" >';
	//	document.getElementById('mask_tip').innerHTML=str;
		$("#div8").css("display", "");
		if ($("#myMarq").text() == "") {
			$("#mask_tip").css("display", "");
		} else {
			$("#mask_tip").css("display", "none");
		}
	}
}

function waiPingXinXi(datas){
	var ckh = datas.ckidh;
	var ckm = datas.ckmch;
	var sx = datas.sxhh;
	alert(ckh);
	
}

//打开选号页面
function showXhym(datas){
	var yzxx = datas.yzxx;
	var http = yzxx.substring(4,5);
	if('s' == http){
		var qian1 = yzxx.split(":");
		var dizhi = "http:"+qian[1]+":9080"+qian1[2].substring(4);
		yzxx = dizhi;
	}
	var features = "height=900px,width=1440px,status=no,resizable=no,top=0,left=0,scrollbars=no,titlebar=no,menubar=no,location=no,toolbar=no,z-look=yes,fullcsreen=yes"
							window.open(encodeURI(encodeURI(yzxx,'utf-8')), "_blank", features);
	
//* oNewWindow：被打开的窗口的对象
//* sUrl：被打开窗口的url
//* sName：在哪个窗口打开新的url链接，例如可以为_blank（新窗口）、_top（最外层窗口）等等
//* sFeatures：对窗口的一些控制属性
//* fullscreen：是否为全屏模式（相当于F11的效果），可取值：yes/1、no/0
//* directories：是否带有目录按钮（例如收藏夹中的’链接’目录），可取值同上
//* location：是否带有地址栏，可取值同上
//* channelmode：是否为影院模式，可取值同上
//* menubar：是否带有菜单条，可取值同上
//* resizable：是否可以改变窗口的尺寸，可取值同上
//* scrollbars：是否带有滚动条，可取值同上
//* status：是否带有状态栏，可取值同上
//* titlebar：是否带有标题栏，可取值同上
//* toolbar：是否带有快捷工具栏，可取值同上
//* height：窗口高度
//* width：窗口宽度
//* top：距屏幕上边缘的距离
//* left：距屏幕左边缘的距离
//* bReplace：如果在同一窗口打开新窗口，该值用于指定是否在history中替换原窗口的url链接，可取值：true/false
}


/* 提交评价 */
function enterEval(evaluResult, sub_flag, reason) {
	thanks.play();
	$("#_eval").css("display", "none");
	$("#test").css("display", "none");
	//拍照,sub_flag判断是否拍取 0不拍取
	if (sub_flag == 1) {//手动提交评价
		window.clearInterval(setTimeoutReturn);
		if (isok == 0) {
			//camera();
		}
	}
	
	//flag提交标记(1手动提交2自动);evaluResult评价结果;id工号;base64Code图片base64编码
	var params = 'flag=' + sub_flag + '&evaluResult=' + evaluResult + '&reason=' + reason
		+ /*'&photoBase64=' + encodeURIComponent($("#base64Code").val()) +*/ '&operNum=' + $("#operNum").val()
		+ '&loginIP=' + $("#loginIP").val() + '&stream=' ;//+ encodeURIComponent(obj.HWGetBase64Stream(2));
	$.ajax({type:"POST", cache:false, data:params,
		url:"/queue/number/saveEvalu.action",
		success:function (req) {
			if (req == null || req == "") {
				$("#next").css("display", "none");
			} else {
				$("#next").css("display", "").html("<h5>\u6e29\u99a8\u63d0\u793a</h5>" + req);
				setTimeout("$('#next').css('display', 'none')", 10000);
			}
		}
	});
	
	$("#base64Code").val("");
	$("#div1").css("display", "none");
	$("#" +
	"").css("display", "");
	if ($("#myMarq").text() == "") {
		$("#mask_tip").css("display", "");
	} else {
		$("#mask_tip").css("display", "none");
	}
	$("#quhaophoto").find("img").remove();
}