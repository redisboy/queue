package com.suntendy.queue.util.scriptsession.event;

import org.springframework.context.ApplicationEvent;

public class GuaQiTuiSong extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	
	public GuaQiTuiSong(Object source) {
		super(source);
	}
}