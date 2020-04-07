package com.suntendy.queue.util.scriptsession.event;

import org.springframework.context.ApplicationEvent;

public class SendStatusCY extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	
	public SendStatusCY(Object source) {
		super(source);
	}
}