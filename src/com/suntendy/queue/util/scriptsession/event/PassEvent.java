package com.suntendy.queue.util.scriptsession.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author:liuwj
 * @date:2014-4-18  下午07:27:18
 * @version:1.0
 */
public class PassEvent extends ApplicationEvent{
	private static final long serialVersionUID = 1L;

	public PassEvent(Object source) {
		super(source);
	}

}
