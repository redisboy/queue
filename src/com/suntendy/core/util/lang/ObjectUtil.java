package com.suntendy.core.util.lang;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by IntelliJ IDEA.
 * User:chenYuan (mail:cayurain@21cn.com)
 * Date: 2007-4-13
 * Time: 8:50:52
 */
public class ObjectUtil {
    private static final Log log = LogFactory.getLog(ObjectUtil.class);

    private ObjectUtil() {

    }

	public static boolean isNullOrEmptyString(Object o) {
		if (o == null)
			return true;
		if (o instanceof String) {
			String str = (String) o;
			if (str.length() == 0)
				return true;
		}
		if(o instanceof List) {
			List _list = (List) o;
			if (_list.size() == 0)
				return true;
		}
		return false;
	}
	
	
}
