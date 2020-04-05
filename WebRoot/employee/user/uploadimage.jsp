<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  String picUrl = (String)request.getAttribute("Picurl");
  String  step = (String)request.getAttribute("step"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
<title></title>
<link href="css/main.css" type="text/css" rel="Stylesheet" />
	<link rel="stylesheet" href="/queue/plugs/photo/css/main.css" type="text/css"></link>
    <script type="text/javascript" src="/queue/plugs/photo/js/jquery1.2.6.pack.js"></script>
    <script type="text/javascript" src="/queue/plugs/photo/js/ui.core.packed.js"></script>
    <script type="text/javascript" src="/queue/plugs/photo/js/ui.draggable.packed.js" ></script>
    <script type="text/javascript" src="/queue/plugs/photo/js/CutPic.js"></script>
    <script type="text/javascript">
        function Step1() {
            $("#Step2Container").hide();           
        }
        function Step2() {
            $("#CurruntPicContainer").hide();
        }
       function subUpLoadPic(){
       		var url=$('#file1').val();
       		var empliyeeid=$('#empliyeeid').val();
       		if(null == url || "" == url){
       			alert("请选择图片");
       		}
			var pos = url.lastIndexOf("/");
			if(pos == -1){pos = url.lastIndexOf("\\");}
			var filename = url.substr(pos +1);
			form1.action="../employee!uploadImg.action?filename="+encodeURIComponent(filename)+"&id="+empliyeeid;
			form1.submit();
       }
       function subCutPic(){
       		var empliyeeid=$('#empliyeeid').val();
       		var filename=encodeURIComponent("<%=picUrl %>");
			form2.action="employee!saveCutPic.action?filename="+encodeURIComponent(filename)+"&id="+empliyeeid;
			form2.submit();
			javascript:opener.location.href=opener.location.href;opener=null;window.close();
			window.close();
       }
    </script>
</head>
<body>
   

    <div>
     <div class="left">
         <!--Step 2-->
         <div id="Step2Container">
           <div class="title"><b> 裁切头像照片</b></div>
           <div class="uploadtooltip">您可以拖动照片以裁剪满意的头像</div>                              
                   <div id="Canvas" class="uploaddiv">
                   
                            <div id="ImageDragContainer">                               
                               <img id="ImageDrag" class="imagePhoto" src="../upload/<%=picUrl %>" style="border-width:0px;" />                                                        
                            </div>
                            <div id="IconContainer">                               
                               <img id="ImageIcon" class="imagePhoto" src="../upload/<%=picUrl%>" style="border-width:0px;" />                                                        
                            </div>            
                    </div>
                    <div class="uploaddiv">
                       <table>
                            <tr>
                                <td id="Min">
                                        <img alt="缩小" src="/queue/employee/user/image/_c.gif" onmouseover="this.src='/queue/employee/user/image/_c.gif';" onmouseout="this.src='/queue/employee/user/image/_h.gif';" id="moresmall" class="smallbig" />
                                </td>
                                <td>
                                    <div id="bar">
                                        <div class="child">
                                        </div>
                                    </div>
                                </td>
                                <td id="Max">
                                        <img alt="放大" src="/queue/employee/user/image/c.gif" onmouseover="this.src='/queue/employee/user/image/c.gif';" onmouseout="this.src='/queue/employee/user/image/h.gif';" id="morebig" class="smallbig" />
                                </td>
                            </tr>
                        </table>
                    </div>
                    <form name="form2" action="" method="post">
                    <input type="hidden" name="picture" value="<%=picUrl%>"/>
	                    <div class="uploaddiv">
	                        <input type="button" name="btn_Image" value="保存头像" id="btn_Image" onclick="subCutPic()"/>
	                    </div>           
	                    <div>
		                    <input name="empliyeeid" type="hidden" value="<%=(String)request.getParameter("id") %>" id="empliyeeid" /><br />
		                    <input name="txt_width" type="text" value="1" id="txt_width" /><br />
		                    <input name="txt_height" type="text" value="1" id="txt_height" /><br />
		                    <input name="txt_top" type="text" value="82" id="txt_top" /><br />
		                    <input name="txt_left" type="text" value="73" id="txt_left" /><br />
		                    <input name="txt_DropWidth" type="text" value="120" id="txt_DropWidth" /><br />
		                    <input name="txt_DropHeight" type="text" value="120" id="txt_DropHeight" /><br />
		                    <input name="txt_Zoom" type="text" id="txt_Zoom" />
		                </div>  
	            	</form>
         </div>
     
     </div>
      <form name="form1" method="post" action="../employee!uploadImg.action" id="form1" enctype="multipart/form-data">
     <div class="right">
         <!--Step 1-->
         <div id="Step1Container">
            <div class="title"><b>更换照片</b></div>
            <div id="uploadcontainer">
                <div class="uploadtooltip">请选择新的照片文件，文件需小于2.5MB</div>
                <div class="uploaddiv"><input type="file" name="file1" id="file1" title="选择照片" /></div>
                <div class="uploaddiv"><input type="button" name="btnUpload" value="上 传" id="btnUpload" onclick="subUpLoadPic()"/></div>
            </div>
         </div>
     </div>
     </form>
    </div>
    <% 
      if(null==picUrl||"".equals(picUrl))
      {%>
          <script type='text/javascript'>Step1();</script>
      <%}else if(!"".equals(picUrl)&& "2".equals(step)){
      %>
      <script type='text/javascript'>Step2();</script>
      <%}else if(!"".equals(picUrl)&& "3".equals(step)){
      %>
      <script type='text/javascript'>Step3();</script>
      <%}%>

</body>
</html>