import java.io.*;
import java.awt.*;
//import java.lang.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

class InfoWindow{
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
        f.setSize(200, 110); //Tamanho da janela
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for(int i = 0; i < r.getInfoSize(); i++){
            panel.add(new JLabel(r.getName(i) + ": " + r.getInfo(i)));
        }
        //panel.add(button); // Adiciona botão ao painel
        //f.add(picLabel);
        f.setVisible(true);
    }

}
