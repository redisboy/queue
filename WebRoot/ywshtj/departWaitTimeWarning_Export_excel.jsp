<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ page import="java.io.*"%>  
<%@ page import="jxl.write.*"%> 
<%@ page import="jxl.write.WritableWorkbook,jxl.Workbook,jxl.write.WritableSheet,
				jxl.write.Label,com.suntendy.queue.util.ExcelUtil,java.util.*,com.suntendy.queue.ywshtj.domain.*"%>

<%  
	List<YwShTj> list = (List<YwShTj>)request.getSession().getAttribute("list");
    response.reset();  
    response.setContentType("application/vnd.ms-excel;charset=UTF-8");  
    response.setHeader("Content-Disposition", "attachment;filename=" + new String("管理部门等候时间预警.xls".getBytes("gb2312"), "iso8859-1"));  
  
    OutputStream os = response.getOutputStream();  
  
    WritableWorkbook wwb = null;  
    wwb = Workbook.createWorkbook(os);//将 WritableWorkbook 写入到输出流  
    WritableSheet ws = wwb.createSheet("管理部门等候时间预警", 0);//创建第一个sheet  

  	ws.addCell(new Label(0, 1, "部门编号"));
  	ws.addCell(new Label(1, 1, "人数"));
	ws.addCell(new Label(2, 1, "平均等待时间"));  
    for(int i=0;i<list.size();i++) {   
	    ws.addCell(new Label(0, i+3, list.get(i).getDeptcode()));
	    ws.addCell(new Label(1, i+3, list.get(i).getTotal()));
	    ws.addCell(new Label(2, i+3, list.get(i).getAve_waittime()));
	  }
  
    os.flush();  
    wwb.write();  
    wwb.close();  
    os.close();
    out.clear();
	out = pageContext.pushBody();  
%>  