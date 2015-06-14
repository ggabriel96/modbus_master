package modbus;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			Master master = new Master(1000, 9600, "COM1");
			master.closePort();
			master.openPort();
			master.start();
			master.closePort();
		}
		catch (IOException ioe) {
			
		}
	}
}
