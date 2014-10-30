package com.peterservice.stcp.networklayer;

import java.util.Random;

public class Layer3 implements Subject {

	public static final int MAXDATASIZE = 20;
	private Observer o;
	private Packet packet;

	private long delay;
	private Random rand;

	private double corruptProb;

	public Layer3(long delay, double corruptProb) {
		this.rand = new Random();
		this.corruptProb = corruptProb;
		this.delay = delay;
	}

	public void send(Packet packet) {
		this.packet = packet;
		try {
			corrupt();
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		notifyObserver();
	}

	public void unregister(Observer o) {

	}

	public void register(Observer o) {
		this.o = o;
	}

	public void notifyObserver() {
		if (o != null) {
			o.gotPacket(packet);
		}
	}

	public Observer getObserver() {
		return o;
	}

	private void corrupt() {
		double x = rand.nextDouble();

		if (x < corruptProb) {
			System.out.println("toLayer3: packet being corrupted");
			
			x = rand.nextDouble();
			if (x < 0.75) {
				String payload = packet.getPayload();

				if (payload.length() > 0)
					payload = "?" + payload.substring(payload.length() - 1);
				else
					payload = "?";

				packet.setPayload(payload);
			} else if (x < 0.875)
				packet.setSeqnum(999999);
			else
				packet.setAcknum(999999);
		}
	}

	public void setCorruptProb(double corruptProb) {
		this.corruptProb = corruptProb;
	}
}
