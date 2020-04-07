<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ page import="java.io.*"%>  
<%@ page import="jxl.write.*"%> 
<%@ page import="jxl.write.WritableWorkbook,jxl.Workbook,jxl.write.WritableSheet,
				jxl.write.Label,com.suntendy.queue.util.ExcelUtil,java.util.*,com.suntendy.queue.count.domain.*"%>

<%  
	List<AgentWaitCount> list = (List<AgentWaitCount>)request.getSession().getAttribute("list");
    response.reset();  
    response.setContentType("application/vnd.ms-excel;charset=UTF-8");  
    response.setHeader("Content-Disposition", "attachment;filename=" + new String("代理人排队情况统计.xls".getBytes("gb2312"), "iso8859-1"));  
  
    OutputStream os = response.getOutputStream();  
  
    WritableWorkbook wwb = null;  
    wwb = Workbook.createWorkbook(os);//将 WritableWorkbook 写入到输出流  
    WritableSheet ws = wwb.createSheet("代理人排队情况统计", 0);//创建第一个sheet  
   // ws.mergeCells(0, 0, 3, 0);//合并单元格  
  	ws.addCell(new Label(0, 1, "代理人身份证号码"));
	ws.addCell(new Label(1, 1, "代理人姓名"));  
	ws.addCell(new Label(2, 1, "正常办理"));
	ws.addCell(new Label(3, 1, "过号"));
	ws.addCell(new Label(4, 1, "未呼叫"));
	ws.addCell(new Label(5, 1, "正在办理"));
	ws.addCell(new Label(6, 1, "排队数合计"));
    for(int i=0;i<list.size();i++) {   
	    ws.addCell(new Label(0, i+3, list.get(i).getIdcard()));
	    ws.addCell(new Label(1, i+3, list.get(i).getName()));
	    ws.addCell(new Label(2, i+3, list.get(i).getX6()));
	    ws.addCell(new Label(3, i+3, list.get(i).getX3()));
	    ws.addCell(new Label(4, i+3, list.get(i).getX1()));
	    ws.addCell(new Label(5, i+3, list.get(i).getX2()));
	    ws.addCell(new Label(6, i+3, list.get(i).getCount()));
	  }
  
    os.flush();  
    wwb.write();  
    wwb.close();  
    os.close();
    out.clear();
	out = pageContext.pushBody();  
%>  