package com.suntendy.queue.util.scriptsession.event;

import org.springframework.context.ApplicationEvent;

public class ShowLzxxDataEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	public ShowLzxxDataEvent(Object source) {
		super(source);
	}
}