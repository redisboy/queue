package com.suntendy.queue.util;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

import com.ibatis.common.logging.LogFactory;
public class SpringUtil {

	private static com.ibatis.common.logging.Log logger = LogFactory.getLog(SpringUtil.class);  
    /** Spring框架应用上下文对象 */  
    private static ApplicationContext factory = getApplicationContext();  
      
    static{  
        getApplicationContext();  
    }  
      
    public static void setFactoryBean(ApplicationContext factory){  
        SpringUtil.factory = factory;  
    }  
    /** 
     * 获得Spring框架应用上下文对象  
     * @return ApplicationContext 
     */  
    public static ApplicationContext getApplicationContext()  
    {  
        //判断如果 ApplicationContext 的对象 ＝＝ NULL  
        if ( factory == null )  
        {  
            if(logger.isDebugEnabled()) logger.debug("===================================Init Spring's ApplicationContext=========================================");  
            try  
            {  
                factory = new ClassPathXmlApplicationContext(new String[]{"spring/**/*.xml"});  
            }  
            catch ( Exception e1 )  
            {  
                if(logger.isDebugEnabled()) logger.debug("err = " + e1.getMessage());  
                e1.printStackTrace();  
            }  
        }  
        //返回ApplicationContext  
        return factory;  
    }
}
