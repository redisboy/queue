package com.suntendy.queue.login.tree;

import java.util.List;

import com.suntendy.queue.login.vo.MenuLevel;
import com.suntendy.queue.login.vo.MenuRoot;


/**
 * 树加载
 */
@SuppressWarnings("unchecked")
public class TreeUtil {
	
	/**
	 * 根据结点，叶子加载
	 * @param orders,list
	 * @return string
	 */
	public static String createTreeInfo1(List menurootlist, List menulist,String username) {
		StringBuffer contents = new StringBuffer();
		contents.append("dtree = new dTree('dtree');\n");
		contents.append("dtree.add(");
		contents.append(0);
		contents.append(",");
		contents.append(-1);
		contents.append(",'");
		contents.append("叫号管理系统");
		contents.append("','");
		contents.append("');\n");
		  
		MenuRoot menuroot = null;
		for (int j = 0; j < menurootlist.size(); j++) {
			menuroot = (MenuRoot) menurootlist.get(j);
			contents.append("dtree.add(");
			contents.append(menuroot.getMenuid());
			contents.append(",");
			contents.append(menuroot.getParentmenuid());
			contents.append(",'");
			contents.append(menuroot.getMenutext());
			contents.append("','");
			contents.append("');\n");
		}
	
		MenuLevel menu = null;
		for (int max = menulist.size(), i = 0; i < max; i++) {
			menu = (MenuLevel) menulist.get(i);
			if("27".equals(menu.getLevelid()) ){
				if("suntendy".equals(username)){
				contents.append("dtree.add(");
				contents.append(menu.getLevelid());
				contents.append(",");
				contents.append(menu.getMenuid());
				contents.append(",'");
				contents.append(menu.getLeveltext());
				contents.append("','");
				contents.append(menu.getTargeturl());
				contents.append("','','main');\n");
				}
			}else{
				contents.append("dtree.add(");
				contents.append(menu.getLevelid());
				contents.append(",");
				contents.append(menu.getMenuid());
				contents.append(",'");
				contents.append(menu.getLeveltext());
				contents.append("','");
				contents.append(menu.getTargeturl());
				contents.append("','','main');\n");
			}
		}
		contents.append("document.write(dtree);");
		//System.out.print("menulist== "+contents);
		return contents.toString();
		
	}
}