package gui;

import modbus.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class Main implements ActionListener {
	public static Master master;
    public static DataStruct alarm;
    public static Integer dataRead;
    public static JLabel alarmLabel;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
           public void run() {
               (new Main()).createAndShowGUI();
           }
        });
    }

    public Main() {
    	Main.dataRead = null;
    	
        try {
			Main.master = new Master(1000, 9600, "COM1");
			Main.master.openPort();
			Main.master.start();
			Main.alarm = new DataStruct(0, 8, 0, "Alarme da casa");
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
    
    public static void triggerAlarm() {
    	if (Main.alarmLabel.getText().equals("ALARME: LIGADO")) {
    		Main.alarmLabel.setText("ALARME: DISPARADO");
    	}
    }
    
    public void actionPerformed(ActionEvent e) {
    	if (e.getActionCommand().equals("Ligar") && !Main.alarmLabel.getText().contains("DISPARADO")) {
        	Main.master.write(Main.alarm.reg, Main.alarm.newBit(1));
        	Main.alarmLabel.setText("ALARME: LIGADO");
    	}
    	else if (e.getActionCommand().equals("Desligar")) {
    		Main.master.write(Main.alarm.reg, Main.alarm.newBit(0));
    		Main.alarmLabel.setText("ALARME: DESLIGADO");
    	}
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Casa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new CustomJPanel("src/assets/planta-baixa.jpg"));
        
        JPanel alarmPanel = (JPanel) frame.getContentPane();
        alarmPanel.setLayout(null);
        
        alarmLabel = new JLabel("ALARME: DESLIGADO");
        alarmLabel.setBackground(Color.white);
        alarmLabel.setOpaque(true);
        alarmPanel.add(alarmLabel);
        
        JButton alarmOn = new JButton("Ligar");
        alarmOn.setActionCommand("Ligar");
        alarmOn.addActionListener(this);
        alarmPanel.add(alarmOn);
        
        JButton alarmOff = new JButton("Desligar");
        alarmOff.setActionCommand("Desligar");
        alarmOff.addActionListener(this);
        alarmPanel.add(alarmOff);
        
        Insets insets = alarmPanel.getInsets();
        
        Dimension size = alarmOn.getPreferredSize();
        alarmOn.setBounds(20 + insets.left, 45 + insets.top, size.width, size.height);
        
        size = alarmOff.getPreferredSize();
        alarmOff.setBounds(90 + insets.left, 45 + insets.top, size.width, size.height);
        
        size = alarmLabel.getPreferredSize();
        alarmLabel.setBounds(35 + insets.left, 25 + insets.top, size.width + 20, size.height);
        
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            	Main.master.closePort();
            }
        });

        //Display the window.
//        frame.pack();
	    frame.setResizable(false);
	    frame.setMinimumSize(new Dimension(1424, 700));
	    frame.setLocationRelativeTo(null);
	    frame.setSize(1424, 700);
        frame.setVisible(true);
    }
}
