<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ page import="java.io.*"%>  
<%@ page import="jxl.write.*"%> 
<%@ page import="jxl.write.WritableWorkbook,jxl.Workbook,jxl.write.WritableSheet,
				jxl.write.Label,com.suntendy.queue.util.ExcelUtil,java.util.*,com.suntendy.queue.ywshtj.domain.*"%>

<%  
	List<YwShTj> list = (List<YwShTj>)request.getSession().getAttribute("list");
	String flag = (String)request.getSession().getAttribute("flag");
	if("0".equals(flag)){
	    response.reset();  
	    response.setContentType("application/vnd.ms-excel;charset=UTF-8");  
	    response.setHeader("Content-Disposition", "attachment;filename=" + new String("业务人员差评率考核.xls".getBytes("gb2312"), "iso8859-1"));  
	  
	    OutputStream os = response.getOutputStream();  
	  
	    WritableWorkbook wwb = null;  
	    wwb = Workbook.createWorkbook(os);//将 WritableWorkbook 写入到输出流  
	    WritableSheet ws = wwb.createSheet("业务人员差评率考核", 0);//创建第一个sheet  
	
	  	ws.addCell(new Label(0, 1, "员工姓名"));
	  	ws.addCell(new Label(1, 1, "员工编号"));
		ws.addCell(new Label(2, 1, "非常满意"));  
		ws.addCell(new Label(3, 1, "满意"));
		ws.addCell(new Label(4, 1, "一般"));
		ws.addCell(new Label(5, 1, "不满意"));
		ws.addCell(new Label(6, 1, "差评率"));
	    for(int i=0;i<list.size();i++) {   
		    ws.addCell(new Label(0, i+3, list.get(i).getName()));
		    ws.addCell(new Label(1, i+3, list.get(i).getCode()));
		    ws.addCell(new Label(2, i+3, list.get(i).getA1()));
		    ws.addCell(new Label(3, i+3, list.get(i).getA2()));
		    ws.addCell(new Label(4, i+3, list.get(i).getA3()));
		    ws.addCell(new Label(5, i+3, list.get(i).getA4()));
		    ws.addCell(new Label(6, i+3, list.get(i).getAvg_badReview()));
		  }
	  
	    os.flush();  
	    wwb.write();  
	    wwb.close();  
	    os.close();
	    out.clear();
		out = pageContext.pushBody(); 
	}else{
		response.reset();  
	    response.setContentType("application/vnd.ms-excel;charset=UTF-8");  
	    response.setHeader("Content-Disposition", "attachment;filename=" + new String("部门差评率考核.xls".getBytes("gb2312"), "iso8859-1"));  
	  
	    OutputStream os = response.getOutputStream();  
	  
	    WritableWorkbook wwb = null;  
	    wwb = Workbook.createWorkbook(os);//将 WritableWorkbook 写入到输出流  
	    WritableSheet ws = wwb.createSheet("部门差评率考核", 0);//创建第一个sheet  
	
	  	ws.addCell(new Label(0, 1, "部门编号"));
		ws.addCell(new Label(1, 1, "非常满意"));  
		ws.addCell(new Label(2, 1, "满意"));
		ws.addCell(new Label(3, 1, "一般"));
		ws.addCell(new Label(4, 1, "不满意"));
		ws.addCell(new Label(5, 1, "差评率"));
	    for(int i=0;i<list.size();i++) {   
		    ws.addCell(new Label(0, i+3, list.get(i).getDeptcode()));
		    ws.addCell(new Label(1, i+3, list.get(i).getA1()));
		    ws.addCell(new Label(2, i+3, list.get(i).getA2()));
		    ws.addCell(new Label(3, i+3, list.get(i).getA3()));
		    ws.addCell(new Label(4, i+3, list.get(i).getA4()));
		    ws.addCell(new Label(5, i+3, list.get(i).getAvg_badReview()));
		  }
	  
	    os.flush();  
	    wwb.write();  
	    wwb.close();  
	    os.close();
	    out.clear();
		out = pageContext.pushBody(); 
	} 
%>  