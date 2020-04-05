<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="/webwork" prefix="ww"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>

<html>
  <head>
    <title>LED队列屏内容设置</title>
  </head>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script type="text/javascript">
window.onload = function(){
        editor = CKEDITOR.replace('fcknr'); //参数‘content’是textarea元素的name属性值，而非id属性值
        title1content = CKEDITOR.replace('title1content');
        title2content = CKEDITOR.replace('title2content');
        title3content = CKEDITOR.replace('title3content');
        title4content = CKEDITOR.replace('title4content');
    }

function setLed() { 
	editor.updateElement();
	title1content.updateElement();
	title2content.updateElement();
	title3content.updateElement();
	title4content.updateElement();
 	document.form1.action = "updateLED_Content.action";
  	document.form1.submit();

}
function showPic(fanshi){
	if("0" == fanshi.value){
		$('#typePic').html("<img src='images/fangshi1.jpg'/>");
	}else{
		$('#typePic').html("<img src='images/fangshi2.jpg'/>");
	}
}
function concealPic(){
	$('#typePic').html("");
}
function isShowPz(isValue){
		if("2"==isValue){
			$("#jngdnr").css("display","")
		}else{
			$("#jngdnr").css("display","none")
		}
}
</script>
<body>
<form action="" method="post" name="form1">
<input type="hidden" name="isExist" value="${isExist}">
<input name="id" id="id" type="hidden" value="${id}"/>
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" >
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">LED队列屏内容设置</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    <div id ="typePic" style="position:absolute;left:400px;top:95px;"></div>
          <table width="55%" border="0" cellpadding="1" cellspacing="1" class="table_back">
          	<tr>
          		<td class=tableheader1 width=10% height=30>显示模式:</td>
	          	<td width=500 bgColor=#f1f9fd height=30>&nbsp;
		            <input type="radio" name="showType" value="0" checked onmouseover="showPic(this)" onmouseout="concealPic()" onclick="isShowPz(0)"/>模式1
					<input type="radio" name="showType" value="1" onmouseover="showPic(this)" onmouseout="concealPic()" onclick="isShowPz(1)"/>模式2
					<input type="radio" name="showType" value="2" onclick="isShowPz(2)" />济宁
					<input type="radio" name="showType" value="3" onclick="isShowPz(3)" />贵阳
					<input type="radio" name="showType" value="4" onclick="isShowPz(4)" />银川
					<input type="radio" name="showType" value="5" onclick="isShowPz(5)" />潍坊
					<input type="radio" name="showType" value="6" onclick="isShowPz(6)" />江门
					<input type="radio" name="showType" value="7" onclick="isShowPz(7)" />海口
					<input type="radio" name="showType" value="8" onclick="isShowPz(8)" />金阳
					<input type="radio" name="showType" value="9" onclick="isShowPz(9)" />成都
					<input type="radio" name="showType" value="10" onclick="isShowPz(10)" />新会新厅
					<input type="radio" name="showType" value="11" onclick="isShowPz(11)" />新会老厅
					<input type="radio" name="showType" value="12" onclick="isShowPz(12)" />宁波
					<input type="radio" name="showType" value="13" onclick="isShowPz(13)" />柳州
					<input type="radio" name="showType" value="14" onclick="isShowPz(14)" />琼南
					<input type="radio" name="showType" value="15" onclick="isShowPz(15)" />乌兰察布
					<input type="radio" name="showType" value="16" onclick="isShowPz(16)" />成都队列
					<input type="radio" name="showType" value="17" onclick="isShowPz(17)" />成都四所
					<input type="radio" name="showType" value="18" onclick="isShowPz(18)" />成都一所
					<input type="radio" name="showType" value="19" onclick="isShowPz(19)" />成都新津
					<input type="radio" name="showType" value="20" onclick="isShowPz(20)" />防城港
					<input type="radio" name="showType" value="21" onclick="isShowPz(21)" />安阳
					<input type="radio" name="showType" value="22" onclick="isShowPz(22)" />贵港
					<input type="radio" name="showType" value="23" onclick="isShowPz(23)" />南宁
					<input type="radio" name="showType" value="24" onclick="isShowPz(24)" />枣庄
					<input type="radio" name="showType" value="25" onclick="isShowPz(25)" />南宁江北
					<input type="radio" name="showType" value="26" onclick="isShowPz(26)" />珠海总所
					<input type="radio" name="showType" value="27" onclick="isShowPz(27)" />廊坊
					<input type="radio" name="showType" value="28" onclick="isShowPz(28)" />中山
					<input type="radio" name="showType" value="29" onclick="isShowPz(29)" />珠海西所
					<input type="radio" name="showType" value="30" onclick="isShowPz(30)" />梧州
					<input type="radio" name="showType" value="30" onclick="isShowPz(31)" />佛山支队
					<input type="radio" name="showType" value="30" onclick="isShowPz(32)" />佛山驾协
	            </td>
	        </tr>
	        <tr>
				<td width="22%" height="35" bgcolor="#FFFFFF" class="tableheader1">窗口高:</td>
				<td width="78%" height="35" bgcolor="#F1F9FD">&nbsp;
				 <input name="windowheight" id="windowheight" type="text" value="${led.windowheight }"/>
				</td>
			</tr>
			<tr>
				<td width="22%" height="35" bgcolor="#FFFFFF" class="tableheader1">窗口宽:</td>
				<td width="78%" height="35" bgcolor="#F1F9FD">&nbsp;
				 <input name="windowwidth" id="windowwidth" type="text" value="${led.windowwidth }"/>
				</td>
			</tr>
          	<tr>
				<td width="22%" height="35" bgcolor="#FFFFFF" class="tableheader1">大厅名称:</td>
				<td width="78%" height="35" bgcolor="#F1F9FD">&nbsp;
				 <input name="hallname" id="hallname" type="text" value="${led.hallname }"/>
				</td>
			</tr>
            <tr>
				<td width="22%" height="35" bgcolor="#FFFFFF" class="tableheader1">滚动信息:</td>
				<td width="78%" height="35" bgcolor="#F1F9FD">&nbsp;
				 <input name="gdxx" id="gdxx" type="text" value="${led.gdxx }"/>
				</td>
			</tr>
            <tr>
				<td width="22%" height="35" bgcolor="#FFFFFF" class="tableheader1">滚动信息1:</td>
				<td width="78%" height="35" bgcolor="#F1F9FD">&nbsp;
				 <input name="gdxx1" id="gdxx1" type="text" value="${led.gdxx1 }"/>
				</td>
			</tr>
			<tr>
				<td width="22%" height="35" bgcolor="#FFFFFF" class="tableheader1">标题1:</td>
				<td width="78%" height="35" bgcolor="#F1F9FD">&nbsp;
				 	<select name="title1" id="title1" style="width:155px;font-size:13px">
						<option value="">-请选择-</option>
						<option value="警务公开" <ww:if test="${led.title1=='警务公开'}">selected</ww:if>>警务公开</option>
						<option value="排队信息" <ww:if test="${led.title1=='排队信息'}">selected</ww:if>>排队信息</option>
						<option value="公告信息" <ww:if test="${led.title1=='公告信'}">selected</ww:if>>公告信息</option>
						<option value="领证信息" <ww:if test="${led.title1=='领证信息'}">selected</ww:if>>领证信息</option>
						<option value="车管业务" <ww:if test="${led.title1=='车管业务'}">selected</ww:if>>车管业务</option>
					</select>
				</td>
			</tr>
			<tr>
				<td width="22%" height="35" bgcolor="#FFFFFF" class="tableheader1">内容1:</td>
				<td width="78%" height="35" bgcolor="#F1F9FD">&nbsp;
				 <!-- <textarea name="title1content" id="title1content" rows="3" style="width:300px;" name="message">${led.title1content }</textarea> -->
				 <textarea id="title1content" rows="30" cols="50" name="title1content"></textarea>
				</td>
			</tr>
        	<tr>
				<td width="22%" height="35" bgcolor="#FFFFFF" class="tableheader1">标题2:</td>
				<td width="78%" height="35" bgcolor="#F1F9FD">&nbsp;
					<select name="title2" id="title2" style="width:155px;font-size:13px">
						<option value="">-请选择-</option>
						<option value="警务公开" <ww:if test="${led.title2=='警务公开'}">selected</ww:if>>警务公开</option>
						<option value="排队信息" <ww:if test="${led.title2=='排队信息'}">selected</ww:if>>排队信息</option>
						<option value="公告信息" <ww:if test="${led.title2=='公告信'}">selected</ww:if>>公告信息</option>
						<option value="领证信息" <ww:if test="${led.title2=='领证信息'}">selected</ww:if>>领证信息</option>
						<option value="车管业务" <ww:if test="${led.title1=='车管业务'}">selected</ww:if>>车管业务</option>
					</select>
				</td>
			</tr>
			<tr>
				<td width="22%" height="35" bgcolor="#FFFFFF" class="tableheader1">内容2:</td>
				<td width="78%" height="35" bgcolor="#F1F9FD">&nbsp;
				 <!-- <textarea name="title2content" id="title2content" rows="3" style="width:300px;" name="message">${led.title2content }</textarea> -->
				 <textarea id="title2content" rows="30" cols="50" name="title2content"></textarea>
				</td>
			</tr>
			<tr>
				<td width="22%" height="35" bgcolor="#FFFFFF" class="tableheader1">标题3:</td>
				<td width="78%" height="35" bgcolor="#F1F9FD">&nbsp;
				 	<select name="title3" id="title3" style="width:155px;font-size:13px">
						<option value="">-请选择-</option>
						<option value="警务公开" <ww:if test="${led.title3=='警务公开'}">selected</ww:if>>警务公开</option>
						<option value="排队信息" <ww:if test="${led.title3=='排队信息'}">selected</ww:if>>排队信息</option>
						<option value="公告信息" <ww:if test="${led.title3=='公告信息'}">selected</ww:if>>公告信息</option>
						<option value="领证信息" <ww:if test="${led.title3=='领证信息'}">selected</ww:if>>领证信息</option>
						<option value="车管业务" <ww:if test="${led.title1=='车管业务'}">selected</ww:if>>车管业务</option>
					</select>
				</td>
			</tr>
			<tr>
				<td width="22%" height="35" bgcolor="#FFFFFF" class="tableheader1">内容3:</td>
				<td width="78%" height="35" bgcolor="#F1F9FD">&nbsp;
				 <!-- <textarea name="title3content" id="title3content" rows="3" style="width:300px;" name="message">${led.title3content }</textarea> -->
				 <textarea id="title3content" rows="30" cols="50" name="title3content"></textarea>
				</td>
			</tr>
			<tr>
				<td width="22%" height="35" bgcolor="#FFFFFF" class="tableheader1">标题4:</td>
				<td width="78%" height="35" bgcolor="#F1F9FD">&nbsp;
				 	<select name="title4" id="title4" style="width:155px;font-size:13px">
						<option value="">-请选择-</option>
						<option value="警务公开" <ww:if test="${led.title4=='警务公开'}">selected</ww:if>>警务公开</option>
						<option value="排队信息" <ww:if test="${led.title4=='排队信息'}">selected</ww:if>>排队信息</option>
						<option value="公告信息" <ww:if test="${led.title4=='公告信'}">selected</ww:if>>公告信息</option>
						<option value="领证信息" <ww:if test="${led.title4=='领证信息'}">selected</ww:if>>领证信息</option>
						<option value="车管业务" <ww:if test="${led.title1=='车管业务'}">selected</ww:if>>车管业务</option>
					</select>
				</td>
			</tr>
			<tr>
				<td width="22%" height="35" bgcolor="#FFFFFF" class="tableheader1">内容4:</td>
				<td width="78%" height="35" bgcolor="#F1F9FD">&nbsp;
				 <!-- <textarea name="title4content" id="title4content" rows="30" cols="50">${led.title4content }</textarea> -->
				 <textarea id="title4content" rows="30" cols="50" name="title4content"></textarea>
				</td>
			</tr>
			<tr id="jngdnr" style="display:none">
				<td width="22%" height="35" bgcolor="#FFFFFF" class="tableheader1">滚动内容:</td>
				<td width="78%" height="35" bgcolor="#F1F9FD">&nbsp;
					<textarea id="fcknr" rows="30" cols="50" name="fcknr"></textarea>
				 <!-- <FCK:editor instanceName="fcknr" 	basePath="/plugs/fckeditor/" width="100%"  height="600">
		          		<jsp:attribute name="value">${fckContent}</jsp:attribute>
		          </FCK:editor> -->
				</td>
			</tr>
		<!-- 	<tr>
				<td width="22%" height="35" bgcolor="#FFFFFF" class="tableheader1">滚动延续内容:</td>
				<td width="78%" height="35" bgcolor="#F1F9FD">&nbsp;
				 <FCK:editor instanceName="fcknr1" 	basePath="/plugs/fckeditor/" width="100%"  height="600">
		          		<jsp:attribute name="value">${fckContent1}</jsp:attribute>
		          </FCK:editor>
				</td>
			</tr> -->
          </table>
          
          <br/>
          <input name="button" type="button" class="button" value="保存数据" onClick="setLed()"/>
          <br/>
         
	<br/>
    </td>
    <td width="8" background="/queue/images/t-5.jpg">&nbsp;</td>
  </tr>
  <tr>
    <td ><img src="/queue/images/t-6.jpg" width="7" height="11"></td>
    <td height="11" background="/queue/images/t-7.jpg"></td>
    <td ><img src="/queue/images/t-8.jpg" width="8" height="11"></td>
  </tr>
</table>
</form>

</body>
</html>