package com.suntendy.queue.util.scriptsession.event;

import org.springframework.context.ApplicationEvent;

public class DualScreenEventFail extends ApplicationEvent{
	private static final long serialVersionUID = 1L;
	public DualScreenEventFail(Object source) {
		super(source);
	}

}
