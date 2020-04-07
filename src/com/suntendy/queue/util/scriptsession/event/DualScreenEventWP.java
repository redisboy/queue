package com.suntendy.queue.util.scriptsession.event;

import org.springframework.context.ApplicationEvent;

public class DualScreenEventWP extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	
	public DualScreenEventWP(Object source) {
		super(source);
	}

}
