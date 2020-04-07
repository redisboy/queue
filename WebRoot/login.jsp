<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>系统登录</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<LINK href="images/style.css" rel=stylesheet>
		<META content="MSHTML 6.00.2900.3562" name=GENERATOR>
		<style type="text/css">
		<!--
		* {
			margin: 0;
			padding: 0;
			border: 0;
		}
		
		BODY {
			FONT-SIZE: 11px;
			font-family: "宋体";
			FONT-FAMILY: Verdana, Geneva, Arial, Helvetica, sans-serif;
			background-image: url(images/login_bk.gif);
		}
		
		INPUT {
			FONT-SIZE: 11px;
			FONT-FAMILY: Verdana, Geneva, Arial, Helvetica, sans-serif
		}
		
		TD {
			FONT-SIZE: 11px;
			FONT-FAMILY: Verdana, Geneva, Arial, Helvetica, sans-serif
		}
		
		.login_input {
			BORDER-RIGHT: #d0deec 1px solid;
			BORDER-TOP: #d0deec 1px solid;
			FONT-SIZE: 9pt;
			BORDER-LEFT: #d0deec 1px solid;
			COLOR: #ffffff;
			BORDER-BOTTOM: #d0deec 1px solid;
			HEIGHT: 18px;
			BACKGROUND-COLOR: #256fb2;
			TEXT-DECORATION: none
		}
		
		.btn_tj {
			background: url(images/qd_but.gif) no-repeat;
			width: 47px;
			height: 20px;
			cursor: hand;
		}
		
		.btn_cz {
			width: 47px;
			height: 20px;
			background: url(images/qx_but.gif) no-repeat;
			margin-left: 20px;
			cursor: hand;
		}
		-->
		</style>
	</HEAD>
	<body>
		<BR/><BR/><BR/><BR/><BR/><BR/><BR/>
		<BR/><BR/><BR/><BR/><BR/><BR/><BR/>
		<form action="" name="form1" method="post">
			
			<TABLE cellSpacing=0 cellPadding=0 width=588 align=center border=0>
				<TBODY>
					<TR>
						<TD vAlign=top background=images/login.gif height=384>
							<BR/><BR/>
							<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0 style="position: relative;top:70px;">
								<TBODY >
									<TR >
										<TD width="100%" height=130 colspan=2 align="center">
											<br/>
											<font color="#ff0000" size="4"><c:out value="${msg}" /></font>
										</TD>
									</TR>
									
									<TR>
										<TD>&nbsp;</TD>
										<TD vAlign=top height=35>
											<INPUT class=login_input id="yhdh" tabIndex=1 size=18  />
											<INPUT id="J_userName" name="J_userName" type="hidden"/>
										</TD>
									</TR>
									<TR>
										<TD width="60%" height=4>&nbsp;</TD>
										<TD width="40%">&nbsp;</TD>
									</TR>
									<TR>
										<TD>&nbsp;</TD>
										<TD vAlign=top height=35>
											<INPUT class=login_input id="mm" tabIndex=2 type=password size=18 />
											<INPUT id="J_passWrod"  name="J_passWrod" type="hidden"/>
										</TD>
									</TR>
								
									<TR>
										<TD>&nbsp;</TD>
										<TD height=30>
											<TABLE height=30 cellSpacing=0 cellPadding=0 width="100%" border=0>
												<TBODY>
													<TR>
														<TD vAlign=top width="50%" height=26>
															<input name="loginbt" type="button" class="btn_tj" id="loginbt" value="" onclick="login()">
															<input name="resetbt" type="reset" class="btn_cz" id="resetbt" value="">
														</TD>
													</TR>
													<tr>
														<td style="text-align:right;font-size:18px;	font-weight:300;color:white;">版本号:1.0&nbsp;&nbsp;</td>&nbsp;
													</tr>
												</TBODY>
											</TABLE>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
		</form>
	</body>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
	
    
    function encode64(input){
    	// base64加密开始  
        var keyStr = "ABCDEFGHIJKLMNOP" + "QRSTUVWXYZabcdef" + "ghijklmnopqrstuv"  
                + "wxyz0123456789+/" + "=";  
          
        var output = "";  
        var chr1, chr2, chr3 = "";  
        var enc1, enc2, enc3, enc4 = "";  
        var i = 0;  
        do {  
            chr1 = input.charCodeAt(i++);  
            chr2 = input.charCodeAt(i++);  
            chr3 = input.charCodeAt(i++);  
            enc1 = chr1 >> 2;  
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);  
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);  
            enc4 = chr3 & 63;  
            if (isNaN(chr2)) {  
                enc3 = enc4 = 64;  
            } else if (isNaN(chr3)) {  
                enc4 = 64;  
            }  
            output = output + keyStr.charAt(enc1) + keyStr.charAt(enc2)  
                    + keyStr.charAt(enc3) + keyStr.charAt(enc4);  
            chr1 = chr2 = chr3 = "";  
            enc1 = enc2 = enc3 = enc4 = "";  
        } while (i < input.length);  
  
        return output;  
    }  
	
	function login(){
		var yhdh = document.form1.yhdh.value;
		var mm = document.form1.mm.value;
		
		if(yhdh.length == 0){
			alert("用户名不能为空！");
			document.form1.yhdh.focus();
			return false;
		}
		
		if(mm.length == 0){
			alert("密码不能为空！");
			document.form1.mm.focus();
			return false;
		}
		mm=encode64(mm); 
		yhdh=encode64(yhdh);  //对数据加密  
		var user=document.getElementById('J_userName');
		var pass=document.getElementById('J_passWrod');
		user.value=yhdh;
		pass.value=mm;
		
		document.form1.action = "login.action";
		document.form1.submit();
	}
	
	/* function document.onkeydown(){
		if(event.keyCode == 13){
			document.getElementById("loginbt").click();   
			return false;                               
		}
	} */
	</script>
</html>
