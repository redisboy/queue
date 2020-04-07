<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="com.suntendy.queue.agent.vo.AgentVO"%>
<html>
	<head>
		<title></title>
		
	</head>
	<%
		AgentVO agentVO = (AgentVO) request.getAttribute("agentInfo");
	%>
	<script type="text/javascript">
		function print(){
			var myDoc={
				documents:document,
				copyrights : '杰创软件拥有版权  www.jatools.com'
			};
			jatoolsPrinter.printPreview(myDoc);   		//打印预览
			//jatoolsPrinter.print(myDoc,false);	    //打印
			 
		};
	</script>
	
	<body style="width:300mm">
		<!-- 加载打印控件 -->
		<OBJECT ID='jatoolsPrinter'	CLASSID='CLSID:B43D3361-D075-4BE2-87FE-057188254255' codebase='../plugs/cab/jatoolsPrinter.cab#version=8,5,2549' width='0' height='0'  >打印控件加载失败</OBJECT>
		<div id="page1" style="width:91mm;height:127mm;border:1px solid black;text-align:center;float:left;"  >
		
			<div style="font-size:30px;font-weight:bolder;margin-top:13mm;">机动车业务代办卡</div>
			<div style="border:1px solid black;margin-top:10mm;width:25mm;height:34mm;">
				<div style="margin-top:11mm;font-weight:blod;font-size:17px;">照</div>
				<div style="margin-bottom:11mm;font-weight:blod;font-size:17px;">片</div>
			</div>
			<div style="margin-top:10mm;margin-left:13mm;font-weight:bold;text-align:left;font-size:17px;">
				服务单位: <span><%=agentVO.getUnit()%></span>
			</div>
			<div style="margin-top:6mm;margin-left:13mm;font-weight:bold;text-align:left;font-size:17px">
				姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名: <span><%=agentVO.getName()%></span> 
			</div>
			<div style="margin-top:6mm;margin-left:13mm;font-weight:bold;text-align:left;font-size:17px">
				代办业务: <span>新车 在用车 年检</span>
			</div>
		</div>
		<div id="page2" style="width:91mm;height:127mm;border:1px solid black;text-align:center;float:left;margin-left:20mm;">
			<div style="margin-top:13mm;font-weight:bold;font-size:25px;">说明</div>
			
			<div style="margin-top:5mm;">
				<table>
					<tr>
						<td style="text-align:right;font-weight:bold;font-size:20px;">1、</td><td style="text-align:left;font-weight:bold;font-size:20px;">代办人员办理机动车业务</td>
					</tr>
					<tr>
						<td></td><td style="text-align:left;font-weight:bold;font-size:20px;">时必须佩带此卡。</td>
					</tr>
					<tr>
						<td style="text-align:right;font-weight:bold;font-size:20px;">2、</td><td style="text-align:left;font-weight:bold;font-size:20px;">本卡应当妥善保管，不得</td>
					</tr>
					<tr>
						<td></td><td style="text-align:left;font-weight:bold;font-size:20px;">转借、涂改、伪造。</td>
					</tr>
					<tr>
						<td style="text-align:right;font-weight:bold;font-size:20px;">3、</td><td style="text-align:left;font-weight:bold;font-size:20px;">如有丢失，请及时申请补</td>
					</tr>
					<tr>
						<td></td><td style="text-align:left;font-weight:bold;font-size:20px;">领。</td>
					</tr>
				</table>
			</div>
			<div style="margin-top:10mm;font-weight:bold;font-size:22px;">
				江门市公安交通管理局监制
			</div>
			<div style="margin-top:5mm;">
				<table width="98%">
					<tr>
						<td style="padding-bottom:5mm;font-size:15px;font-weight:bold;text-align:right;width:33%;">编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</td><td style="text-align:left;padding-bottom:5mm;font-size:15px;font-weight:bold;width:65%"><%=agentVO.getId() %></td>
					</tr>
					<tr>
						<td style="font-size:15px;font-weight:bold;text-align:right;width:33%" >证件编号:</td><td style="text-align:left;font-size:15px;font-weight:bold;width:65%"><%=agentVO.getIdCard() %></td>
					</tr>
				</table>
			</div>
		</div>
		<div style="margin-left:10mm;float:left;width:80mm;">
			<div style="margin-top:40mm;">
				<span style="font-size:20px;font-weight:bold;">&nbsp;&nbsp;&nbsp;&nbsp;左侧为打印预览,请确认信息无误后点击打印按钮进行打印。</span>
			</div>
			<div>
				<input type="button" value="打  印" style="font-size:25px;font-weight:bold;height:13mm;width:25mm;margin:20mm 0 0 30mm;" onclick="print()"/>
			</div>
		</div>
	</body>
</html>
