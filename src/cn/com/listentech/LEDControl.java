package cn.com.listentech;

public class LEDControl {
	static {
		System.loadLibrary("ListenPlayDll");
	}

	public native static int AddProgram(int handle, int jno, int playTime);

	public native static int AddControl(int handle,int pno, int DBColor);

	public native static int SetSerialPortPara(int handle,int pno, int comno, int baud);

	public native static int AddLnTxtArea(int handle, int jno, int qno, int left,
			int top, int width, int height, String LnFileName, int PlayStyle,
			int Playspeed, int times);

	public native static int AddFileArea(int handle, int jno, int qno, int left,
			int top, int width, int height);
	
	public native static int AddFile(int handle, int jno, int qno, int mno,
			String fileName, int width, int height, int playstyle,
			int QuitStyle, int playspeed, int delay, int MidText);

	// 计时
	public native static int AddTimerArea(int handle, int jno, int qno, int left,
			int top, int width, int height, int fontColor, String fontName,
			int fontSize, int fontBold,int italic ,int underline, int mode, int dayshow, int culweek,
			int culday,int culhour,int culmin,int culse,int year,
			int week, int month, int day, int hour, int minute, int second);
	
	// 数字时钟
	public native static int AddDClockArea(int handle, int jno, int qno, int left, int top, int width, int height,
                            int fontColor, String fontName, int fontSize, int fontBold,int Italic,int Underline,
                            int year,int week,int month,int day,int hour,int minute,int second,int TwoOrFourYear,
                            int HourShow, int format,int spanMode, int Advacehour, int Advaceminute);
							
	public native static int SendControl(int handle, int SendType, int hwd);
	
	public native static int SetOrderPara(int handle, String diskName);
	
	public native static int AddFileString(int handle,int jno, int qno, int mno, String str,String fontname,int fontsize,int fontcolor,boolean bold,boolean italic,boolean underline,int align,int width,int height,int playstyle,int QuitStyle,int playspeed, int delay,int MidText);
	
	public native static int SetTransMode(int handle,int TransMode,int controlType);

	public native static int SetNetworkPara(int handle, int pno, String ip,int port);

	public native static int StartSend();
	
	public native static int SetProgramTimer(int handle,int pno,int jno,int TimingModel,int WeekSelect,
                             int startSecond,int startMinute,int startHour,int startDay,int startMonth,int startWeek,int startYear,
                             int endSecond,int endMinute,int endHour,int endDay,int endMonth,int endWeek,int endYear);

	public native static int AddLnTxtString(int handle, int jno, int qno, int left, int top, int width, int height,String str,String fontname,int fontsize,int fontcolor,boolean bold,boolean italic,boolean underline,int PlayStyle, int Playspeed,int times);
	
	public native static int AddQuitText(int handle, int jno, int qno, int left, int top, int width, int height,int FontColor,String fontName, int fontSize,boolean fontBold,boolean Italic,boolean Underline,String text);
	
	public native static int SetTest( int handle, int value);

	public native static int  AdjustTime(int handle);

	public native static int SetPower(int handle,int power) ;

	public native static int SetHardPara(int handle,int Mirror,int ScanStyle,int LineOrder,int cls,int RGChange,int zhangKong);

	

	public native static int SearchController(int handle,String filePath,Boolean broadcast);
	
	public native static int SetRemoteNetwork(int handle,String macAddress, String ip, String gateway,String subnetmask,String newmac,String newip,String newgateway,String newsubnetmask);

	public native static int SetPowerTimer(int handle, int bTimer,
                           int startHour1, int startMinute1,int endHour1, int endMinute1,
                           int startHour2, int startMinute2,int endHour2, int endMinute2,
                           int startHour3, int startMinute3,int endHour3, int endMinute3);

	public native static int SetBrightnessTimer(int handle, int bTimer,
                                int startHour1, int startMinute1,int endHour1, int endMinute1, int brightness1,
                                int startHour2, int startMinute2,int endHour2, int endMinute2, int brightness2,
                                int startHour3, int startMinute3,int endHour3, int endMinute3, int brightness3);

	public native static int SendScreenPara(int handle,int DBColor,int width,int height) ;

