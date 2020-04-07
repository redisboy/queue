package com.suntendy.queue.util.scriptsession.event;

import org.springframework.context.ApplicationEvent;

public class ModifyWindowCountEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	public ModifyWindowCountEvent(Object source) {
		super(source);
	}
}