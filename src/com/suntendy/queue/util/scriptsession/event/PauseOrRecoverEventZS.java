package com.suntendy.queue.util.scriptsession.event;

import org.jcp.xml.dsig.internal.dom.ApacheCanonicalizer;
import org.springframework.context.ApplicationEvent;

public class PauseOrRecoverEventZS extends ApplicationEvent{
private static final long serialVersionUID = 1L;
	
	public PauseOrRecoverEventZS(Object source) {
		super(source);
	}
}
