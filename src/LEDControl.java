import java.math.BigDecimal;

public class LEDControl {
	static {
		//System.out.println(System.getProperty("java.library.path"));
		System.loadLibrary("ListenPlayDll2012");
	}

	/**
	 * 添加节目
	 * @param pno 屏号
	 * @param jno 节目号
	 * @param playTime 节目播放时间
	 * @return 1、成功<br>2、参数错误
	 */
	public native static int AddProgram(int pno, int jno, int playTime);

	/**
	 * 添加显示屏
	 * @param pno 屏号
	 * @param DBColor 单双色(单色为1,双色为2,三基色3)
	 * @return 1、成功<br>2、参数错误
	 */
	public native static int AddControl(int pno, int DBColor);

	/**
	 * 设置串口参数
	 * @param pno 屏号
	 * @param comno 串口端口号
	 * @param baud 波特率
	 * @return 1：成功<br>0：不成功
	 */
	public native static int SetSerialPortPara(int pno, int comno, int baud);

	public native static int AddLnTxtArea(int pno, int jno, int qno, int left,
			int top, int width, int height, String LnFileName, int PlayStyle,
			int Playspeed, int times);

	/**
	 * 添加文件区域
	 * @param pno 屏号(>=1)
	 * @param jno 节目号(>=1)
	 * @param qno 区域号(>=1)
	 * @param left 区域左上角顶点x坐标：8的倍数，单位：象素
	 * @param top 区域左上角顶点y坐标
	 * @param width 区域宽度：8的倍数，单位：象素
	 * @param height 区域高度
	 * @return
	 */
	public native static int AddFileArea(int pno, int jno, int qno, int left,
			int top, int width, int height);

	public native static int AddFile(int pno, int jno, int qno, int mno,
			String fileName, int width, int height, int playstyle,
			int QuitStyle, int playspeed, int delay, int MidText);

	// 计时
	public native static int AddTimerArea(int pno, int jno, int qno, int left,
			int top, int width, int height, int fontColor, String fontName,
			int fontSize, int fontBold, int mode, int format, int year,
			int week, int month, int day, int hour, int minute, int second);

	// 数字时钟
	public native static int AddDClockArea(int pno, int jno, int qno, int left,
			int top, int width, int height, int fontColor, String fontName,
			int fontSize, int fontBold, int mode, int format, int spanMode,
			int hour, int minute);

	public native static int SetNetPara();

	/**
	 * 发送数据
	 * @param pno 屏号
	 * @param SendType 发送模式 1为普通 2为SD卡发送
	 * @param hwd 窗口句柄
	 * @return 1：发送成功<br>2：通讯失败<br>3：发送过程中出错
	 */
	public native static int SendControl(int pno, int SendType, int hwd);

	public native static int SetOrderPara(int pno, String diskName);

	/**
	 * 添加字符串到图文区域中
	 * @param pno 屏号(>=1)
	 * @param jno 节目号(>=1)
	 * @param qno 区域号 (>=1)
	 * @param mno 文件号 (>=1)
	 * @param text 字符数据
	 * @param fontname 字体名称
	 * @param fontsize 字体大小
	 * @param fontcolor 字体颜色（255－－红色，65280－－黄色，65535－－绿色)
	 * @param bold 是否粗体(取值0或1)
	 * @param italic 是否斜体(取值0或1)
	 * @param underline 是否下划线 (取值0或1)
	 * @param align 对齐方式(1左对齐 2居中 3右对齐)
	 * @param width 文件显示宽度(目前为区域的宽度)
	 * @param height 文件显示高度(目前为区域的高度)
	 * @param playstyle 特技类型
	 * @param QuitStyle 退场方式
	 * @param playspeed 运行速度（单位：毫秒）
	 * @param delay 停留时间（单位：毫秒）
	 * @param MidText 多行文本上下居中 1为正常，2为上下居中
	 * @return
	 */
	public native static int AddFileString(int pno, int jno, int qno, int mno,
			String text, String fontname, int fontsize, int fontcolor,
			boolean bold, boolean italic, boolean underline, int align,
			int width, int height, int playstyle, int QuitStyle, int playspeed,
			int delay, int MidText);

