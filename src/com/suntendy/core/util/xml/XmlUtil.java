package com.suntendy.core.util.xml;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtil {
  
	private static Log log = LogFactory.getLog(XmlUtil.class);    

	/**
	 * create a empyt xml document object
	 * @param xmlPath
	 * @return
	 */
	public static Document createEmptyXmlFile(String xmlPath){
		if(xmlPath==null || xmlPath.equals(""))
			return null;

		XMLWriter output;
		Document document = DocumentHelper.createDocument();
		    
		OutputFormat format = OutputFormat.createPrettyPrint();
		try {
			output = new XMLWriter(new FileWriter(xmlPath), format);
			output.write(document);
			output.close();
		} catch (IOException e) {
			return null;
		}
		return document;
	}

	public static Document createEmptyXmlFile(String xmlPath, String rootStr){
		if(xmlPath==null || xmlPath.equals(""))
			return null;
		
		XMLWriter output;
		Document document = DocumentHelper.createDocument();
		if(document!=null && rootStr!=null && !rootStr.trim().equals("")){
			document.addElement(rootStr);
		}
		    
		OutputFormat format = OutputFormat.createPrettyPrint();
		try {
			output = new XMLWriter(new FileWriter(xmlPath), format);
			output.write(document);
			output.close();
		} catch (IOException e) {
			return null;
		}
		return document;
	}

	public static Document createDC(){
		Document document = DocumentHelper.createDocument();
		return document;
	}

	/**
	 * output document into xml file
	 */
	public static boolean saveXmlFile(Document document, String xmlPath){
		if(document==null)
			return false;
		
		if(xmlPath==null || xmlPath.equals(""))
			return false;
		
	    File file = new File(xmlPath);
	    File dir = file.getParentFile();
	    if(dir!=null && !dir.exists()){
	    	dir.mkdirs();
	    }
	    
		XMLWriter output;
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		try {
			output = new XMLWriter(new FileWriter(xmlPath), format);
			output.write(document);
			output.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	public static boolean saveXmlFile(Document document, String xmlPath, String coding){
		if(document==null)
			return false;
		
		if(xmlPath==null || xmlPath.equals(""))
			return false;
		
	    File file = new File(xmlPath);
	    File dir = file.getParentFile();
	    if(dir!=null && !dir.exists()){
	    	dir.mkdirs();
	    }
	    
		XMLWriter output;
		
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding(coding);
		try {
			output = new XMLWriter(new FileWriter(xmlPath), format);
			output.write(document);
			output.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * get DC from xml file
	 * @param xmlPath
	 * @return
	 * @throws DocumentException
	 */
	public static Document getDC(String xmlPath){
		if(xmlPath==null || xmlPath.equals(""))
			return null;

		File file = new File(xmlPath);
		if(file.exists()==false){
			return createEmptyXmlFile(xmlPath);
		}
		
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(xmlPath);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}
	
	/**
	 * get DC from xml InputStream
	 * @param xmlPath
	 * @return
	 * @throws DocumentException
	 */
	public static Document getDC(InputStream xmlInputStream){
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(xmlInputStream);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}

	public static Document getDC(String xmlPath, String rootStr){
		if(xmlPath==null || xmlPath.equals(""))
			return null;

		File file = new File(xmlPath);
		if(file.exists()==false){
			return createEmptyXmlFile(xmlPath, rootStr);
		}
		
		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(xmlPath);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}
	
	
	/**
	 * get DC from xml String
	 */
	public static Document getDCFromString(String xmlStr){
		Document document = null;
		try {
			document = DocumentHelper.parseText(xmlStr);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}
	
	/**
	 * get root node from DC
	 * @param document
	 * @return
	 */
	public static Element getRootNode(Document document){
		if(document==null)
			return null;
		
		Element root = document.getRootElement();
		return root;
	}

	/**
	 * add root node into DC
	 * @param document
	 * @param nodeName
	 * @param attrName
	 * @param attrVal
	 * @param content
	 * @return
	 */
	public static Element addRootNode(Document document, String nodeName, 
			String attrName, String attrVal, String content){
		if(document==null)
			return null;
		
		if(nodeName==null || nodeName.trim().equals("")){
			return null;
		}
		
		Element e = document.addElement(nodeName);
		
		if(attrName!=null && !attrName.trim().equals("")
				&& attrVal!=null && !attrVal.trim().equals("")){
			e.addAttribute(attrName, attrVal);
		}
		
		if(content!=null && !content.trim().equals("")){
			e.addText(content);
		}

		return e;
	}

	public static Element addRootNode(Document document, String nodeName, 
			String attrName, String attrVal){
		if(document==null)
			return null;
		
		if(nodeName==null || nodeName.trim().equals("")){
			return null;
		}
		
		Element e = document.addElement(nodeName);
		
		if(attrName!=null && !attrName.trim().equals("")
				&& attrVal!=null && !attrVal.trim().equals("")){
			e.addAttribute(attrName, attrVal);
		}

		return e;
	}

	public static Element addRootNode(Document document, String nodeName, 
			String content){
		if(document==null)
			return null;
		
		if(nodeName==null || nodeName.trim().equals("")){
			return null;
		}
		
		Element e = document.addElement(nodeName);
		
		if(content!=null && !content.trim().equals("")){
			e.addText(content);
		}

		return e;
	}

	public static Element addRootNode(Document document, String nodeName){
		if(document==null)
			return null;
		
		if(nodeName==null || nodeName.trim().equals("")){
			return null;
		}
		
		Element e = document.addElement(nodeName);

		return e;
	}

	/**
	 * append a child node to a parent node 
	 * @param parent
	 * @param nodeName
	 * @param attrName
	 * @param attrVal
	 * @return
	 */
	public static Element addNode(Element parent, String nodeName, 
			String attrName, String attrVal, String content){
		if(parent==null)
			return null;
		
		if(nodeName==null || nodeName.trim().equals("")){
			return null;
		}
		
		Element e = parent.addElement(nodeName);
		
		if(attrName!=null && !attrName.trim().equals("")
				&& attrVal!=null && !attrVal.trim().equals("")){
			e.addAttribute(attrName, attrVal);
		}
		
		if(content!=null && !content.trim().equals("")){
			e.addText(content);
		}

		return e;
	}

	public static Element addNode(Element parent, String nodeName, 
			String attrName, String attrVal){
		if(parent==null)
			return null;
		
		if(nodeName==null || nodeName.trim().equals("")){
			return null;
		}
		
		Element e = parent.addElement(nodeName);
		
		if(attrName!=null && !attrName.trim().equals("")
				&& attrVal!=null && !attrVal.trim().equals("")){
			e.addAttribute(attrName, attrVal);
		}

		return e;
	}
	
	public static Element addNode(Element parent, String nodeName, 
			String content){
		if(parent==null)
			return null;
		
		if(nodeName==null || nodeName.trim().equals("")){
			return null;
		}
		
		Element e = parent.addElement(nodeName);
		
		if(content!=null && !content.trim().equals("")){
			e.addText(content);
		}

		return e;
	}
	
	/**
	 * get child of a node
	 * @param parent
	 * @param nodeName
	 * @return
	 */
	public static Element getChild(Element parent, String nodeName){
		if(parent==null)
			return null;
		
		if(nodeName==null || nodeName.trim().equals(""))
			return null;
		
		Element e = null;
		Iterator it = getChildren(parent);
		while(it!=null && it.hasNext()){
			Element k = (Element)it.next();
			if(k==null)continue;
			if(k.getName().equalsIgnoreCase(nodeName)){
				e = k;
				break;
			}
		}
			
		return e;
	}

	/**
	 * @param parent
	 * @param nodeName
	 * @return
	 */
	public static Element getChild(Element parent, String nodeName, String nodeVal){
		if(parent==null)
			return null;
		
		if(nodeName==null || nodeName.trim().equals(""))
			return null;
		
		Element e = null;
		Iterator it = getChildren(parent);
		while(it!=null && it.hasNext()){
			Element k = (Element)it.next();
			if(k==null)continue;
			if(k.getName().equalsIgnoreCase(nodeName) && k.getText().equalsIgnoreCase(nodeVal)){
				e = k;
				break;
			}
		}
			
		return e;
	}
	
	/**
	 * select node from a DC var string "/xx/xx/xx/" etc.
	 * @param document
	 * @param path
	 * @return
	 */
	public static Element select(Document document, String path){
		Element e = null, curr = null;
		
		if(document==null)
			return null;
		
		if(path==null || path.trim().equals(""))
			return null;
		
		String[] tags = path.split("/");
		if(tags==null)
			return null;
		
		int i = 0, j = 0;
		List list = new ArrayList();
		for(i=0;i<tags.length;i++){
			String t = tags[i];
			if(t==null || t.trim().equals(""))
				continue;
			list.add(t);
		}
		
		for(i=0;i<list.size();i++){
			String t = (String) list.get(i);
			if(j==0){
				curr = getRootNode(document);
				if(curr!=null && !curr.getName().equals(t))
					break;
			}
			else{
				curr = getChild(curr,t);
				if(curr==null)
					break;
			}
			j++;
		}
		
		if(j==list.size())
			e = curr;
		
		return e;
	}

	public static void delete(Element parent,int idx){
		if(parent==null)return;
		parent.elements().remove(idx);
	}

	public static void delete(Element parent, String nodeName){
		Element e = getChild(parent,nodeName);
		if(e!=null)
			parent.remove(e);
	}

	public static void delete(Element parent, String nodeName, String nodeVal){
		Element e = getChild(parent,nodeName,nodeVal);
		if(e!=null)
			parent.remove(e);
	}
	
	/**
	 * get kids of a node
	 * @param node
	 * @return
	 */
	public static List getKids(Element node){
		if(node==null)
			return null;

		return node.elements();
	}

	public static Iterator getChildren(Element node){
		if(node==null)
			return null;
		
		Iterator it = node.elementIterator();
		return it;
	}

	public static List getChildList(Element node){
		if(node==null)
			return null;
		
		Iterator it = node.elementIterator();
		if(it==null)
			return null;
		
		List kids = new ArrayList();
		while(it.hasNext()){
			Element e = (Element)it.next();
			if(e!=null)
				kids.add(e);
		}
		return kids;
	}

	/**
	 * travell
	 * @param mode 
	 * 		0: order by floor
	 * 		1: root first
	 */
	public static List travell(Document document, int mode){
		Element root = getRootNode(document);
		if(root==null)
			return null;
		
		if(mode<=0)
			mode = 0;
		
		List nodeList = new ArrayList();
		List resultList = new ArrayList();
		nodeList.add(root);
		visit(nodeList,resultList,mode);
		
		return resultList;
	}
	
	private static void visit(List stack, List result, int mode){
		if(stack.size()<=0)
			return;
		
		Element e = (Element)stack.get(0);
		if(e==null)
			return;
		
		result.add(e);
		stack.remove(0);
		Iterator it = getChildren(e);
		if(mode==0){
			while(it.hasNext()){
				Element k = (Element)it.next();
				stack.add(k);
			}
			visit(stack,result, mode);
		}
		if(mode==1){
			while(it.hasNext()){
				Element k = (Element)it.next();
				stack.add(k);
				visit(stack,result, mode);
			}
		}
	}
	
	public static String formatXml(String str) throws Exception {
		  Document document = null;
		  document = DocumentHelper.parseText(str);
		  // 格式化输出格式
		  OutputFormat format = OutputFormat.createPrettyPrint();
		  format.setEncoding("GBK");
		  StringWriter writer = new StringWriter();
		  // 格式化输出流
		  XMLWriter xmlWriter = new XMLWriter(writer, format);
		  // 将document写入到输出流
		  xmlWriter.write(document);
		  xmlWriter.close();

		  return writer.toString();
	}


	
	public static void main(String args[]){
		/*
		String strImg = "<root><a href='http://www.xiaoshuo.com/jsp/writers.jsp'><img src='http://www.xiaoshuo.com/timages/zzzl.gif' border='0' /></a><a href='/jsp/club/index.jsp?clubid=200242'><img src='/timages/images_column/xqhyyh.gif' border='0' /></a><a href='http://www.xiaoshuo.com/jsp/xianren.jsp'><img src='http://www.xiaoshuo.com/timages/xianren.gif' border='0' /></a></root>";
		Document dc = getDCFromString(strImg);
		Element root = dc.getRootElement();
		List list = getChildList(root);
		for(int i=0;list!=null && i<list.size();i++){
			Element e = (Element)list.get(i);
			log.info(e.attribute("href").getValue());
			List sub = getChildList(e);
			if(sub==null)continue;
			e = (Element)sub.get(0);
			log.info(e.attribute("src").getValue());
		}
		
		
		//List wList = null;
		Document dc = XmlUtil.getDC(xmlFile);
		Element w = XmlUtil.select(dc,"/root/article/");
		if(w==null){
			Element r = XmlUtil.getRootNode(dc);
			if(r==null)r = XmlUtil.addRootNode(dc, "root");
			if(r!=null)w = XmlUtil.addNode(r, "article", "");
		}
		if(w!=null){
			XmlUtil.addNode(w, "link", "test.jpg");
		}
		if(w!=null){
			List list = XmlUtil.getKids(w);
			for(int i=0;i<list.size();i++){
				Element e = (Element)list.get(i);
				log.info(e.asXML());
			}
		}
		//wList = XmlUtil.getChildList(w);
		XmlUtil.saveXmlFile(dc, xmlFile);
		*/

		String xmlFile = "system.xml";
		InputStream   in   =  ClassLoader.getSystemClassLoader().getResourceAsStream(xmlFile);   

		Document dc = XmlUtil.getDC(in);
		Element rootElement = dc.getRootElement();   
		
		System.out.println(rootElement.elementText("appName"));
	}
}