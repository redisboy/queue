package com.suntendy.queue.util;

import jxl.format.Alignment;  
import jxl.format.Border;  
import jxl.format.BorderLineStyle;  
import jxl.format.Colour;  
import jxl.format.UnderlineStyle;  
import jxl.format.VerticalAlignment;  
import jxl.write.WritableCellFormat;  
import jxl.write.WritableFont;  
import jxl.write.WriteException;  
  
public class ExcelUtil {  
    /** 
     * 返回大标题格式 
     */  
    public static WritableCellFormat createWcfTitle() {  
        WritableFont wfc_big = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,  
                UnderlineStyle.NO_UNDERLINE, Colour.BLACK);  
        WritableCellFormat wcf_title = new WritableCellFormat(wfc_big);  
        try {  
            wcf_title.setBorder(Border.ALL, BorderLineStyle.THIN);  
            wcf_title.setAlignment(Alignment.CENTRE);  
            wcf_title.setVerticalAlignment(VerticalAlignment.CENTRE);  
            wcf_title.setWrap(true);  
  
        } catch (WriteException e) {  
            e.printStackTrace();  
        }  
        return wcf_title;  
    }  
  
    /** 
     * 返回正文格式 
     */  
    public static WritableCellFormat createWcfText() {  
        WritableFont wfc_small = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD, false,  
                UnderlineStyle.NO_UNDERLINE, Colour.BLACK);  
        WritableCellFormat wcf_text = new WritableCellFormat(wfc_small);  
        try {  
            wcf_text.setBorder(Border.ALL, BorderLineStyle.THIN);  
            wcf_text.setAlignment(Alignment.LEFT);  
            wcf_text.setWrap(true);  
            wcf_text.setVerticalAlignment(VerticalAlignment.CENTRE);  
        } catch (WriteException e) {  
            e.printStackTrace();  
        }  
        return wcf_text;  
    }  
} 