	/**
	 * 设置通讯模式
	 * @param TransMode 1、网络通讯 2、232通讯 3、485通讯
	 * @param ConType 控制器型号
	 * @return 1：成功<br>0：不成功
	 */
	public native static int SetTransMode(int TransMode, int ConType);

	public native static int SetNetworkPara(int pno, String ip);

	/**
	 * 初始化xml文件
	 */
	public native static void StartSend();

	public native static int SetProgramTimer(int pno, int jno, int TimingModel,
			int WeekSelect, int startSecond, int startMinute, int startHour,
			int startDay, int startMonth, int startWeek, int startYear,
			int endSecond, int endMinute, int endHour, int endDay,
			int endMonth, int endWeek, int endYear);

	/**
	 * 添加单行文本（使用字符串）
	 * @param pno 屏号(>=1)
	 * @param jno 节目号(>=1)
	 * @param qno 区域号(>=1)
	 * @param left 区域左上角顶点x坐标：8的倍数，单位：象素
	 * @param top 区域左上角顶点y坐标
	 * @param width 区域宽度：8的倍数，单位：象素
	 * @param height 区域高度
	 * @param text 显示内容
	 * @param fontname 字体名称
	 * @param fontsize 字体大小
	 * @param fontcolor 字体颜色（255－－红色，65280－－黄色，65535－－绿色);
	 * @param bold 是否粗体
	 * @param italic 是否斜体
	 * @param underline 是否下划线
	 * @param PlayStyle 显示特技（支持左移、右移、上移、下移）
	 * @param Playspeed 显示速度
	 * @param times 保留参数（暂未使用）
	 * @return 1、成功<br>2、参数错误
	 */
	public native static int AddLnTxtString(int pno, int jno, int qno,
			int left, int top, int width, int height, String text,
			String fontname, int fontsize, int fontcolor, boolean bold,
			boolean italic, boolean underline, int PlayStyle, int Playspeed,
			int times);

	/**
	 * 添加静止文本
	 * @param pno 屏号(>=1)
	 * @param jno 节目号(>=1)
	 * @param qno 区域号(>=1)
	 * @param left 区域左上角顶点x坐标：8的倍数，单位：象素
	 * @param top 区域左上角顶点y坐标
	 * @param width 区域宽度：8的倍数，单位：象素
	 * @param height 区域高度
	 * @param FontColor 字体颜色（255-红色，65280-黄色，65535-绿色)
	 * @param fontName 字体名
	 * @param fontSize 字体号
	 * @param b 字体粗细 0：不加粗 1：加粗
	 * @param c 斜体
	 * @param d 下划线
	 * @param text 显示字符串
	 * @return
	 */
	public native static int AddQuitText(int pno, int jno, int qno, int left,
			int top, int width, int height, int FontColor, String fontName,
			int fontSize, boolean b, boolean c, boolean d, String text);

	public native static int AddDClockArea(int pno, int jno, int qno, int left,
			int top, int width, int height, int fontColor, String fontName,
			int fontSize, int fontBold, int Italic, int Underline, int year,
			int week, int month, int day, int hour, int minute, int second,
			int TwoOrFourYear, int HourShow, int format, int spanMode,
			int Advacehour, int Advaceminute);

	public native static int SetTest(int pno, int value);

	public native static int AdjustTime(int PNO);

	public native static int SetPower(int PNO, int power);

	public native static int SetHardPara(int PNO, int Sign, int Mirror,
			int ScanStyle, int LineOrder, int cls, int RGChange, int zhangKong);

	public native static int GetHardPara(int PNO, String FilePath);

	public native static int SearchController(String filePath, String IPAddress);

	public native static int ComSearchController(int PNO, int ComNo,
			int BaudRate, String filePath);

	public native static int SetRemoteNetwork(int pno, String macAddress,
			String ip, String gateway, String subnetmask);

	public native static int SetPowerTimer(int pno, int bTimer, int startHour1,
			int startMinute1, int endHour1, int endMinute1, int startHour2,
			int startMinute2, int endHour2, int endMinute2, int startHour3,
			int startMinute3, int endHour3, int endMinute3);

