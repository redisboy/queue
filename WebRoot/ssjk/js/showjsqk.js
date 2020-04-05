dwr.engine.setActiveReverseAjax(true);
dwr.engine.setNotifyServerOnPageUnload(true);

window.onload = function(){
		showNum();
}
		
function showNum(){
	$.ajax({type: "POST", cache: false,dataType: "json",async: false,
					url: "getAddressAndStatuss.action?"+parseInt(Math.random()*100000),
					success: function (req) {
						var role = req.role;
						var result = req.result;
						var deptCode;
						var deptCodeName;
						var rowSpan;
						var hallInfo;
						var deptHall;
						var deptHallName;
						var info;           
						var ckInfo;         //窗口信息
						var infoLength;
						var ckInfoLength;
						var htmlString="";
						if(role==0){
							htmlString+="<table width='70%' height='180' align='center' border='1' border='0' cellpadding='0' cellspacing='0' class='tab1'>"+
										"<tr><td colspan='8' height='50' style='text-align:center;font-size:30px;'><b>实时监控情况</b></td></tr>"+
										"<tr><td height='30' width='25%' style='text-align:center;font-size:20px;'>管理部门</td>"+
										"<td height='30' width='25%' style='text-align:center;font-size:20px;'>大厅名称</td>"+
										"<td height='30' width='25%' style='text-align:center;font-size:20px;'>监控类型</td>"+
										"<td height='30' width='25%' style='text-align:center;font-size:20px;'>监控数量</td></tr>";		
							for(var i=0;i<result.length;i++){   //遍历信息集合
								deptCode=result[i].deptCode;
								deptCodeName=result[i].deptCodeName;
								rowSpan=result[i].rowSpan;
								htmlString+="<tr><td rowspan='"+rowSpan+"' width='25%' style='text-align:center;font-size:20px;'>"+deptCodeName+"</td>";
								hallInfo=result[i].hallInfo;				
								for(var a=0;a<hallInfo.length;a++){
									info=hallInfo[a].info;
									deptHallName=hallInfo[a].deptHallName;
									var infoArray=info.split("@");
									infoLength= infoArray.length-1;
									if(a==0){
										htmlString+="<td rowspan='"+infoLength+"' width='25%' style='text-align:center;font-size:20px;'>"+deptHallName+"</td>";
									}else{
										htmlString+="<tr><td rowspan='"+infoLength+"' width='25%' style='text-align:center;font-size:20px;'>"+deptHallName+"</td>";
									}
									for(var b=0;b<infoLength;b++){
										if(b==0){
											htmlString+="<td rowspan='1' height='30' width='25%' style='text-align:center'>"+infoArray[b].split(":")[0]+"</td><td rowspan='1' colspan='2' style='text-align:center'>"+infoArray[b].split(":")[1]+"</td></tr>";
										}else{
											htmlString+="<tr><td rowspan='1' height='30' width='25%' style='text-align:center'>"+infoArray[b].split(":")[0]+"</td><td rowspan='1' colspan='2' style='text-align:center'>"+infoArray[b].split(":")[1]+"</td></tr>";
										}
									}
								}
							}
						}else if(role==1){
							htmlString+="<table width='70%' height='180' align='center' border='1' border='0' cellpadding='0' cellspacing='0' class='tab1'>"+
										"<tr><td colspan='8' style='text-align:center;font-size:30px;'><b>实时监控情况</b></td></tr>"+
										"<td height='30' width='33%' style='text-align:center;font-size:20px;'>大厅名称</td>"+
										"<td height='30' width='33%' style='text-align:center;font-size:20px;'>监控类型</td>"+
										"<td height='30' width='34%' style='text-align:center;font-size:20px;'>监控数量</td></tr>";		
							for(var i=0;i<result.length;i++){   //遍历信息集合
								deptHall=result[i].deptHall;
								deptHallName=result[i].deptHallName;
								info = result[i].info;
								var infoArray=info.split("@");
								infoLength= infoArray.length-1;
								
								htmlString+="<tr><td rowspan='"+infoLength+"' width='33%' style='text-align:center;font-size:20px;'>"+deptHallName+"</td>";
								for(var a=0;a<infoLength;a++){
									if(a==0){
										htmlString+="<td rowspan='1' height='30' width='33%' style='text-align:center'>"+infoArray[a].split(":")[0]+"</td><td width='34%' rowspan='1' colspan='2' style='text-align:center'>"+infoArray[a].split(":")[1]+"</td></tr>";
									}else{
										htmlString+="<tr><td rowspan='1' height='30' width='33%' style='text-align:center'>"+infoArray[a].split(":")[0]+"</td><td width='34%' rowspan='1' colspan='2' style='text-align:center'>"+infoArray[a].split(":")[1]+"</td></tr>";
									}
								}
							}
						}else if(role==2){
							htmlString+="<table width='70%'  align='center' border='1' border='0' cellpadding='0' cellspacing='0' class='tab1'>"+
										"<tr><td colspan='8' style='text-align:center;font-size:30px;'><b>实时监控情况</b></td></tr>"+
										"<tr><td height='30' width='50%' style='text-align:center;font-size:20px;'>监控类型</td>"+
										"<td height='30' width='50%' style='text-align:center;font-size:20px;'>监控数量</td></tr>";		
							for(var i=0;i<result.length;i++){   //遍历信息集合
								deptCode=result[i].deptCode;
								deptHall=result[i].deptHall;
								info = result[i].info;
								ckInfo = result[i].ckInfo;
								var infoArray=info.split("@");
								var ckInfoArray=ckInfo.split("@");
								infoLength= infoArray.length-1;
								ckInfoLength= ckInfoArray.length-1;
								for(var a=0;a<infoLength;a++){
									htmlString+="<tr><td rowspan='1' height='30' width='50%' style='text-align:center;font-size:17px;'>"+infoArray[a].split(":")[0]+"</td>"+
											"<td width='50%' rowspan='1' colspan='2' style='text-align:center;font-size:17px;'>"+infoArray[a].split(":")[1]+"</td></tr>";
								}
								htmlString+="</table>";
								htmlString+="<table width='70%'  align='center' border='1' border='0' cellpadding='0' cellspacing='0' class='tab1'>"+
										  "<tr><td colspan='7' style='text-align:center;font-size:30px;'><b>业务窗口监控情况</b></td></tr>"+
										  "<tr><td height='30'  style='text-align:center;font-size:20px;'>窗口号</td>"+
										  "<td height='30'  style='text-align:center;font-size:20px;'>员工编号</td>"+
										  "<td height='30'  style='text-align:center;font-size:20px;'>员工姓名</td>"+
										  "<td height='30' style='text-align:center;font-size:20px;'>叫号人数</td>"+
										  "<td height='30' style='text-align:center;font-size:20px;'>过号人数</td>"+
										  "<td height='30' style='text-align:center;font-size:20px;'>挂起人数</td>"+
										  "<td height='30' style='text-align:center;font-size:20px;'>评价人数</td></tr>";
								
								for(var b=0;b<ckInfoLength;b++){
									if(""!=ckInfoArray[b].split(":")[0]){
										var xm="",yhbh="";
										if(null!=ckInfoArray[b].split(":")[1]&&"null"!=ckInfoArray[b].split(":")[1]&&""!=ckInfoArray[b].split(":")[1]){
											yhbh=ckInfoArray[b].split(":")[1];
										}
										if(null != ckInfoArray[b].split(":")[2] && "null" != ckInfoArray[b].split(":")[2] && "" != ckInfoArray[b].split(":")[2]){
											xm=ckInfoArray[b].split(":")[2];
										}
										htmlString += "<tr><td height='30'  style='text-align:center;font-size:17px;'>"+ckInfoArray[b].split(":")[0]+"</td>"+
													"<td height='30' style='text-align:center;font-size:17px;'>"+ckInfoArray[b].split(":")[1]+"</td>"+
													"<td height='30' style='text-align:center;font-size:17px;'>"+ckInfoArray[b].split(":")[2]+"</td>"+
													"<td height='30' style='text-align:center;font-size:17px;'>"+ckInfoArray[b].split(":")[3]+"</td>"+
													"<td height='30' style='text-align:center;font-size:17px;'>"+ckInfoArray[b].split(":")[4]+"</td>"+
													"<td height='30' style='text-align:center;font-size:17px;'>"+ckInfoArray[b].split(":")[5]+"</td>"+
													"<td height='30' style='text-align:center;font-size:17px;'>"+ckInfoArray[b].split(":")[6]+"</td></tr>"; 
									}
								}
							}
						}
						htmlString+="</table>";
						$("#sspdqk").html(htmlString);
						
					
					
					
					
						//var countName = req.name.split("@");
						//var countShul = req.sl.split("@");
						//var type="<table width='70%' height='180' align='center' border='1' border='0' cellpadding='0' cellspacing='0' class='tab1'>"+
						//		 "<tr><td colspan='7' style='text-align:center;font-size:30px;'><b>实时取号情况</b></td></tr>"+
						//		 "<tr><td colspan='4' style='text-align:center;font-size:20px;'>业务类型</td><td colspan='3' style='text-align:center;font-size:20px;'>业务数量</td></tr>";
						//for(var i=0;i<countName.length;i++){
						//	if(""!=countName[i].split(":")[0]){
						//		type+="<tr><td colspan='4' style='text-align:center'>"+countName[i].split(":")[0]+"</td><td colspan='3' style='text-align:center'>"+countName[i].split(":")[1]+"</td></tr>";
						//	}
						//}
						//type+="<tr><td colspan='7' style='text-align:center;font-size:30px;'><b>实时排队情况</b></td></tr>"+
						//	  "<td style='text-align:center;font-size:20px;'>窗口号</td><td style='text-align:center;font-size:20px;'>员工编号</td>"+
						//	  "<td style='text-align:center;font-size:20px;'>员工姓名<td style='text-align:center;font-size:20px;'>叫号人数</td><td style='text-align:center;font-size:20px;'>过号人数</td>"+
						//	  "<td style='text-align:center;font-size:20px;'>挂起人数</td><td style='text-align:center;font-size:20px;'>评价人数</td></tr>";
							  
						//for(var i=0;i<countShul.length;i++){
						//	if(""!=countShul[i].split(":")[0]){
						//	var xm="",yhbh="";
						//		if(null != countShul[i].split(":")[1] && "null" != countShul[i].split(":")[1] && "" != countShul[i].split(":")[1]){
						//			yhbh=countShul[i].split(":")[1];
						//		}
						//		if(null != countShul[i].split(":")[2] && "null" != countShul[i].split(":")[2] && "" != countShul[i].split(":")[2]){
						//			xm=countShul[i].split(":")[2];
						//		}
						//		type +="<tr><td style='text-align:center'>"+countShul[i].split(":")[0]+"</td><td style='text-align:center'>"+countShul[i].split(":")[1]+"</td>"+
						//				"<td style='text-align:center'>"+countShul[i].split(":")[2]+"</td><td style='text-align:center'>"+countShul[i].split(":")[3]+"</td><td style='text-align:center'>"+countShul[i].split(":")[4]+"</td>"+
						//				"<td style='text-align:center'>"+countShul[i].split(":")[5]+"</td><td style='text-align:center'>"+countShul[i].split(":")[6]+"</td></tr>";
						//	}
						//}
						//type+="</table>";
						//$("#sspdqk").html(type);
					}
		});	
//		window.setInterval("showNum()",180000);
}

