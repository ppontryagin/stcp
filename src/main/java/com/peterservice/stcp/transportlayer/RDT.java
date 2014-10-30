package com.peterservice.stcp.transportlayer;

import com.peterservice.stcp.networklayer.Packet;

public class RDT {

	public void receive(Packet packet) {
		System.out.println(packet.getPayload());
	}
}
