package com.suntendy.queue.window.action;

import java.io.IOException;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.util.cache.CacheManager;

/*******************************************************************************
 * 描述: ajxa验证address,ipaddress是否可用 <br>
 * //////////////////////////////////////////////////////// <br>
 * 创建者: 施海波 <br>
 * 创建日期: 2013-10-15 14:53:08 <br>
 * 修改者: <br>
 * 修改日期: <br>
 * 修改说明: <br>
 ******************************************************************************/
public class ValidBaridAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	public String checkBarid() throws IOException {
		String barid = this.getRequest().getParameter("barid");
		boolean result = CacheManager.getInstance().isExistOfAddress(barid);
		getResponse().getWriter().print(result);
		return null;
	}

	public String checkIP() throws Exception {
		boolean result = false;
		String ipaddress = this.getRequest().getParameter("ipaddress");
		if (null != CacheManager.getInstance().getWindow(ipaddress)) {
			result = true; // 存在IP不可用
		}
		
		getResponse().getWriter().print(result);
		return null;
	}
	
	public String checkWindowCode() throws Exception {
		String windowCode = getRequest().getParameter("windowCode");
		boolean result = CacheManager.getInstance().isExistOfWindowCode(windowCode);
		getResponse().getWriter().print(result);
		return null;
	}
}