/**
 * 
 */
package com.suntendy.queue.util.trff;

import java.net.HttpURLConnection;
import java.net.URL;

import com.suntendy.queue.util.trff.URLAvailability;

/**
 * @author:liuwj
 * @date:2014-4-4  下午01:32:54
 * @version:1.0
 */
public class URLAvailability {

	private static URL url;
	private static HttpURLConnection con;
	/**
	   * 功能：检测当前URL是否可连接或是否有效,
	   * 描述：最多连接网络 2 次, 如果 2 次都不成功，视为该地址不可用
	   * @param urlStr 指定URL网络地址
	   * @return URL
	   */
	public synchronized int  isConnect(String urlStr) {
		   int counts = 0;
		   int state = -1;
//		   while (counts < 2) {
		    try {
		     url = new URL(urlStr);
		     con = (HttpURLConnection) url.openConnection();
		     //jdk1.5之前的版本设置
//		     System.setProperty("sun.net.client.defaultConnectTimeout", "3000");
//		     System.setProperty("sun.net.client.defaultReadTimeout", "3000");
		     //jdk1.5之后的版本设置
		     con.setConnectTimeout(3000);//设置连接主机超时时间
		     con.setReadTimeout(3000);//设置从主机读取数据超时
		     state = con.getResponseCode();
		     if (state == 200) {
		      System.out.println("URL="+urlStr+"可用");
//			  break;
		     }
		    }catch (Exception ex) {
//			     counts++; 
			     System.out.println("URL="+urlStr+"不可用;"+ex.getMessage());
//			     continue;
		    }finally{
		    	 if (con != null) {
		    	     con.disconnect(); //中断连接
		    	    }
		    }
//		  }
		   System.out.println("state="+state);
		   return state;
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		URLAvailability u=new URLAvailability();
		System.out.print(u.isConnect("http://www.sina.com.cn"));//不可用的webservice接口
		//System.out.print(u.isConnect("http://webservice.webxml.com.cn/WebServices/StockInfoWS.asmx"));//可用的webservice接口
	}

}