	public native static int SetTimingLimit(int handle,int FSecond,int FMinute,int FHour,int FDay,int FMonth,int FWeek,int FYear,int compo,Boolean enable);

	public native static int  CancelTimingLimit(int handle);
	
	public native static int  EndSend(int handle);


	public native static int AddNeiMaTxtArea(int handle , int jno, int qno, int left, int top, int width, int 							 
	height,String showtext,int ShowStyle,int fontname,int fontcolor,int fontsize,int PlayStyle, int 							 
	QuitStyle,int Playspeed,int times);
	
	public static int getWordCount(String str, String len) {
		str = str.replaceAll("[^\\x00-\\xff]", "**");
		int length = str.length() * Integer.parseInt(len) / 2;
		return length;
	}
	
	/**
	 * 设置屏参
	 * @param transMode 通讯模式1、网络通讯 2、串口通讯
	 * controlType 控制器型号
	 * @param comno 串口号
	 * @param pno 屏号
	 * @param baud 波特率
	 * @param width 屏宽
	 * @param height 屏高
	 * @param dbColor 单双色
	 * @return 发送结果
	 */
	public static String setScreenPara(String transMode, String comno, String pno, String baud, String width, String height, String dbColor) {
		System.out.println("开始设置屏参");
		int handle = StartSend();
		SetTransMode(handle, 2, 3);
		SetSerialPortPara(handle, Integer.parseInt(pno), Integer.parseInt(comno), Integer.parseInt(baud));
		SendScreenPara(handle, Integer.parseInt(dbColor), Integer.parseInt(width), Integer.parseInt(height));
		int end = EndSend(handle);
		return ""+end;
	}
	
