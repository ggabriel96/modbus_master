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
    	int i, j;
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
        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //panel.setLayout(new BorderLayout());

        for (i = 0, j = 0; i < this.room.getDataSize(); i++, j++) {
            JPanel tmp = new JPanel(); //Painel para campo[i]
            tmp.setLayout(new BorderLayout());

            if (this.room.getName(i).matches("Lampada [0-9]") || (this.room.getName(i).matches("Sensor de Alarme") && this.room.roomName.equals("Garagem")) || (this.room.getName(i).contains("Agua"))) {
                //if r.getInfo(i) is equal to 0, the light is off, else the ligth is on
                tmp.add(new JLabel(this.room.getName(i) + ": " + (this.room.getInfo(i) == 0 ? "Desligado" : "Ligado")), BorderLayout.NORTH);

                JPanel button = new JPanel();
                JButton option = new JButton("Ligar");
                option.setActionCommand("Ligar" + this.room.getName(i).substring(this.room.getName(i).length() - 2)); //Message sent when the button is clicked
                option.addActionListener(this);
                button.add(option);

                option = new JButton("Desligar");
                option.setActionCommand("Desligar" + this.room.getName(i).substring(this.room.getName(i).length() - 2)); //Message sent when the button is clicked
                option.addActionListener(this);
                button.add(option);

                tmp.add(button, BorderLayout.CENTER);
                
                j += 4;
            }
            else if (this.room.getName(i).matches("Sensor de Alarme")) {
            	tmp.add(new JLabel(this.room.getName(i) + ": " + (this.room.getInfo(i) == 0 ? "Desligado" : "Ligado")), BorderLayout.NORTH);
            }
            else if (this.room.getName(i).contains("Temperatura") || this.room.getName(i).contains("Nivel")) {
            	tmp.add(new JLabel(this.room.getName(i) + ": " + this.room.getInfo(i)), BorderLayout.NORTH);
            	
            	if (this.room.getName(i).matches("Nivel da agua da banheira")) {
            		JPanel button = new JPanel();
                    JButton option = new JButton("Esvaziar");
                    option.setActionCommand("Esvaziar"); //Message sent when the button is clicked
                    option.addActionListener(this);
                    button.add(option);
                    
                    tmp.add(button, BorderLayout.CENTER);
                    j += 4;
            	}
            }
            else {
                //Agua (??);
            }
            
            this.panel.add(tmp);
        }
        //panel.add(button); // Adiciona botão ao painel
        //f.add(picLabel);
        this.f.setVisible(true);
        this.f.setSize(270, j * 20); //Tamanho da janela
        
        this.f.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            	CustomJPanel.closed((JFrame)windowEvent.getComponent());
            }
        });
    }
    
    public void updateLabel(String obj, String info) {
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
        if ("Ligar 1".equals(e.getActionCommand())) {
        	DataStruct tmp = this.room.getInfo("Lampada 1");
        	Main.master.write(tmp.reg, tmp.newBit(1));
        	this.updateLabel("Lampada 1", "Ligado");
        }
        else if ("Ligar 2".equals(e.getActionCommand())) {
        	DataStruct tmp = this.room.getInfo("Lampada 2");
        	Main.master.write(tmp.reg, tmp.newBit(1));
        	this.updateLabel("Lampada 2", "Ligado");
        }
        else if ("Ligar 3".equals(e.getActionCommand())) {
        	DataStruct tmp = this.room.getInfo("Lampada 3");
        	Main.master.write(tmp.reg, tmp.newBit(1));
        	this.updateLabel("Lampada 3", "Ligado");
        }
        // ligar agua quente da piscina
        else if ("Ligarte".equals(e.getActionCommand())) {
        	DataStruct tmp = this.room.getInfo("Agua quente");
        	Main.master.write(tmp.reg, tmp.newBit(1));
        	this.updateLabel("Agua quente", "Ligado");
        }
        // ligar agua fria da piscina
        else if ("Ligaria".equals(e.getActionCommand())) {
        	DataStruct tmp = this.room.getInfo("Agua fria");
        	Main.master.write(tmp.reg, tmp.newBit(1));
        	this.updateLabel("Agua fria", "Ligado");
        }
        // ligar alarme
        else if ("Ligarme".equals(e.getActionCommand())) {
        	DataStruct tmp = this.room.getInfo("Sensor de Alarme");
        	Main.master.write(tmp.reg, tmp.newBit(1));
        	this.updateLabel("Alarme", "Ligado");
        }
        else if ("Desligar 1".equals(e.getActionCommand())) {
        	DataStruct tmp = this.room.getInfo("Lampada 1");
        	Main.master.write(tmp.reg, tmp.newBit(0));
        	this.updateLabel("Lampada 1", "Desligado");
        }
        else if ("Desligar 2".equals(e.getActionCommand())) {
        	DataStruct tmp = this.room.getInfo("Lampada 2");
        	Main.master.write(tmp.reg, tmp.newBit(0));
        	this.updateLabel("Lampada 2", "Desligado");
        }
        else if ("Desligar 3".equals(e.getActionCommand())) {
        	DataStruct tmp = this.room.getInfo("Lampada 3");
        	Main.master.write(tmp.reg, tmp.newBit(0));
        	this.updateLabel("Lampada 3", "Desligado");
        }
        // desligar agua quente da piscina
        else if ("Desligarte".equals(e.getActionCommand())) {
        	DataStruct tmp = this.room.getInfo("Agua quente");
        	Main.master.write(tmp.reg, tmp.newBit(0));
        	this.updateLabel("Agua quente", "Desligado");
        }
        // desligar agua fria da piscina
        else if ("Desligaria".equals(e.getActionCommand())) {
        	DataStruct tmp = this.room.getInfo("Agua fria");
        	Main.master.write(tmp.reg, tmp.newBit(0));
        	this.updateLabel("Agua fria", "Desligado");
        }
        // desligar alarme
        else if ("Desligarme".equals(e.getActionCommand())) {
        	DataStruct tmp = this.room.getInfo("Alarme");
        	Main.master.write(tmp.reg, tmp.newBit(0));
        	this.updateLabel("Alarme", "Desligado");
        }
        else if ("Esvaziar".equals(e.getActionCommand())) {
        	DataStruct tmp = this.room.getInfo("Nivel da agua da banheira");
        	Main.master.write(tmp.reg, tmp.newBit(1));
        	Main.master.write(7, 0);
        	this.updateLabel("Nivel da agua da banheira", tmp.info + "");
        }
    }

}
