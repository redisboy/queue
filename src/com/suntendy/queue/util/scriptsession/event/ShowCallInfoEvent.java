package com.suntendy.queue.util.scriptsession.event;

import org.springframework.context.ApplicationEvent;

public class ShowCallInfoEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	
	public ShowCallInfoEvent(Object source) {
		super(source);
	}
}