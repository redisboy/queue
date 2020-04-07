// JavaScript Document
var AUTO_HIDE_MENU=0;
var arrow="";
var arrowpic1="images/arrow_l.gif";
var arrowpic2="images/arrow_r.gif"

//--------------------- 状态初始 ----------------------
var MENU_SWITCH;
function panel_menu_open()
{
 MENU_SWITCH=AUTO_HIDE_MENU;
 panel_menu_ctrl();
}


//------------------ 面板状态切换 ---------------------
function panel_menu_ctrl()
{
   if(MENU_SWITCH==0)
   {
      parent.frame2.cols="178,12,*";
      MENU_SWITCH=1;
	  arrow.src=arrowpic1;
     }
   else
   {
      parent.frame2.cols="0,12,*";
      MENU_SWITCH=0;
	  arrow.src=arrowpic2;
   }
}

//------------------ 面板状态切换 ---------------------
function enter_menu_ctrl()
{
   if(AUTO_HIDE_MENU==1)    // 判断面板是否允许自动隐藏
   {
     if(MENU_SWITCH==0)
     {
        parent.frame2.cols="0,12,*";
        MENU_SWITCH=1;
        arrow.src=arrowpic1;
     }
     else
     {
        parent.frame2.cols="178,12,*";
        MENU_SWITCH=0;
        arrow.src=arrowpic2;
     }
   }
}