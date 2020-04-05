package com.suntendy.queue.util.trff;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.suntendy.queue.advertise.domain.Advertise;

/*****************************************************************
* 描述: 封装、解析XML工具类 <br>
* //////////////////////////////////////////////////////// <br>
* 创建者: 刘东东 <br>
* 创建日期: 2013-09-26 14:37:54 <br>
* 修改者:  <br>
* 修改日期:  <br>
* 修改说明:  <br>
******************************************************************/
public class XMLUtils_wfxx {
	//XML文档的类型 写入
	public static final String TYPE_WRITE = "tmri";

	/**
	 * 创建XML文档
	 * @param type XML文档的类型
	 * @param rows 要封装的节点名和内容
	 * @return XML格式字符串
	 */
	public static String createXML(String type, String[][] rows) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("root");
		Element typeElement = root.addElement(type);
		
		for (String[] row : rows) {
			Element node = typeElement.addElement(row[0]);
			try {
				node.addText(URLEncoder.encode(StringUtils.trimToEmpty(row[1]), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		System.out.println("违法信息XML===="+document.asXML());
		return document.asXML();
	}
	
	/**
	 * 读取XML返回的结果
	 * @param strXML XML格式封装的字符串
	 * @return 可否取号的状态及相关提示信息
	 */
	public static String readXML(String strXML) {
		String[] result = new String[3];
		JSONObject datas = new JSONObject();
		if (StringUtils.isNotEmpty(strXML)) {
			try {
				Document document = DocumentHelper.parseText(strXML);
				Element root = document.getRootElement();
				result[0] = ((Node) root.selectNodes("/root/head/code").get(0)).getText();
				result[1] = ((Node) root.selectNodes("/root/head/message").get(0)).getText();
				result[2] = ((Node) root.selectNodes("/root/head/rownum").get(0)).getText();
				
				List wfxxList=document.selectNodes("//vioSurveil");
				System.out.println("违法信息掉用接口返回结果："+result[0]+"-返回条数："+result[2]);
				if("1".equals(result[0])){
					if (null != wfxxList && !wfxxList.isEmpty()) {
						JSONArray typeGroup = new JSONArray();
						for(int i=0;i<wfxxList.size();i++){
							JSONObject obj = new JSONObject();
							Element  element  = (Element) wfxxList.get(i);
								obj.put("wfsj", element.elementText("wfsj"));
								obj.put("wfdd", element.elementText("wfdd"));
								obj.put("fkje", element.elementText("fkje"));
								obj.put("jkbj", element.elementText("jkbj"));
							typeGroup.put(obj);
						}
						datas.put("datas", typeGroup);
					} else {
						datas.put("error", "获取信息失败");
					}
				}
//				 System.out.println(wfxxList.size());
//				  for(int i=0;i<wfxxList.size();i++){
//					  Element  element  = (Element) wfxxList.get(i);
//					  System.out.print(element.elementText("wfsj"));
//					  System.out.print(element.elementText("wfdd"));
//					  System.out.print(element.elementText("fkje"));
//					  System.out.print(element.elementText("clbj"));
//					  System.out.print(element.elementText("jkbj"));
//				  }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(datas.toString());
		return "";
	}
}