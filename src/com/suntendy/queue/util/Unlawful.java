package com.suntendy.queue.util;

import com.suntendy.queue.employee.domain.Employee;
import com.suntendy.queue.statistics.domain.SaveLCount;
import com.suntendy.queue.systemlog.domain.Loginls;
import com.suntendy.queue.systemlog.domain.Operate;
import com.suntendy.queue.systemlog.domain.Security;

public class Unlawful {
	public static String div = "<div style='height:26px;background-color:#FF0000;text-align:center;'>";

	/**
	 * PD_USER表发送非法篡改
	 */
	public static Employee unlawfulEmp(Employee emp) throws Exception {
		emp.setCode(div + emp.getCode() + "</div>");
		emp.setName(div + emp.getName() + "</div>");
		emp.setModuleRdac(div + emp.getModuleRdac() + "</div>");
		emp.setRole(div + emp.getRole() + "</div>");
		emp.setSex(div + emp.getSex() + "</div>");
		emp.setPolice(div + emp.getPolice() + "</div>");
		emp.setDeptHall(div + emp.getDeptHall() + "</div>");
		emp.setLoginIp(div + emp.getLoginIp() + "</div>");
		emp.setDepartment(div + emp.getDepartment() + "</div>");
		emp.setComments(div + emp.getComments() + "</div>");
		emp.setFmt_status(div + "不可使用</div>");
		emp.setResetPwd(div + "不可使用</div>");
		emp.setOperate(div + "用户数据被非法篡改</div>");
		return emp;
	}

	/**
	 * PD_LOGINLS_LOG表发送非法篡改
	 */
	public static Loginls unlawfulLoginls(Loginls logls) throws Exception {
		logls.setUserName(div + logls.getUserName() + "</div>");
		logls.setEvent(div + logls.getEvent() + "</div>");
		logls.setOriginIp(div + logls.getOriginIp() + "</div>");
		logls.setEventType(div + logls.getEventType() + "</div>");
		logls.setResult(div + logls.getResult() + "</div>");
		logls.setResultDepict(div + "用户数据被非法篡改</div>");
		logls.setRecTime(div + logls.getRecTime() + "</div>");
		logls.setLeaTime(div + logls.getLeaTime() + "</div>");
		return logls;
	}

	/**
	 * PD_OPERATE_LOG表发送非法篡改
	 */
	public static Operate unlawfulOpertate(Operate operate) throws Exception {
		operate.setUserName(div + operate.getUserName() + "</div>");
		operate.setEvent(div + operate.getEvent() + "</div>");
		operate.setOriginIp(div + operate.getOriginIp() + "</div>");
		operate.setModule(div + operate.getModule() + "</div>");
		operate.setModuleType(div + operate.getModuleType() + "</div>");
		operate.setEventType(div + operate.getEventType() + "</div>");
		operate.setCoreBusiness(div + operate.getCoreBusiness() + "</div>");
		operate.setResultDepict(div + "用户数据被非法篡改</div>");
		operate.setoTime(div + operate.getoTime() + "</div>");
		return operate;
	}

	/**
	 * PD_SECURITY_LOG表发送非法篡改
	 */
	public static Security unlawfulSecurity(Security security) throws Exception {
		security.setUserName(div + security.getUserName() + "</div>");
		security.setEvent(div + security.getEvent() + "</div>");
		security.setOriginIp(div + security.getOriginIp() + "</div>");
		security.setEventType(div + security.getEventType() + "</div>");
		security.setResultDepict(div + "用户数据被非法篡改</div>");
		security.setrTime(div + security.getrTime() + "</div>");
		return security;
	}

	/**
	 * 安全审计标记
	 */
	public static Security unlawfulAudit(Security security) throws Exception {
		security.setResultDepict(div + "用户行为异常</div>");
		return security;
	}

	/**
	 * 用户明细封装 flag 0:新增 1:修改 2:删除
	 */
	public static String unlawfulEmpMx(Employee employee, int flag)
			throws Exception {
		String str = "";
		if (flag == 0) {
			str = "新增用户*------";
		} else if (flag == 1) {
			str = "修改用户*------";
		} else {
			str = "删除用户*------";
		}
		String content = "用户名:" + employee.getCode() + "---姓名:"
				+ employee.getName() + "---警员(0:是 1:否):" + employee.getPolice()
				+ "---管理模块:" + employee.getModuleRdac();
		return str + content;
	}

