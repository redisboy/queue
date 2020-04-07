package com.suntendy.queue.util.scriptsession.event;

import org.springframework.context.ApplicationEvent;

public class ChuangKouPing extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	
	public ChuangKouPing(Object source) {
		super(source);
	}

}
