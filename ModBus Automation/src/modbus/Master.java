package modbus;

import java.io.*;
import java.util.TooManyListenersException;

import gnu.io.*;

public class Master {
	private long time;
	private int timeout;
	public int baudrate;
	private Thread thread;
	private SerialPort port;
	private String portName;
	private SerialReader sr;
	private SerialWriter swr;
	private InputStream inputPort;
	private OutputStream outputPort;
	private CommPortIdentifier portId;
	private static final int SLAVE_ADDR = 0x3A;

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

	private int[] checksum(int[] message) {
		int sum = 0;
		int[] result = new int[2];

		for (int i = 1; i < 7; i++) {
			sum += message[i];
		}

		sum %= 256;
		sum = 0xFF - sum + 1;

		result[0] = sum / 16;
		result[1] = sum % 16;

		return result;
	}

	public void write(int reg, int data) {
		String s;
		int[] checksum;
		String hexReg[] = Integer.toHexString(reg).split("");
		String hexData[] = Integer.toHexString(data).split("");

		int message[] = new int[11];
		message[0] = Master.SLAVE_ADDR; // :
		message[1] = Master.SLAVE_ADDR;
		message[2] = 0x06; // fn
		if (hexReg.length > 1) {
			message[3] = Integer.parseInt(hexReg[0]);
			message[4] = Integer.parseInt(hexReg[1]);
		}
		else {
			message[3] = 0;
			message[4] = Integer.parseInt(hexReg[0]);
		}
		if (hexData.length > 1) {
			message[5] = Integer.parseInt(hexData[0]);
			message[6] = Integer.parseInt(hexData[1]);
		}
		else {
			message[5] = 0;
			message[6] = Integer.parseInt(hexData[0]);
		}
		checksum = this.checksum(message);
		message[7] = checksum[0];
		message[8] = checksum[1];
		message[9] = '\r';
		message[10] = '\n';

		s = (char)message[0] + "";
		s += "3A";
		s += "06";
		s += (message[3] < 16 ? ("0" + Integer.toHexString(message[3])) : Integer.toHexString(message[3]));
		s += (message[4] < 16 ? ("0" + Integer.toHexString(message[4])) : Integer.toHexString(message[4]));
		s += (message[5] < 16 ? ("0" + Integer.toHexString(message[5])) : Integer.toHexString(message[5]));
		s += (message[6] < 16 ? ("0" + Integer.toHexString(message[6])) : Integer.toHexString(message[6]));
		s += (message[7] >= 10 ? ((char) (Integer.toHexString(message[7]).charAt(0) - 'a' + 'A')) : ((char)(message[7] + '0')));
		s += (message[8] >= 10 ? ((char) (Integer.toHexString(message[8]).charAt(0) - 'a' + 'A')) : ((char)(message[8] + '0')));
		s += "\r\n";

		this.time = System.nanoTime();
		this.swr.write(s);
		while (System.nanoTime() - this.time < 1e9); // wait for 1 second
	}

	public void read(int reg, int qtty) {
		String s;
		int[] checksum;
		String hexReg[] = Integer.toHexString(reg).split("");
		String hexQtty[] = Integer.toHexString(qtty).split("");

		int message[] = new int[11];
		message[0] = Master.SLAVE_ADDR; // :
		message[1] = Master.SLAVE_ADDR;
		message[2] = 0x03; // fn
		if (hexReg.length > 1) {
			message[3] = Integer.parseInt(hexReg[0]);
			message[4] = Integer.parseInt(hexReg[1]);
		}
		else {
			message[3] = 0;
			message[4] = Integer.parseInt(hexReg[0]);
		}
		if (hexQtty.length > 1) {
			message[5] = Integer.parseInt(hexQtty[0]);
			message[6] = Integer.parseInt(hexQtty[1]);
		}
		else {
			message[5] = 0;
			message[6] = Integer.parseInt(hexQtty[0]);
		}
		checksum = this.checksum(message);
		message[7] = checksum[0];
		message[8] = checksum[1];
		message[9] = '\r';
		message[10] = '\n';

		s = (char)message[0] + "";
		s += "3A";
		s += "03";
		s += (message[3] < 16 ? ("0" + Integer.toHexString(message[3])) : Integer.toHexString(message[3]));
		s += (message[4] < 16 ? ("0" + Integer.toHexString(message[4])) : Integer.toHexString(message[4]));
		s += (message[5] < 16 ? ("0" + Integer.toHexString(message[5])) : Integer.toHexString(message[5]));
		s += (message[6] < 16 ? ("0" + Integer.toHexString(message[6])) : Integer.toHexString(message[6]));
		s += (message[7] >= 10 ? ((char) (Integer.toHexString(message[7]).charAt(0) - 'a' + 'A')) : ((char)(message[7] + '0')));
		s += (message[8] >= 10 ? ((char) (Integer.toHexString(message[8]).charAt(0) - 'a' + 'A')) : ((char)(message[8] + '0')));
		s += "\r\n";

		this.time = System.nanoTime();
		this.swr.write(s);
		while (System.nanoTime() - this.time < 1e9); // wait for 1 second
	}

	public void start() {
		try {
			this.inputPort = this.port.getInputStream();
			this.sr = new SerialReader(this.inputPort);
			this.port.addEventListener(this.sr);
			this.port.notifyOnDataAvailable(true);

			this.outputPort = this.port.getOutputStream();
			this.swr = new SerialWriter(this.outputPort);

			this.thread = new Thread(swr);
			this.thread.start();

			this.write(1, 3);
			this.write(2, 4);
			this.write(3, 5);
			this.read(1, 3);
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
