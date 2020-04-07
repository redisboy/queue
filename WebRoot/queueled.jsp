<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   
   
   String flag = (String) request.getAttribute("flag");
   String width =(String) request.getAttribute("width");
   String height =(String) request.getAttribute("height");
   String style =(String) request.getAttribute("style");
   String showtype =(String) request.getAttribute("showtype");
   String hallname =(String) request.getAttribute("hallname");
   String gdxx =(String) request.getAttribute("gdxx");
   String gdxx1 =(String) request.getAttribute("gdxx1");
   String title1 =(String) request.getAttribute("title1");
   String content1 =(String) request.getAttribute("content1");
   if(!"".equals(content1) && content1 != null){
	   content1 = content1.replace("\"","\'");
   }
   String title2 =(String) request.getAttribute("title2");
   String content2 =(String) request.getAttribute("content2");
   if(!"".equals(content2) && content2 != null){
	   content2 = content2.replace("\"","\'");
   }
   String title3 =(String) request.getAttribute("title3");
   String content3 =(String) request.getAttribute("content3");
   if(!"".equals(content3) && content3 != null){
	   content3 = content3.replace("\"","\'");
   }
   String title4 =(String) request.getAttribute("title4");
   String content4 =(String) request.getAttribute("content4");
   if(!"".equals(content4) && content4 != null){
	   content4 = content4.replace("\"","\'");
   }
   String wbbjqnr1 =(String) request.getAttribute("wbbjqnr");//文本编辑器内容
	String duilie =(String) request.getAttribute("duilie");
   if(!"".equals(wbbjqnr1) && wbbjqnr1 != null){
	   wbbjqnr1 = wbbjqnr1.replace("\"","\'");
   }
   
   String wbbjqnr = wbbjqnr1;
  
    String action = "window/tv_queue_led_1.jsp";
    if("1".equals(showtype)){
    	action = "window/tv_queue_led_2.jsp";
    }else if("2".equals(showtype)){
    	if("0".equals(flag)){
    		action = "window/tv_queue_led_jn.jsp";
    	}else if("1".equals(flag)){
    		action = "window/tv_queue_led_jn1.jsp";
    	}
    }else if("3".equals(showtype)){
    	action = "window/tv_queue_led_guiyang.jsp";
    }else if("4".equals(showtype)){
    	action = "window/tv_queue_led_yinchuanDS.jsp";
    }else if("5".equals(showtype)){
    	action = "window/tv_queue_led_weifang.jsp";
    }else if("6".equals(showtype)){
    	if("0".equals(flag)){
    		action = "window/tv_queue_led_jiangmen.jsp";
    	}else if("1".equals(flag)){
    		action = "window/tv_queue_led_jiangmenZHP.jsp";
    	}
    }else if("7".equals(showtype)){
    	action = "window/tv_queue_led_haikou.jsp";
    }else if("8".equals(showtype)){
    	action = "window/tv_queue_led_xinhui.jsp";
    }else if("9".equals(showtype)){
    	action = "window/tv_queue_led_chengdu.jsp";
    }else if("10".equals(showtype)){
    	action = "window/tv_queue_led_xinhui.jsp";
    }else if("11".equals(showtype)){
    	action = "window/tv_queue_led_old_xinhui.jsp";
    }else if("12".equals(showtype)){
    	action = "window/tv_queue_led_ningbo.jsp";
    }else if("13".equals(showtype)){
    	action = "window/tv_queue_led_liuzhouZHP.jsp";
    }else if("14".equals(showtype)){
    	action = "window/tv_queue_led_haikou_qn.jsp";
    }else if("lzpjxxp".equals(showtype)){
    	action = "window/tv_queue_led_liuzhouPJXXP.jsp";     //柳州评价信息屏页面  
    }else if("15".equals(showtype)){
    	action = "window/tv_queue_led_wulanchabu.jsp";
    }else if("16".equals(showtype)){
    	action = "window/tv_queue_led_chengduDL.jsp";
    }else if("17".equals(showtype)){
    	action = "window/tv_queue_led_chengdu_sisuo.jsp";
    }else if("18".equals(showtype)){
    	action = "window/tv_queue_led_chengdu_yisuo.jsp";
    }else if("19".equals(showtype)){
    	action = "window/tv_queue_led_chengdu_xinjin.jsp";
    }else if("20".equals(showtype)){
    	if("0".equals(flag)){
    		action = "window/tv_queue_led_fangchenggang.jsp";
    	}else if("1".equals(flag)){
    		action = "window/tv_queue_led_fangchenggangSP.jsp";
    	}
    }else if("21".equals(showtype)){
    	action = "window/tv_queue_led_anyang.jsp";
    }else if("22".equals(showtype)){
			action = "window/tv_queue_led_guigang_2.jsp";
    }else if("23".equals(showtype)){
    	if("0".equals(flag)){
    		action = "window/tv_queue_led_nanning_pj.jsp";
    	}else if("1".equals(flag)){
    		action = "window/tv_queue_led_nanningLZ.jsp";
    	}
    }else if("24".equals(showtype)){
    	action = "window/tv_queue_led_zaozhuang.jsp";
    }else if("25".equals(showtype)){
    	if("0".equals(flag)){
			action = "window/tv_queue_led_nanning_jb.jsp";
		}else if("1".equals(flag)){
			action = "window/tv_queue_led_nanningLZ_jb.jsp";
		}
    }else if("26".equals(showtype)){
    	action = "window/tv_queue_led_zhuhai_zongsuo.jsp";
    }else if("27".equals(showtype)){
    	action = "window/tv_queue_led_langfang.jsp";
    }else if("28".equals(showtype)){
    	if("0".equals(flag)){
    		action = "window/tv_queue_led_zhongshan.jsp";
		}else if("1".equals(flag)){
			action = "window/tv_queue_led_zhongshan_ZHP.jsp";
		}
    }else if("29".equals(showtype)){
    	if("0".equals(flag)){
    		action = "window/tv_queue_led_zhuhai_xisuoLz.jsp";
		}else if("1".equals(flag)){
			action = "window/tv_queue_led_zhuhai_xisuo.jsp";
		}
    }else if("30".equals(showtype)){
    	if("0".equals(flag)){
    		action = "window/tv_queue_led_wuzhou.jsp";
		}else if("1".equals(flag)){
			action = "window/tv_queue_led_wuzhou_CY.jsp";
		}else if("2".equals(flag)){
			action = "window/tv_queue_led_wuzhou2.jsp";
		}else if("3".equals(flag)){
			action = "window/tv_queue_led_wuzhou_jsr.jsp";
		}
    }else if("31".equals(showtype)){
    		action = "window/tv_queue_led_foshan.jsp";
    }else if("32".equals(showtype)){
		action = "window/tv_queue_led_foshan_jiaxie.jsp";
}
    
    
    
    
   	basePath += action;
