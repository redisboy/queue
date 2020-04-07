package com.suntendy.queue.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface IWindowService {

	/**
	 * 设备经办人绑定
	 * 
	 * @param operatorInfo
	 *        存有操作人员信息的JSON格式字符串
	 *        格式：{"operNum":"操作员编号", "operName":"操作员姓名", "operDept":"所属部门编号", "loginIP":"登录系统IP"}
	 * @return true：成功 false：失败
	 */
	@WebMethod
	@WebResult
	public boolean saveOperatorInfo(@WebParam(name="operatorInfo")String operatorInfo);
	
	/**
	 * 业务办理信息显示（双屏）
	 * @param loginIP 登录系统IP
	 * @param tableTitle 显示内容标题
	 * @param screenInfo 显示到屏幕的JSON格式的数据信息
	 * @return true：成功 false：失败
	 */
	@WebMethod
	@WebResult
	public boolean showScreenInfo(@WebParam(name="loginIP")String loginIP, @WebParam(name="tableTitle")String tableTitle, @WebParam(name="screenInfo")String screenInfo);
	
	/**
	 * 综合屏幕显示领证信息
	 * @param loginIP 登录系统IP
	 * @param jsz 驾驶证
	 * @param xsz 行驶证
	 * @param djzs 登记证书
	 * @return true：成功 false：失败
	 */
	@WebMethod
	@WebResult
	public boolean showInformationMessage(@WebParam(name="loginIP")String loginIP, @WebParam(name="jsz")String jsz, @WebParam(name="xsz")String xsz, @WebParam(name="djzs")String djzs);
	
	public String queryCondition(@WebParam(name="sfzmhm")String sfzmhm, @WebParam(name="lsh")String lsh);
	
	
	public String showYHXX(@WebParam(name="zt")String zt, @WebParam(name="jym")String jym, @WebParam(name="url")String url);
}
