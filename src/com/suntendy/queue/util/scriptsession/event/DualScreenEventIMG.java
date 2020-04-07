package com.suntendy.queue.util.scriptsession.event;

import org.springframework.context.ApplicationEvent;

public class DualScreenEventIMG extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	
	public DualScreenEventIMG(Object source) {
		super(source);
	}

}
