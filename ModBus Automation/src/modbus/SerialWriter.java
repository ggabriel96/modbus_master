package modbus;

//import gnu.io.SerialPortEvent;

import java.io.*;

public class SerialWriter implements Runnable {
    OutputStream out;
    
    public SerialWriter(OutputStream out) {
        this.out = out;
    }
    
    public void write(String data) {
        int i, len;
      
        for (i = 0, len = data.length(); i < len; i++) {
        	try {
        		System.out.print(Integer.toHexString((int) data.charAt(i)));
        		this.out.write((int) data.charAt(i));
        	}
        	catch (IOException ioe) {
        		ioe.printStackTrace();
        		System.exit(-1);
        	}
        }
        
        System.out.println();
    }
    
    public void run () {
        try {                
            int c = 0;
            
            while ((c = System.in.read()) > -1) {
                this.out.write(c);
            }                
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }            
    }
}
