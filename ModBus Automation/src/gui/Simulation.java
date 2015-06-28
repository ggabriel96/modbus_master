package gui;

import java.awt.Component;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Simulation extends Thread {
	private int bathWater;
	private InfoWindow window;
	private Random random = new Random();
	public static long millis = System.nanoTime() / 1_000_000;
	
	public Simulation(InfoWindow window) {
		this.bathWater = 0;
		this.window = window;
	}
	
	public void setBathWater(int bathWater) {
		this.bathWater = bathWater;
	}
	
	public void setInfoWindow(InfoWindow window) {
		this.window = window;
	}
	
	@Override
	public void run() {
		DataStruct d1 = null, d2 = null;
		
		while (true) {
			if (System.nanoTime() / 1_000_000 - Simulation.millis > 2_000) {
				Simulation.millis = System.nanoTime() / 1_000_000;
				
				for (int i = 0; i < this.window.room.getDataSize(); i++) {
	                if (this.window.room.getName(i).contains("Nivel")) {
	                	d1 = this.window.room.getInfo("Agua quente");
	                	d2 = this.window.room.getInfo("Agua fria");
	                	
	                	if ((d1 != null && d1.info == 1) || (d2 != null && d2.info == 1)) {
		                	if (d1 != null && d1.info == 1) {
			                	this.bathWater++;
		                	}
		                	
		                	if (d2 != null && d2.info == 1) {
		                		this.bathWater++;
		                	}
		                	
		                	Main.master.write(7, this.bathWater % 65536);
	                	}
	                }
	                
	                if ((d1 = this.window.room.getInfo("Sensor de Alarme")) != null) {
	                	d1.info = this.random.nextInt(2);
	                	Main.master.write(d1.reg, d1.newBit(d1.info));
	                }
	                if ((d2 = this.window.room.getInfo("Temperatura")) != null) {
	                	d2.info = 20 + this.random.nextInt(11);
	                	Main.master.write(d2.reg, d2.info);
	                }
				}
				
				for (Component c1: this.window.panel.getComponents()) {
		    		if (c1 instanceof JPanel) {
			    		for (Component c2: ((JPanel)c1).getComponents()) {
			    			if (c2 instanceof JLabel) {
			    				String label = ((JLabel)c2).getText();
			    				if (label.contains("Nivel")) {
			    					((JLabel)c2).setText(label.substring(0, label.indexOf(":") + 2) + this.bathWater + "L");
			    				}
			    				else if (label.contains("Alarme")) {
			    					((JLabel)c2).setText(label.substring(0, label.indexOf(":") + 2) + (d1.info == 0 ? "Sem movimento" : "Com movimento"));
			    					if (d1.info == 1) {
				                		Main.triggerAlarm();
				                	}
			    				}
			    				else if (label.contains("Temperatura")) {
			    					((JLabel)c2).setText(label.substring(0, label.indexOf(":") + 2) + d2.info);
			    				}
			    			}
			    		}
		    		}
		    	}
			}
		}
	}
}
