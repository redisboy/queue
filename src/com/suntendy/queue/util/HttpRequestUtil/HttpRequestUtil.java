package com.suntendy.queue.util.HttpRequestUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.suntendy.queue.util.HttpRequestUtil.X509.FoShanX509TrustManager;
import com.suntendy.queue.util.HttpRequestUtil.net.TLSSocketConnectionFactory;





public class HttpRequestUtil {
	/**  
     * 发起http请求并获取结果  
     *   
     * @param requestUrl 请求地址  
     * @param requestMethod 请求方式（GET、POST）  
     * @param outputStr 提交的数据  
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)  
     */    
    public static ArrayList<JSONObject> httpRequest(String requestUrl, String requestMethod, String outputStr) {    
        JSONObject jsonObject = new JSONObject();
        ArrayList<JSONObject> arrayList = new ArrayList<JSONObject>();
        StringBuffer buffer = new StringBuffer();  
        InputStream inputStream=null;  
        try {  
            URL url = new URL(requestUrl);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();    
            httpUrlConn.setDoOutput(true);    
            httpUrlConn.setDoInput(true);    
            httpUrlConn.setUseCaches(false); 
            httpUrlConn.setConnectTimeout(3000);//建立链接超时时间3秒
            httpUrlConn.setReadTimeout(3000);//读取数据超时时间3秒
            // 设置请求方式（GET/POST）    
            httpUrlConn.setRequestMethod(requestMethod); 
            httpUrlConn.connect();    
            if ("GET".equalsIgnoreCase(requestMethod))    
                httpUrlConn.connect();    
    
            // 当有数据需要提交时    
            if (null != outputStr) {    
                OutputStream outputStream = httpUrlConn.getOutputStream();    
                // 注意编码格式，防止中文乱码    
                outputStream.write(outputStr.getBytes("UTF-8"));    
                outputStream.close();    
            }  
            //将返回的输入流转换成字符串    
            inputStream = httpUrlConn.getInputStream();    
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");    
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);    
    
            String str = null;    
            while ((str = bufferedReader.readLine()) != null) {    
                buffer.append(str);    
            }    
            bufferedReader.close();    
            inputStreamReader.close();    
            // 释放资源    
            inputStream.close();    
            inputStream = null;    
            httpUrlConn.disconnect();    
            System.out.println("预约信息查询返回结果："+buffer.toString());
            String a = buffer.toString().replace("[", "").replace("]", "");
            if(!"".equals(a)){
            	
            	//jsonObject = new JSONObject(a);
            	//jsonObject.put("code", "1");
            	
            	JSONObject jsonObjectTemp;
        		while(a.indexOf("}")!=-1){
        			int index = a.indexOf("}");
        			String temp = a.substring(0, index+1);
        			try {
        				jsonObjectTemp = new JSONObject(temp);
        				jsonObjectTemp.put("code", "1");
        				arrayList.add(jsonObjectTemp);
        			} catch (JSONException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			if((a.length()-1)!=index){
        				a =a.substring(index+2);
        			}else{
        				break;
        			}
        			//int tempint = a.length();
        			
        		}
            }else{
            	jsonObject.put("code", "0");
            	arrayList.add(jsonObject);
            }
            //jsonObject = jsonObject.getJSONObject(buffer.toString());
            
          //jsonObject = JSONObject.fromObject(buffer.toString());  
        } catch (ConnectException ce) {    
              ce.printStackTrace();  
              try {
				jsonObject.put("code", "2");
				jsonObject.put("msg", "网络不通，查询预约信息失败");
				arrayList.add(jsonObject);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } catch (Exception e) {    
               e.printStackTrace();  
               try {
				jsonObject.put("code", "2");
				jsonObject.put("msg", "网络不通，查询预约信息失败！");
				//jsonObject.put("msg", "查询预约信息发生异常");
				arrayList.add(jsonObject);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }finally{  
            try {  
                if(inputStream!=null){  
                    inputStream.close();  
                }  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }   
        return arrayList;    
    }    
    /**  
     * 发起http请求并获取结果  
     *   
     * @param requestUrl 请求地址  
     * @param requestMethod 请求方式（GET、POST）  
     * @param outputStr 提交的数据  
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)  
     */    
    @SuppressWarnings("finally")
	public static net.sf.json.JSONObject httpRequestYf(String requestUrl, String requestMethod, String outputStr) {    
    	net.sf.json.JSONObject yyJson = null;
    	StringBuffer buffer = new StringBuffer();  
    	InputStream inputStream=null;  
    	try {  
    		URL url = new URL(requestUrl);  
    		HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();   
    		httpUrlConn.setRequestProperty("ContentType", "application/json");
    		httpUrlConn.setRequestProperty("Accept", "application/json");
    		httpUrlConn.setDoOutput(true);    
    		httpUrlConn.setDoInput(true);    
    		httpUrlConn.setUseCaches(false); 
    		httpUrlConn.setConnectTimeout(3000);//建立链接超时时间3秒
    		httpUrlConn.setReadTimeout(3000);//读取数据超时时间3秒
    		// 设置请求方式（GET/POST）    
    		httpUrlConn.setRequestMethod(requestMethod); 
    		httpUrlConn.connect();    
    		if ("GET".equalsIgnoreCase(requestMethod))    
    			httpUrlConn.connect();    
    		
    		// 当有数据需要提交时    
            if (null != outputStr) {    
                OutputStream outputStream = httpUrlConn.getOutputStream();    
                // 注意编码格式，防止中文乱码    
                outputStream.write(outputStr.getBytes("UTF-8"));    
                outputStream.close();    
            }  
    		//将返回的输入流转换成字符串    
    		inputStream = httpUrlConn.getInputStream();    
    		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");    
    		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);    
    		
    		String str = null;    
    		while ((str = bufferedReader.readLine()) != null) {    
    			buffer.append(str);    
    		}    
    		bufferedReader.close();    
    		inputStreamReader.close();    
    		// 释放资源    
    		inputStream.close();    
    		inputStream = null;    
    		httpUrlConn.disconnect();    
    		System.out.println("用户信息查询返回结果："+buffer.toString());
    		yyJson = net.sf.json.JSONObject.fromObject(buffer.toString());
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{  
            try {  
                if(inputStream!=null){  
                    inputStream.close();  
                }  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }   
		
		return yyJson;
    	  
    }  
    /**  
     * 发起http请求并获取结果  
     *   
     * @param requestUrl 请求地址  
     * @param requestMethod 请求方式（GET、POST）  
     * @param json 提交的数据  
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)  
     */    
    @SuppressWarnings("finally")
	public static net.sf.json.JSONObject httpRequest(String requestUrl, String requestMethod, JSONObject json) {    
    	net.sf.json.JSONObject yyJson = null;
    	StringBuffer buffer = new StringBuffer();  
    	InputStream inputStream=null;  
    	try {  
    		URL url = new URL(requestUrl);  
    		HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();   
    		httpUrlConn.setRequestProperty("ContentType", "application/json");
    		httpUrlConn.setRequestProperty("Accept", "application/json");
    		httpUrlConn.setDoOutput(true);    
    		httpUrlConn.setDoInput(true);    
    		httpUrlConn.setUseCaches(false); 
    		httpUrlConn.setConnectTimeout(3000);//建立链接超时时间3秒
    		httpUrlConn.setReadTimeout(3000);//读取数据超时时间3秒
    		// 设置请求方式（GET/POST）    
    		httpUrlConn.setRequestMethod(requestMethod); 
    		httpUrlConn.connect();    
    		if ("GET".equalsIgnoreCase(requestMethod))    
    			httpUrlConn.connect();    
    		
    		// 当有数据需要提交时  
    		if (null != json) {    
    			OutputStream outputStream = httpUrlConn.getOutputStream();    
    			// 注意编码格式，防止中文乱码    
    			outputStream.write(json.toString().getBytes("UTF-8"));    
    			outputStream.close();    
    		}  
    		//将返回的输入流转换成字符串    
    		inputStream = httpUrlConn.getInputStream();    
    		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");    
    		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);    
    		
    		String str = null;    
    		while ((str = bufferedReader.readLine()) != null) {    
    			buffer.append(str);    
    		}    
    		bufferedReader.close();    
    		inputStreamReader.close();    
    		// 释放资源    
    		inputStream.close();    
    		inputStream = null;    
    		httpUrlConn.disconnect();    
    		System.out.println("预约信息查询返回结果："+buffer.toString());
    		yyJson = net.sf.json.JSONObject.fromObject(buffer.toString());
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{  
            try {  
                if(inputStream!=null){  
                    inputStream.close();  
                }  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }   
		
		return yyJson;
    	  
    }    
    /**  
     * 发起https请求并获取结果  
     *   
     * @param requestUrl 请求地址  
     * @param requestMethod 请求方式（GET、POST）  
     * @param json 提交的数据  
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)  
     */    
    @SuppressWarnings("finally")
    public static net.sf.json.JSONObject httpsRequest(String requestUrl, String requestMethod, String requestString) {    
    	net.sf.json.JSONObject yyJson = null;
    	StringBuffer buffer = new StringBuffer();  
    	InputStream inputStream=null;  
    	try {  
    		//创建SSLContext  
    		//SSLContext sslContext = SSLContext.getInstance("SSL");
    		//TrustManager[] tm = {new FoShanX509TrustManager()};
    		//初始化  
    	    //sslContext.init(null, tm, new java.security.SecureRandom());;  
    	    //sslContext.init(null, tm, null);;  
    	    //获取SSLSocketFactory对象  
    	    //SSLSocketFactory ssf=sslContext.getSocketFactory();
    	    
    	    //设置当前实例使用的SSLSoctetFactory
    	    //HttpsURLConnection.setDefaultSSLSocketFactory(ssf);  
//    	    HostnameVerifier hv = new HostnameVerifier() {
//                public boolean verify(String urlHostName, SSLSession session) {
//                	System.out.println("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost());
//                    
//                    return true;
//                }
//            };
//            HttpsURLConnection.setDefaultHostnameVerifier(hv);
    		URL url = new URL(requestUrl);  
    		HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();   
    		//httpUrlConn.setRequestProperty("ContentType", "application/json");
    		//httpUrlConn.setRequestProperty("Accept", "application/json");
    		httpUrlConn.setSSLSocketFactory(new TLSSocketConnectionFactory());
    		httpUrlConn.setDoOutput(true);    
    		httpUrlConn.setDoInput(true);    
    		httpUrlConn.setUseCaches(false); 
    		
    		httpUrlConn.setConnectTimeout(3000);//建立链接超时时间3秒
    		httpUrlConn.setReadTimeout(3000);//读取数据超时时间3秒
    		// 设置请求方式（GET/POST）    
    		httpUrlConn.setRequestMethod(requestMethod); 
    		httpUrlConn.connect();    
    		//if ("GET".equalsIgnoreCase(requestMethod))    
    		//	httpUrlConn.connect();    
    		
    		// 当有数据需要提交时  
    		if (null != requestString) {    
    			OutputStream outputStream = httpUrlConn.getOutputStream();    
    			// 注意编码格式，防止中文乱码    
    			outputStream.write(requestString.getBytes("UTF-8"));    
    			outputStream.close();    
    		}  
    		//将返回的输入流转换成字符串    
    		inputStream = httpUrlConn.getInputStream();    
    		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");    
    		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);    
    		
    		String str = null;    
    		while ((str = bufferedReader.readLine()) != null) {    
    			buffer.append(str);    
    		}    
    		bufferedReader.close();    
    		inputStreamReader.close();    
    		// 释放资源    
    		inputStream.close();    
    		inputStream = null;    
    		httpUrlConn.disconnect();    
    		System.out.println("预约信息查询返回结果："+buffer.toString());
    		System.out.println("replace结果："+buffer.toString().replace("\"{", "{").replace("}\"", "}").replace("\\\"", "\""));
    		yyJson = net.sf.json.JSONObject.fromObject(buffer.toString().replace("\"{", "{").replace("}\"", "}").replace("\\\"", "\""));
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{  
    		try {  
    			if(inputStream!=null){  
    				inputStream.close();  
    			}  
    		} catch (IOException e) {  
    			// TODO Auto-generated catch block  
    			e.printStackTrace();  
    		}  
    	}   
    	
    	return yyJson;
    	
    }    
    
}
