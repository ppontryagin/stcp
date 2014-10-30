package com.peterservice.stcp.networklayer;

public interface Observer {
	
	public void gotPacket(Packet packet);
}
