package com.suntendy.queue.util.scriptsession.event;

import org.springframework.context.ApplicationEvent;

public class JspRegisterEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	public JspRegisterEvent(Object source) {
		super(source);
	}
}