	/**
	 * 发送滚动字幕
	 * @param transMode
	 * @param pno
	 * @param comno
	 * @param baud
	 * @param dbColor
	 * @param align
	 * @param width
	 * @param height
	 * @param content1
	 * @param content2
	 * @param lattice
	 * @param fontColor
	 * @param fontColor1
	 * @param playSpeed
	 * @return
	 */
	public static String sendGunDongContent(String transMode, String pno, String comno, String baud,
			String dbColor,	String align, String width, String height, String content1,
			String content2, String lattice, String fontColor, String fontColor1, String playSpeed, String isOpenGunDong) {
		int handle = StartSend();
		SetTransMode(handle, 2, 3);
		SetSerialPortPara(handle, Integer.parseInt(pno), Integer.parseInt(comno), Integer.parseInt(baud));
		AddControl(handle, Integer.parseInt(pno), Integer.parseInt(dbColor));
		AddProgram(handle, 1, 0);
		
		
		int gundong= 32;
		/*if(null != isOpenGunDong && !"".equals(isOpenGunDong) && "0".equals(isOpenGunDong)){
			gundong = 32;
		}*/
		 playSpeed = "10";
	/*	if(null == playSpeed || "".equals(playSpeed)){
			playSpeed = "0";
		}*/
		if(null == content2 || "".equals(content2)){
			content2 = " ";
		}
		
		if ("".equals(content1)) {
			int x = 0;
//			if ("2".equals(align)) {
//				x = (Integer.parseInt(width) - getWordCount(content2, lattice)) / 2;
//				width = String.valueOf(Integer.parseInt(width) - x);
//			} else if ("3".equals(align)) {
//				x = Integer.parseInt(width) - getWordCount(content2, lattice);
//				width = String.valueOf(Integer.parseInt(width) - x);
//			}
			AddNeiMaTxtArea(handle, 1, 1, x, 0, Integer.parseInt(width), Integer.parseInt(height), 
					content2, Integer.parseInt(lattice), 0,	Integer.parseInt(fontColor),0, gundong, 255, Integer.parseInt(playSpeed), 0);
		} else {
			int x1 = 0, x2 = 0;
			int width1 = Integer.parseInt(width);
			int width2 = Integer.parseInt(width);
			if ("2".equals(align)) {
				x1 = (Integer.parseInt(width) - getWordCount(content1, lattice)) / 2;
				if (x1%8 != 0) {x1= x1-x1%8;}
				if(getWordCount(content2, lattice) > Integer.parseInt(width)){
					x2=0;
				}else{
					x2 = (Integer.parseInt(width) - getWordCount(content2, lattice)) / 2;
					if (x2%8 != 0) {x2= x2-x2%8;}
				}
			} else if ("3".equals(align)) {
				x1 = Integer.parseInt(width) - getWordCount(content1, lattice);
				if(getWordCount(content2, lattice) > Integer.parseInt(width)){
					x2=0;
				}else{
					x2 = Integer.parseInt(width) - getWordCount(content2, lattice);
				}
			}
			width1 = width1 - x1;
			width2 = width2 - x2;
			int y = Integer.parseInt(height) / 2;
			AddNeiMaTxtArea(handle, 1, 1, x1, 0, width1, y, content1, Integer.parseInt(lattice), 0, 
					Integer.parseInt(fontColor),0, 1, 255, 0, 0);
			AddNeiMaTxtArea(handle, 1, 1, 0, Integer.parseInt(lattice), Integer.parseInt(width), y, content2, Integer.parseInt(lattice), 0, 
					Integer.parseInt(fontColor1),0, gundong, 255, Integer.parseInt(playSpeed), 0);
		}
		int sed = SendControl(handle, 1, 0);
		EndSend(handle);
		return "" + sed;
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
	public static String sendText(String transMode, String pno, String comno, String baud,
			String dbColor,	String align, String width, String height, String content1,
			String content2,String content3,String content4, String lattice, String fontColor, String fontColor1,
			String fontColor2,String fontColor3, String showNum, String threegd, String fourgd) {
		int handle = StartSend();
		SetTransMode(handle, 2, 3);
		SetSerialPortPara(handle, Integer.parseInt(pno), Integer.parseInt(comno), Integer.parseInt(baud));
		AddControl(handle, Integer.parseInt(pno), Integer.parseInt(dbColor));
		AddProgram(handle, 1, 0);
		
		if ("".equals(content1)) {//显示单行
			int x = 0;
			int y = 0;
			if ("2".equals(align)) {
				x = (Integer.parseInt(width) - getWordCount(content2, lattice)) / 2;
				if (x%8 != 0) {x= x-x%8;}
				y = (Integer.parseInt(height) - Integer.parseInt(lattice)) / 2;
				if (y%8 != 0) {y= y-y%8;}
				width = String.valueOf(Integer.parseInt(width) - x);
				height =  String.valueOf(Integer.parseInt(height) - y);
			} else if ("3".equals(align)) {
				x = Integer.parseInt(width) - getWordCount(content2, lattice);
				if (x%8 != 0) {x= x-x%8;}
				y = (Integer.parseInt(height) - Integer.parseInt(lattice)) / 2;
				if (y%8 != 0) {y= y-y%8;}
				width = String.valueOf(Integer.parseInt(width) - x);
				height =  String.valueOf(Integer.parseInt(height) - y);
			}
			AddNeiMaTxtArea(handle, 1, 1, x, y, Integer.parseInt(width), Integer.parseInt(height),
					content2, Integer.parseInt(lattice), 0,	Integer.parseInt(fontColor), 0, 1, 255, 0, 0);
		} else {//显示双行三行四行
				int x1 = 0, x2 = 0, x3 = 0, x4 = 0;
				int heightJG = (Integer.parseInt(height)-Integer.parseInt(lattice)*Integer.parseInt(showNum))/(Integer.parseInt(showNum)+1);
				if (heightJG%8 != 0) {
					if (heightJG-heightJG%8 == 0) {
						heightJG=(Integer.parseInt(height)-Integer.parseInt(lattice)*Integer.parseInt(showNum))/(Integer.parseInt(showNum));
					}
					heightJG= heightJG-heightJG%8;
					}
				System.out.println("heightJG="+heightJG);
				int y1 = 0;
				y1 = heightJG;
				if (y1%8 != 0) {y1= y1-y1%8;}
				int y2 = Integer.parseInt(lattice);
				y2 = heightJG*2+Integer.parseInt(lattice);
				if (y2%8 != 0) {y2= y2-y2%8;}
				int y3 = Integer.parseInt(lattice)*2;
				y3 = heightJG*3+Integer.parseInt(lattice)*2;
				if (y3%8 != 0) {y3= y3-y3%8;}
				int y4 = Integer.parseInt(lattice)*3;
				y4 = heightJG*4+Integer.parseInt(lattice)*3;
				if (y4%8 != 0) {y4= y4-y4%8;}
				System.out.println("y1="+y1+"~~~y2="+y2+"~~~y3="+y3+"~~~y4="+y4);
				int width1 = Integer.parseInt(width), width2 = Integer.parseInt(width), width3 = Integer.parseInt(width), width4 = Integer.parseInt(width);
				if ("2".equals(align)) {
					x1 = (Integer.parseInt(width) - getWordCount(content1, lattice)) / 2;
					if (x1%8 != 0) {x1= x1-x1%8;}
					x2 = (Integer.parseInt(width) - getWordCount(content2, lattice)) / 2;
					if (x2%8 != 0) {x2= x2-x2%8;}
					x3 = (Integer.parseInt(width) - getWordCount(content3, lattice)) / 2;
					if (x3%8 != 0) {x3= x3-x3%8;}
					x4 = (Integer.parseInt(width) - getWordCount(content4, lattice)) / 2;
					if (x4%8 != 0) {x4= x4-x4%8;}
				} else if ("3".equals(align)) {
					x1 = Integer.parseInt(width) - getWordCount(content1, lattice);
					if (x1%8 != 0) {x1= x1-x1%8;}
					x2 = Integer.parseInt(width) - getWordCount(content2, lattice);
					if (x2%8 != 0) {x2= x2-x2%8;}
					x3 = Integer.parseInt(width) - getWordCount(content2, lattice);
					if (x3%8 != 0) {x3= x3-x3%8;}
					x4 = Integer.parseInt(width) - getWordCount(content2, lattice);
					if (x4%8 != 0) {x4= x4-x4%8;}
				}
				width1 = width1 - x1;
				width2 = width2 - x2;
				width3 = width3 - x3;
				width4 = width4 - x4;
				int height1 = Integer.parseInt(lattice);
				int height2 = Integer.parseInt(lattice);
				int height3 = Integer.parseInt(lattice);
				int height4 = Integer.parseInt(lattice);
				
				int y = Integer.parseInt(height) / 2;
				
					AddNeiMaTxtArea(handle, 1, 1, x1, y1, width1, height1, content1, Integer.parseInt(lattice), 0,
							Integer.parseInt(fontColor), 0, 1, 255, 0, 0);
					AddNeiMaTxtArea(handle, 1, 1, x2, y2, width2, height2, content2, Integer.parseInt(lattice), 0,
							Integer.parseInt(fontColor1), 0, 1, 255, 0, 0);
				if ("3".equals(showNum) || "4".equals(showNum)) {
					if ("0".equals(threegd)) {
						AddNeiMaTxtArea(handle, 1, 1, x3, y3, width3, height3, content3, Integer.parseInt(lattice), 0,
								Integer.parseInt(fontColor2), 0, 1, 255, 0, 0);
						System.out.println("三行不滚动");
					}else if ("1".equals(threegd)) {
						AddNeiMaTxtArea(handle, 1, 1, 0, y3, Integer.parseInt(width), height3, content3, Integer.parseInt(lattice), 0,
								Integer.parseInt(fontColor2), 0, 32, 255, 10, 0);
						System.out.println("三行滚动");
					}
				}
				if ("4".equals(showNum)) {
					if ("0".equals(fourgd)) {
						AddNeiMaTxtArea(handle, 1, 1, x4, y4, width4, height4, content4, Integer.parseInt(lattice), 0,
								Integer.parseInt(fontColor3), 0, 1, 255, 0, 0);
						System.out.println("四行不滚动");
					}else if ("1".equals(fourgd)) {
						AddNeiMaTxtArea(handle, 1, 1, 0, y4, Integer.parseInt(width), height4, content4, Integer.parseInt(lattice), 0,
								Integer.parseInt(fontColor3), 0, 32, 255, 10, 0);
						System.out.println("四行滚动");
					}
				}
		}
		int sed = SendControl(handle, 1, 0);
		EndSend(handle);
		return ""+sed;
	}
	
	/**
	 * 发送LED纵向文本
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
	 * @param contentAll 内容综合
	 * @param Everyconent 单个内容长度
	 * @param showHang 显示行数
	 * @return
	 */
	
	public static String sendLEDText(String transMode, String pno, String comno, String baud,
			String dbColor,	String align, String width, String height, String contentAll,
			String lattice, String fontColor,String everyConent,String space) {//, String fqsl,String qswz,String showHang
		int handle = StartSend();
		SetTransMode(handle, 2, 3);
		SetSerialPortPara(handle, Integer.parseInt(pno), Integer.parseInt(comno), Integer.parseInt(baud));
		AddControl(handle, Integer.parseInt(pno), Integer.parseInt(dbColor));
		AddProgram(handle, 1, 0);
		AddProgram(Integer.parseInt(pno), 1, 0);
		
	
		//字符长度
		String kongge ="";
		if(null!=space && !"".equals(space)&& !"0".equals(space)){
			for(int x=0;x<Integer.parseInt(space);x++){
				kongge += " ";
			}
		}
		int contentLength =0;
		if(null != everyConent){
			contentLength = getWordCount(everyConent+kongge, lattice)+Integer.parseInt(lattice)/2;
		}
		//行数(高度/点阵数)
		int hangshu = Integer.parseInt(height)/Integer.parseInt(lattice);
		//列数(屏宽/字符长度)
		int lieshu = (int) Math.floor(Integer.parseInt(width)/contentLength);
		//区域高(点阵数*行数)
		int quyuHeight = Integer.parseInt(lattice) * hangshu;
		//区域宽(列数*字符长度)
		int quyuWidth = contentLength;
		//横向起始坐标(屏宽 - 区域宽*列数)/2
		int hengStartZuoBiao = (Integer.parseInt(width) - quyuWidth*lieshu)/2;
		//纵向起始坐标(屏高 - 区域高)/2
		int zongStartZuoBiao = (Integer.parseInt(height) - quyuHeight)/2;
		
		System.out.println("行数:"+hangshu+"列数:"+lieshu+"区域高:"+quyuHeight+"区域宽:"+quyuWidth+"横向起始坐标:"+hengStartZuoBiao+"纵向起始坐标:"+zongStartZuoBiao);
		
		//contentall += Everyconent+"@";
		String[] contents = new String[contentAll.length()];
		contents = contentAll.split("@");

		String nr="";
		for(int i=0;i<contents.length;i++){
			for(int j=0;j<hangshu;j++){
				for(int k=0,z=j;k<hangshu*lieshu;k++,z++){
//					System.out.println(k+"---lieshu*i+1===="+(lieshu*i+j));
					if(k == lieshu*i+j){
//						System.out.println(contents[i]);
						if((contentLength * hangshu+Integer.parseInt(lattice)/2*hangshu) != Integer.parseInt(width)){}
						nr += (contents[i]+(Integer.parseInt(width)-contentLength)+"");
					}
				}
			}
		}
		
		System.out.println("nr===="+nr);
		/*
		//默认分区数量为1
//		if(null == fqsl || "".equals(fqsl)){
//			fqsl = "1";
//		}
		for(int i=0;i<lieshu;i++){
//			if ("2".equals(align)) {
//				hengStartZuoBiao = (Integer.parseInt(width) - getWordCount(Everyconent, lattice)) / 2;
//				width = String.valueOf(Integer.parseInt(width) - hengStartZuoBiao);
//			} else if ("3".equals(align)) {
//				hengStartZuoBiao = Integer.parseInt(width) - getWordCount(Everyconent, lattice);
//				width = String.valueOf(Integer.parseInt(width) - hengStartZuoBiao);
//			}
			int contCount= hangshu ;
			String content01=" ";
			String content02=" ";
			if(i==0){
				String nr ="";
				for(int j=0;j<contents.length;j++){
						if(j < hangshu){
							nr+=contents[j];
						}
				}
				content01 = nr;
				System.out.println("hengStartZuoBiao======="+hengStartZuoBiao+"  quyuWidth==="+quyuWidth);
				System.out.println("纵向content01======="+content01);
				AddNeiMaTxtArea(Integer.parseInt(pno), 1, 1, hengStartZuoBiao, zongStartZuoBiao, quyuWidth, Integer.parseInt(height),
					content01.replaceAll("#", " "), Integer.parseInt(lattice), 0,Integer.parseInt(fontColor), 0, 1, 255, 0, 0);
			}else{
				String nr ="";
				for(int j=(i * hangshu);j<contents.length;j++){
						if(j>=contCount && j<contCount*(i+1)){
							nr+=contents[j];
						}
				}
				if(!"".equals(nr)){
					content02 = nr;
					System.out.println("第"+i+"个起始点坐标"+hengStartZuoBiao+((getWordCount(everyConent, lattice)*i)+(Integer.parseInt(lattice)/2*i)));
					System.out.println("纵向content02======="+content02);
					AddNeiMaTxtArea(Integer.parseInt(pno), 1, 1, hengStartZuoBiao+((getWordCount(everyConent, lattice)*i)+(Integer.parseInt(lattice)/2*i)), zongStartZuoBiao, quyuWidth, Integer.parseInt(height),
						content02.replaceAll("#", " "), Integer.parseInt(lattice), 0,	Integer.parseInt(fontColor), 0, 1, 255, 0, 0);
				}
			}
		}
		return "" + SendControl(Integer.parseInt(pno), 1, 0);*/
		return null;
		
	}
	
	
	
	/**
	 * 发送LED横向文本
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
	
	public static String sendLEDTexthx(String transMode, String pno, String comno, String baud,
			String dbColor,	String align, String width, String height, String contentAll,
			String lattice, String fontColor ,String everyConent,String space) {//, String fqsl,String qswz,String showHang
		int handle = StartSend();
		SetTransMode(handle, 2, 3);
		SetSerialPortPara(handle, Integer.parseInt(pno), Integer.parseInt(comno), Integer.parseInt(baud));
		AddControl(handle, Integer.parseInt(pno), Integer.parseInt(dbColor));
		AddProgram(handle, 1, 0);
		
		//字符长度
		String kongge ="";
		if(null!=space && !"".equals(space)&& !"0".equals(space)){
			for(int x=0;x<Integer.parseInt(space);x++){
				kongge += "#";
			}
		}
		
		int contentLength =0;
		if(null != everyConent){
			contentLength = getWordCount(everyConent+kongge, lattice)+Integer.parseInt(lattice)/2;
		}
		//行数(高度/点阵数)
		int hangshu = Integer.parseInt(height)/Integer.parseInt(lattice);
		//列数(屏宽/字符长度)
		int lieshu = (int) Math.floor(Integer.parseInt(width)/contentLength);
		//区域高(点阵数*行数)
		int quyuHeight = Integer.parseInt(lattice) * hangshu;
		//区域宽(列数*字符长度)
		int qyWidth = lieshu * contentLength;
		int quyuWidth = qyWidth - qyWidth%8;
		//横向起始坐标(屏宽 - 区域宽)/2
		int hengStartZB = (Integer.parseInt(width) - quyuWidth)/2;
		int hengStartZuoBiao = hengStartZB-(hengStartZB%8);
		//纵向起始坐标(屏高 - 区域高)/2
		int zongStartZuoBiao = (Integer.parseInt(height) - quyuHeight)/2;
		
		AddNeiMaTxtArea(handle, 1, 1, hengStartZuoBiao, zongStartZuoBiao, quyuWidth, Integer.parseInt(height),
				contentAll.replaceAll("#", " "), Integer.parseInt(lattice), 0,Integer.parseInt(fontColor), 0, 1, 255, 0, 0);
		int sed = SendControl(handle, 1, 0);
		EndSend(handle);
		return ""+sed;
		
	}
	
	/**
	 * 发送LED横向文本通用
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
	
	public static String sendLEDTexthxTY(String transMode, String pno, String comno, String baud,
			String dbColor,	String align, String width, String height, String contentAll,
			String lattice, String fontColor ,String everyConent,String space) {//, String fqsl,String qswz,String showHang
		System.out.println("发送到屏的字符串为="+contentAll.replaceAll("、", "  "));
		int handle = StartSend();
		SetTransMode(handle, 2, 3);
		SetSerialPortPara(handle, Integer.parseInt(pno), Integer.parseInt(comno), Integer.parseInt(baud));
		AddControl(handle, Integer.parseInt(pno), Integer.parseInt(dbColor));
		AddProgram(handle, 1, 0);
		
		AddNeiMaTxtArea(handle, 1, 1, 0, 0, Integer.parseInt(width), Integer.parseInt(height),
				contentAll.replaceAll("、", "  "), Integer.parseInt(lattice), 0,Integer.parseInt(fontColor), 0, 1, 255, 0, 0);
		
		int sed = SendControl(handle, 1, 0);
		EndSend(handle);
		return ""+sed;
	}
	
	public static void SendNeiMa(String transMode, String comno, String pno, String baud, String width, String height, String dbColor) {
		
	    LEDControl jc = new LEDControl();
		int handle = jc.StartSend();
		jc.SetTransMode(handle,2, 3);
		//jc.SetSerialPortPara(1, 1, 9600);
//		jc.SetNetworkPara(handle,1, "192.168.1.130",10000);
		jc.SetSerialPortPara(handle, 1, 3, 9600);
		

		
		int j = jc.AddControl(handle,1, 1);
		jc.AddProgram(handle, 1, 0);
		jc.AddNeiMaTxtArea(handle,1, 1, 32, 0, 96, 16, "生命指南", 16, 0, 1,
				0, 16, 255, 10, 10);
		jc.AddNeiMaTxtArea(handle,1, 1, 36, 16, 92, 16, "生命指1", 16, 0, 1,
				0, 16, 255, 10, 10);
		jc.SendControl(handle, 1, 0);
		jc.EndSend(handle);
		
	}
	
	public static void main(String[] args) throws Exception {
		//System.out.println(setScreenPara("3", "4", "1", "115200", "384", "128", "2"));
		//System.out.println(sendText("3", "1", "4", "115200", "1", "1", "384", "128", "1234567890qwertyuasdzxcvbnmasdfghjkl", " ", "16", "1","1"));
//		for(int i=0;i<3 ;i++){
//			String s=String.valueOf(i);
//			if(i<10){
//				s="0"+s;
//			}
//		sendLEDText("3", "1", "4", "115200", "1", "1", "398", "64", "A0001到"+s+"号窗口 @A0001到"+s+"号窗口 @A0001到"+s+"号窗口 @A0001到"+s+"号窗口 @A0001到"+s+"号窗口 @A0001到"+s+"号窗口 @","16", "1","A0001到"+s+"号窗口","");//,"4","2","3"
//		//sendLEDTexthx("3", "1", "4", "115200", "1", "1", "960", "128", "A0001到"+s+"号窗口","16", "1");//,"4","2","3"
//		//sendLEDTexthx("3", "1", "4", "115200", "1", "1", "384", "128", "到12号","16", "1","4","0","3");
//		Thread.sleep(500);
//		}
		//--------------------------------------------------------
		String contentAll = "";
		String s="";
		int contentLength =0;
			String everyConent="A0001号到10号窗口";
			if(null != everyConent){
				contentLength = getWordCount(everyConent, "16")+16/2;
			}
			
		//行数(高度/点阵数)
		int hangshu = 128/16;
		//列数(屏宽/字符长度)
		int lieshu = (int) Math.floor(384/contentLength);
		String num = "";
		for(int i=0;i<500;i++){
			if(i<10){
				num = "A000"+i;
			}
			if(i>=10 && i<100){
				num ="A00"+i;
			}if(i>=100){
				num ="A0"+i;
			}else{
				
			}
			contentAll += num+"号到10号窗口 ";
			int len = contentAll.replaceAll("[^\\x00-\\xff]", "**").length();
			if((len * 16 /2) > (lieshu * hangshu * contentLength)){
				contentAll = contentAll.substring(contentAll.indexOf(" ")+1,contentAll.length());
				
			}
			sendLEDTexthx("3", "1", "4", "9600", "1", "1", "384", "128", contentAll,"16", "1","A0001号到10号窗口","");
		//	System.out.println(contentAll);
			Thread.sleep(50);
		}
	}
}