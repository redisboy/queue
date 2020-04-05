<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"  %>
<%@ page isELIgnored="false" import="java.util.*,com.suntendy.queue.count.domain.*"%>
<html>
  <head>
    <title>员工工作量统计</title>
  </head>
  <meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/queue/plugs/ecside/css/ecside_style.css" />
<script type="text/javascript" src="/queue/plugs/ecside/js/prototype_mini.js" ></script>	
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside.js" ></script>
<script type="text/javascript" src="/queue/plugs/ecside/js/ecside_msg_utf8_cn.js" ></script> 
<script type="text/javascript" src="/queue/plugs/time/time.js" ></script> 
<script type="text/javascript" src="/queue/plugs/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="/queue/js/common.js"></script>
	<script type="text/javascript" src="/queue/js/jquery.js"></script>
<script type="text/javascript">
		window.onload=function(){
			tuibiao();
			window.setInterval("tuibiao()",300000);
			}
			function tuibiao(){
				var str1 = "", str2="", str3="", str4="", htmlString="";
//				htmlString+="<table width='230px'  height='172px' align='center' border='1' border='0' cellpadding='0' cellspacing='0'>"+
//				"<tr><td colspan='2' style='text-align:center;font-size:24px;color:#ee0000;'><b>排队信息公示</b></td></tr>";
					$.ajax({type: "POST", cache: false,dataType: "json",async: false,
						url: "jltjyb.action?"+parseInt(Math.random()*100000),
						success: function (req) {
							var str = req.result.split('@');
							var yjstr = req.yjresult.split('@');
							var zonghe = 0;
							var yjzonghe=0;
							var diya = 0;
							var yjdiya=0;
							var shendang = 0;
							var yjshendang=0;
							for(var b=0;b<str.length-1;b++){
								if(str[b].split(':')[0] == '抵押业务' || str[b].split(':')[0] == '解押业务'){
									diya  =Number(diya)+ Number(str[b].split(':')[1]);
								}
								if(str[b].split(':')[0] == '综合业务'){
									zonghe  =Number(zonghe)+ Number(str[b].split(':')[1]);
								}
								if(str[b].split(':')[0] == '审档业务'){
									shendang  =Number(shendang)+ Number(str[b].split(':')[1]);
								}
							}
							for(var j=0;j<yjstr.length-1;j++){
								if(yjstr[j].split(':')[0] == '测试业务类型1' || yjstr[j].split(':')[0] == '123'){
									yjdiya  =Number(yjdiya)+ Number(yjstr[j].split(':')[1]);
								}
								if(yjstr[j].split(':')[0] == '综合业务'){
									yjzonghe  =Number(yjzonghe)+ Number(yjstr[j].split(':')[1]);
								}
								if(yjstr[j].split(':')[0] == '审档业务'){
									yjshendang  =Number(yjshendang)+ Number(yjstr[j].split(':')[1]);
								}
							}
							var zonghepjsj=20;//综合业务一个号预计办理时间（单位分钟）
							var shendangpjsj=20;//审档业务一个号预计办理时间
							var zongheck=6;//综合业务窗口数量
							var shendangck=6;//审档业务窗口数量
							var date = new Date();       
						    var zonghedate = new Date(date.getTime()+zonghepjsj*60*1000*zonghe/zongheck);
						    var shendangdate = new Date(date.getTime()+shendangpjsj*60*1000*shendang/shendangck);
						    var zonghesj = zonghedate.getHours()+':'+zonghedate.getMinutes()+':'+zonghedate.getSeconds();
						    var shendangsj = shendangdate.getHours()+':'+shendangdate.getMinutes()+':'+shendangdate.getSeconds();
						    
							$("#yjzonghe").html(yjzonghe);
							$("#zonghe").html(zonghe);
							$("#zonghesj").html(zonghesj);
							$("#yjshendang").html(yjshendang);
							$("#shendang").html(shendang);
							$("#shendangsj").html(shendangsj);
							
					 }
				});
					
			}
</script>

<body>
	<table width="235" height="175" border="0" align="left" cellpadding="0" cellspacing="0">
  <tr>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#000000">
    <div id="sspdqk" style="background:#000000;">
    	<table width='235px'  height='175px' align='center' border='0' border='0' cellpadding='0' cellspacing='0'>
    		<tr>
    			<td colspan='5' style='text-align:center;font-size:24px;color:#ee0000;'><b>排队信息公示</b></td>
    		</tr>
    		<tr>
    			<td rowspan='1' height='45' width='15%' style='text-align:center;color:#ee0000'>业务类型</td>
    			<td rowspan='1' height='45' width='15%' style='text-align:center;color:#ee0000'>办结人数</td>
    			<td rowspan='1' height='45' width='15%' style='text-align:center;color:#ee0000'>等待人数</td>
    			<td rowspan='1' height='45' width='40%' style='text-align:center;color:#ee0000'>预计全部</br>办结时间</td>
    			<td rowspan='1' height='45' width='15%' style='text-align:center;color:#ee0000'>是否发号</td>
    		</tr>
    		<tr>
    			<td rowspan='1' height='45' width='15%' style='text-align:center;color:#ee0000'>综合业务</td>
    			<td rowspan='1' height='45' width='15%' style='text-align:center;color:#ee0000' id="yjzonghe"></td>
    			<td rowspan='1' height='45' width='15%' style='text-align:center;color:#ee0000' id="zonghe"></td>
    			<td rowspan='1' height='45' width='40%' style='text-align:center;color:#ee0000' id="zonghesj"></td>
    			<td rowspan='1' height='45' width='25%' style='text-align:center;color:#ee0000'>是</td>
    		</tr>
    		<tr>
    			<td rowspan='1' height='45' width='15%' style='text-align:center;color:#ee0000'>综合业务</td>
    			<td rowspan='1' height='45' width='15%' style='text-align:center;color:#ee0000' id="yjshendang"></td>
    			<td rowspan='1' height='45' width='15%' style='text-align:center;color:#ee0000' id="shendang"></td>
    			<td rowspan='1' height='45' width='40%' style='text-align:center;color:#ee0000' id="shendangsj"></td>
    			<td rowspan='1' height='45' width='15%' style='text-align:center;color:#ee0000'>是</td>
    		</tr>
    	</table>
	</div>
	</td>
  </tr>
  <tr>
    <td ><img src="/queue/images/t-6.jpg" width="7" height="11"></td>
    <td height="11" background="/queue/images/t-7.jpg"></td>
    <td ><img src="/queue/images/t-8.jpg" width="8" height="11"></td>
  </tr>
</table>
</body>
</html>
