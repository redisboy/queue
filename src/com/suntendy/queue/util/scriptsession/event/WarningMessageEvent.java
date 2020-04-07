package com.suntendy.queue.util.scriptsession.event;

import org.springframework.context.ApplicationEvent;

public class WarningMessageEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	
	public WarningMessageEvent(Object source) {
		super(source);
	}
}