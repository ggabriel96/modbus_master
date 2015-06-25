package gui;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class CustomJPanel extends JPanel implements MouseListener {
	public static final int POOLW = 0, BATH2W = 1;
	private static final long serialVersionUID = 1L;
	private Room pool = new Room("Piscina");
	private Room bathroom2 = new Room("Banheiro da suite");
	private Image backgroundImage;
	private static boolean windows[];

    public CustomJPanel(String fileName) {
        try {
            backgroundImage = ImageIO.read(new File(fileName));
        }
        catch (Exception e) {
            System.out.println("Erro ao carregar imagem!");
            System.exit(-1);
        }
        
        CustomJPanel.windows = new boolean[10];
        for (int i = 0; i < 10; i++) windows[i] = false;
        
        this.pool.newLamp(1, 13, 3);
        this.pool.newLamp(2, 13, 4);
        this.pool.newLamp(3, 13, 5);
        this.pool.newWaterIO(8, 4, "quente");
        this.pool.newWaterIO(8, 5, "fria");
        this.pool.newTemperature(0, "da agua");
        this.pool.newAlarm(10, 1);
        
        this.bathroom2.newLamp(1, 15, 4);
        this.bathroom2.newBath(7);
        this.bathroom2.newTemperature(1, "da agua da banheira");

        addMouseListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, this);
    }

    public void eventOutput(String eventDescription, MouseEvent e) {
//    	System.out.println(eventDescription + " detected on " + e.getComponent().getClass().getName() + ".");
//       System.out.println("X: " + e.getX() + "      Y: " + e.getY());
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    	this.eventOutput("Mouse clicked (# of clicks: " + e.getClickCount() + ")", e);
        if (e.getX() >= 240 && e.getX() <= 860 && e.getY() >= 13 && e.getY() <= 408 && !windows[CustomJPanel.POOLW]) {
        	CustomJPanel.windows[CustomJPanel.POOLW] = true;
	        this.pool.openInfoWindow();
        }
        else if (e.getX() >= 15 && e.getX() <= 180 && e.getY() >= 570 && e.getY() <= 660 && !windows[CustomJPanel.BATH2W]) {
        	CustomJPanel.windows[CustomJPanel.BATH2W] = true;
        	this.bathroom2.openInfoWindow();
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
    
    public static void closed(JFrame f) {
    	switch (f.getTitle()) {
    		case "Piscina":
    			CustomJPanel.windows[CustomJPanel.POOLW] = false;
    			break;
    		case "Banheiro da suite":
    			CustomJPanel.windows[CustomJPanel.BATH2W] = false;
    			break;
    		default:
    			break;
    	}
    }

    public void mouseClicked(MouseEvent e) {
    }

}
