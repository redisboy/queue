<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ page import="java.io.*"%>  
<%@ page import="jxl.write.*"%> 
<%@ page import="jxl.write.WritableWorkbook,jxl.Workbook,jxl.write.WritableSheet,
				jxl.write.Label,com.suntendy.queue.util.ExcelUtil,java.util.*,com.suntendy.queue.count.domain.*"%>

<%  
	List<GetNumberInfoCount> list = (List<GetNumberInfoCount>)request.getSession().getAttribute("list");
    response.reset();  
    response.setContentType("application/vnd.ms-excel;charset=UTF-8");  
    response.setHeader("Content-Disposition", "attachment;filename=" + new String("取号信息查询.xls".getBytes("gb2312"), "iso8859-1"));  
  
    OutputStream os = response.getOutputStream();  
  
    WritableWorkbook wwb = null;  
    wwb = Workbook.createWorkbook(os);//将 WritableWorkbook 写入到输出流  
    WritableSheet ws = wwb.createSheet("取号信息查询", 0);//创建第一个sheet  
   // ws.mergeCells(0, 0, 3, 0);//合并单元格  
  		ws.addCell(new Label(0, 1, "经办人"));
	ws.addCell(new Label(1, 1, "实际取号号码"));  
  	ws.addCell(new Label(2, 1, "取号时间"));
	ws.addCell(new Label(3, 1, "取号机ip地址"));  
	ws.addCell(new Label(4, 1, "办理业务"));
	ws.addCell(new Label(5, 1, "取号值"));
	ws.addCell(new Label(6, 1, "证件类型"));
	ws.addCell(new Label(7, 1, "证件号码"));
	ws.addCell(new Label(8, 1, "是否代理人"));
	ws.addCell(new Label(9, 1, "业务笔数"));
	ws.addCell(new Label(10, 1, "流水号"));
    for(int i=0;i<list.size();i++) {   
     	ws.addCell(new Label(0, i+3, list.get(i).getXm()));
	    ws.addCell(new Label(1, i+3, list.get(i).getSerialNum()));
	    ws.addCell(new Label(2, i+3, list.get(i).getEntertime()));
	    ws.addCell(new Label(3, i+3, list.get(i).getServerip()));
	    ws.addCell(new Label(4, i+3, list.get(i).getCodequeue()));
	    ws.addCell(new Label(5, i+3, list.get(i).getClientno()));
	    ws.addCell(new Label(6, i+3, list.get(i).getIdtype()));
	    ws.addCell(new Label(7, i+3, list.get(i).getIdnumber()));
	    ws.addCell(new Label(8, i+3, list.get(i).getIsagent()));
	    ws.addCell(new Label(9, i+3, list.get(i).getBusinesscount()));
	    ws.addCell(new Label(10, i+3, list.get(i).getLsh()));
	  }
  
    os.flush();  
    wwb.write();  
    wwb.close();  
    os.close();
    out.clear();
	out = pageContext.pushBody();  
%>  