import java.io.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

class Bedroom extends Room{
    public Bedroom(File image){
        super();

        newLamp(1);
        newAlarm();
        newTemperature();

        try{
            picture = ImageIO.read(image);
        }catch(Exception e){
            System.err.println("Couldn't find file");
            System.exit(-1);
        }
    }

    public void openInfoWindow(){
        new InfoWindow(this);
    }
}
