<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ page import="java.io.*"%>  
<%@ page import="jxl.write.*"%> 
<%@ page import="jxl.write.WritableWorkbook,jxl.Workbook,jxl.write.WritableSheet,
				jxl.write.Label,com.suntendy.queue.util.ExcelUtil,java.util.*,com.suntendy.queue.tuiban.domain.*"%>

<%  
	List<TuiBan> list = (List<TuiBan>) request.getSession().getAttribute("list");
    response.reset();  
    response.setContentType("application/vnd.ms-excel;charset=UTF-8");  
    response.setHeader("Content-Disposition", "attachment;filename=" + new String("退办信息列表 .xls".getBytes("gb2312"), "iso8859-1"));  
  
    OutputStream os = response.getOutputStream();  
  
    WritableWorkbook wwb = null;  
    wwb = Workbook.createWorkbook(os);//将 WritableWorkbook 写入到输出流  
	    WritableSheet ws = wwb.createSheet("退办信息列表", 0);//创建第一个sheet  
   // ws.mergeCells(0, 0, 3, 0);//合并单元格  
  	ws.addCell(new Label(0, 1, "身份证号"));
   	ws.addCell(new Label(1,1,"记录人员"));
	ws.addCell(new Label(2, 1, "记录时间")); 
	ws.addCell(new Label(3, 1, "退办原因"));  
    for(int i=0;i<list.size();i++) {   
	    ws.addCell(new Label(0, i+3, list.get(i).getIdnumber()));
	    ws.addCell(new Label(1,i+3,list.get(i).getJiluren()));
	    ws.addCell(new Label(2, i+3, list.get(i).getJilutime()));
	    ws.addCell(new Label(3, i+3, list.get(i).getYuanyin()));
	  }
  
    os.flush();  
    wwb.write();  
    wwb.close();  
    os.close();
    out.clear();
	out = pageContext.pushBody();  
%>  