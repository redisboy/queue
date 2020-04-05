package com.suntendy.queue.util.scriptsession.event;

import org.springframework.context.ApplicationEvent;

public class EvaluateCompleteEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	public EvaluateCompleteEvent(Object source) {
		super(source);
	}
}