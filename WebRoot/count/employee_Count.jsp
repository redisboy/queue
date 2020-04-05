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
			window.setInterval("tuibiao()",600000);
			}
			function tuibiao(){
				var str1 = "", str2="", str3="", str4="";
					$.ajax({type: "POST", cache: false,dataType: "json",async: false,
						url: "ygtjyb.action?"+parseInt(Math.random()*100000),
						success: function (req) {
							$.each(req, function(i, obj) {
								if(null != obj.name && "underfind" != obj.name){
								str1 += "<category label='"+obj.name+"' />";
								str2 += "<set value='"+obj.jiaohaocount+"' />";
								str3 += "<set value='"+obj.gongzuocount+"' />";
								str4 += "<set value='"+obj.manyicount+"' />";
								}
							});
							var strXML = "<chart xAxisName='' yAxisName='' caption='工作量、满意度实时统计' legendBgColor='#000000' legendBorderColor='#FF0000' hoverCapBgColor='#000000' baseFontSize='16' outCnvBaseFontSize='24' numDivLines='0' showToolTip='0' shownames='1' showvalues='1' showSum='1' useRoundEdges='1' bgColor='#000000' bgAlpha='100' bgRatio='100' showCanvasBg='0' canvasBgColor='#000000' baseFontColor='#FF0000' canvasBgAlpha='100' canvasBorderColor='#FF0000'>";
							strXML += "<categories>"+str1+"</categories>";
							strXML += "<dataset seriesName='叫号量(红)' color='#FF0000' showValues=''>"+str2+"</dataset>";
							strXML += "<dataset seriesName='工作量(绿)' color='#00FF00' showValues=''>"+str3+"</dataset>";
							strXML += "<dataset seriesName='满意量(黄)' color='#FFFF00' showValues=''>"+str4+"</dataset></chart>";
						
						    var chart1 = new FusionCharts("/queue/plugs/FusionCharts/MSColumn2D.swf","chart1ID","1630","352","0","0");
						 	chart1.setDataXML(strXML);
						 	chart1.render("chart1div");
					 	}
					});
					
			}
</script>

<body>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="100" valign="middle" bgcolor="#f7f7f7">
    <div style="margin-left:16px;position:absolute;left:0;top:0;background:#000;">
			<table width="60%">
				<TR>
					<TD height="20" bgcolor="#F7F7F7">
						<div id="chart1div" align="center"></div>
					</TD>
				</TR>
			</table>
	</div>
	<br>
    <form action="" method="post" name="form1">
     <input name="flag" id="flag" type="hidden" value="1"/>
         
	</form>
	<br/>
    </td>
  </tr>
</table>
</body>
</html>
