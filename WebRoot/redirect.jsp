<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" pageEncoding="GBK"%>
<% String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   
   String action = "";
   String kw = request.getParameter("kw");
   String tv = request.getParameter("tv");
   String qp = request.getParameter("qp");
   if("qh".equals(qp)){
	   action = "indexQuHao.jsp";
   } else if ("sfz".equals(qp)){
	   action = "indexSFZ.html";
   } else if ("fsfz".equals(qp)){
	   action = "indexFSFZ.html";
   } else if ("zz".equals(qp)){
	   action = "indexZZ.html";
   } else if ("zzlb".equals(qp)){
	   action = "indexZZ_LB.html";
   } else if ("zz1280".equals(qp)){
	   action = "index_1280_1024.html";
   } else if ("zzjm".equals(qp)){
	   action = "indexZZ_JM.html";
   } else if ("zzj".equals(qp)){
	   action = "indexZZ.jsp";
   } else if ("zzgy".equals(qp)){
	   action = "indexGY.html";	   
   } else if ("pc".equals(kw)) {
	   action = "pcwindow.action";
   } else if ("la".equals(tv)) {
	   action = "window/tv_window_lin_an.jsp";
   } else if ("yc".equals(tv)) {
	   action = "window/tv_window_yin_chuan.jsp";
   } else if ("wx".equals(tv)) {
	   action = "window/tv_window_wu_xi.jsp";
   } else if ("jl".equals(tv)) {
	   action = "window/tv_window_ji_lin_1.jsp";
   } else if ("liuz".equals(tv)) {
	   action = "window/tv_window_liuzhou.jsp";
   } else if ("wx2".equals(tv)) {
	   action = "window/tv_window_wu_xi_2.jsp";
   } else if ("jl2".equals(tv)) {
	   action = "window/tv_window_ji_lin_2.jsp";
   } else if ("xh".equals(tv)) {
	   action = "window/tv_window_xin_hui.jsp";
   } else if("dp".equals(tv)){
   	   action = "window/comprehensiveScreen.jsp";
   } else if("ly".equals(tv)){
   	   action = "window/tv_window_lin_yi.jsp";
   } else if("zs".equals(tv)){
   	   action = "window/tv_window_zhong_shan.jsp";
   } else if("nb".equals(tv)){
   	   action = "window/tvScreen.jsp";
   } else if("wf".equals(tv)){
   	   action = "advertise/showQuhaoSum.jsp";
  
   } else if("wftj".equals(tv)){
   	   action = "ygtj.action";
  
   } else if("led".equals(tv)){
   	   response.sendRedirect("number/showLEDqueue.action?flag=0");
   	   %>
   	   	<script type="text/javascript">
//		<!--
			window.close();
		//-->
		</script>
   	   <%
   }else if("led1".equals(tv)){
   	   response.sendRedirect("number/showLEDqueue.action?flag=1");
   	   %>
   	   	<script type="text/javascript">
//		<!--
			window.close();
		//-->
		</script>
   	   <%
   }else if("led2".equals(tv)){
   	   response.sendRedirect("number/showLEDqueue.action");
   	   %>
   	   	<script type="text/javascript">
//		<!--
			window.close();
		//-->
		
		</script>
   	   <%
   }else if("led3".equals(tv)){
   	   response.sendRedirect("number/showLEDqueue.action?flag=2");
   	   %>
   	   	<script type="text/javascript">
//		<!--
			window.close();
		//-->
		</script>
   	   <%
   }else if("led4".equals(tv)){
	   response.sendRedirect("number/showLEDqueue.action?pjxxp=liuz"); //������Ϣ��=����  
	   %>
  	   	<script type="text/javascript">
		<!--
			window.close();
		//-->
		
		</script>
  	   <%
   }else if("led5".equals(tv)){
   	   response.sendRedirect("number/showLEDqueue.action?flag=3");
   	   %>
   	   	<script type="text/javascript">
//		<!--
			window.close();
		//-->
		</script>
   	   <%
   }
   basePath += action;
%>
<html>
	<head> 
    <title>��תҳ��</title>
    <script type="text/javascript" > 
	/**
 	 * ʵ��IE�����ȫ����ʾ����
     * @param path ��·��
     * @return 
     */
	(function(){
	 	//�ȶ���һ���´��ڣ����ڵ���߾���ϱ߾�ֱ�Ϊ0�����ڴ�С���û���Ļ��ССһ���
		var features = "fullscreen=1,status=no,resizable=yes,top=0,left=0,scrollbars=no," 
	        + "titlebar=no,menubar=no,location=no,toolbar=no,z-look=yes," 
	        + "width="+(screen.availWidth-8)+",height="+(screen.availHeight-45);
		var newWin = window.open("<%=basePath%>", "_blank", features);
		if(newWin != null){
			//�رմ���������ʾ�Ƿ�ر�
			window.opener = null;
			window.open('','_self');
			//�رո�����
			window.close();
		}
	})();
	</script>
	</head>
</html>