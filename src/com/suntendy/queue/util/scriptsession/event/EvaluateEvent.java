package com.suntendy.queue.util.scriptsession.event;

import org.springframework.context.ApplicationEvent;

public class EvaluateEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	public EvaluateEvent(Object source) {
		super(source);
	}
}