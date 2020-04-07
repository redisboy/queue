package com.suntendy.queue.util.scriptsession.event;

import org.springframework.context.ApplicationEvent;

public class ReadDataEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	public ReadDataEvent(Object source) {
		super(source);
	}
}