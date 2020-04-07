<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="jxl.write.*"%>
<%@ page
	import="jxl.write.WritableWorkbook,jxl.Workbook,jxl.write.WritableSheet,
				jxl.write.Label,com.suntendy.queue.util.ExcelUtil,java.util.*,com.suntendy.queue.systemlog.domain.*"%>
<%@page import="jxl.Cell"%>

<%
	List<Operate> list =  (List<Operate>) request.getSession().getAttribute("list");
	response.reset();
	response.setContentType("application/vnd.ms-excel;charset=UTF-8");
	response.setHeader("Content-Disposition", "attachment;filename="
			+ new String("Operatelog.xls".getBytes("gb2312"), "iso8859-1"));

	OutputStream os = response.getOutputStream();

	WritableWorkbook wwb = null;
	wwb = Workbook.createWorkbook(os);//将 WritableWorkbook 写入到输出流  
	WritableSheet ws = wwb.createSheet("操作查询", 0);//创建第一个sheet  

	WritableCellFormat headerFormat = new WritableCellFormat();
	headerFormat.setAlignment(Alignment.CENTRE);
	headerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

		ws.addCell(new Label(0, 0, "用户名", headerFormat));
		ws.addCell(new Label(1, 0, "被操作用户", headerFormat));
		ws.addCell(new Label(2, 0, "来源", headerFormat));
		ws.addCell(new Label(3, 0, "模块", headerFormat));
		ws.addCell(new Label(4, 0, "模块细类", headerFormat));
		ws.addCell(new Label(5, 0, "类型", headerFormat));
		ws.addCell(new Label(6, 0, "核心业务", headerFormat));
		ws.addCell(new Label(7, 0, "操作时间", headerFormat));
		ws.addCell(new Label(8, 0, "结果描述", headerFormat));
		for (int i = 0; i < list.size(); i++) {
			ws.addCell(new Label(0, i + 1, list.get(i).getUserName(),
					headerFormat));
			ws.addCell(new Label(1, i + 1, list.get(i).getOpName(),
					headerFormat));
			ws.addCell(new Label(2, i + 1, list.get(i).getOriginIp(),
					headerFormat));
			ws.addCell(new Label(3, i + 1, list.get(i).getModule(),
					headerFormat));
			ws.addCell(new Label(4, i + 1, list.get(i).getModuleType(),
					headerFormat));
			ws.addCell(new Label(5, i + 1, list.get(i).getEventType(),
					headerFormat));
			ws.addCell(new Label(6, i + 1, list.get(i).getCoreBusiness(),
					headerFormat));
			ws.addCell(new Label(7, i + 1, list.get(i).getoTime(),
					headerFormat));
			ws.addCell(new Label(8, i + 1, list.get(i).getResultDepict(),
					headerFormat));
		}
		for (int i = 0; i < 2; i++) {
			int count = 1;
			Cell[] cells = ws.getColumn(i);
			if (cells.length > 2) {
				for (int j = 1; j < (cells.length - 1); j++) {

					if (cells[j].getContents() == cells[j + 1]
							.getContents()) {
						count++;
						if (j + 2 == cells.length) {
							ws.mergeCells(i, j + 2 - count, i, j + 1);
						}
					} else {
						ws.mergeCells(i, j + 1 - count, i, j);
						count = 1;
					}
				}
			}
		}
	

	os.flush();
	wwb.write();
	wwb.close();
	os.close();
	out.clear();
	out = pageContext.pushBody();
%>
