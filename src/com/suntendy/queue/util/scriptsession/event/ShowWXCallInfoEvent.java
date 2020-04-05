package com.suntendy.queue.util.scriptsession.event;

import org.springframework.context.ApplicationEvent;

public class ShowWXCallInfoEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	
	public ShowWXCallInfoEvent(Object source) {
		super(source);
	}
}