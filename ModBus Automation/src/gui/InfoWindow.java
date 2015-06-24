package gui;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

class InfoWindow implements ActionListener {
    private JFrame f;
    private Room room;
    private JPanel panel;
    //private JButton button;
    //JLabel picLabel;

    public InfoWindow(Room r) {
        /*try{
            BufferedImage pic = ImageIO.read(new File("teste.png"));
            button = new JButton(new ImageIcon(pic));
        }catch(Exception e){
            return;
        }*/

    	this.room = r;
    	this.panel = new JPanel();
        this.f = new JFrame(this.room.roomName);

        this.f.add(panel); //Adiciona painel ao frame
        this.f.setSize(225, 300); //Tamanho da janela
        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //panel.setLayout(new BorderLayout());

        for (int i = 0; i < this.room.getDataSize(); i++) {
            JPanel tmp = new JPanel(); //Painel para campo[i]
            tmp.setLayout(new BorderLayout());

            if (this.room.getName(i).matches("Lampada[0-9]") || this.room.getName(i).matches("Sensor de Alarme")) {
                //if r.getInfo(i) is equal to 0, the light is off, else the ligth is on
                tmp.add(new JLabel(this.room.getName(i) + ": " + (this.room.getInfo(i) == 0 ? "Desligado" : "Ligado")), BorderLayout.NORTH);

                JPanel button = new JPanel();
                JButton option = new JButton("Ligar");
                option.setActionCommand("Ligar" + this.room.getName(i).charAt(this.room.getName(i).length() - 1)); //Message send when the button is clicked
                option.addActionListener(this);
                button.add(option);

                option = new JButton("Desligar");
                option.setActionCommand("Desligar" + this.room.getName(i).charAt(this.room.getName(i).length() - 1)); //Message send when the button is clicked
                option.addActionListener(this);
                button.add(option);

                tmp.add(button, BorderLayout.CENTER);
            }
            else if (this.room.getName(i).matches("Temperatura ambiente")) {
                tmp.add(new JLabel(this.room.getName(i) + ": " + this.room.getInfo(i)), BorderLayout.NORTH);
            }
            else {
                //Agua (??);
            }
            
            this.panel.add(tmp);
        }
        //panel.add(button); // Adiciona botão ao painel
        //f.add(picLabel);
        this.f.setVisible(true);
    }
    
    private void updateLabel(String obj, String info) {
    	for (Component c1: this.panel.getComponents()) {
    		if (c1 instanceof JPanel) {
	    		for (Component c2: ((JPanel)c1).getComponents()) {
	    			if (c2 instanceof JLabel && ((JLabel)c2).getText().contains(obj)) {
	    				((JLabel)c2).setText(obj + ": " + info);
	    			}
	    		}
    		}
    		
    	}
    }

    public void actionPerformed(ActionEvent e) {

        if ("Ligar1".equals(e.getActionCommand())) {
        	DataStruct tmp = this.room.getInfo("Lampada1");
        	Main.master.write(tmp.reg, tmp.newBit(1));
        	this.updateLabel("Lampada1", "Ligado");
        }
        else if ("Ligar2".equals(e.getActionCommand())) {
        	DataStruct tmp = this.room.getInfo("Lampada2");
        	Main.master.write(tmp.reg, tmp.newBit(1));
        	this.updateLabel("Lampada2", "Ligado");
        }
        else if ("Ligar3".equals(e.getActionCommand())) {
        	DataStruct tmp = this.room.getInfo("Lampada3");
        	Main.master.write(tmp.reg, tmp.newBit(1));
        	this.updateLabel("Lampada3", "Ligado");
        }
        else if ("Desligar1".equals(e.getActionCommand())) {
        	DataStruct tmp = this.room.getInfo("Lampada1");
        	Main.master.write(tmp.reg, tmp.newBit(0));
        	this.updateLabel("Lampada1", "Desligado");
        }
        else if ("Desligar2".equals(e.getActionCommand())) {
        	DataStruct tmp = this.room.getInfo("Lampada2");
        	Main.master.write(tmp.reg, tmp.newBit(0));
        	this.updateLabel("Lampada2", "Desligado");
        }
        else if ("Desligar3".equals(e.getActionCommand())) {
        	DataStruct tmp = this.room.getInfo("Lampada3");
        	Main.master.write(tmp.reg, tmp.newBit(0));
        	this.updateLabel("Lampada3", "Desligado");
        }
    }

}
