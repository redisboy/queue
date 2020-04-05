<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
    <title>跳转页面</title>
    <script type="text/javascript" > 
	/**
 	 * 实现IE浏览器全屏显示功能
     * @param path 根路径
     * @return 
     */
	(function(){
	 	//先定义一个新窗口，窗口的左边距和上边距分别为0，窗口大小比用户屏幕大小小一点点
		var features = "fullscreen=1,status=no,resizable=yes,top=0,left=0,scrollbars=no," 
	        + "titlebar=no,menubar=no,location=no,toolbar=no,z-look=yes," 
	        + "width="+(screen.availWidth-8)+",height="+(screen.availHeight-45);
		var newWin = window.open("window/comprehensiveScreen.jsp", "_blank", features);
		if(newWin != null){
			//关闭窗口屏蔽提示是否关闭
			window.opener = null;
			window.open('','_self');
			//关闭父窗口
			window.close();
		}
	})();
	</script>
	</head>
</html>