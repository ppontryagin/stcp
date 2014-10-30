package com.peterservice.stcp.networklayer;

public interface Subject {

	public void unregister(Observer o);
	public void register(Observer o);
	public void notifyObserver();
}
