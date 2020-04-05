package com.suntendy.queue.util.scriptsession.event;

import org.springframework.context.ApplicationEvent;

public class PauseOrRecoverEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	public PauseOrRecoverEvent(Object source) {
		super(source);
	}
}