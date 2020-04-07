<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page import="com.suntendy.queue.util.cache.CacheManager"%>
<%@ page import="java.io.File" %>
<%
	String path = request.getContextPath();
	String path2 = request.getSession().getServletContext().getRealPath("/");
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/" + "TVupload";
	String num = CacheManager.getInstance().getSystemConfig("winMode");
	String TV = CacheManager.getInstance().getTVPath("tvName");
	
	if(null== TV){
		File file = new File(path2+"\\"+"TVupload");
	       String[] tempList = file.list();
	       
	       File temp = null;
	       for (int i = 0; i < tempList.length; i++) {
	          if (path2.endsWith(File.separator)) {
	             temp = new File(path2 +"TVupload/"+ tempList[i]);
	             System.out.println("temp=="+temp);
	             TV=basePath+"/"+tempList[i];
	          }
	       }
	}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>电视窗口</title>
		<link rel="stylesheet" type="text/css"
			href="/queue/queue/css/fetch.css" />
		<style type="text/css">
li {
	list-style: none;
}

.tab3 td {
	vertical-align: top;
}
.date{
position:absolute;
left:300px;
top:25px;
width:900px;
font-size:70px;
font-weight:bold;
}
.video{
position:absolute;
left:10px;
top:100px;
}
.numInfo{
position:absolute;
left:550px;
top:100px;
 border:1px solid #000; 
width:520px;
height:650px;
}
.numInfo2{
position:absolute;
left:10px;
top:100px;
border:1px solid #000; 
width:520px;
height:650px;
background-color:#000;
}
.message{
position:absolute;
left:10px;
top:790px;
width:1058px;
font-size:50px;
font-weight:bold;
color: #FF0000;
}
li {list-style:none;}
	.ul_1 li{
	height:10px;line-height:40px;
	border-bottom: none;
}

</style>
		<script type="text/javascript" src="/queue/js/ajax.js"></script>
		<script type="text/javascript" src="/queue/js/jquery.js"></script>
		<script type="text/javascript" src="/queue/dwr/util.js"></script>
		<script type="text/javascript" src="/queue/dwr/engine.js"></script>
		<script type="text/javascript" src="/queue/window/js/tv_window_yin_chuan.js"></script>
		<script type="text/javascript">
		function onload(){
		    	$.ajax({type: "POST", cache: false, data: null,
		         url: 'number/getAdvertise.action',
		         success: function (req) {alert(req);
		   		 $("#message").html(req);
		         }
	            });
		
			 var value="<%=num%>";
	         if(value == 1){
	            $("#top_u1").css("display", "none"); 
	            $("#top_u2").css("display", "");
	         }else{
	           
	            $("#top_u1").css("display", ""); 
	            $("#top_u2").css("display", "none");
	           // $("#top_u1").css("display", "none");
	            //$("#top_u2").css("display", "");
	         }
	         var date = new Date();       
	         var y = date.getFullYear();       
	         var m = date.getMonth()+1;       
	         var d = date.getDate();       
	         var h = date.getHours();       
	         var i = date.getMinutes();       
	         var s = date.getSeconds();       
	         $("#date").html(y+"年"+(m>9?m:("0"+m))+"月"+(d>9?d:("0"+d))+"日 "); 
         }
	  </script>
	</head>
	<body scroll="no" onload="onload()">

		<div class="inner tv" name="top_u1" id="top_u1" style="display:none;position:relative;background:#000;">
			<ul class="ul_1" name="date_u1" id="date_u1" style="left:10px;top:0;color:#f00;" >
				<li style='white-space:nowrap;display:inline;width:100%;list-style:none;border-bottom: none;font-size: 35px;'>J0001号请到01号窗口    </li>
				<li style='white-space:nowrap;display:inline;width:100%;list-style:none;border-bottom: none;font-size: 35px;'>J0001号请到01号窗口    </li>
				<li style='white-space:nowrap;display:inline;width:100%;list-style:none;border-bottom: none;font-size: 35px;'>J0001号请到01号窗口    </li>
				<li style='white-space:nowrap;display:inline;width:100%;list-style:none;border-bottom: none;font-size: 35px;'>J0001号请到01号窗口    </li>
				<li style='white-space:nowrap;display:inline;width:100%;list-style:none;border-bottom: none;font-size: 35px;'>J0001号请到01号窗口    </li>
				<li style='white-space:nowrap;display:inline;width:100%;list-style:none;border-bottom: none;font-size: 35px;'>J0001号请到01号窗口    </li>
				
			</ul>
		</div>
		<div class="inner tv" name="top_u2" id="top_u2" style="font-family:楷体;display:none;position:relative;">
			<ul class="ul_1" >
				<div class="date" id="date"></div>
				<div  class="numInfo2"  id="numInfo2" >
					<div class="video" id="video" height="800" width="500">
					 <embed height="400" width="500" showstatusbar="0" stretchtofit="0"
						autosize="1" displaysize="0" showcontrols="0" id="MediaPlayer"
						PlayCount="0"  AutoSize="-1"  
						name="MediaPlayer" src="<%=TV %>"
						pluginspage="http://www.microsoft.com/windows/windowsmedia/"
						type="application/x-mplayer2" style="border: 0px; margin: 0px">
					 </embed>
					</div>
				</div>
				<div name="numInfo" id="numInfo"  class="numInfo"></div>
				<div class="message" >
						<marquee  direction="left" scrollamount="3" id="message"></marquee>
				</div>
			</ul>
		</div>
	</body>
</html>