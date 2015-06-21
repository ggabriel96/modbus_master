package modbus;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.*;

/* Handles the input coming from the serial port. A new line character
 * is treated as the end of a block in this example. 
 * */
public class SerialReader implements SerialPortEventListener {
	private byte[] buffer;
    private InputStream in;
    
    public SerialReader(InputStream in) {
        this.in = in;
        this.buffer = new byte[1024];
    }
    
    private int getData(String input) {
    	String tmp = input.substring(7, 11);
    	System.out.println(tmp);
    	return Integer.parseInt(tmp, 16);
    }
    
    public void serialEvent(SerialPortEvent arg0) {
    	String rd;
        int data, len;
      
        try {
            len = 0;
            
            while ((data = in.read()) > -1) {
                if (data == '\n') {
                    break;
                }
                
                buffer[len++] = (byte) data;
            }
            
            rd = new String(buffer, 0, len);
            gui.Main.dataRead = this.getData(rd);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            System.exit(-1);
        }             
    }

}
