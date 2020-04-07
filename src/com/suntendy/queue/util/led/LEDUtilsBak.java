package com.suntendy.queue.util.led;

/*******************************************************************************
 * 描述: 窗口屏操作工具类 <br>
 * //////////////////////////////////////////////////////// <br>
 * 创建者: 刘东东 <br>
 * 创建日期: 2013-09-27 13:34:42 <br>
 * 修改者: <br>
 * 修改日期: <br>
 * 修改说明: <br>
 ******************************************************************************/
public class LEDUtilsBak {
	static {
//		System.out.println(System.getProperty("java.library.path"));
		System.loadLibrary("xhledplayer");
	}
	
	/**
	 * 获取字符内容长度
	 * @param str 内容
	 * @return 字符长度
	 */
	private static int getWordCount(String str) {
		str = str.replaceAll("[^\\x00-\\xff]", "**");
		int length = str.length() * 16;
		return length;
	}
	
	/**
	 * 发送屏参
	 * @param sCom 串口号
	 * @param sPno 屏号
	 * @param sBaud 波特率
	 * @param sDataBit 串口数据位(4~8),Parity: MARK WordLength: 8
	 * @param sParityCheckBit 奇偶校验位(1:表示偶,2:表示奇,3:表示无,4:表示标志Mark,5:表示空格Space)
	 * @param sStopBit 停止位(1:表示1,2:表示1.5,3:表示2)
	 * @param sStreamCtr 流控制(1:表示xon/xoff,2:表示硬件,3:无)
	 * @param sComConMod 串口连接模式(1:表示485方式,2:表示232方式)
	 * @param sWidth 宽
	 * @param sHeight 高
	 * @param sColorMod 颜色模式(1:表示单色,2:表示双色)
	 * @param sAreaMod 分区版本(1:表示多区域版本,2:表示上中下分区版本)
	 * @return 1:表示发送屏参成功<br>
	 *         2:表示打开com口失败<br>
	 *         3:设置com口参数(带Mark)时失败<br>
	 *         4:握手时,发送握手命令字失败<br>
	 *         5:接收握手回复时,没有获取到屏号<br>
	 *         6:设置com口参数(带SPACE 校验)时失败<br>
	 *         7:发送屏参后,无法接收返回的正确屏号信息,或接收到的屏号为0屏号
	 */
	private native static int SumLedSetParamValue(String sCom, String sPno, String sBaud, String sDataBit, String sParityCheckBit,
		String sStopBit, String sStreamCtr, String sComConMod, String sWidth, String sHeight, String sColorMod, String sAreaMod);
	
	/**
	 * 发送数据
	 * @param sCom 串口号
	 * @param sPno 屏号
	 * @param sJno 节目号
	 * @param sQno 区域号
	 * @param sLeft x坐标
	 * @param sTop y坐标
	 * @param sWidth 宽
	 * @param sHeight 高
	 * @param sBaud 波特率
	 * @param sColor 颜色("1"表示红色,"2"表示绿色,"3"表示黄色)
	 * @param s16Or32 字库点阵 16点阵或32点阵("1"表示16点阵字库,"2"表示32点阵字库)
	 * @param strMsg 发送的内容
	 * @return 1:表示发送屏参成功<br>
	 *         2:表示打开com口失败<br>
	 *         3:设置com口参数(带Mark)时失败<br>
	 *         4:握手时,发送握手命令字失败<br>
	 *         5:接收握手回复时,没有获取到屏号<br>
	 *         6:设置com口参数(带SPACE 校验)时失败<br>
	 *         7:发送屏参后,无法接收返回的正确屏号信息,或接收到的屏号为0屏号
	 */
	private native static int SunLedSendProgramData(String sCom, String sPno, String sJno, String sQno, String sLeft,
		String sTop, String sWidth,	String sHeight, String sBaud, String sColor, String s16Or32, String strMsg);

