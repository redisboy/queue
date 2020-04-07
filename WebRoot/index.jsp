<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>叫号管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
(function() {
    if (window.screen) {
    	var myw = screen.availWidth;
    	var myh = screen.availHeight;
    	window.moveTo(0, 0);
    	window.resizeTo(myw, myh);
    }
})();
</script>
</head>
<frameset id=frame1 rows="110,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="/queue/index/top.html" name="top" scrolling="No" noresize="noresize" id="top" title="topFrame" />
  <frameset id=frame2 cols="195,12,*" frameborder="no" border="0" framespacing="0">
    <frame src="/queue/index/menu.jsp" name="left" noresize="noresize" id="left" title="leftFrame" />
    <frame src="/queue/index/middle.html" name="middleFrame" scrolling="no" id="middleFrame" title="middleFrame" />
    <frame src="/queue/index/welcome.html" name="main" id="main" title="main" />
  </frameset>
</frameset>
</html>