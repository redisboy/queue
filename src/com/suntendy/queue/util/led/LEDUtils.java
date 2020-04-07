package com.suntendy.queue.util.led;

import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;

import com.suntendy.queue.led.domain.LED;
import com.suntendy.queue.queue.domain.Number;
import com.suntendy.queue.util.cache.CacheManager;
import com.suntendy.queue.util.scriptsession.Publisher;
import com.suntendy.queue.util.scriptsession.event.ChuangKouPing;
import com.suntendy.queue.util.scriptsession.event.ModifyWindowCountEvent;
import com.suntendy.queue.util.scriptsession.event.ShowCallInfoEvent;
import com.suntendy.queue.util.scriptsession.event.ShowCallNumEvent;
import com.suntendy.queue.util.scriptsession.event.ShowWXCallInfoEvent;
import com.suntendy.queue.window.domain.Screen;

/*******************************************************************************
 * 描述: 窗口屏操作工具类 <br>
 * //////////////////////////////////////////////////////// <br>
 * 创建者: 刘东东 <br>
 * 创建日期: 2013-09-27 13:34:42 <br>
 * 修改者: <br>
 * 修改日期: <br>
 * 修改说明: <br>
 ******************************************************************************/
public class LEDUtils {
	private static Class<?> clazz = null;
	private static Class<?> clazz2014 = null;
	static {
		CacheManager cacheManager = CacheManager.getInstance();
		String ledxh = cacheManager.getSystemConfig("ledxh");
		try {
			if ("1".equals(ledxh)) {
				clazz2014 = Class.forName("cn.com.listentech.LEDControl");
			}
			clazz = Class.forName("LEDControl");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置屏参
	 * @param transMode 2、232通讯 3、485通讯
	 * @param comno 串口号
	 * @param pno 屏号
	 * @param baud 波特率
	 * @param width 屏宽
	 * @param height 屏高
	 * @return
	 */
	public static String setScreenPara(Screen screen) {
		String result = "";
		CacheManager cacheManager = CacheManager.getInstance();
		String ledxh = cacheManager.getSystemConfig("ledxh");
		if ("1".equals(ledxh)) {
			try {
				Method fooMethod = clazz2014.getMethod("setScreenPara", new Class[] {
						String.class, String.class, String.class, String.class,
						String.class, String.class, String.class });
				result = (String) fooMethod.invoke(clazz2014, new Object[] {
						screen.getLed().getTransmode(), screen.getComnum(), screen.getAddress(),
						screen.getLed().getBaud(), screen.getLed().getWidth(), screen.getLed().getHeight(), 
						screen.getLed().getDbcolor() });
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				Method fooMethod = clazz.getMethod("setScreenPara", new Class[] {
						String.class, String.class, String.class, String.class,
						String.class, String.class, String.class });
				result = (String) fooMethod.invoke(clazz, new Object[] {
						screen.getLed().getTransmode(), screen.getComnum(), screen.getAddress(),
						screen.getLed().getBaud(), screen.getLed().getWidth(), screen.getLed().getHeight(), 
						screen.getLed().getDbcolor() });
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 发送文本
	 * @param transMode 2、232通讯 3、485通讯
	 * @param pno 屏号
	 * @param comno 串口号
	 * @param baud 波特率
	 * @param dbColor 单双色
	 * @param align 对齐方式(1左对齐 2居中 3右对齐)
	 * @param width 屏宽
	 * @param height 屏高
	 * @param content1 文本1
	 * @param content2 文本2
	 * @param lattice 使用的字库(16点是16,32点是32)
	 * @param fontColor 文本颜色 红色：1，绿色：2，黄色：3
	 * @return
	 */
	public static synchronized String sendText(Screen screen, Publisher publisher, Number number, boolean isExcute) {
		if (isExcute) {
			publisher.publish(new ShowCallNumEvent(screen.getBarip() + "@" + number.getSerialNum()));
			publisher.publish(new ModifyWindowCountEvent(number.getBusinessType() + "@-"));
			String windowCode = screen.getWindowId() + StringUtils.trimToEmpty(screen.getMenuAddress());
			String deptCode = CacheManager.getInstance().getOfDeptCache("deptCode");
			if ("320200000400".equals(deptCode)) {
				publisher.publish(new ShowWXCallInfoEvent(number.getSerialNum() + "@" + windowCode + "@" + number.getQueueCode()));
			}
		}
		
		String result = "", content1 = "", content2 = "", content3="", content4="";
		if (StringUtils.isEmpty(screen.getContent())) {
			content2 = screen.isPause() ? "暂停受理" : screen.getContent2();
		} else {
			if ("2".equals(screen.getShowNum())) {
				content1 = screen.getContent();
				content2 = screen.isPause() ? "暂停受理" : screen.getContent2();
//				content1 = screen.isPause() ? "暂停受理" : screen.getContent();
//				content2 = screen.getContent2();
			}else if ("3".equals(screen.getShowNum())) {
				content1 = screen.getContent();
				content2 = screen.isPause() ? "暂停受理" : screen.getContent2();
//				content1 = screen.isPause() ? "暂停受理" : screen.getContent();
//				content2 = screen.getContent2();
				if (!"".equals(screen.getContent3()) && screen.getContent3() != null) {
					content3 = screen.getContent3();
				}else{
					content3 = " ";
				}
			}else if ("4".equals(screen.getShowNum())) {
				content1 = screen.getContent();
				content2 = screen.isPause() ? "暂停受理" : screen.getContent2();
//				content1 = screen.isPause() ? "暂停受理" : screen.getContent();
//				content2 = screen.getContent2();
				content3 = screen.getContent3();
				if (!"".equals(screen.getContent3()) && screen.getContent3() != null) {
					content3 = screen.getContent3();
				}else{
					content3 = " ";
				}
				content4 = screen.getContent4();
				if (!"".equals(screen.getContent4()) && screen.getContent4() != null) {
					content4 = screen.getContent4();
				}else{
					content4 = " ";
				}
			}
		}
		CacheManager cacheManager = CacheManager.getInstance();
		String ledxh = cacheManager.getSystemConfig("ledxh");
		if ("1".equals(ledxh)) {
			try {
				Method fooMethod = clazz2014.getMethod("sendText", new Class[] {
						String.class, String.class, String.class, String.class,
						String.class, String.class, String.class, String.class,
						String.class, String.class, String.class, String.class,
						String.class, String.class, String.class, String.class,
						String.class, String.class, String.class, String.class });
				result = (String) fooMethod.invoke(clazz2014,
						new Object[] { screen.getLed().getTransmode(), screen.getAddress(), screen.getComnum(),
						screen.getLed().getBaud(), screen.getLed().getDbcolor(), screen.getAlign(), screen.getLed().getWidth(),
						screen.getLed().getHeight(), content1, content2,content3,content4, screen.getLattice(), screen.getColor(),
						screen.getColor1(),screen.getColor2(),screen.getColor3(),screen.getShowNum(),
						screen.getThreegd(),screen.getFourgd() });
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if ("0".equals(ledxh)) {
			try {
				Method fooMethod = clazz.getMethod("sendText", new Class[] {
						String.class, String.class, String.class, String.class,
						String.class, String.class, String.class, String.class,
						String.class, String.class, String.class, String.class,
						String.class });
				result = (String) fooMethod.invoke(clazz,
						new Object[] { screen.getLed().getTransmode(), screen.getAddress(), screen.getComnum(),
						screen.getLed().getBaud(), screen.getLed().getDbcolor(), screen.getAlign(), screen.getLed().getWidth(),
						screen.getLed().getHeight(), content1, content2, screen.getLattice(), screen.getColor(),
						screen.getColor1() });
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	
	/**
	 * 设置屏参
	 * @param transMode 2、232通讯 3、485通讯
	 * @param comno 串口号
	 * @param pno 屏号
	 * @param baud 波特率
	 * @param width 屏宽
	 * @param height 屏高
	 * @return
	 */
	public static String setLED_tv(LED led) {
		String result = "";
//		CacheManager cacheManager = CacheManager.getInstance();
//		String ledxh = cacheManager.getSystemConfig("ledxh");
//		if ("1".equals(ledxh)) {
//			try {
//				Method fooMethod = clazz2014.getMethod("setScreenPara", new Class[] {
//						String.class, String.class, String.class, String.class,
//						String.class, String.class, String.class });
//				result = (String) fooMethod.invoke(clazz2014,new Object[] {
//						led.getTransmode(), led.getCom1(), led.getAddress(),
//						led.getBaud(), led.getWidth(), led.getHeight(), 
//						led.getDbcolor()
//				});
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}else {
			try {
				Method fooMethod = clazz.getMethod("setScreenPara", new Class[] {
						String.class, String.class, String.class, String.class,
						String.class, String.class, String.class });
				result = (String) fooMethod.invoke(clazz,new Object[] {
						led.getTransmode(), led.getCom1(), led.getAddress(),
						led.getBaud(), led.getWidth(), led.getHeight(), 
						led.getDbcolor()
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
//		}
		return result;
	}
	
	/**
	 * 字库发送数据(发送滚动字幕)
	 * @param pno
	 * @param jno 节目号
	 * @param qno 区域号
	 * @param left 区域左上角顶点x坐标
	 * @param top 区域左上角顶点y坐标
	 * @param width 区域宽度
	 * @param height 区域高度
	 * @param showText 发送的内容
	 * @param ShowStyle 使用的字库(16点是16,32点是32)
	 * @param fontname 默认值为0(0代表宋体)
	 * @param fontcolor 红色：0x01，绿色：0x02，黄色：0x03
	 * @param fontsize 默认值0
	 * @param PlayStyle 运行方式(0-255)
	 * @param QuitStyle 退场方式(0-255)
	 * @param PlaySpeed 运行速度(0-255) 值越大,速度越慢
	 * @param showtime 显示时间(0-255)<br>
	 * 具体运行方式和退场方式取值如下:
		 0   随机 
		 1   翻页 
		 2   左覆盖
		 3   右覆盖 
		 4   上覆盖
		 5   下覆盖
		 6   左上角覆盖(斜线)
		 7   右上角覆盖(斜线)
		 8   左下角覆盖(斜线)
		 9   右下角覆盖(斜线) 
		 10  左上角覆盖(直线) 
		 11  右上角覆盖(直线)
		 12  左下角覆盖(直线) 
		 13  右下角覆盖(直线)
		 14  左上角覆盖(边沿)
		 15  右上角覆盖(边沿)
		 16  左下角覆盖(边沿)
		 17  右下角覆盖(边沿)
		 18  水平百叶(左右)
		 19  水平百叶(右左)
		 20  垂直百叶(上下)
		 21  垂直百叶(下上)
		 22  左右对开
		 23  上下对开
		 24  左右闭合
		 25  上下闭合
		 26  上移
		 27  下移
		 28  左移
		 29  右移
		 30  连续上移
		 31  连续下移
		 32  连续左移
		 33  连续右移
		 34  中间向四周(矩形)
		 35  四周向中间(矩形)
		 36  中间向四周(菱形)
		 37  四周向中间(菱形)
		 38  中间向四周(十字)
		 39  四周向中间(十字)
		 255  无 (注：退场方式可以用“无”特技，显示特技没有“无”特技)  
	 * @return
	 */
	public static String sendGunDongContent(Screen screen) {
		String result = "", content1 = "", content2 = "";
		if (StringUtils.isEmpty(screen.getContent())) {
			content2 = screen.isPause() ? "暂停受理" : screen.getContent2();
		} else {
			content1 = screen.isPause() ? "暂停受理" : screen.getContent();
			content2 = screen.getContent2();
		}
		
		CacheManager cacheManager = CacheManager.getInstance();
		String ledxh = cacheManager.getSystemConfig("ledxh");
		if ("1".equals(ledxh)) {
			try {
				Method fooMethod = clazz2014.getMethod("sendGunDongContent", new Class[] {
						String.class, String.class, String.class, String.class,
						String.class, String.class, String.class, String.class,
						String.class, String.class, String.class, String.class,
						String.class, String.class, String.class });
				result = (String) fooMethod.invoke(clazz2014,
						new Object[] { screen.getLed().getTransmode(), screen.getAddress(), screen.getComnum(),
						screen.getLed().getBaud(), screen.getLed().getDbcolor(), "2", screen.getLed().getWidth(),
						screen.getLed().getHeight(), content1, content2, screen.getLattice(), screen.getColor(),
						screen.getColor1(),screen.getLed().getPlaySpeed(), screen.getLed().getIsOpenGunDong() });
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			try {
				Method fooMethod = clazz.getMethod("sendGunDongContent", new Class[] {
						String.class, String.class, String.class, String.class,
						String.class, String.class, String.class, String.class,
						String.class, String.class, String.class, String.class,
						String.class, String.class, String.class });
				result = (String) fooMethod.invoke(clazz,
						new Object[] { screen.getLed().getTransmode(), screen.getAddress(), screen.getComnum(),
						screen.getLed().getBaud(), screen.getLed().getDbcolor(), "2", screen.getLed().getWidth(),
						screen.getLed().getHeight(), content1, content2, screen.getLattice(), screen.getColor(),
						screen.getColor1(),screen.getLed().getPlaySpeed(), screen.getLed().getIsOpenGunDong() });
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 发送文本
	 * @param transMode 2、232通讯 3、485通讯
	 * @param pno 屏号
	 * @param comno 串口号
	 * @param baud 波特率
	 * @param dbColor 单双色
	 * @param align 对齐方式(1左对齐 2居中 3右对齐)
	 * @param width 屏宽
	 * @param height 屏高
	 * @param content1 文本1
	 * @param content2 文本2
	 * @param lattice 使用的字库(16点是16,32点是32)
	 * @param fontColor 文本颜色 红色：1，绿色：2，黄色：3
	 * @param fqsl 显示列数
	 * @param qswz 起始坐标
	 * @param showHang 显示行数
	 * @return
	 */
	public static synchronized String sendLED_TVText(LED led,String hengAndZongFlag,String contentAll) {
		String result = "";
		CacheManager cacheManager = CacheManager.getInstance();
		String leddlpxh = cacheManager.getSystemConfig("leddlpxh");
		if ("1".equals(leddlpxh)) {
			try {
				
				if("0".equals(hengAndZongFlag)){//判断发送横向和纵向 0 竖直方向字幕  1 横向字幕
					
					Method fooMethod = clazz2014.getMethod("sendLEDText", new Class[] {
							String.class,String.class,String.class,String.class,String.class,
							String.class,String.class,String.class,String.class,String.class,
							String.class,String.class,String.class});//,String.class,String.class 
					result = (String) fooMethod.invoke(clazz2014,new Object[] {
							led.getTransmode(), led.getAddress(), led.getCom1(), led.getBaud(),
							led.getDbcolor(),"3", led.getWidth(), led.getHeight(),  contentAll,
							led.getLattice(), led.getFontColor(),//, led.getFqsl(),led.getQswz(),led.getShowHang()
							led.getContent(),led.getSpace()
					});
					
				}else if("1".equals(hengAndZongFlag)){
					
					Method fooMethod = clazz2014.getMethod("sendLEDTexthx", new Class[] {
							String.class,String.class,String.class,String.class,String.class,
							String.class,String.class,String.class,String.class,String.class,
							String.class,String.class,String.class});//,String.class,String.class 
					result = (String) fooMethod.invoke(clazz2014,new Object[] {
							led.getTransmode(), led.getAddress(), led.getCom1(), led.getBaud(),
							led.getDbcolor(),"3", led.getWidth(), led.getHeight(),  contentAll,
							led.getLattice(), led.getFontColor(),led.getContent(),led.getSpace()//, led.getFqsl(),led.getQswz(),led.getShowHang()
					});
					
				}
				System.out.println("LED队列屏发送结果："+result+"------内容："+contentAll);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				
				if("0".equals(hengAndZongFlag)){//判断发送横向和纵向 0 竖直方向字幕  1 横向字幕
					
					Method fooMethod = clazz.getMethod("sendLEDText", new Class[] {
							String.class,String.class,String.class,String.class,String.class,
							String.class,String.class,String.class,String.class,String.class,
							String.class,String.class,String.class});//,String.class,String.class 
					result = (String) fooMethod.invoke(clazz,new Object[] {
							led.getTransmode(), led.getAddress(), led.getCom1(), led.getBaud(),
							led.getDbcolor(),"3", led.getWidth(), led.getHeight(),  contentAll,
							led.getLattice(), led.getFontColor(),//, led.getFqsl(),led.getQswz(),led.getShowHang()
							led.getContent(),led.getSpace()
					});
					
				}else if("1".equals(hengAndZongFlag)){
					
					Method fooMethod = clazz.getMethod("sendLEDTexthx", new Class[] {
							String.class,String.class,String.class,String.class,String.class,
							String.class,String.class,String.class,String.class,String.class,
							String.class,String.class,String.class});//,String.class,String.class 
					result = (String) fooMethod.invoke(clazz,new Object[] {
							led.getTransmode(), led.getAddress(), led.getCom1(), led.getBaud(),
							led.getDbcolor(),"3", led.getWidth(), led.getHeight(),  contentAll,
							led.getLattice(), led.getFontColor(),led.getContent(),led.getSpace()//, led.getFqsl(),led.getQswz(),led.getShowHang()
					});
					
				}
				System.out.println("LED队列屏发送结果："+result+"------内容："+contentAll);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	/**
	 * 通用发送文本
	 * @param transMode 2、232通讯 3、485通讯
	 * @param pno 屏号
	 * @param comno 串口号
	 * @param baud 波特率
	 * @param dbColor 单双色
	 * @param align 对齐方式(1左对齐 2居中 3右对齐)
	 * @param width 屏宽
	 * @param height 屏高
	 * @param content1 文本1
	 * @param content2 文本2
	 * @param lattice 使用的字库(16点是16,32点是32)
	 * @param fontColor 文本颜色 红色：1，绿色：2，黄色：3
	 * @param fqsl 显示列数
	 * @param qswz 起始坐标
	 * @param showHang 显示行数
	 * @return
	 */
	public static synchronized String sendLED_TVTextTY(LED led,String hengAndZongFlag,String contentAll) {
		String result = "";
		CacheManager cacheManager = CacheManager.getInstance();
		String leddlpxh = cacheManager.getSystemConfig("leddlpxh");
		if ("1".equals(leddlpxh)) {
			try {
				
				if("0".equals(hengAndZongFlag)){//判断发送横向和纵向 0 竖直方向字幕  1 横向字幕
					
					Method fooMethod = clazz2014.getMethod("sendLEDText", new Class[] {
							String.class,String.class,String.class,String.class,String.class,
							String.class,String.class,String.class,String.class,String.class,
							String.class,String.class,String.class});//,String.class,String.class 
					result = (String) fooMethod.invoke(clazz2014,new Object[] {
							led.getTransmode(), led.getAddress(), led.getCom1(), led.getBaud(),
							led.getDbcolor(),"3", led.getWidth(), led.getHeight(),  contentAll,
							led.getLattice(), led.getFontColor(),//, led.getFqsl(),led.getQswz(),led.getShowHang()
							led.getContent(),led.getSpace()
					});
					
				}else if("1".equals(hengAndZongFlag)){
					
					Method fooMethod = clazz2014.getMethod("sendLEDTexthxTY", new Class[] {
							String.class,String.class,String.class,String.class,String.class,
							String.class,String.class,String.class,String.class,String.class,
							String.class,String.class,String.class});//,String.class,String.class 
					result = (String) fooMethod.invoke(clazz2014,new Object[] {
							led.getTransmode(), led.getAddress(), led.getCom1(), led.getBaud(),
							led.getDbcolor(),"3", led.getWidth(), led.getHeight(),  contentAll,
							led.getLattice(), led.getFontColor(),led.getContent(),led.getSpace()//, led.getFqsl(),led.getQswz(),led.getShowHang()
					});
					
				}
				System.out.println("LED队列屏发送结果："+result+"------内容："+contentAll);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				
				if("0".equals(hengAndZongFlag)){//判断发送横向和纵向 0 竖直方向字幕  1 横向字幕
					
					Method fooMethod = clazz.getMethod("sendLEDText", new Class[] {
							String.class,String.class,String.class,String.class,String.class,
							String.class,String.class,String.class,String.class,String.class,
							String.class,String.class,String.class});//,String.class,String.class 
					result = (String) fooMethod.invoke(clazz,new Object[] {
							led.getTransmode(), led.getAddress(), led.getCom1(), led.getBaud(),
							led.getDbcolor(),"3", led.getWidth(), led.getHeight(),  contentAll,
							led.getLattice(), led.getFontColor(),//, led.getFqsl(),led.getQswz(),led.getShowHang()
							led.getContent(),led.getSpace()
					});
					
				}else if("1".equals(hengAndZongFlag)){
					
					Method fooMethod = clazz.getMethod("sendLEDTexthxTY", new Class[] {
							String.class,String.class,String.class,String.class,String.class,
							String.class,String.class,String.class,String.class,String.class,
							String.class,String.class,String.class});//,String.class,String.class 
					result = (String) fooMethod.invoke(clazz,new Object[] {
							led.getTransmode(), led.getAddress(), led.getCom1(), led.getBaud(),
							led.getDbcolor(),"3", led.getWidth(), led.getHeight(),  contentAll,
							led.getLattice(), led.getFontColor(),led.getContent(),led.getSpace()//, led.getFqsl(),led.getQswz(),led.getShowHang()
					});
					
				}
				System.out.println("LED队列屏发送结果："+result+"------内容："+contentAll);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	public static void main(String[] args) {
//		Screen screen = new Screen();
//		screen.setTransMode("3");
//		screen.setAddress("1");
//		screen.setComnum("5");
//		screen.setBaud("9600");
//		screen.setDbColor("2");
//		screen.setAlign("2");
//		screen.setWidth("64");
//		screen.setHeight("64");
//		screen.setContent("理");
//		screen.setContent2("0004");
//		screen.setLattice("32");
//		screen.setColor("1");
//		if ("1".equals(setScreenPara(screen))) {
//			System.out.println(sendText(screen));
//		}
	}
}