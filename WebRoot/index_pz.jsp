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
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>取号拍照窗口</title>
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
			background-image: url(right/images/btn_1.gif);
		}
		#fods #Fod_list .sb {
			WIDTH: 117px;
			COLOR:#fff;
			PADDING-TOP: 5px;
			HEIGHT: 22px;
			background-image: url(right/images/btn_2.jpg);
		}
		</STYLE>
		<script type="text/javascript" >
		var sxh = window.opener.sxh.value;
		var sxhid = window.opener.sxhid.value;
		</script>
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
	   <TABLE id=fods cellSpacing=0 cellPadding=0  border=0 style="width:600px;margin:0 auto;">
		  <TBODY>
		    <TR>
		      <td width="5" background="right/images/cardbbg2.gif"></td>
		      <TD height=33 vAlign=top background="right/images/cardbbg2.gif" id=Fod_list><DIV class=sb onclick=fodb(this)>摄像头采集区域</DIV>
		       </TD>
		    </TR>
		    <TR>
		      <TD width="100%"  style="text-align:center;" vAlign=top class="notopborder"  colspan="2">
		         <UL class=dis>
		          <LI>
		            <table width="80%" border="0" style="margin:0 auto;">
		              <tr>
		                  <td><div id="showzp" style="text-align:center"><img src="right/images/u13.png" width="420" height="420"></div></td>
		                  <td style="text-align:left; vertical-align:top;">
		                  		<div id="zpjg" name="zpjg" style="padding-top:5px;"><img src="right/images/u13.png" alt="" width="77" height="77"></div>
		                  </td>
		              </tr>
		                <tr>
		                  <td style="text-align:center">
		                  	<span id="openSxt">
		                  	<!-- <INPUT id=u17  type=submit value="打开摄像头" onClick="po.open();" style="width:100px; height:50px; ; ; text-align: center ; font-family:'Arial'; font-size: 13px; color:#000000; font-style:normal; font-weight:normal; text-decoration:none" > -->
		                  	</span>&nbsp;
							<span id="pz"></span>
		                  </td>
		                  <td>&nbsp;</td>
		                </tr>
		              </table>
		            
		          </LI>
		        </UL>
		        <UL class=undis>
		          <LI> 
		          </LI>
		        </UL>
		      </TD>
		    </TR>
		  </TBODY>
		</TABLE>
	   
	   
	   <input type="hidden" id="base64Code" name="base64Code" />
	   <input type="hidden" id="clientIp" name="clientIp" value="<%=clientIp %>" />
	</body>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="/util.js"></script>
	<script type="text/javascript" src="/queue/dwr/engine.js" id="ldEngine"></script>
	<script type="text/javascript" src="/queue/dwr/interface/JspRegister.js"></script>
	<script type="text/javascript" src="queue/js/index_Photo.js"></script>
	<SCRIPT LANGUAGE=javascript FOR=jsrzP EVENT="FireCaptureZpEvent(pszInfo1)">
	<!--
		picPhoto = pszInfo1;
	//-->
	</SCRIPT>
	<script type="text/javascript">var picPhoto="";</script>
	<SCRIPT ID=clientEventHandlersJS LANGUAGE=javascript>
		<!--
		function btnCapture_onclick(){
		    jsrzP.sZpImgFilePath = "c:\\99999.jpg";
		    if(jsrzP.OnCaptureZp()){
		    	$("#zpjg").html("<img name='zp' style='width:150px;height:170px;' src='c:\\99999.jpg'/>");
		    	$("#base64Code").val(picPhoto);
		    }
		}
		//-->
	</SCRIPT>
</html>