/**
 * 
 */
package com.suntendy.queue.util;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author:liuwj
 * @date:2014-9-14  下午04:23:03
 * @version:1.0
 */
public class TimerTest {
static int count = 0;
    public static void showTimer( final String canshu) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ++count;
                System.out.println("时间=" + new Date() + " 执行了" + count + "次"+canshu); // 1次
            }
        };

        //设置执行时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);//每天
        //定制每天的21:09:00执行，
        calendar.set(year, month, day, 9, 32, 00);
        Date date = calendar.getTime();
        Timer timer = new Timer();
        System.out.println(date);
        
        int period = 2 * 1000;
        //每天的date时刻执行task，每隔2秒重复执行
        timer.schedule(task, date, period);
        //每天的date时刻执行task, 仅执行一次
        //timer.schedule(task, date);
    }

    public static void main(String[] args) throws Exception {
        //showTimer("您好");
    	String bdjym = "114.116.34.246"+"1"+"411327"+"fa:16:3e:66:06:fd"+"0"+"S00500";//本机校验码组
    	System.out.println(EncryptionUtil.encodingMd5(bdjym));
    	System.out.println(RSAUtilOperate.RSAOperate(bdjym, 0));
    }
}
