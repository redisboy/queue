<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String clientIp = request.getHeader("x-forwarded-for");//客户端IP
	if (null == clientIp || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
		clientIp = request.getHeader("Proxy-Client-IP");
	}
	if (null == clientIp || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
		clientIp = request.getHeader("WL-Proxy-Client-IP");
	}
	if (null == clientIp || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
		clientIp = request.getRemoteAddr();
	}
	
    String serverIP = "50.104.78.8:8080"; //服务端IP 192.168.1.105:8088 10.118.243.58:8080 10.119.20.103:8080
    String serverDwrPath = "http://" + serverIP + "/queue/dwr";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>拍照窗口</title>
		<link rel="stylesheet" type="text/css" href="css/styles.css">
	</head>
	<STYLE>
		td {
			font-family: "宋体";
			font-size: 9pt;
		}
		#fods UL {
			LIST-STYLE-TYPE: none;
			margin: 0px;
			padding: 0px;
		}
		
		LI {
			PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-TOP: 0px; LIST-STYLE-TYPE: none
		}
		.dis {
			DISPLAY: block
		}
		.undis {
			DISPLAY: none
		}
		#fods #Fod_list DIV {
			FONT-WEIGHT: bold; FLOAT: left; CURSOR: pointer; LINE-HEIGHT: 20px; TEXT-ALIGN: center
		}
		#fods #Fod_list P {
			PADDING-RIGHT: 0px; PADDING-LEFT: 0px; FLOAT: left; PADDING-BOTTOM: 0px; MARGIN: 0px; WIDTH: 4px; PADDING-TOP: 0px; HEIGHT: 20px
		}
		#fods #Fod_list DIV.cardb {
			WIDTH: 117px;
			COLOR:#000;
			PADDING-TOP: 5px;
			HEIGHT: 22px;
			background-image: url(images/btn_1.gif);
		}
		#fods #Fod_list .sb {
			WIDTH: 117px;
			COLOR:#fff;
			PADDING-TOP: 5px;
			HEIGHT: 22px;
			background-image: url(images/btn_2.jpg);
		}
		</STYLE>
	<SCRIPT language=javascript>
		  function fodb(obj)
		  {
			var pb = obj.parentNode.parentNode.parentNode.parentNode;
			var pb1 = obj.parentNode.getElementsByTagName("div");
			var tb = pb.getElementsByTagName("tr")[1].getElementsByTagName("ul");
			var nb = pb1.length;
			for(i=0;i<nb;i++)
			{
				if(pb1[i] == obj)
				{
					pb1[i].className = "sb";
					tb[i].className = "dis";
				}
				else
				{
					pb1[i].className = "cardb";
					tb[i].className = "undis";				
				}
			}
		  }
		   function fodb2(obj)
				  {
					var pb = obj.parentNode.parentNode.parentNode.parentNode;
					var pb1 = obj.parentNode.getElementsByTagName("div");
					var tb = pb.getElementsByTagName("tr")[1].getElementsByTagName("ul");
					var nb = pb1.length;
					for(i=0;i<nb;i++)
					{
						if(pb1[i] == obj)
						{
							pb1[i].className = "sb2";
							tb[i].className = "dis";
						}
						else
						{
							pb1[i].className = "cardb2";
							tb[i].className = "undis";				
						}
					}
				  }
		</SCRIPT>

	<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td background="/queue/images/top_bg.jpg" style="text-align:middle">
    	<img src="/queue/images/top.jpg" width="999" height="108" />
    </td>
  </tr>