	/**
	 * 用户明细封装 比较修改内容
	 */
	public static String unlawfulEmpbj(Employee employee, Employee emp)
			throws Exception {
		String hall = "", empNum = "", sex = "", poflag = "", dlip = "", kdsd = "", model = "", yxq = "", mmyxq = "";
		if (null != employee.getYhyxq()
				&& !employee.getYhyxq().equals(emp.getYhyxq())) {
			yxq = "用户有效期修改为【" + employee.getYhyxq() + "】---";
		}
		if (null != employee.getPassCode()
				&& !employee.getPassCode().equals(emp.getPassCode())) {
			mmyxq = "密码有效期修改为【" + employee.getPassCode() + "】";
		}
		if (null != employee.getDepartment()
				&& !employee.getDepartment().equals(emp.getDepartment())) {
			hall = "部门编号修改为【" + employee.getDepartment() + "】---";
		}
		if (null != employee.getPolice()
				&& !employee.getPolice().equals(emp.getPolice())) {
			if (employee.getPolice().equals("0")) {
				poflag = "警员修改为【是】---";
			} else {
				poflag = "警员修改为【否】---";
			}
		}
		if (null != employee.getPoliceCode()
				&& !employee.getPoliceCode().equals(emp.getPoliceCode())) {
			empNum = "警员或员工编号修改为【" + employee.getPoliceCode() + "】---";
		}
		if (null != employee.getSex()
				&& !employee.getSex().equals(emp.getSex())) {
			sex = "性别修改为【" + employee.getSex() + "】---";
		}
		if (null != employee.getKdlip()
				&& !employee.getKdlip().equals(emp.getKdlip())) {
			dlip = "可登录IP修改为【" + employee.getKdlip() + "】---";
		}
		if (null != employee.getKdlsjd()
				&& !employee.getKdlsjd().equals(emp.getKdlsjd())) {
			kdsd = "可登录时间修改为【" + employee.getKdlsjd() + "】---";
		}
	/*	if (null != employee.getModuleRdac()
				&& !employee.getModuleRdac().equals(emp.getModuleRdac())) {
			if (null != employee.getModuleRdac()) {
				int key = Integer.parseInt(employee.getModuleRdac());
				switch (key) {
				case 0:
					employee.setModuleRdac("业务管理");
					break;
				case 1:
					employee.setModuleRdac("系统管理");
					break;
				case 2:
					employee.setModuleRdac("安全管理");
					break;
				case 3:
					employee.setModuleRdac("审计管理");
					break;
				default:
					break;
				}
			}
			model = "角色功能修改为【" + employee.getModuleRdac() + "】";
		}*/
		String msg = yxq + mmyxq + hall + empNum + sex + poflag + dlip + kdsd
				+ model;
		if (msg.length() <= 0) {
			msg = "没有修改内容！！";
		}
		String content = "修改内容*------";
		return content + msg;
	}

	/**
	 * 用户明细封装 比较权限修改
	 */
	public static String unlawfulEmpRdac(Employee employee, Employee emp)
			throws Exception {
		String a = employee.getLevelid();// 修改的
		String b = emp.getLevelid();// 原来的
		String c = "没有新增权限";
		String bc = "没有减少权限";
		StringBuilder sb = new StringBuilder();
		String[] strings = a.split(",");
		for (String s : strings) {
			if (!b.contains(s)) {
				sb.append(s + ",");
			}
		}
		if (sb.length() > 0) {
			c = sb.substring(0, sb.length() - 1).toString();
		}
		b = employee.getLevelid();// 修改的
		a = emp.getLevelid();// 原来的
		StringBuilder sa = new StringBuilder();
		String[] stringsa = a.split(",");
		for (String x : stringsa) {
			if (!b.contains(x)) {
				sa.append(x + ",");
			}
		}
		if (sa.length() > 0) {
			bc = sa.substring(0, sa.length() - 1).toString();
		}
		return c + ":" + bc;
	}
}
