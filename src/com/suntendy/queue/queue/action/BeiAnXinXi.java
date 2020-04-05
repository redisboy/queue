package com.suntendy.queue.queue.action;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.suntendy.queue.base.BaseAction;
import com.suntendy.queue.dept.domain.Dept;
import com.suntendy.queue.dept.service.DeptService;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.util.trff.TrffUtils;
import com.suntendy.queue.window.domain.Screen;
import com.suntendy.queue.window.service.ISetWindowService;

public class BeiAnXinXi extends BaseAction {
	
	private ISetWindowService setWindowService;
	private DeptService deptService;
	
	public void setSetWindowService(ISetWindowService setWindowService) {
		this.setWindowService = setWindowService;
	}
	public DeptService getDeptService() {
		return deptService;
	}
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	/**
	 * 叫号评价备案信息读取
	 * @return
	 */
	public String duQuBAXX(){
		CacheManager cacheManager = CacheManager.getInstance();
		String deptCode = cacheManager.getOfDeptCache("deptCode");
		String deptHall = cacheManager.getOfDeptCache("deptHall");
		String isUseInterface = cacheManager.getSystemConfig("isUseInterface");//是否启用接口
		String jklx = cacheManager.getSystemConfig("jklx");
		String sbkzjsjip = "";//设备控制计算机ip
		String sbkzjsjbh = "";//设备控制计算机ip
		Map<String, Object> result = new HashMap<String, Object>();
		String jsjip="",jbr="",kbywlb="";
		if ("0".equals(isUseInterface) && "1".equals(jklx)) {
			try {
				sbkzjsjip = InetAddress.getLocalHost().getHostAddress().toString();
			} catch (UnknownHostException e2) {
				e2.printStackTrace();
			}
			
//			sbkzjsjip = "127.0.0.1";
			
			
			System.out.println("查询备案信息ip="+sbkzjsjip);
			try {
				result = TrffUtils.query_sbkzjsjip(sbkzjsjip,deptCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!result.isEmpty()) {
				sbkzjsjbh = (String) result.get("sbkzjsjbh");
				Dept dept = new Dept();
				dept.setSbkzjsjbh(sbkzjsjbh);
				dept.setServersip(sbkzjsjip);
				try {
					deptService.updateDeptByIp(dept);
					result.remove("sbkzjsjbh");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				for (String s:result.keySet()) {
					Map<String, String> map = (Map<String, String>) result.get(s);
					jsjip = map.get("jsjip");
					jbr = map.get("jbr");
					kbywlb = map.get("kbywlb");
					Screen screen = new Screen();
					screen.setBarip(jsjip);
					screen.setJbr(jbr);
					screen.setKbywlb(kbywlb);
					try {
						setWindowService.updateCallerByIp(screen);
					} catch (Exception e) {
						e.printStackTrace();
					}
			
				}
			}
		}else {
			System.out.println("接口没有打开，无需初始化备案信息");
		}

		return null;
	}
	
	public static void main(String[] args) {
		BeiAnXinXi xx = new BeiAnXinXi();
		xx.duQuBAXX();
	}

}