</table>
	   <TABLE align="center"  id=fods cellSpacing=0 cellPadding=0  border=0 style="width:600px;margin:0 auto;margin-top: 20px;">
		  <TBODY>
		    <TR>
		      <td width="5" background="images/cardbbg2.gif"></td>
		      <TD height=33 vAlign=top background="images/cardbbg2.gif" id=Fod_list><DIV class=sb onclick=fodb(this)>摄像头采集区域</DIV>
		        <P></P>
		        <DIV class=cardb onclick=fodb(this)>手写板采集区域</DIV>
		        <P></P>
		        <DIV class=cardb onclick=fodb(this)>高拍仪</DIV>
		        <P></P>
		      </TD>
		    </TR>
		    <TR>
		      <TD width="100%"  style="text-align:center;" vAlign=top class="notopborder"  colspan="2">
		         <UL class=dis>
		          <LI>
		            <table width="80%" border="0" style="margin:0 auto;">
		              <tr>
		                  <td><div id="showzp" style="text-align:center"><img src="images/u13.png" width="420" height="420"></div></td>
		                  <td style="text-align:left; vertical-align:top;">
		                  		<div id="zpjg" name="zpjg" style="padding-top:5px;"><img src="images/u13.png" alt="" width="77" height="77"></div>
		                  </td>
		              </tr>
		                <tr>
		                  <td style="text-align:center">
		                    <input type="button" value="打印" onclick="window.open('dyzp.jsp')" style="width:100px; height:50px; text-align: center ; font-family:'Arial'; font-size: 13px; color:#000000; font-style:normal; font-weight:normal; text-decoration:none"> &nbsp; 
		                  	<span id="openSxt"><INPUT id=u17  type=submit value="打开摄像头" onClick="po.open('<%=serverIP %>',0);" style="width:100px; height:50px; ; ; text-align: center ; font-family:'Arial'; font-size: 13px; color:#000000; font-style:normal; font-weight:normal; text-decoration:none" ></span>&nbsp;
							<span id="pz"></span>
		                  </td>
		                  <td>&nbsp;</td>
		                </tr>
		              </table>
		            
		          </LI>
		        </UL>
		        <UL class=undis>
		          <LI> 
		            <table width="80%" border="0" style="margin:0 auto;">
		              <tr>
		                  <td>
		                  		<div id="showImg" style="text-align:center"><img src="images/u13.png" width="420" height="420"></div>
		                  		<div id="showSignature" style="text-align:center"></div>
		                  </td>
		                  <td style="text-align:left; vertical-align:top;">
		                  </td>
		              </tr>
		                <tr>
		                  <td style="text-align:center">
		                  	<span id="openSignature"><INPUT id=u17  type=submit value="打开手写板" onClick="po.open('<%=serverIP %>',1);" style="width:100px; height:50px; ; ; text-align: center ; font-family:'Arial'; font-size: 13px; color:#000000; font-style:normal; font-weight:normal; text-decoration:none" >&nbsp;</span>
							<span id="SignatureSave"></span>
		                  </td>
		                  <td>&nbsp;</td>
		                </tr>
		              </table>
		            
		          </LI>
		        </UL>
		        <UL class=undis>
		          <LI> 
		            <table width="80%" border="0" style="margin:0 auto;">
		              <tr>
		                  	<td style="text-align:center;font-size:30px;">
		                  	<!--<div id="zllx">
		                  		<select class="sel1" id="zlType"></select>
		                  	</div>-->
			                   资料类型:<select id="zllx" name="zllx" style="text-align:center;font-size:30px;">  
								  <option value ="0">身份证A面</option>  
								  <option value ="1">身份证B面</option>  
								  <option value ="2">登记证书</option>  
								  <option value ="3">行驶证</option>  
								  <option value ="4">驾驶证</option>  
								  <option value ="5">企业代码证书</option>
								  <option value ="6">士兵证</option> 
								  <option value ="7">退伍证书</option> 
								  <option value ="8">护照</option> 
								  <option value ="9">号牌号码</option> 
								  <option value ="10">代办人证件A面</option> 
								  <option value ="11">代办人证件B面</option> 
								  <option value ="12">居住证</option> 
							   </select>  
							</td>
		              </tr>
		              <tr>
		                  <td><div id="showGpy" style="text-align:center"><img src="images/u13.png" width="420" height="420"></div></td>
		                  <td style="text-align:left; vertical-align:top;">
		                  		<div id="gpyjg" name="gpyjg" style="padding-top:5px;"><img src="images/u13.png" alt="" width="77" height="77"></div>
		                  </td>
		              </tr>
		                <tr>
		                  <td style="text-align:center">
		                  	<span id="openGpy"><INPUT id=u17  type=submit value="打开高拍仪" onClick="po.open('<%=serverIP %>',2);" style="width:100px; height:50px; ; ; text-align: center ; font-family:'Arial'; font-size: 13px; color:#000000; font-style:normal; font-weight:normal; text-decoration:none" ></span>&nbsp;
							<span id="gpy"></span>
		                  </td>
		                  <td>&nbsp;</td>
		                </tr>
		              </table>
		            
		          </LI>
		        </UL>
		      </TD>
		    </TR>
		    
		  </TBODY>
		</TABLE>
		<div align=left id="blink"></div>
				   
	   <input type="hidden" id="base64Code" name="base64Code" />
	   <input type="hidden" id="clientIp" name="clientIp" value="<%=clientIp %>" />
	</body>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="<%=serverDwrPath%>/util.js"></script>
	<script type="text/javascript" src="<%=serverDwrPath%>/engine.js" id="ldEngine"></script>
	<script type="text/javascript" src="<%=serverDwrPath%>/interface/JspRegister.js"></script>
	<script type="text/javascript" src="js/capturePhoto.js"></script>
