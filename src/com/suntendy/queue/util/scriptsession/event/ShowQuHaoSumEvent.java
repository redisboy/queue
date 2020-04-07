package com.suntendy.queue.util.scriptsession.event;

import org.springframework.context.ApplicationEvent;

public class ShowQuHaoSumEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	
	public ShowQuHaoSumEvent(Object source) {
		super(source);
	}
}