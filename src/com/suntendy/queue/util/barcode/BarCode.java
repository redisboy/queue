package com.suntendy.queue.util.barcode;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;

import org.jbarcode.JBarcode;
import org.jbarcode.encode.BarcodeEncoder;
import org.jbarcode.encode.Code39Encoder;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.WideRatioCodedPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.jbarcode.util.ImageUtil;

import com.opensymphony.webwork.ServletActionContext;


/**
 * 
 *@类功能说明:java生成一维码图片
 *@修改人员名:陈甲帅
 *@修改日期:2016-11-7
 *@创建时间:2016-11-7
 * ----------------------------------------------------------------------------------------------------------
 *  修改次数         修改人员    修改内容                       修改原因                     
 *                                                                                                                                   
 * @备注：
 * @版本： V1.0
 */

public class BarCode {

	
	public static void main(String args[]){
		try {
		byte[] by=createDimensionalCode("A0001");
		//生成的图片高度是固定60像素
		String path="D:/Tomcat 6.0/webapps/queue/IcardPic/12w3.jpg";//输出gif、jpg、png、bmp都行
//		ServletContext d=ServletActionContext.getServletContext();
//		String path=d.getRealPath("/");
		//打开输入流
		FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
		//将byte数组写入本地硬盘
		imageOutput.write(by, 0, by.length);
		//关闭输入流
		   imageOutput.close();
		   
		 scale("D:/Tomcat 6.0/webapps/queue/IcardPic/12w3.jpg", "D:/Tomcat 6.0/webapps/queue/IcardPic/12w4.jpg", 2);
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
		/**
		* 
		* @方法功能备注:生成最终一维码字节数组
		* @创建人员:陈甲帅
		* @修改日期:2016-11-7
		* @param value
		* @return:
		*/
    public static byte[] createDimensionalCode(String value){
        return createCode(Code39Encoder.class,value,false);
    }
      
    public static byte[] createCode(Class<?> clazz,String value,boolean checkDigit){  
          try{  
              JBarcode localJBarcode = new JBarcode(getUnique(clazz),WidthCodedPainter.getInstance(),EAN13TextPainter.getInstance());
              localJBarcode.setBarHeight(10);//条码高度
//              localJBarcode.setWideRatio(5);//条码粗细
              localJBarcode.setPainter(WideRatioCodedPainter.getInstance());
              localJBarcode.setTextPainter(BaseLineTextPainter.getInstance());
              localJBarcode.setCheckDigit(checkDigit);
              localJBarcode.setShowCheckDigit(checkDigit);
              return getImageBytes(localJBarcode.createBarcode(value));
          }catch (Exception e) {
            e.printStackTrace();
            return null;
        }  
    }  
    //获取唯一单例的对象
    private static BarcodeEncoder getUnique(Class<?> clazz) throws Exception{
        Constructor<?>[] constructors=clazz.getDeclaredConstructors();
        Constructor<?> privateConstructor = constructors[0];

        privateConstructor.setAccessible(true);
        return (BarcodeEncoder)privateConstructor.newInstance();
    }  
      
    //获得图片字节码数组
    private static byte[] getImageBytes(BufferedImage paramBufferedImage) throws IOException{
    	return ImageUtil.encode(paramBufferedImage,"jpeg", 96, 100);
    }
	 
	 
    
    /**
	 * 等比例放大图象
	 * 
	 * @param imgUrl 图像路径
	 * @param resultImgUrl 放大后的存放路径
	 * @param scale 放大倍数
	 * @throws IOException
	 */
	public static void scale(String imgUrl, String resultImgUrl, int scale)
			throws IOException {
		BufferedImage src = ImageIO.read(new File(imgUrl));
		int width = src.getWidth();
		int height = src.getHeight();

		width = width * scale;
		height = height * scale;

		Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		BufferedImage tag = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = tag.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		ImageIO.write(tag, "JPEG", new File(resultImgUrl));
	}
    
    
    
}