	public native static int SetBrightnessTimer(int pno, int bTimer,
			int startHour1, int startMinute1, int endHour1, int endMinute1,
			int brightness1, int startHour2, int startMinute2, int endHour2,
			int endMinute2, int brightness2, int startHour3, int startMinute3,
			int endHour3, int endMinute3, int brightness3);

	/**
	 * 发送屏参
	 * @param pno 屏号
	 * @param DBColor 单双色
	 * @param width 宽度
	 * @param height 高度
	 * @return 1：发送成功<br>2：通讯失败<br>3：发送过程中出错
	 */
	public native static int SendScreenPara(int pno, int DBColor, int width, int height);

	public native static int SetTimingLimit(int pno, int FSecond, int FMinute,
			int FHour, int FDay, int FMonth, int FWeek, int FYear);

	public native static int CancelTimingLimit(int pno);

	public native static int GenerateFile(int PNO, String buffer);
	
	/**
	 * 字库发送数据
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
	public native static int AddNeiMaTxtArea(int pno, int jno, int qno, int left, int top, int width, int height,
			String showText, int ShowStyle, int fontname, int fontcolor, int fontsize, int PlayStyle, int QuitStyle,
			int PlaySpeed, int showtime);
	
	public static int getWordCount(String str, String len) {
		str = str.replaceAll("[^\\x00-\\xff]", "**");
		int length = str.length() * Integer.parseInt(len) / 2;
		return length;
	}
	
	/**
	 * 设置屏参
	 * @param transMode 通讯模式
	 * @param comno 串口号
	 * @param pno 屏号
	 * @param baud 波特率
	 * @param width 屏宽
	 * @param height 屏高
	 * @param dbColor 单双色
	 * @return 发送结果
	 */
	public static String setScreenPara(String transMode, String comno, String pno, String baud, String width, String height, String dbColor) {
		SetTransMode(Integer.parseInt(transMode), 3);
		SetSerialPortPara(Integer.parseInt(pno), Integer.parseInt(comno), Integer.parseInt(baud));
		return "" + SendScreenPara(Integer.parseInt(pno), Integer.parseInt(dbColor), Integer.parseInt(width), Integer.parseInt(height));
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
		SetTransMode(Integer.parseInt(transMode), 3);
		SetSerialPortPara(Integer.parseInt(pno), Integer.parseInt(comno), Integer.parseInt(baud));
		StartSend();//初始化xml文件
		AddControl(Integer.parseInt(pno), Integer.parseInt(dbColor));
		AddProgram(Integer.parseInt(pno), 1, 0);
		
		
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
			AddNeiMaTxtArea(Integer.parseInt(pno), 1, 1, x, 0, Integer.parseInt(width), Integer.parseInt(height),
					content2, Integer.parseInt(lattice), 0,	Integer.parseInt(fontColor), 0, gundong, 255, Integer.parseInt(playSpeed), 0);
		} else {
			int x1 = 0, x2 = 0;
			int width1 = Integer.parseInt(width);
			int width2 = Integer.parseInt(width);
			if ("2".equals(align)) {
				x1 = (Integer.parseInt(width) - getWordCount(content1, lattice)) / 2;
				if(getWordCount(content2, lattice) > Integer.parseInt(width)){
					x2=0;
				}else{
					x2 = (Integer.parseInt(width) - getWordCount(content2, lattice)) / 2;
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
			AddNeiMaTxtArea(Integer.parseInt(pno), 1, 1, x1, 0, width1, y, content1, Integer.parseInt(lattice), 0,
					Integer.parseInt(fontColor), 0, 1, 255, 0, 0);
			AddNeiMaTxtArea(Integer.parseInt(pno), 1, 1, 0, Integer.parseInt(lattice), Integer.parseInt(width), y, content2, Integer.parseInt(lattice), 0,
					Integer.parseInt(fontColor1), 0, gundong, 255, Integer.parseInt(playSpeed), 0);
		}
		return "" + SendControl(Integer.parseInt(pno), 1, 0);
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
			String content2, String lattice, String fontColor, String fontColor1) {
		SetTransMode(Integer.parseInt(transMode), 3);
		SetSerialPortPara(Integer.parseInt(pno), Integer.parseInt(comno), Integer.parseInt(baud));
		StartSend();//初始化xml文件
		AddControl(Integer.parseInt(pno), Integer.parseInt(dbColor));
		AddProgram(Integer.parseInt(pno), 1, 0);
		
		if ("".equals(content1)) {
			int x = 0;
			if ("2".equals(align)) {
				x = (Integer.parseInt(width) - getWordCount(content2, lattice)) / 2;
				width = String.valueOf(Integer.parseInt(width) - x);
			} else if ("3".equals(align)) {
				x = Integer.parseInt(width) - getWordCount(content2, lattice);
				width = String.valueOf(Integer.parseInt(width) - x);
			}
			AddNeiMaTxtArea(Integer.parseInt(pno), 1, 1, x, 0, Integer.parseInt(width), Integer.parseInt(height),
					content2, Integer.parseInt(lattice), 0,	Integer.parseInt(fontColor), 0, 1, 255, 0, 0);
		} else {
			int x1 = 0, x2 = 0;
			int width1 = Integer.parseInt(width), width2 = Integer.parseInt(width);
			if ("2".equals(align)) {
				x1 = (Integer.parseInt(width) - getWordCount(content1, lattice)) / 2;
				x2 = (Integer.parseInt(width) - getWordCount(content2, lattice)) / 2;
			} else if ("3".equals(align)) {
				x1 = Integer.parseInt(width) - getWordCount(content1, lattice);
				x2 = Integer.parseInt(width) - getWordCount(content2, lattice);
			}
			width1 = width1 - x1;
			width2 = width2 - x2;
			int y = Integer.parseInt(height) / 2;
			
			AddNeiMaTxtArea(Integer.parseInt(pno), 1, 1, x1, 0, width1, y, content1, Integer.parseInt(lattice), 0,
					Integer.parseInt(fontColor), 0, 1, 255, 0, 0);
			AddNeiMaTxtArea(Integer.parseInt(pno), 1, 1, x2, y, width2, y, content2, Integer.parseInt(lattice), 0,
					Integer.parseInt(fontColor1), 0, 1, 255, 0, 0);
		}
		return "" + SendControl(Integer.parseInt(pno), 1, 0);
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
		SetTransMode(Integer.parseInt(transMode), 3);
		SetSerialPortPara(Integer.parseInt(pno), Integer.parseInt(comno), Integer.parseInt(baud));
		StartSend();//初始化xml文件
		AddControl(Integer.parseInt(pno), Integer.parseInt(dbColor));
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
		SetTransMode(Integer.parseInt(transMode), 3);
		SetSerialPortPara(Integer.parseInt(pno), Integer.parseInt(comno), Integer.parseInt(baud));
		StartSend();//初始化xml文件
		AddControl(Integer.parseInt(pno), Integer.parseInt(dbColor));
		AddProgram(Integer.parseInt(pno), 1, 0);
		
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
		
		AddNeiMaTxtArea(Integer.parseInt(pno), 1, 1, hengStartZuoBiao, zongStartZuoBiao, quyuWidth, Integer.parseInt(height),
				contentAll.replaceAll("#", " "), Integer.parseInt(lattice), 0,Integer.parseInt(fontColor), 0, 1, 255, 0, 0);
		
		return "" + SendControl(Integer.parseInt(pno), 1, 0);
		
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
		SetTransMode(Integer.parseInt(transMode), 3);
		SetSerialPortPara(Integer.parseInt(pno), Integer.parseInt(comno), Integer.parseInt(baud));
		StartSend();//初始化xml文件
		AddControl(Integer.parseInt(pno), Integer.parseInt(dbColor));
		AddProgram(Integer.parseInt(pno), 1, 0);
		
		AddNeiMaTxtArea(Integer.parseInt(pno), 1, 1, 0, 0, Integer.parseInt(width), Integer.parseInt(height),
				contentAll.replaceAll("、", "  "), Integer.parseInt(lattice), 0,Integer.parseInt(fontColor), 0, 1, 255, 0, 0);
		
		return "" + SendControl(Integer.parseInt(pno), 1, 0);
		
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