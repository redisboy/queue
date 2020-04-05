<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="ec" uri="http://www.ecside.org"  %>
<%@ page isELIgnored="false"%>
<html>
  <head>
    <title>详细评价记录</title>
  </head>
  <meta http-equiv="Content-Type" content="text/html; charset=gbk"/>
<link href="/queue/css/mainframe.css" rel="stylesheet" type="text/css" />

<body>

 
<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
  <tr>
    <td width="7" height="51"><img src="/queue/images/t-1.jpg" width="7" height="51"></td>
    <td height="51" valign="middle"  background="/queue/images/t-2.jpg">
    	<span style="font-family:'黑体', '宋体';font-size:18px; color:#fff;padding-left:15px;letter-spacing: 10px;letter-spacing: 10px;">详细评价记录</span>
    </td>
    <td width="3" height="51"><img src="/queue/images/t-3.jpg" width="8" height="51"></td>
  </tr>
  <tr>
    <td width="7" background="/queue/images/t-4.jpg">&nbsp;</td>
    <td height="100"  style="text-align:center" valign="middle" bgcolor="#f7f7f7">
    
	<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" >
	<tr>
	<td> 
		<div align="center" style="text-align: left">
    <table id="DataList1" cellspacing="0" style="height:308px;width:779px;border-collapse:collapse;">
	<tr>
		<td>
            <table  style="width:100%;" border="1" frame="box" rules="all">
                <tr>
                    <td colspan="3" style="text-align: center">
                       详细评价记录</td>
                    <td class="style14" style="text-align: center">
                        &nbsp;</td>
                    <td style="text-align: center">
                        &nbsp;</td>
                </tr>
                <tr>
                    <td class="style8">
                        窗口号</td>
                    <td class="style19">
                        <span id="DataList1_Label_ckid_0">${AllDetail.barid}</span>
                    </td>
                    
                    <td class="style10">
                        排队号</td>
                    <td class="style14">
                        <span id="DataList1_Label_xh_0">${AllDetail.clientno}</span>
                    </td>
                    <td rowspan="8"  >
                        <img  width=200 height=200  id="DataList1_imgid_0" src="getPhoto.action?id=${id }" />
                    </td>
                </tr>
                <tr>
                    <td class="style8">
                        取号时间</td>
                    <td class="style19">
                        <span id="DataList1_Label_qhsj_0">${AllDetail.entertime}</span>
                    </td>
                    <td class="style10">
                        开始时间</td>
                    <td class="style14">
                        <span id="DataList1_Label_slsj_0">${AllDetail.begintime}</span>
                    </td>
                </tr>
                <tr>
                    <td class="style8">
                        结束时间</td>
                    <td class="style19">
                        <span id="DataList1_Label_bjsj_0">${AllDetail.endtime}</span>
                    </td>
                    <td class="style10">
                        经办人</td>
                    <td class="style14">
                        <span id="DataList1_Label_jbr_0">${AllDetail.name}</span>
                    </td>
                </tr>
                <tr>
                    <td class="style8">
                        经办过程</td>
                    <td class="style19">
                        <span id="DataList1_Label_jbgc_0">${AllDetail.works}</span>
                    </td>
                    <td class="style10">
                        评价时间</td>
                    <td class="style14">
                        <span id="DataList1_Label_pjsj_0">${AllDetail.valuetime}</span>
                    </td>
                </tr>
                <tr>
                    <td class="style8">
                        评价结果</td>
                    <td class="style19">
                        <span id="DataList1_Label_pjjg_0">${AllDetail.valueName}</span>
                    </td>
                    <td class="style10">
                        申请办理业务笔数</td>
                    <td class="style14">
                        <span id="DataList1_Label_bls_0">${AllDetail.businesscount}</span>
                    </td>
                </tr>
                <tr>
                    <td class="style8">
                        部门</td>
                    <td class="style19">
                        <span id="DataList1_Label_sjbls_0">${AllDetail.department} </span>
                    </td>
                    <td class="style10">
                        申请人证件号码</td>
                    <td class="style14">
                        <span id="DataList1_Label_sfzmhm_0">${AllDetail.idnumber}</span>
                    </td>
                </tr>
                <tr>
                    <td class="style8">
                        申请业务类型</td>
                    <td class="style19">
                        <span id="DataList1_Label_ywlx_0">${AllDetail.queuename}</span>
                    </td>
                    <td class="style10">
                        申请方式</td>
                    <td class="style14">
                        <span id="DataList1_Label_sqfs_0">${AllDetail.dmsm1}</span>
                    </td>
                </tr>
                <tr>
              
                </tr>
            </table>
        </td>
	</tr>
</table>
</div>
          
		<br>
</td>
	</tr>
	</table>
	
	<br>


    </td>
    <td width="8" background="/queue/images/t-5.jpg">&nbsp;</td>
  </tr>
  <tr>
    <td ><img src="/queue/images/t-6.jpg" width="7" height="11"></td>
    <td height="11" background="/queue/images/t-7.jpg"></td>
    <td ><img src="/queue/images/t-8.jpg" width="8" height="11"></td>
  </tr>
</table>

</body>

</html>
