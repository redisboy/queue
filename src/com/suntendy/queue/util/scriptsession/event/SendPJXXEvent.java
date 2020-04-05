package com.suntendy.queue.util.scriptsession.event;

import org.springframework.context.ApplicationEvent;

public class SendPJXXEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	
	public SendPJXXEvent(Object source) {
		super(source);
	}

}