	/**
	 * 发送屏参
	 * @param com 串口号
	 * @param pno 屏号
	 * @param baud 波特率
	 * @param width 屏宽
	 * @param height 屏高
	 * @return 1:表示发送屏参成功<br>
	 *         2:表示打开com口失败<br>
	 *         3:设置com口参数(带Mark)时失败<br>
	 *         4:握手时,发送握手命令字失败<br>
	 *         5:接收握手回复时,没有获取到屏号<br>
	 *         6:设置com口参数(带SPACE 校验)时失败<br>
	 *         7:发送屏参后,无法接收返回的正确屏号信息,或接收到的屏号为0屏号
	 */
	public static int sendParamsToLed(String com, String pno, String baud, String width, String height) {
		return SumLedSetParamValue(com, pno, baud, "8", "3", "1", "3", "1", width, height, "2", "1");
	}
	
	/**
	 * 发送单行文本
	 * @param comno 串口号
	 * @param pno 屏号
	 * @param width 屏宽
	 * @param height 屏高
	 * @param baud 波特率
	 * @param color 颜色("1"表示红色,"2"表示绿色,"3"表示黄色)
	 * @param lattice 字库点阵 ("1"表示16点阵字库,"2"表示32点阵字库)
	 * @param align 对齐方式(1左对齐 2居中 3右对齐)
	 * @param content 发送内容
	 * @return 执行结果
	 */
	public static int sendSingleLineText(String comno, String pno, String width, String height, String baud, String color,
			String lattice, int align, String content) {
		int x = 0;
		if (2 == align) {
			x = (Integer.parseInt(width) - getWordCount(content)) / 2;
			width = String.valueOf(Integer.parseInt(width) - x);
		} else if (3 == align) {
			x = Integer.parseInt(width) - getWordCount(content);
			width = String.valueOf(Integer.parseInt(width) - x);
		}
		return SunLedSendProgramData(comno, pno, "1", "1", String.valueOf(x), "0", width, height, baud, color, lattice, content);
	}
	
	/**
	 * 发送单行文本
	 * @param comno 串口号
	 * @param pno 屏号
	 * @param width 屏宽
	 * @param height 屏高
	 * @param baud 波特率
	 * @param color 颜色("1"表示红色,"2"表示绿色,"3"表示黄色)
	 * @param lattice 字库点阵 ("1"表示16点阵字库,"2"表示32点阵字库)
	 * @param align 对齐方式(1左对齐 2居中 3右对齐)
	 * @param content1 第一行内容
	 * @param content2 第二行内容
	 * @return 执行结果
	 */
	public static String sendDoubleLineText(String comno, String pno, String width, String height, String baud, String color,
			String lattice, int align, String content1, String content2) {
		int x1 = 0, x2 = 0;
		String width1 = width, width2 = width;
		if (2 == align) {
			x1 = (Integer.parseInt(width) - getWordCount(content1)) / 2;
			x2 = (Integer.parseInt(width) - getWordCount(content2)) / 2;
			width1 = String.valueOf(Integer.parseInt(width1) - x1);
			width2 = String.valueOf(Integer.parseInt(width2) - x2);
		} else if (3 == align) {
			x1 = Integer.parseInt(width) - getWordCount(content1);
			x2 = Integer.parseInt(width) - getWordCount(content2);
			width1 = String.valueOf(Integer.parseInt(width1) - x1);
			width2 = String.valueOf(Integer.parseInt(width2) - x2);
		}
		int y2 = Integer.parseInt(height) / 2;
		
		int result1 = SunLedSendProgramData(comno, pno, "1", "1", String.valueOf(x1), "0", width1, String.valueOf(y2), baud, color, lattice, content1);
		if (1 == result1) {
			int result2 = SunLedSendProgramData(comno, pno, "1", "2", String.valueOf(x2), String.valueOf(y2), width2, String.valueOf(y2), baud, color, lattice, content2);
			if (1 != result2) {
				return "第二行[" + result1 + "]";
			} else {
				return "1";
			}
		} else {
			return "第一行[" + result1 + "]";
		}
	}
}