package com.suntendy.queue.util;


public class RSAUtilOperate {
	/**
	 * 用户 加密字段 Code name passwork
	 * 员工 加密字段 Code LoginIp DeptCode
	 * 系统查询日志 加密字段 UserName OriginIp
	 * @param context
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	public static String RSAOperate(String context,int flag) throws Exception{
		//加密key
		String privateKey ="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKG4q2i4ZjA5IR/9J9sXQ6tEM2xwaWWNX1aiGramaBvdAaC27GHgsg/Z+IrvYmJAHn8akjo+KxK+mvtFF589T7CeKHg3/7uDMEDimrVbeR04vqjFRMkYwt0EjMbBKtSFPUeOub3zaaC0DIscT95eihcVLrcxPqYBvUNSkCKSpfH1AgMBAAECgYAwYWS2IWRAzPaB8WQ5AQ63b+HKcR62yMJa0ogXWFUQ8N8Jy2+QIH5ki5r1RYQzTGdTMwnH7s1IAEmxHgxhESCDhi7lUbfIRWlhvNKrWqUIq3Q4pgAgZjvGwV1PLlrZ8pBrjquRQntFIBPxgXitu0GOYOAes1Szvs23zOtCtNWeiQJBAMwZDewIiO6EW7R43bA7Xbfy0nux1XdQejg6o33/0H/31BacyUcdCqv98s3sDXifnwoZuM2deeEhGlRpjva9AYMCQQDK2N5eEx00GtB+sKJMx7g8pujuIx/WdPVWacpSKEUNn4SqFNx23LDVWtrGb4aTxOUeI5jjs+Hj9ABTAkGbv70nAkAWeB+nP51LnPi3mqLVVwPPT4VKpt5YX1zFOfdr1LDwlakcwDAMiy70lmWXtAgbon8Qzpog0NhtJ4bNij15/n7fAkANtich2Mzqjr11Mt9lrUJo5ydroXgveOx0kOYaM5qnVxcSoCJe7oANi/yp2TQRnQeXx/Q3wBsO25mCAu8IZieVAkB5ffOCCUWdcSB7m5SLVApvS0g0RpMIEJKPHa0t4RuL9d07jlVzBxBi1okQA6q7sDwOAXP+z52hBBBmqz8TbxjA";
		//解密key
		String publicKey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChuKtouGYwOSEf/SfbF0OrRDNscGlljV9Wohq2pmgb3QGgtuxh4LIP2fiK72JiQB5/GpI6PisSvpr7RRefPU+wnih4N/+7gzBA4pq1W3kdOL6oxUTJGMLdBIzGwSrUhT1Hjrm982mgtAyLHE/eXooXFS63MT6mAb1DUpAikqXx9QIDAQAB";
		
			if(context!=null && !context.equals("")){
				if(flag==0){
					//加密方法
					String dataE = RSAUtil.encryptedData(context, publicKey);
					return dataE;
				}else {
					//解密方法
				    String dataD = RSAUtil.decryptData(context, privateKey);
				    return dataD;
				}
			}  
			System.out.println("------------------加密解密key为空!------------------");
			return null;
		}
}
