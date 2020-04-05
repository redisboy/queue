<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ page import="java.io.*"%>  
<%@ page import="jxl.write.*"%> 
<%@ page import="jxl.write.WritableWorkbook,jxl.Workbook,jxl.write.WritableSheet,
				jxl.write.Label,com.suntendy.queue.util.ExcelUtil,java.util.*,com.suntendy.queue.count.domain.*"%>
<%@page import="com.suntendy.queue.queue.domain.Number"%>

<%  
	List<Number> list = (List<Number>)request.getSession().getAttribute("list");
    response.reset();  
    response.setContentType("application/vnd.ms-excel;charset=UTF-8");  
    response.setHeader("Content-Disposition", "attachment;filename=" + new String("证件异常预警查询结果.xls".getBytes("gb2312"), "iso8859-1"));  
  
    OutputStream os = response.getOutputStream();  
  
    WritableWorkbook wwb = null;  
    wwb = Workbook.createWorkbook(os);//将 WritableWorkbook 写入到输出流  
    WritableSheet ws = wwb.createSheet("证件异常预警查询结果", 0);//创建第一个sheet  
   // ws.mergeCells(0, 0, 3, 0);//合并单元格  
  	ws.addCell(new Label(0, 1, "顺序号"));
	ws.addCell(new Label(1, 1, "取号时间"));  
	ws.addCell(new Label(2, 1, "取号人证件号码"));
	ws.addCell(new Label(3, 1, "办理人证件号码"));
	ws.addCell(new Label(4, 1, "流水号"));
    for(int i=0;i<list.size();i++) {   
	    ws.addCell(new Label(0, i+3, list.get(i).getSerialNum()));
	    ws.addCell(new Label(1, i+3, list.get(i).getEnterTime()));
	    ws.addCell(new Label(2, i+3, list.get(i).getIDNumber()));
	    ws.addCell(new Label(3, i+3, list.get(i).getIDNumberB()));
	    ws.addCell(new Label(4, i+3, list.get(i).getLsh()));
	  }
  
    os.flush();  
    wwb.write();  
    wwb.close();  
    os.close();
    out.clear();
	out = pageContext.pushBody();  
%>  