import java.io.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

class PlayRoom extends Room{
    public PlayRoom(){
        super();

        newLamp(1);
        newLamp(2);
        newAlarm();
        newTemperature();

        try{
            picture = ImageIO.read(new File("assets/playroom.jpg"));
        }catch(Exception e){
            System.err.println("Couldn't find file");
            System.exit(-1);
        }
    }

    public void openInfoWindow(){
        new InfoWindow(this);
    }
}
