package com.suntendy.queue.util.trff;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import com.suntendy.queue.util.cache.CacheManager;

/*****************************************************************
* 描述: 查询违法信息 <br>
* //////////////////////////////////////////////////////// <br>
******************************************************************/
public class TrffClient_wfxx {
	
	/**
	 * 查询类接口
	 * @param interfaceID 接口标识
	 * @param strXML XML格式封装的数据
	 * @return XML格式封装的结果
	 * @throws Exception 
	 */
	public static String write(String interfaceID, String strXML) throws Exception {
		String result = "";
		String METHOD_NAME = "queryObjectOut";
		String SYSTEM_ID; // 系统类别
		String INTERFACE_SERIAL_NUM; // 接口序列号
		CacheManager config = CacheManager.getInstance();
		String[] ip = config.getSystemConfig("ip").split(",");
		String[] SERVER_URL = new String[ip.length];
		String[] TARGET_NAMESPACE = new String[ip.length];
		int i = 0;
		int state = -1;
		while (i < ip.length) {
			SERVER_URL[i] = "http://" + ip[i] + "/trffweb/services/TmriOutAccess?wsdl";
			TARGET_NAMESPACE[i] = "http://" + ip[i] + "/trffweb/services/TmriOutAccess";
			SYSTEM_ID = config.getSystemConfig("systemId"); // 系统类别
			INTERFACE_SERIAL_NUM = "7F190909030417040815F3E8E2F8EF9586E5F0CDE38EE0A68E85ABCFA586636E"; // 接口序列号
			// 判断接口连接URL是否可用
			URLAvailability u = new URLAvailability();
			state = u.isConnect(SERVER_URL[i]);
			if (state == 200) {
		    	try {
					Service service = new Service();
					Call call = (Call) service.createCall();
					call.setTargetEndpointAddress(new URL(SERVER_URL[i]));
					call.setUseSOAPAction(true);
					call.setSOAPActionURI(TARGET_NAMESPACE + METHOD_NAME);
					call.setOperationName(new QName(TARGET_NAMESPACE[i], METHOD_NAME));
					call.addParameter("xtlb", XMLType.XSD_STRING, ParameterMode.IN);
					call.addParameter("jkxlh", XMLType.XSD_STRING, ParameterMode.IN);
					call.addParameter("jkid", XMLType.XSD_STRING, ParameterMode.IN);
					call.addParameter("UTF8XmlDoc", XMLType.XSD_STRING, ParameterMode.IN);
					call.setReturnType(XMLType.XSD_STRING);
					result = URLDecoder.decode((String) call.invoke(new Object[] { SYSTEM_ID, INTERFACE_SERIAL_NUM, interfaceID, strXML }), "UTF-8");
				  } catch (ServiceException e) {
		               e.printStackTrace();
		               i++;
					   continue;
	              } catch (RemoteException e) {
	                   e.printStackTrace();
	                   i++;
					   continue;
	              } catch (MalformedURLException e) {
	                   e.printStackTrace();
	                   i++;
					   continue;
	              }catch (Exception e) {
		               e.printStackTrace();
					   i++;
					   continue;
				}
				break;
		    } else {
				i++;
				continue;
			}
		}
		return result;	
   }
}