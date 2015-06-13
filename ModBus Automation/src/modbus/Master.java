package modbus;

import java.io.*;
import java.util.TooManyListenersException;

import gnu.io.*;

public class Master {
	Thread thread;
	private int timeout;
	public int baudrate;
	private SerialPort port;
	private String portName;
	private CommPortIdentifier portId;
	
	public Master(int timeout, int baudrate, String portName) {
		this.timeout = timeout;
		this.baudrate = baudrate;
		this.portName = portName;
	}
	
	public void openComPort() {
		try {
			this.portId = CommPortIdentifier.getPortIdentifier(this.portName);
			this.port = (SerialPort)portId.open(this.portName, this.timeout);
			this.port.setSerialPortParams(this.baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		}
		catch (NoSuchPortException nspe){
			System.err.println("This port doesn't exist: " + portName);
		}
		catch (PortInUseException piu){
			System.err.println("Port already open.");
		}
		catch (UnsupportedCommOperationException uscoe){
			System.err.println("Wrong parameter configuration!");
		}
	}
	
	public void startReadProcess() {
		try {
			InputStream inputPort = this.port.getInputStream();
			this.port.addEventListener(new SerialReader(inputPort));
			this.port.notifyOnDataAvailable(true);
			
			OutputStream outputPort = this.port.getOutputStream();
			this.thread = new Thread(new SerialWriter(outputPort));
			this.thread.start();
		}
		catch (IOException ioe) {
			System.err.println("Communication error");
			System.exit(-1);
		}
		catch (TooManyListenersException tmle) {
			System.err.println("Too many listener methods on port");
			System.exit(-1);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
}
