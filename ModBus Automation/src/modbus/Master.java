package modbus;

import java.io.*;
import java.util.TooManyListenersException;

import gnu.io.*;

public class Master {
	private int timeout;
	public int baudrate;
	private Thread thread;
	private SerialPort port;
	private String portName;
	private InputStream inputPort;
	private OutputStream outputPort;
	private CommPortIdentifier portId;
	
	public Master(int timeout, int baudrate, String portName) throws IOException {
		this.timeout = timeout;
		this.baudrate = baudrate;
		this.portName = portName;
	}
	
	public void openPort() {
		try {
			this.portId = CommPortIdentifier.getPortIdentifier(this.portName);
			this.port = (SerialPort)portId.open(this.portName, this.timeout);
			this.port.setSerialPortParams(this.baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		}
		catch (NoSuchPortException nspe){
			System.err.println("This port doesn't exist: " + portName);
			System.exit(-1);
		}
		catch (PortInUseException piu){
			System.err.println("Port already open.");
			System.exit(-1);
		}
		catch (UnsupportedCommOperationException uscoe){
			System.err.println("Wrong parameter configuration!");
			System.exit(-1);
		}
	}
	
	public void closePort() {
		if (this.port != null) {
	        try {
	            this.outputPort.close();
	            this.inputPort.close();
	        }
	        catch (IOException ex) {
	            // don't care?
	        }
	        
	        this.port.close();
	    }
	}
	
	public void start() {
		try {
			this.inputPort = this.port.getInputStream();
			this.port.addEventListener(new SerialReader(this.inputPort));
			this.port.notifyOnDataAvailable(true);
			
			this.outputPort = this.port.getOutputStream();
			this.thread = new Thread(new SerialWriter(this.outputPort));
			this.thread.start();
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
