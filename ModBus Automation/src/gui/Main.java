package gui;
import modbus.*;
import java.io.*;
import javax.swing.*;
import javax.swing.JOptionPane;

public class Main{

    public static Master master;
    public static Integer dataRead;

    public static void main(String[] args){
        //?? Funciona, só aceite :P

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
           public void run() {
               (new Main()).createAndShowGUI();
           }
        });

        //Quando fecha?
        //master.closePort();
    }

    public Main(){
    	Main.dataRead = null;
    	
        try {
			Main.master = new Master(1000, 9600, "COM1");
//			Main.master.closePort();
			Main.master.openPort();
			Main.master.start();
		}
		catch (IOException ioe) {
			Main.master.closePort();
			ioe.printStackTrace();
			System.exit(-1);
		}
        catch (Exception e) {
        	Main.master.closePort();
        	e.printStackTrace();
			System.exit(-1);
        }
    }

    private void createAndShowGUI(){

        JFrame frame = new JFrame("Casa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new CustomJPanel("src/assets/planta-baixa.jpg"));
        
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame, 
                    "Are you sure to close this window?", "Really Closing?", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                	Main.master.closePort();
                    System.exit(0);
                }
            }
        });

        //Display the window.
        frame.pack();
	    frame.setSize(1424, 700);
        frame.setVisible(true);
    }
}
