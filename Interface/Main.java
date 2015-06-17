import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

class Main{

    public static void main(String[] args){
        //?? Funciona, só aceite :P
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
           public void run() {
               (new Main()).createAndShowGUI();
           }
        });
    }

    private void createAndShowGUI(){

        //PlayRoom playRoom = new PlayRoom();
        Bathroom bath1, lavatorio;
        bath1 = new Bathroom(new File("assets/bathroom2.jpg"));
        lavatorio = new Bathroom(new File("assets/lavatorio.jpg"));

        Bedroom b1, b2;
        b1 = new Bedroom(new File("assets/bedroom1.jpg"));
        b2 = new Bedroom(new File("assets/bedroom2.jpg"));

        Circulacao circulacao = new Circulacao();

        //Create and set up the window
        JFrame frame = new JFrame("Casa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Montando casa
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        Button tmp = new Button(circulacao);
        panel.add(tmp, BorderLayout.NORTH);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        tmp = new Button(lavatorio);
        panel2.add(tmp, BorderLayout.NORTH);
        tmp = new Button(bath1);
        panel2.add(tmp, BorderLayout.SOUTH);

        panel.add(panel2, BorderLayout.CENTER);

        tmp = new Button(b1);
        panel.add(tmp, BorderLayout.EAST);
        tmp = new Button(b2);
        panel.add(tmp, BorderLayout.WEST);

        JPanel last = new JPanel();
        last.add(panel);

        //Create and set up the content pane.
        //Button pR = new Button(playRoom);
        //pR.setOpaque(true); //content panes must be opaque
        frame.setContentPane(last);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
