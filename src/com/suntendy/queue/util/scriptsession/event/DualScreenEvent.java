package com.suntendy.queue.util.scriptsession.event;

import org.springframework.context.ApplicationEvent;

public class DualScreenEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	
	public DualScreenEvent(Object source) {
		super(source);
	}

}
