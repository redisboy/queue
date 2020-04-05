<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ page import="java.io.*"%>  
<%@ page import="jxl.write.*"%> 
<%@ page import="jxl.write.WritableWorkbook,jxl.Workbook,jxl.write.WritableSheet,
				jxl.write.Label,com.suntendy.queue.util.ExcelUtil,java.util.*,com.suntendy.queue.count.domain.*"%>
<%@page import="jxl.Cell"%>

<%  
	List<QueueWaitCount> list = (List<QueueWaitCount>) request.getSession().getAttribute("list");
    response.reset();  
    response.setContentType("application/vnd.ms-excel;charset=UTF-8");  
    response.setHeader("Content-Disposition", "attachment;filename=" + new String("队列排队统计.xls".getBytes("gb2312"), "iso8859-1"));  
  
    OutputStream os = response.getOutputStream();  
  
    WritableWorkbook wwb = null;  
    wwb = Workbook.createWorkbook(os);//将 WritableWorkbook 写入到输出流  
    WritableSheet ws = wwb.createSheet("队列排队统计", 0);//创建第一个sheet  
    
    WritableCellFormat headerFormat = new WritableCellFormat();
    headerFormat.setAlignment(Alignment.CENTRE);
    headerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
	
    String role = (String)request.getSession().getAttribute("role");
    if(role.equals("0")){
    	ws.addCell(new Label(0, 0, "部门名称",headerFormat));
	  	ws.addCell(new Label(1, 0, "大厅名称",headerFormat));
	  	ws.addCell(new Label(2, 0, "队列名称",headerFormat));
    	ws.addCell(new Label(3, 0, "队列编号",headerFormat));  
    	ws.addCell(new Label(4, 0, "人数",headerFormat));
    	ws.addCell(new Label(5, 0, "平均等待时间",headerFormat));
    	ws.addCell(new Label(6, 0, "平均办理时间",headerFormat));
	    for(int i=0;i<list.size();i++) {   
		    ws.addCell(new Label(0, i+1, list.get(i).getDeptCodeName(),headerFormat));
		    ws.addCell(new Label(1, i+1, list.get(i).getDeptname(),headerFormat));
		    ws.addCell(new Label(2, i+1, list.get(i).getName(),headerFormat));
    	    ws.addCell(new Label(3, i+1, list.get(i).getCode(),headerFormat));
    	    ws.addCell(new Label(4, i+1, list.get(i).getTotal(),headerFormat));
    	    ws.addCell(new Label(5, i+1, list.get(i).getAve_waittime(),headerFormat));
    	    ws.addCell(new Label(6, i+1, list.get(i).getAve_worktime(),headerFormat));
		}
    	for(int i=0;i<2;i++){
    		int count=1;
      		Cell[] cells = ws.getColumn(i);
      		if(cells.length>2){
      			for(int j=1;j<(cells.length-1);j++){
      				
      				if(cells[j].getContents()==cells[j+1].getContents()){
      					count++;
      					if(j+2==cells.length){
      						ws.mergeCells(i,j+2-count,i,j+1);
      					}
      				}else{
      					ws.mergeCells(i,j+1-count,i,j);
      					count=1;
      				}
      			}
      		}
      	}
    }else if(role.equals("1")){
    	ws.addCell(new Label(0, 0, "大厅名称",headerFormat));
    	ws.addCell(new Label(1, 0, "队列名称",headerFormat));
    	ws.addCell(new Label(2, 0, "队列编号",headerFormat));  
    	ws.addCell(new Label(3, 0, "人数",headerFormat));
    	ws.addCell(new Label(4, 0, "平均等待时间",headerFormat));
    	ws.addCell(new Label(5, 0, "平均办理时间",headerFormat));
		for(int i=0;i<list.size();i++) {
		    ws.addCell(new Label(0, i+1, list.get(i).getDeptname(),headerFormat));
		    ws.addCell(new Label(1, i+1, list.get(i).getName(),headerFormat));
    	    ws.addCell(new Label(2, i+1, list.get(i).getCode(),headerFormat));
    	    ws.addCell(new Label(3, i+1, list.get(i).getTotal(),headerFormat));
    	    ws.addCell(new Label(4, i+1, list.get(i).getAve_waittime(),headerFormat));
    	    ws.addCell(new Label(5, i+1, list.get(i).getAve_worktime(),headerFormat));
		}
		for(int i=0;i<1;i++){
    		int count=1;
      		Cell[] cells = ws.getColumn(i);
      		if(cells.length>2){
      			for(int j=1;j<(cells.length-1);j++){
      				
      				if(cells[j].getContents()==cells[j+1].getContents()){
      					count++;
      					if(j+2==cells.length){
      						ws.mergeCells(i,j+2-count,i,j+1);
      					}
      				}else{
      					ws.mergeCells(i,j+1-count,i,j);
      					count=1;
      				}
      			}
      		}
      	}
    }else{
    	ws.addCell(new Label(0, 0, "队列名称",headerFormat));
    	ws.addCell(new Label(1, 0, "队列编号",headerFormat));  
    	ws.addCell(new Label(2, 0, "人数",headerFormat));
    	ws.addCell(new Label(3, 0, "平均等待时间",headerFormat));
    	ws.addCell(new Label(4, 0, "平均办理时间",headerFormat));
        for(int i=0;i<list.size();i++) {   
    	    ws.addCell(new Label(0, i+1, list.get(i).getName(),headerFormat));
    	    ws.addCell(new Label(1, i+1, list.get(i).getCode(),headerFormat));
    	    ws.addCell(new Label(2, i+1, list.get(i).getTotal(),headerFormat));
    	    ws.addCell(new Label(3, i+1, list.get(i).getAve_waittime(),headerFormat));
    	    ws.addCell(new Label(4, i+1, list.get(i).getAve_worktime(),headerFormat));
    	}
    }
    
  	
  
    os.flush();  
    wwb.write();  
    wwb.close();  
    os.close();
    out.clear();
	out = pageContext.pushBody();  
%>  