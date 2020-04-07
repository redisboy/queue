package com.suntendy.queue.printSet.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.printSet.domain.Print;
import com.suntendy.queue.printSet.service.IPrintSetService;
import com.suntendy.queue.util.cache.CacheManager;

public class PrintSetAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private IPrintSetService printSetService;

	public void setPrintSetService(IPrintSetService printSetService) {
		this.printSetService = printSetService;
	}

	/**
	 * 获取打印条内容
	 * @return
	 * @throws Exception
	 */
	public String getPrint() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		List<Print> printInfo = printSetService.getPrint(deptCode, deptHall);
		if (!printInfo.isEmpty()) {
			Print printVo = printInfo.get(0);
			request.setAttribute("printVo", printVo);
		}
		return SUCCESS;
	}

	/**
	 * 更改打印条格式
	 * @return
	 * @throws Exception
	 */
	public String PrintSet() throws Exception {
		HttpServletRequest request = getRequest();
		CacheManager cacheManager = CacheManager.getInstance();
		
		Print print = new Print();
		print.setId(request.getParameter("id"));
		print.setContent(request.getParameter("code"));
		print.setDeptCode(cacheManager.getOfDeptCache("deptCode"));
		print.setDeptHall(cacheManager.getOfDeptCache("deptHall"));
		
		try {
			printSetService.PrintSet(print);
			request.setAttribute("msg", "打印条设置成功！");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "打印条设置失败，<br>在执行过程中发生异常！");
		}
		request.setAttribute("action", "printSet.action");
		return SUCCESS;
	}

}
