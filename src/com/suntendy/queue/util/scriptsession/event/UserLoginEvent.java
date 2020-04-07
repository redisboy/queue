package com.suntendy.queue.util.scriptsession.event;

import org.springframework.context.ApplicationEvent;

public class UserLoginEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	public UserLoginEvent(Object source) {
		super(source);
	}
}