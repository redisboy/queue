package com.suntendy.queue.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Client {
	
	public static final String IP_ADDR="192.168.47.128";
	public static final int PORT = 1001;
	
	public void sendQueue(){
		System.out.println("客户端启动..."); 
		
		
		
		Socket socket = null;
		
		try {
			System.out.println("1");
			//创建一个流套接字并将其连接到指定主机上的指定端口号
			socket = new Socket(IP_ADDR, PORT);
			System.out.println("2");
			//读取服务器端数据
            DataInputStream input = new DataInputStream(socket.getInputStream());
            System.out.println("3");
            //向服务器端发送数据
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            System.out.println("4");
            out.write("U1111211192304\t1".getBytes("GBK"));
            System.out.println("5");
//            String ret = input.readUTF();
            System.out.println("6");
//            System.out.println("服务器端返回过来的是: " + ret);
            
            // 如接收到 "OK" 则断开连接    
//            if ("OK".equals(ret)) {    
//                System.out.println("客户端将关闭连接");    
//                Thread.sleep(500);    
//            }
//            out.close();
//            System.out.println("7");
//            input.close();
            socket.close();
            System.out.println("8");
		} catch (Exception e) {
			System.out.println("客户端异常:" + e.getMessage());
		}finally {  
            if (socket != null) {  
                try {  
                    socket.close();  
                } catch (IOException e) {  
                    socket = null;   
                    System.out.println("客户端 finally 异常:" + e.getMessage());   
                }  
            }  
        }
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		client.sendQueue();
		
		
	}
	
//	public static void main(String args[]) throws Exception {  
//	      //为了简单起见，所有的异常都直接往外抛  
//	      String host = "192.168.47.128";  //要连接的服务端IP地址  
//	      int port = 1001;   //要连接的服务端对应的监听端口  
//	      //与服务端建立连接  
//	      Socket client = new Socket(host, port);  
//	      //建立连接后就可以往服务端写数据了  
//	      Writer writer = new OutputStreamWriter(client.getOutputStream());  
//	      writer.write("U11112111923041");  
//	      writer.flush();//写完后要记得flush  
//	      writer.close();  
//	      client.close();  
//	   } 
//	
	
	
	
//	public static void main(String args[]) throws Exception {  
//	      //为了简单起见，所有的异常都直接往外抛  
//	     String host = "192.168.47.128";  //要连接的服务端IP地址  
//	     int port = 1001;   //要连接的服务端对应的监听端口  
//	     //与服务端建立连接  
//	     Socket client = new Socket(host, port);  
//	      //建立连接后就可以往服务端写数据了  
//	     Writer writer = new OutputStreamWriter(client.getOutputStream(), "GBK");  
//	      writer.write("U21112111923041");  
////	      writer.write("eof\n");  
//	      writer.flush();  
//	      //写完以后进行读操作  
//	     BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));  
//	      //设置超时间为10秒  
//	     client.setSoTimeout(10*1000);  
//	      StringBuffer sb = new StringBuffer();  
//	      String temp;  
//	      int index;  
////	      try {  
////	         while ((temp=br.readLine()) != null) {  
////	            if ((index = temp.indexOf("eof")) != -1) {  
////	                sb.append(temp.substring(0, index));  
////	                break;  
////	            }  
////	            sb.append(temp);  
////	         }  
////	      } catch (SocketTimeoutException e) {  
////	         System.out.println("数据读取超时。");  
////	      }  
//	      System.out.println("服务端: " + sb);  
//	      writer.close();  
//	      br.close();  
//	      client.close();  
//	   } 
//	
//	
	
	
	
	
	
	
	
	
	
	
}
