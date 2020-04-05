package com.suntendy.queue.util;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.log4j.Logger;

public class WebServiceHandler extends BasicHandler  {

	@Override
	public void invoke(MessageContext messageContext) throws AxisFault   
    {   
//		Logger log=Logger.getLogger(WebServiceHandler.class);
        String timeout=(String)this.getOption("timeout");   
        if("".equals(timeout)||timeout==null )   
        {   
            timeout="30000";   
        }   
        /*设置webservice客户端调用的超时时间是60秒  
         * 这个设置会覆盖 你显示的调用 call.setTimeOut();  
         * */  
        System.out.println("webservice 超时过滤器被调用，默认的超时时间是"+timeout+"毫秒");   
        messageContext.setTimeout(Integer.parseInt(timeout));   
    } 

}
