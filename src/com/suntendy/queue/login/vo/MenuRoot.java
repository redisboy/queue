package com.suntendy.queue.login.vo;

import java.util.List;

@SuppressWarnings("unchecked")
public class MenuRoot {
	
	private String menuid;
	private String menutext;
	private String parentmenuid;
	private String menuindex;
	private String menutype;
	private String menuimage;
	private List menulevellist;
    private String levelidstr;
    
	public String getLevelidstr() {
		return levelidstr;
	}
	public void setLevelidstr(String levelidstr) {
		this.levelidstr = levelidstr;
	}
	public List getMenulevellist() {
		return menulevellist;
	}
	public void setMenulevellist(List menulevellist) {
		this.menulevellist = menulevellist;
	}
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getMenuimage() {
		return menuimage;
	}
	public void setMenuimage(String menuimage) {
		this.menuimage = menuimage;
	}
	public String getMenuindex() {
		return menuindex;
	}
	public void setMenuindex(String menuindex) {
		this.menuindex = menuindex;
	}
	public String getMenutext() {
		return menutext;
	}
	public void setMenutext(String menutext) {
		this.menutext = menutext;
	}
	public String getMenutype() {
		return menutype;
	}
	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}
	public String getParentmenuid() {
		return parentmenuid;
	}
	public void setParentmenuid(String parentmenuid) {
		this.parentmenuid = parentmenuid;
	}
	

}
