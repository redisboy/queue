package com.suntendy.core.util.lang;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ListUtil {

	/** Hastset根据hashcode判断是否重复，数据不会重复**/
	public static void removeDuplicate(List arlList) {
		HashSet h = new HashSet(arlList);
		arlList.clear();
		arlList.addAll(h);
	}

	/** 通过Hashset的add方法判断是否已经添加过相同的数据，如果已存在相同的数据则不添加 **/
	public static void removeDuplicateWithOrder(List arlList) {
		Set set = new HashSet();
		List newList = new ArrayList();
		for (Iterator iter = arlList.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		arlList.clear();
		arlList.addAll(newList);
	}

}
