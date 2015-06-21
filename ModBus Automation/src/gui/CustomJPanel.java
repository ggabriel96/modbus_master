package gui;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class CustomJPanel extends JPanel implements MouseListener{

	//??
	private static final long serialVersionUID = 1L;

	private Image backgroundImage;
    private Room pool = new Room();

    //pool.addLamp(1, );

    public CustomJPanel(String fileName){

        try{
            backgroundImage = ImageIO.read(new File(fileName));
        }catch(Exception e){
            System.out.println("Erro ao carregar imagem!");
            System.exit(-1);
        }

        pool.newLamp(1, 13, 3);
        pool.newLamp(2, 13, 4);
        pool.newLamp(3, 13, 5);
        pool.newAlarm(10, 1);
        pool.newTemperature(0);

        addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, this);
    }

    public void eventOutput(String eventDescription, MouseEvent e) {
       // System.out.println(eventDescription + " detected on " + e.getComponent().getClass().getName() + ".");
       // System.out.println("X: " + e.getX() + "      Y: " + e.getY());
    }

    public void mousePressed(MouseEvent e) {
        eventOutput("Mouse pressed (# of clicks: "
                + e.getClickCount() + ")", e);


    }

    public void mouseReleased(MouseEvent e) {
        eventOutput("Mouse released (# of clicks: "
                + e.getClickCount() + ")", e);
    }

    public void mouseEntered(MouseEvent e) {
        eventOutput("Mouse entered", e);

    }

    public void mouseExited(MouseEvent e) {
        eventOutput("Mouse exited", e);
    }

    public void mouseClicked(MouseEvent e) {
        eventOutput("Mouse clicked (# of clicks: " + e.getClickCount() + ")", e);
        if(e.getX() >= 240 && e.getX() <= 860 && e.getY() >= 13 && e.getY() <= 408){
	        pool.openInfoWindow();
        }
    }

}
