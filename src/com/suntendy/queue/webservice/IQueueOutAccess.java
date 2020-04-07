package com.suntendy.queue.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface IQueueOutAccess {

	
	
	/**
	 * 叫号信息接口
	 * @param xml
	 * @return
	 */
	@WebMethod
	@WebResult
	public String queryNumbersByIp(@WebParam(name="xml")String xml);
	
	
	/**
	 * 评价接口
	 * @param xml
	 * @return
	 */
	@WebMethod
	@WebResult
	public String toEvalu(@WebParam(name="xml")String xml);
	
	/**
	 * 互联网、微信预约信息增删改接口
	 * @param xml
	 * @return
	 */
	@WebMethod
	@WebResult
	public String reservationEdit(@WebParam(name="xml")String xml);
	
	/**
	 * 互联网、微信预约信息查询接口
	 * @param xml
	 * @return
	 */
	@WebMethod
	@WebResult
	public String reservationQuery(@WebParam(name="xml")String xml);
	
	/**
	 * 互联网、微信预约业务类型信息查询接口
	 * @param xml
	 * @return
	 */
	@WebMethod
	@WebResult
	public String businessQueryForReservation(@WebParam(name="xml")String xml);
	
	/**
	 * 互联网、微信预约证件类型查询接口
	 * @param xml
	 * @return
	 */
	@WebMethod
	@WebResult
	public String codeQueryForReservation(@WebParam(name="xml")String xml);
	
	/**
	 * 实时监控数据查询接口
	 * @param xml
	 * @return
	 */
	@WebMethod
	@WebResult
	public String realTimeMonitoring(@WebParam(name="xml")String xml);
	
	/**
	 * 大厅、管理部门、应用服务器ip对应关系查询接口
	 * @param xml
	 * @return
	 */
	@WebMethod
	@WebResult
	public String queryDept(@WebParam(name="xml")String xml);
	
	/**
	 * 领证信息推送接口
	 * @param xml
	 * @return
	 */
	@WebMethod
	@WebResult
	public String tsLzxx(@WebParam(name="xml")String xml);
	
	/**
	 * 叫号信息模拟插入
	 */
	@WebMethod
	@WebResult
	public String crjhxx(@WebParam(name="xml")String xml);
	
	/**
	 * 推送照片流
	 */
	@WebMethod
	@WebResult
	public String tszpl(@WebParam(name="xml")String xml);
	
	/**
	 * 微信取号
	 */
	@WebMethod
	@WebResult
	public String wxqh(@WebParam(name="xml")String xml);
}
