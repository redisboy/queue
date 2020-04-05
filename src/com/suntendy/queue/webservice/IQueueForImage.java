package com.suntendy.queue.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface IQueueForImage {

	
	
	/**
	 * 根据排队号查询number
	 * @param xml
	 * @return
	 */
	@WebMethod
	@WebResult
	public String queryNumbersById(@WebParam(name="pdh")String pdh);
	/**
	 * 根据IP查询number
	 * @param xml
	 * @return
	 */
	@WebMethod
	@WebResult
	public String queryNumbersByIp(@WebParam(name="serverip")String serverip);
	
}
