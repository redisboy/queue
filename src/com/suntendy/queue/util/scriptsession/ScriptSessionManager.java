package com.suntendy.queue.util.scriptsession;

import java.util.HashMap;
import java.util.Map;

import org.directwebremoting.ScriptSession;

public class ScriptSessionManager {

	private Map<String, ScriptSession> sessions = new HashMap<String, ScriptSession>();
	private static ScriptSessionManager cm = new ScriptSessionManager();
	private ScriptSessionManager() {}
	
	public static ScriptSessionManager getInstance() {
		return cm;
	}
	
	public void setValue(String key, ScriptSession value) {
		if (null != sessions.get(key)) {
			sessions.remove(key);
		}
		sessions.put(key, value);
	}
	
	public ScriptSession getValue(String key) {
		return sessions.get(key);
	}
}