package gui;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

class InfoWindow implements ActionListener{
    private JFrame f;
    private JPanel panel;
    private Room room;
    //private JButton button;
    //JLabel picLabel;

    public InfoWindow(Room r){
        /*try{
            BufferedImage pic = ImageIO.read(new File("teste.png"));
            button = new JButton(new ImageIcon(pic));
        }catch(Exception e){
            return;
        }*/

    	panel = new JPanel();
        f = new JFrame(r.roomName);
        room = r;

        f.add(panel); //Adiciona painel ao frame
        f.setSize(225, 300); //Tamanho da janela
        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //panel.setLayout(new BorderLayout());

        for(int i = 0; i < r.getDataSize(); i++){
            JPanel tmp = new JPanel(); //Painel para campo[i]
            tmp.setLayout(new BorderLayout());

            if(r.getName(i).matches("Lampada[0-9]") || r.getName(i).matches("Sensor de Alarme")){
                //if r.getInfo(i) is equal to 0, the light is off, else the ligth is on
                tmp.add(new JLabel(r.getName(i) + ": " + (r.getInfo(i) == 0 ? "Desligado" : "Ligado")), BorderLayout.NORTH);

                JPanel button = new JPanel();
                JButton option = new JButton("Ligar");
                option.setActionCommand("Ligar" + r.getName(i).charAt(r.getName(i).length() - 1)); //Message send when the button is clicked
                option.addActionListener(this);
                button.add(option);

                option = new JButton("Desligar");
                option.setActionCommand("Desligar" + r.getName(i).charAt(r.getName(i).length() - 1)); //Message send when the button is clicked
                option.addActionListener(this);
                button.add(option);

                tmp.add(button, BorderLayout.CENTER);

            }else if(r.getName(i).matches("Temperatura ambiente")){
                tmp.add(new JLabel(r.getName(i) + ": " + r.getInfo(i)), BorderLayout.NORTH);
            }else{
                //Agua (??);
            }
            panel.add(tmp);

        }
        //panel.add(button); // Adiciona botão ao painel
        //f.add(picLabel);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if ("Ligar1".equals(e.getActionCommand())) {
        	DataStruct tmp = room.getInfo("Lampada1");
        	Main.master.write(tmp.reg, tmp.newBit(1));
        } else if ("Ligar2".equals(e.getActionCommand())) {
            System.out.println("Ligar2");
        } else if ("Ligar3".equals(e.getActionCommand())) {
            System.out.println("Ligar3");
        } else if ("Desligar1".equals(e.getActionCommand())) {
            System.out.println("Desligar1");
        } else if ("Desligar2".equals(e.getActionCommand())) {
            System.out.println("Desligar2");
        } else if ("Desligar3".equals(e.getActionCommand())) {
            System.out.println("Desligar3");
        }
    }

}