%>
<html>
	<head>
    <title>跳转页面</title>
    <script type="text/javascript" >
	/**
 	 * 实现IE浏览器全屏显示功能
     * @param path 根路径
     * @return 
     */
     
     var fontsize="<%=style %>";var hallname="<%=hallname %>";var gdxx="<%=gdxx %>";
     var gdxx1="<%=gdxx1 %>";var title1="<%=title1 %>";var content1="<%=content1 %>";
     var title2="<%=title2 %>";var content2="<%=content2 %>";var title3="<%=title3 %>";
     var content3="<%=content3 %>";var title4="<%=title4 %>";var content4="<%=content4 %>";
     var width="<%=width %>";var height="<%=height %>";var wbbjqnr="<%=wbbjqnr %>";
     var duilie="<%=duilie %>";
     
	(function(){
	 	var features = "fullscreen=1,status=no,resizable=yes,top=0,left=0,scrollbars=no,alwaysRaised=yes"//fullscreen=1,
				+ "titlebar=no,menubar=no,location=no,toolbar=no,width=1088,height=384,z-look=yes,top=0,left=0" 
				var newWin = window.open("<%=basePath%>", "_blank", features);
	/**			if(newWin != null){
					//关闭窗口屏蔽提示是否关闭
					window.opener = null;
					window.open('','_self');
					//关闭父窗口
					window.close();
				}**/
	})();
	</script>
	</head>
</html>