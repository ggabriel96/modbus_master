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
    
    public void serialEvent(SerialPortEvent arg0) {
        int data, len;
      
        try {
            len = 0;
            
            while ((data = in.read()) > -1) {
                if (data == '\n') {
                    break;
                }
                
                buffer[len++] = (byte) data;
            }
            
            System.out.print(new String(buffer, 0, len));
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            System.exit(-1);
        }             
    }

}
