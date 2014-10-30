package com.peterservice.stcp.transportlayer;

import com.peterservice.stcp.networklayer.Observer;
import com.peterservice.stcp.networklayer.Packet;

public class RDTReceiver extends RDT implements Observer{
	
	Packet currentPacket;

	public void gotPacket(Packet packet) {
		
		currentPacket = packet;
		receive(packet);
	}

	public Packet getCurrentPacket() {
		return currentPacket;
	}
	
	

}
