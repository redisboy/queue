<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ page import="java.io.*"%>  
<%@ page import="jxl.write.*"%> 
<%@ page import="jxl.write.WritableWorkbook,jxl.Workbook,jxl.write.WritableSheet,
				jxl.write.Label,com.suntendy.queue.util.ExcelUtil,java.util.*,com.suntendy.queue.evaluation.domain.*"%>

<%  
	List<AllDetail> list = (List<AllDetail>) request.getSession().getAttribute("list");
    response.reset();  
    response.setContentType("application/vnd.ms-excel;charset=UTF-8");  
    response.setHeader("Content-Disposition", "attachment;filename=" + new String("详细评价记录.xls".getBytes("gb2312"), "iso8859-1"));  
  
    OutputStream os = response.getOutputStream();  
  
    WritableWorkbook wwb = null;  
    wwb = Workbook.createWorkbook(os);//将 WritableWorkbook 写入到输出流  
    WritableSheet ws = wwb.createSheet("详细评价记录", 0);//创建第一个sheet  

  	ws.addCell(new Label(0, 1, "窗口名称"));   
	ws.addCell(new Label(1, 1, "姓名"));  
	ws.addCell(new Label(2, 1, "编号"));
	ws.addCell(new Label(3, 1, "业务类型"));
	ws.addCell(new Label(4, 1, "取票时间"));
	ws.addCell(new Label(5, 1, "开始时间"));
	ws.addCell(new Label(6, 1, "结束时间"));
	ws.addCell(new Label(7, 1, "评价时间"));
	ws.addCell(new Label(8, 1, "评价结果"));
	ws.addCell(new Label(9, 1, "等待时间"));
	ws.addCell(new Label(10, 1, "办理时间"));
    for(int i=0;i<list.size();i++) {   
	    ws.addCell(new Label(0, i+3, list.get(i).getBarid()));
	    ws.addCell(new Label(1, i+3, list.get(i).getName()));
	    ws.addCell(new Label(2, i+3, list.get(i).getCode()));
	    ws.addCell(new Label(3, i+3, list.get(i).getQueuename()));
	    ws.addCell(new Label(4, i+3, list.get(i).getEntertime()));
	    ws.addCell(new Label(5, i+3, list.get(i).getBegintime()));
	    ws.addCell(new Label(6, i+3, list.get(i).getEndtime()));
	    ws.addCell(new Label(7, i+3, list.get(i).getValuetime()));
	    ws.addCell(new Label(8, i+3, list.get(i).getValueName()));
	    ws.addCell(new Label(9, i+3, list.get(i).getWaits()));
	    ws.addCell(new Label(10, i+3, list.get(i).getWorks()));
	  }
  
    os.flush();  
    wwb.write();  
    wwb.close();  
    os.close();
    out.clear();
	out = pageContext.pushBody();  
%>  