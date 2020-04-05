package com.suntendy.queue.util.scriptsession.event;

import org.springframework.context.ApplicationEvent;

public class SetWindowRelaodEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	
	public SetWindowRelaodEvent(Object source) {
		super(source);
	}

}
