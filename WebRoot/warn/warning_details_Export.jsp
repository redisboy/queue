<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ page import="java.io.*"%>  
<%@ page import="jxl.write.*"%> 
<%@ page import="jxl.write.WritableWorkbook,jxl.Workbook,jxl.write.WritableSheet,
				jxl.write.Label,com.suntendy.queue.util.ExcelUtil,java.util.*,com.suntendy.queue.log.domain.*"%>

<%  
	List<LogVo> list = (List<LogVo>) request.getSession().getAttribute("list");
    response.reset();  
    response.setContentType("application/vnd.ms-excel;charset=UTF-8");  
    response.setHeader("Content-Disposition", "attachment;filename=" + new String("警告人员详细列表 .xls".getBytes("gb2312"), "iso8859-1"));  
  
    OutputStream os = response.getOutputStream();  
  
    WritableWorkbook wwb = null;  
    wwb = Workbook.createWorkbook(os);//将 WritableWorkbook 写入到输出流  
    WritableSheet ws = wwb.createSheet("警告人员列表 ", 0);//创建第一个sheet  
   // ws.mergeCells(0, 0, 3, 0);//合并单元格  
  	ws.addCell(new Label(0, 1, "员工编号"));
   	ws.addCell(new Label(1,1,"姓名"));
	ws.addCell(new Label(2, 1, "窗口号"));  
	ws.addCell(new Label(3, 1, "警告时间"));  
    for(int i=0;i<list.size();i++) {   
	    ws.addCell(new Label(0, i+3, list.get(i).getCode()));
	    ws.addCell(new Label(1,i+3,list.get(i).getXm()));
	    ws.addCell(new Label(2, i+3, list.get(i).getBarId()));
	    ws.addCell(new Label(3, i+3, list.get(i).getCreateTime()));
	  }
  
    os.flush();  
    wwb.write();  
    wwb.close();  
    os.close();
    out.clear();
	out = pageContext.pushBody();  
%>  