package gui;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class CustomJPanel extends JPanel implements MouseListener {
	private Room[] rooms;
	private Image backgroundImage;
	public static boolean windows[];
	private static final long serialVersionUID = 1L;
	public static final int ROOMS_QTTY = 20, POOL = 0, BATH2 = 1, STORAGE = 2, BATH3 = 3, POOL_BALC = 4,
			SUITE = 5, CLOSET = 6, BEDROOM2 = 7, BATH1 = 8, WASHROOM = 9, HALL = 10, BEDROOM1 = 11, PLAYROOM = 12,
			DINING = 13, LIVING = 14, BALCONY = 15, KITCHEN = 16, LAVATORY = 17, LAUNDRY = 18, GARAGE = 19;
	
    public CustomJPanel(String fileName) {
        try {
            backgroundImage = ImageIO.read(new File(fileName));
        }
        catch (Exception e) {
            System.out.println("Erro ao carregar imagem!");
            System.exit(-1);
        }
        
        this.rooms = new Room[CustomJPanel.ROOMS_QTTY];
        CustomJPanel.windows = new boolean[CustomJPanel.ROOMS_QTTY];
        for (int i = 0; i < CustomJPanel.ROOMS_QTTY; i++) {
        	this.rooms[i] = new Room();
        	CustomJPanel.windows[i] = false;
        }
        
        this.rooms[CustomJPanel.POOL].setName("Piscina");
        this.rooms[CustomJPanel.POOL].newLamp(1, 13, 3);
        this.rooms[CustomJPanel.POOL].newLamp(2, 13, 4);
        this.rooms[CustomJPanel.POOL].newLamp(3, 13, 5);
        this.rooms[CustomJPanel.POOL].newWaterIO(8, 5, "fria");
        this.rooms[CustomJPanel.POOL].newWaterIO(8, 4, "quente");
        this.rooms[CustomJPanel.POOL].newAlarm(10, 1);
        this.rooms[CustomJPanel.POOL].newTemperature(0, "da agua");
        
        this.rooms[CustomJPanel.BATH2].setName("Banheiro da suite");
        this.rooms[CustomJPanel.BATH2].newLamp(1, 15, 4);
        this.rooms[CustomJPanel.BATH2].newWaterIO(8, 2, "fria");
        this.rooms[CustomJPanel.BATH2].newWaterIO(8, 1, "quente");
        this.rooms[CustomJPanel.BATH2].newBath(7);
        this.rooms[CustomJPanel.BATH2].newTemperature(1, "da agua da banheira");
        
        this.rooms[CustomJPanel.STORAGE].setName("Deposito");
        this.rooms[CustomJPanel.STORAGE].newLamp(1, 16, 1);
        
        this.rooms[CustomJPanel.BATH3].setName("Banheiro externo");
        this.rooms[CustomJPanel.BATH3].newLamp(1, 16, 0);
        
        this.rooms[CustomJPanel.POOL_BALC].setName("Varanda da piscina");
        this.rooms[CustomJPanel.POOL_BALC].newLamp(1, 16, 2);
        
        this.rooms[CustomJPanel.SUITE].setName("Suite");
        this.rooms[CustomJPanel.SUITE].newLamp(1, 15, 5);
        this.rooms[CustomJPanel.SUITE].newLamp(2, 15, 6);
        this.rooms[CustomJPanel.SUITE].newLamp(3, 15, 7);
        this.rooms[CustomJPanel.SUITE].newAlarm(10, 5);
        this.rooms[CustomJPanel.SUITE].newTemperature(2, "ambiente");
        
        this.rooms[CustomJPanel.CLOSET].setName("Closet");
        this.rooms[CustomJPanel.CLOSET].newLamp(1, 15, 3);
        
        this.rooms[CustomJPanel.BEDROOM2].setName("Dormitorio 2");
        this.rooms[CustomJPanel.BEDROOM2].newLamp(1, 15, 2);
        this.rooms[CustomJPanel.BEDROOM2].newAlarm(10, 7);
        this.rooms[CustomJPanel.BEDROOM2].newTemperature(6, "ambiente");
        
        this.rooms[CustomJPanel.BATH1].setName("Banheiro dos dormitorios");
        this.rooms[CustomJPanel.BATH1].newLamp(1, 15, 0);
        
        this.rooms[CustomJPanel.WASHROOM].setName("Lavatorio");
        this.rooms[CustomJPanel.WASHROOM].newLamp(1, 16, 6);
        
        this.rooms[CustomJPanel.HALL].setName("Circulacao");
        this.rooms[CustomJPanel.HALL].newLamp(1, 15, 1);
        
        this.rooms[CustomJPanel.BEDROOM1].setName("Dormitorio 1");
        this.rooms[CustomJPanel.BEDROOM1].newLamp(1, 14, 7);
        this.rooms[CustomJPanel.BEDROOM1].newAlarm(10, 6);
        this.rooms[CustomJPanel.BEDROOM1].newTemperature(5, "ambiente");
        
        this.rooms[CustomJPanel.PLAYROOM].setName("Sala de Jogos");
        this.rooms[CustomJPanel.PLAYROOM].newLamp(1, 14, 5);
        this.rooms[CustomJPanel.PLAYROOM].newLamp(2, 14, 6);
        this.rooms[CustomJPanel.PLAYROOM].newAlarm(10, 4);
        this.rooms[CustomJPanel.PLAYROOM].newTemperature(4, "ambiente");
        
        this.rooms[CustomJPanel.DINING].setName("Sala de Jantar");
        this.rooms[CustomJPanel.DINING].newLamp(1, 14, 3);
        this.rooms[CustomJPanel.DINING].newLamp(2, 14, 4);
        
        this.rooms[CustomJPanel.LIVING].setName("Sala de Estar");
        this.rooms[CustomJPanel.LIVING].newLamp(1, 14, 0);
        this.rooms[CustomJPanel.LIVING].newLamp(2, 14, 1);
        this.rooms[CustomJPanel.LIVING].newLamp(3, 14, 2);
        this.rooms[CustomJPanel.LIVING].newAlarm(10, 3);
        this.rooms[CustomJPanel.LIVING].newTemperature(3, "ambiente");
        
        this.rooms[CustomJPanel.BALCONY].setName("Varanda");
        this.rooms[CustomJPanel.BALCONY].newLamp(1, 16, 7);
        
        this.rooms[CustomJPanel.KITCHEN].setName("Cozinha");
        this.rooms[CustomJPanel.KITCHEN].newLamp(1, 17, 0);
        this.rooms[CustomJPanel.KITCHEN].newLamp(2, 17, 1);
        this.rooms[CustomJPanel.KITCHEN].newLamp(3, 17, 2);
        this.rooms[CustomJPanel.KITCHEN].newAlarm(10, 2);
        
        this.rooms[CustomJPanel.LAVATORY].setName("Lavabo");
        this.rooms[CustomJPanel.LAVATORY].newLamp(1, 17, 3);
        
        this.rooms[CustomJPanel.LAUNDRY].setName("Lavanderia");
        this.rooms[CustomJPanel.LAUNDRY].newLamp(1, 17, 4);
        
        this.rooms[CustomJPanel.GARAGE].setName("Garagem");
        this.rooms[CustomJPanel.GARAGE].newLamp(1, 17, 5);
        this.rooms[CustomJPanel.GARAGE].newAlarm(10, 0);

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
        if (e.getX() >= 240 && e.getX() <= 860 && e.getY() >= 13 && e.getY() <= 408 && !windows[CustomJPanel.POOL]) {
        	CustomJPanel.windows[CustomJPanel.POOL] = true;
        	this.rooms[CustomJPanel.POOL].openInfoWindow();
        }
        else if (e.getX() >= 15 && e.getX() <= 180 && e.getY() >= 570 && e.getY() <= 660 && !windows[CustomJPanel.BATH2]) {
        	CustomJPanel.windows[CustomJPanel.BATH2] = true;
        	this.rooms[CustomJPanel.BATH2].openInfoWindow();
        }
        else if (e.getX() >= 15 && e.getX() <= 100 && e.getY() >= 95 && e.getY() <= 225 && !windows[CustomJPanel.STORAGE]) {
        	CustomJPanel.windows[CustomJPanel.STORAGE] = true;
        	this.rooms[CustomJPanel.STORAGE].openInfoWindow();
        }
        else if (e.getX() >= 110 && e.getX() <= 180 && e.getY() >= 95 && e.getY() <= 225 && !windows[CustomJPanel.BATH3]) {
        	CustomJPanel.windows[CustomJPanel.BATH3] = true;
        	this.rooms[CustomJPanel.BATH3].openInfoWindow();
        }
        else if (e.getX() >= 15 && e.getX() <= 180 && e.getY() >= 240 && e.getY() <= 415 && !windows[CustomJPanel.SUITE]) {
        	CustomJPanel.windows[CustomJPanel.SUITE] = true;
        	this.rooms[CustomJPanel.SUITE].openInfoWindow();
        }
        else if (e.getX() >= 15 && e.getX() <= 180 && e.getY() >= 416 && e.getY() <= 560 && !windows[CustomJPanel.CLOSET]) {
        	CustomJPanel.windows[CustomJPanel.CLOSET] = true;
        	this.rooms[CustomJPanel.CLOSET].openInfoWindow();
        }
        else if (e.getX() >= 190 && e.getX() <= 235 && e.getY() >= 235 && e.getY() <= 405 && !windows[CustomJPanel.POOL_BALC]) {
        	CustomJPanel.windows[CustomJPanel.POOL_BALC] = true;
        	this.rooms[CustomJPanel.POOL_BALC].openInfoWindow();
        }
        else if (e.getX() >= 190 && e.getX() <= 335 && e.getY() >= 475 && e.getY() <= 660 && !windows[CustomJPanel.BEDROOM2]) {
        	CustomJPanel.windows[CustomJPanel.BEDROOM2] = true;
        	this.rooms[CustomJPanel.BEDROOM2].openInfoWindow();
        }
        else if (e.getX() >= 240 && e.getX() <= 575 && e.getY() >= 415 && e.getY() <= 460 && !windows[CustomJPanel.HALL]) {
        	CustomJPanel.windows[CustomJPanel.HALL] = true;
        	this.rooms[CustomJPanel.HALL].openInfoWindow();
        }
        else if (e.getX() >= 345 && e.getX() <= 420 && e.getY() >= 475 && e.getY() <= 560 && !windows[CustomJPanel.WASHROOM]) {
        	CustomJPanel.windows[CustomJPanel.WASHROOM] = true;
        	this.rooms[CustomJPanel.WASHROOM].openInfoWindow();
        }
        else if (e.getX() >= 345 && e.getX() <= 420 && e.getY() >= 570 && e.getY() <= 660 && !windows[CustomJPanel.BATH1]) {
        	CustomJPanel.windows[CustomJPanel.BATH1] = true;
        	this.rooms[CustomJPanel.BATH1].openInfoWindow();
        }
        else if (e.getX() >= 430 && e.getX() <= 575 && e.getY() >= 475 && e.getY() <= 660 && !windows[CustomJPanel.BEDROOM1]) {
        	CustomJPanel.windows[CustomJPanel.BEDROOM1] = true;
        	this.rooms[CustomJPanel.BEDROOM1].openInfoWindow();
        }
        else if (e.getX() >= 590 && e.getX() <= 860 && e.getY() >= 415 && e.getY() <= 660 && !windows[CustomJPanel.PLAYROOM]) {
        	CustomJPanel.windows[CustomJPanel.PLAYROOM] = true;
        	this.rooms[CustomJPanel.PLAYROOM].openInfoWindow();
        }
        else if (e.getX() >= 870 && e.getX() <= 1010 && e.getY() >= 95 && e.getY() <= 370 && !windows[CustomJPanel.KITCHEN]) {
        	CustomJPanel.windows[CustomJPanel.KITCHEN] = true;
        	this.rooms[CustomJPanel.KITCHEN].openInfoWindow();
        }
        else if (e.getX() >= 1025 && e.getX() <= 1130 && e.getY() >= 95 && e.getY() <= 200 && !windows[CustomJPanel.LAUNDRY]) {
        	CustomJPanel.windows[CustomJPanel.LAUNDRY] = true;
        	this.rooms[CustomJPanel.LAUNDRY].openInfoWindow();
        }
        else if (e.getX() >= 1060 && e.getX() <= 1130 && e.getY() >= 265 && e.getY() <= 360 && !windows[CustomJPanel.LAVATORY]) {
        	CustomJPanel.windows[CustomJPanel.LAVATORY] = true;
        	this.rooms[CustomJPanel.LAVATORY].openInfoWindow();
        }
        else if (e.getX() >= 870 && e.getX() <= 1030 && e.getY() >= 375 && e.getY() <= 665 && !windows[CustomJPanel.DINING]) {
        	CustomJPanel.windows[CustomJPanel.DINING] = true;
        	this.rooms[CustomJPanel.DINING].openInfoWindow();
        }
        else if (e.getX() >= 1031 && e.getX() <= 1200 && e.getY() >= 375 && e.getY() <= 665 && !windows[CustomJPanel.LIVING]) {
        	CustomJPanel.windows[CustomJPanel.LIVING] = true;
        	this.rooms[CustomJPanel.LIVING].openInfoWindow();
        }
        else if (e.getX() >= 1140 && e.getX() <= 1400 && e.getY() >= 95 && e.getY() <= 360 && !windows[CustomJPanel.GARAGE]) {
        	CustomJPanel.windows[CustomJPanel.GARAGE] = true;
        	this.rooms[CustomJPanel.GARAGE].openInfoWindow();
        }
        else if (e.getX() >= 1290 && e.getX() <= 1400 && e.getY() >= 375 && e.getY() <= 485 && !windows[CustomJPanel.BALCONY]) {
        	CustomJPanel.windows[CustomJPanel.BALCONY] = true;
        	this.rooms[CustomJPanel.BALCONY].openInfoWindow();
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
    
    public static int arrayIndex(JFrame f) {
    	switch (f.getTitle()) {
		case "Piscina":
			return CustomJPanel.POOL;
		case "Banheiro da suite":
			return CustomJPanel.BATH2;
		case "Deposito":
			return CustomJPanel.STORAGE;
		case "Banheiro externo":
			return CustomJPanel.BATH3;
		case "Varanda da piscina":
			return CustomJPanel.POOL_BALC;
		case "Suite":
			return CustomJPanel.SUITE;
		case "Closet":
			return CustomJPanel.CLOSET;
		case "Dormitorio 2":
			return CustomJPanel.BEDROOM2;
		case "Banheiro dos dormitorios":
			return CustomJPanel.BATH1;
		case "Lavatorio":
			return CustomJPanel.WASHROOM;
		case "Circulacao":
			return CustomJPanel.HALL;
		case "Dormitorio 1":
			return CustomJPanel.BEDROOM1;
		case "Sala de Jogos":
			return CustomJPanel.PLAYROOM;
		case "Sala de Jantar":
			return CustomJPanel.DINING;
		case "Sala de Estar":
			return CustomJPanel.LIVING;
		case "Varanda":
			return CustomJPanel.BALCONY;
		case "Cozinha":
			return CustomJPanel.KITCHEN;
		case "Lavabo":
			return CustomJPanel.LAVATORY;
		case "Lavanderia":
			return CustomJPanel.LAUNDRY;
		case "Garagem":
			return CustomJPanel.GARAGE;
		default:
			return -1;
    	}
    }
    
    public static void closed(JFrame f) {
    	int i = CustomJPanel.arrayIndex(f);
    	CustomJPanel.windows[i] = false;
    	
    	if (!f.getTitle().equals("Banheiro da suite")) {
    		InfoWindow.threads[i].stop();
    	}
    }

    public void mouseClicked(MouseEvent e) {
    }

}
