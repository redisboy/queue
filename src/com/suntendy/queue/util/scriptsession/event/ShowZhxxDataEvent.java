package com.suntendy.queue.util.scriptsession.event;

import org.springframework.context.ApplicationEvent;

public class ShowZhxxDataEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	public ShowZhxxDataEvent(Object source) {
		super(source);
	}
}