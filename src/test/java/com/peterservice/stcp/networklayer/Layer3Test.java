package com.peterservice.stcp.networklayer;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.peterservice.stcp.transportlayer.RDTReceiver;

public class Layer3Test {

	Layer3 layer3;
	Packet simplePacket;
	RDTReceiver rdtReceiver;
	long delay = 100;
	double corruptProb = 0;


	@Before
	public void setUp() {
		layer3 = new Layer3(delay, corruptProb);
		simplePacket = new Packet(0, 0, 0, "fromSender");
		//Receiver is an observer
		rdtReceiver = new RDTReceiver(); 
		layer3.register(rdtReceiver);

	}

	@Test
	public void layerAccepts() {
		layer3.send(simplePacket);
	}

	@Test
	public void layerAcceptsMultipleTimes() {
		for (int i = 0; i < 10; i++) {
			layer3.send(simplePacket);
		}
	}
	
	@Test
	public void layerRegistersReceiver() {
		
		assertEquals(layer3.getObserver(), rdtReceiver);		
	}
	
	@Test
	public void receiverGetsPacket() {

		layer3.send(simplePacket);		
		assertEquals(simplePacket, rdtReceiver.getCurrentPacket());
	}
	
	@Test
	public void packetIsDelayed() {
		long started = System.currentTimeMillis();
		layer3.send(simplePacket);		
		long finished = System.currentTimeMillis();
		long actDelay = finished - started;
		
		assertTrue(actDelay - delay >= 0);
		
	}
	
	@Test
	public void packetDataIsCorrupted() {
		corruptProb = 1;
		layer3.setCorruptProb(corruptProb);
		String sentPacket = simplePacket.toString();
		layer3.send(simplePacket);		
		corruptProb = 0;

		String gotPacket = rdtReceiver.getCurrentPacket().toString();
		
		assertTrue(!gotPacket.equals(sentPacket));
		
	}
}
