import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

class InfoWindow implements ActionListener{
    private JFrame f;
    //private JButton button;
    private JPanel panel;
    //JLabel picLabel;

    public InfoWindow(Room r){
        /*try{
            BufferedImage pic = ImageIO.read(new File("teste.png"));
            button = new JButton(new ImageIcon(pic));
        }catch(Exception e){
            return;
        }*/

        f = new JFrame(r.roomName);
        panel = new JPanel();

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
                option.setActionCommand("Oi"); //Message send when the button is clicked
                option.addActionListener(this);
                button.add(option);

                option = new JButton("Desligar");
                option.setActionCommand("Tchau"); //Message send when the button is clicked
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
        if ("Oi".equals(e.getActionCommand())) {
            System.out.println("Oi");
        }else if ("Tchau".equals(e.getActionCommand())){
            System.out.println("Tchau");
        }
    }

}