<!--    <script type="text/javascript">sw.login("012371", "<%=clientIp%>", "<%=serverIP%>", "<%=serverDwrPath%>");</script>-->
	<SCRIPT LANGUAGE=javascript FOR=jsrzP EVENT="FireCaptureZpEvent(pszInfo1)">
	
	<!--
		picPhoto = pszInfo1;
	//-->
	</SCRIPT>
	<script type="text/javascript">
	//window.onload = function(){
	//	showZllx();
	//}
	function showZllx(){
	/**	$.ajax({type: "POST", cache: false, async: false, dataType: "json",
			        url: 'http://<%=serverIP%>/queue/number/getAllZllx_Ajax.action?'+parseInt(Math.random()*100000),
			        success: function(request) {
			            var oSelect = document.getElementById("zlType");
			            oSelect.options.length=0;
						oSelect.options.add(new Option("-证件类型-","0"));
				        $.each(request, function(i, obj) {
				            oSelect.options.add(new Option(obj.mc,obj.dm));
				        });
				    }
		});*/
	}
	
	var picPhoto="";
	function send() {
        var soapMessage ='<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">' +
			'<SOAP-ENV:Header/>' +
			'<SOAP-ENV:Body>' +
				'<ns:showScreenInfo xmlns:ns="http://webservice.queue.suntendy.com/">' +
					'<loginIP><%=clientIp%></loginIP>' +
					'<tableTitle>'+$("#title").val()+'</tableTitle>' +
					'<screenInfo>'+$("#content").val()+'</screenInfo>' +
				'</ns:showScreenInfo>' +
			'</SOAP-ENV:Body>' +
		'</SOAP-ENV:Envelope>';
		
	    $.ajax({
	        type: 'POST',
	        cache: false,
	        async: false,
	        contentType: 'text/xml; charset="UTF-8"',
	        url: 'http://<%=serverIP%>/queue/ws/OpenInter',
	        data: soapMessage,
	        dataType: "xml",
	        success: function(req) {
	           alert(true);
	        }
	    });
	}
	var fn = (function() {
			var blink = document.getElementById('blink');
			return function() {
				blink.style.color = blink.style.color == "red" ? "yellow" : "red";
				setTimeout(closeblink, 15000);
			}
	})()
	function closeblink(){
		$("#blink").html("");
		window.location.reload();
	}
	</script>
	<SCRIPT ID=clientEventHandlersJS LANGUAGE=javascript>
		<!--
		function btnCapture_onclick(){
		    jsrzP.sZpImgFilePath = "c:\\99999.jpg";
		    if(jsrzP.OnCaptureZp()){
		        var img_src='c:\\99999.jpg?t='+Math.random();
		    	$("#zpjg").html("<img name='zp' style='width:150px;height:170px;' src='"+img_src+"'/>");
		    	$("#base64Code").val(picPhoto);
		    }
		}
		//-->
	</SCRIPT>
	<style type="text/css">
		<!--
		body,td,th {
		font-family: 宋体;
		font-size: 9pt;
		}
		body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
		background:url(../images/d1.gif);
		}
		-->
	</style>
</html>