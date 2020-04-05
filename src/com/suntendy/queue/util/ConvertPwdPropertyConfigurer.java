package com.suntendy.queue.util;  
  

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;  
  
public class ConvertPwdPropertyConfigurer extends  PropertyPlaceholderConfigurer{   
    @Override  
    public void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {  
		String privateKey ="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKG4q2i4ZjA5IR/9J9sXQ6tEM2xwaWWNX1aiGramaBvdAaC27GHgsg/Z+IrvYmJAHn8akjo+KxK+mvtFF589T7CeKHg3/7uDMEDimrVbeR04vqjFRMkYwt0EjMbBKtSFPUeOub3zaaC0DIscT95eihcVLrcxPqYBvUNSkCKSpfH1AgMBAAECgYAwYWS2IWRAzPaB8WQ5AQ63b+HKcR62yMJa0ogXWFUQ8N8Jy2+QIH5ki5r1RYQzTGdTMwnH7s1IAEmxHgxhESCDhi7lUbfIRWlhvNKrWqUIq3Q4pgAgZjvGwV1PLlrZ8pBrjquRQntFIBPxgXitu0GOYOAes1Szvs23zOtCtNWeiQJBAMwZDewIiO6EW7R43bA7Xbfy0nux1XdQejg6o33/0H/31BacyUcdCqv98s3sDXifnwoZuM2deeEhGlRpjva9AYMCQQDK2N5eEx00GtB+sKJMx7g8pujuIx/WdPVWacpSKEUNn4SqFNx23LDVWtrGb4aTxOUeI5jjs+Hj9ABTAkGbv70nAkAWeB+nP51LnPi3mqLVVwPPT4VKpt5YX1zFOfdr1LDwlakcwDAMiy70lmWXtAgbon8Qzpog0NhtJ4bNij15/n7fAkANtich2Mzqjr11Mt9lrUJo5ydroXgveOx0kOYaM5qnVxcSoCJe7oANi/yp2TQRnQeXx/Q3wBsO25mCAu8IZieVAkB5ffOCCUWdcSB7m5SLVApvS0g0RpMIEJKPHa0t4RuL9d07jlVzBxBi1okQA6q7sDwOAXP+z52hBBBmqz8TbxjA";
//        System.out.println("beanFactory=================="+beanFactory);  
//        System.out.println("props=================="+props);  
        String username = props.getProperty("jdbc.username"); 
        String driverClassName = props.getProperty("jdbc.driverClassName"); 
        String url = props.getProperty("jdbc.url"); 
        String password = props.getProperty("jdbc.password"); 
        String initialSize = props.getProperty("jdbc.initialSize"); 
        String maxActive = props.getProperty("jdbc.maxActive"); 
        String maxIdle = props.getProperty("jdbc.maxIdle"); 
        String minIdle = props.getProperty("jdbc.minIdle"); 
        String maxWait = props.getProperty("jdbc.maxWait"); 
        String timeBetweenEvictionRunsMillis = props.getProperty("jdbc.timeBetweenEvictionRunsMillis"); 
        String removeAbandoned = props.getProperty("jdbc.removeAbandoned"); 
        String removeAbandonedTimeout = props.getProperty("jdbc.removeAbandonedTimeout");
        String logAbandoned = props.getProperty("jdbc.logAbandoned"); 
        String testOnBorrow = props.getProperty("jdbc.testOnBorrow"); 
        String testWhileIdle = props.getProperty("jdbc.testWhileIdle"); 
        String testOnReturn = props.getProperty("jdbc.testOnReturn"); 
        String validationQuery = props.getProperty("jdbc.validationQuery"); 
        String poolPreparedStatements = props.getProperty("jdbc.poolPreparedStatements"); 
        String maxOpenPreparedStatements = props.getProperty("jdbc.maxOpenPreparedStatements"); 
        try {
			props.setProperty("jdbc.username",RSAUtil.decryptData(username, privateKey));
			props.setProperty("jdbc.password",RSAUtil.decryptData(password, privateKey));
			props.setProperty("jdbc.driverClassName",driverClassName);
			props.setProperty("jdbc.url",url);
			props.setProperty("jdbc.initialSize",initialSize);
			props.setProperty("jdbc.maxActive",maxActive);
			props.setProperty("jdbc.maxIdle",maxIdle);
			props.setProperty("jdbc.minIdle",minIdle);
			props.setProperty("jdbc.maxWait",maxWait);
			props.setProperty("jdbc.timeBetweenEvictionRunsMillis",timeBetweenEvictionRunsMillis);
			props.setProperty("jdbc.removeAbandoned",removeAbandoned);
			props.setProperty("jdbc.removeAbandonedTimeout",removeAbandonedTimeout);
			props.setProperty("jdbc.logAbandoned",logAbandoned);
			props.setProperty("jdbc.testOnBorrow",testOnBorrow);
			props.setProperty("jdbc.testWhileIdle",testWhileIdle);
			props.setProperty("jdbc.testOnReturn",testOnReturn);
			props.setProperty("jdbc.testOnReturn",testOnReturn);
			props.setProperty("jdbc.validationQuery",validationQuery);
			props.setProperty("jdbc.poolPreparedStatements",poolPreparedStatements);
			props.setProperty("jdbc.maxOpenPreparedStatements",maxOpenPreparedStatements);
			super.processProperties(beanFactory, props); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }  
}  