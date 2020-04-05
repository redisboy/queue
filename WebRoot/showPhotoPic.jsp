<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>照片</title>
<script language="JavaScript">
setTimeout("self.close()",15000)
</script>
</head>
<body>
<img width="100%" height="100%" src="/queue/IcardPic/<%=request.getParameter("cardNumber") %>.jpg"/>
</body>
</html>